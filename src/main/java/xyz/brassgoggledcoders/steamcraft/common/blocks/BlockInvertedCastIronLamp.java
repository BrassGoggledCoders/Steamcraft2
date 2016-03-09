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
package xyz.brassgoggledcoders.steamcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.steamcraft.common.init.InitBlocks;

/**
 * @author Surseance
 *
 */
public class BlockInvertedCastIronLamp extends BlockCastIronLamp
{
	public BlockInvertedCastIronLamp(boolean powered)
	{
		super(powered);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int metadata, float hitX, float hitY, float hitZ, int side)
	{
		if ((side == 1) || (side == 3))
			world.setBlockMetadataWithNotify(x, y, z, 0, 2);
		if (side == 2)
			world.setBlockMetadataWithNotify(x, y, z, 1, 2);
		if (side == 4)
			world.setBlockMetadataWithNotify(x, y, z, 2, 2);
		if (side == 5)
			world.setBlockMetadataWithNotify(x, y, z, 3, 2);
		return metadata;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		if (!world.isRemote)
		{
			if (!this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.scheduleBlockUpdate(x, y, z, this, 4);
				world.setBlock(x, y, z, InitBlocks.blockInvertedCastIronLamp, world.getBlockMetadata(x, y, z), 2);
			}
			else if (this.powered && world.isBlockIndirectlyGettingPowered(x, y, z))
				world.setBlock(x, y, z, InitBlocks.blockInvertedCastIronLampOff, world.getBlockMetadata(x, y, z), 2);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block p_149695_5_)
	{
		if (!world.isRemote)
		{
			if (!this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.scheduleBlockUpdate(x, y, z, this, 4);
				world.setBlock(x, y, z, InitBlocks.blockInvertedCastIronLamp, world.getBlockMetadata(x, y, z), 2);
			}
			else if (this.powered && world.isBlockIndirectlyGettingPowered(x, y, z))
				world.setBlock(x, y, z, InitBlocks.blockInvertedCastIronLampOff, world.getBlockMetadata(x, y, z), 2);
		}
	}
}
