package common.steamcraft.common.block.tile;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;

import common.steamcraft.common.block.machines.BlockSteamFurnace;

public class TileEntitySteamFurnace extends TileEntityMachine
{
	private static final int[] slots_top = new int[] {0};
	private static final int[] slots_bottom = new int[] {2, 1};
	private static final int[] slots_sides = new int[] {1};
	public int furnaceBurnTime;
	public int currentItemBurnTime;
	public int furnaceCookTime;
	public int waterLevel;

	public TileEntitySteamFurnace()
	{
		inventory = new ItemStack[4];
		furnaceBurnTime = 0;
		currentItemBurnTime = 0;
		furnaceCookTime = 0;
		waterLevel = 0;
	}

	@Override
	public String getInvName()
	{
		return "Steam Furnace";
	}

	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		furnaceBurnTime = nbt.getShort("BurnTime");
		furnaceCookTime = nbt.getShort("CookTime");
		currentItemBurnTime = getItemBurnTime(inventory[1]);
		waterLevel = nbt.getShort("WaterLevel");
	}

	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setShort("BurnTime", (short)furnaceBurnTime);
		nbt.setShort("CookTime", (short)furnaceCookTime);
		nbt.setShort("WaterLevel", (short)waterLevel);
	}

	public int getCookProgressScaled(int i)
	{
		return (furnaceCookTime * i) / 600;
	}

	public int getWaterScaled(int i)
	{
		if(waterLevel > 0){
			return (int)(Math.ceil((double)((waterLevel * i) / 4096)));
		}else{
			return -2;
		}
	}

	public int getBurnTimeRemainingScaled(int i)
	{
		if(currentItemBurnTime == 0)
		{
			currentItemBurnTime = 600;
		}
		return (furnaceBurnTime * i) / currentItemBurnTime;
	}

	public boolean isBurning()
	{
		return furnaceBurnTime > 0;
	}

	public int getWater()
	{
		return waterLevel;
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
			if(isBurning())
			{
				if(waterLevel == 1)
				{
					BlockSteamFurnace.playSound(worldObj, xCoord, yCoord, zCoord, "random.fizz");
				}

				waterLevel --;
			}
			if(isBurning() && canSmelt())
			{
				if(waterLevel > 0)
				{
					furnaceCookTime+=4;
				} else
				{
					furnaceCookTime+=3;
				}
				if(waterLevel > 4096)
				{
					waterLevel = 4096;
				}
				if(waterLevel < 0)
				{
					waterLevel = 0;
				}

				ItemStack waterstack = new ItemStack(Item.bucketWater, 1);

				if(inventory[3] != null)
				{
					if(waterLevel <= 0 && inventory[3].itemID == waterstack.itemID)
					{
						inventory[3].itemID = Item.bucketEmpty.itemID;
						waterLevel = 4096;
						BlockSteamFurnace.playSound(worldObj, xCoord, yCoord, zCoord, "random.fizz");
					}
				}
				if(furnaceCookTime >= 600)
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
				BlockSteamFurnace.updateFurnaceBlockState(furnaceBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
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

		ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(this.inventory[0]);

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

		if(i < 256 && Block.blocksList[i].blockMaterial == Material.wood)
		{
			return 300;
		}
		if(i == Item.stick.itemID)
		{
			return 100;
		}
		if(i == Item.coal.itemID)
		{
			return 1600;
		}
		if(i == Item.bucketLava.itemID)
		{
			return 20000;
		}

		return i != Block.sapling.blockID ? 0 : 100;
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