package common.steamcraft.common.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class ItemTeaSeeds extends ItemMod implements IPlantable
{
	private int blockType;
	private int soilBlockID;

	public ItemTeaSeeds(int id, int plantID, int soilID)
	{
		super(id);
		this.blockType = plantID;
		this.soilBlockID = soilID;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int i, int j, int k, int l, float par8, float par9, float par10)
	{
		if(l != 1)
		{
			return false;
		} else if (player.canPlayerEdit(i, j, k, l, stack) && player.canPlayerEdit(i, j + 1, k, l, stack))
		{
			int i1 = world.getBlockId(i, j, k);
			Block soil = Block.blocksList[i1];

			if(soil != null && soil.canSustainPlant(world, i, j, k, ForgeDirection.UP, this) && world.isAirBlock(i, j + 1, k))
			{
				world.setBlock(i, j + 1, k, this.blockType);
				--stack.stackSize;
				return true;
			} else
			{
				return false;
			}
		} else
		{
			return false;
		}
	}

	@Override
	public int getPlantID(World world, int x, int y, int z)
	{
		return blockType;
	}

	@Override
	public int getPlantMetadata(World world, int x, int y, int z)
	{
		return 0;
	}

	@Override
	public EnumPlantType getPlantType(World world, int x, int y, int z) 
	{
		return EnumPlantType.Crop;
	}
}