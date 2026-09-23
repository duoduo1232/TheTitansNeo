package net.byAqua3.thetitansneo.render.projectile;

import net.byAqua3.thetitansneo.entity.projectile.EntityProtoBall;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.state.EntityRenderState;

public class RenderProtoBall extends EntityRenderer<EntityProtoBall, EntityRenderState> {

	public RenderProtoBall(Context context) {
		super(context);
	}

	// 26.1.2: RenderState 架构下不再需要 getTextureLocation，改用原版的 createRenderState。
	@Override
	public EntityRenderState createRenderState() {
		return new EntityRenderState();
	}
}
