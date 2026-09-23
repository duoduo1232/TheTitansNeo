package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.titan.EntityGhastTitan;
import net.byAqua3.thetitansneo.model.ModelGhastTitan;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderGhastTitan extends LivingEntityRenderer<EntityGhastTitan, TitanRenderState, ModelGhastTitan> {

	public static final Identifier GHAST_TITAN = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/ghast_titan.png");
	public static final Identifier GHAST_TITAN_SHOOTING = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/ghast_titan_shooting.png");

	public RenderGhastTitan(Context context) {
		super(context, new ModelGhastTitan(), 4.0F);
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(EntityGhastTitan entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.titan = entity;
		state.isCharging = entity.isCharging();
		state.invulTime = entity.getInvulTime();
		state.extraPower = entity.getExtraPower();
		// 26.1.2: attackCounter 是动画插值量，这里在渲染状态里预插值。
		state.squishFactor = (entity.prevAttackCounter + (entity.attackCounter - entity.prevAttackCounter) * partialTicks) / 20.0F;
	}

	@Override
	protected void scale(TitanRenderState state, PoseStack poseStack) {
		float f1 = state.squishFactor;
		if (f1 < 0.0F) {
			f1 = 0.0F;
		}
		f1 = 1.0F / (f1 * f1 * f1 * f1 * f1 * 2.0F + 1.0F);
		float f2 = (8.0F + f1) / 2.0F;
		float f3 = (8.0F + 1.0F / f1) / 2.0F;
		poseStack.scale(f3, f2, f3);

		float f11 = 24.0F;
		int i = state.invulTime;
		if (i > 0)
			f11 -= (i - state.partialTick) / 440.0F * 7.75F;
		int i2 = state.extraPower;
		if (i2 > 0) {
			f1 += i2 * 0.5F;
		}
		poseStack.scale(f11, f11, f11);
		poseStack.translate(0.0F, 0.01F, 0.0F);
	}
	@Override
	public boolean shouldRender(EntityGhastTitan livingEntity, net.minecraft.client.renderer.culling.Frustum camera, double cameraX, double cameraY, double cameraZ) {
		return true;
	}

	@Override
	protected boolean shouldShowName(EntityGhastTitan entity, double distanceToCameraSq) {
		return false;
	}

	@Override
	protected Identifier getTextureLocation(TitanRenderState state) {
		return state.isCharging ? GHAST_TITAN_SHOOTING : GHAST_TITAN;
	}
}
