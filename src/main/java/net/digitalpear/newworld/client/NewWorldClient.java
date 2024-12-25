package net.digitalpear.newworld.client;

import net.digitalpear.newworld.init.NWBlocks;
import net.digitalpear.newworld.init.data.Woodset;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.RaftEntityRenderer;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.RaftEntityModel;
import net.minecraft.util.Identifier;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public class NewWorldClient implements ClientModInitializer {


    @Override
    public void onInitializeClient() {
        registerBoatModels(NWBlocks.FIR);

        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), NWBlocks.MOSS_SPROUTS, NWBlocks.FIR_SAPLING, NWBlocks.POTTED_FIR_SAPLING, NWBlocks.FIR.getLeaves(), NWBlocks.POTTED_POINTED_DRIPSTONE);
    }


    public static void registerBoatModels(Woodset woodset){
        if (!woodset.getWoodsetSettings().hasBoats()){
            return;
        }
        Identifier layerName = woodset.getNameID().withPrefixedPath("boat/");
        Identifier chestLayerName = woodset.getNameID().withPrefixedPath("chest_boat/");

        final EntityModelLayer BOAT_MODEL_LAYER = new EntityModelLayer(layerName, "main");
        final EntityModelLayer CHEST_BOAT_MODEL_LAYER = new EntityModelLayer(chestLayerName, "main");

        final boolean raft = Objects.equals(woodset.getWoodsetSettings().getBoatType(), Woodset.Settings.BoatType.RAFT);

        EntityModelLayerRegistry.registerModelLayer(BOAT_MODEL_LAYER, raft ? RaftEntityModel::getTexturedModelData : BoatEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(woodset.getBoat(), ctx -> raft ? new RaftEntityRenderer(ctx, BOAT_MODEL_LAYER) : new BoatEntityRenderer(ctx, BOAT_MODEL_LAYER));

        EntityModelLayerRegistry.registerModelLayer(CHEST_BOAT_MODEL_LAYER, raft ? RaftEntityModel::getChestTexturedModelData : BoatEntityModel::getChestTexturedModelData);
        EntityRendererRegistry.register(woodset.getBoat(), ctx -> raft ? new RaftEntityRenderer(ctx, CHEST_BOAT_MODEL_LAYER) : new BoatEntityRenderer(ctx, CHEST_BOAT_MODEL_LAYER));
    }
}
