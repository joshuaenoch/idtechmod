package com.idtech.entity;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
import com.idtech.BaseMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class MarshyModel<T extends MarshyEntity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(BaseMod.MODID, "marshy"), "main");
	private final ModelPart bone;

	public MarshyModel(ModelPart root) {
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -11.01F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(10, 14).addBox(-4.5F, -8.0F, -1.0F, 9.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-1.5F, -6.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(23, 0).addBox(-2.5F, -6.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(23, 6).addBox(1.5F, -6.0F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(11, 10).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(11, 10).addBox(0.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}