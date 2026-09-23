package net.byAqua3.thetitansneo.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;

public class ModelOptimaAxe extends EntityModel<LivingEntityRenderState> {

	public final ModelPart grip;
	public final ModelPart handle;
	public final ModelPart blade1;
	public final ModelPart blade2;

	public ModelOptimaAxe() {
		super(createBodyLayer().bakeRoot());
		ModelPart root = this.root;
		this.grip = root.getChild("grip");
		this.handle = root.getChild("grip").getChild("handle");
		this.blade1 = root.getChild("grip").getChild("handle").getChild("blade1");
		this.blade2 = root.getChild("grip").getChild("handle").getChild("blade2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		CubeDeformation cubeDeformation = new CubeDeformation(0.0F);
		partDefinition.addOrReplaceChild("grip", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -48.0F, -8.0F, 16, 48, 16, cubeDeformation), PartPose.offsetAndRotation(0.0F, 24.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		partDefinition.getChild("grip").addOrReplaceChild("handle", CubeListBuilder.create().texOffs(96, 0).addBox(-4.0F, -120.0F, -4.0F, 8, 120, 8, cubeDeformation), PartPose.offsetAndRotation(0.0F, -48.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		partDefinition.getChild("grip").getChild("handle").addOrReplaceChild("blade1", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, -32.0F, 4.0F, 0, 64, 48, cubeDeformation), PartPose.offsetAndRotation(0.0F, -116, 0.0F, 0.0F, 0.0F, 0.0F));
		partDefinition.getChild("grip").getChild("handle").addOrReplaceChild("blade2", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(0.0F, -32.0F, 4.0F, 0, 64, 48, cubeDeformation), PartPose.offsetAndRotation(0.0F, -116, 0.0F, 0.0F, 3.1415927F, 0.0F));
		return LayerDefinition.create(meshDefinition, 128, 128);
	}

	@Override
	public void setupAnim(LivingEntityRenderState state) {
	}
	// 26.1.2: Model.renderToBuffer 已被基类 final 化，改为渲染整棵 root。

}
