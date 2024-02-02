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

//            List<MultiNoiseUtil.NoiseHypercube> meadowPoints = new ParameterPointListBuilder()
//                    .temperature(Temperature.COOL, Temperature.NEUTRAL, Temperature.UNFROZEN, Temperature.ICY)
//                    .humidity(Humidity.NEUTRAL, Humidity.WET, Humidity.HUMID)
//                    .continentalness(Continentalness.span(Continentalness.FAR_INLAND, Continentalness.FAR_INLAND), Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND))
//                    .erosion(Erosion.EROSION_0, Erosion.EROSION_1, Erosion.EROSION_2)
//                    .depth(Depth.SURFACE)
//                    .weirdness(Weirdness.HIGH_SLICE_VARIANT_DESCENDING, Weirdness.MID_SLICE_NORMAL_DESCENDING, Weirdness.MID_SLICE_NORMAL_ASCENDING, Weirdness.MID_SLICE_VARIANT_DESCENDING, Weirdness.MID_SLICE_VARIANT_ASCENDING)
//                    .build();
//            meadowPoints.forEach(point -> builder.replaceBiome(point, NWBiomes.WOODED_MEADOW));

//            builder.replaceBiome(BiomeKeys.CHERRY_GROVE, NWBiomes.WOODED_MEADOW);
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
