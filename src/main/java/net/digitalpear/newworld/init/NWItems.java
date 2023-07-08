package net.digitalpear.newworld.init;


import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.impl.item.TerraformBoatItem;
import net.digitalpear.newworld.Newworld;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;



@SuppressWarnings("unused")
public class NWItems {

    public static final RegistryKey<TerraformBoatType> FIR_BOAT_TYPE = TerraformBoatTypeRegistry.createKey(new Identifier(Newworld.MOD_ID, "fir"));

    public static Item createBoatItem(RegistryKey<TerraformBoatType> registryKey) {
        return new TerraformBoatItem(registryKey, false, new Item.Settings().maxCount(1));
    }
    public static Item createChestBoatItem(RegistryKey<TerraformBoatType> registryKey) {
        return new TerraformBoatItem(registryKey, true, new Item.Settings().maxCount(1));
    }



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Newworld.MOD_ID, name), item);
    }



    public static final Item FIR_BOAT = registerItem("fir_boat", createBoatItem(FIR_BOAT_TYPE));
    public static final Item FIR_CHEST_BOAT = registerItem("fir_chest_boat", createChestBoatItem(FIR_BOAT_TYPE));
    public static final Item FIR_SIGN = NWBlocks.FIR.getSignItem();
    public static final Item FIR_HANGING_SIGN = NWBlocks.FIR.getHangingSignItem();


    public static void init(){

        TerraformBoatType boat = new TerraformBoatType.Builder()
                .item(FIR_BOAT)
                .chestItem(FIR_CHEST_BOAT)
                .planks(NWBlocks.FIR_PLANKS.asItem())
                .build();
    }
}
