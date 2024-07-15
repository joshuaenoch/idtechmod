package com.idtech.item;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class FlamethrowerItem extends Item {

    public FlamethrowerItem(Properties properties) {
        super(properties);
    }

    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);

    //static instance for registration
    public static Item INSTANCE = new FlamethrowerItem(properties).setRegistryName("flamethrower");

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        // Check if player is sneaking to prevent accidental usage
        if (player.isShiftKeyDown()) {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }

        // Get the player's line of sight
        HitResult rayTraceResult = getPlayerPOVHitResult(world, player, ClipContext.Fluid.NONE);

        // Check if the ray hit something
        if (rayTraceResult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }

        // Determine the range (adjust to your needs)
        double range = 64.0;

        // Get a bounding box in the direction the player is looking
        AABB area = player.getBoundingBox().expandTowards(player.getLookAngle().scale(range)).inflate(1.0);

        // Get all entities within the bounding box
        for (Entity entity : world.getEntities(player, area)) {
            if (entity instanceof LivingEntity) {
                // Ignite living entities
                ((LivingEntity) entity).setSecondsOnFire(5);
            }
        }

        // Ignite blocks at the hit position
        if (rayTraceResult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }

        // Return success
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
