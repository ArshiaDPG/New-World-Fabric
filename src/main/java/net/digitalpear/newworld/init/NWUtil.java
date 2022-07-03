package net.digitalpear.newworld.init;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.impl.content.registry.CompostingChanceRegistryImpl;
import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;

public class NWUtil {
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
        CompostingChanceRegistryImpl.INSTANCE.add(NWBlocks.FIR_SAPLING, 0.3F);
    }


    public static void init(){
        registerCustomTrades();
        registerStrippables();
        registerCompostable();
    }




    public static TradeOffers.Factory registerSaplingTrade(ItemConvertible sapling){
        return (entity, random) ->  new TradeOffer(new ItemStack(Items.EMERALD, 5), new ItemStack(sapling), 8, 1, 0.05f);
    }
}
