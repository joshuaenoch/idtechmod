package com.idtech.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class SnowballLauncherItem extends Item {

    public SnowballLauncherItem(Properties properties) {
        super(properties);
    }

    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);

    //static instance for registration
    public static Item INSTANCE = new SnowballLauncherItem(properties).setRegistryName("snowballlauncher");

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        // Get player's eye position
        double eyeX = playerIn.getX();
        double eyeY = playerIn.getY() + playerIn.getEyeHeight(playerIn.getPose());
        double eyeZ = playerIn.getZ();

        // Get normalized player's look vector (direction the player is looking)
        Vec3 lookVector = playerIn.getViewVector(1.0F).normalize();

        // Create snowball entity at eye position
        Snowball snowball = new Snowball(level, eyeX, eyeY, eyeZ);

        // Set initial motion for shooting straight forward
        double speed = 10.0; // Adjust this value for higher initial speed
        snowball.shoot(lookVector.x, lookVector.y, lookVector.z, (float) speed, 0.0F);

        // Add the snowball entity to the world
        level.addFreshEntity(snowball);

        // Get the held item for return
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        // Return pass
        return InteractionResultHolder.pass(itemstack);
    }

    /*protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(DamageSource.thrown(this, this.getOwner()), (float)10);
    }*/


}