package steamcraft.common.tiles;

import net.minecraft.block.BlockLog;
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
			if(this.worldObj.getBlock(this.xCoord - 1, this.yCoord, this.zCoord) == InitBlocks.blockPlankStack)
			{
				int meta = this.worldObj.getBlockMetadata(this.xCoord + 1, this.yCoord, this.zCoord);
				this.worldObj.setBlockToAir(this.xCoord + 1, this.yCoord, this.zCoord);

				BlockPlankStack stack = (BlockPlankStack) this.worldObj.getBlock(this.xCoord - 1, this.yCoord, this.zCoord);
				stack.setPlankMeta(meta);
				stack.setNumStoredPlanks(stack.getNumStoredPlanks() + 6);
			}
			else if(this.worldObj.isAirBlock(this.xCoord - 1, this.yCoord, this.zCoord))
			{
				int meta = this.worldObj.getBlockMetadata(this.xCoord + 1, this.yCoord, this.zCoord);
				this.worldObj.setBlockToAir(this.xCoord + 1, this.yCoord, this.zCoord);
				this.worldObj.setBlock(this.xCoord - 1, this.yCoord, this.zCoord, InitBlocks.blockPlankStack);
				BlockPlankStack stack = (BlockPlankStack) this.worldObj.getBlock(this.xCoord - 1, this.yCoord, this.zCoord);
				stack.setPlankMeta(meta);
			}
		}
	}
}
