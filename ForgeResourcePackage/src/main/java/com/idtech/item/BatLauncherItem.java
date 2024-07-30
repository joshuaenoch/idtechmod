package com.idtech.item;

import com.idtech.Utils;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BatLauncherItem extends Item {

    public BatLauncherItem(Properties properties) {
        super(properties);
    }

    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);

    // static instance for registration
    public static Item INSTANCE = new BatLauncherItem(properties).setRegistryName("batlauncher");

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        // Get player's eye position
        double eyeX = playerIn.getX();
        double eyeY = playerIn.getY() + playerIn.getEyeHeight(playerIn.getPose());
        double eyeZ = playerIn.getZ();

        // Get player's look vector
        Vec3 lookVector = playerIn.getViewVector(1.0F);

        // Create a wolf entity at eye position
        Bat bat = EntityType.BAT.create(level);
        if (bat != null) {
            // Set wolf position
            bat.moveTo(eyeX, eyeY, eyeZ, playerIn.getYRot(), 0.0F);

            // Set initial motion for shooting straight forward
            double speed = 10; // Adjust this value for initial speed
            bat.setDeltaMovement(lookVector.x * speed, lookVector.y * speed, lookVector.z * speed);

            // Add the wolf entity to the world
            level.addFreshEntity(bat);

            // Get the held item for return
            ItemStack itemstack = playerIn.getItemInHand(handIn);

            // Return success
            return InteractionResultHolder.success(itemstack);
        }

        // If creation fails for some reason, return pass
        return InteractionResultHolder.pass(playerIn.getItemInHand(handIn));
    }

}
