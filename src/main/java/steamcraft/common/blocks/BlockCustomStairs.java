/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
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
		// this.setLightOpacity(255);
		this.useNeighborBrightness = true;
	}
}
