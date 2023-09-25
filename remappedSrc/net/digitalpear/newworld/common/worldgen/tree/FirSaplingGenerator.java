package net.digitalpear.newworld.common.worldgen.tree;

import net.digitalpear.newworld.init.worldgen.features.NWConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class FirSaplingGenerator extends SaplingGenerator {

    @Nullable
    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(net.minecraft.util.math.random.Random random, boolean bees) {
        return bees ? NWConfiguredFeatures.GROWN_FIR_BEES_002 : NWConfiguredFeatures.GROWN_FIR;
    }
}
