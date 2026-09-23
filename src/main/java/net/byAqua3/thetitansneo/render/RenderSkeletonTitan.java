package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.titan.EntitySkeletonTitan;
import net.byAqua3.thetitansneo.model.ModelSkeletonTitan;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderSkeletonTitan extends LivingEntityRenderer<EntitySkeletonTitan, TitanRenderState, ModelSkeletonTitan> {

	public static final Identifier SKELETON_TITAN = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/skeleton_titan.png");
	public static final Identifier SKELETON_TITAN_PULL_0 = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/skeleton_titan_pulling_0.png");
	public static final Identifier SKELETON_TITAN_PULL_1 = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/skeleton_titan_pulling_1.png");
	public static final Identifier SKELETON_TITAN_PULL_2 = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/skeleton_titan_pulling_2.png");
	public static final Identifier SKELETON_TITAN_BROKEN_BOW = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/skeleton_titan_broken_bow.png");

	public static final Identifier WITHER_SKELETON_TITAN = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/wither_skeleton_titan.png");

	public RenderSkeletonTitan(Context context) {
		super(context, new ModelSkeletonTitan(0.0F), 0.5F);
		this.addLayer(new net.byAqua3.thetitansneo.render.layer.LayerSkeletonTitanItemInHand(this));
		this.addLayer(new net.byAqua3.thetitansneo.render.layer.LayerSkeletonTitanArmor(this));
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(EntitySkeletonTitan entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.titan = entity;
		state.invulTime = entity.getInvulTime();
		state.extraPower = entity.getExtraPower();
		state.skeletonType = entity.getSkeletonType();
		state.attackTimer = entity.attackTimer;
		state.isStunned = entity.isStunned;
	}

	@Override
	protected void scale(TitanRenderState state, PoseStack poseStack) {
		float f1 = (state.skeletonType == 1) ? 28.0F : 16.0F;
		int i = state.invulTime;
		if (i > 0) {
			f1 -= (i - state.partialTick) / 440.0F * 7.75F;
		}
		int i2 = state.extraPower;
		if (i2 > 0) {
			f1 += i2 * 0.5F;
		}
		poseStack.scale(f1, f1, f1);
		poseStack.translate(0.0F, 0.0075F, 0.0F);
	}
	@Override
	protected boolean shouldRenderLayers(TitanRenderState state) {
		this.getModel().isWither = state.skeletonType == 1;
		return true;
	}

	@Override
	protected boolean shouldShowName(EntitySkeletonTitan entity, double distanceToCameraSq) {
		return false;
	}

	@Override
	public Identifier getTextureLocation(TitanRenderState state) {
		return (state.skeletonType == 1) ? WITHER_SKELETON_TITAN : ((state.attackTimer < 20 && state.attackTimer >= 10) ? SKELETON_TITAN_PULL_0 : ((state.attackTimer < 30 && state.attackTimer >= 20) ? SKELETON_TITAN_PULL_1 : ((state.attackTimer >= 30) ? SKELETON_TITAN_PULL_2 : (state.isStunned ? SKELETON_TITAN_BROKEN_BOW : SKELETON_TITAN))));
	}
}
