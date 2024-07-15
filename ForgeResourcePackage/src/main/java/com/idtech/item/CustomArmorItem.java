package com.idtech.item;

import com.idtech.BaseMod;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;

import javax.annotation.Nullable;

public class CustomArmorItem extends ArmorItem {
    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);

    //material
    private static ArmorMaterial customMaterial = ItemUtils.buildArmorMaterial("gel", 22, new int[]{5,8,6,4} ,5, SoundEvents.ARMOR_EQUIP_IRON, 4.0f, 0.3f,"examplemod:gelore");

    //constructor
    public CustomArmorItem(ArmorMaterial material, EquipmentSlot slot, Properties properties) {
        super(material, slot, properties);
    }

    //instances
    public static final Item HELM = new CustomArmorItem(customMaterial, EquipmentSlot.HEAD, (properties)).setRegistryName(BaseMod.MODID, "customhelm");
    public static final Item LEGGINGS = new CustomArmorItem(customMaterial, EquipmentSlot.LEGS, (properties)).setRegistryName(BaseMod.MODID, "movingpants");

    @Nullable @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type){
        if(slot == EquipmentSlot.LEGS){
            return "examplemod:textures/models/armor/custom_armor_layer_2.png";
        } else {
            return "examplemod:textures/models/armor/custom_armor_layer_1.png";
        }

    }
}