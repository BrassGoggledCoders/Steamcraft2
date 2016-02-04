
package steamcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

import steamcraft.common.Steamcraft;

/**
 * @author warlordjones
 *
 */
public class BlockCustomStairs extends BlockStairs
{
	public BlockCustomStairs(Block block)
	{
		super(block, 0);
		this.setCreativeTab(Steamcraft.tabSC2);
		this.useNeighborBrightness = true;
	}

	public BlockCustomStairs(Block block, int metadata)
	{
		super(block, metadata);
		this.setCreativeTab(Steamcraft.tabSC2);
		this.useNeighborBrightness = true;
	}
}
