package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.titan.EntitySlimeTitan;
import net.byAqua3.thetitansneo.model.ModelSlimeTitan;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;import net.minecraft.util.Mth;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderSlimeTitan extends LivingEntityRenderer<EntitySlimeTitan, TitanRenderState, ModelSlimeTitan> {

	public static final Identifier SLIME_TITAN = Identifier.withDefaultNamespace("textures/entity/slime/slime.png");

	public RenderSlimeTitan(Context context) {
		super(context, new ModelSlimeTitan(16), 0.25F);
		this.addLayer(new net.byAqua3.thetitansneo.render.layer.LayerSlimeTitanOuter(this));
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(EntitySlimeTitan entity, TitanRenderState state, float partialTicks) {
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
		// 原实现：this.shadowRadius = 0.25F * entity.getSlimeSize()
		return 0.25F * state.slimeSize;
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
			poseStack.scale(1.0F + f * 2.0F, 1.0F - f * 0.99F, 1.0F + f * 2.0F);
		}
	}

	@Override
	protected boolean shouldShowName(EntitySlimeTitan entity, double distanceToCameraSq) {
		return false;
	}

	@Override
	protected Identifier getTextureLocation(TitanRenderState state) {
		return SLIME_TITAN;
	}
}
