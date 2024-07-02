package net.digitalpear.newworld.client;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.digitalpear.newworld.Newworld;
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
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), NWBlocks.FIR_SAPLING, NWBlocks.POTTED_FIR_SAPLING, NWBlocks.FIR_LEAVES, NWBlocks.POTTED_POINTED_DRIPSTONE);

        TerraformBoatClientHelper.registerModelLayers(Newworld.id("fir"), false);



//        TexturedRenderLayers.SIGN_TYPE_TEXTURES.put(NWBlocks.FIR.getWoodType(), TexturedRenderLayers.getSignTextureId(NWBlocks.FIR.getWoodType()));
//        TexturedRenderLayers.HANGING_SIGN_TYPE_TEXTURES.put(NWBlocks.FIR.getWoodType(), TexturedRenderLayers.getHangingSignTextureId(NWBlocks.FIR.getWoodType()));

    }
}
