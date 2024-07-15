package com.idtech.item;

import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.entity.item.PrimedTnt;

public class LauncherItem extends Item {

    public LauncherItem(Properties properties) {
        super(properties);
    }

    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);

    //static instance for registration
    public static Item INSTANCE = new LauncherItem(properties).setRegistryName("launcher");

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
        PrimedTnt primedTnt = new PrimedTnt(level, eyeX, eyeY, eyeZ, playerIn);

        // Set initial motion for shooting straight forward
        double speed = 1.5; // Adjust this value for initial speed
        primedTnt.setDeltaMovement(lookX * speed, lookY * speed, lookZ * speed);

        // Add the primed TNT entity to the world
        level.addFreshEntity(primedTnt);

        // Get the held item for return
        ItemStack itemstack = playerIn.getItemInHand(handIn);

        // Return pass
        return InteractionResultHolder.pass(itemstack);
    }
}