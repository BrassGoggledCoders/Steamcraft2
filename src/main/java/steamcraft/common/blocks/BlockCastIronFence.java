/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [3/15/14, 11:15]
 */
package steamcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.lib.LibInfo;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockCastIronFence.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class BlockCastIronFence extends BlockFence
{

	/**
	 * Instantiates a new block cast iron fence.
	 */
	public BlockCastIronFence()
	{
		super(LibInfo.PREFIX + "blockCastIron", Material.iron);
		setBlockName("blockCastIronFence");
		setHardness(4.5F);
		setResistance(20.0F);
		setStepSound(Block.soundTypeMetal);
		setCreativeTab(Steamcraft.tabSC2);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.BlockFence#canConnectFenceTo(net.minecraft.world.IBlockAccess, int, int, int)
	 */
	@Override
	public boolean canConnectFenceTo(final IBlockAccess world, final int x,
			final int y, final int z)
	{
		final Block block = world.getBlock(x, y, z);

		if ((block != this) && (block != ConfigBlocks.blockCastIronGate))
		{
			return ((block != null) && (block.getMaterial().isOpaque())
					&& (block.renderAsNormalBlock()) ? block.getMaterial() != Material.cactus
					: false);
		}
		else
		{
			return true;
		}
	}
}
