package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.byAqua3.thetitansneo.entity.EntityWitherTurretGround;
import net.byAqua3.thetitansneo.model.ModelWitherTurretGround;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderWitherTurretGround extends LivingEntityRenderer<EntityWitherTurretGround, TitanRenderState, ModelWitherTurretGround> {

	public static final Identifier WITHER = Identifier.withDefaultNamespace("textures/entity/wither/wither.png");

	public RenderWitherTurretGround(Context context) {
		super(context, new ModelWitherTurretGround(), 1.0F);
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(EntityWitherTurretGround entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.titan = entity;
		state.invulTime = entity.getInvulTime();
	}

	@Override
	protected void scale(TitanRenderState state, PoseStack poseStack) {
		poseStack.scale(2.0F, 2.0F, 2.0F);
	}

	@Override
	protected boolean shouldShowName(EntityWitherTurretGround entity, double distanceToCameraSq) {
		return false;
	}

	@Override
	public Identifier getTextureLocation(TitanRenderState state) {
		return WITHER;
	}
}
