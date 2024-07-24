package com.idtech.item;

import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

// Feel free to change the name of the item
// But remember the class and file name have to be the same
public class ChallengeItem extends Item {

    public ChallengeItem(Properties properties) {
        super(properties);
    }
    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);

    public static Item INSTANCE = new ChallengeItem(properties).setRegistryName("challengeitem");


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {

        BlockPos location = Utils.getBlockAtCursor(playerIn, 200.0d, true);

        if(location != null){
            // CHANGE THE CODE HERE TO SPAWN ANY ENTITY

            // Tip:
            // You can create an entity variable like this: Creeper creeper = EntityType.CREEPER.create(level);

            Utils.createExplosion(level, location, 5.0f);
            Utils.strikeLightning(level, location);
        }

        ItemStack itemstack = playerIn.getItemInHand(handIn);
        return InteractionResultHolder.pass(itemstack);
    }
}

// Don't forget to register the item in ItemMod. Texture or name change isn't required for tickets but still feel
// free to do so