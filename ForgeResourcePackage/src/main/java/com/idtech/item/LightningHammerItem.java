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

public class LightningHammerItem extends Item {

    //constructor and properties
    public LightningHammerItem(Properties properties) {
        super(properties);
    }
    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);

    //static instance for registration
    public static Item INSTANCE = new LightningHammerItem(properties).setRegistryName("lightninghammer");


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        //find the location at the cursor
        BlockPos location = Utils.getBlockAtCursor(playerIn, 200.0d, true);

        //as long as the location exists
        if(location != null){
            //create an explosion
            Utils.createExplosion(level, location, 5.0f);
            //strike lightning
            Utils.strikeLightning(level, location);
        }

        //get the held item for return
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        //return pass
        return InteractionResultHolder.pass(itemstack);
    }
}