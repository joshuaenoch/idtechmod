package com.idtech.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ItemMod {

    //BASIC ITEMS
    public static final Item STRUCTURE_GEL = ItemUtils.buildBasicItem("structuregel", CreativeModeTab.TAB_MISC);

    public static final Item GEL_ORE = ItemUtils.buildBasicItem("gelore", CreativeModeTab.TAB_MISC);

    public static final Item YELLOW_FACE = ItemUtils.buildBasicItem("yellowface", CreativeModeTab.TAB_MISC);

    public static final Item MY_DOG = ItemUtils.buildBasicItem("mydog", CreativeModeTab.TAB_MISC);

    //FOODS

    public static FoodProperties mcdonalds = (new FoodProperties.Builder().nutrition(10).saturationMod(2f).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 3)  , 1.0f).alwaysEat().build());
    public static Item mcdonaldsItem = ItemUtils.buildFoodItem("mcdonalds", mcdonalds);


    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        //BASIC ITEMS
        event.getRegistry().register(STRUCTURE_GEL);

        event.getRegistry().register(GEL_ORE);

        event.getRegistry().register(YELLOW_FACE);

        event.getRegistry().register(MY_DOG);

        // ITEMS
        event.getRegistry().register(TeleportRodItem.INSTANCE);
        event.getRegistry().register(LightningHammerItem.INSTANCE);

        event.getRegistry().register(LauncherItem.INSTANCE);
        event.getRegistry().register(DogLauncherItem.INSTANCE);
        event.getRegistry().register(FlamethrowerItem.INSTANCE);
        event.getRegistry().register(ArrowLauncherItem.INSTANCE);
        event.getRegistry().register(SnowballLauncherItem.INSTANCE);
        event.getRegistry().register(ChargedCreeperSpawnerItem.INSTANCE);


        // TOOLS
        event.getRegistry().register(GelPickaxeItem.INSTANCE);
        event.getRegistry().register(MaceItem.INSTANCE);

        // FOOD

        event.getRegistry().register(mcdonaldsItem);

        // ARMOR

        event.getRegistry().register(CustomArmorItem.HELM);
        event.getRegistry().register(CustomArmorItem.LEGGINGS);

        //PROJECTILES
        event.getRegistry().register(BombArrowItem.INSTANCE);
        event.getRegistry().register(LightningArrowItem.INSTANCE);
    }
}
