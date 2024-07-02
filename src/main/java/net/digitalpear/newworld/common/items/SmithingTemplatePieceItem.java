package net.digitalpear.newworld.common.items;


import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class SmithingTemplatePieceItem extends Item {
    public SmithingTemplatePieceItem(Settings settings) {
        super(settings);
    }


    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(getDescription().formatted(Formatting.GRAY));
        super.appendTooltip(stack, context, tooltip, type);
    }

    public MutableText getDescription() {
        return Text.translatable(this.getTranslationKey() + ".desc");
    }
}
