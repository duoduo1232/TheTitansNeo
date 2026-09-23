package net.byAqua3.thetitansneo.render.minion;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.minion.EnumMinionType;
import net.byAqua3.thetitansneo.entity.minion.IMinion;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.CreeperRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.Creeper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderCreeperTitanMinion extends CreeperRenderer {

	public static final Identifier CREEPER = Identifier.withDefaultNamespace("textures/entity/creeper/creeper.png");
	public static final Identifier CREEPER_PRIEST = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/creeper/creeper_priest.png");
	public static final Identifier CREEPER_ZEALOT = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/creeper/creeper_zealot.png");
	public static final Identifier CREEPER_BISHOP = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/creeper/creeper_bishop.png");
	public static final Identifier CREEPER_TEMPLAR = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/creeper/creeper_templar.png");

	public RenderCreeperTitanMinion(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(Creeper entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		// 26.1.2: getTextureLocation 改为按 RenderState 取，所以纹理在这里选定。
		state.texture = getMinionTexture(entity);
	}

	private static Identifier getMinionTexture(Creeper entity) {
		if (entity instanceof IMinion) {
			IMinion minion = (IMinion) entity;
			switch (minion.getMinionType()) {
			case PRIEST:
				return CREEPER_PRIEST;
			case ZEALOT:
				return CREEPER_ZEALOT;
			case BISHOP:
				return CREEPER_BISHOP;
			case TEMPLAR:
				return CREEPER_TEMPLAR;
			default:
				return CREEPER;
			}
		}
		return CREEPER;
	}

	@Override
	public Identifier getTextureLocation(TitanRenderState state) {
		return state.texture;
	}
}
