package net.byAqua3.thetitansneo.item;

import net.byAqua3.thetitansneo.entity.titan.EntityTitan;
import net.byAqua3.thetitansneo.loader.TheTitansNeoSounds;
import net.byAqua3.thetitansneo.loader.TheTitansNeoTiers;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraft.server.level.ServerLevel;
public class ItemAdminiumHoe extends HoeItem {

	public ItemAdminiumHoe(Properties properties) {
		super(TheTitansNeoTiers.ADMINIUM, 1000000000.0F, 1024.0F, properties.hoe(TheTitansNeoTiers.ADMINIUM, 1000000000.0F, 1024.0F));
		// 26.1.2：ToolMaterial 为 record，属性已并入 Properties.hoe(...)。
		// 原 createAttributes(1000000000.0F, Float.MAX_VALUE) 的攻速收敛为 1024.0F，避免修饰符溢出。
	}
	
	@Override
	public boolean isFoil(ItemStack stack) {
        return true;
    }

	@Override
	public void hurtEnemy(ItemStack stack, LivingEntity entity, LivingEntity attacker) {
		if (entity != null) {
			if (entity.getBbHeight() >= 6.0F || entity instanceof EntityTitan || !entity.onGround()) {
				entity.playSound(TheTitansNeoSounds.TITAN_PUNCH.get(), 10.0F, 1.0F);
				entity.hurtServer((ServerLevel) entity.level(), entity.damageSources().mobAttack(attacker), 1.0E9F);
			}
		}
		super.hurtEnemy(stack, entity, attacker);
	}
	
	@Override
	public InteractionResult useOn(UseOnContext context) {
		Level level = context.getLevel();
		Player player = context.getPlayer();
		BlockPos blockPos = context.getClickedPos();
		BlockState blockState = level.getBlockState(blockPos);
		Block block = blockState.getBlock();
		if (level.getBlockState(blockPos.atY(blockPos.getY() + 1)).isAir() && (block == Blocks.SPONGE || block == Blocks.WET_SPONGE || block == Blocks.GRAVEL)) {
			level.playSound(player, blockPos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
			level.setBlockAndUpdate(blockPos, Blocks.FARMLAND.defaultBlockState());
			if (!level.isClientSide()) {
				if (player != null) {
					context.getItemInHand().hurtAndBreak(1, player, context.getHand());
				}
			}
			return (level.isClientSide() ? InteractionResult.SUCCESS : InteractionResult.SUCCESS_SERVER);
		}
		return super.useOn(context);
	}
}
