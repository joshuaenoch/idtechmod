package com.idtech.block;
import com.idtech.BaseMod;
import com.idtech.Utils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class PigBlock extends Block
{
    private static Properties properties = Properties.of(Material.STONE);

    public static Block INSTANCE = new PigBlock(properties).setRegistryName(BaseMod.MODID, "pigblock");
    public static Item ITEM = BlockUtils.createBlockItem(INSTANCE, CreativeModeTab.TAB_MISC);

    public PigBlock(Properties properties){
        super(properties);
    }

    @Override
    public void stepOn(Level levelIn, BlockPos posIn, BlockState blockStateIn, Entity entityIn) {
        Pig pig = EntityType.PIG.create(levelIn);
        Utils.spawnEntity(levelIn, pig, posIn);
    }
}
