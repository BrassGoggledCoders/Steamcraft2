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
		if(worldObj.getBlock(xCoord + 1, yCoord, zCoord) instanceof BlockLog)
		{
			if(worldObj.getBlock(xCoord -1, yCoord, zCoord) == InitBlocks.blockPlankStack)
			{
				BlockPlankStack stack = (BlockPlankStack) worldObj.getBlock(xCoord - 1, yCoord, zCoord);
				stack.setNumStoredPlanks(stack.getNumStoredPlanks() + 1);
				worldObj.setBlockToAir(xCoord + 1, yCoord, zCoord);
			}
			else
			{
				worldObj.setBlock(xCoord - 1, yCoord, zCoord, InitBlocks.blockPlankStack);
				worldObj.setBlockToAir(xCoord + 1, yCoord, zCoord);
			}
		}
	}
}
