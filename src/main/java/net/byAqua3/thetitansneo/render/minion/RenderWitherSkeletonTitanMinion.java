package net.byAqua3.thetitansneo.render.minion;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.minion.EnumMinionType;
import net.byAqua3.thetitansneo.entity.minion.IMinion;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.WitherSkeletonRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderWitherSkeletonTitanMinion extends WitherSkeletonRenderer {

	public static final Identifier WITHER_SKELETON = Identifier.withDefaultNamespace("textures/entity/skeleton/wither_skeleton.png");
	public static final Identifier WITHER_SKELETON_PRIEST = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/skeleton/wither_skeleton_priest.png");
	public static final Identifier WITHER_SKELETON_ZEALOT = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/skeleton/wither_skeleton_zealot.png");
	public static final Identifier WITHER_SKELETON_BISHOP = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/skeleton/wither_skeleton_bishop.png");
	public static final Identifier WITHER_SKELETON_TEMPLAR = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/minions/skeleton/wither_skeleton_templar.png");

	public RenderWitherSkeletonTitanMinion(EntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(WitherSkeleton entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		// 26.1.2: getTextureLocation 改为按 RenderState 取，所以纹理在这里选定。
		state.texture = getMinionTexture(entity);
	}

	private static Identifier getMinionTexture(WitherSkeleton entity) {
		if (entity instanceof IMinion) {
			IMinion minion = (IMinion) entity;
			switch (minion.getMinionType()) {
			case PRIEST:
				return WITHER_SKELETON_PRIEST;
			case ZEALOT:
				return WITHER_SKELETON_ZEALOT;
			case BISHOP:
				return WITHER_SKELETON_BISHOP;
			case TEMPLAR:
				return WITHER_SKELETON_TEMPLAR;
			default:
				return WITHER_SKELETON;
			}
		}
		return WITHER_SKELETON;
	}

	@Override
	public Identifier getTextureLocation(TitanRenderState state) {
		return state.texture;
	}
}
