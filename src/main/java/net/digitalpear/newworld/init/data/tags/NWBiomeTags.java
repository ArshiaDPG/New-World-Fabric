package net.digitalpear.newworld.init.data.tags;

import net.digitalpear.newworld.Newworld;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.Biome;

public class NWBiomeTags {
    public static final TagKey<Biome> BURIED_BUNKER_HAS_STRUCTURE = hasStructure("buried_bunker");

    private static TagKey<Biome> hasStructure(String id) {
        return TagKey.of(RegistryKeys.BIOME, new Identifier(Newworld.MOD_ID, "has_structure/" + id));
    }
    private static TagKey<Biome> of(String id) {
        return TagKey.of(RegistryKeys.BIOME, new Identifier(Newworld.MOD_ID, id));
    }
}
