package net.digitalpear.newworld.common.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.digitalpear.newworld.init.worldgen.NWBiomes;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class NWRegion extends Region {
    public NWRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
            this.addBiomeSimilar(mapper, BiomeKeys.MEADOW, NWBiomes.WOODED_MEADOW_KEY);
    }
}
