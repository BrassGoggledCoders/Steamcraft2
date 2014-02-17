package common.steamcraft.common.item;

import common.steamcraft.common.block.ModPowerBlocks;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCopperWire extends ItemMod
{
	public ItemCopperWire(int id)
	{
		super(id);
	}
	
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int i, int j, int k, int meta, float par8, float par9, float par10)
    {
        if(world.getBlockId(i, j, k) != Block.snow.blockID)
        {
            if(meta == 0)
            {
                --j;
            }
            if(meta == 1)
            {
                ++j;
            }
            if(meta == 2)
            {
                --k;
            }
            if(meta == 3)
            {
                ++k;
            }
            if(meta == 4)
            {
                --i;
            }
            if(meta == 5)
            {
                ++i;
            }
            if(!world.isAirBlock(i, j, k))
            {
                return false;
            }
        }
        if(!player.canPlayerEdit(i, j, k, meta, stack))
        {
            return false;
        } else
        {
            if(ModPowerBlocks.copperWire.canPlaceBlockAt(world, i, j, k))
            {
                --stack.stackSize;
                world.setBlock(i, j, k, ModPowerBlocks.copperWire.blockID);
            }

            return true;
        }
    }
}