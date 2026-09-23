package net.byAqua3.thetitansneo.render.minion;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.minion.EnumMinionType;
import net.byAqua3.thetitansneo.entity.minion.IMinion;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.BlazeRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.Blaze;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;

@OnlyIn(Dist.CLIENT)
public class RenderBlazeTitanMinion extends BlazeRenderer {

	private Identifier currentTexture;


	public static final Identifier BLAZE = Identifier.withDefaultNamespace("textures/entity/blaze/blaze.png");
	public static final Identifier BLAZE_PRIEST = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/blaze/blaze_priest.png");
	public static final Identifier BLAZE_ZEALOT = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/blaze/blaze_zealot.png");
	public static final Identifier BLAZE_BISHOP = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/blaze/blaze_bishop.png");
	public static final Identifier BLAZE_TEMPLAR = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/blaze/blaze_templar.png");

	public RenderBlazeTitanMinion(EntityRendererProvider.Context context) {
		super(context);
	}



	@Override
	public void extractRenderState(Blaze entity, LivingEntityRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		// 26.1.2: getTextureLocation 改为按 RenderState 取，所以纹理在这里选定。
		this.currentTexture = getMinionTexture(entity);
	}

	private static Identifier getMinionTexture(Blaze entity) {
		if (entity instanceof IMinion) {
			IMinion minion = (IMinion) entity;
			switch (minion.getMinionType()) {
			case PRIEST:
				return BLAZE_PRIEST;
			case ZEALOT:
				return BLAZE_ZEALOT;
			case BISHOP:
				return BLAZE_BISHOP;
			case TEMPLAR:
				return BLAZE_TEMPLAR;
			default:
				return BLAZE;
			}
		}
		return BLAZE;
	}

	@Override
	public Identifier getTextureLocation(LivingEntityRenderState state) {
		return this.currentTexture == null ? BLAZE : this.currentTexture;
	}
}
