package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.titan.EntityMagmaCubeTitan;
import net.byAqua3.thetitansneo.model.ModelMagmaCubeTitan;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;import net.minecraft.util.Mth;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderMagmaCubeTitan extends LivingEntityRenderer<EntityMagmaCubeTitan, TitanRenderState, ModelMagmaCubeTitan> {

	public static final Identifier MAGMACUBE_TITAN = Identifier.withDefaultNamespace("textures/entity/slime/magmacube.png");

	public RenderMagmaCubeTitan(Context context) {
		super(context, new ModelMagmaCubeTitan(), 0.25F);
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(EntityMagmaCubeTitan entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.titan = entity;
		state.invulTime = entity.getInvulTime();
		state.deathTicks = entity.deathTicks;
		state.slimeSize = entity.getSlimeSize();
		state.squishFactor = Mth.lerp(partialTicks, entity.prevSquishFactor, entity.squishFactor);
	}

	@Override
	protected void scale(TitanRenderState state, PoseStack poseStack) {
		float f1 = state.slimeSize;
		float f2 = state.squishFactor / (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		poseStack.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
		float fl = 16.0F;
		int i = state.invulTime;
		if (i > 0) {
			fl -= (i - state.partialTick) / 10.0F;
		}
		poseStack.scale(fl, fl, fl);
		poseStack.translate(0.0F, 0.0075F, 0.0F);
	}
	@Override
	protected float getShadowRadius(TitanRenderState state) {
		return this.shadowRadius * state.boundingBoxWidth;
	}

	@Override
	protected void setupRotations(TitanRenderState state, PoseStack poseStack, float bodyRot, float entityScale) {
		super.setupRotations(state, poseStack, bodyRot, entityScale);
		if (state.deathTicks > 0) {
			float f = (state.deathTicks + state.partialTick - 1.0F) / 20.0F * 1.6F;
			f = (float) Math.sqrt(f);
			if (f > 1.0F) {
				f = 1.0F;
			}
			poseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(f * this.getFlipDegrees()));
		}
	}

	@Override
	protected boolean shouldShowName(EntityMagmaCubeTitan entity, double distanceToCameraSq) {
		return false;
	}

	@Override
	protected Identifier getTextureLocation(TitanRenderState state) {
		return MAGMACUBE_TITAN;
	}
}
