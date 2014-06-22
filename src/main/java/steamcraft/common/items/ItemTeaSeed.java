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

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigBlocks;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemTeaSeed.
 * 
 * @author Surseance (Johnny Eatmon)
 */
public class ItemTeaSeed extends BaseItem implements IPlantable
{
	/**
	 * Instantiates a new item tea seed.
	 */
	public ItemTeaSeed()
	{
		super();
		setMaxStackSize(64);
		setCreativeTab(Steamcraft.tabSC2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.item.Item#onItemUse(net.minecraft.item.ItemStack,
	 * net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, int,
	 * int, int, int, float, float, float)
	 */
	@Override
	public boolean onItemUse(final ItemStack is, final EntityPlayer player, final World world, final int x, final int y, final int z, final int side,
			final float hitX, final float hitY, final float hitZ)
	{
		if (side != 1)
		{
			return false;
		}
		else if (player.canPlayerEdit(x, y, z, side, is) && player.canPlayerEdit(x, y + 1, z, side, is))
		{
			world.getBlock(x, y, z);
			final Block soil = Blocks.farmland;

			if (soil != null && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z))
			{
				world.setBlockToAir(x, y + 1, z);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraftforge.common.IPlantable#getPlant(net.minecraft.world.
	 * IBlockAccess, int, int, int)
	 */
	@Override
	public Block getPlant(final IBlockAccess world, final int x, final int y, final int z)
	{
		return ConfigBlocks.blockTeaPlant;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraftforge.common.IPlantable#getPlantMetadata(net.minecraft.world
	 * .IBlockAccess, int, int, int)
	 */
	@Override
	public int getPlantMetadata(final IBlockAccess world, final int x, final int y, final int z)
	{
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraftforge.common.IPlantable#getPlantType(net.minecraft.world
	 * .IBlockAccess, int, int, int)
	 */
	@Override
	public EnumPlantType getPlantType(final IBlockAccess world, final int x, final int y, final int z)
	{
		return EnumPlantType.Crop;
	}
}
