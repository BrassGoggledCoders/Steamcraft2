
package steamcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitBlocks;

/**
 * @author Surseance
 *
 */
public class BlockCastIronFence extends BlockCustomFence
{
	public BlockCastIronFence()
	{
		super("metal/blockCastIron", Material.iron);
		this.setHardness(4.5F);
		this.setResistance(20.0F);
		this.setStepSound(Block.soundTypeMetal);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public boolean canConnectFenceTo(final IBlockAccess world, final int x, final int y, final int z)
	{
		final Block block = world.getBlock(x, y, z);

		if ((block != this) && (block != InitBlocks.blockCastIronGate))
			return (block != null) && block.getMaterial().isOpaque() && block.renderAsNormalBlock() && (block.getMaterial() != Material.cactus);
		else
			return true;
	}
}
