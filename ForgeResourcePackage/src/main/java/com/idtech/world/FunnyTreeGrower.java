package com.idtech.world;

import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.Random;

public class FunnyTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ConfiguredFeature<?, ?> getConfiguredFeature(Random p_204307_, boolean p_204308_) {
        //return ModConfiguredFeatures.FUNNY_TREE;
        return null;
    }
}
