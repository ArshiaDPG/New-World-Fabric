package net.digitalpear.newworld.init.data;

import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.NWItems;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.impl.content.registry.CompostingChanceRegistryImpl;
import net.minecraft.block.Block;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;

public class NWData {
    //Adds to wandering trader trades
    public static void registerCustomTrades(){
        TradeOfferHelper.registerWanderingTraderOffers(1, factories -> {
            factories.add(registerSaplingTrade(NWBlocks.FIR_SAPLING));
        });
    }

    private static void registerStrippables() {
    }

    public static void registerCompostable(){
        CompostingChanceRegistryImpl.INSTANCE.add(NWBlocks.FIR_SAPLING, 0.3F);
        CompostingChanceRegistryImpl.INSTANCE.add(NWBlocks.FIR_LEAVES, 0.3F);
    }

    public static void registerLootTableModifications(){
        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            if (id.equals(LootTables.DESERT_PYRAMID_CHEST) || id.equals(LootTables.ANCIENT_CITY_CHEST) || id.equals(LootTables.STRONGHOLD_CORRIDOR_CHEST)){
                tableBuilder.pool(LootPool.builder()
                        .with(ItemEntry.builder(NWItems.MATTOCK_CRAFTING_TEMPLATE_SHAFT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(-3.0f, 1.0f))).build()));
            }
            else if (id.equals(LootTables.TRAIL_RUINS_RARE_ARCHAEOLOGY)){
                tableBuilder.modifyPools(builder -> builder.with(ItemEntry.builder(NWItems.MATTOCK_CRAFTING_TEMPLATE_HEAD)));
            }
        });
    }


    public static void addFlammable(Block block, int burn, int spread){
        FlammableBlockRegistry.getDefaultInstance().add(block, burn, spread);
    }
    public static void addFlammable(TagKey<Block> block, int burn, int spread){
        FlammableBlockRegistry.getDefaultInstance().add(block, burn, spread);
    }
    public static void registerFlammables(){

    }


    public static void init(){
        registerCustomTrades();
        registerStrippables();
        registerCompostable();
        registerFlammables();
        registerLootTableModifications();
    }




    public static TradeOffers.Factory registerSaplingTrade(ItemConvertible sapling){
        return (entity, random) ->  new TradeOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(sapling), 8, 1, 1);
    }
}
