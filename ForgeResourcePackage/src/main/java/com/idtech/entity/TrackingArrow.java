package com.idtech.entity;

import com.idtech.item.BombArrowItem;
import com.idtech.item.TrackingArrowItem;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class TrackingArrow extends AbstractArrow
{
    public TrackingArrow(Level levelIn, LivingEntity entityIn)
    {
        super(EntityType.ARROW, entityIn, levelIn);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(TrackingArrowItem.INSTANCE);
    }

    @Override
    public void tick() {
        super.tick();
        Player player = Minecraft.getInstance().player;
        Level world = player.level;

        // Get the player's position and calculate the direction to it
        double targetX = player.getX()/* Get player's target X position */;
        double targetY = player.getY()/* Get player's target Y position */;
        double targetZ = player.getZ()/* Get player's target Z position */;

        double dX = targetX - this.getX();
        double dY = targetY - this.getY();
        double dZ = targetZ - this.getZ();

        // Normalize the direction vector
        double distance = Math.sqrt(dX * dX + dY * dY + dZ * dZ);
        this.setDeltaMovement(dX / distance * 0.5, dY / distance * 0.5, dZ / distance * 0.5); // Adjust the multiplier as needed

    }

    int count = 0;

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        Vec3 loc = result.getLocation();
        LightningBolt bolt = EntityType.LIGHTNING_BOLT.create(level);
        bolt.setPos(loc.x, loc.y, loc.z);
        // Summon a lightning bolt at the hit location
        level.addFreshEntity(bolt);
        if(count < 1) {
            //level.explode(null, loc.x, loc.y, loc.z, 10.0F, Explosion.BlockInteraction.DESTROY);
            count++;
        }
    }
}