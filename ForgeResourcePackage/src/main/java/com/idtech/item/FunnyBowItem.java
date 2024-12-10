package com.idtech.item;

import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FunnyBowItem extends BowItem {

    public FunnyBowItem(Properties properties) {
        super(properties);
    }

    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);

    //static instance for registration
    public static Item INSTANCE = new FunnyBowItem(properties).setRegistryName("funnybow");

    @Override
    public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int num) {
        super.releaseUsing(itemStack, level, livingEntity, num);

        if (livingEntity instanceof Player player) {
            player.setSecondsOnFire(5);
        }

    }
}