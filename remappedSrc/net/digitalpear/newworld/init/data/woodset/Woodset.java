package net.digitalpear.newworld.init.data.woodset;

import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;

public class Woodset {


    private List<Block> registeredBlocksList = new ArrayList<>();
    private List<Item> registeredItemsList = new ArrayList<>();
    private Identifier name;
    private MapColor sideColor;
    private MapColor topColor;
    private WoodPreset woodPreset;
    private BlockSetType blockSetType;
    private WoodType woodType;

    private Block log;
    private Block strippedLog;
    private Block wood;
    private Block strippedWood;
    private Block leaves;
    private Block planks;
    private Block stairs;
    private Block slab;
    private Block mosaic;
    private Block mosaicStairs;
    private Block mosaicSlab;
    private Block fence;
    private Block fenceGate;
    private Block pressurePlate;
    private Block button;
    private Block door;
    private Block trapDoor;
    private Block sign;
    private Block wallSign;
    private Block hangingSign;
    private Block hangingWallSign;
    private Item signItem;
    private Item hangingSignItem;



    private void registerWood(){
        blockSetType = createBlockSetType();
        woodType = WoodTypeRegistry.register(this.getNameID(), getBlockSetType());

        log = createLog();
        strippedLog = createStrippedLog();
        StrippableBlockRegistry.register(log, strippedLog);

        if (this.getWoodPreset() != WoodPreset.BAMBOO){
            wood = createWood();
            strippedWood = createStrippedWood();
            StrippableBlockRegistry.register(wood, strippedWood);
        }
        else{
            mosaic = createMosaic();
            mosaicStairs = createMosaicStairs();
            mosaicSlab = createMosaicSlab();
        }
        if (this.getWoodPreset() == WoodPreset.DEFAULT || this.getWoodPreset() == WoodPreset.FANCY){
            leaves = createLeaves();
        }
        planks = createPlanks();
        stairs = createStairs();
        slab = createSlab();
        fence = createFence();
        fenceGate = createFenceGate();
        pressurePlate = createPressurePlate();
        button = createButton();
        door = createDoor();
        trapDoor = createTrapDoor();
        sign = createSign();
        wallSign = createWallSign();
        hangingSign = createHangingSign();
        hangingWallSign = createWallHangingSign();
        signItem = createSignItem();
        hangingSignItem = createHangingSignItem();
    }




    public Woodset(Identifier name, MapColor sideColor, MapColor topColor, WoodPreset woodPreset){
        this.woodPreset = woodPreset;
        this.name = name;
        this.sideColor = sideColor;
        this.topColor = topColor;
        registerWood();
    }
    public Woodset(Identifier name, MapColor sideColor, MapColor topColor){
        this.woodPreset = WoodPreset.DEFAULT;
        this.name = name;
        this.sideColor = sideColor;
        this.topColor = topColor;
        registerWood();
    }

    private BlockItem createBlockItem(String blockID, Block block){
        return Registry.register(Registries.ITEM, new Identifier(this.getModID(), blockID), new BlockItem(block, new Item.Settings()));
    }

    private Block createBlockWithItem(String blockID, Block block){
        createBlockItem(blockID, block);
        Block listBlock = Registry.register(Registries.BLOCK, new Identifier(this.getModID(), blockID), block);
        registeredBlocksList.add(listBlock);
        return listBlock;
    }

    private Block createBlockWithoutItem(String blockID, Block block){
        return Registry.register(Registries.BLOCK, new Identifier(this.getModID(), blockID), block);
    }
    private Item createSignItem(Block sign, Block wallSign) {
        return new SignItem(new FabricItemSettings().maxCount(16), sign, wallSign);
    }
    private Item createHangingSignItem(Block hangingSign, Block hangingWallSign) {
        return new HangingSignItem(hangingSign, hangingWallSign, new FabricItemSettings().maxCount(16));
    }
    public Item createItem(String blockID, Item item){
        Item listItem = Registry.register(Registries.ITEM, new Identifier(this.getModID(), blockID), item);
        registeredItemsList.add(listItem);
        return listItem;
    }

    private PillarBlock createLogBlock(MapColor topMapColor, MapColor sideMapColor) {
        return new PillarBlock(AbstractBlock.Settings.create().mapColor(state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).strength(2.0F).sounds(this.woodType().soundType()));
    }

