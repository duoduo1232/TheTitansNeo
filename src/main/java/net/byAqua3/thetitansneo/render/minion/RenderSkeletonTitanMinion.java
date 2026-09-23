package net.byAqua3.thetitansneo.render.minion;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.minion.EnumMinionType;
import net.byAqua3.thetitansneo.entity.minion.IMinion;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.minecraft.client.renderer.entity.state.SkeletonRenderState;

@OnlyIn(Dist.CLIENT)
public class RenderSkeletonTitanMinion extends SkeletonRenderer {

	private Identifier currentTexture;


	public static final Identifier SKELETON = Identifier.withDefaultNamespace("textures/entity/skeleton/skeleton.png");
	public static final Identifier SKELETON_PRIEST = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/skeleton/skeleton_priest.png");
	public static final Identifier SKELETON_ZEALOT = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/skeleton/skeleton_zealot.png");
	public static final Identifier SKELETON_BISHOP = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/skeleton/skeleton_bishop.png");
	public static final Identifier SKELETON_TEMPLAR = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/skeleton/skeleton_templar.png");

	public RenderSkeletonTitanMinion(EntityRendererProvider.Context context) {
		super(context);
	}



	@Override
	public void extractRenderState(Skeleton entity, SkeletonRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		// 26.1.2: getTextureLocation 改为按 RenderState 取，所以纹理在这里选定。
		this.currentTexture = getMinionTexture(entity);
	}

	private static Identifier getMinionTexture(Skeleton entity) {
		if (entity instanceof IMinion) {
			IMinion minion = (IMinion) entity;
			switch (minion.getMinionType()) {
			case PRIEST:
				return SKELETON_PRIEST;
			case ZEALOT:
				return SKELETON_ZEALOT;
			case BISHOP:
				return SKELETON_BISHOP;
			case TEMPLAR:
				return SKELETON_TEMPLAR;
			default:
				return SKELETON;
			}
		}
		return SKELETON;
	}

	@Override
	public Identifier getTextureLocation(SkeletonRenderState state) {
		return this.currentTexture == null ? SKELETON : this.currentTexture;
	}
}
