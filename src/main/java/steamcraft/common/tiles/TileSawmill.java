package steamcraft.common.tiles;

import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNewLog;
import net.minecraft.tileentity.TileEntity;
import steamcraft.common.InitBlocks;
import steamcraft.common.blocks.BlockPlankStack;

public class TileSawmill extends TileEntity
{
	@Override
	public void updateEntity()
	{
		if(this.worldObj.getBlock(this.xCoord + 1, this.yCoord, this.zCoord) instanceof BlockLog)
		{
			if(this.worldObj.isAirBlock(this.xCoord - 1, this.yCoord, this.zCoord))
			{
				int meta = this.worldObj.getBlockMetadata(this.xCoord + 1, this.yCoord, this.zCoord);
				this.worldObj.setBlockToAir(this.xCoord + 1, this.yCoord, this.zCoord);
				this.worldObj.setBlock(this.xCoord - 1, this.yCoord, this.zCoord, InitBlocks.blockPlankStack);
				BlockPlankStack stack = (BlockPlankStack) this.worldObj.getBlock(this.xCoord - 1, this.yCoord, this.zCoord);
				worldObj.setBlockMetadataWithNotify(xCoord - 1, yCoord, zCoord, meta, 2);
			}
		}
		else if(this.worldObj.getBlock(this.xCoord + 1, this.yCoord, this.zCoord) instanceof BlockNewLog)
		{
			if(this.worldObj.isAirBlock(this.xCoord - 1, this.yCoord, this.zCoord))
			{
				int meta = this.worldObj.getBlockMetadata(this.xCoord + 1, this.yCoord, this.zCoord);
				this.worldObj.setBlockToAir(this.xCoord + 1, this.yCoord, this.zCoord);
				this.worldObj.setBlock(this.xCoord - 1, this.yCoord, this.zCoord, InitBlocks.blockPlankStack);
				BlockPlankStack stack = (BlockPlankStack) this.worldObj.getBlock(this.xCoord - 1, this.yCoord, this.zCoord);
				//TODO
				worldObj.setBlockMetadataWithNotify(xCoord - 1, yCoord, zCoord, meta + 4, 2);
			}
		}
	}
}
