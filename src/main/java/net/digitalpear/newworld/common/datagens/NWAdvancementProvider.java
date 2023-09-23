package net.digitalpear.newworld.common.datagens;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.init.NWItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class NWAdvancementProvider extends FabricAdvancementProvider {

    public NWAdvancementProvider(FabricDataOutput output) {
        super(output);
    }


    @Override
    public void generateAdvancement(Consumer<AdvancementEntry> consumer) {
        /*
            BROKEN
         */
//        AdvancementEntry collectAncientMattock = Advancement.Builder.create()
//                .display(
//                        NWItems.ANCIENT_MATTOCK, // The display icon
//                        Text.translatable("advancements.story.collect_ancient_mattock.title"), // The title
//                        Text.translatable("advancements.story.collect_ancient_mattock.description"), // The description
//                        null, // Background image used
//                        AdvancementFrame.TASK, // Options: TASK, CHALLENGE, GOAL
//                        true, // Show toast top right
//                        true, // Announce to chat
//                        true // Hidden in the advancement tab
//                )
//                .rewards(AdvancementRewards.Builder.experience(60))
//                // The first string used in criterion is the name referenced by other advancements when they want to have 'requirements'
//                .criterion("has_ancient_mattock", InventoryChangedCriterion.Conditions.items(NWItems.ANCIENT_MATTOCK))
//                .parent(new Identifier("story/iron_tools"))
//                .build(consumer, Newworld.MOD_ID + "/collect_ancient_mattock");
    }
}
