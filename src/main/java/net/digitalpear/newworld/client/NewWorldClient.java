package net.digitalpear.newworld.client;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.init.NWBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class NewWorldClient implements ClientModInitializer {

    protected static final Identifier SIGN_TEXTURE_ID = new Identifier(Newworld.MOD_ID, "entity/signs/fir");
    protected static final Identifier HANGING_SIGN_TEXTURE_ID = new Identifier(Newworld.MOD_ID, "entity/signs/hanging/fir");

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), NWBlocks.FIR_SAPLING, NWBlocks.POTTED_FIR_SAPLING, NWBlocks.FIR_LEAVES, NWBlocks.POTTED_POINTED_DRIPSTONE);

        TerraformBoatClientHelper.registerModelLayers(new Identifier(Newworld.MOD_ID, "fir"), false);


        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, SIGN_TEXTURE_ID));
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, HANGING_SIGN_TEXTURE_ID));

    }
}
