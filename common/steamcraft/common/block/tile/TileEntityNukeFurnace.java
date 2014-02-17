package common.steamcraft.common.block.tile;

import common.steamcraft.common.block.machines.BlockNukeFurnace;
import common.steamcraft.common.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityNukeFurnace extends TileEntity implements ISidedInventory
{
	private static final int[] slots_top = new int[] {0};
    private static final int[] slots_bottom = new int[] {2, 1};
    private static final int[] slots_sides = new int[] {1};
	private ItemStack furnaceItemStacks[];
	public int furnaceBurnTime;
	public int currentItemBurnTime;
	public int furnaceCookTime;
	public int furnaceHeat;
	private String field_94130_e;
	
	public TileEntityNukeFurnace()
	{
		furnaceItemStacks = new ItemStack[3];
		furnaceBurnTime = 0;
		currentItemBurnTime = 0;
		furnaceCookTime = 0;
		furnaceHeat = 0;
	}

	@Override
	public int getSizeInventory()
	{
		return furnaceItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int i)
	{
		return furnaceItemStacks[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j)
	{
		if(furnaceItemStacks[i] != null)
		{
			if(furnaceItemStacks[i].stackSize <= j)
			{
				ItemStack itemstack = furnaceItemStacks[i];
				furnaceItemStacks[i] = null;
				return itemstack;
			}

			ItemStack itemstack1 = furnaceItemStacks[i].splitStack(j);

			if(furnaceItemStacks[i].stackSize == 0)
			{
				furnaceItemStacks[i] = null;
			}

			return itemstack1;
		} else
		{
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack stack)
	{
		furnaceItemStacks[i] = stack;

		if(stack != null && stack.stackSize > getInventoryStackLimit())
		{
			stack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInvName()
	{
		return "Nuclear Reactor";
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int i)
	{
		if(this.furnaceItemStacks[i] != null)
		{
			ItemStack stack = this.furnaceItemStacks[i];
			this.furnaceItemStacks[i] = null;
			return stack;
		} else
		{
			return null;
		}
	}

	@Override
	public boolean isInvNameLocalized()
	{
		return this.field_94130_e != null && this.field_94130_e.length() > 0;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		NBTTagList nbttaglist = nbt.getTagList("Items");
		furnaceItemStacks = new ItemStack[getSizeInventory()];

		for(int i = 0; i < nbttaglist.tagCount(); i++)
		{
			NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
			byte byte0 = nbttagcompound1.getByte("Slot");

			if(byte0 >= 0 && byte0 < furnaceItemStacks.length)
			{
				furnaceItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}

		furnaceBurnTime = nbt.getShort("BurnTime");
		furnaceCookTime = nbt.getShort("CookTime");
		furnaceHeat = nbt.getShort("Heat");
		currentItemBurnTime = getItemBurnTime(furnaceItemStacks[1]);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setShort("BurnTime", (short)furnaceBurnTime);
		nbt.setShort("CookTime", (short)furnaceCookTime);
		nbt.setShort("Heat", (short)furnaceHeat);
		NBTTagList nbttaglist = new NBTTagList();
		for(int i = 0; i < furnaceItemStacks.length; i++)
		{
			if(furnaceItemStacks[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte)i);
				furnaceItemStacks[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		nbt.setTag("Items", nbttaglist);
	}

	@Override
	public int getInventoryStackLimit()
	{
		return 64;
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
				currentItemBurnTime = furnaceBurnTime = getItemBurnTime(furnaceItemStacks[1]);

				if(furnaceBurnTime > 0)
				{
					flag1 = true;

					if(furnaceItemStacks[1] != null)
					{
						if(furnaceItemStacks[1].getItem().hasContainerItem())
						{
							furnaceItemStacks[1] = new ItemStack(furnaceItemStacks[1].getItem().getContainerItem());
						} else
						{
							furnaceItemStacks[1].stackSize--;
						}
						if(furnaceItemStacks[1].stackSize == 0)
						{
							furnaceItemStacks[1] = null;
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
		if(furnaceItemStacks[0] == null)
		{
			return false;
		}

		ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(furnaceItemStacks[0]);

		if(stack == null)
		{
			return false;
		}
		if(furnaceItemStacks[2] == null)
		{
			return true;
		}
		if(!furnaceItemStacks[2].isItemEqual(stack))
		{
			return false;
		}
		if(furnaceItemStacks[2].stackSize < getInventoryStackLimit() && furnaceItemStacks[2].stackSize < furnaceItemStacks[2].getMaxStackSize())
		{
			return true;
		}

		return furnaceItemStacks[2].stackSize < stack.getMaxStackSize();
	}

	public void smeltItem()
	{
		if(!canSmelt())
		{
			return;
		}

		ItemStack stack = FurnaceRecipes.smelting().getSmeltingResult(furnaceItemStacks[0]);

		if(furnaceItemStacks[2] == null)
		{
			furnaceItemStacks[2] = stack.copy();
		} else if(furnaceItemStacks[2].itemID == stack.itemID)
		{
			furnaceItemStacks[2].stackSize++;
		}
		if(furnaceItemStacks[0].getItem().hasContainerItem())
		{
			furnaceItemStacks[0] = new ItemStack(furnaceItemStacks[0].getItem().getContainerItem());
		} else
		{
			furnaceItemStacks[0].stackSize--;
		}
		if(furnaceItemStacks[0].stackSize <= 0)
		{
			furnaceItemStacks[0] = null;
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
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
		{
			return false;
		}
		
		return player.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
	}

	@Override
	public void openChest() {}

	@Override
	public void closeChest() {}
	
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