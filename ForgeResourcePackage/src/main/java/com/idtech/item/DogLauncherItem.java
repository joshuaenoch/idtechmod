package com.idtech.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class DogLauncherItem extends Item {

    public DogLauncherItem(Properties properties) {
        super(properties);
    }

    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);

    // static instance for registration
    public static Item INSTANCE = new DogLauncherItem(properties).setRegistryName("doglauncher");

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        // Get player's eye position
        double eyeX = playerIn.getX();
        double eyeY = playerIn.getY() + playerIn.getEyeHeight(playerIn.getPose());
        double eyeZ = playerIn.getZ();

        // Get player's look vector
        Vec3 lookVector = playerIn.getViewVector(1.0F);

        // Create a wolf entity at eye position
        Wolf wolf = EntityType.WOLF.create(level);
        if (wolf != null) {
            // Set wolf position
            wolf.moveTo(eyeX, eyeY, eyeZ, playerIn.getYRot(), 0.0F);

            // Set the wolf's attributes to make it appear wild
            wolf.setTame(false); // Make sure the wolf is un tamed

            // Set initial motion for shooting straight forward
            double speed = 4; // Adjust this value for initial speed
            wolf.setDeltaMovement(lookVector.x * speed, lookVector.y * speed, lookVector.z * speed);

            if (wolf instanceof LivingEntity) {
                ((LivingEntity) wolf).setSecondsOnFire(5); // 5 seconds of fire duration
            }

            // Add the wolf entity to the world
            level.addFreshEntity(wolf);

            // Get the held item for return
            ItemStack itemstack = playerIn.getItemInHand(handIn);

            // Return success
            return InteractionResultHolder.success(itemstack);
        }

        // If creation fails for some reason, return pass
        return InteractionResultHolder.pass(playerIn.getItemInHand(handIn));
    }

}
