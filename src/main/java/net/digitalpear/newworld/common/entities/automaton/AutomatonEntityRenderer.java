package net.digitalpear.newworld.common.entities.automaton;

import net.digitalpear.newworld.Newworld;
import net.digitalpear.newworld.client.NewWorldClient;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class AutomatonEntityRenderer extends MobEntityRenderer<AutomatonEntity, AutomatonEntityModel> {

    public Identifier getTexture(String name){
        return Newworld.id("textures/entity/automaton/" + name + ".png");
    }

    public AutomatonEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new AutomatonEntityModel(context.getPart(NewWorldClient.AUTOMATON_MODEL_LAYER)), 0.4f);
    }


    @Override
    public Identifier getTexture(AutomatonEntity entity) {
        return entity.isRusted() ? getTexture("automaton_disabled") : getTexture("automaton");
    }
}
