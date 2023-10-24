package net.digitalpear.newworld.common.items;

import net.digitalpear.newworld.init.NWItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

public class ArcaneTomeItem extends Item {
    public ArcaneTomeItem(Settings settings) {
        super(settings);
    }

    @Override
    public Rarity getRarity(ItemStack stack) {
        return Rarity.EPIC;
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return new ItemStack(NWItems.ARCANE_TOME);
    }
}
