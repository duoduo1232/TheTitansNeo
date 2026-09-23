package net.byAqua3.thetitansneo.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;

import net.byAqua3.thetitansneo.model.ModelEnderColossus;
import net.byAqua3.thetitansneo.render.RenderEnderColossus;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerEnderColossusEyesLaser extends RenderLayer<TitanRenderState, ModelEnderColossus> {

	public LayerEnderColossusEyesLaser(RenderLayerParent<TitanRenderState, ModelEnderColossus> renderer) {
		super(renderer);
	}

	// 26.1.2: 实体位置/朝向不再从实体读取，改由 RenderState 捕获。
	private Vec3 getEntityInterpolatedPosition(TitanRenderState state, double y) {
		double d1 = state.prevEntityX + (state.entityX - state.prevEntityX) * state.partialTick;
		double d2 = y + state.prevEntityY + (state.entityY - state.prevEntityY) * state.partialTick;
		double d3 = state.prevEntityZ + (state.entityZ - state.prevEntityZ) * state.partialTick;
		return new Vec3(d1, d2, d3);
	}

	private Vec3 getEntityLookAheadPosition(TitanRenderState state, double y) {
		Vec3 vec3 = state.lookVector;
		double dx = vec3.x * 300.0;
		double dy = vec3.y * 300.0;
		double dz = vec3.z * 300.0;
		double d1 = state.prevEntityX + (state.entityX + dx - state.prevEntityX) * state.partialTick;
		double d2 = y + state.prevEntityY + (state.entityY + dy - state.prevEntityY) * state.partialTick;
		double d3 = state.prevEntityZ + (state.entityZ + dz - state.prevEntityZ) * state.partialTick;
		return new Vec3(d1, d2, d3);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, TitanRenderState state, float yRot, float xRot) {
		if (state.isAlive && state.eyeLaserTime >= 0) {
			poseStack.pushPose();
			float f4 = state.worldTicks + state.partialTick;
			float f5 = f4 * 0.5F % 1.0F;
			float f6 = state.eyeHeight;
			poseStack.pushPose();
			this.getParentModel().bodyBottom.translateAndRotate(poseStack);

			poseStack.pushPose();
			this.getParentModel().bodyMiddle.translateAndRotate(poseStack);
			this.getParentModel().bodyTop.translateAndRotate(poseStack);
			this.getParentModel().mouth.translateAndRotate(poseStack);
			this.getParentModel().head.translateAndRotate(poseStack);
			poseStack.translate(0.0F, -0.22F, -0.25F);

			Vec3 vec3 = this.getEntityLookAheadPosition(state, f6);
			Vec3 vec31 = this.getEntityInterpolatedPosition(state, f6);
			Vec3 vec32 = vec3.subtract(vec31);
			double d3 = vec32.length() + 0.1;
			double d4 = f4 * 0.005 * -1.5;
			double d15 = Math.cos(d4 * 0.0 + Math.PI) * 0.25;
			double d16 = Math.sin(d4 * 0.0 + Math.PI) * 0.25;
			double d17 = Math.cos(d4 * 0.0 + 0.0) * 0.25;
			double d18 = Math.sin(d4 * 0.0 + 0.0) * 0.25;
			double d25 = (-1.0F + f5);
			double d26 = d3 * 2.5 + d25;

			final double fd3 = d3, fd15 = d15, fd16 = d16, fd17 = d17, fd18 = d18, fd25 = d25, fd26 = d26;
			// 26.1.2: 原先直接写 VertexConsumer，改用 submitCustomGeometry 提交自定义几何。
			submitNodeCollector.submitCustomGeometry(poseStack, RenderTypes.energySwirl(RenderEnderColossus.ENDER_COLOSSUS_EYES_BEAM, 0.0F, 0.0F), (pose, vertexConsumer) -> {
				vertexConsumer.addVertex(pose, (float) fd15, (float) fd16, (float) -fd3).setColor(255, 0, 255, 255).setUv(0.5F, (float) fd26).setOverlay(OverlayTexture.NO_OVERLAY).setLight(lightCoords).setNormal(pose, 0.0F, 0.0F, 0.0F);
				vertexConsumer.addVertex(pose, (float) fd15, (float) fd16, 0.0F).setColor(255, 0, 255, 255).setUv(0.5F, (float) fd25).setOverlay(OverlayTexture.NO_OVERLAY).setLight(lightCoords).setNormal(pose, 0.0F, 0.0F, 0.0F);
				vertexConsumer.addVertex(pose, (float) fd17, (float) fd18, 0.0F).setColor(255, 0, 255, 255).setUv(0.0F, (float) fd25).setOverlay(OverlayTexture.NO_OVERLAY).setLight(lightCoords).setNormal(pose, 0.0F, 0.0F, 0.0F);
				vertexConsumer.addVertex(pose, (float) fd17, (float) fd18, (float) -fd3).setColor(255, 0, 255, 255).setUv(0.0F, (float) fd26).setOverlay(OverlayTexture.NO_OVERLAY).setLight(lightCoords).setNormal(pose, 0.0F, 0.0F, 0.0F);
			});

			poseStack.popPose();

			poseStack.popPose();
			poseStack.popPose();
		}
	}
}