    private BlockSetType woodType() {
        return this.blockSetType;
    }

    public Identifier getNameID() {
        return name;
    }
    public String getName() {
        return name.getPath();
    }
    public String getModID() {
        return name.getNamespace();
    }

    public BlockSetType getBlockSetType() {
        return blockSetType;
    }

    public WoodPreset getWoodPreset() {
        return woodPreset;
    }

    public MapColor getSideColor() {
        return sideColor;
    }

    public MapColor getTopColor() {
        return topColor;
    }

    public WoodType getWoodType() {
        return woodType;
    }

    public Block getButton() {
        return button;
    }

    public Block getFence() {
        return fence;
    }

    public Block getPlanks() {
        return planks;
    }

    public Block getSlab() {
        return slab;
    }

    public Block getFenceGate() {
        return fenceGate;
    }

    public Block getStairs() {
        return stairs;
    }

    public Block getDoor() {
        return door;
    }

    public Block getHangingSign() {
        return hangingSign;
    }

    public Block getHangingWallSign() {
        return hangingWallSign;
    }

    public Block getPressurePlate() {
        return pressurePlate;
    }

    public Block getSign() {
        return sign;
    }

    public Block getTrapDoor() {
        return trapDoor;
    }

    public Block getWallSign() {
        return wallSign;
    }

    public Item getHangingSignItem() {
        return hangingSignItem;
    }

    public Item getSignItem() {
        return signItem;
    }

    public Block getLog() {
        return log;
    }

    public Block getStrippedLog() {
        return strippedLog;
    }

    public Block getWood() {
        return wood;
    }

    public Block getStrippedWood() {
        return strippedWood;
    }

    public Block getMosaic() {
        return mosaic;
    }

    public Block getMosaicStairs() {
        return mosaicStairs;
    }

    public Block getMosaicSlab() {
        return mosaicSlab;
    }

    public Block getLeaves() {
        return leaves;
    }

    public List<Block> getRegisteredBlocksList() {
        return registeredBlocksList;
    }

    public List<Item> getRegisteredItemsList() {
        return registeredItemsList;
    }

