package net.digitalpear.newworld.common.items;

import net.digitalpear.newworld.init.data.tags.NWBlockTags;
import net.minecraft.item.MiningToolItem;
import net.minecraft.item.ToolMaterial;

public class MattockItem extends MiningToolItem {


    public MattockItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Settings properties) {
        super(attackDamage, attackSpeed, toolMaterial, NWBlockTags.MATTOCK_MINEABLE, properties);
    }
}
