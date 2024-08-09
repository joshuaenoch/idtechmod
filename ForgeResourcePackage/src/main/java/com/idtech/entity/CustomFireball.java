package com.idtech.entity;

import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class CustomFireball extends LargeFireball {

    public CustomFireball(Level world, LivingEntity shooter, double x, double y, double z, int i) {
        super(world, shooter, x, y, z, i);
    }

    @Override
    public void tick() {
        super.tick();
        // Ensure it doesn't disappear or gets removed
        // You can also add custom logic here to check conditions
        if (this.tickCount > 10000) { // Example: you can customize this duration
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult result) {
        if (result.getType() == HitResult.Type.ENTITY) {
            // Apply some damage to the entity hit, if needed
            EntityHitResult entityHit = (EntityHitResult) result;
            entityHit.getEntity().hurt(DamageSource.ON_FIRE, 3);
            entityHit.getEntity().setSecondsOnFire(5); // Optional: Set the entity on fire
        }
        if (result.getType() == HitResult.Type.BLOCK) {

            BlockHitResult blockHit = (BlockHitResult) result;
            Vec3 hitPosition = new Vec3(blockHit.getLocation().x, blockHit.getLocation().y, blockHit.getLocation().z);

            int radius = 3; // Define the radius
            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        if (x * x + y * y + z * z <= radius * radius) {
                            BlockState blockState = this.level.getBlockState(blockHit.getBlockPos().offset(x, y, z));
                            if (blockState.getBlock() == Blocks.AIR) {
                                this.level.setBlock(blockHit.getBlockPos().offset(x, y, z), Blocks.FIRE.defaultBlockState(), 3);
                            }
                        }
                    }
                }
            }
        }
    }
}