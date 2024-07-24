package com.idtech.item;

import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ShulkerLauncherItem extends Item {

    //constructor and properties
    public ShulkerLauncherItem(Properties properties) {
        super(properties);
    }
    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);

    //static instance for registration
    public static Item INSTANCE = new ShulkerLauncherItem(properties).setRegistryName("shulkerlauncher");


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {

        Vec3 eyePos = playerIn.getEyePosition(1.0f);
        Vec3 lookVec = playerIn.getViewVector(1.0f);

        // Calculate initial position of the shulker bullet (slightly in front of the player)
        double posX = eyePos.x + lookVec.x * 1.0D;
        double posY = eyePos.y + lookVec.y * 1.0D;
        double posZ = eyePos.z + lookVec.z * 1.0D;

        // Spawn the shulker bullet entity
        ShulkerBullet shulkerBullet = new ShulkerBullet(EntityType.SHULKER_BULLET, level);
        shulkerBullet.setPos(posX, posY, posZ);

        double speed = 10;
        // Calculate the motion vector for the shulker bullet (forward direction)
        shulkerBullet.setDeltaMovement(lookVec.x * speed, lookVec.y * speed, lookVec.z * speed);

        level.addFreshEntity(shulkerBullet);

        return InteractionResultHolder.success(playerIn.getItemInHand(handIn));
    }
}