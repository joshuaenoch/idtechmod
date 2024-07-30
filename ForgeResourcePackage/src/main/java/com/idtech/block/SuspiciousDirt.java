package com.idtech.block;
import com.idtech.BaseMod;
import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class SuspiciousDirt extends Block
{
    private static Properties properties = Properties.of(Material.DIRT);

    public static Block INSTANCE = new SuspiciousDirt(properties).setRegistryName(BaseMod.MODID, "suspiciousdirt");
    public static Item ITEM = BlockUtils.createBlockItem(INSTANCE, CreativeModeTab.TAB_MISC);

    public SuspiciousDirt(Properties properties){
        super(properties);
    }

    @Override
    public void stepOn(Level levelIn, BlockPos posIn, BlockState blockStateIn, Entity entityIn) {
        super.stepOn(levelIn, posIn, blockStateIn, entityIn);

        Utils.createExplosion(levelIn, posIn, 4);
    }
}
