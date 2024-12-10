package com.idtech.entity;
import com.idtech.BaseMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ZombieRenderer;
import net.minecraft.client.renderer.entity.layers.CreeperPowerLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Zombie;

public class ZomboRenderer extends ZombieRenderer{
    public ZomboRenderer(EntityRendererProvider.Context provider) {
        super(provider);
    }

    @Override
    public ResourceLocation getTextureLocation(Zombie entity) {
        ZomboEntity zomboEntity = (ZomboEntity) entity;
        if (zomboEntity.isCharged()) {
            return new ResourceLocation(BaseMod.MODID, "textures/entity/marshy.png");
        }
        return new ResourceLocation(BaseMod.MODID, "textures/entity/zombo.png");
    }
}
