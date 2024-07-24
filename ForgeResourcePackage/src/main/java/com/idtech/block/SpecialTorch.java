package com.idtech.block;

import com.idtech.BaseMod;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;

public class SpecialTorch extends Block {
    private static BlockBehaviour.Properties properties = BlockBehaviour.Properties.of(Material.DECORATION).lightLevel((state) -> 15);

    public static Block INSTANCE = new SpecialTorch(properties).setRegistryName(BaseMod.MODID, "specialtorch");
    public static Item ITEM = BlockUtils.createBlockItem(INSTANCE, CreativeModeTab.TAB_MISC);

    public SpecialTorch(BlockBehaviour.Properties properties) {
        super(properties);
        // super(properties, ParticleTypes.FLAME);
    }
}