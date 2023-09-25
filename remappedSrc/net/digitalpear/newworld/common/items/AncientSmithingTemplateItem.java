package net.digitalpear.newworld.common.items;

import net.digitalpear.newworld.Newworld;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.List;

public class AncientSmithingTemplateItem extends SmithingTemplateItem {
    private static final Formatting TITLE_FORMATTING = Formatting.GRAY;
    private static final Formatting DESCRIPTION_FORMATTING = Formatting.BLUE;


    public AncientSmithingTemplateItem(String name) {
        super(makeAppliesToText(name), makeIngredientText(name), makeTitleText(name), makeBaseSlotDescriptionText(name), makeAdditionsSlotDescriptionText(name), getEmptyList(), getEmptyList());
    }
    public static Text makeAdditionsSlotDescriptionText(String name){
        return Text.translatable(Util.createTranslationKey("item", new Identifier(Newworld.MOD_ID, "smithing_template." + name +".additions_slot_description")));
    }
    public static Text makeBaseSlotDescriptionText(String name){
        return Text.translatable(Util.createTranslationKey("item", new Identifier(Newworld.MOD_ID, "smithing_template." + name +".base_slot_description")));
    }
    public static Text makeIngredientText(String name){
        return Text.translatable(Util.createTranslationKey("item", new Identifier(Newworld.MOD_ID, "smithing_template." + name +".ingredients"))).formatted(DESCRIPTION_FORMATTING);
    }
    public static Text makeAppliesToText(String name){
        return Text.translatable(Util.createTranslationKey("item", new Identifier(Newworld.MOD_ID, "smithing_template." + name +".applies_to"))).formatted(DESCRIPTION_FORMATTING);
    }
    public static Text makeTitleText(String name){
        return Text.translatable(Util.createTranslationKey("upgrade", new Identifier(Newworld.MOD_ID, name))).formatted(TITLE_FORMATTING);
    }
    private static List<Identifier> getEmptyList() {
        return List.of();
    }
}
