package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.byAqua3.thetitansneo.entity.titan.EntitySnowGolemTitan;
import net.minecraft.client.model.animal.golem.SnowGolemModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.byAqua3.thetitansneo.render.layer.LayerSnowGolemTitanHead;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderSnowGolemTitan extends LivingEntityRenderer<EntitySnowGolemTitan, TitanRenderState, SnowGolemModel> {

	private static final Identifier SNOW_GOLEM_TTIAN = Identifier.withDefaultNamespace("textures/entity/snow_golem.png");
	/** 26.1.2: 方块模型需要在 extract 阶段预解析进 BlockModelRenderState。 */
	private static final BlockDisplayContext BLOCK_DISPLAY_CONTEXT = BlockDisplayContext.create();
	private final BlockModelResolver blockModelResolver;

	public RenderSnowGolemTitan(Context context) {
		super(context, new SnowGolemModel(context.bakeLayer(ModelLayers.SNOW_GOLEM)), 0.5F);
		this.blockModelResolver = context.getBlockModelResolver();
		this.addLayer(new LayerSnowGolemTitanHead(this));
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(EntitySnowGolemTitan entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.titan = entity;
		state.invulTime = entity.getInvulTime();
		state.deathTicks = entity.deathTicks;
		state.titanHealth = entity.getTitanHealth();
		state.titanMaxHealth = entity.getMaxHealth();
		if (state.titanHealth > state.titanMaxHealth / 4.0F) {
			this.blockModelResolver.update(
				state.pumpkinBlock,
				(state.titanHealth > state.titanMaxHealth / 2.0F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN).defaultBlockState(),
				BLOCK_DISPLAY_CONTEXT
			);
		} else {
			state.pumpkinBlock.clear();
		}
	}

	@Override
	protected void scale(TitanRenderState state, PoseStack poseStack) {
		float f1 = 16.0F;
		int i = state.invulTime;
		if (i > 0) {
			f1 -= (i - state.partialTick) / 10.0F;
		}
		poseStack.scale(f1, f1, f1);
		poseStack.translate(0.0F, 0.0275F, 0.0F);
	}
	@Override
	protected void setupRotations(TitanRenderState state, PoseStack poseStack, float bodyRot, float entityScale) {
		super.setupRotations(state, poseStack, bodyRot, entityScale);
		if (state.deathTicks > 0) {
			float f = (state.deathTicks + state.partialTick - 1.0F) / 20.0F * 1.6F;
			f = (float) Math.sqrt(f);
			if (f > 1.0F) {
				f = 1.0F;
			}
			poseStack.scale(1.0F + f * 1.05F, 1.0F - f * 0.5F, 1.0F + f * 1.05F);
		}
	}

	@Override
	protected boolean shouldShowName(EntitySnowGolemTitan entity, double distanceToCameraSq) {
		return false;
	}

	@Override
	public Identifier getTextureLocation(TitanRenderState state) {
		return SNOW_GOLEM_TTIAN;
	}
}
