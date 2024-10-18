package net.digitalpear.newworld.client;

import net.digitalpear.newworld.init.NWBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class NewWorldClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        NWBlocks.FIR.registerBoatModels();

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), NWBlocks.FIR_SAPLING, NWBlocks.POTTED_FIR_SAPLING, NWBlocks.FIR.getLeaves(), NWBlocks.POTTED_POINTED_DRIPSTONE);
    }
}
