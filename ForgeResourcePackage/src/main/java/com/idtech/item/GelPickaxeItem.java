package com.idtech.item;

import com.idtech.BaseMod;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class GelPickaxeItem extends PickaxeItem {
    // PROPERTIES
    private static Properties properties = new Item.Properties().tab(CreativeModeTab.TAB_MISC);

    // CONSTRUCTOR
    public GelPickaxeItem(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties){
        super(tier, attackDamageIn, attackSpeedIn, properties);
    }

    // TIER
    public static Tier tier = new ForgeTier(4, 1561, 8.0F, 10.0F, 10, null, ()->{return Ingredient.of(ItemMod.STRUCTURE_GEL);});

    // INSTANCE
    public static Item INSTANCE = new GelPickaxeItem(tier, 100, 100, new Properties().tab(CreativeModeTab.TAB_MISC)).setRegistryName(BaseMod.MODID,"gelpickaxe");
}