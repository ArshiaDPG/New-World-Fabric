package net.digitalpear.newworld.init.data.tags;

import net.digitalpear.newworld.Newworld;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class NWItemTags {
    public static final TagKey<Item> FIR_LOGS = of("fir_logs");


    private static TagKey<Item> of(String id) {
        return TagKey.of(RegistryKeys.ITEM, Newworld.id(id));
    }
}
