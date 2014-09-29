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
				int meta = worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord);
				worldObj.setBlockToAir(xCoord + 1, yCoord, zCoord);

				BlockPlankStack stack = (BlockPlankStack) worldObj.getBlock(xCoord - 1, yCoord, zCoord);
				stack.setPlankMeta(meta);
				stack.setNumStoredPlanks(stack.getNumStoredPlanks() + 6);
			}
			else if(worldObj.isAirBlock(xCoord - 1, yCoord, zCoord))
			{
				int meta = worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord);
				worldObj.setBlockToAir(xCoord + 1, yCoord, zCoord);
				worldObj.setBlock(xCoord - 1, yCoord, zCoord, InitBlocks.blockPlankStack);
				BlockPlankStack stack = (BlockPlankStack) worldObj.getBlock(xCoord - 1, yCoord, zCoord);
				stack.setPlankMeta(meta);
			}
		}
	}
}
