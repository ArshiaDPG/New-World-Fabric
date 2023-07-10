package net.digitalpear.newworld.init.data;

import net.digitalpear.newworld.init.NWBlocks;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.impl.content.registry.CompostingChanceRegistryImpl;
import net.minecraft.block.Block;
import net.minecraft.block.FireBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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
        StrippableBlockRegistry.register(NWBlocks.FIR_LOG, NWBlocks.STRIPPED_FIR_LOG);
        StrippableBlockRegistry.register(NWBlocks.FIR_WOOD, NWBlocks.STRIPPED_FIR_WOOD);
    }

    public static void registerCompostable(){
        CompostingChanceRegistryImpl.INSTANCE.add(NWBlocks.FIR_SAPLING, 0.3F);
        CompostingChanceRegistryImpl.INSTANCE.add(NWBlocks.FIR_LEAVES, 0.3F);
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
    }




    public static TradeOffers.Factory registerSaplingTrade(ItemConvertible sapling){
        return (entity, random) ->  new TradeOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(sapling), 8, 1, 1);
    }
}
