package net.digitalpear.newworld.init;


import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.items.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.item.BoatItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Rarity;

import java.util.function.Function;


@SuppressWarnings("unused")
public class NWItems {

    public static RegistryKey<Item> keyOf(String name){
        return RegistryKey.of(RegistryKeys.ITEM, Newworld.id(name));
    }
    private static Item registerBoatItem(String name, EntityType<? extends AbstractBoatEntity> boat){
        return registerItem(name, settings -> new BoatItem(boat, settings), new Item.Settings().maxCount(1));
    }
    private static Item registerItem(String name, Function<Item.Settings, Item> factory, Item.Settings settings) {
        return Items.register(keyOf(name), factory, settings.useItemPrefixedTranslationKey());
    }



//    public static final Item FIR_BOAT = registerBoatItem("fir_boat", NWBlocks.FIR.getBoat());
//    public static final Item FIR_CHEST_BOAT = registerBoatItem("fir_chest_boat", NWBlocks.FIR.getChestBoat());

    public static final Item MATTOCK_CRAFTING_TEMPLATE = registerItem("mattock_crafting_template", settings -> new AncientSmithingTemplateItem("mattock_crafting", settings), new Item.Settings());
    public static final Item MATTOCK_CRAFTING_TEMPLATE_HEAD = registerItem("mattock_crafting_template_head", SmithingTemplatePieceItem::new, new Item.Settings());
    public static final Item MATTOCK_CRAFTING_TEMPLATE_SHAFT = registerItem("mattock_crafting_template_shaft", SmithingTemplatePieceItem::new, new Item.Settings());

    public static final Item TOMBSTONE = registerItem("tombstone", settings -> new TombstoneBlockItem(NWBlocks.TOMBSTONE, settings), new Item.Settings().maxCount(1));

    public static final Item ANCIENT_MATTOCK = registerItem("ancient_mattock", Item::new, new Item.Settings()
            .pickaxe(NWToolMaterials.ANCIENT, 0F, -3.0F)
            .axe(NWToolMaterials.ANCIENT, 0F, -3.0F)
            .shovel(NWToolMaterials.ANCIENT, 0F, -3.0F)
            .hoe(NWToolMaterials.ANCIENT, 0F, -3.0F)
            .maxCount(1)
            .rarity(Rarity.RARE)
    );


    public static final Item ILLAGER_TOME = registerItem("illager_tome", IllagerTomeItem::new, new Item.Settings().rarity(Rarity.EPIC));

    public static final Item JEB_BOOK = registerItem("jeb_book", JebBookItem::new, new Item.Settings());


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
            entries.addAfter(Items.SPRUCE_CHEST_BOAT, NWBlocks.FIR.getBoatItem(), NWBlocks.FIR.getChestBoatItem());
            entries.addAfter(Items.NETHERITE_HOE, ANCIENT_MATTOCK);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addAfter(Items.SPRUCE_HANGING_SIGN, NWBlocks.FIR.getSignItem(), NWBlocks.FIR.getHangingSignItem());
            entries.add(NWBlocks.TOMBSTONE);
        });
    }
}
