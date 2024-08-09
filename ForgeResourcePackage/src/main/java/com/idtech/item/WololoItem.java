package com.idtech.item;

import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class WololoItem extends Item {

    //constructor and properties
    public WololoItem(Properties properties) {
        super(properties);
    }
    private static Properties properties = new Properties().tab(CreativeModeTab.TAB_MISC);

    //static instance for registration
    public static Item INSTANCE = new WololoItem(properties).setRegistryName("wololo");


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {
        //find the location at the cursor
        BlockPos location = Utils.getBlockAtCursor(playerIn, 200.0d, true);

        //as long as the location exists
        if(location != null){
            level.playSound(playerIn, location, SoundEvents.EVOKER_PREPARE_WOLOLO, SoundSource.HOSTILE, 1.0F, 1.0F);
            EvokerFangs fangs = EntityType.EVOKER_FANGS.create(level);
            Utils.spawnEntity(level, fangs, location.above());
        }

        //get the held item for return
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        //return pass
        return InteractionResultHolder.pass(itemstack);
    }
}