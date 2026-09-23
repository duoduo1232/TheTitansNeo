package net.byAqua3.thetitansneo.render.state;

import net.minecraft.client.renderer.entity.state.ZombieVillagerRenderState;
import net.minecraft.resources.Identifier;

/**
 * 26.1.2: minion 的纹理改由 RenderState 决定（getTextureLocation(S state)），
 * 而 HumanoidRenderState 里没有纹理字段，所以在这里补一个。
 */
public class MinionHumanoidRenderState extends ZombieVillagerRenderState {

	public Identifier texture;
}
