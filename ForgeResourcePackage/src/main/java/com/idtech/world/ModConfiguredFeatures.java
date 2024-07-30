/*package com.idtech.world;

import com.idtech.block.BlockMod;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ModConfiguredFeatures {
    // Define a custom TrunkPlacerType, if necessary
    public static final TrunkPlacerType<StraightTrunkPlacer> STRAIGHT_TRUNK_PLACER = Registry.register(Registry.TRUNK_PLACER_TYPE, new ResourceLocation("modid", "straight_trunk_placer"), new TrunkPlacerType<>(StraightTrunkPlacer.CODEC));

    // Define and register the custom tree feature
    public static final ConfiguredFeature<?, ?> FUNNY_TREE = register("funnytree",
            Feature.TREE.configured(new TreeConfiguration.TreeConfigurationBuilder(
                            new SimpleBlockStateProvider(BlockRegistry.FUNNY_LOG.defaultBlockState()),
                            new StraightTrunkPlacer(5, 6, 3),
                            new SimpleBlockStateProvider(Blocks.OAK_LEAVES.defaultBlockState()),
                            new BlobFoliagePlacer(BlockStatePredicate.forBlock(Blocks.OAK_LEAVES), BlockStatePredicate.forBlock(Blocks.OAK_LEAVES), 3, 0, 0)
                    ).build())
                    .decorated(FeatureDecorator.COUNT.configured(new FrequencyDecoratorConfiguration(10))));

    // Helper method to register the ConfiguredFeature
    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<?, ?> configuredFeature) {
        return Registry.register(Registry.CONFIGURED_FEATURE, new ResourceLocation("modid", key), configuredFeature);
    }

    /*public static final PlacedFeature FUNNY_CHECKED = PlacementUtils.register("funny_checked", FUNNY_TREE,
            PlacementUtils.(ModBlocks.EBONY_SAPLING.get()));

    public static final ConfiguredFeature<RandomFeatureConfiguration, ?> FUNNY_SPAWN =
            FeatureUtils.register("funny_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfiguration(List.of(new WeightedPlacedFeature(FUNNY_CHECKED,
                            0.5F)), FUNNY_CHECKED));


}*/
