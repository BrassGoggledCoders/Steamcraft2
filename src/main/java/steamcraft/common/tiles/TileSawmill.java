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
		if(blockMetadata == 0)
		{
			if(this.worldObj.getBlock(this.xCoord + 1, this.yCoord, this.zCoord) == Blocks.log)
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
			else if(this.worldObj.getBlock(this.xCoord + 1, this.yCoord, this.zCoord) == Blocks.log2)
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
		else if(blockMetadata == 1)
		{
			if(this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord + 1) == Blocks.log)
			{
				if(this.worldObj.isAirBlock(this.xCoord, this.yCoord, this.zCoord - 1))
				{
					int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord + 1);
					this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord + 1);
					this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord - 1, InitBlocks.blockPlankStack);
					BlockPlankStack stack = (BlockPlankStack) this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord - 1);
					worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord - 1, meta, 2);
				}
			}
			else if(this.worldObj.getBlock(this.xCoord + 1, this.yCoord, this.zCoord) == Blocks.log2)
			{
				if(this.worldObj.isAirBlock(this.xCoord - 1, this.yCoord, this.zCoord))
				{
					int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord + 1);
					this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord + 1);
					this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord - 1, InitBlocks.blockPlankStack);
					BlockPlankStack stack = (BlockPlankStack) this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord - 1);
					//TODO
					worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord - 1, meta + 4, 2);
				}
			}
		}
	}
}
