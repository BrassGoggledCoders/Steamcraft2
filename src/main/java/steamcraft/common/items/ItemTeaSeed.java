/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [Mar 20, 2014, 10:11:46 AM]
 */
package steamcraft.common.items;

import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class ItemTeaSeed extends Item implements IPlantable
{
	public void reigsterIcons(IIconRegister ir)
	{
		this.itemIcon = ir.registerIcon(LibInfo.PREFIX + "itemTeaSeed");
	}
	
	public ItemTeaSeed()
	{
		this.setMaxStackSize(64);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if (side != 1)
			return false;
		else if (player.canPlayerEdit(x, y, z, side, is) && player.canPlayerEdit(x, y + 1, z, side, is))
		{
			Block block = world.getBlock(x, y, z);
			Block soil = Blocks.farmland;

			if (soil != null && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z))
			{
				world.setBlock(x, y + 1, z, Blocks.air); // XXX
				--is.stackSize;
				return true;
			} 
			else
			{
				return false;
			}
		} 
		else
		{
			return false;
		}
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z)
    {
    	return Blocks.bed; // XXX
    }

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z)
	{
		return 0;
	}
	
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z)
	{
		return EnumPlantType.Crop;
	}
}
