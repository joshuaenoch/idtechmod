package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvent;


public class MarshyEntity extends Zombie
{
    //TYPE
    public static EntityType<MarshyEntity> TYPE = (EntityType<MarshyEntity>)
            EntityType.Builder.of(MarshyEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).
                    clientTrackingRange(8).build("marshy").setRegistryName(BaseMod.MODID, "marshy");
    //EGG
    public static Item EGG = EntityUtils.buildEntitySpawnEgg(TYPE, 0xffffff , 0xffffff);

    public MarshyEntity(EntityType<? extends Zombie> p_29656_, Level p_29657_) {
        super(p_29656_, p_29657_);
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(.5);
    }

    protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean isSunSensitive() {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource e) {
        return SoundEvents.LAVA_POP;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.FIRE_EXTINGUISH;
    }
}