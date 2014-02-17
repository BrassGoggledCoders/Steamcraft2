package common.steamcraft.mod.common.item;

import java.util.List;

import common.steamcraft.mod.client.core.helper.ClientHelper;
import common.steamcraft.mod.common.lib.MaterialMod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHoeMod extends ItemMod
{
	protected EnumToolMaterial toolMaterial;

	public ItemHoeMod(int id, EnumToolMaterial toolMat)
	{
		super(id);
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
		
		int i1 = world.getBlockId(i, j, k);
		int j1 = world.getBlockId(i, j + 1, k);
		
		if(l != 0 && j1 == 0 && i1 == Block.grass.blockID || i1 == Block.dirt.blockID)
		{
			Block block = Block.tilledField;
			world.playSoundEffect((double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), block.stepSound.getStepSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
			
			if(world.isRemote)
			{
				return true;
			} else
			{
				world.setBlock(i, j, k, block.blockID);
				
				if(this.toolMaterial == MaterialMod.STEAM_TOOL)
				{
					stack.damageItem(1 + (int)Math.round(stack.getItemDamage() * 5 / 320), player);
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

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(!world.isRemote)
		{
			if(toolMaterial==MaterialMod.INSTANCE.STEAM_TOOL)
			{
				if(player.inventory.hasItem(ModItems.canisterSteam.itemID))
				{
					int i = 0;

					while(stack.getItemDamage() != 0 && i < 36)
					{
						if(player.inventory.mainInventory[i]!=null &&
								player.inventory.mainInventory[i].itemID == ModItems.canisterSteam.itemID)
						{
							while(player.inventory.mainInventory[i].getItemDamage() < ModItems.canisterSteam.getMaxDamage() && stack.getItemDamage() > 0)
							{
								player.inventory.mainInventory[i].damageItem(1, player);
								stack.setItemDamage(stack.getItemDamage() - 1);
							}
						}
						
						i++;
					}
				}
			}
		}

		return stack;
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool) 
	{
		if(toolMaterial==MaterialMod.INSTANCE.STEAM_TOOL)
		{
			if(!ClientHelper.isShiftKeyDown())
			{
				list.add(ClientHelper.shiftForInfo);
				return;
			}

			list.add("\u00A77"+ (this.getMaxDamage() - itemStack.getItemDamage()) + "/" + this.getMaxDamage() + " steam");
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}
}