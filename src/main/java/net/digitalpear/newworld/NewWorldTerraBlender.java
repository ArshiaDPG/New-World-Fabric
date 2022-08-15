package net.digitalpear.newworld;

import net.digitalpear.newworld.init.worldgen.NWRegion;
import net.minecraft.util.Identifier;
import terrablender.api.Regions;
import terrablender.api.TerraBlenderApi;

public class NewWorldTerraBlender implements TerraBlenderApi {
    @Override
    public void onTerraBlenderInitialized()
    {
        Regions.register(new NWRegion(new Identifier(NewWorld.MOD_ID, "overworld"), 2));
        NewWorld.LOGGER.info("New World biomes loaded.");
    }
}
