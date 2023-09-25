package net.digitalpear.newworld.init.worldgen.structures;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.digitalpear.newworld.Newworld;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolElement;
import net.minecraft.structure.pool.StructurePools;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class NWStructurePools {
    public static List<RegistryKey<StructurePool>> structurePools = new ArrayList<>();

    public static RegistryKey<StructurePool> of(String id) {
        RegistryKey<StructurePool> registryKey = RegistryKey.of(RegistryKeys.TEMPLATE_POOL, new Identifier(Newworld.MOD_ID, id));
        structurePools.add(registryKey);
        return registryKey;
    }
    public static final RegistryKey<StructurePool> BURIED_BUNKER = of("buried_bunker");

    public static void bootstrap(Registerable<StructurePool> structurePoolsRegisterable) {
        RegistryEntryLookup<StructureProcessorList> processorListRegistryEntryLookup = structurePoolsRegisterable.getRegistryLookup(RegistryKeys.PROCESSOR_LIST);
        RegistryEntryLookup<StructurePool> registryEntryLookup = structurePoolsRegisterable.getRegistryLookup(RegistryKeys.TEMPLATE_POOL);

        RegistryEntry.Reference<StructureProcessorList> bunkerProcessor = processorListRegistryEntryLookup.getOrThrow(NWProcessorLists.BURIED_BUNKER_REPLACEMENTS);

        structurePoolsRegisterable.register(BURIED_BUNKER, new StructurePool(registryEntryLookup.getOrThrow(StructurePools.EMPTY), ImmutableList.of(
                Pair.of(StructurePoolElement.ofProcessedSingle("newworld:buried_bunker", bunkerProcessor), 1),
                Pair.of(StructurePoolElement.ofProcessedSingle("newworld:buried_bunker_empty", bunkerProcessor), 10)
        ),
                StructurePool.Projection.RIGID));
    }

}
