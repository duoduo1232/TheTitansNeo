package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.byAqua3.thetitansneo.entity.EntityWitherTurret;
import net.byAqua3.thetitansneo.model.ModelWitherTurret;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderWitherTurret extends LivingEntityRenderer<EntityWitherTurret, TitanRenderState, ModelWitherTurret> {

	public static final Identifier WITHER = Identifier.withDefaultNamespace("textures/entity/wither/wither.png");

	public RenderWitherTurret(Context context) {
		super(context, new ModelWitherTurret(), 1.0F);
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(EntityWitherTurret entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.titan = entity;
		state.invulTime = entity.getInvulTime();
	}

	@Override
	protected void scale(TitanRenderState state, PoseStack poseStack) {
		poseStack.scale(2.0F, 2.0F, 2.0F);
	}

	@Override
	protected boolean shouldShowName(EntityWitherTurret entity, double distanceToCameraSq) {
		return false;
	}

	@Override
	protected Identifier getTextureLocation(TitanRenderState state) {
		return WITHER;
	}
}
