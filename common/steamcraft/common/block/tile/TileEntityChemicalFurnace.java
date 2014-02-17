package common.steamcraft.common.block.tile;

import common.steamcraft.common.block.machines.BlockChemicalFurnace;
import common.steamcraft.common.item.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityChemicalFurnace extends TileEntity implements ISidedInventory
{
	private static final int[] slots_top = new int[] {0};
	private static final int[] slots_bottom = new int[] {2, 1};
	private static final int[] slots_sides = new int[] {1};
	private ItemStack furnaceItemStacks[];
	public int furnaceBurnTime;
	public int currentItemBurnTimea;
	public int currentItemBurnTimeb;
	public int furnaceCookTime;
	private String field_94130_e;

	public TileEntityChemicalFurnace()
	{
		furnaceItemStacks = new ItemStack[4];
		furnaceBurnTime = 0;
		currentItemBurnTimea = 0;
		currentItemBurnTimeb = 0;
		furnaceCookTime = 0;
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
		return "Chemical Furnace";
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
		currentItemBurnTimea = getItemBurnTime(furnaceItemStacks[1]);
		currentItemBurnTimeb = getItemBurnTime(furnaceItemStacks[3]);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setShort("BurnTime", (short)furnaceBurnTime);
		nbt.setShort("CookTime", (short)furnaceCookTime);
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
				if(furnaceItemStacks[1] != null && furnaceItemStacks[3] != null)
				{
					if(furnaceItemStacks[1].itemID != furnaceItemStacks[3].itemID)
					{
						currentItemBurnTimea = getItemBurnTime(furnaceItemStacks[1]);
						currentItemBurnTimeb = getItemBurnTime(furnaceItemStacks[3]);

						if(currentItemBurnTimea > 0 && currentItemBurnTimeb > 0)
						{
							furnaceBurnTime = currentItemBurnTimea + currentItemBurnTimeb;
							flag1 = true;

							if(furnaceItemStacks[1] != null && furnaceItemStacks[3] != null)
							{
								if(furnaceItemStacks[1].getItem().hasContainerItem())
								{
									furnaceItemStacks[1] = new ItemStack(furnaceItemStacks[1].getItem().getContainerItem());
								} else
								{
									furnaceItemStacks[1].stackSize--;
								}
								if(furnaceItemStacks[3].getItem().hasContainerItem())
								{
									furnaceItemStacks[3] = new ItemStack(furnaceItemStacks[3].getItem().getContainerItem());
								} else
								{
									furnaceItemStacks[3].stackSize--;
								}
								if(furnaceItemStacks[1].stackSize == 0)
								{
									furnaceItemStacks[1] = null;
								}
								if(furnaceItemStacks[3].stackSize == 0)
								{
									furnaceItemStacks[3] = null;
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
			furnaceItemStacks[2].stackSize += stack.stackSize;
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