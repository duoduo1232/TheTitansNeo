package net.byAqua3.thetitansneo.render.item;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;

/**
 * 26.1.2: 本模组的武器与刷怪蛋原本靠 {@code IItemRenderer} + 对 ItemRenderer 的 mixin 劫持来绘制，
 * 那条路径在 26.1.2 已被整体删除。现在改为数据驱动的 special model：
 * <ul>
 * <li>这里把自定义 codec 注册到 {@link RegisterSpecialModelRendererEvent}（mod 事件总线）；</li>
 * <li>{@code assets/thetitansneo/items/<id>.json} 里用
 * {@code {"type":"minecraft:special","base":...,"model":{"type":"thetitansneo:<id>"}}} 选中它。</li>
 * </ul>
 */
@OnlyIn(Dist.CLIENT)
public final class TheTitansNeoSpecialModels {

	private TheTitansNeoSpecialModels() {
	}

	public static void register(RegisterSpecialModelRendererEvent event) {
		event.register(id("optima_axe"), RenderOptimaAxe.Unbaked.MAP_CODEC);
		event.register(id("ultima_blade"), RenderUltimaBlade.Unbaked.MAP_CODEC);
		event.register(id("titan_spawn_egg"), RenderTitanSpawnEgg.Unbaked.MAP_CODEC);
	}

	private static Identifier id(String path) {
		return Identifier.tryBuild(TheTitansNeo.MODID, path);
	}
}
