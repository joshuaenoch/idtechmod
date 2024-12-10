package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class sorenRenderer extends MobRenderer<sorenEntity, sorenModel<sorenEntity>> {

    public sorenRenderer(EntityRendererProvider.Context provider) {
        super(provider, new sorenModel<>(provider.bakeLayer(sorenModel.LAYER_LOCATION)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(sorenEntity p_114482_) {
        return new ResourceLocation(BaseMod.MODID, "textures/entity/soren.png");
    }
}