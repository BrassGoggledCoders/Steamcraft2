/*
 *
 */
package steamcraft.common.tiles;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import boilerplate.steamapi.machines.IHammerable;

// TODO: Auto-generated Javadoc
/**
 * The Class TileDropHammer.
 */
public class TileDropHammer extends TileEntity
{
	@Override
	public void updateEntity()
	{
		Block block = worldObj.getBlock(xCoord, yCoord+1, zCoord);
		//if(worldObj.getBlock(xCoord, yCoord+1, zCoord) != Blocks.air)
		//{
			if(block instanceof IHammerable)
			{
				IHammerable hammerable = (IHammerable)worldObj.getBlock(xCoord, yCoord+1, zCoord);

				if(hammerable.getOutput() != null)
				{
					ItemStack output = hammerable.getOutput();
					Item outputItem = output.getItem();
					int meta = worldObj.getBlockMetadata(xCoord, yCoord+1, zCoord);
					if(meta < 4)
					{
					worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord, yCoord+1, zCoord, new ItemStack(outputItem,2,meta)));
					worldObj.setBlockToAir(xCoord, yCoord+1, zCoord);
					}
				}
			}
	}
}