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

public class TeleportRodItem extends Item
{
    private static Properties properties = new Item.Properties().tab(CreativeModeTab.TAB_MISC);

    public static Item INSTANCE = new TeleportRodItem(properties).setRegistryName("teleportrod");

    public TeleportRodItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        BlockPos blockPos = Utils.getBlockAtCursor(playerIn, 35, true);
        if(blockPos != null){
            playerIn.teleportTo(blockPos.getX(), blockPos.getY(), blockPos.getZ());
            playerIn.fallDistance = 0.0F;
        } else {
            double yawRadians = Math.toRadians(playerIn.getYRot());
            double pitchRadians = Math.toRadians(playerIn.getXRot());

            double offsetX = -Math.sin(yawRadians) * Math.cos(pitchRadians) * 30;
            double offsetY = -Math.sin(pitchRadians) * 30;
            double offsetZ = Math.cos(yawRadians) * Math.cos(pitchRadians) * 30;

            BlockPos newPos = playerIn.blockPosition().offset((int) offsetX, (int) offsetY, (int) offsetZ);
            playerIn.teleportTo(newPos.getX() + 0.5, newPos.getY(), newPos.getZ() + 0.5);
            playerIn.fallDistance = 0.0F;
        }


        ItemStack itemstack = playerIn.getItemInHand(handIn);
        return InteractionResultHolder.pass(itemstack);
    }
}
