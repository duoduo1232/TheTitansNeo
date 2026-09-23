package net.byAqua3.thetitansneo.render.state;

import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

/**
 * 26.1.2: 本模组的泰坦共用一套实体数据，原版没有对应的 RenderState，
 * 因此在这里统一定义一个自定义 RenderState，把所有渲染期读取的实体字段都拷进来。
 */
@OnlyIn(Dist.CLIENT)
public class TitanRenderState extends LivingEntityRenderState {

	/**
	 * 26.1.2: 模型动画仍要读实体的一部分状态（animationTick / onGround / walkAnimation /
	 * getRandom 等），这些在 RenderState 里没有一对一字段，因此在 state 上保留实体引用，
	 * 由各渲染器的 extractRenderState 逐帧填充。
	 */
	public net.minecraft.world.entity.Entity titan;

	public int invulTime;
	public int extraPower;
	public int animationID;
	public int deathTicks;
	public int attackTimer;
	public boolean isStunned;
	public boolean isArmored;
	public boolean isArmed;
	public boolean isVillager;
	public boolean isBurrowing;
	public boolean isCharged;
	public boolean isInOmegaForm;
	public boolean isScreaming;
	public int eyeLaserTime;
	public int skeletonType;
	public int slimeSize;
	public float squishFactor;
	public float prevSquishFactor;
	public float titanHealth;
	public float titanMaxHealth;
	public int affectTicks;
	public int worldTicks;
	public float bodyRotDegrees;
	/** 眼睛光柱用的实体插值坐标（原实现直接从 LiveEntity 取）。 */
	public double entityX;
	public double entityY;
	public double entityZ;
	public double prevEntityX;
	public double prevEntityY;
	public double prevEntityZ;
	/** 实体视线方向（原 entity.getViewVector(1.0F)）。 */
	public net.minecraft.world.phys.Vec3 lookVector = net.minecraft.world.phys.Vec3.ZERO;
	public boolean isAlive = true;
	/** 凋灵斯拉三颗头颅的偏航/俯仰（角度制）。 */
	public float[] headYRot = new float[0];
	public float[] headXRot = new float[0];
	public float[] headGauss = new float[0];
	/** 26.1.2: 雪傀儡泰坦头顶的南瓜方块（原版方块模型改为预解析进 RenderState）。 */
	public final BlockModelRenderState pumpkinBlock = new BlockModelRenderState();
}
