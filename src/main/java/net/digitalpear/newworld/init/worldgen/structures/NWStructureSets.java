package net.digitalpear.newworld.init.worldgen.structures;

import net.digitalpear.newworld.Newworld;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.gen.chunk.placement.SpreadType;
import net.minecraft.world.gen.structure.Structure;

import java.util.ArrayList;
import java.util.List;

public class NWStructureSets {
    public static List<RegistryKey<StructureSet>> structureSets = new ArrayList<>();

    public static RegistryKey<StructureSet> of(String id) {
        RegistryKey<StructureSet> registryKey = RegistryKey.of(RegistryKeys.STRUCTURE_SET, new Identifier(Newworld.MOD_ID, id));
        structureSets.add(registryKey);
        return registryKey;
    }
    public static final RegistryKey<StructureSet> BURIED_BUNKER = of("buried_bunker");

    public static void bootstrap(Registerable<StructureSet> structureSetRegisterable) {
        RegistryEntryLookup<Structure> registryEntryLookup = structureSetRegisterable.getRegistryLookup(RegistryKeys.STRUCTURE);
        RegistryEntryLookup<Biome> registryEntryLookup2 = structureSetRegisterable.getRegistryLookup(RegistryKeys.BIOME);


        structureSetRegisterable.register(BURIED_BUNKER, new StructureSet(registryEntryLookup.getOrThrow(NWStructures.BURIED_BUNKER),
                new RandomSpreadStructurePlacement(24, 4, SpreadType.LINEAR, 1694767080)));
    }

}
