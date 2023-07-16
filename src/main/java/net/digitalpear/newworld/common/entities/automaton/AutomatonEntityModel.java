package net.digitalpear.newworld.common.entities.automaton;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class AutomatonEntityModel extends EntityModel<AutomatonEntity> {
    private final ModelPart origin;
    private final ModelPart LowerTorso;
    private final ModelPart Torso;
    private final ModelPart Head;
    private final ModelPart RightArm;
    private final ModelPart LeftArm;
    private final ModelPart RightLeg;
    private final ModelPart LeftLeg;

    public AutomatonEntityModel(ModelPart root) {
        this.origin = root.getChild("origin");
        this.LowerTorso = origin.getChild("LowerTorso");
        this.Torso = LowerTorso.getChild("Torso");
        this.Head = Torso.getChild("Head");
        this.RightArm = Torso.getChild("RightArm");
        this.LeftArm = Torso.getChild("LeftArm");
        this.RightLeg = origin.getChild("RightLeg");
        this.LeftLeg = origin.getChild("LeftLeg");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData origin = modelPartData.addChild("origin", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 11.0F, 0.0F));

        ModelPartData LowerTorso = origin.addChild("LowerTorso", ModelPartBuilder.create().uv(25, 21).cuboid(-3.0F, -4.0F, -1.5F, 6.0F, 6.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Torso = LowerTorso.addChild("Torso", ModelPartBuilder.create().uv(0, 17).cuboid(-4.0F, -7.0F, -2.0F, 8.0F, 7.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -4.0F, 0.0F));

        ModelPartData Head = Torso.addChild("Head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(2.0F, -13.0F, 1.0F, 1.0F, 5.0F, 0.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -7.0F, 0.0F));

        ModelPartData RightArm = Torso.addChild("RightArm", ModelPartBuilder.create().uv(44, 19).cuboid(-4.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.0F))
                .uv(33, 0).cuboid(-3.0F, 3.0F, -2.0F, 3.0F, 8.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, -6.0F, 0.0F));

        ModelPartData LeftArm = Torso.addChild("LeftArm", ModelPartBuilder.create().uv(33, 0).mirrored().cuboid(0.0F, 3.0F, -2.0F, 3.0F, 8.0F, 4.0F, new Dilation(0.0F)).mirrored(false)
                .uv(44, 19).mirrored().cuboid(0.0F, -2.0F, -2.0F, 4.0F, 5.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(4.0F, -6.0F, 0.0F));

        ModelPartData RightLeg = origin.addChild("RightLeg", ModelPartBuilder.create().uv(48, 0).cuboid(-2.1F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.9F, 1.0F, 0.0F));

        ModelPartData LeftLeg = origin.addChild("LeftLeg", ModelPartBuilder.create().uv(48, 0).mirrored().cuboid(-1.9F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(1.9F, 1.0F, 0.0F));
        return TexturedModelData.of(modelData, 64, 64);
    }

    @Override
    public void setAngles(AutomatonEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
        this.Head.setAngles(headPitch * ((float)Math.PI / 180), headYaw * ((float)Math.PI / 180), 0);

        float intensity = 0.3f;
        this.RightLeg.pitch = MathHelper.cos(limbSwing * intensity) * limbSwingAmount;
        this.LeftLeg.pitch = MathHelper.cos(limbSwing * intensity + (float)Math.PI + 0.3f) * limbSwingAmount;
    }
    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
        origin.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
    }
}