package net.digitalpear.newworld.common.items;


import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

public class SmithingTemplatePieceItem extends Item {
    public SmithingTemplatePieceItem(Settings settings) {
        super(settings);
    }


    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(getDescription().formatted(Formatting.GRAY));
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }



    public MutableText getDescription() {
        return Text.translatable(this.getTranslationKey() + ".desc");
    }
}
