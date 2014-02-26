/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * File created @ [Jan 31, 2014, 3:48:02 PM]
 */
package common.steamcraft.common.block;

import common.steamcraft.common.lib2.LibInfo;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;

/**
 * @author MrArcane111
 *
 */
public class BlockCastIronFence <B extends BlockMod> extends BlockFence
{
	public BlockCastIronFence(int id)
	{
		super(id, LibInfo.SC2_PREFIX + "castirongate", Material.iron);
	}

	@Override
	public boolean canConnectFenceTo(IBlockAccess iBlockAccess, int i, int j, int k)
	{
		int bid = iBlockAccess.getBlockId(i, j, k);

		if(bid != this.blockID && bid != ModBlocks.railingCastIron.blockID)
		{
			Block block = Block.blocksList[bid];
			return block != null && block.blockMaterial.isOpaque() && block.renderAsNormalBlock() ? block.blockMaterial != Material.pumpkin : false;
		} else
		{
			return true;
		}
	}
}