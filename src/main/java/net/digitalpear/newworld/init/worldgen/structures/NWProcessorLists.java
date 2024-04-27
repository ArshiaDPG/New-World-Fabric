package net.digitalpear.newworld.init.worldgen.structures;

import com.google.common.collect.ImmutableList;
import net.digitalpear.newworld.Newworld;
import net.minecraft.block.Blocks;
import net.minecraft.block.LanternBlock;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.processor.RuleStructureProcessor;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorList;
import net.minecraft.structure.processor.StructureProcessorRule;
import net.minecraft.structure.rule.AlwaysTrueRuleTest;
import net.minecraft.structure.rule.RandomBlockMatchRuleTest;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class NWProcessorLists {
    public static List<RegistryKey<StructureProcessorList>> processorLists = new ArrayList<>();

    public static RegistryKey<StructureProcessorList> of(String id) {
        RegistryKey<StructureProcessorList> registryKey = RegistryKey.of(RegistryKeys.PROCESSOR_LIST, new Identifier(Newworld.MOD_ID, id));
        processorLists.add(registryKey);
        return registryKey;
    }

    public static final RegistryKey<StructureProcessorList> BURIED_BUNKER_REPLACEMENTS = of("buried_bunker_replacements");


    public static void bootstrap(Registerable<StructureProcessorList> processorListRegisterable) {
        //RegistryEntryLookup<Block> blockRegistry = processorListRegisterable.getRegistryLookup(RegistryKeys.BLOCK);

        register(processorListRegisterable, BURIED_BUNKER_REPLACEMENTS, ImmutableList.of(new RuleStructureProcessor(List.of(
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.STONE, 0.1f), AlwaysTrueRuleTest.INSTANCE, Blocks.INFESTED_STONE.getDefaultState()),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.DIRT, 0.2f), AlwaysTrueRuleTest.INSTANCE, Blocks.ROOTED_DIRT.getDefaultState()),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.DIRT, 0.1f), AlwaysTrueRuleTest.INSTANCE, Blocks.COARSE_DIRT.getDefaultState()),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.DIRT, 0.05f), AlwaysTrueRuleTest.INSTANCE, Blocks.AIR.getDefaultState()),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.CHAIN, 0.1f), AlwaysTrueRuleTest.INSTANCE, Blocks.LANTERN.getDefaultState().with(LanternBlock.HANGING, true)),
                new StructureProcessorRule(new RandomBlockMatchRuleTest(Blocks.MOSSY_STONE_BRICK_STAIRS, 0.8f), AlwaysTrueRuleTest.INSTANCE, Blocks.MOSSY_STONE_BRICK_SLAB.getDefaultState())
        ))));
    }


    private static void register(Registerable<StructureProcessorList> processorListRegisterable, RegistryKey<StructureProcessorList> key, List<StructureProcessor> processors) {
        processorListRegisterable.register(key, new StructureProcessorList(processors));
    }
}
