package net.byAqua3.thetitansneo.render.item;

import java.util.function.Consumer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.MapCodec;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.item.ItemTitanSpawnEgg;
import net.byAqua3.thetitansneo.loader.TheTitansNeoConfigs;
import net.byAqua3.thetitansneo.loader.TheTitansNeoEntities;
import net.byAqua3.thetitansneo.model.ModelTitanSpawnEgg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector3fc;

/**
 * 26.1.2: ported from NeoForge's deleted {@code IItemRenderer} extension (hijacked through a mixin on
 * {@code ItemRenderer}) to the vanilla {@link net.minecraft.client.renderer.special.SpecialModelRenderer}
 * pipeline. The per-context {@code PoseStack} transforms below are carried over verbatim; what changed is how
 * the geometry reaches the GPU and how the egg texture is resolved.
 */
public class RenderTitanSpawnEgg implements net.minecraft.client.renderer.special.SpecialModelRenderer<ItemStack> {

	public ModelTitanSpawnEgg model = new ModelTitanSpawnEgg();

	private final SpriteGetter sprites;

	public RenderTitanSpawnEgg(SpriteGetter sprites) {
		this.sprites = sprites;
	}

	/** 26.1.2: atlases are addressed by {@link SpriteId} instead of a hand-built {@code TextureAtlasSprite}. */
	public TextureAtlasSprite sprite(Identifier texture) {
		return this.sprites.get(new SpriteId(net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS, texture));
	}

	/** 26.1.2: {@code RenderType::entityTranslucentCull} no longer exists as a method reference. */
	public static RenderType entityTranslucentCull(Identifier texture) {
		return RenderTypes.entityTranslucentCullItemTarget(texture);
	}

	/** 26.1.2: {@code RenderType::entityTranslucentCull} no longer exists as a method reference. */
	public static RenderType translucentItemSheet() {
		return RenderTypes.itemTranslucent(net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS);
	}

	public void renderItem(Identifier itemTexture, boolean itemFlipped, PoseStack poseStack, int packedLight, int packedOverlay, SubmitNodeCollector submitNodeCollector) {
		poseStack.pushPose();

		this.model.item.translateAndRotate(poseStack);

		if (itemFlipped) {
			poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
			poseStack.translate(0.0F, 0.5F, 0.0F);
		}

		poseStack.mulPose(Axis.XP.rotationDegrees(-120.0F));
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
		poseStack.translate(0.325F, -0.5F, 0.0F);

		poseStack.scale(0.68F, 0.68F, 0.68F);
		poseStack.translate(0.070625F, 0.2F, 0.070625F);
		poseStack.mulPose(Axis.YP.rotationDegrees(-90.0F));
		poseStack.mulPose(Axis.ZP.rotationDegrees(25.0F));

		// 26.1.2: geometry is emitted inside submitCustomGeometry, which hands back a live VertexConsumer.
		final TextureAtlasSprite textureAtlasSprite = this.sprite(itemTexture);
		final PoseStack frozen = poseStack;
		submitNodeCollector.submitCustomGeometry(frozen, translucentItemSheet(), (pose, buffer) ->
				ItemSpriteGeometry.renderSprite(frozen, buffer, textureAtlasSprite, packedLight));

		poseStack.popPose();
	}

