package net.digitalpear.newworld.init.data;

import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.village.TradeOffers;

public class NWData {
    //Adds to wandering trader trades
    public static void registerCustomTrades(){
        TradeOfferHelper.registerWanderingTraderOffers(wanderingTraderOffersBuilder -> {
            wanderingTraderOffersBuilder.addOffersToPool(
                    TradeOfferHelper.WanderingTraderOffersBuilder.SELL_COMMON_ITEMS_POOL,
                    new TradeOffers.SellItemFactory(NWBlocks.FIR.getLog(), 1, 8, 4, 1),
                    new TradeOffers.SellItemFactory(NWBlocks.FIR_SAPLING, 5, 1, 8, 1)
            );
        });
    }

    private static void registerStrippables() {
    }

    public static void registerFlammables(){
    }

    public static void registerCompostable(){
        CompostingChanceRegistry compostingChanceRegistry = CompostingChanceRegistry.INSTANCE;
        compostingChanceRegistry.add(NWBlocks.FIR_SAPLING, 0.3F);
        compostingChanceRegistry.add(NWBlocks.FIR.getLeaves(), 0.3F);
    }

    public static void registerLootTableModifications(){
        LootTableEvents.MODIFY.register((key, tableBuilder, source, wrapperLookup) -> {
            if (source.isBuiltin()){
                if (key.equals(LootTables.ANCIENT_CITY_CHEST) || key.equals(LootTables.STRONGHOLD_CORRIDOR_CHEST)){
                    tableBuilder.pool(LootPool.builder()
                            .with(ItemEntry.builder(NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD))
                            .conditionally(RandomChanceLootCondition.builder(0.15f))
                    );
                }
                else if (key.equals(LootTables.DESERT_PYRAMID_ARCHAEOLOGY) || key.equals(LootTables.TRAIL_RUINS_RARE_ARCHAEOLOGY)){
                    tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT)));

                } else if (key.equals(LootTables.WOODLAND_MANSION_CHEST)) {
                    tableBuilder.pool(LootPool.builder().conditionally(RandomChanceLootCondition.builder(0.85f))
                            .with(ItemEntry.builder(NWItems.ILLAGER_TOME)).build());
                }

                if (key.equals(LootTables.ANCIENT_CITY_CHEST)){
                    tableBuilder.pool(LootPool.builder().conditionally(RandomChanceLootCondition.builder(0.15f))
                            .with(ItemEntry.builder(NWBlocks.TOMBSTONE).weight(7))
                            .with(ItemEntry.builder(NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD).weight(1))
                    );
                }
            }
        });
    }


    public static void addFlammable(TagKey<Block> block, int burn, int spread){
        FlammableBlockRegistry.getDefaultInstance().add(block, burn, spread);
    }

    public static void init(){
        registerCustomTrades();
        registerStrippables();
        registerCompostable();
        registerFlammables();
        registerLootTableModifications();
    }
}
