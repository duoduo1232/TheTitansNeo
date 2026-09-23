package net.byAqua3.thetitansneo.render;

import net.byAqua3.thetitansneo.entity.titan.EntityTitanSpirit;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

public class RenderTitanSpirit extends EntityRenderer<EntityTitanSpirit, EntityRenderState> {

	public RenderTitanSpirit(Context context) {
		super(context);
	}

	// 26.1.2: 该渲染器本身不绘制任何东西（原先 getTextureLocation 返回 null），
	// 只需要提供 RenderState 类型即可。
	@Override
	public EntityRenderState createRenderState() {
		return new EntityRenderState();
	}
}