	public void renderFire(PoseStack poseStack, int packedLight, int packedOverlay, SubmitNodeCollector submitNodeCollector) {
		// 26.1.2: BlockRenderDispatcher + ModelRenderer.renderModel(pose, buf, state, model, r, g, b, light,
		// overlay, ModelData, renderType) are gone. The replacement is a two-step resolve-then-submit:
		// Minecraft.getBlockModelResolver().update(renderState, state, displayContext) fills a
		// BlockModelRenderState, and BlockModelRenderState.submit(pose, collector, light, overlay, outline)
		// hands it to the deferred node collector. Same block state, same transforms, same atlas.
		poseStack.pushPose();

		this.model.fire.translateAndRotate(poseStack);

		poseStack.translate(0.0F, -0.34375F, 0.0F);
		poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));

		poseStack.scale(0.8F, -0.625F, -0.8F);
		poseStack.translate(-0.5F, -0.5F, -0.5F);

		Minecraft mc = Minecraft.getInstance();
		BlockModelRenderState blockRenderState = new BlockModelRenderState();
		BlockState blockState = Blocks.FIRE.defaultBlockState();
		mc.getBlockModelResolver().update(blockRenderState, blockState, BlockDisplayContext.create());
		blockRenderState.submit(poseStack, submitNodeCollector, packedLight, packedOverlay, 0);

		poseStack.popPose();
	}

	@Override
	public void submit(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, int packedOverlay, boolean hasFoil, int outlineColor) {
		Minecraft mc = Minecraft.getInstance();
		Item item = stack.getItem();

		// 26.1.2: the caller passes the live pose, so the model tick is advanced here instead of relying on
		// the removed ItemRenderer#render mixin.
		this.model.ticksExisted = mc.player != null ? mc.player.tickCount : 0;

		Identifier texture = null;
		Identifier itemTexture = null;
		boolean itemFlipped = false;

		if (item instanceof ItemTitanSpawnEgg) {
			ItemTitanSpawnEgg spawnEgg = (ItemTitanSpawnEgg) item;
			EntityType<?> entityType = spawnEgg.getEntityType();

			if (entityType == TheTitansNeoEntities.SNOW_GOLEM_TITAN.get()) {
				this.model.eggType = 0;
				texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/snow_golem_titan_egg");
			} else if (entityType == TheTitansNeoEntities.SLIME_TITAN.get()) {
				this.model.eggType = 0;
				texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/slime_titan_egg");
			} else if (entityType == TheTitansNeoEntities.MAGMACUBE_TITAN.get()) {
				this.model.eggType = 0;
				texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/magma_cube_titan_egg");
			} else if (entityType == TheTitansNeoEntities.OMEGAFISH.get()) {
				this.model.eggType = 3;
				texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/omegafish_egg");
			} else if (entityType == TheTitansNeoEntities.ZOMBIE_TITAN.get()) {
				this.model.eggType = 0;
				texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/zombie_titan_egg");
			} else if (entityType == TheTitansNeoEntities.SKELETON_TITAN.get()) {
				if (spawnEgg.getSpecialId() == 1) {
					this.model.eggType = 4;
					texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/wither_skeleton_titan_egg");
					itemTexture = Identifier.tryBuild(TheTitansNeo.MODID, "item/stone_sword_256");
				} else {
					this.model.eggType = 4;
					texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/skeleton_titan_egg");
					itemTexture = Identifier.tryBuild(TheTitansNeo.MODID, "item/bow_pulling_2_256");
					itemFlipped = true;
				}
			} else if (entityType == TheTitansNeoEntities.CREEPER_TITAN.get()) {
				if (spawnEgg.getSpecialId() == 1) {
					this.model.eggType = 1;
					texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/charged_creeper_titan_egg");
				} else {
					this.model.eggType = 0;
					texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/creeper_titan_egg");
				}
			} else if (entityType == TheTitansNeoEntities.SPIDER_TITAN.get()) {
				if (spawnEgg.getSpecialId() == 1) {
					this.model.eggType = 4;
					texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/spider_jockey_titan_egg");
					itemTexture = Identifier.tryBuild(TheTitansNeo.MODID, "item/bow_pulling_2_256");
					itemFlipped = true;
				} else {
					this.model.eggType = 0;
					texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/spider_titan_egg");
				}
			} else if (entityType == TheTitansNeoEntities.CAVE_SPIDER_TITAN.get()) {
				this.model.eggType = 0;
				texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/cave_spider_titan_egg");
			} else if (entityType == TheTitansNeoEntities.ZOMBIFIED_PIGLIN_TITAN.get()) {
				this.model.eggType = 4;
				texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/zombified_piglin_titan_egg");
				itemTexture = Identifier.tryBuild(TheTitansNeo.MODID, "item/gold_sword_256");
			} else if (entityType == TheTitansNeoEntities.BLAZE_TITAN.get()) {
				this.model.eggType = 2;
				texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/blaze_titan_egg");
			} else if (entityType == TheTitansNeoEntities.ENDER_COLOSSUS.get()) {
				this.model.eggType = 5;
				texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/ender_colossus_egg");
			} else if (entityType == TheTitansNeoEntities.GHAST_TITAN.get()) {
				this.model.eggType = 3;
				texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/ghast_titan_egg");
			} else if (entityType == TheTitansNeoEntities.IRON_GOLEM_TITAN.get()) {
				this.model.eggType = 0;
				texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/iron_golem_titan_egg");
			}
		}

		if (TheTitansNeoConfigs.getBoolean(TheTitansNeoConfigs.titanWeaponOldModel, false)) {
			if (!this.model.item.visible) {
				this.model.item.visible = true;
			}
		} else if (itemTexture != null) {
			// 26.1.2: the legacy GUI-only endBatch()/Lighting juggling is unnecessary; custom geometry is
			// already submitted as an ordered, separately batched node.
			this.model.item.visible = false;

			final Identifier resolved = itemTexture;
			final boolean flipped = itemFlipped;
			switch (context) {
			case FIRST_PERSON_LEFT_HAND:
				poseStack.pushPose();
				poseStack.scale(1.0F, 1.0F, 1.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(190.0F));
				poseStack.translate(0.57F, -0.8F, 0.1F);
				this.renderItem(resolved, flipped, poseStack, packedLight, packedOverlay, submitNodeCollector);
				poseStack.popPose();
				break;
			case FIRST_PERSON_RIGHT_HAND:
				poseStack.pushPose();
				poseStack.scale(1.0F, 1.0F, 1.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(190.0F));
				poseStack.translate(-0.55F, -0.8F, 0.1F);
				this.renderItem(resolved, flipped, poseStack, packedLight, packedOverlay, submitNodeCollector);
				poseStack.popPose();
				break;
			case THIRD_PERSON_LEFT_HAND:
				poseStack.pushPose();
				poseStack.scale(1.0F, 1.0F, 1.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
				poseStack.translate(0.1F, -0.7F, -0.4F);
				this.renderItem(resolved, flipped, poseStack, packedLight, packedOverlay, submitNodeCollector);
				poseStack.popPose();
				break;
			case THIRD_PERSON_RIGHT_HAND:
				poseStack.pushPose();
				poseStack.scale(1.0F, 1.0F, 1.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
				poseStack.translate(0.1F, -0.7F, -0.4F);
				this.renderItem(resolved, flipped, poseStack, packedLight, packedOverlay, submitNodeCollector);
				poseStack.popPose();
				break;
			case GROUND:
				poseStack.pushPose();
				poseStack.scale(1.0F, 1.0F, 1.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
				poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
				poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
				poseStack.translate(-0.15F, -0.6F, 0.2F);
				this.renderItem(resolved, flipped, poseStack, packedLight, packedOverlay, submitNodeCollector);
				poseStack.popPose();
				break;
			case GUI:
				poseStack.pushPose();
				poseStack.scale(0.7F, 0.65F, 0.65F);
				poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
				poseStack.mulPose(Axis.XP.rotationDegrees(30.0F));
				poseStack.mulPose(Axis.YP.rotationDegrees(45.0F));
				poseStack.translate(0.05F, -0.75F, 0.3F);
				this.renderItem(resolved, flipped, poseStack, packedLight, packedOverlay, submitNodeCollector);
				poseStack.popPose();
				break;
			case FIXED:
				poseStack.pushPose();
				poseStack.scale(1.0F, 1.0F, 1.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
				poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
				poseStack.translate(-0.9F, -0.5F, 0.25F);
				this.renderItem(resolved, flipped, poseStack, packedLight, packedOverlay, submitNodeCollector);
				poseStack.popPose();
				break;
			default:
				break;
			}
		}

		if (this.model.eggType == 2) {
			final RenderType fireType = entityTranslucentCull(net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS);

			this.model.fire.visible = false;

			switch (context) {
			case FIRST_PERSON_LEFT_HAND:
				poseStack.pushPose();
				poseStack.scale(1.0F, 1.0F, 1.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(190.0F));
				poseStack.translate(1.12F, -0.4F, 0.1F);
				this.renderFire(poseStack, packedLight, packedOverlay, submitNodeCollector);
				poseStack.popPose();
				break;
			case FIRST_PERSON_RIGHT_HAND:
				poseStack.pushPose();
				poseStack.scale(1.0F, 1.0F, 1.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(190.0F));
				poseStack.translate(0.0F, -0.4F, 0.1F);
				this.renderFire(poseStack, packedLight, packedOverlay, submitNodeCollector);
				poseStack.popPose();
				break;
			case THIRD_PERSON_LEFT_HAND:
				poseStack.pushPose();
				poseStack.scale(1.0F, 1.0F, 1.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
				poseStack.translate(0.5F, -0.3F, -0.2F);
				this.renderFire(poseStack, packedLight, packedOverlay, submitNodeCollector);
				poseStack.popPose();
				break;
			case THIRD_PERSON_RIGHT_HAND:
				poseStack.pushPose();
				poseStack.scale(1.0F, 1.0F, 1.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
				poseStack.translate(0.5F, -0.3F, -0.2F);
				this.renderFire(poseStack, packedLight, packedOverlay, submitNodeCollector);
				poseStack.popPose();
				break;
			case GROUND:
				poseStack.pushPose();
				poseStack.scale(1.0F, 1.0F, 1.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
				poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
				poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
				poseStack.translate(0.5F, -0.3F, 0.4F);
				this.renderFire(poseStack, packedLight, packedOverlay, submitNodeCollector);
				poseStack.popPose();
				break;
			case GUI:
				poseStack.pushPose();
				poseStack.scale(0.7F, 0.7F, 0.7F);
				poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
				poseStack.mulPose(Axis.XP.rotationDegrees(30.0F));
				poseStack.mulPose(Axis.YP.rotationDegrees(45.0F));
				poseStack.translate(0.2F, -0.35F, 0.8F);
				this.renderFire(poseStack, packedLight, packedOverlay, submitNodeCollector);
				poseStack.popPose();
				break;
			case FIXED:
				poseStack.pushPose();
				poseStack.scale(1.0F, 1.0F, 1.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
				poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
				poseStack.translate(-0.5F, -0.1F, 0.5F);
				this.renderFire(poseStack, packedLight, packedOverlay, submitNodeCollector);
				poseStack.popPose();
				break;
			default:
				break;
			}
		}

		if (texture != null) {
			// 26.1.2: Material.buffer(MultiBufferSource, ...) is gone; the model is now submitted through
			// submitModelPart, which resolves the sprite and render type for us.
			final Material material = new Material(net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS, texture);
			final SpriteId spriteId = new SpriteId(material.sprite(), material.sprite());
			final ModelTitanSpawnEgg eggModel = this.model;

			switch (context) {
			case FIRST_PERSON_LEFT_HAND:
				poseStack.pushPose();
				poseStack.scale(2.0F, 2.0F, 2.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(190.0F));
				poseStack.translate(0.56F, -0.4F, 0.1F);
				submitNodeCollector.submitModel(eggModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
				poseStack.popPose();
				break;
			case FIRST_PERSON_RIGHT_HAND:
				poseStack.pushPose();
				poseStack.scale(2.0F, 2.0F, 2.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(190.0F));
				poseStack.translate(0.0F, -0.4F, 0.1F);
				submitNodeCollector.submitModel(eggModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
				poseStack.popPose();
				break;
			case THIRD_PERSON_LEFT_HAND:
				poseStack.pushPose();
				poseStack.scale(2.0F, 2.0F, 2.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
				poseStack.translate(0.25F, -0.3F, -0.1F);
				submitNodeCollector.submitModel(eggModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
				poseStack.popPose();
				break;
			case THIRD_PERSON_RIGHT_HAND:
				poseStack.pushPose();
				poseStack.scale(2.0F, 2.0F, 2.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
				poseStack.translate(0.25F, -0.3F, -0.1F);
				submitNodeCollector.submitModel(eggModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
				poseStack.popPose();
				break;
			case GROUND:
				poseStack.pushPose();
				poseStack.scale(2.0F, 2.0F, 2.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
				poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
				poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
				poseStack.translate(0.25F, -0.3F, 0.2F);
				submitNodeCollector.submitModel(eggModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
				poseStack.popPose();
				break;
			case GUI:
				poseStack.pushPose();
				poseStack.scale(1.25F, 1.25F, 1.25F);
				poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
				poseStack.mulPose(Axis.XP.rotationDegrees(30.0F));
				poseStack.mulPose(Axis.YP.rotationDegrees(45.0F));
				poseStack.translate(0.25F, -0.45F, 0.3F);
				submitNodeCollector.submitModel(eggModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
				poseStack.popPose();
				break;
			case FIXED:
				poseStack.pushPose();
				poseStack.scale(2.0F, 2.0F, 2.0F);
				poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
				poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
				poseStack.translate(-0.25F, -0.25F, 0.25F);
				submitNodeCollector.submitModel(eggModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
				poseStack.popPose();
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		PoseStack poseStack = new PoseStack();
		this.model.root().getExtentsForGui(poseStack, output);
	}

	/** 26.1.2: replaces the deleted per-item renderer registry with the data-driven special model route. */
	public static final class Unbaked implements SpecialModelUnbaked {

		public static final MapCodec<RenderTitanSpawnEgg.Unbaked> MAP_CODEC = MapCodec.unit(new RenderTitanSpawnEgg.Unbaked());

		@Override
		public MapCodec<RenderTitanSpawnEgg.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public net.minecraft.client.renderer.special.SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
			return new RenderTitanSpawnEgg(context.sprites());
		}
	}
	@Override
	public net.minecraft.world.item.ItemStack extractArgument(ItemStack stack) {
		return stack;
	}

	@Override
	public void submit(ItemStack stack, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, int packedOverlay, boolean hasFoil, int outlineColor) {
		this.submit(stack, net.minecraft.world.item.ItemDisplayContext.NONE, poseStack, submitNodeCollector, packedLight, packedOverlay, hasFoil, outlineColor);
	}
}
