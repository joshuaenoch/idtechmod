package com.idtech.world;

import com.idtech.BaseMod;
import com.idtech.block.BlockMod;
import com.idtech.block.RubberBlock;
import com.idtech.entity.EvilRabbit;
import com.idtech.entity.ZomboEntity;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.common.world.MobSpawnSettingsBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = BaseMod.MODID)
public class WorldMod {
    public static ConfiguredFeature<?, ?> RUBBER_BLOCK_FEATURE = new ConfiguredFeature(
            Feature.ORE, new OreConfiguration(
            OreFeatures.STONE_ORE_REPLACEABLES,
            RubberBlock.INSTANCE.defaultBlockState(),
            9));

    public static PlacedFeature RUBBER_BLOCK_PLACED_FEATURE = RUBBER_BLOCK_FEATURE.placed(
            List.of(
                    CountPlacement.of(10),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.triangle(VerticalAnchor.absolute(-80), VerticalAnchor.absolute(80)),
                    BiomeFilter.biome()
            ));

    private static ResourceKey<Biome> RUBBER_PEAKS = ResourceKey.create(Registry.BIOME_REGISTRY, RubberPeaksBiome.INSTANCE.getRegistryName());

    public static void registerBiomes(final RegistryEvent.Register<Biome> event){
        event.getRegistry().register(RubberPeaksBiome.INSTANCE);
    }

    public static void setupBiomes() {
        BiomeManager.addBiome(BiomeManager.BiomeType.WARM, new BiomeManager.BiomeEntry(RUBBER_PEAKS, 9000));
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void addFeatures(BiomeLoadingEvent event) {

        BiomeGenerationSettingsBuilder biomeGen = event.getGeneration();
        Biome.BiomeCategory biomeCategory = event.getCategory();
        MobSpawnSettingsBuilder spawner = event.getSpawns();

        // natural rubber block generation here
        FeatureUtils.register("rubber_block_feature", RUBBER_BLOCK_FEATURE);
        PlacementUtils.register("rubber_block_feature", RUBBER_BLOCK_PLACED_FEATURE);

        if(event.getName().getPath().equals(RUBBER_PEAKS.location().getPath()))
        {
            biomeGen.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, RUBBER_BLOCK_PLACED_FEATURE);
        }

        if(biomeCategory == Biome.BiomeCategory.MOUNTAIN){
            spawner.addSpawn(MobCategory.MONSTER,
                    new MobSpawnSettings.SpawnerData(ZomboEntity.TYPE, 5, 2, 8));
        }

        if(biomeCategory == Biome.BiomeCategory.THEEND){
            spawner.addSpawn(MobCategory.MONSTER,
                    new MobSpawnSettings.SpawnerData(EvilRabbit.TYPE, 1, 1, 1));
        }
    }
}