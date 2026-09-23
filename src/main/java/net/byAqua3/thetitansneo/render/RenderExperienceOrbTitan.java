package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.byAqua3.thetitansneo.entity.titan.EntityExperienceOrbTitan;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.ExperienceOrbRenderState;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderExperienceOrbTitan extends EntityRenderer<EntityExperienceOrbTitan, ExperienceOrbRenderState> {

	public static final Identifier EXPERIENCE_ORB = Identifier.withDefaultNamespace("textures/entity/experience_orb.png");
	// 26.1.2: 贴图与 RenderType 在类加载期固定下来，submit 阶段只做几何提交。
	private static final RenderType RENDER_TYPE = RenderTypes.entityTranslucentCullItemTarget(EXPERIENCE_ORB);

	public RenderExperienceOrbTitan(Context context) {
		super(context);
		this.shadowRadius = 0.15F * 16.0F;
		this.shadowStrength = 0.75F;
	}

	@Override
	public ExperienceOrbRenderState createRenderState() {
		return new ExperienceOrbRenderState();
	}

	@Override
	public void extractRenderState(EntityExperienceOrbTitan entity, ExperienceOrbRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.icon = entity.getIcon();
	}

	private static void vertex(VertexConsumer vertexConsumer, PoseStack.Pose pose, float x, float y, int red, int green, int blue, float u, float v, int lightCoords) {
		vertexConsumer.addVertex(pose, x, y, 0.0F).setColor(red, green, blue, 128).setUv(u, v).setOverlay(OverlayTexture.NO_OVERLAY).setLight(lightCoords).setNormal(pose, 0.0F, 1.0F, 0.0F);
	}

	@Override
	public void submit(ExperienceOrbRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		poseStack.pushPose();
		int i = state.icon;
		float f = (float) (i % 4 * 16 + 0) / 64.0F;
		float f1 = (float) (i % 4 * 16 + 16) / 64.0F;
		float f2 = (float) (i / 4 * 16 + 0) / 64.0F;
		float f3 = (float) (i / 4 * 16 + 16) / 64.0F;
		float f8 = state.ageInTicks / 2.0F;
		int j = (int) ((Mth.sin(f8 + 0.0F) + 1.0F) * 0.5F * 255.0F);
		int l = (int) ((Mth.sin(f8 + (float) (Math.PI * 4.0 / 3.0)) + 1.0F) * 0.1F * 255.0F);
		poseStack.translate(0.0F, 2.0F, 0.0F);
		poseStack.mulPose(camera.orientation);
		poseStack.scale(8.0F, 8.0F, 8.0F);
		submitNodeCollector.submitCustomGeometry(poseStack, RENDER_TYPE, (pose, vertexConsumer) -> {
			vertex(vertexConsumer, pose, -0.5F, -0.25F, j, 255, l, f, f3, state.lightCoords);
			vertex(vertexConsumer, pose, 0.5F, -0.25F, j, 255, l, f1, f3, state.lightCoords);
			vertex(vertexConsumer, pose, 0.5F, 0.75F, j, 255, l, f1, f2, state.lightCoords);
			vertex(vertexConsumer, pose, -0.5F, 0.75F, j, 255, l, f, f2, state.lightCoords);
		});
		poseStack.popPose();
		super.submit(state, poseStack, submitNodeCollector, camera);
	}
}
