package net.digitalpear.newworld.init;


import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.impl.item.TerraformBoatItem;
import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.items.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Rarity;

import java.util.function.Function;


@SuppressWarnings("unused")
public class NWItems {

    public static RegistryKey<Item> keyOf(String name){
        return RegistryKey.of(RegistryKeys.ITEM, Newworld.id(name));
    }
    public static final RegistryKey<TerraformBoatType> FIR_BOAT_TYPE = TerraformBoatTypeRegistry.createKey(Newworld.id("fir"));

    private static Item registerBoatItem(String name, boolean chest, RegistryKey<TerraformBoatType> registryKey){
        return registerItem(name, settings -> new TerraformBoatItem(registryKey, chest, new Item.Settings().maxCount(1)));
    }
    private static Item registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        Item item = factory.apply(settings.registryKey(keyOf(name)));
        return Registry.register(Registries.ITEM, Newworld.id(name), item);
    }
    private static Item registerItem(String name, Function<Item.Settings, Item> factory) {
        return registerItem(name, factory, new Item.Settings());
    }


    public static final Item FIR_BOAT = registerBoatItem("fir_boat", false, FIR_BOAT_TYPE);
    public static final Item FIR_CHEST_BOAT = registerBoatItem("fir_chest_boat", true, FIR_BOAT_TYPE);
    public static final Item FIR_SIGN = NWBlocks.FIR.getSignItem();
    public static final Item FIR_HANGING_SIGN = NWBlocks.FIR.getHangingSignItem();

    public static final Item MATTOCK_CRAFTING_TEMPLATE = registerItem("mattock_crafting_template", settings -> new AncientSmithingTemplateItem("mattock_crafting"));
    public static final Item MATTOCK_CRAFTING_TEMPLATE_HEAD = registerItem("mattock_crafting_template_head", settings -> new SmithingTemplatePieceItem(new Item.Settings()));
    public static final Item MATTOCK_CRAFTING_TEMPLATE_SHAFT = registerItem("mattock_crafting_template_shaft", settings -> new SmithingTemplatePieceItem(new Item.Settings()));


    public static final Item TOMBSTONE = registerItem("tombstone", settings -> new TombstoneBlockItem(NWBlocks.TOMBSTONE, new Item.Settings().maxCount(1)));

    public static final Item ANCIENT_MATTOCK = registerItem("ancient_mattock", settings -> new MattockItem(NWToolMaterials.ANCIENT, new Item.Settings()));

    public static final Item ILLAGER_TOME = registerItem("illager_tome", settings -> new IllagerTomeItem(new Item.Settings().rarity(Rarity.EPIC)));

    public static final Item JEB_BOOK = registerItem("jeb_book", settings -> new JebBookItem(new Item.Settings()));


    public static void init(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, MATTOCK_CRAFTING_TEMPLATE);
            entries.addAfter(MATTOCK_CRAFTING_TEMPLATE, MATTOCK_CRAFTING_TEMPLATE_HEAD);
            entries.addAfter(MATTOCK_CRAFTING_TEMPLATE_HEAD, MATTOCK_CRAFTING_TEMPLATE_SHAFT);
            entries.add(ILLAGER_TOME);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.SPRUCE_LOG, NWBlocks.FIR.getLog());
                });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.addAfter(Items.SPRUCE_CHEST_BOAT, FIR_BOAT, FIR_CHEST_BOAT);
            entries.addAfter(Items.NETHERITE_HOE, ANCIENT_MATTOCK);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addAfter(Items.SPRUCE_HANGING_SIGN, FIR_SIGN, FIR_HANGING_SIGN);
            entries.add(NWBlocks.TOMBSTONE);
        });

    }
}
