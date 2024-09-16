package net.digitalpear.newworld.common.items;

import net.digitalpear.newworld.init.data.tags.NWBlockTags;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;

public class MattockItem extends MiningToolItem {
    public MattockItem(ToolMaterial toolMaterial, Settings properties) {
        super(toolMaterial, NWBlockTags.MATTOCK_MINEABLE, 0f, -3.0f, properties);
    }
}
