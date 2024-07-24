package com.idtech.item;

import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BombItem extends Item {

    // Constructor and properties
    public BombItem(Properties properties) {
        super(properties);
    }

    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);

    // Static instance for registration
    public static Item INSTANCE = new BombItem(properties).setRegistryName("bomb");

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        double playerX = playerIn.getX();
        double playerY = playerIn.getY();
        double playerZ = playerIn.getZ();
        BlockPos location = new BlockPos(playerX, playerY, playerZ);

        while (location.getY() >= -60 && !level.getBlockState(location).isSolidRender(level, location)) {
            location = location.below();
        }
        //playerIn.sendMessage(new TextComponent(location.toShortString()), playerIn.getUUID());
        Utils.createExplosion(level, location, 3.0f);

        ItemStack itemstack = playerIn.getItemInHand(handIn);
        itemstack.shrink(1);

        return InteractionResultHolder.pass(playerIn.getItemInHand(handIn));
    }
}
