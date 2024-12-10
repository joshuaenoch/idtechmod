package com.idtech.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class sorenRenderFactory implements EntityRendererProvider<sorenEntity> {

    public static sorenRenderFactory INSTANCE = new sorenRenderFactory();

    @Override
    public EntityRenderer<sorenEntity> create(Context manager) {
        return new sorenRenderer(manager);
    }
}