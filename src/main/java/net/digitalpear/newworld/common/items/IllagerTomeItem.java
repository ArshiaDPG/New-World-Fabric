package net.digitalpear.newworld.common.items;

import net.digitalpear.newworld.init.NWItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class IllagerTomeItem extends Item {
    public IllagerTomeItem(Settings settings) {
        super(settings);
    }


    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return new ItemStack(NWItems.ILLAGER_TOME);
    }
}
