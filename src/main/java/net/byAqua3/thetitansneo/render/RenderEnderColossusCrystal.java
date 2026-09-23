package net.byAqua3.thetitansneo.render;

import java.awt.Color;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.titan.EntityEnderColossusCrystal;
import net.byAqua3.thetitansneo.model.ModelEnderColossusCrystal;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderEnderColossusCrystal extends EntityRenderer<EntityEnderColossusCrystal, TitanRenderState> {

	public static final Identifier ENDER_COLOSSUS_CRYSTAL = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/crystal.png");
	public static final Identifier ENDER_COLOSSUS_CRYSTAL_BEAM = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/endercrystal_beam.png");

	// 26.1.2: 贴图固定，RenderType 在类加载期构建。
	private static final RenderType CRYSTAL_RENDER_TYPE = RenderTypes.entityCutout(ENDER_COLOSSUS_CRYSTAL);
	private static final RenderType BEAM_RENDER_TYPE = RenderTypes.entityTranslucent(ENDER_COLOSSUS_CRYSTAL_BEAM);
	private static final int CRYSTAL_COLOR = new Color(1.0F, 1.0F, 1.0F, 1.0F).getRGB();

	private final ModelEnderColossusCrystal model = new ModelEnderColossusCrystal();

	public RenderEnderColossusCrystal(Context context) {
		super(context);
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(EntityEnderColossusCrystal entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.titan = entity;
		// 26.1.2: 原先在 render 里直接读实体，现在把水晶自转与光束锚点拷进 RenderState。
		state.animationID = entity.innerRotation;
		state.titanHealth = entity.innerRotation + partialTicks;
		state.isAlive = entity.owner != null;
		if (entity.owner != null) {
			state.entityX = entity.owner.getX();
			state.entityY = entity.owner.getY() + 48.0F;
			state.entityZ = entity.owner.getZ();
			state.prevEntityX = entity.getX() + 0.5D;
			state.prevEntityY = entity.getY() + 0.5D;
			state.prevEntityZ = entity.getZ() + 0.5D;
		}
	}

	public static void renderCrystalBeams(float x, float y, float z, float partialTicks, int tickCount, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight) {
		float f = Mth.sqrt(x * x + z * z);
		float f1 = Mth.sqrt(x * x + y * y + z * z);
		poseStack.pushPose();
		poseStack.translate(0.0F, 2.0F, 0.0F);
		poseStack.mulPose(Axis.YP.rotation((float) (-Math.atan2((double) z, (double) x)) - (float) (Math.PI / 2)));
		poseStack.mulPose(Axis.XP.rotation((float) (-Math.atan2((double) f, (double) y)) - (float) (Math.PI / 2)));
		float f2 = 0.0F - ((float) tickCount + partialTicks) * 0.01F;
		float f3 = Mth.sqrt(x * x + y * y + z * z) / 32.0F - ((float) tickCount + partialTicks) * 0.01F;
		int i = 64;

		submitNodeCollector.submitCustomGeometry(poseStack, BEAM_RENDER_TYPE, (poseStack$pose, vertexconsumer) -> {
			float f4 = 0.0F;
			float f5 = 0.75F;
			float f6 = 0.0F;

			for (int j = 1; j <= i; j++) {
				float f7 = Mth.sin((float) j * (float) (Math.PI * 2) / i) * 0.75F;
				float f8 = Mth.cos((float) j * (float) (Math.PI * 2) / i) * 0.75F;
				float f9 = (float) j / i;
				vertexconsumer.addVertex(poseStack$pose, f4 * 0.2F, f5 * 0.2F, 0.0F).setColor(-16777216).setUv(f6, f2).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(poseStack$pose, 0.0F, -1.0F, 0.0F);
				vertexconsumer.addVertex(poseStack$pose, f4, f5, f1).setColor(-1).setUv(f6, f3).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(poseStack$pose, 0.0F, -1.0F, 0.0F);
				vertexconsumer.addVertex(poseStack$pose, f7, f8, f1).setColor(-1).setUv(f9, f3).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(poseStack$pose, 0.0F, -1.0F, 0.0F);
				vertexconsumer.addVertex(poseStack$pose, f7 * 0.2F, f8 * 0.2F, 0.0F).setColor(-16777216).setUv(f9, f2).setOverlay(OverlayTexture.NO_OVERLAY).setLight(packedLight).setNormal(poseStack$pose, 0.0F, -1.0F, 0.0F);
				f4 = f7;
				f5 = f8;
				f6 = f9;
			}
		});
		poseStack.popPose();
	}

	@Override
	public void submit(TitanRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
		poseStack.pushPose();
		float f1 = state.titanHealth;
		float f2 = Mth.sin(f1 * 0.2F) / 2.0F + 0.5F;
		f2 = (f2 * f2 + f2) * 0.4F - 1.4F;
		// 26.1.2: 模型动画参数改由 setAnimation 注入，模型本身不再接收实体。
		this.model.setAnimation(f1 * 3.0F, f2 * 0.2F);
		submitNodeCollector.submitModel(this.model, state, poseStack, CRYSTAL_RENDER_TYPE, state.lightCoords, OverlayTexture.NO_OVERLAY, CRYSTAL_COLOR, null, state.outlineColor, null);
		if (state.isAlive) {
			float f4 = (float) state.entityX;
			float f5 = (float) state.entityY;
			float f6 = (float) state.entityZ;
			float f7 = (float) ((double) f4 - state.prevEntityX + 0.5F);
			float f8 = (float) ((double) f5 - state.prevEntityY + 0.5F);
			float f9 = (float) ((double) f6 - state.prevEntityZ + 0.5F);
			poseStack.translate(f7, f8, f9);
			renderCrystalBeams(-f7, -f8 + f2, -f9, state.partialTick, state.animationID, poseStack, submitNodeCollector, state.lightCoords);
		}
		poseStack.popPose();
		super.submit(state, poseStack, submitNodeCollector, camera);
	}
}
