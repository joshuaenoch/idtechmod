package com.idtech.item;

import com.idtech.entity.BombArrow;
import com.idtech.entity.TrackingArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TrackingArrowItem extends ArrowItem
{
    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);

    public TrackingArrowItem(Properties properties)
    {
        super (properties);
    }

    public static Item INSTANCE = new TrackingArrowItem(properties).setRegistryName("trackingarrow");

    @Override
    public AbstractArrow createArrow(Level levelIn, ItemStack stack, LivingEntity shooter) {
        TrackingArrow arrowEntity = new TrackingArrow(levelIn, shooter);
        return arrowEntity;
    }
}