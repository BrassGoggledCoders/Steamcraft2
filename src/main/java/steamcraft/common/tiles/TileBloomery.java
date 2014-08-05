/**
 * This class was created by BrassGoggledCoders modding team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 */
package steamcraft.common.tiles;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import steamcraft.common.blocks.machines.BlockBloomery;
import steamcraft.common.tiles.recipes.BloomeryRecipes;
import boilerplate.common.baseclasses.BaseTileWithInventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones & decebaldecebal
 * 
 */
public class TileBloomery extends BaseTileWithInventory
{
	public TileBloomery()
	{
		super((byte) 4);
	}

	private static final int[] slotsInput = new int[] { 1, 2 };
	private static final int[] slotsFuel = new int[] { 0 };
	private static final int[] slotsOutput = new int[] { 3 };

	public short burnTime;
	public short currentItemBurnTime;
	public short cookTime;

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		burnTime = tag.getShort("BurnTime");
		cookTime = tag.getShort("CookTime");
		currentItemBurnTime = tag.getShort("ItemBurnTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		tag.setShort("BurnTime", burnTime);
		tag.setShort("CookTime", cookTime);
		tag.setShort("ItemBurnTime", currentItemBurnTime);
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int i)
	{
		return (cookTime * i) / 400;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int i)
	{
		if (currentItemBurnTime == 0)
			currentItemBurnTime = 200;

		return (burnTime * i) / currentItemBurnTime;
	}

	public boolean isBurning()
	{
		return burnTime > 0;
	}

	@Override
	public void updateEntity()
	{
		boolean flag = burnTime > 0;
		boolean flag1 = false;

		if (burnTime > 0)
			--burnTime;

		if (!worldObj.isRemote)
		{
			if ((burnTime == 0) && canSmelt())
			{
				currentItemBurnTime = burnTime = getItemBurnTime(inventory[0]);

				if (burnTime > 0)
				{
					flag1 = true;

					if (inventory[0] != null)
					{
						--inventory[0].stackSize;

						if (inventory[0].stackSize == 0)
							inventory[0] = inventory[0].getItem().getContainerItem(inventory[0]);
					}
				}
			}

			if (isBurning() && canSmelt())
			{
				++cookTime;

				if (cookTime == 400)
				{
					cookTime = 0;
					smeltItem();
					flag1 = true;
				}
			}
			else
				cookTime = 0;

			if (flag != (burnTime > 0))
			{
				flag1 = true;
				BlockBloomery.updateBloomeryBlockState(burnTime > 0, worldObj, xCoord, yCoord, zCoord);
			}
		}

		if (flag1)
			markDirty();
	}

	private boolean canSmelt()
	{
		if ((inventory[1] != null) && (inventory[2] != null))
		{
			ItemStack result = getRecipeResult();

			if (result != null)
			{
				if (inventory[3] == null)
					return true;
				if (!inventory[3].isItemEqual(result))
					return false;
				int amount = inventory[3].stackSize + result.stackSize;

				return (amount <= getInventoryStackLimit()) && (amount <= inventory[3].getMaxStackSize());
			}
		}
		return false;
	}

	public void smeltItem()
	{
		if (canSmelt())
		{
			ItemStack result = getRecipeResult();

			if (inventory[3] == null)
				inventory[3] = result.copy();
			else if (inventory[3].getItem() == result.getItem())
				inventory[3].stackSize += result.stackSize;

			byte[] stackSizes = BloomeryRecipes.getInstance().getStackSizeForInputs(inventory[1], inventory[2], result);

			if (inventory[1] != null)
			{
				inventory[1].stackSize -= stackSizes[0];
				if (inventory[1].stackSize <= 0)
					inventory[1] = null;
			}

			if (inventory[2] != null)
			{
				inventory[2].stackSize -= stackSizes[1];
				if (inventory[2].stackSize <= 0)
					inventory[2] = null;
			}
		}
	}

	private ItemStack getRecipeResult()
	{
		ItemStack result = BloomeryRecipes.getInstance().getResult(inventory[1], inventory[2]);

		if (result == null)
			result = BloomeryRecipes.getInstance().getResult(inventory[2], inventory[1]);

		return result;
	}

	public static short getItemBurnTime(ItemStack stack)
	{
		if (stack == null)
			return 0;
		else
			return (short) TileEntityFurnace.getItemBurnTime(stack);
	}

	public static boolean isItemFuel(ItemStack stack)
	{
		return getItemBurnTime(stack) > 0;
	}

	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return par1 == 2 ? false : par1 == 1 ? isItemFuel(par2ItemStack) : true;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return par1 == 0 ? slotsFuel : par1 == 1 ? slotsInput : slotsOutput;
	}

	@Override
	public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return isItemValidForSlot(par1, par2ItemStack);
	}

	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return (par3 != 0) || (par1 != 1) || (par2ItemStack.getItem() == Items.bucket);
	}

	@Override
	public String getInventoryName()
	{
		return "Bloomery";
	}
}
