package net.byAqua3.thetitansneo.render.minion;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.minion.EnumMinionType;
import net.byAqua3.thetitansneo.entity.minion.IMinion;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SilverfishRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.Silverfish;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderOmegafishMinion extends SilverfishRenderer {

	public static final Identifier SILVERFISH = Identifier.withDefaultNamespace("textures/entity/silverfish.png");
	public static final Identifier SILVERFISH_PRIEST = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/silverfish_priest.png");
	public static final Identifier SILVERFISH_ZEALOT = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/silverfish_zealot.png");
	public static final Identifier SILVERFISH_BISHOP = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/silverfish_bishop.png");
	public static final Identifier SILVERFISH_TEMPLAR = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/silverfish_templar.png");

	public RenderOmegafishMinion(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(Silverfish entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		// 26.1.2: getTextureLocation 改为按 RenderState 取，所以纹理在这里选定。
		state.texture = getMinionTexture(entity);
	}

	private static Identifier getMinionTexture(Silverfish entity) {
		if (entity instanceof IMinion) {
			IMinion minion = (IMinion) entity;
			switch (minion.getMinionType()) {
			case PRIEST:
				return SILVERFISH_PRIEST;
			case ZEALOT:
				return SILVERFISH_ZEALOT;
			case BISHOP:
				return SILVERFISH_BISHOP;
			case TEMPLAR:
				return SILVERFISH_TEMPLAR;
			default:
				return SILVERFISH;
			}
		}
		return SILVERFISH;
	}

	@Override
	public Identifier getTextureLocation(TitanRenderState state) {
		return state.texture;
	}
}
