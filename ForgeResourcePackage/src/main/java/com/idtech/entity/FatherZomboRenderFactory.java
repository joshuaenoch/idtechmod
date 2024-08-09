package com.idtech.entity;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.monster.Zombie;


public class FatherZomboRenderFactory implements EntityRendererProvider<Zombie> {

    public static FatherZomboRenderFactory INSTANCE = new FatherZomboRenderFactory();

    @Override
    public EntityRenderer<Zombie> create(Context manager) {
        return new FatherZomboRenderer(manager);
    }
}