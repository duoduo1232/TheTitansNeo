package net.byAqua3.thetitansneo.render.projectile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.projectile.EntityHarcadiumArrow;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;

public class RenderHarcadiumArrow extends EntityRenderer<EntityHarcadiumArrow, ArrowRenderState> {

	public static final Identifier HARCADIUM_ARROW = Identifier.tryBuild(TheTitansNeo.MODID ,"textures/entity/harcadium_arrow_entity.png");
	// 26.1.2: 贴图固定，RenderType 在类加载期构建。
	private static final RenderType RENDER_TYPE = RenderTypes.entityCutout(HARCADIUM_ARROW);

	public RenderHarcadiumArrow(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public ArrowRenderState createRenderState() {
		return new ArrowRenderState();
	}

	@Override
	public void extractRenderState(EntityHarcadiumArrow entity, ArrowRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.yRot = Mth.lerp(partialTicks, entity.yRotO, entity.getYRot());
		state.xRot = Mth.lerp(partialTicks, entity.xRotO, entity.getXRot());
		state.shake = (float) entity.shakeTime - partialTicks;
	}

	public void vertex(PoseStack.Pose pPose, VertexConsumer pConsumer, int pX, int pY, int pZ, float pU, float pV, int normalX, int normalY, int normalZ, int packedLight) {
		pConsumer.addVertex(pPose, (float) pX, (float) pY, (float) pZ).setColor(-1).setUv(pU, pV).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(pPose, (float) normalX, (float) normalZ, (float) normalY);
	}

	@Override
	public void submit(ArrowRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		poseStack.pushPose();

		poseStack.mulPose(Axis.YP.rotationDegrees(state.yRot - 90.0F));
		poseStack.mulPose(Axis.ZP.rotationDegrees(state.xRot));
		if (state.shake > 0.0F) {
			float f2 = -Mth.sin(state.shake * 3.0F) * state.shake;
			poseStack.mulPose(Axis.ZP.rotationDegrees(f2));
		}

		poseStack.mulPose(Axis.XP.rotationDegrees(45.0F));
		poseStack.scale(0.05625F, 0.05625F, 0.05625F);
		poseStack.translate(-4.0F, 0.0F, 0.0F);
		submitNodeCollector.submitCustomGeometry(poseStack, RENDER_TYPE, (pose, vertexconsumer) -> {
			this.vertex(pose, vertexconsumer, -7, -2, -2, 0.0F, 0.15625F, -1, 0, 0, state.lightCoords);
			this.vertex(pose, vertexconsumer, -7, -2, 2, 0.15625F, 0.15625F, -1, 0, 0, state.lightCoords);
			this.vertex(pose, vertexconsumer, -7, 2, 2, 0.15625F, 0.3125F, -1, 0, 0, state.lightCoords);
			this.vertex(pose, vertexconsumer, -7, 2, -2, 0.0F, 0.3125F, -1, 0, 0, state.lightCoords);
			this.vertex(pose, vertexconsumer, -7, 2, -2, 0.0F, 0.15625F, 1, 0, 0, state.lightCoords);
			this.vertex(pose, vertexconsumer, -7, 2, 2, 0.15625F, 0.15625F, 1, 0, 0, state.lightCoords);
			this.vertex(pose, vertexconsumer, -7, -2, 2, 0.15625F, 0.3125F, 1, 0, 0, state.lightCoords);
			this.vertex(pose, vertexconsumer, -7, -2, -2, 0.0F, 0.3125F, 1, 0, 0, state.lightCoords);
		});

		for (int j = 0; j < 4; j++) {
			poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
			submitNodeCollector.submitCustomGeometry(poseStack, RENDER_TYPE, (pose, vertexconsumer) -> {
				this.vertex(pose, vertexconsumer, -8, -2, 0, 0.0F, 0.0F, 0, 1, 0, state.lightCoords);
				this.vertex(pose, vertexconsumer, 8, -2, 0, 0.5F, 0.0F, 0, 1, 0, state.lightCoords);
				this.vertex(pose, vertexconsumer, 8, 2, 0, 0.5F, 0.15625F, 0, 1, 0, state.lightCoords);
				this.vertex(pose, vertexconsumer, -8, 2, 0, 0.0F, 0.15625F, 0, 1, 0, state.lightCoords);
			});
		}

		poseStack.popPose();
		super.submit(state, poseStack, submitNodeCollector, camera);
	}
}
