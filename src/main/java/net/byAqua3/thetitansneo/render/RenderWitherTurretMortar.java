package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.byAqua3.thetitansneo.entity.EntityWitherTurretMortar;
import net.byAqua3.thetitansneo.model.ModelWitherTurretMortar;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderWitherTurretMortar extends LivingEntityRenderer<EntityWitherTurretMortar, TitanRenderState, ModelWitherTurretMortar> {

	public static final Identifier WITHER = Identifier.withDefaultNamespace("textures/entity/wither/wither.png");

	public RenderWitherTurretMortar(Context context) {
		super(context, new ModelWitherTurretMortar(), 1.25F);
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(EntityWitherTurretMortar entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.titan = entity;
		state.invulTime = entity.getInvulTime();
	}

	@Override
	protected void scale(TitanRenderState state, PoseStack poseStack) {
		poseStack.scale(2.5F, 2.5F, 2.5F);
	}

	@Override
	protected boolean shouldShowName(EntityWitherTurretMortar entity, double distanceToCameraSq) {
		return false;
	}

	@Override
	public Identifier getTextureLocation(TitanRenderState state) {
		return WITHER;
	}
}
