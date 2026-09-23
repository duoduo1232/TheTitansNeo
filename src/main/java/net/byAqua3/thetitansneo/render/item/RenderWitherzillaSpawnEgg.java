package net.byAqua3.thetitansneo.render.item;

import java.util.function.Consumer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.MapCodec;

import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.model.ModelTitanSpawnEgg;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.client.resources.model.sprite.SpriteGetter;
import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3fc;

/**
 * 26.1.2: migrated from the deleted NeoForge {@code IItemRenderer} hook to
 * {@link net.minecraft.client.renderer.special.SpecialModelRenderer}. Transforms are unchanged; drawing now
 * goes through the deferred node collector and the atlas sprite getter.
 */
public class RenderWitherzillaSpawnEgg implements net.minecraft.client.renderer.special.SpecialModelRenderer<ItemStack> {

	public ModelTitanSpawnEgg model = new ModelTitanSpawnEgg();

	private final SpriteGetter sprites;

	public RenderWitherzillaSpawnEgg(SpriteGetter sprites) {
		this.sprites = sprites;
	}

	@Override
	public void submit(ItemStack stack, ItemDisplayContext context, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int packedLight, int packedOverlay, boolean hasFoil, int outlineColor) {
		Minecraft mc = Minecraft.getInstance();

		this.model.ticksExisted = mc.player != null ? mc.player.tickCount : 0;
		this.model.eggType = 0;

		Identifier texture = Identifier.tryBuild(TheTitansNeo.MODID, "entity/items/eggs/witherzilla_egg");
		// 26.1.2: Material.buffer(MultiBufferSource, ...) is gone; this is replaced by a submitted model.
		final SpriteId spriteId = new SpriteId(net.minecraft.client.renderer.texture.TextureAtlas.LOCATION_BLOCKS, texture);
		final ModelTitanSpawnEgg eggModel = this.model;

		switch (context) {
		case FIRST_PERSON_LEFT_HAND:
			poseStack.pushPose();
			poseStack.scale(4.0F, 4.0F, 4.0F);
			poseStack.mulPose(Axis.XP.rotationDegrees(190.0F));
			poseStack.translate(0.279F, -0.175F, 0.2F);
			submitNodeCollector.submitModel(eggModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
			poseStack.popPose();
			break;
		case FIRST_PERSON_RIGHT_HAND:
			poseStack.pushPose();
			poseStack.scale(4.0F, 4.0F, 4.0F);
			poseStack.mulPose(Axis.XP.rotationDegrees(190.0F));
			poseStack.translate(0.0F, -0.175F, 0.2F);
			submitNodeCollector.submitModel(eggModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
			poseStack.popPose();
			break;
		case THIRD_PERSON_LEFT_HAND:
			poseStack.pushPose();
			poseStack.scale(4.0F, 4.0F, 4.0F);
			poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
			poseStack.translate(0.15F, -0.3F, -0.1F);
			submitNodeCollector.submitModel(eggModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
			poseStack.popPose();
			break;
		case THIRD_PERSON_RIGHT_HAND:
			poseStack.pushPose();
			poseStack.scale(4.0F, 4.0F, 4.0F);
			poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
			poseStack.translate(0.15F, -0.3F, -0.1F);
			submitNodeCollector.submitModel(eggModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
			poseStack.popPose();
			break;
		case GROUND:
			poseStack.pushPose();
			poseStack.scale(4.0F, 4.0F, 4.0F);
			poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));
			poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
			poseStack.translate(0.15F, -0.2F, 0.2F);
			submitNodeCollector.submitModel(eggModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
			poseStack.popPose();
			break;
		case GUI:
			poseStack.pushPose();
			poseStack.scale(1.875F, 1.875F, 1.875F);
			poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Axis.XP.rotationDegrees(30.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(45.0F));
			poseStack.translate(0.075F, -0.21F, 0.3F);
			submitNodeCollector.submitModel(eggModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
			poseStack.popPose();
			break;
		case FIXED:
			poseStack.pushPose();
			poseStack.scale(4.0F, 4.0F, 4.0F);
			poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
			poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
			poseStack.translate(-0.125F, -0.15F, 0.15F);
			submitNodeCollector.submitModel(eggModel, null, poseStack, packedLight, packedOverlay, -1, spriteId, this.sprites, outlineColor, null);
			poseStack.popPose();
			break;
		default:
			break;
		}
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		PoseStack poseStack = new PoseStack();
		this.model.root().getExtentsForGui(poseStack, output);
	}

	/** 26.1.2: replaces the deleted per-item renderer map with the data-driven special model route. */
	public static final class Unbaked implements SpecialModelUnbaked {

		public static final MapCodec<RenderWitherzillaSpawnEgg.Unbaked> MAP_CODEC = MapCodec.unit(new RenderWitherzillaSpawnEgg.Unbaked());

		@Override
		public MapCodec<RenderWitherzillaSpawnEgg.Unbaked> type() {
			return MAP_CODEC;
		}

		@Override
		public net.minecraft.client.renderer.special.SpecialModelRenderer<?> bake(SpecialModelRenderer.BakingContext context) {
			return new RenderWitherzillaSpawnEgg(context.sprites());
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
