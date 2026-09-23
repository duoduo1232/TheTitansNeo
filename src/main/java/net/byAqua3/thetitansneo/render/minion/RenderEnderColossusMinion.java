package net.byAqua3.thetitansneo.render.minion;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.minion.EnumMinionType;
import net.byAqua3.thetitansneo.entity.minion.IMinion;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EndermanRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.EnderMan;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.minecraft.client.renderer.entity.state.EndermanRenderState;

@OnlyIn(Dist.CLIENT)
public class RenderEnderColossusMinion extends EndermanRenderer {

	private Identifier currentTexture;


	public static final Identifier ENDERMAN = Identifier.withDefaultNamespace("textures/entity/enderman/enderman.png");
	public static final Identifier ENDERMAN_PRIEST = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/enderman/enderman_priest.png");
	public static final Identifier ENDERMAN_ZEALOT = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/enderman/enderman_zealot.png");
	public static final Identifier ENDERMAN_BISHOP = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/enderman/enderman_bishop.png");
	public static final Identifier ENDERMAN_TEMPLAR = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/enderman/enderman_templar.png");

	public RenderEnderColossusMinion(EntityRendererProvider.Context context) {
		super(context);
	}



	@Override
	public void extractRenderState(EnderMan entity, EndermanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		// 26.1.2: getTextureLocation 改为按 RenderState 取，所以纹理在这里选定。
		this.currentTexture = getMinionTexture(entity);
	}

	private static Identifier getMinionTexture(EnderMan entity) {
		if (entity instanceof IMinion) {
			IMinion minion = (IMinion) entity;
			switch (minion.getMinionType()) {
			case PRIEST:
				return ENDERMAN_PRIEST;
			case ZEALOT:
				return ENDERMAN_ZEALOT;
			case BISHOP:
				return ENDERMAN_BISHOP;
			case TEMPLAR:
				return ENDERMAN_TEMPLAR;
			default:
				return ENDERMAN;
			}
		}
		return ENDERMAN;
	}

	@Override
	public Identifier getTextureLocation(EndermanRenderState state) {
		return this.currentTexture == null ? ENDERMAN : this.currentTexture;
	}
}
