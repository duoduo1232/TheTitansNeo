package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.titan.EntityCreeperTitan;
import net.byAqua3.thetitansneo.model.ModelCreeperTitan;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderCreeperTitan extends LivingEntityRenderer<EntityCreeperTitan, TitanRenderState, ModelCreeperTitan> {

	public static final Identifier CREEPER_TITAN = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/creeper_titan.png");

	public RenderCreeperTitan(Context context) {
		super(context, new ModelCreeperTitan(0.0F), 0.75F);
		this.addLayer(new net.byAqua3.thetitansneo.render.layer.LayerCreeperTitanArmor(this));
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(EntityCreeperTitan entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.titan = entity;
		state.invulTime = entity.getInvulTime();
		state.extraPower = entity.getExtraPower();
		state.isCharged = entity.getCharged();
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
	protected boolean shouldShowName(EntityCreeperTitan entity, double distanceToCameraSq) {
		return false;
	}

	@Override
	protected Identifier getTextureLocation(TitanRenderState state) {
		return CREEPER_TITAN;
	}
}
