package net.digitalpear.newworld.common.worldgen.tree;

import net.digitalpear.newworld.init.worldgen.features.NWConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import java.util.Random;

public class FirSaplingGenerator extends SaplingGenerator {

    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return bees ? NWConfiguredFeatures.PLANTED_FIR_BEES_002 : NWConfiguredFeatures.PLANTED_FIR;
    }
}
