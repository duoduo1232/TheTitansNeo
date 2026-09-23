package net.byAqua3.thetitansneo.model;

import org.joml.Quaternionf;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class ModelEnderColossusCrystal extends EntityModel<LivingEntityRenderState> {

	private static final float SIN_45 = (float) Math.sin(Math.PI / 4);

	private final ModelPart cube;
	private final ModelPart glass;
	/** 26.1.2: 摆动参数在 setupAnim 里从 RenderState 写入，renderToBuffer 只负责绘制。 */
	private float yRotDegrees;
	private float yOffset;

	public ModelEnderColossusCrystal() {
		super(createBodyLayer().bakeRoot());
		ModelPart root = this.root;
		this.cube = root.getChild("cube");
		this.glass = root.getChild("glass");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		partdefinition.addOrReplaceChild("glass", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
		partdefinition.addOrReplaceChild("cube", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 8.0F, 8.0F), PartPose.ZERO);
		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	/** 26.1.2: 由渲染器在 extract 之后设置（原实现在 render 里直接读实体）。 */
	public void setAnimation(float yRotDegrees, float yOffset) {
		this.yRotDegrees = yRotDegrees;
		this.yOffset = yOffset;
	}

	@Override
	public void setupAnim(LivingEntityRenderState state) {

	}
	// 26.1.2: Model.renderToBuffer 已被基类 final 化，改为渲染整棵 root。

}
