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

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import boilerplate.steamapi.machines.IHammerable;

/**
 * @author warlordjones
 *
 */
public class TileDropHammer extends TileEntity
{
	@Override
	public void updateEntity()
	{
		Block block = worldObj.getBlock(xCoord, yCoord + 1, zCoord);
		int meta = worldObj.getBlockMetadata(xCoord, yCoord + 1, zCoord);
		// if(worldObj.getBlock(xCoord, yCoord+1, zCoord) != Blocks.air)
		// {
		if (block instanceof IHammerable)
		{
			IHammerable hammerable = (IHammerable) worldObj.getBlock(xCoord, yCoord + 1, zCoord);

			if (hammerable.getOutput(meta) != null)
			{
				ItemStack output = hammerable.getOutput(meta);
				Item outputItem = output.getItem();
				worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord, yCoord + 1, zCoord, new ItemStack(outputItem, 1, meta)));
				worldObj.spawnEntityInWorld(new EntityItem(worldObj, xCoord, yCoord + 1, zCoord, new ItemStack(outputItem, 1, meta)));
				worldObj.setBlockToAir(xCoord, yCoord + 1, zCoord);
			}
		}
	}
}