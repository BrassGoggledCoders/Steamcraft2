package common.steamcraft.common.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemElectricLamp extends ItemMod
{
	private int spawnID;

	public ItemElectricLamp(int id, Block block)
	{
		super(id);
		this.spawnID = block.blockID;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int i, int j, int k, int l, float f1, float f2, float f3)
	{
		if(world.getBlockId(i, j, k) != Block.snow.blockID)
		{
			if(l == 0)
			{
				j--;
			}
			if(l == 1)
			{
				j++;
			}
			if(l == 2)
			{
				k--;
			}
			if(l == 3)
			{
				k++;
			}
			if(l == 4)
			{
				i--;
			}
			if(l == 5)
			{
				i++;
			}
			if(!world.isAirBlock(i, j, k))
			{
				return false;
			}
		}
		if(!player.canPlayerEdit(i, j, k, l, stack))
		{
			return false;
		}
		if(stack.stackSize == 0)
		{
			return false;
		}
		if(world.canPlaceEntityOnSide(spawnID, i, j, k, false, l, player, stack))
		{
			Block block = Block.blocksList[spawnID];

			if(world.setBlock(i, j, k, spawnID))
			{
				if(world.getBlockId(i, j, k) == spawnID)
				{
					Block.blocksList[spawnID].onBlockAdded(world, i, j, k);
					Block.blocksList[spawnID].onBlockPlacedBy(world, i, j, k, player, stack);
				}

				world.playSoundEffect((float)i + 0.5F, (float)j + 0.5F, (float)k + 0.5F, block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
				stack.stackSize--;
			}
		}
		
		return true;
	}
}