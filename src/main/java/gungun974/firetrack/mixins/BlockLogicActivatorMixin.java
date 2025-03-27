package gungun974.firetrack.mixins;

import gungun974.firetrack.FireTrack;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicActivator;
import net.minecraft.core.block.BlockLogicVeryRotatable;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(remap = false, value = BlockLogicActivator.class)
public class BlockLogicActivatorMixin extends BlockLogicVeryRotatable {
	public BlockLogicActivatorMixin(Block<?> block, Material material) {
		super(block, material);
	}

	@Unique
	public void onBlockPlacedByMob(World world, int x, int y, int z, @NotNull Side side, Mob mob, double xPlaced, double yPlaced) {
		super.onBlockPlacedByMob(world, x, y, z, side, mob, xPlaced, yPlaced);
		if (mob instanceof Player) {
			FireTrack.LOGGER.info("{} place an activator at ({}, {}, {})", ((Player) mob).username, x, y, z);
		}
	}

	@Unique
	public void onBlockPlacedByWorld(World world, int x, int y, int z) {
		FireTrack.LOGGER.info("An activator have been placed at ({}, {}, {})", x, y, z);
	}
}
