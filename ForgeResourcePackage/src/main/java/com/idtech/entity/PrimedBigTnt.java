package com.idtech.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Explosion;
import net.minecraftforge.registries.ObjectHolder;

import java.lang.reflect.Field;

public class PrimedBigTnt extends PrimedTnt {

    @ObjectHolder("examplemod:primed_big_tnt")
    public static final EntityType<PrimedBigTnt> PRIMED_BIG_TNT = null;

    public PrimedBigTnt(EntityType<? extends PrimedTnt> type, Level level) {
        super(type, level);
    }

    public PrimedBigTnt(Level level, double x, double y, double z, LivingEntity igniter) {
        super(PRIMED_BIG_TNT, level);
        this.setPos(x, y, z);
        this.setFuse(80); // Default fuse time, adjust as needed
        setOwner(igniter);
    }

    private void setOwner(LivingEntity igniter) {
        try {
            Field ownerField = PrimedTnt.class.getDeclaredField("owner");
            ownerField.setAccessible(true);
            ownerField.set(this, igniter);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void explode() {
        this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 10.0F, Explosion.BlockInteraction.BREAK); // Custom radius here (10.0F)
    }
}
