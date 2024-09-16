package net.digitalpear.newworld.init.data;

import com.terraformersmc.terraform.sign.api.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.api.block.TerraformWallSignBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.type.BlockSetTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.data.family.BlockFamily;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeGenerator;
import net.minecraft.item.*;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;


@SuppressWarnings("unused")
public class Woodset {

    private static final String SIGN_PATH = "entity/signs/";
    private static final String HANGING_SIGN_PATH = SIGN_PATH + "hanging/";
    private static final String HANGING_SIGN_GUI_PATH = "textures/gui/hanging_signs/";


    private final List<Block> registeredBlocksList = new ArrayList<>();
    private final List<Item> registeredItemsList = new ArrayList<>();
    private final Identifier name;
    private final MapColor sideColor;
    private final MapColor topColor;
    private final WoodPreset woodPreset;
    private BlockSetType blockSetType;
    private WoodType woodType;
    private BlockSoundGroup leaveSounds;

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
    private Block wallHangingSign;
    private Item signItem;
    private Item hangingSignItem;

    private BlockFamily.Builder blockFamily;

    private void registerWood(){
        blockSetType = createBlockSetType();
        woodType = new WoodTypeBuilder().build(this.getNameID(), getBlockSetType());

        planks = createPlanks();
        blockFamily = new BlockFamily.Builder(planks).unlockCriterionName(hasPlanks());

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
        wallHangingSign = createWallHangingSign();
        signItem = createSignItem();
        hangingSignItem = createHangingSignItem();

        blockFamily.stairs(stairs);
        blockFamily.slab(slab);
        if (getWoodPreset() == WoodPreset.BAMBOO){
            blockFamily.customFence(fence);
            blockFamily.customFenceGate(fenceGate);
        }else{
            blockFamily.fence(fence);
            blockFamily.fenceGate(fenceGate);
        }

        blockFamily.door(door);
        blockFamily.trapdoor(trapDoor);
        blockFamily.sign(slab, wallSign);
        blockFamily.sign(hangingSign, wallHangingSign);
        blockFamily.button(button);
        blockFamily.pressurePlate(pressurePlate);
    }




