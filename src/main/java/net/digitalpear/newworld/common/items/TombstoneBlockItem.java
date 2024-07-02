package net.digitalpear.newworld.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class TombstoneBlockItem extends BlockItem {
    public TombstoneBlockItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return new ItemStack(this);
    }
}
