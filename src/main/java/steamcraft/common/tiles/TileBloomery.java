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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.blocks.machines.BlockBloomery;
import steamcraft.common.tiles.recipes.BloomeryRecipes;
import boilerplate.common.baseclasses.BaseTileWithInventory;

/**
 * @author warlordjones & decebaldecebal
 * 
 */
public class TileBloomery extends BaseTileWithInventory
{
	public TileBloomery()
	{
		super(4);
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

		this.burnTime = tag.getShort("BurnTime");
		this.cookTime = tag.getShort("CookTime");
		this.currentItemBurnTime = tag.getShort("ItemBurnTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		tag.setShort("BurnTime", this.burnTime);
		tag.setShort("CookTime", this.cookTime);
		tag.setShort("ItemBurnTime", this.currentItemBurnTime);
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int i)
	{
		return (this.cookTime * i) / 400;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int i)
	{
		if(this.currentItemBurnTime == 0)
			this.currentItemBurnTime = 200;

		return (this.burnTime * i) / this.currentItemBurnTime;
	}

	public boolean isBurning()
	{
		return this.burnTime > 0;
	}

	@Override
	public void updateEntity()
	{
		boolean flag = this.burnTime > 0;
		boolean flag1 = false;

		if(this.burnTime > 0)
			--this.burnTime;

		if(!this.worldObj.isRemote)
		{
			if((this.burnTime == 0) && this.canSmelt())
			{
				this.currentItemBurnTime = this.burnTime = getItemBurnTime(this.inventory[0]);

				if(this.burnTime > 0)
				{
					flag1 = true;

					if(this.inventory[0] != null)
					{
						--this.inventory[0].stackSize;

						if(this.inventory[0].stackSize == 0)
							this.inventory[0] = this.inventory[0].getItem().getContainerItem(this.inventory[0]);
					}
				}
			}

			if(this.isBurning() && this.canSmelt())
			{
				++this.cookTime;

				if(this.cookTime == 400)
				{
					this.cookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			}
			else
				this.cookTime = 0;

			if(flag != (this.burnTime > 0))
			{
				flag1 = true;
				BlockBloomery.updateBloomeryBlockState(this.burnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if(flag1)
			this.markDirty();
	}

	private boolean canSmelt()
	{
		if((this.inventory[1] != null) && (this.inventory[2] != null))
		{
			ItemStack result = this.getRecipeResult();

			if(result != null)
			{
				if(this.inventory[3] == null)
					return true;
				if(!this.inventory[3].isItemEqual(result))
					return false;
				int amount = this.inventory[3].stackSize + result.stackSize;

				return (amount <= this.getInventoryStackLimit()) && (amount <= this.inventory[3].getMaxStackSize());
			}
		}
		return false;
	}

	public void smeltItem()
	{
		if(this.canSmelt())
		{
			ItemStack result = this.getRecipeResult();

			if(this.inventory[3] == null)
				this.inventory[3] = result.copy();
			else if(this.inventory[3].getItem() == result.getItem())
				this.inventory[3].stackSize += result.stackSize;

			byte[] stackSizes = BloomeryRecipes.getInstance().getStackSizeForInputs(this.inventory[1], this.inventory[2], result);

			if(this.inventory[1] != null)
			{
				this.inventory[1].stackSize -= stackSizes[0];
				if(this.inventory[1].stackSize <= 0)
					this.inventory[1] = null;
			}

			if(this.inventory[2] != null)
			{
				this.inventory[2].stackSize -= stackSizes[1];
				if(this.inventory[2].stackSize <= 0)
					this.inventory[2] = null;
			}
		}
	}

	private ItemStack getRecipeResult()
	{
		ItemStack result = BloomeryRecipes.getInstance().getResult(this.inventory[1], this.inventory[2]);

		if(result == null)
			result = BloomeryRecipes.getInstance().getResult(this.inventory[2], this.inventory[1]);

		return result;
	}

	public static short getItemBurnTime(ItemStack stack)
	{
		if(stack == null)
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
		return this.isItemValidForSlot(par1, par2ItemStack);
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