    public Woodset(Identifier name, MapColor sideColor, MapColor topColor, WoodPreset woodPreset){
        this.woodPreset = woodPreset;
        this.name = name;
        this.sideColor = sideColor;
        this.topColor = topColor;
        setLeavesSounds();
        registerWood();
    }
    public Woodset(Identifier name, MapColor sideColor, MapColor topColor, WoodPreset woodPreset, BlockSoundGroup leaveSounds){
        this.woodPreset = woodPreset;
        this.name = name;
        this.sideColor = sideColor;
        this.topColor = topColor;
        this.leaveSounds = leaveSounds;
        registerWood();
    }
    public Woodset(Identifier name, MapColor sideColor, MapColor topColor){
        this.woodPreset = WoodPreset.DEFAULT;
        this.name = name;
        this.sideColor = sideColor;
        this.topColor = topColor;
        setLeavesSounds();
        registerWood();
    }
    public Woodset(Identifier name, MapColor sideColor, MapColor topColor, BlockSoundGroup leaveSounds){
        this.woodPreset = WoodPreset.DEFAULT;
        this.name = name;
        this.sideColor = sideColor;
        this.topColor = topColor;
        this.leaveSounds = leaveSounds;
        registerWood();
    }
    private void setLeavesSounds(){
        if (isOverworldTreeWood() && woodPreset == WoodPreset.DEFAULT){
            this.leaveSounds = BlockSoundGroup.GRASS;
        } else if (woodPreset == WoodPreset.FANCY) {
            this.leaveSounds = BlockSoundGroup.CHERRY_LEAVES;
        }
    }
    private RegistryKey<Item> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.ITEM, Identifier.of(this.getModID(), id));
    }
    public Item createBlockItem(String id, Block block, BiFunction<Block, Item.Settings, Item> factory) {
        Item.Settings settings = new Item.Settings();
        return createBlockItem(id, block, factory, settings);
    }
    public Item createBlockItem(String id, Block block, BiFunction<Block, Item.Settings, Item> factory, Item.Settings settings) {
        Item item = factory.apply(block, settings.registryKey(keyOf(id)));
        return Registry.register(Registries.ITEM, Identifier.of(this.getModID(), id), item);
    }
    private Block createBlockWithItem(String blockID, AbstractBlock.Settings settings){
        return createBlockWithItem(blockID, Block::new, settings);
    }
    private Block createBlockWithItem(String blockID, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings){
        Block block = factory.apply(settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(name.getNamespace(), blockID))));
        createBlockItem(blockID, block, BlockItem::new);
        return createBlockWithoutItem(blockID, block);
    }
    private Block createBlockWithItem(String blockID, Function<AbstractBlock.Settings, Block> factory){
        return createBlockWithItem(blockID, factory, AbstractBlock.Settings.create());
    }
    private Block createBlockWithoutItem(String blockID, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings){
        Block block = factory.apply(settings.registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(name.getNamespace(), blockID))));
        return createBlockWithoutItem(blockID, block);
    }
    private Block createBlockWithoutItem(String blockID, Block block){
        Block listBlock = Registry.register(Registries.BLOCK, Identifier.of(this.getModID(), blockID), block);
        registeredBlocksList.add(listBlock);
        return listBlock;
    }

    public Item createItem(String blockID, Function<Item.Settings, Item> factory, Item.Settings settings){
        Item item = factory.apply(settings.registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(this.getModID(), blockID))));
        Item listItem = Registry.register(Registries.ITEM, Identifier.of(this.getModID(), blockID), item);
        registeredItemsList.add(listItem);
        return listItem;
    }

    private AbstractBlock.Settings createLogBlock(MapColor topMapColor, MapColor sideMapColor) {
        return AbstractBlock.Settings.create().mapColor(state -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).strength(2.0F).sounds(this.woodType().soundType());
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

    public Block getWallHangingSign() {
        return wallHangingSign;
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

    public BlockFamily getBlockFamily() {
        return blockFamily.build();
    }
    private Block createLog() {
        return createBlockWithItem(getLogName(), PillarBlock::new, createLogBlock(this.getSideColor(), this.getTopColor()));
    }
    private Block createStrippedLog() {
        return createBlockWithItem("stripped_" + getLogName(), PillarBlock::new, createLogBlock(this.getSideColor(), this.getTopColor()));
    }
    private Block createWood() {
        return createBlockWithItem(getWoodName(), PillarBlock::new, createLogBlock(this.getSideColor(), this.getSideColor()));
    }
    private Block createStrippedWood() {
        return createBlockWithItem("stripped_" + getWoodName(), PillarBlock::new, createLogBlock(this.getTopColor(), this.getTopColor()));
    }
    private Block createLeaves() {
        return createBlockWithItem(this.getName() + "_leaves", LeavesBlock::new, createLeavesBlock(leaveSounds));
    }
    private Block createPlanks(){
        return createBlockWithItem(this.getName() + "_planks", AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createStairs(){
        return createBlockWithItem(this.getName() + "_stairs", settings -> new StairsBlock(getBase().getDefaultState(), settings), AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createSlab(){
        return createBlockWithItem(this.getName() + "_slab", SlabBlock::new, AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createMosaic(){
        return createBlockWithItem(this.getName() + "_mosaic", AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createMosaicStairs(){
        return createBlockWithItem(this.getName() + "_mosaic_stairs", settings -> new StairsBlock(getBase().getDefaultState(), settings), AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createMosaicSlab(){
        return createBlockWithItem(this.getName() + "_mosaic_slab", SlabBlock::new, AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createFence(){
        return createBlockWithItem(this.getName() + "_fence", FenceBlock::new, AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createFenceGate(){
        return createBlockWithItem(this.getName() + "_fence_gate", settings -> new FenceGateBlock(this.getWoodType(), settings), AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createPressurePlate(){
        return createBlockWithItem(this.getName() + "_pressure_plate", settings -> new PressurePlateBlock(this.getBlockSetType(), settings), AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createButton(){
        return createBlockWithItem(this.getName() + "_button", settings -> new ButtonBlock(this.getBlockSetType(), 30, settings), AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createDoor(){
        return createBlockWithItem(this.getName() + "_door", settings -> new DoorBlock(this.getBlockSetType(), settings), AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createTrapDoor(){
        return createBlockWithItem(this.getName() + "_trapdoor", settings -> new TrapdoorBlock(this.getBlockSetType(), settings), AbstractBlock.Settings.copy(getBase()).sounds(getBlockSetType().soundType()).mapColor(getTopColor()));
    }
    private Block createSign(){
        return createBlockWithoutItem(this.getName() + "_sign", settings -> new TerraformSignBlock(this.getNameID().withPrefixedPath(SIGN_PATH), settings), AbstractBlock.Settings.copy(getSignBase()).mapColor(this.getTopColor()));
    }
    private Block createWallSign(){
        return createBlockWithoutItem(this.getName() + "_wall_sign", settings -> new TerraformWallSignBlock(this.getNameID().withPrefixedPath(SIGN_PATH), settings), AbstractBlock.Settings.copy(getSignBase()).mapColor(this.getTopColor()).lootTable(sign.getLootTableKey()));
    }
    private Block createHangingSign(){
        return createBlockWithoutItem(this.getName() + "_hanging_sign", settings -> new TerraformHangingSignBlock(this.getNameID().withPrefixedPath(HANGING_SIGN_PATH), this.getNameID().withPrefixedPath(HANGING_SIGN_GUI_PATH), settings), AbstractBlock.Settings.copy(getHangingSignBase()).mapColor(this.getTopColor()));
    }
    private Block createWallHangingSign(){
        return createBlockWithoutItem(this.getName() + "_wall_hanging_sign", settings -> new TerraformWallHangingSignBlock(this.getNameID().withPrefixedPath(HANGING_SIGN_PATH), this.getNameID().withPrefixedPath(HANGING_SIGN_GUI_PATH), settings), AbstractBlock.Settings.copy(getHangingSignBase()).mapColor(this.getTopColor()).lootTable(hangingSign.getLootTableKey()));
    }

    private Item createSignItem(){
        return createItem(this.getName() + "_sign", settings -> new SignItem(this.getSign(), this.getWallSign(), settings), new Item.Settings().maxCount(16));
    }
    private Item createHangingSignItem(){
        return createItem(this.getName() + "_hanging_sign", settings -> new HangingSignItem(this.getHangingSign(), this.getWallHangingSign(), settings), new Item.Settings().maxCount(16));
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
            return BlockSetTypeBuilder.copyOf(BlockSetType.BAMBOO).build(this.getNameID());
        }
        else if (this.getWoodPreset() == WoodPreset.FANCY){
            return BlockSetTypeBuilder.copyOf(BlockSetType.CHERRY).build(this.getNameID());
        }
        else if (this.woodPreset == WoodPreset.NETHER){
            return BlockSetTypeBuilder.copyOf(BlockSetType.CRIMSON).build(this.getNameID());
        }
        else{
            return new BlockSetTypeBuilder().build(this.getNameID());
        }
    }

    public boolean isOverworldTreeWood(){
        return this.getWoodPreset() == WoodPreset.DEFAULT || this.getWoodPreset() == WoodPreset.FANCY;
    }
    public boolean isBambooVariant(){
        return this.getWoodPreset() == WoodPreset.BAMBOO;
    }
    public enum WoodPreset {
        DEFAULT,
        FANCY,
        NETHER,
        BAMBOO
    }
    public static AbstractBlock.Settings createLeavesBlock(BlockSoundGroup soundGroup) {
        return AbstractBlock.Settings.create().mapColor(MapColor.DARK_GREEN).strength(0.2F).ticksRandomly().sounds(soundGroup).nonOpaque().allowsSpawning(Blocks::canSpawnOnLeaves).suffocates(Blocks::never).blockVision(Blocks::never).burnable().pistonBehavior(PistonBehavior.DESTROY).solidBlock(Blocks::never);
    }
    public static void addToBuildingTab(Item proceedingItem, Woodset woodset){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.addAfter(proceedingItem, woodset.getPlanks(), woodset.getStairs(), woodset.getSlab(),
                    woodset.getFence(), woodset.getFenceGate(),
                    woodset.getDoor(), woodset.getTrapDoor(),
                    woodset.getPressurePlate(), woodset.getButton());
            if (woodset.getWoodPreset() != WoodPreset.BAMBOO){
                entries.addAfter(proceedingItem, woodset.getWood(), woodset.getStrippedWood());
            }
            else{
                entries.addAfter(proceedingItem, woodset.getMosaic(), woodset.getMosaicStairs(), woodset.getMosaicSlab());
            }
            entries.addAfter(proceedingItem, woodset.getLog(), woodset.getStrippedLog());
        });
    }
    private String hasPlanks(){
        return RecipeGenerator.hasItem(getPlanks());
    }
    public void generateRecipes(RecipeGenerator recipeGenerator, RegistryEntryLookup<Item> lookup, RecipeExporter exporter, TagKey<Item> logs){
        recipeGenerator.offerPlanksRecipe(getPlanks(), logs, 4);
        recipeGenerator.generateFamily(getBlockFamily(), FeatureSet.empty());
//        recipeGenerator.createStairsRecipe(getStairs(), Ingredient.ofItems(getPlanks())).criterion(hasPlanks(), recipeGenerator.conditionsFromItem(getStairs())).offerTo(exporter);
//        recipeGenerator.offerSlabRecipe(RecipeCategory.BUILDING_BLOCKS, getSlab(), getPlanks());
//        recipeGenerator.createFenceRecipe(getFence(), Ingredient.ofItems(getPlanks())).criterion(hasPlanks(), recipeGenerator.conditionsFromItem(getPlanks())).offerTo(exporter);
//        recipeGenerator.createFenceGateRecipe(getFenceGate(), Ingredient.ofItems(getPlanks())).criterion(hasPlanks(), recipeGenerator.conditionsFromItem(NWBlocks.FIR_PLANKS)).offerTo(exporter);

        if (getWoodPreset() != WoodPreset.BAMBOO){
            recipeGenerator.offerBarkBlockRecipe(getWood(), getLog());
            recipeGenerator.offerBarkBlockRecipe(getStrippedWood(), getStrippedLog());
        }
        recipeGenerator.offerHangingSignRecipe(getHangingSignItem(), getStrippedLog());

//        recipeGenerator.createSignRecipe(NWItems.FIR_SIGN, Ingredient.ofItems(NWBlocks.FIR_PLANKS)).criterion(hasPlanks(), recipeGenerator.conditionsFromItem(getPlanks())).offerTo(exporter);
//        recipeGenerator.createDoorRecipe(NWBlocks.FIR_DOOR, Ingredient.ofItems(NWBlocks.FIR_PLANKS)).criterion(hasPlanks(), recipeGenerator.conditionsFromItem(getPlanks())).offerTo(exporter);
//        recipeGenerator.createTrapdoorRecipe(NWBlocks.FIR_TRAPDOOR, Ingredient.ofItems(NWBlocks.FIR_PLANKS)).criterion(hasPlanks(), recipeGenerator.conditionsFromItem(getPlanks())).offerTo(exporter);
//        ShapelessRecipeJsonBuilder.create(lookup, RecipeCategory.REDSTONE, getButton().asItem()).input(Ingredient.ofItems(getPlanks())).criterion(hasPlanks(), recipeGenerator.conditionsFromItem(getPlanks())).offerTo(exporter);
//        recipeGenerator.offerPressurePlateRecipe(NWBlocks.FIR_PRESSURE_PLATE, NWBlocks.FIR_PLANKS);
    }

}

