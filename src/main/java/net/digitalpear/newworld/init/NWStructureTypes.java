package net.digitalpear.newworld.init;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.worldgen.structures.BuriedBunkerFeature;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.structure.StructureType;

public class NWStructureTypes {


    public static StructureType<BuriedBunkerFeature> BURIED_BUNKER = Registry.register(Registries.STRUCTURE_TYPE, Newworld.id("buried_bunker"), () -> BuriedBunkerFeature.CODEC);


    public static void init() {
    }
}