    private Block createLog() {
        return createBlockWithItem(getLogName(), createLogBlock(this.getSideColor(), this.getTopColor()));
    }
    private Block createStrippedLog() {
        return createBlockWithItem("stripped_" + getLogName(), createLogBlock(this.getSideColor(), this.getTopColor()));
    }
    private Block createWood() {
        return createBlockWithItem(getWoodName(), createLogBlock(this.getSideColor(), this.getSideColor()));
    }
    private Block createStrippedWood() {
        return createBlockWithItem("stripped_" + getWoodName(), createLogBlock(this.getTopColor(), this.getTopColor()));
    }
    private Block createLeaves() {
        return createBlockWithItem(this.getName() + "_leaves", new LeavesBlock(AbstractBlock.Settings.copy(getBaseLeaves())));
    }
    private Block createPlanks(){
        return createBlockWithItem(this.getName() + "_planks", new Block(AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
    }
    private Block createStairs(){
        return createBlockWithItem(this.getName() + "_stairs", new StairsBlock(getBase().getDefaultState(), AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
    }
    private Block createSlab(){
        return createBlockWithItem(this.getName() + "_slab", new SlabBlock(AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
    }
    private Block createMosaic(){
        return createBlockWithItem(this.getName() + "_mosaic", new Block(AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
    }
    private Block createMosaicStairs(){
        return createBlockWithItem(this.getName() + "_mosaic_stairs", new StairsBlock(getBase().getDefaultState(), AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
    }
    private Block createMosaicSlab(){
        return createBlockWithItem(this.getName() + "_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
    }
    private Block createFence(){
        return createBlockWithItem(this.getName() + "_fence", new FenceBlock(AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor())));
    }
    private Block createFenceGate(){
        return createBlockWithItem(this.getName() + "_fence_gate", new FenceGateBlock(AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()), this.getWoodType()));
    }
    private Block createPressurePlate(){
        return createBlockWithItem(this.getName() + "_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()), this.getBlockSetType()));
    }
    private Block createButton(){
        return createBlockWithItem(this.getName() + "_button", new ButtonBlock(AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()), this.getBlockSetType(), 30, true));
    }
    private Block createDoor(){
        return createBlockWithItem(this.getName() + "_door", new DoorBlock(AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()), this.getBlockSetType()));
    }
    private Block createTrapDoor(){
        return createBlockWithItem(this.getName() + "_trapdoor", new TrapdoorBlock(AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()), this.getBlockSetType()));
    }
    private Block createSign(){
        return createBlockWithoutItem(this.getName() + "_sign", new TerraformSignBlock(new Identifier(getModID(), "entity/signs/" + this.getName()), AbstractBlock.Settings.copy(getSignBase()).mapColor(this.getTopColor())));
    }
    private Block createWallSign(){
        return createBlockWithoutItem(this.getName() + "_wall_sign", new TerraformWallSignBlock(new Identifier(getModID(), "entity/signs/" + this.getName()), AbstractBlock.Settings.copy(getSignBase()).mapColor(this.getTopColor()).dropsLike(sign)));
    }
    private Block createHangingSign(){
        return createBlockWithoutItem(this.getName() + "_hanging_sign", new TerraformHangingSignBlock(getNameID().withPrefixedPath("entity/signs/hanging/"), getNameID().withPrefixedPath("textures/gui/hanging_signs/"), AbstractBlock.Settings.copy(getHangingSignBase()).mapColor(this.getTopColor())));
    }
    private Block createWallHangingSign(){
        return createBlockWithoutItem(this.getName() + "_wall_hanging_sign", new TerraformWallHangingSignBlock(getNameID().withPrefixedPath("entity/signs/hanging/"), getNameID().withPrefixedPath("textures/gui/hanging_signs/"), AbstractBlock.Settings.copy(getHangingSignBase()).mapColor(this.getTopColor()).dropsLike(hangingSign)));
    }


    private Item createSignItem(){
        return createItem(this.getName() + "_sign", createSignItem(this.getSign(), this.getWallSign()));
    }
    private Item createHangingSignItem(){
        return createItem(this.getName() + "_hanging_sign", createHangingSignItem(this.getHangingSign(), this.getHangingWallSign()));
    }
    private String getWoodName(){
        String name;
        if (this.getWoodPreset() == WoodPreset.NETHER){
            name = this.getName() + "_hyphae";
        }
        else{
            name = this.getName() + "_wood";
        }
        return name;
    }
    private String getLogName(){
        String name;
        if (this.getWoodPreset() == WoodPreset.BAMBOO){
            name = this.getName() + "_block";
        }
        else if (this.getWoodPreset() == WoodPreset.NETHER){
            name = this.getName() + "_stem";
        }
        else{
            name = this.getName() + "_log";
        }
        return name;
    }
    private Block getBaseLeaves(){
        Block base;
        if (this.getWoodPreset() == WoodPreset.FANCY){
            base = Blocks.AZALEA_LEAVES;
        }
        else{
            base = Blocks.OAK_LEAVES;
        }
        return base;
    }
    private Block getBase(){
        Block base;
        if (this.getWoodPreset() == WoodPreset.BAMBOO){
            base = Blocks.BAMBOO_PLANKS;
        }
        else if (this.getWoodPreset() == WoodPreset.FANCY){
            base = Blocks.CHERRY_PLANKS;
        }
        else if (this.getWoodPreset() == WoodPreset.NETHER){
            base = Blocks.CRIMSON_PLANKS;
        }
        else{
            base = Blocks.OAK_PLANKS;
        }
        return base;
    }
    private Block getSignBase(){
        Block base;
        if (this.getWoodPreset() == WoodPreset.BAMBOO){
            base = Blocks.BAMBOO_SIGN;
        }
        else if (this.getWoodPreset() == WoodPreset.FANCY){
            base = Blocks.CHERRY_SIGN;
        }
        else if (this.getWoodPreset() == WoodPreset.NETHER){
            base = Blocks.CRIMSON_SIGN;
        }
        else{
            base = Blocks.OAK_SIGN;
        }
        return base;
    }
    private Block getHangingSignBase(){
        Block base;
        if (this.getWoodPreset() == WoodPreset.BAMBOO){
            base = Blocks.BAMBOO_HANGING_SIGN;
        }
        else if (this.getWoodPreset() == WoodPreset.FANCY){
            base = Blocks.CHERRY_HANGING_SIGN;
        }
        else if (this.getWoodPreset() == WoodPreset.NETHER){
            base = Blocks.CRIMSON_HANGING_SIGN;
        }
        else{
            base = Blocks.OAK_HANGING_SIGN;
        }
        return base;
    }

    private BlockSetType createBlockSetType(){
        if (this.woodPreset == WoodPreset.BAMBOO){
            return BlockSetTypeRegistry.register(this.getNameID(), true, BlockSoundGroup.BAMBOO_WOOD, SoundEvents.BLOCK_BAMBOO_WOOD_DOOR_CLOSE, SoundEvents.BLOCK_BAMBOO_WOOD_DOOR_OPEN, SoundEvents.BLOCK_BAMBOO_WOOD_TRAPDOOR_CLOSE, SoundEvents.BLOCK_BAMBOO_WOOD_TRAPDOOR_OPEN, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_BAMBOO_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.BLOCK_BAMBOO_WOOD_BUTTON_CLICK_OFF, SoundEvents.BLOCK_BAMBOO_WOOD_BUTTON_CLICK_ON);
        }
        else if (this.getWoodPreset() == WoodPreset.FANCY){
            return BlockSetTypeRegistry.register(this.getNameID(), true, BlockSoundGroup.CHERRY_WOOD, SoundEvents.BLOCK_CHERRY_WOOD_DOOR_CLOSE, SoundEvents.BLOCK_CHERRY_WOOD_DOOR_OPEN, SoundEvents.BLOCK_CHERRY_WOOD_TRAPDOOR_CLOSE, SoundEvents.BLOCK_CHERRY_WOOD_TRAPDOOR_OPEN, SoundEvents.BLOCK_CHERRY_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_CHERRY_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.BLOCK_CHERRY_WOOD_BUTTON_CLICK_OFF, SoundEvents.BLOCK_CHERRY_WOOD_BUTTON_CLICK_ON);
        }
        else if (this.woodPreset == WoodPreset.NETHER){
            return BlockSetTypeRegistry.register(this.getNameID(), true, BlockSoundGroup.NETHER_WOOD, SoundEvents.BLOCK_NETHER_WOOD_DOOR_CLOSE, SoundEvents.BLOCK_NETHER_WOOD_DOOR_OPEN, SoundEvents.BLOCK_NETHER_WOOD_TRAPDOOR_CLOSE, SoundEvents.BLOCK_NETHER_WOOD_TRAPDOOR_OPEN, SoundEvents.BLOCK_NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF, SoundEvents.BLOCK_NETHER_WOOD_PRESSURE_PLATE_CLICK_ON, SoundEvents.BLOCK_NETHER_WOOD_BUTTON_CLICK_OFF, SoundEvents.BLOCK_NETHER_WOOD_BUTTON_CLICK_ON);
        }
        else{
            return BlockSetTypeRegistry.registerWood(this.getNameID());
        }
    }

    public boolean isNormalWood(){
        return this.getWoodPreset() == WoodPreset.DEFAULT || this.getWoodPreset() == WoodPreset.FANCY;
    }
    public enum WoodPreset {
        DEFAULT,
        FANCY,
        NETHER,
        BAMBOO
    }

    public static void addToBuildingTab(Item proceedingItem, Woodset woodset){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(proceedingItem, woodset.getLog(), woodset.getStrippedLog());
            if (woodset.getWoodPreset() != WoodPreset.BAMBOO){
                entries.addAfter(proceedingItem, woodset.getWood(), woodset.getStrippedWood());
            }
            else{
                entries.addAfter(proceedingItem, woodset.getMosaic(), woodset.getMosaicStairs(), woodset.getMosaicSlab());
            }
            entries.addAfter(proceedingItem, woodset.getPlanks(), woodset.getStairs(), woodset.getSlab(),
                    woodset.getFence(), woodset.getFenceGate(),
                    woodset.getDoor(), woodset.getTrapDoor(),
                    woodset.getPressurePlate(), woodset.getButton());
        });
    }

}

