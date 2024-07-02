package net.digitalpear.newworld.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class IllagerTomeItem extends Item {
    public IllagerTomeItem(Settings settings) {
        super(settings);
    }


    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return new ItemStack(this);
    }
}
