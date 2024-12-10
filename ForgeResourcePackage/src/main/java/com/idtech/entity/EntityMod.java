package com.idtech.entity;

import com.idtech.BaseMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityMod {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, BaseMod.MODID);

    /*public static final RegistryObject<EntityType<Bomb>> BOMB =
            ENTITY_TYPES.register("bombentity", () -> EntityType.Builder.<Bomb>of(Bomb::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("bombentity"));*/

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event){
        event.getRegistry().register(ZomboEntity.TYPE);
        event.getRegistry().register(EvilRabbit.TYPE);
        event.getRegistry().register(MarshyEntity.TYPE);
        event.getRegistry().register(FatherZomboEntity.TYPE);
        event.getRegistry().register(sorenEntity.TYPE);
    }
    @SubscribeEvent
    public static void registerEntityEggs(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ZomboEntity.EGG);
        event.getRegistry().register(EvilRabbit.EGG);
        event.getRegistry().register(MarshyEntity.EGG);
        event.getRegistry().register(FatherZomboEntity.EGG);
        event.getRegistry().register(sorenEntity.EGG);
    }
    @SubscribeEvent
    public static void entityRenderers(final EntityRenderersEvent.RegisterRenderers event){
        event.registerEntityRenderer(ZomboEntity.TYPE, ZomboRenderFactory.INSTANCE);
        event.registerEntityRenderer(EvilRabbit.TYPE, EvilRabbitRenderFactory.INSTANCE);
        event.registerEntityRenderer(MarshyEntity.TYPE, MarshyRenderFactory.INSTANCE);
        event.registerEntityRenderer(FatherZomboEntity.TYPE, FatherZomboRenderFactory.INSTANCE);
        event.registerEntityRenderer(sorenEntity.TYPE, sorenRenderFactory.INSTANCE);
    }

    // this is different than in 1.16 but everything else is the same
    // I do think this makes more sense than the other way but alas change is usually hard.
    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(ZomboEntity.TYPE, ZomboEntity.createAttributes().build());
        event.put(EvilRabbit.TYPE, EvilRabbit.createAttributes().build());
        event.put(MarshyEntity.TYPE, MarshyEntity.createAttributes().build());
        event.put(FatherZomboEntity.TYPE, FatherZomboEntity.createAttributes().build());
        event.put(sorenEntity.TYPE, sorenEntity.createAttributes().build());
    }

}
