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
package steamcraft.common.tiles;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;

import steamcraft.common.blocks.BlockPlankStack;
import steamcraft.common.init.InitBlocks;

public class TileSawmill extends TileEntity
{
	@Override
	public void updateEntity()
	{
		int logPosX = this.xCoord;
		int logPosZ = this.zCoord;
		int plankPosX = this.xCoord;
		int plankPosZ = this.zCoord;
		switch(this.blockMetadata)
		{
			case 0:
				logPosX = this.xCoord - 1;
				plankPosX = this.xCoord + 1;
				break;
			case 1:
				logPosX = this.xCoord + 1;
				plankPosX = this.xCoord - 1;
				break;
			case 2:
				logPosZ = this.zCoord - 1;
				plankPosZ = this.zCoord + 1;
				break;
			case 3:
				logPosZ = this.zCoord + 1;
				plankPosZ = this.zCoord - 1;
				break;
		}
		if((this.worldObj.getBlock(logPosX, this.yCoord, logPosZ) == Blocks.log) || (this.worldObj.getBlock(logPosX, this.yCoord, logPosZ) == Blocks.log2))
		{
			if(this.worldObj.isAirBlock(plankPosX, this.yCoord, plankPosZ))
			{
				int meta = this.worldObj.getBlockMetadata(logPosX, this.yCoord, logPosZ);
				this.worldObj.setBlockToAir(logPosX, this.yCoord, logPosZ);
				this.worldObj.setBlock(plankPosX, this.yCoord, plankPosZ, InitBlocks.blockPlankStack);
				BlockPlankStack stack = (BlockPlankStack) this.worldObj.getBlock(plankPosX, this.yCoord, plankPosZ);
				if(this.worldObj.getBlock(logPosX, this.yCoord, logPosZ) == Blocks.log2)
					this.worldObj.setBlockMetadataWithNotify(this.xCoord - 1, this.yCoord, this.zCoord, meta + 4, 2);
				else
					this.worldObj.setBlockMetadataWithNotify(this.xCoord - 1, this.yCoord, this.zCoord, meta, 2);
			}
		}
	}
}
