package com.idtech.block;
import com.idtech.BaseMod;
import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class LuckyBlock23 extends Block
{
    private static Properties properties = Properties.of(Material.STONE);

    public static Block INSTANCE = new LuckyBlock23(properties).setRegistryName(BaseMod.MODID, "lucky");
    public static Item ITEM = BlockUtils.createBlockItem(INSTANCE, CreativeModeTab.TAB_MISC);

    public LuckyBlock23(Properties properties){
        super(properties);
    }

    @Override
    public void playerDestroy(Level world, Player player, BlockPos posIn, BlockState blockStateIn, BlockEntity entity, ItemStack stack) {
        int max = 5;
        int min = 1;
        int range = max - min + 1;
        int choice = (int)(Math.random() * range) + min;

        double x = posIn.getX();
        double y = posIn.getY() + 1;
        double z = posIn.getZ();

        FireworkRocketEntity firework = EntityType.FIREWORK_ROCKET.create(world);
        Utils.spawnEntity(world, firework, posIn.above().above());

        FireworkRocketEntity firework2 = EntityType.FIREWORK_ROCKET.create(world);
        Utils.spawnEntity(world, firework2, posIn.above().above());

        FireworkRocketEntity firework3 = EntityType.FIREWORK_ROCKET.create(world);
        Utils.spawnEntity(world, firework3, posIn.above().above());
        //player.sendMessage(new TextComponent(String.valueOf(choice)), player.getUUID());
        /*switch(choice){
            case 5:
                ItemStack itemStack = new ItemStack(Items.DIAMOND);
                itemStack.setCount(5);
                ItemEntity itemEntity = new ItemEntity(world, x, y, z, itemStack);
                world.addFreshEntity(itemEntity);
                break;
            case 2:
                Zombie zombie = EntityType.ZOMBIE.create(world);
                zombie.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 9999, 10));
                zombie.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 9999, 10));
                Utils.spawnEntity(world, zombie, posIn);
                break;
            case 3:
                player.kill();
                break;
            case 4:
                player.sendMessage(new TextComponent("Training montage..."), player.getUUID());
                player.giveExperiencePoints(10000);
                break;
            case 1:
                player.sendMessage(new TextComponent("Bye bye"), player.getUUID());
                player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 200, 2));
                break;
        }*/
    }
}
