package com.idtech.entity;

import com.idtech.BaseMod;
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
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class EvilRabbit extends Rabbit
{
    //TYPE
    public static EntityType<EvilRabbit> TYPE = (EntityType<EvilRabbit>)
            EntityType.Builder.of(EvilRabbit::new, MobCategory.MONSTER).sized(0.6F, 1.95F).
                    clientTrackingRange(8).build("evilrabbit").setRegistryName(BaseMod.MODID, "evilrabbit");
    //EGG
    public static Item EGG = EntityUtils.buildEntitySpawnEgg(TYPE, 0xfef9f8 , 0x383737);

    public EvilRabbit(EntityType<? extends Rabbit> p_29656_, Level p_29657_) {
        super(p_29656_, p_29657_);
    }

    public static AttributeSupplier.Builder createAttributes()
    {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 12)
                .add(Attributes.MOVEMENT_SPEED, 5)
                .add(Attributes.ATTACK_DAMAGE, 4)
                .add(Attributes.FOLLOW_RANGE, 35);
    }

    protected void registerGoals()
    {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.0D, false));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }
}