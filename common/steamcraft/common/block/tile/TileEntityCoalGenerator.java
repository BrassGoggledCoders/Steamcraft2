package common.steamcraft.common.block.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import common.steamcraft.common.block.machines.BlockCoalGenerator;


/**
 * A basic generator that produces steam from coal.Requires water.
 * 
 * @author Decebaldecebal
 *
 */
public class TileEntityCoalGenerator extends TileEntityMachine
{
	public int furnaceBurnTime = 0;
	public int currentItemBurnTime = 0;
	public FluidTank waterTank;
	public FluidTank steamTank;
	
	public TileEntityCoalGenerator()
	{
		super();
		inventory = new ItemStack[3];
		waterTank = new FluidTank(new FluidStack(FluidRegistry.WATER, 1000), 10000);
		steamTank = new FluidTank(new FluidStack(FluidRegistry.getFluid("steam"), 0), 10000);
	}
	
	@Override
	public String getInvName()
	{
		return "Coal Generator";
	}
	
	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		
		furnaceBurnTime = par1NBTTagCompound.getShort("BurnTime");
		currentItemBurnTime = par1NBTTagCompound.getShort("ItemTime");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		
		par1NBTTagCompound.setShort("BurnTime", (short) furnaceBurnTime);
		par1NBTTagCompound.setShort("ItemTime", (short) currentItemBurnTime);
	}

    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
    }

	public boolean isBurning()
	{
		return furnaceBurnTime > 0;
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		boolean var1 = furnaceBurnTime > 0;
		boolean var2 = false;

		if (!worldObj.isRemote)
		{			
			if (this.getItemBurnTime()>0 && furnaceBurnTime==0 && this.waterTank.getFluidAmount()>=10 &&
					this.steamTank.fill(new FluidStack(FluidRegistry.getFluid("steam"), 5), false)>0)
			{	
				currentItemBurnTime = furnaceBurnTime = this.getItemBurnTime()/4;
				
				if(this.inventory[0].stackSize==1)
					inventory[0]=inventory[0].getItem().getContainerItemStack(inventory[0]);
				else
					--inventory[0].stackSize;
			}
			
			if(furnaceBurnTime > 0 && this.waterTank.getFluidAmount()>=10)
			{
				this.steamTank.fill(new FluidStack(FluidRegistry.getFluid("steam"), 5), true);
				furnaceBurnTime--;
				this.waterTank.drain(10, true);
			}
			else
				furnaceBurnTime=0;

			if (var1 != furnaceBurnTime > 0)
			{
				var2 = true;
				BlockCoalGenerator.updateFurnaceBlockState(furnaceBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
			}
		}

		if (var2)
			this.onInventoryChanged();
	}
	
	private int getItemBurnTime()
	{
		if (inventory[0] == null)
			return 0;
		
		return TileEntityFurnace.getItemBurnTime(inventory[0]);
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return new int[]{0};
	}
	
	@Override
	public boolean canInsertItem(int par1, ItemStack itemstack, int par3)
	{
		if(par1 == 0)
			return true;
		return false;
	}

	@Override
	public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
	{
		return false;
	}

	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		if(i == 0)
			return true;
		return false;
	}
}
