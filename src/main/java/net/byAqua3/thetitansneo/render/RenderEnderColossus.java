package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.titan.EntityEnderColossus;
import net.byAqua3.thetitansneo.model.ModelEnderColossus;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderEnderColossus extends LivingEntityRenderer<EntityEnderColossus, TitanRenderState, ModelEnderColossus> {

	public static final Identifier ENDER_COLOSSUS = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/ender_colossus.png");
	public static final Identifier ENDER_COLOSSUS_DEAD = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/ender_colossus_dead.png");
	public static final Identifier ENDER_COLOSSUS_EYES = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/ender_colossus_eyes.png");
	public static final Identifier ENDER_COLOSSUS_EYES_DEAD = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/ender_colossus_eyes_dead.png");
	public static final Identifier ENDER_COLOSSUS_EYES_BEAM = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/ender_colossus_beam.png");

	public RenderEnderColossus(Context context) {
		super(context, new ModelEnderColossus(), 0.5F);
		this.addLayer(new net.byAqua3.thetitansneo.render.layer.LayerEnderColossusEyes(this));
		this.addLayer(new net.byAqua3.thetitansneo.render.layer.LayerEnderColossusEyesLaser(this));
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(EntityEnderColossus entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.titan = entity;
		state.invulTime = entity.getInvulTime();
		state.extraPower = entity.getExtraPower();
		state.animationID = entity.getAnimationID();
		state.deathTicks = entity.deathTicks;
		state.isStunned = entity.isStunned;
		state.isScreaming = entity.isScreaming();
		state.eyeLaserTime = entity.getEyeLaserTime();
		// 26.1.2: 眼睛光柱需要世界时间与实体插值位置/朝向，这里一并捕获。
		state.worldTicks = (int) entity.level().getOverworldClockTime();
		state.entityX = entity.getX();
		state.entityY = entity.getY();
		state.entityZ = entity.getZ();
		state.prevEntityX = entity.xOld;
		state.prevEntityY = entity.yOld;
		state.prevEntityZ = entity.zOld;
		state.lookVector = entity.getViewVector(1.0F);
		state.isAlive = entity.isAlive();
	}

	@Override
	protected boolean shouldRenderLayers(TitanRenderState state) {
		this.getModel().isAttacking = state.isScreaming;
		return true;
	}

	@Override
	protected void scale(TitanRenderState state, PoseStack poseStack) {
		float f1 = 24.0F;
		int i = state.invulTime;
		if (i > 0) {
			f1 -= (i - state.partialTick) / 440.0F * 7.75F;
		}
		int i2 = state.extraPower;
		if (i2 > 0) {
			f1 += i2 * 0.5F;
		}
		poseStack.scale(f1, f1, f1);
		poseStack.translate(0.0F, 0.015F, 0.0F);
		if (state.isPassenger) {
			poseStack.translate(0.0F, 0.1F, 0.0F);
			poseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(180.0F));
		}
	}

	@Override
	protected boolean shouldShowName(EntityEnderColossus entity, double distanceToCameraSq) {
		return false;
	}

	@Override
	protected Identifier getTextureLocation(TitanRenderState state) {
		return (state.animationID == 10 && state.deathTicks > 200) ? ENDER_COLOSSUS_DEAD : ENDER_COLOSSUS;
	}
}
