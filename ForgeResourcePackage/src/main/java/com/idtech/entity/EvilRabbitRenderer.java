package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class EvilRabbitRenderer extends MobRenderer<EvilRabbit, EvilRabbitModel<EvilRabbit>> {

    public EvilRabbitRenderer(EntityRendererProvider.Context provider) {
        super(provider, new EvilRabbitModel<>(provider.bakeLayer(EvilRabbitModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(EvilRabbit p_114482_) {
        return new ResourceLocation(BaseMod.MODID, "textures/entity/evilrabbit.png");
    }
}