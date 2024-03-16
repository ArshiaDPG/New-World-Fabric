package net.digitalpear.newworld.init;


import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.impl.item.TerraformBoatItem;
import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.common.items.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import java.awt.print.Book;


@SuppressWarnings("unused")
public class NWItems {

    public static final RegistryKey<TerraformBoatType> FIR_BOAT_TYPE = TerraformBoatTypeRegistry.createKey(new Identifier(Newworld.MOD_ID, "fir"));

    public static Item createBoatItem(RegistryKey<TerraformBoatType> registryKey) {
        return new TerraformBoatItem(registryKey, false, new Item.Settings().maxCount(1));
    }
    public static Item createChestBoatItem(RegistryKey<TerraformBoatType> registryKey) {
        return new TerraformBoatItem(registryKey, true, new Item.Settings().maxCount(1));
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Newworld.MOD_ID, name), item);
    }


    public static final Item FIR_BOAT = registerItem("fir_boat", createBoatItem(FIR_BOAT_TYPE));
    public static final Item FIR_CHEST_BOAT = registerItem("fir_chest_boat", createChestBoatItem(FIR_BOAT_TYPE));
    public static final Item FIR_SIGN = NWBlocks.FIR.getSignItem();
    public static final Item FIR_HANGING_SIGN = NWBlocks.FIR.getHangingSignItem();

    public static final Item MATTOCK_CRAFTING_TEMPLATE = registerItem("mattock_crafting_template", new AncientSmithingTemplateItem("mattock_crafting"));
    public static final Item MATTOCK_CRAFTING_TEMPLATE_HEAD = registerItem("mattock_crafting_template_head", new SmithingTemplatePieceItem(new Item.Settings()));
    public static final Item MATTOCK_CRAFTING_TEMPLATE_SHAFT = registerItem("mattock_crafting_template_shaft", new SmithingTemplatePieceItem(new Item.Settings()));


    public static final Item TOMBSTONE = registerItem("tombstone", new TombstoneBlockItem(NWBlocks.TOMBSTONE, new Item.Settings().maxCount(1)));

    public static final Item ANCIENT_MATTOCK = registerItem("ancient_mattock", new MattockItem(NWToolMaterials.ANCIENT, 0.0F, -3.0F, new Item.Settings()));

    public static final Item ILLAGER_TOME = registerItem("illager_tome", new IllagerTomeItem(new Item.Settings()));

    public static final Item JEB_BOOK = registerItem("jeb_book", new JebBookItem(new Item.Settings()));

    public static void init(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, MATTOCK_CRAFTING_TEMPLATE);
            entries.addAfter(MATTOCK_CRAFTING_TEMPLATE, MATTOCK_CRAFTING_TEMPLATE_HEAD);
            entries.addAfter(MATTOCK_CRAFTING_TEMPLATE_HEAD, MATTOCK_CRAFTING_TEMPLATE_SHAFT);
            entries.add(ILLAGER_TOME);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.addAfter(Items.SPRUCE_LOG, NWBlocks.FIR_LOG);
                });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
            entries.addAfter(Items.SPRUCE_CHEST_BOAT, FIR_BOAT, FIR_CHEST_BOAT);
            entries.addAfter(Items.NETHERITE_HOE, ANCIENT_MATTOCK);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.addAfter(Items.SPRUCE_HANGING_SIGN, FIR_SIGN, FIR_HANGING_SIGN);
            entries.add(NWBlocks.TOMBSTONE);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> {
            entries.add(JEB_BOOK);
        });

    }
}
