package net.byAqua3.thetitansneo.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.byAqua3.thetitansneo.entity.titan.EntityMagmaCubeTitan;
import net.minecraft.client.model.EntityModel;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelMagmaCubeTitan extends EntityModel<TitanRenderState> {

	public ModelPart[] segments = new ModelPart[8];
	public ModelPart core;

	public ModelMagmaCubeTitan() {
		super(createBodyLayer().bakeRoot());
		ModelPart root = this.root;
		this.core = root.getChild("core");
		for (int i = 0; i < this.segments.length; i++) {
			this.segments[i] = root.getChild("segment" + i);
		}
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshDefinition = new MeshDefinition();
		PartDefinition partDefinition = meshDefinition.getRoot();
		CubeDeformation cubeDeformation = new CubeDeformation(0.0F);
		partDefinition.addOrReplaceChild("core", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 18.0F, -2.0F, 4, 4, 4, cubeDeformation), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		for (int i = 0; i < 8; i++) {
			byte b0 = 0;
			int j = i;
			if (i == 2) {
				b0 = 24;
				j = 10;
			} else if (i == 3) {
				b0 = 24;
				j = 19;
			}
			partDefinition.addOrReplaceChild("segment" + i, CubeListBuilder.create().texOffs(b0, j).addBox(-4.0F, (16 + i), -4.0F, 8, 1, 8, cubeDeformation), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));
		}
		return LayerDefinition.create(meshDefinition, 64, 32);
	}

	@Override
	public void setupAnim(TitanRenderState state) {
		float f3 = state.prevSquishFactor + (state.squishFactor - state.prevSquishFactor) * state.ageInTicks;
		if (f3 < 0.0F) {
			f3 = 0.0F;
		}
		for (int i = 0; i < this.segments.length; i++) {
			this.segments[i].y = -(4 - i) * f3 * 1.7F;
		}
	}
	// 26.1.2: Model.renderToBuffer 已被基类 final 化，改为渲染整棵 root。

}
