package net.byAqua3.thetitansneo.render.minion;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.minion.EnumMinionType;
import net.byAqua3.thetitansneo.entity.minion.IMinion;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SpiderRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.spider.Spider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderSpiderTitanMinion extends SpiderRenderer<Spider> {

	public static final Identifier SPIDER = Identifier.withDefaultNamespace("textures/entity/spider/spider.png");
	public static final Identifier SPIDER_PRIEST = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/spider/spider_priest.png");
	public static final Identifier SPIDER_ZEALOT = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/spider/spider_zealot.png");
	public static final Identifier SPIDER_BISHOP = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/spider/spider_bishop.png");
	public static final Identifier SPIDER_TEMPLAR = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/spider/spider_templar.png");

	public RenderSpiderTitanMinion(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(Spider entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		// 26.1.2: getTextureLocation 改为按 RenderState 取，所以纹理在这里选定。
		state.texture = getMinionTexture(entity);
	}

	private static Identifier getMinionTexture(Spider entity) {
		if (entity instanceof IMinion) {
			IMinion minion = (IMinion) entity;
			switch (minion.getMinionType()) {
			case PRIEST:
				return SPIDER_PRIEST;
			case ZEALOT:
				return SPIDER_ZEALOT;
			case BISHOP:
				return SPIDER_BISHOP;
			case TEMPLAR:
				return SPIDER_TEMPLAR;
			default:
				return SPIDER;
			}
		}
		return SPIDER;
	}

	@Override
	public Identifier getTextureLocation(TitanRenderState state) {
		return state.texture;
	}
}
