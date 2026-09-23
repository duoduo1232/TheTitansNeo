package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.titan.EntitySpiderTitan;
import net.byAqua3.thetitansneo.model.ModelSpiderTitan;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderSpiderTitan extends LivingEntityRenderer<EntitySpiderTitan, TitanRenderState, ModelSpiderTitan> {

	public static final Identifier SPIDER_TITAN = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/spider_titan.png");
	public static final Identifier SPIDER_TITAN_EYE = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/spider_titan_eyes.png");

	public RenderSpiderTitan(Context context) {
		super(context, new ModelSpiderTitan(0.0F), 1.0F);
		this.addLayer(new net.byAqua3.thetitansneo.render.layer.LayerSpiderTitanEyes(this));
		this.addLayer(new net.byAqua3.thetitansneo.render.layer.LayerSpiderTitanArmor(this));
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(EntitySpiderTitan entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.titan = entity;
		state.invulTime = entity.getInvulTime();
		state.extraPower = entity.getExtraPower();
		state.isStunned = entity.isStunned;
	}

	@Override
	protected void scale(TitanRenderState state, PoseStack poseStack) {
		float f1 = 16.0F;
		int i = state.invulTime;
		if (i > 0) {
			f1 -= (i - state.partialTick) / 440.0F * 7.75F;
		}
		int i2 = state.extraPower;
		if (i2 > 0) {
			f1 += i2 * 0.5F;
		}
		poseStack.scale(f1, f1, f1);
		poseStack.translate(0.0F, 0.01F, 0.0F);
	}
	@Override
	protected float getFlipDegrees() {
		return 180.0F;
	}

	@Override
	protected boolean isBodyVisible(TitanRenderState state) {
		return !state.isInvisible;
	}

	@Override
	protected boolean shouldShowName(EntitySpiderTitan entity, double distanceToCameraSq) {
		return false;
	}

	@Override
	public Identifier getTextureLocation(TitanRenderState state) {
		return SPIDER_TITAN;
	}
}
