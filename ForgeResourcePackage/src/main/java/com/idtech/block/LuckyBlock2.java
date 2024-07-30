package com.idtech.block;

import com.idtech.BaseMod;
import com.idtech.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class LuckyBlock2 extends Block {
    private static Properties properties = Properties.of(Material.STONE);

    public static Block INSTANCE = new LuckyBlock2(properties).setRegistryName(BaseMod.MODID, "luckyblock2");
    public static Item ITEM = BlockUtils.createBlockItem(INSTANCE, CreativeModeTab.TAB_MISC);

    private static final List<DelayedFireworkTask> tasks = new ArrayList<>();
    private static boolean fireworkTask = false;

    public LuckyBlock2(Properties properties){
        super(properties);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void playerDestroy(Level level, Player player, BlockPos blockPos, BlockState blockState, BlockEntity blockEntity, ItemStack itemStack) {
        tasks.add(new DelayedFireworkTask(level, blockPos, 0, new ItemStack(Items.DIAMOND)));
    }

    private static class DelayedFireworkTask {
        private final Level level;
        private final BlockPos blockPos;
        private final int delay;
        private final long scheduledTick;
        private final ItemStack itemStack;
        private SoundEvent soundEvent = null;

        public DelayedFireworkTask(Level level, BlockPos blockPos, int delay, ItemStack stack) {
            this.level = level;
            this.blockPos = blockPos;
            this.delay = delay;
            this.scheduledTick = level.getGameTime() + delay;
            this.itemStack = stack;
        }

        public DelayedFireworkTask(Level world, BlockPos pos, int delay, ItemStack stack, SoundEvent soundEvent) {
            this.level = world;
            this.blockPos = pos;
            this.delay = delay;
            this.scheduledTick = world.getGameTime() + delay;
            this.itemStack = stack;
            this.soundEvent = soundEvent;
        }

        public boolean shouldRun(long currentTick) {
            return currentTick >= scheduledTick;
        }

        public void run() {

            FireworkRocketEntity firework = EntityType.FIREWORK_ROCKET.create(level);
            Utils.spawnEntity(level, firework, blockPos.above().above());

            FireworkRocketEntity fireworkrocketentity = new FireworkRocketEntity(level, blockPos.getX(), blockPos.getY(),
                    blockPos.getZ(), new ItemStack(Items.FIREWORK_ROCKET));
            level.addFreshEntity(fireworkrocketentity);

            //popItem(blockPos, level, itemStack);

            if (soundEvent!=null){

                level.playSound((Player)null, blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                        soundEvent, SoundSource.BLOCKS, 3.0F, 1.0F);

            }

        }
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent event) {
        if(fireworkTask == true){
            if (event.phase == TickEvent.Phase.END) {
                Level world = Minecraft.getInstance().level;
                if (world != null) {
                    long currentTick = world.getGameTime();
                    if(tasks.isEmpty()){
                        fireworkTask = false;
                    }
                    tasks.removeIf(task -> {
                        if (task.shouldRun(currentTick)) {
                            task.run();
                            return true;
                        }
                        return false;
                    });
                }
            }
        }
    }
}
