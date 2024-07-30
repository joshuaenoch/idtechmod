package com.idtech.entity;

import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Bomb extends Snowball {

    public Bomb(EntityType<? extends Snowball> entityType, Level level) {
        super(entityType, level);
    }

    public Bomb(Level level, LivingEntity owner) {
        super(level, owner);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        Vec3 loc = result.getLocation();
        Utils.createExplosion(level, new BlockPos(loc.x, loc.y, loc.z), 5.0f);
    }
}