package net.digitalpear.newworld.init.tags;

import net.digitalpear.newworld.Newworld;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class NWBlockTags {
    public static final TagKey<Block> FIR_LOGS = of("fir_logs");
    public static final TagKey<Block> MATTOCK_MINEABLE = of("mattock_mineable");

    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(Newworld.MOD_ID, id));
    }
}
