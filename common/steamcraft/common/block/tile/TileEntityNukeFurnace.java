package common.steamcraft.common.block.tile;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;

import common.steamcraft.common.block.machines.BlockNukeFurnace;
import common.steamcraft.common.item.ModItems;

public class TileEntityNukeFurnace extends TileEntityMachine
{
	private static final int[] slots_top = new int[] {0};
    private static final int[] slots_bottom = new int[] {2, 1};
    private static final int[] slots_sides = new int[] {1};
	public int furnaceBurnTime;
	public int currentItemBurnTime;
	public int furnaceCookTime;
	public int furnaceHeat;
	
	public TileEntityNukeFurnace()
	{
		inventory = new ItemStack[3];
		furnaceBurnTime = 0;
		currentItemBurnTime = 0;
		furnaceCookTime = 0;
		furnaceHeat = 0;
	}

	@Override
	public String getInvName()
	{
		return "Nuclear Reactor";
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		furnaceBurnTime = nbt.getShort("BurnTime");
		furnaceCookTime = nbt.getShort("CookTime");
		furnaceHeat = nbt.getShort("Heat");
		currentItemBurnTime = getItemBurnTime(inventory[1]);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setShort("BurnTime", (short)furnaceBurnTime);
		nbt.setShort("CookTime", (short)furnaceCookTime);
		nbt.setShort("Heat", (short)furnaceHeat);
	}

	public int getCookProgressScaled(int i)
	{
		return (furnaceCookTime * i) / 20;
	}

	public int getHeatScaled(int i)
	{
		return (furnaceHeat * i) / 2560;
	}

	public int getBurnTimeRemainingScaled(int i)
	{
		if(currentItemBurnTime == 0)
		{
			currentItemBurnTime = 20;
		}

		return (furnaceBurnTime * i) / currentItemBurnTime;
	}

	public boolean isBurning()
	{
		return furnaceBurnTime > 0;
	}

	public void addHeat(int i)
	{
		furnaceHeat += i;
	}

	public int getHeat()
	{
		return furnaceHeat;
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
				currentItemBurnTime = furnaceBurnTime = getItemBurnTime(inventory[1]);

				if(furnaceBurnTime > 0)
				{
					flag1 = true;

					if(inventory[1] != null)
					{
						if(inventory[1].getItem().hasContainerItem())
						{
							inventory[1] = new ItemStack(inventory[1].getItem().getContainerItem());
						} else
						{
							inventory[1].stackSize--;
						}
						if(inventory[1].stackSize == 0)
						{
							inventory[1] = null;
						}
					}
				}
			}
			if(isBurning() && canSmelt())
			{
				furnaceCookTime++;

				if(furnaceCookTime == 20)
				{
					furnaceCookTime = 0;

					if(furnaceHeat <= 2560)
					{
						furnaceHeat += 40;
					}
					if(furnaceHeat > 2520 && furnaceHeat < 2560)
					{
						furnaceHeat = 2560;
					}

					smeltItem();
					flag1 = true;
				}
			} else
			{
				furnaceCookTime = 0;

				if(!isBurning() || !canSmelt())
				{
					if(furnaceHeat > 0)
					{
						furnaceHeat -= 1;
					}
				}
			}
			if(flag != (furnaceBurnTime > 0))
			{
				flag1 = true;
				BlockNukeFurnace.updateFurnaceBlockState(furnaceBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
			}
			if(furnaceHeat >= 2560)
			{
				BlockNukeFurnace.meltdown(worldObj, xCoord, yCoord, zCoord);
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
			inventory[2].stackSize++;
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
		return i != ModItems.uraniumPellet.itemID ? 0 : 3200;
	}
	
	public static boolean isItemFuel(ItemStack stack)
    {
        return getItemBurnTime(stack) > 0;
    }

	@Override
	public boolean isItemValidForSlot(int i, ItemStack stack)
    {
        return i == 2 ? false : (i == 1 ? isItemFuel(stack) : true);
    }

	@Override
    public int[] getAccessibleSlotsFromSide(int i)
    {
        return i == 0 ? slots_bottom : (i == 1 ? slots_top : slots_sides);
    }

    @Override
    public boolean canInsertItem(int i, ItemStack stack, int j)
    {
        return this.isItemValidForSlot(i, stack);
    }

    @Override
    public boolean canExtractItem(int i, ItemStack stack, int j)
    {
        return j != 0 || i != 1 || stack.itemID == Item.bucketEmpty.itemID;
    }
}