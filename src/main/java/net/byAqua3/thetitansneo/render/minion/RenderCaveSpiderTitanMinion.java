package net.byAqua3.thetitansneo.render.minion;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.minion.EnumMinionType;
import net.byAqua3.thetitansneo.entity.minion.IMinion;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.CaveSpiderRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.spider.CaveSpider;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderCaveSpiderTitanMinion extends CaveSpiderRenderer {

	public static final Identifier CAVE_SPIDER = Identifier.withDefaultNamespace("textures/entity/spider/cave_spider.png");
	public static final Identifier CAVE_SPIDER_PRIEST = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/spider/cave_spider_priest.png");
	public static final Identifier CAVE_SPIDER_ZEALOT = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/spider/cave_spider_zealot.png");
	public static final Identifier CAVE_SPIDER_BISHOP = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/spider/cave_spider_bishop.png");
	public static final Identifier CAVE_SPIDER_TEMPLAR = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/spider/cave_spider_templar.png");

	public RenderCaveSpiderTitanMinion(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(CaveSpider entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		// 26.1.2: getTextureLocation 改为按 RenderState 取，所以纹理在这里选定。
		state.texture = getMinionTexture(entity);
	}

	private static Identifier getMinionTexture(CaveSpider entity) {
		if (entity instanceof IMinion) {
			IMinion minion = (IMinion) entity;
			switch (minion.getMinionType()) {
			case PRIEST:
				return CAVE_SPIDER_PRIEST;
			case ZEALOT:
				return CAVE_SPIDER_ZEALOT;
			case BISHOP:
				return CAVE_SPIDER_BISHOP;
			case TEMPLAR:
				return CAVE_SPIDER_TEMPLAR;
			default:
				return CAVE_SPIDER;
			}
		}
		return CAVE_SPIDER;
	}

	@Override
	public Identifier getTextureLocation(TitanRenderState state) {
		return state.texture;
	}
}
