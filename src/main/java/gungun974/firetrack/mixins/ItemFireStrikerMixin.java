package gungun974.firetrack.mixins;

import gungun974.firetrack.FireTrack;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemFireStriker;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(remap = false, value = ItemFireStriker.class)
public class ItemFireStrikerMixin {
	@Inject(
		method = "onUseItemOnBlock",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;setBlockWithNotify(IIII)Z")
	)
	void logFireStriker(ItemStack itemstack, Player player, World world, int x, int y, int z, Side side, double xPlaced, double yPlaced, CallbackInfoReturnable<Boolean> cir) {
		if (player == null) {
			FireTrack.LOGGER.info("An activator use fire striker at ({}, {}, {})", x, y, z);
			return;
		}
		FireTrack.LOGGER.info("{} use fire striker at ({}, {}, {})", player.username, x, y, z);
	}
}
