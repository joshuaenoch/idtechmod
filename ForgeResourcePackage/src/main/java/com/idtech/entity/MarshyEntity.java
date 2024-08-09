package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;


public class MarshyEntity extends Zombie implements PlayerRideable, Saddleable
{
    private boolean saddled = false;

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

    @Override
    public void tick() {
        super.tick();
        if (this.getControllingPassenger() != null) {
            Entity rider = this.getControllingPassenger();
            if (rider instanceof ServerPlayer) {
                Player player = (Player) rider;
                this.setYRot(player.getYRot());


                // Move entity forward based on player actions or commands
                // For example, we might use a simple forward movement
                Vec3 currentMovement = this.getDeltaMovement();
                double moveSpeed = 0.5; // Adjust speed as needed

                // Apply a simple forward movement
                Vec3 newMovement = new Vec3(
                        Math.sin(Math.toRadians(this.getYRot())) * moveSpeed,
                        currentMovement.y, // Preserve vertical movement (gravity)
                        -Math.cos(Math.toRadians(this.getYRot())) * moveSpeed
                );

                this.setDeltaMovement(newMovement);
            }
        }
    }

    @javax.annotation.Nullable
    public Entity getControllingPassenger() {
        return this.getFirstPassenger();
    }

    @Override
    public boolean canBeControlledByRider() {
        return true;
    }

    @Override
    public boolean isSaddleable() {
        return true;
    }

    @Override
    public void equipSaddle(@Nullable SoundSource p_21748_) {
        saddled = true;
        if (p_21748_ != null) {
            this.level.playSound((Player)null, this, SoundEvents.STRIDER_SADDLE, p_21748_, 0.5F, 1.0F);
        }
    }

    @Override
    public boolean isSaddled() {
        return saddled;
    }

    public InteractionResult mobInteract(Player p_30713_, InteractionHand p_30714_) {
        ItemStack itemstack = p_30713_.getItemInHand(p_30714_);
        this.doPlayerRide(p_30713_);
        return InteractionResult.sidedSuccess(this.level.isClientSide);
    }

    public void doPlayerRide(Player p_30634_) {
        if (!this.level.isClientSide) {
            p_30634_.startRiding(this);
        }
    }
}