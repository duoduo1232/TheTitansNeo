package net.byAqua3.thetitansneo.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.model.animal.golem.SnowGolemModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerSnowGolemTitanHead extends RenderLayer<TitanRenderState, SnowGolemModel> {

	public LayerSnowGolemTitanHead(RenderLayerParent<TitanRenderState, SnowGolemModel> renderer) {
		super(renderer);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, TitanRenderState state, float yRot, float xRot) {
		// 26.1.2: 方块模型在 extractRenderState 中已解析好，这里只负责摆姿势并提交。
		if (state.pumpkinBlock.isEmpty()) {
			return;
		}
		boolean flag = state.appearsGlowing() && state.isInvisible;
		if (state.isInvisible && !flag) {
			return;
		}
		poseStack.pushPose();

		this.getParentModel().getHead().translateAndRotate(poseStack);
		poseStack.translate(0.0F, -0.34375F, 0.0F);
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
		poseStack.scale(0.625F, -0.625F, -0.625F);
		poseStack.translate(-0.5F, -0.5F, -0.5F);

		state.pumpkinBlock.submit(poseStack, submitNodeCollector, lightCoords, OverlayTexture.NO_OVERLAY, state.outlineColor);

		poseStack.popPose();
	}
}
