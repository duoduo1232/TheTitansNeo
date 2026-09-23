package net.byAqua3.thetitansneo.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.byAqua3.thetitansneo.TheTitansNeo;
import net.byAqua3.thetitansneo.entity.titan.EntityZombieTitan;
import net.byAqua3.thetitansneo.model.ModelZombieTitan;
import net.byAqua3.thetitansneo.render.state.TitanRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.Identifier;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderZombieTitan extends LivingEntityRenderer<EntityZombieTitan, TitanRenderState, ModelZombieTitan> {

	public static final Identifier ZOMBIE_TITAN = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/zombie_titan.png");
	public static final Identifier ZOMBIE_TITAN_ARMED = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/zombie_titan_armed.png");
	public static final Identifier ZOMBIE_VILLAGER_TITAN = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/zombie_villager_titan.png");
	public static final Identifier ZOMBIE_VILLAGER_TITAN_ARMED = Identifier.tryBuild(TheTitansNeo.MODID, "textures/entity/titans/zombie_villager_titan_armed.png");

	public RenderZombieTitan(Context context) {
		super(context, new ModelZombieTitan(0.0F), 0.5F);
		this.addLayer(new net.byAqua3.thetitansneo.render.layer.LayerZombieTitanItemInHand(this));
		this.addLayer(new net.byAqua3.thetitansneo.render.layer.LayerZombieTitanArmor(this));
	}

	@Override
	public TitanRenderState createRenderState() {
		return new TitanRenderState();
	}

	@Override
	public void extractRenderState(EntityZombieTitan entity, TitanRenderState state, float partialTicks) {
		super.extractRenderState(entity, state, partialTicks);
		state.titan = entity;
		state.invulTime = entity.getInvulTime();
		state.extraPower = entity.getExtraPower();
		state.isArmed = entity.isArmed();
		state.isVillager = entity.isVillager();
		state.isStunned = entity.isStunned;
		state.titanHealth = entity.getTitanHealth();
		state.titanMaxHealth = entity.getMaxHealth();
	}

	@Override
	protected void scale(TitanRenderState state, PoseStack poseStack) {
		float f1 = 16.0F;
		int i = state.invulTime;
		if (i > 0) {
			f1 -= (i - state.partialTick) / 440.0F * 7.75F;
		}
		int i2 = state.extraPower;
		if (i2 > 0) {
			f1 += i2 * 0.5F;
		}
		poseStack.scale(f1, f1, f1);
		poseStack.translate(0.0F, 0.01F, 0.0F);
	}

	@Override
	protected boolean shouldShowName(EntityZombieTitan entity, double distanceToCameraSq) {
		return false;
	}

	@Override
	public Identifier getTextureLocation(TitanRenderState state) {
		if (state.isArmed) {
			return !state.isVillager ? ZOMBIE_TITAN_ARMED : ZOMBIE_VILLAGER_TITAN_ARMED;
		}
		return !state.isVillager ? ZOMBIE_TITAN : ZOMBIE_VILLAGER_TITAN;
	}
}
