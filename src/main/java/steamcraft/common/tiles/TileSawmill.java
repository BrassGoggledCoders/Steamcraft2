package steamcraft.common.tiles;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import steamcraft.common.InitBlocks;
import steamcraft.common.blocks.BlockPlankStack;

public class TileSawmill extends TileEntity
{
	@Override
	public void updateEntity()
	{
		int logPosX = xCoord;
		int logPosZ = zCoord;
		int plankPosX = xCoord;
		int plankPosZ = zCoord;
		switch(blockMetadata)
		{
			case 0: logPosX = xCoord - 1; plankPosX = xCoord + 1;
			break;
			case 1: logPosX = xCoord + 1; plankPosX = xCoord - 1;
			break;
			case 2: logPosZ = zCoord - 1; plankPosZ = zCoord + 1;
			break;
			case 3: logPosZ = zCoord + 1; plankPosZ = zCoord - 1;
			break;
		}
		if(this.worldObj.getBlock(logPosX, this.yCoord, logPosZ) == Blocks.log || this.worldObj.getBlock(logPosX, this.yCoord, logPosZ) == Blocks.log2)
		{
			if(this.worldObj.isAirBlock(plankPosX, this.yCoord, plankPosZ))
			{
				int meta = this.worldObj.getBlockMetadata(logPosX, this.yCoord, logPosZ);
				this.worldObj.setBlockToAir(logPosX, this.yCoord, logPosZ);
				this.worldObj.setBlock(plankPosX, this.yCoord, plankPosZ, InitBlocks.blockPlankStack);
				BlockPlankStack stack = (BlockPlankStack) this.worldObj.getBlock(plankPosX, this.yCoord, plankPosZ);
				if(this.worldObj.getBlock(logPosX, this.yCoord, logPosZ) == Blocks.log2)
				worldObj.setBlockMetadataWithNotify(xCoord - 1, yCoord, zCoord, meta + 4, 2);
				else worldObj.setBlockMetadataWithNotify(xCoord - 1, yCoord, zCoord, meta, 2);
			}
		}
	}
}
