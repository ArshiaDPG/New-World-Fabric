package net.digitalpear.newworld.init;


import gg.moonflower.pollen.api.item.PollinatedBoatItem;
import gg.moonflower.pollen.api.item.PollinatedSignItem;
import net.digitalpear.newworld.NewWorld;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


@SuppressWarnings("unused")
public class NWItems {


    public static Item createBoatItem(BoatEntity.Type type) {
        return new PollinatedBoatItem(NWEntities.FIR_BOAT, new Item.Settings().maxCount(1).group(ItemGroup.TRANSPORTATION));
    }

    public static Item createSignItem(Block sign, Block wall_sign) {
        return new PollinatedSignItem(new FabricItemSettings().maxCount(16).group(ItemGroup.DECORATIONS), sign, wall_sign);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(NewWorld.MOD_ID, name), item);
    }



    public static final Item FIR_BOAT = registerItem("fir_boat", createBoatItem(BoatEntity.Type.OAK));
    public static final Item FIR_SIGN = registerItem("fir_sign", createSignItem(NWBlocks.FIR_SIGN_BLOCK, NWBlocks.FIR_WALL_SIGN_BLOCK));


    public static void init(){
    }


}
