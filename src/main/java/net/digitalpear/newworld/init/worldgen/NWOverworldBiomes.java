package net.digitalpear.newworld.init.worldgen;

import net.digitalpear.newworld.NewWorld;
import net.digitalpear.newworld.init.worldgen.features.NWPlacedFeatures;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.MiscPlacedFeatures;
import org.jetbrains.annotations.Nullable;

public class NWOverworldBiomes {
    protected static int getSkyColor(float temperature) {
        float f = temperature / 3.0F;
        f = MathHelper.clamp(f, -1.0F, 1.0F);
        return MathHelper.hsvToRgb(0.62222224F - f * 0.05F, 0.5F + f * 0.1F, 1.0F);
    }
    private static Biome createBiome(Biome.Precipitation precipitation, Biome.Category category, float temperature, float downfall, SpawnSettings.Builder spawnSettings, net.minecraft.world.biome.GenerationSettings.Builder generationSettings, @Nullable MusicSound music) {
        return createBiome(precipitation, category, temperature, downfall, 4159204, spawnSettings, generationSettings, music);
    }
    private static Biome createBiome(Biome.Precipitation precipitation, Biome.Category category, float temperature, float downfall, int waterColor, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, @Nullable MusicSound music) {
        return (new net.minecraft.world.biome.Biome.Builder()).precipitation(precipitation).category(category).temperature(temperature).downfall(downfall).effects((new net.minecraft.world.biome.BiomeEffects.Builder()).waterColor(waterColor).waterFogColor(329011).fogColor(12638463).skyColor(getSkyColor(temperature)).moodSound(BiomeMoodSound.CAVE).music(music).build()).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }
    private static void addBasicFeatures(GenerationSettings.Builder generationSettings) {
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
    }


    //Wooded Meadow Stuff
    public static void addWoodedMeadowFeatures(GenerationSettings.Builder builder){
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, NWPlacedFeatures.FIR);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, NWPlacedFeatures.GLOW_LICHEN_WOODED_MEADOW);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, NWPlacedFeatures.PATCH_BERRY_WOODED_MEADOW);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, NWPlacedFeatures.MOSS_CARPET_WOODED_MEADOW);
    }

    public static Biome createWoodedMeadow() {
        net.minecraft.world.biome.GenerationSettings.Builder builder = new GenerationSettings.Builder();
        SpawnSettings.Builder builder2 = new SpawnSettings.Builder();
        builder2.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.DONKEY, 1, 1, 2)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 2, 2, 6)).spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.SHEEP, 2, 2, 4));

        DefaultBiomeFeatures.addBatsAndMonsters(builder2);


        addBasicFeatures(builder);
        addWoodedMeadowFeatures(builder);
        DefaultBiomeFeatures.addForestFlowers(builder);
        DefaultBiomeFeatures.addExtraDefaultFlowers(builder);
        builder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, MiscPlacedFeatures.FOREST_ROCK);
        builder.feature(GenerationStep.Feature.LOCAL_MODIFICATIONS, NWPlacedFeatures.FALLEN_FIR_LOG);



        //Default biome features, DO NOT CHANGE THIS
        DefaultBiomeFeatures.addPlainsTallGrass(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);
        DefaultBiomeFeatures.addMeadowFlowers(builder);
        DefaultBiomeFeatures.addEmeraldOre(builder);
        DefaultBiomeFeatures.addInfestedStone(builder);


        MusicSound musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_MEADOW);
        return createBiome(Biome.Precipitation.RAIN, Biome.Category.FOREST, 0.5F, 0.8F, 937679, builder2, builder, musicSound);
    }

    public static final RegistryKey<Biome> WOODED_MEADOW_KEY = createBiomeKey("wooded_meadow");

    public static void init(){
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(BiomeKeys.MEADOW), GenerationStep.Feature.VEGETAL_DECORATION, NWPlacedFeatures.FIR_MEADOW.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_TAIGA), GenerationStep.Feature.VEGETAL_DECORATION, NWPlacedFeatures.FIR_SCARCE.getKey().get());
        register(WOODED_MEADOW_KEY, createWoodedMeadow());
    }

    private static Biome register(RegistryKey<Biome> key, Biome biome)
    {
        return Registry.register(BuiltinRegistries.BIOME, key, biome);
    }
    public static RegistryKey<Biome> createBiomeKey(String id){
        return RegistryKey.of(Registry.BIOME_KEY, new Identifier(NewWorld.MOD_ID, id));
    }
}
