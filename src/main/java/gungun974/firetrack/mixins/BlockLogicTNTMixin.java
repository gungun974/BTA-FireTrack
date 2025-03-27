package gungun974.firetrack.mixins;

import gungun974.firetrack.FireTrack;
import net.minecraft.core.block.BlockLogicTNT;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(remap = false, value = BlockLogicTNT.class)
public class BlockLogicTNTMixin {
	@Inject(
		method = "onBlockRightClicked",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/core/block/BlockLogicTNT;ignite(Lnet/minecraft/core/world/World;IIILnet/minecraft/core/entity/player/Player;Z)V")
	)
	void logTNTFireStriker(World world, int x, int y, int z, Player player, Side side, double xPlaced, double yPlaced, CallbackInfoReturnable<Boolean> cir) {
		if (player == null) {
			FireTrack.LOGGER.info("An activator ignite a tnt at ({}, {}, {})", x, y, z);
			return;
		}
		FireTrack.LOGGER.info("{} ignite a tnt at ({}, {}, {})", player.username, x, y, z);
	}

	@Inject(
		method = "ignite(Lnet/minecraft/core/world/World;IIILnet/minecraft/core/entity/player/Player;Z)V",
		at = @At("HEAD")
	)
	void logTNTIgnite(World world, int x, int y, int z, Player player, boolean sound, CallbackInfo ci) {
		FireTrack.LOGGER.info("a TNT have been ignited at ({}, {}, {})", x, y, z);
	}

	@Unique
	public void onBlockPlacedByMob(World world, int x, int y, int z, @NotNull Side side, Mob mob, double xPlaced, double yPlaced) {
		if (mob instanceof Player) {
			FireTrack.LOGGER.info("{} place a TNT at ({}, {}, {})", ((Player) mob).username, x, y, z);
		}
	}

	@Inject(
		method = "onBlockPlacedByWorld",
		at = @At("HEAD")
	)
	void logTNTIgnite(World world, int x, int y, int z, CallbackInfo ci) {
		FireTrack.LOGGER.info("a TNT have been placed at ({}, {}, {})", x, y, z);
	}
}
