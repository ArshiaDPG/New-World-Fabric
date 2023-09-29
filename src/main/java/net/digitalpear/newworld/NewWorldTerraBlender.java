package net.digitalpear.newworld;

import net.digitalpear.newworld.init.worldgen.NWOverworldRegion;
import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

public class NewWorldTerraBlender implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized()
    {
        Regions.register(new NWOverworldRegion(new Identifier(Newworld.MOD_ID, "overworld"), 5));
        Newworld.LOGGER.info(Newworld.MOD_NAME + " biomes loaded.");
    }
}
