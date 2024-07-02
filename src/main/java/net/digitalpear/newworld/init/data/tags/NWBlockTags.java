package net.digitalpear.newworld.init.data.tags;

import net.digitalpear.newworld.Newworld;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class NWBlockTags {
    public static final TagKey<Block> FIR_LOGS = of("fir_logs");
    public static final TagKey<Block> MATTOCK_MINEABLE = of("mattock_mineable");
    public static final TagKey<Block> SMALL_BUSH_PLANTABLE = of("small_bush_plantable");
    public static final TagKey<Block> TOMBSTONE_REPLACEABLE = of("tombstone_replaceable");

    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, Newworld.id(id));
    }
}
