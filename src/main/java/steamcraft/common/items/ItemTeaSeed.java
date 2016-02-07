
package steamcraft.common.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

import boilerplate.common.baseclasses.items.BaseItem;
import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitBlocks;

/**
 * @author Surseance
 *
 */
public class ItemTeaSeed extends BaseItem implements IPlantable
{
	public ItemTeaSeed()
	{
		super(Steamcraft.instance);
		this.setMaxStackSize(64);
		this.setUnlocalizedName("itemTeaSeed");
	}

	@Override
	public boolean onItemUse(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		if (side != 1)
			return false;
		else if (player.canPlayerEdit(x, y, z, side, is) && player.canPlayerEdit(x, y + 1, z, side, is))
		{
			Block soil = world.getBlock(x, y, z);

			if ((soil != null) && soil.canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z))
			{
				world.setBlock(x, y + 1, z, this.getPlant(world, x, y, z), this.getPlantMetadata(world, x, y, z), 2);
				player.inventory.consumeInventoryItem(is.getItem());
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z)
	{
		return InitBlocks.blockTeaPlant;
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
