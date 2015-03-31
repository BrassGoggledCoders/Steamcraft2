/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the WhaleOilcraft 2 Mod for Minecraft.
 *
 * WhaleOilcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * WhaleOilcraft 2 is based on the original WhaleOilcraft Mod created by Proloe.
 * WhaleOilcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-whaleoilcraft-source-code-releasedmlv054wip/)
 *
 */
package steamcraft.common.tiles;

import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import steamcraft.common.blocks.machines.BlockRefinery;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.init.InitItems;
import boilerplate.common.baseclasses.BaseTileWithInventory;

/**
 * @author Decebaldecebal
 * 
 */
public class TileRefinery extends BaseTileWithInventory implements IFluidHandler
{
	public static final int oilPerTick = 40;

	public int furnaceBurnTime = 0;
	public int currentItemBurnTime = 0;

	public FluidTank oilTank;

	public TileRefinery()
	{
		super(3);
		this.oilTank = new FluidTank(new FluidStack(InitBlocks.whaleOilFluid, 0), 10000);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		this.furnaceBurnTime = tag.getShort("BurnTime");
		this.currentItemBurnTime = tag.getShort("ItemTime");
		this.oilTank.setFluid(new FluidStack(FluidRegistry.getFluid("whaleOil"), tag.getShort("whaleoilLevel")));
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		tag.setShort("BurnTime", (short) this.furnaceBurnTime);
		tag.setShort("ItemTime", (short) this.currentItemBurnTime);
		tag.setShort("whaleoilLevel", (short) this.oilTank.getFluidAmount());
	}

	public int getBurnTimeRemainingScaled(int par1)
	{
		if(this.currentItemBurnTime == 0)
			this.currentItemBurnTime = 200;

		return (this.furnaceBurnTime * par1) / this.currentItemBurnTime;
	}

	public boolean isBurning()
	{
		return this.furnaceBurnTime > 0;
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();

		boolean var1 = this.furnaceBurnTime > 0;
		boolean var2 = false;

		if(!this.worldObj.isRemote)
		{
			// Drain Oil
			if((this.inventory[2] != null) && (this.inventory[2].getItem() instanceof ItemBucket))
			{
				this.inventory[2] = new ItemStack(InitItems.itemWhaleOilBucket);
				this.oilTank.drain(1000, true);
			}
			// Burning Items
			if((this.getItemBurnTime() > 0) && (this.furnaceBurnTime == 0)
					&& (this.oilTank.fill(new FluidStack(FluidRegistry.getFluid("whaleOil"), oilPerTick), false) > 0))
			{
				this.currentItemBurnTime = this.furnaceBurnTime = this.getItemBurnTime() / 4;

				if(this.inventory[0].stackSize == 1)
					this.inventory[0] = this.inventory[0].getItem().getContainerItem(this.inventory[0]);
				else
					--this.inventory[0].stackSize;
			}
			// Create Oil
			if((this.furnaceBurnTime > 0) && (this.oilTank.getFluidAmount() <= 10000) && this.inventory[1].getItem() == InitItems.itemWhaleBlubber)
			{
				if(this.inventory[1].stackSize == 1)
					this.inventory[1] = null;
				else
					--this.inventory[1].stackSize;

				this.oilTank.fill(new FluidStack(FluidRegistry.getFluid("whaleOil"), oilPerTick), true);
				this.furnaceBurnTime--;
			}
			else
				this.furnaceBurnTime = 0;

			if(var1 != (this.furnaceBurnTime > 0))
			{
				var2 = true;
				BlockRefinery.updateBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			}
		}

		if(var2)
			this.markDirty();
	}

	protected int getItemBurnTime()
	{
		if(this.inventory[0] == null)
			return 0;

		return TileEntityFurnace.getItemBurnTime(this.inventory[0]);
	}

	public int getScaledWhaleOilLevel(int i)
	{
		return this.oilTank.getFluid() != null ? (int) (((float) this.oilTank.getFluid().amount / (float) this.oilTank.getCapacity()) * i) : 0;
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return new int[] { 0, 1 };
	}

	@Override
	public boolean canInsertItem(int par1, ItemStack itemstack, int par3)
	{
		return ((par1 == 1) && FluidContainerRegistry.isContainer(itemstack)) || ((par1 == 0) && (TileEntityFurnace.getItemBurnTime(itemstack) > 0));
	}

	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return false;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return (i == 0) || (i == 1);
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		if((resource == null) || !resource.isFluidEqual(this.oilTank.getFluid()))
			return null;
		return this.oilTank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return this.oilTank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return fluid == FluidRegistry.WATER;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return fluid != FluidRegistry.WATER;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[] { this.oilTank.getInfo() };
	}
}
