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

/**
 * @author Surseance
 *
 */
public class ItemTeaSeed extends BaseItem implements IPlantable
{
	public ItemTeaSeed()
	{
		super();
		this.setMaxStackSize(64);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public boolean onItemUse(final ItemStack is, final EntityPlayer player, final World world, final int x, final int y, final int z, final int side,
			final float hitX, final float hitY, final float hitZ)
	{
		if (side != 1)
			return false;
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
				return false;
		}
		else
			return false;
	}

	@Override
	public Block getPlant(final IBlockAccess world, final int x, final int y, final int z)
	{
		return ConfigBlocks.blockTeaPlant;
	}

	@Override
	public int getPlantMetadata(final IBlockAccess world, final int x, final int y, final int z)
	{
		return 0;
	}

	@Override
	public EnumPlantType getPlantType(final IBlockAccess world, final int x, final int y, final int z)
	{
		return EnumPlantType.Crop;
	}
}
