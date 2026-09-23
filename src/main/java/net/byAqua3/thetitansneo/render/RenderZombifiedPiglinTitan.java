package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.titan.EntityZombifiedPiglinTitan;
import net.byAqua3.thetitansneo.model.ModelZombifiedPiglinTitan;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderZombifiedPiglinTitan extends LivingEntityRenderer<EntityZombifiedPiglinTitan, TitanRenderState, ModelZombifiedPiglinTitan> {

	public static final Identifier ZOMBIFIED_PIGLIN_TITAN = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/zombified_piglin_titan.png");

	public RenderZombifiedPiglinTitan(Context context) {
		super(context, new ModelZombifiedPiglinTitan(0.0F), 0.5F);
		this.addLayer(new net.byAqua3.thetitansneo.render.layer.LayerZombifiedPiglinTitanItemInHand(this));
		this.addLayer(new net.byAqua3.thetitansneo.render.layer.LayerZombifiedPiglinTitanArmor(this));
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(EntityZombifiedPiglinTitan entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.titan = entity;
		state.invulTime = entity.getInvulTime();
		state.extraPower = entity.getExtraPower();
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
	protected boolean shouldShowName(EntityZombifiedPiglinTitan entity, double distanceToCameraSq) {
		return false;
	}

	@Override
	protected Identifier getTextureLocation(TitanRenderState state) {
		return ZOMBIFIED_PIGLIN_TITAN;
	}
}
