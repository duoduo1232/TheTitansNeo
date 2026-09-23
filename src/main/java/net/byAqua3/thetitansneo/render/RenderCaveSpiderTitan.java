package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.titan.EntitySpiderTitan;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderCaveSpiderTitan extends RenderSpiderTitan {

	public static final Identifier CAVE_SPIDER_TITAN = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/cave_spider_titan.png");

	public RenderCaveSpiderTitan(Context context) {
		super(context);
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
		poseStack.scale(0.7F, 0.7F, 0.7F);
		poseStack.scale(f1, f1, f1);
		poseStack.translate(0.0F, 0.01F, 0.0F);
	}

	@Override
	protected boolean shouldShowName(EntitySpiderTitan entity, double distanceToCameraSq) {
		return false;
	}

	@Override
	public Identifier getTextureLocation(TitanRenderState state) {
		return CAVE_SPIDER_TITAN;
	}
}
