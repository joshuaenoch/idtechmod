package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MarshyRenderer extends MobRenderer<MarshyEntity, MarshyModel<MarshyEntity>> {

    public MarshyRenderer(EntityRendererProvider.Context provider) {
        super(provider, new MarshyModel<>(provider.bakeLayer(MarshyModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(MarshyEntity p_114482_) {
        return new ResourceLocation(BaseMod.MODID, "textures/entity/marshy.png");
    }
}