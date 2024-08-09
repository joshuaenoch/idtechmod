package com.idtech.item;

import com.idtech.entity.CustomFireball;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FireballLauncher extends Item {

    public FireballLauncher(Properties properties) {
        super(properties);
    }

    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);

    //static instance for registration
    public static Item INSTANCE = new FireballLauncher(properties).setRegistryName("fireballlauncher");

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        double x = playerIn.getX();
        double y = playerIn.getY();
        double z = playerIn.getZ();
        Vec3 look = playerIn.getViewVector(1.0F);

        // initialize and summon fireball
        double speed = 5;
        CustomFireball fireball = new CustomFireball(level, playerIn, look.x, look.y, look.z, 5);
        fireball.setPos(x, y, z);
        fireball.setDeltaMovement(look.x * speed, look.y * speed, look.z * speed);
        level.addFreshEntity(fireball);

        // Get the held item for return
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        // Return pass
        return InteractionResultHolder.pass(itemstack);
    }
}