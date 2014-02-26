package common.steamcraft.common.block.tile;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;

import common.steamcraft.common.block.machines.BlockChemicalFurnace;
import common.steamcraft.common.item.ModItems;

public class TileEntityChemicalFurnace extends TileEntityMachine
{
	private static final int[] slots_top = new int[] {0};
	private static final int[] slots_bottom = new int[] {2, 1};
	private static final int[] slots_sides = new int[] {1};
	public int furnaceBurnTime;
	public int currentItemBurnTimea;
	public int currentItemBurnTimeb;
	public int furnaceCookTime;

	public TileEntityChemicalFurnace()
	{
		inventory = new ItemStack[4];
		furnaceBurnTime = 0;
		currentItemBurnTimea = 0;
		currentItemBurnTimeb = 0;
		furnaceCookTime = 0;
	}

	@Override
	public String getInvName()
	{
		return "Chemical Furnace";
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
	
		furnaceBurnTime = nbt.getShort("BurnTime");
		furnaceCookTime = nbt.getShort("CookTime");
		currentItemBurnTimea = getItemBurnTime(inventory[1]);
		currentItemBurnTimeb = getItemBurnTime(inventory[3]);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		nbt.setShort("BurnTime", (short)furnaceBurnTime);
		nbt.setShort("CookTime", (short)furnaceCookTime);
	}

	public int getCookProgressScaled(int i)
	{
		return (furnaceCookTime * i) / 100;
	}

	public int getBurnTimeRemainingScaled(int i)
	{
		if(currentItemBurnTimea == 0)
		{
			currentItemBurnTimea = 100;
		}
		if(currentItemBurnTimeb == 0)
		{
			currentItemBurnTimeb = 100;
		}

		return (furnaceBurnTime * i) / (currentItemBurnTimea + currentItemBurnTimeb);
	}

	public boolean isBurning()
	{
		return furnaceBurnTime > 0;
	}

	@Override
	public void updateEntity()
	{
		boolean flag = furnaceBurnTime > 0;
		boolean flag1 = false;

		if(furnaceBurnTime > 0)
		{
			furnaceBurnTime--;
		}
		if(!worldObj.isRemote)
		{
			if(furnaceBurnTime == 0 && canSmelt())
			{
				if(inventory[1] != null && inventory[3] != null)
				{
					if(inventory[1].itemID != inventory[3].itemID)
					{
						currentItemBurnTimea = getItemBurnTime(inventory[1]);
						currentItemBurnTimeb = getItemBurnTime(inventory[3]);

						if(currentItemBurnTimea > 0 && currentItemBurnTimeb > 0)
						{
							furnaceBurnTime = currentItemBurnTimea + currentItemBurnTimeb;
							flag1 = true;

							if(inventory[1] != null && inventory[3] != null)
							{
								if(inventory[1].getItem().hasContainerItem())
								{
									inventory[1] = new ItemStack(inventory[1].getItem().getContainerItem());
								} else
								{
									inventory[1].stackSize--;
								}
								if(inventory[3].getItem().hasContainerItem())
								{
									inventory[3] = new ItemStack(inventory[3].getItem().getContainerItem());
								} else
								{
									inventory[3].stackSize--;
								}
								if(inventory[1].stackSize == 0)
								{
									inventory[1] = null;
								}
								if(inventory[3].stackSize == 0)
								{
									inventory[3] = null;
								}
							}
						}
					}
				}
			}
			if(isBurning() && canSmelt())
			{
				furnaceCookTime++;

				if(furnaceCookTime == 100)
				{
					furnaceCookTime = 0;
					smeltItem();
					flag1 = true;
				}
			} else
			{
				furnaceCookTime = 0;
			}
			if(flag != (furnaceBurnTime > 0))
			{
				flag1 = true;
				BlockChemicalFurnace.updateFurnaceBlockState(furnaceBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
			}
		}
		if(flag1)
		{
			onInventoryChanged();
		}
	}

	private boolean canSmelt()
	{
		if(inventory[0] == null)
		{
			return false;
		}

		ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(inventory[0]);

		if(stack == null)
		{
			return false;
		}
		if(inventory[2] == null)
		{
			return true;
		}
		if(!inventory[2].isItemEqual(stack))
		{
			return false;
		}
		if(inventory[2].stackSize < getInventoryStackLimit() && inventory[2].stackSize < inventory[2].getMaxStackSize())
		{
			return true;
		}

		return inventory[2].stackSize < stack.getMaxStackSize();
	}

	public void smeltItem()
	{
		if(!canSmelt())
		{
			return;
		}

		ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(inventory[0]);

		if(inventory[2] == null)
		{
			inventory[2] = stack.copy();
		} else if(inventory[2].itemID == stack.itemID)
		{
			inventory[2].stackSize += stack.stackSize;
		}
		if(inventory[0].getItem().hasContainerItem())
		{
			inventory[0] = new ItemStack(inventory[0].getItem().getContainerItem());
		} else
		{
			inventory[0].stackSize--;
		}
		if(inventory[0].stackSize <= 0)
		{
			inventory[0] = null;
		}
	}

	private static int getItemBurnTime(ItemStack stack)
	{
		if(stack == null)
		{
			return 0;
		}

		int i = stack.getItem().itemID;

		if(i == ModItems.chemicSalt.itemID)
		{
			return 1000;
		}
		if(i == Item.sugar.itemID)
		{
			return 20;
		}
		if(i == Item.gunpowder.itemID)
		{
			return 100;
		}
		if(i == ModItems.bornite.itemID)
		{
			return 200;
		}
		if(i == ModItems.phosphorus.itemID)
		{
			return 1600;
		}
		if(i == Item.glowstone.itemID)
		{
			return 3200;
		}

		return 0;
	}

	public static boolean isItemFuel(ItemStack par0ItemStack)
	{
		return getItemBurnTime(par0ItemStack) > 0;
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return par1 == 2 ? false : (par1 == 1 ? isItemFuel(par2ItemStack) : true);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return par1 == 0 ? slots_bottom : (par1 == 1 ? slots_top : slots_sides);
	}

	@Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return this.isItemValidForSlot(par1, par2ItemStack);
	}

	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return par3 != 0 || par1 != 1 || par2ItemStack.itemID == Item.bucketEmpty.itemID;
	}
}