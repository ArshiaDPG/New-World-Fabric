package net.digitalpear.newworld.init.worldgen.structures;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.worldgen.structures.BuriedBunkerFeature;
import net.digitalpear.newworld.init.data.tags.NWBiomeTags;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureSpawns;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.StructureTerrainAdaptation;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.heightprovider.ConstantHeightProvider;
import net.minecraft.world.gen.structure.Structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class NWStructures {

    public static List<RegistryKey<Structure>> structures = new ArrayList<>();

    public static RegistryKey<Structure> of(String id) {
        RegistryKey<Structure> registryKey = RegistryKey.of(RegistryKeys.STRUCTURE, new Identifier(Newworld.MOD_ID, id));
        structures.add(registryKey);
        return registryKey;
    }
    public static final RegistryKey<Structure> BURIED_BUNKER = of("buried_bunker");

    public static void bootstrap(Registerable<Structure> structureRegisterable) {
        RegistryEntryLookup<Biome> registryEntryLookup = structureRegisterable.getRegistryLookup(RegistryKeys.BIOME);
        RegistryEntryLookup<StructurePool> registryEntryLookup2 = structureRegisterable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL);


        structureRegisterable.register(BURIED_BUNKER, new BuriedBunkerFeature(createConfig(registryEntryLookup.getOrThrow(NWBiomeTags.BURIED_BUNKER_HAS_STRUCTURE), GenerationStep.Feature.UNDERGROUND_STRUCTURES, StructureTerrainAdaptation.NONE), registryEntryLookup2.getOrThrow(NWStructurePools.BURIED_BUNKER), Optional.of(Newworld.id("buried_bunker")), 1, ConstantHeightProvider.create(YOffset.fixed(60)), Optional.of(Heightmap.Type.WORLD_SURFACE_WG), 80));
    }

    private static Structure.Config createConfig(RegistryEntryList<Biome> biomes, Map<SpawnGroup, StructureSpawns> spawns, GenerationStep.Feature featureStep, StructureTerrainAdaptation terrainAdaptation) {
        return new Structure.Config(biomes, spawns, featureStep, terrainAdaptation);
    }

    private static Structure.Config createConfig(RegistryEntryList<Biome> biomes, GenerationStep.Feature featureStep, StructureTerrainAdaptation terrainAdaptation) {
        return createConfig(biomes, Map.of(), featureStep, terrainAdaptation);
    }

    private static Structure.Config createConfig(RegistryEntryList<Biome> biomes, StructureTerrainAdaptation terrainAdaptation) {
        return createConfig(biomes, Map.of(), GenerationStep.Feature.SURFACE_STRUCTURES, terrainAdaptation);
    }
}
