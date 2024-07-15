package com.idtech.block;

import com.idtech.BaseMod;
import com.idtech.entity.PrimedBigTnt;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.Explosion;

public class BigTntBlock extends TntBlock {
    private static Properties properties = Properties.of(Material.STONE);

    public static Block INSTANCE = new BigTntBlock(properties).setRegistryName(BaseMod.MODID, "bigtnt");
    public static Item ITEM = BlockUtils.createBlockItem(INSTANCE, CreativeModeTab.TAB_MISC);

    public BigTntBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void wasExploded(Level level, BlockPos pos, Explosion explosion) {
        if (!level.isClientSide) {
            PrimedBigTnt primedtnt = new PrimedBigTnt(level, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, explosion.getSourceMob());
            level.addFreshEntity(primedtnt);
        }
    }
}
