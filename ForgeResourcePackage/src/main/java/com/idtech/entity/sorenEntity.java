package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class sorenEntity extends WitherBoss
{
    //TYPE
    public static EntityType<sorenEntity> TYPE = (EntityType<sorenEntity>)
            EntityType.Builder.of(sorenEntity::new, MobCategory.MONSTER).sized(0.6F, 1.95F).
                    clientTrackingRange(8).build("soren").setRegistryName(BaseMod.MODID, "soren");
    //EGG
    public static Item EGG = EntityUtils.buildEntitySpawnEgg(TYPE, 0xfef9f8 , 0x383737);

    public sorenEntity(EntityType<? extends WitherBoss> p_29656_, Level p_29657_) {
        super(p_29656_, p_29657_);
    }
}