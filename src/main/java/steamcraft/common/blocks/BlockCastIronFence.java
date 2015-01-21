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
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.Material;
import net.minecraft.world.IBlockAccess;
import steamcraft.common.InitBlocks;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

/**
 * @author Surseance
 * 
 */
public class BlockCastIronFence extends BlockFence
{
	public BlockCastIronFence()
	{
		super(ModInfo.PREFIX + "metal/" + "blockCastIron", Material.iron);
		this.setHardness(4.5F);
		this.setResistance(20.0F);
		this.setStepSound(Block.soundTypeMetal);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public boolean canConnectFenceTo(final IBlockAccess world, final int x, final int y, final int z)
	{
		final Block block = world.getBlock(x, y, z);

		if((block != this) && (block != InitBlocks.blockCastIronGate))
			return (block != null) && block.getMaterial().isOpaque() && block.renderAsNormalBlock() ? block.getMaterial() != Material.cactus : false;
		else
			return true;
	}
}
