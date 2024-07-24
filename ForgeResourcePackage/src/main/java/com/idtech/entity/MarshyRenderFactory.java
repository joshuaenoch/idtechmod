package com.idtech.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.monster.Zombie;


public class MarshyRenderFactory implements EntityRendererProvider<MarshyEntity> {

    public static MarshyRenderFactory INSTANCE = new MarshyRenderFactory();

    @Override
    public EntityRenderer<MarshyEntity> create(Context manager) {
        return new MarshyRenderer(manager);
    }
}