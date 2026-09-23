package net.byAqua3.thetitansneo.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.byAqua3.thetitansneo.entity.titan.EntitySlimeTitan;
import net.minecraft.client.model.EntityModel;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class ModelSlimeTitan extends EntityModel<TitanRenderState> {

	public ModelPart slimeBodies;
	public ModelPart slimeLeftEye;
	public ModelPart slimeRightEye;
	public ModelPart slimeMouth;

	public ModelSlimeTitan(int y) {
		super(createBodyLayer(y).bakeRoot());
		ModelPart root = this.root;
		this.slimeBodies = root.getChild("slimeBodies");
		if (y > 0) {
			this.slimeLeftEye = root.getChild("slimeLeftEye");
			this.slimeRightEye = root.getChild("slimeRightEye");
			this.slimeMouth = root.getChild("slimeMouth");
		}
	}

	public static LayerDefinition createBodyLayer(int y) {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		CubeDeformation cubeDeformation = new CubeDeformation(0.0F);
		partDefinition.addOrReplaceChild("slimeBodies", CubeListBuilder.create().texOffs(0, y).addBox(-4.0F, 16.0F, -4.0F, 8, 8, 8, cubeDeformation), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		if (y > 0) {
			partDefinition.addOrReplaceChild("slimeBodies", CubeListBuilder.create().texOffs(0, y).addBox(-3.0F, 17.0F, -3.0F, 6, 6, 6, cubeDeformation), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
			partDefinition.addOrReplaceChild("slimeLeftEye", CubeListBuilder.create().texOffs(32, 0).addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, cubeDeformation), PartPose.offsetAndRotation(-2.25F, 19.0F, -2.5F, 0.0F, 0.0F, 0.0F));
			partDefinition.addOrReplaceChild("slimeRightEye", CubeListBuilder.create().texOffs(32, 4).addBox(-1.0F, -1.0F, -1.0F, 2, 2, 2, cubeDeformation), PartPose.offsetAndRotation(2.25F, 19.0F, -2.5F, 0.0F, 0.0F, 0.0F));
			partDefinition.addOrReplaceChild("slimeMouth", CubeListBuilder.create().texOffs(32, 8).addBox(0.0F, 21.0F, -3.5F, 1, 1, 1, cubeDeformation), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		}
		return LayerDefinition.create(meshDefinition, 64, 32);
	}

	@Override
	public void setupAnim(TitanRenderState state) {
		if (this.slimeLeftEye != null) {
			this.slimeLeftEye.yRot = state.yRot * Mth.PI / 180.0F;
			this.slimeLeftEye.xRot = state.xRot * Mth.PI / 180.0F;
		}
		if (this.slimeRightEye != null) {
			this.slimeRightEye.yRot = state.yRot * Mth.PI / 180.0F;
			this.slimeRightEye.xRot = state.xRot * Mth.PI / 180.0F;
		}
	}
	// 26.1.2: Model.renderToBuffer 已被基类 final 化，改为渲染整棵 root。

}
