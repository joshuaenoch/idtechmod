package com.idtech.block;

import com.idtech.BaseMod;
import com.idtech.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;

import java.util.ArrayList;
import java.util.List;

public class LuckyBlock extends Block {
    private static Properties properties = Properties.of(Material.STONE);

    public static Block INSTANCE = new LuckyBlock(properties).setRegistryName(BaseMod.MODID, "lucky");
    public static Item ITEM = BlockUtils.createBlockItem(INSTANCE, CreativeModeTab.TAB_MISC);

    private static final List<DelayedFireworkTask> tasks = new ArrayList<>();
    private static boolean fireworkTask = false;

    public LuckyBlock(Properties properties) {
        super(properties);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void playerDestroy(Level world, Player player, BlockPos posIn, BlockState blockStateIn, BlockEntity entity, ItemStack stack) {
        int choice = (int)(Math.random() * 5) + 1;
        if(choice == 1){
            fireworkTask = true;
            tasks.add(new DelayedFireworkTask(world, posIn, 0, new ItemStack(Items.DIAMOND)));
            tasks.add(new DelayedFireworkTask(world, posIn, 20,  new ItemStack(Items.EMERALD), SoundEvents.PIGLIN_DEATH));
            tasks.add(new DelayedFireworkTask(world, posIn, 40, new ItemStack(Items.GOLD_INGOT), SoundEvents.ZOMBIE_DEATH));
        } else if(choice == 2){
            Utils.createExplosion(world, posIn, 5);
        } else if(choice == 3){
            if (world != null && posIn != null) {
                Creeper creeper = EntityType.CREEPER.create(world);
                Zombie zombie = EntityType.ZOMBIE.create(world);

                if (creeper != null && zombie != null) {
                    zombie.setBaby(true);
                    creeper.moveTo(posIn.getX() + 0.5, posIn.getY(), posIn.getZ() + 0.5, 0.0F, 0.0F);
                    zombie.moveTo(posIn.getX() + 0.5, posIn.getY(), posIn.getZ() + 0.5, 0.0F, 0.0F);

                    world.addFreshEntity(zombie);
                    world.addFreshEntity(creeper);

                    creeper.startRiding(zombie);
                }
            }
        } else if(choice == 4){
            player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 160, 1));
            player.sendMessage(new TextComponent("Bye bye"), player.getUUID());
        } else if(choice == 5){
            world.setBlock(posIn, Blocks.DIAMOND_BLOCK.defaultBlockState(), 3);
        }
    }

    private static class DelayedFireworkTask {
        private final Level world;
        private final BlockPos pos;
        private final int delay;
        private final long scheduledTick;
        private final ItemStack stack;
        private final SoundEvent soundEvent;

        public DelayedFireworkTask(Level world, BlockPos pos, int delay, ItemStack stack) {
            this.world = world;
            this.pos = pos;
            this.delay = delay;
            this.scheduledTick = world.getGameTime() + delay;
            this.stack = stack;
            this.soundEvent = null;
        }

        public DelayedFireworkTask(Level world, BlockPos pos, int delay, ItemStack stack, SoundEvent soundEvent) {
            this.world = world;
            this.pos = pos;
            this.delay = delay;
            this.scheduledTick = world.getGameTime() + delay;
            this.stack = stack;
            this.soundEvent = soundEvent;
        }

        public boolean shouldRun(long currentTick) {
            return currentTick >= scheduledTick;
        }

        public void run() {
            Player player = Minecraft.getInstance().player;
            ItemEntity itemEntity = new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), stack);
            Utils.spawnEntity(world, itemEntity, pos.above());
            FireworkRocketEntity firework = EntityType.FIREWORK_ROCKET.create(world);
            Utils.spawnEntity(world, firework, pos.above().above());
            if(soundEvent != null){
                world.playSound(Minecraft.getInstance().player, pos, soundEvent, SoundSource.BLOCKS, 10, 10);
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
