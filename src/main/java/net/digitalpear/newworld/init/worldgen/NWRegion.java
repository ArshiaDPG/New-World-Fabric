package net.digitalpear.newworld.init.worldgen;

import com.mojang.datafixers.util.Pair;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.List;
import java.util.function.Consumer;

import static terrablender.api.ParameterUtils.*;

public class NWRegion extends Region {

    public NWRegion(Identifier name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            List<MultiNoiseUtil.NoiseHypercube> meadowPoints = new ParameterPointListBuilder()
                    .temperature(Temperature.COOL, Temperature.NEUTRAL, Temperature.UNFROZEN, Temperature.ICY)
                    .humidity(Humidity.ARID, Humidity.NEUTRAL, Humidity.WET, Humidity.HUMID)
                    .continentalness(Continentalness.span(Continentalness.FAR_INLAND, Continentalness.FAR_INLAND), Continentalness.span(Continentalness.MID_INLAND, Continentalness.FAR_INLAND))
                    .erosion(Erosion.EROSION_0, Erosion.EROSION_1, Erosion.EROSION_2)
                    .depth(Depth.SURFACE, Depth.FLOOR)
                    .weirdness(Weirdness.HIGH_SLICE_VARIANT_ASCENDING, Weirdness.HIGH_SLICE_VARIANT_DESCENDING, Weirdness.MID_SLICE_NORMAL_ASCENDING)
                    .build();

            meadowPoints.forEach(point -> builder.replaceBiome(point, NWOverworldBiomes.WOODED_MEADOW_KEY));
        });
    }
}
