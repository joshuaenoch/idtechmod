package com.idtech.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ArrowLauncherItem extends Item {

    public ArrowLauncherItem(Properties properties) {
        super(properties);
    }

    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);

    //static instance for registration
    public static Item INSTANCE = new ArrowLauncherItem(properties).setRegistryName("arrowlauncher");

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        // Get player's eye position
        double eyeX = playerIn.getX();
        double eyeY = playerIn.getY() + playerIn.getEyeHeight(playerIn.getPose());
        double eyeZ = playerIn.getZ();

        // Get player's look vector
        double lookX = playerIn.getViewVector(1.0F).x();
        double lookY = playerIn.getViewVector(1.0F).y();
        double lookZ = playerIn.getViewVector(1.0F).z();

        // Create primed TNT entity at eye position
        Arrow arrow = new Arrow(level, eyeX, eyeY, eyeZ);

        // Set initial motion for shooting straight forward
        double speed = 10; // Adjust this value for initial speed
        arrow.shoot(lookX, lookY, lookZ, (float) speed, 0.0F);

        // Add the primed TNT entity to the world
        level.addFreshEntity(arrow);

        // Get the held item for return
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        // Return pass
        return InteractionResultHolder.pass(itemstack);
    }
}