package com.idtech.item;

import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ChargedCreeperSpawnerItem extends Item {

    //constructor and properties
    public ChargedCreeperSpawnerItem(Properties properties) {
        super(properties);
    }
    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);

    //static instance for registration
    public static Item INSTANCE = new ChargedCreeperSpawnerItem(properties).setRegistryName("creeperspawner");


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        //find the location at the cursor
        BlockPos location = Utils.getBlockAtCursor(playerIn, 200.0d, true);

        //as long as the location exists
        if(location != null){
            Creeper creeper = EntityType.CREEPER.create(level);
            //create an explosion
            Utils.spawnEntity(level, creeper, location);
            Creeper creeper2 = EntityType.CREEPER.create(level);
            //create an explosion
            Utils.spawnEntity(level, creeper2, location);
            //strike lightning
            Utils.strikeLightning(level, location);
        }

        //get the held item for return
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        //return pass
        return InteractionResultHolder.pass(itemstack);
    }
}