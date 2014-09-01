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
import boilerplate.steamapi.block.IHammerable;

/**
 * @author warlordjones
 *
 */
public class TileDropHammer extends TileEntity
{
	@Override
	public void updateEntity()
	{
		Block block = this.worldObj.getBlock(this.xCoord, this.yCoord + 1, this.zCoord);
		int meta = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord + 1, this.zCoord);
		// if(worldObj.getBlock(xCoord, yCoord+1, zCoord) != Blocks.air)
		// {
		if(block instanceof IHammerable)
		{
			IHammerable hammerable = (IHammerable) this.worldObj.getBlock(this.xCoord, this.yCoord + 1, this.zCoord);

			if(hammerable.getOutput(meta) != null)
			{
				ItemStack output = hammerable.getOutput(meta);
				Item outputItem = output.getItem();
				if(!worldObj.isRemote)
				{
					this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.xCoord, this.yCoord + 1, this.zCoord, new ItemStack(outputItem,
						1, meta)));
					this.worldObj.spawnEntityInWorld(new EntityItem(this.worldObj, this.xCoord, this.yCoord + 1, this.zCoord, new ItemStack(outputItem,
						1, meta)));
				}
				this.worldObj.setBlockToAir(this.xCoord, this.yCoord + 1, this.zCoord);
			}
		}
	}
}