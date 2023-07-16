package net.digitalpear.newworld.init.data.tags;

import net.digitalpear.newworld.Newworld;
import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class NWEntityTypeTags {
    public static final TagKey<EntityType<?>> AUTOMATONS_WILL_IGNORE = of("automatons_will_ignore");

    private static TagKey<EntityType<?>> of(String id) {
        return TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(Newworld.MOD_ID, id));
    }
}
