package steamcraft.common.items.equipment;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.lib.MaterialHelper;

public class ItemModHoe extends ItemModTool
{
	protected ToolMaterial toolMaterial;

	public ItemModHoe(ToolMaterial toolMat)
	{
		super(1F, toolMat, blocksEffectiveAgainst);
		this.maxStackSize = 1;
		this.setMaxDamage(toolMat.getMaxUses());
		this.toolMaterial = toolMat;
	}

	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int i, int j, int k, int l, float f1, float f2, float f3)
	{
		if(!player.canPlayerEdit(i, j, k, l, stack))
		{
			return false;
		}

		Block i1 = world.getBlock(i, j, k);

		if(l != 0 && world.isAirBlock(i, j, k) && i1 == Blocks.grass || i1 == Blocks.dirt)
		{
			Block block = Blocks.farmland;
			world.playSoundEffect(i + 0.5F, j + 0.5F, k + 0.5F, block.stepSound.getBreakSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);

			if(world.isRemote)
			{
				return true;
			} else
			{
				world.setBlock(i, j, k, block);

				if(this.toolMaterial == MaterialHelper.TOOL_STEAM)
				{
					stack.damageItem(1 + Math.round(stack.getItemDamage() * 5 / 320), player);
					return true;
				}

				stack.damageItem(1, player);
				return true;
			}
		} else
		{
			return false;
		}
	}

	@SuppressWarnings("all")
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(!world.isRemote)
		{
			if(toolMaterial==MaterialHelper.TOOL_STEAM)
			{
				int i = 0;

				while(stack.getItemDamage()-repairAmount >= 0 && canConsumeSteamFromCanister(player))
				{
					stack.setItemDamage(stack.getItemDamage() - repairAmount);	
					i++;
				}
			}
		}

		return stack;
	}
}