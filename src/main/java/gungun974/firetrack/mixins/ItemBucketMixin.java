package gungun974.firetrack.mixins;

import com.llamalad7.mixinextras.sugar.Local;
import gungun974.firetrack.FireTrack;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.entity.TileEntityActivator;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemBucket;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Direction;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(remap = false, value = ItemBucket.class)
public class ItemBucketMixin {
	@Shadow
	@Final
	private @Nullable Block<?> blockToPlace;

	@Inject(
		method = "onUseItem",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;setBlockAndMetadataWithNotify(IIIII)Z")
	)
	void logLavaBucket(ItemStack stack, World world, Player player, CallbackInfoReturnable<ItemStack> cir, @Local(ordinal = 0) int x, @Local(ordinal = 1) int y, @Local(ordinal = 2) int z) {
		if (blockToPlace != null && blockToPlace.id() == Blocks.FLUID_LAVA_FLOWING.id()) {
			if (player == null) {
				FireTrack.LOGGER.info("Something place lava with a bucket at ({}, {}, {})", x, y, z);
				return;
			}
			FireTrack.LOGGER.info("{} place lava with a bucket at ({}, {}, {})", player.username, x, y, z);
		}
	}

	@Inject(
		method = "onUseByActivator",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;setBlockWithNotify(IIII)Z")
	)
	void logLavaBucketActivator(ItemStack itemStack, TileEntityActivator activatorBlock, World world, Random random, int x, int y, int z, double offX, double offY, double offZ, Direction direction, CallbackInfo ci) {
		if (blockToPlace != null && blockToPlace.id() == Blocks.FLUID_LAVA_FLOWING.id()) {
			FireTrack.LOGGER.info("An activator place lava with a bucket at ({}, {}, {})", x, y, z);
		}
	}
}
