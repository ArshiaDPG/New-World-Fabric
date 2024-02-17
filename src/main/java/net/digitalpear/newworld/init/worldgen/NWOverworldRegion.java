package net.digitalpear.newworld.init.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.worldgen.RegionUtils;

import java.util.List;
import java.util.function.Consumer;

public class NWOverworldRegion extends Region {

    public NWOverworldRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {


        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            addBiomeSimilar(mapper, BiomeKeys.MEADOW, NWBiomes.WOODED_MEADOW);

//            List<MultiNoiseUtil.NoiseHypercube> scrapyardPoints = new ParameterPointListBuilder()
//                    .temperature(Temperature.ICY, Temperature.COOL, Temperature.FROZEN, Temperature.NEUTRAL)
//                    .humidity(Humidity.FULL_RANGE)
//                    .continentalness(Continentalness.FULL_RANGE)
//                    .erosion(Erosion.EROSION_0, Erosion.EROSION_1, Erosion.EROSION_2, Erosion.EROSION_3)
//                    .depth(MultiNoiseUtil.ParameterRange.of(1.1f))
//                    .weirdness(Weirdness.FULL_RANGE)
//                    .build();

        });
    }
}
