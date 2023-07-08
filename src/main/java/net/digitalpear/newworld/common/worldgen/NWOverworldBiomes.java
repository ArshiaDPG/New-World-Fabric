package net.digitalpear.newworld.common.worldgen;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.init.worldgen.features.NWPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.MiscPlacedFeatures;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.jetbrains.annotations.Nullable;

public class NWOverworldBiomes {
    protected static int getSkyColor(float temperature) {
        float f = temperature / 3.0F;
        f = MathHelper.clamp(f, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }
    private static Biome createBiome(boolean precipitation, float temperature, float downfall, SpawnSettings.Builder spawnSettings, net.minecraft.world.biome.GenerationSettings.Builder generationSettings, @Nullable MusicSound music) {
        return createBiome(precipitation, temperature, downfall, 4159204, spawnSettings, generationSettings, music);
    }
    private static Biome createBiome(boolean precipitation, float temperature, float downfall, int waterColor, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, @Nullable MusicSound music) {
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(precipitation).temperature(temperature).downfall(downfall).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(waterColor).waterFogColor(329011).fogColor(12638463).skyColor(getSkyColor(temperature)).moodSound(BiomeMoodSound.CAVE).music(music).build()).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }
    //Biome with custom foliage color
    private static Biome createBiome(boolean precipitation, float temperature, float downfall, int waterColor, int foliageColor, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, @Nullable MusicSound music) {
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(precipitation).temperature(temperature).downfall(downfall).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(waterColor).waterFogColor(329011).fogColor(12638463).foliageColor(foliageColor).skyColor(getSkyColor(temperature)).moodSound(BiomeMoodSound.CAVE).music(music).build()).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }
    private static void addBasicFeatures(GenerationSettings.LookupBackedBuilder generationSettings) {
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
    }



    //Wooded Meadow Stuff
    public static void addWoodedMeadowFeatures(GenerationSettings.LookupBackedBuilder builder){
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, NWPlacedFeatures.TREES_FIR);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, NWPlacedFeatures.GLOW_LICHEN_WOODED_MEADOW);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, NWPlacedFeatures.PATCH_BERRY_WOODED_MEADOW);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, NWPlacedFeatures.MOSS_CARPET_WOODED_MEADOW);
    }

    public static Biome createWoodedMeadow(RegistryEntryLookup<PlacedFeature> featureLookup, RegistryEntryLookup<ConfiguredCarver<?>> carverLookup) {
        GenerationSettings.LookupBackedBuilder lookupBackedBuilder = new GenerationSettings.LookupBackedBuilder(featureLookup, carverLookup);
        SpawnSettings.Builder builder2 = new SpawnSettings.Builder();
        builder2.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.DONKEY, 1, 1, 2)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 2, 2, 6)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 2, 2, 4));

        DefaultBiomeFeatures.addBatsAndMonsters(builder2);


        addBasicFeatures(lookupBackedBuilder);
        addWoodedMeadowFeatures(lookupBackedBuilder);
        DefaultBiomeFeatures.addForestFlowers(lookupBackedBuilder);
        DefaultBiomeFeatures.addExtraDefaultFlowers(lookupBackedBuilder);
        lookupBackedBuilder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MiscPlacedFeatures.FOREST_ROCK);
        lookupBackedBuilder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, NWPlacedFeatures.FALLEN_FIR_LOG);


        //Default biome features, DO NOT CHANGE THIS
        DefaultBiomeFeatures.addPlainsTallGrass(lookupBackedBuilder);
        DefaultBiomeFeatures.addDefaultOres(lookupBackedBuilder);
        DefaultBiomeFeatures.addDefaultDisks(lookupBackedBuilder);
        DefaultBiomeFeatures.addMeadowFlowers(lookupBackedBuilder);
        DefaultBiomeFeatures.addEmeraldOre(lookupBackedBuilder);
        DefaultBiomeFeatures.addInfestedStone(lookupBackedBuilder);


        MusicSound musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_MEADOW);
        return createBiome(true, 0.5F, 0.8F, 937679, 12158492, builder2, lookupBackedBuilder, musicSound);
    }

}
