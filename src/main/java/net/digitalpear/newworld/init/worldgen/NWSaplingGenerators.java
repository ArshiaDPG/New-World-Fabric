package net.digitalpear.newworld.init.worldgen;

import net.digitalpear.newworld.init.worldgen.features.NWConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

public class NWSaplingGenerators {
    public static final SaplingGenerator FIR = new SaplingGenerator("fir", Optional.empty(), Optional.of(NWConfiguredFeatures.GROWN_FIR), Optional.of(NWConfiguredFeatures.GROWN_FIR_BEES_002));

}
