package common.steamcraft.common.block.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import common.steamcraft.common.block.machines.BlockSteamBoiler;
import common.steamcraft.common.item.ItemCanister;
import common.steamcraft.common.item.ModItems;


/**
 * A basic generator that produces steam from coal.Requires water.
 * 
 * @author Decebaldecebal
 *
 */
public class TileEntitySteamBoiler extends TileEntityMachine implements IFluidHandler
{
	/*
	 * 20 steam/tick is basically 4 MJ/t if we use RailCraft ratios
	 * I also calculated that for a piece of coal that burns 400 ticks in this, we get 1600 MJ/t
	 * and if we transform that in EU using the MFR conversion ratio, we get 4000 EU/t, which is the same
	 * as a piece of coal in an IC2 generator
	 * 
	 * Anyways, I think this is a good number
	 * Maybe we'll have to tweak the water a bit
	 */
	private final int steamPerTick = 20; //how much steam it produces per tick
	private final int waterPerTick = 30; //how much water it uses per tick
	public int furnaceBurnTime = 0;
	public int currentItemBurnTime = 0;
	public FluidTank waterTank;
	public FluidTank steamTank;
	
	public TileEntitySteamBoiler()
	{
		super();
		inventory = new ItemStack[3];
		waterTank = new FluidTank(new FluidStack(FluidRegistry.WATER, 0), 5000);
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
		steamTank.setFluid(new FluidStack(FluidRegistry.getFluid("steam"), par1NBTTagCompound.getShort("steamLevel")));
		waterTank.setFluid(new FluidStack(FluidRegistry.getFluid("water"), par1NBTTagCompound.getShort("waterLevel")));
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		
		par1NBTTagCompound.setShort("BurnTime", (short) furnaceBurnTime);
		par1NBTTagCompound.setShort("ItemTime", (short) currentItemBurnTime);
		par1NBTTagCompound.setShort("steamLevel", (short) this.steamTank.getFluidAmount());
		par1NBTTagCompound.setShort("waterLevel", (short) this.waterTank.getFluidAmount());
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
			if(inventory[1]!=null)			
			{
				FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(inventory[1]);

				if(liquid != null && this.waterTank.fill(new FluidStack(FluidRegistry.getFluid("water"), liquid.amount), false)==liquid.amount)
					if(liquid.getFluid() == FluidRegistry.WATER)
				    {
				    	this.waterTank.fill(new FluidStack(FluidRegistry.getFluid("water"), liquid.amount), true);
				    	
				    	if(inventory[1].stackSize > 1)
				    		inventory[1].stackSize--;
				    	else
				    		inventory[1] = inventory[1].getItem().getContainerItemStack(inventory[1]);
				    }
			}
			
			if(inventory[2] != null && inventory[2].getItem() == ModItems.canisterSteam)
				if(!ItemCanister.isFullSteam(inventory[2]))
					this.steamTank.drain(ItemCanister.addSteam(inventory[2], this.steamTank.getFluidAmount()), true);

			if (this.getItemBurnTime()>0 && furnaceBurnTime==0 && this.waterTank.getFluidAmount()>=this.waterPerTick &&
					this.steamTank.fill(new FluidStack(FluidRegistry.getFluid("steam"), this.steamPerTick), false)>0)
			{	
				currentItemBurnTime = furnaceBurnTime = this.getItemBurnTime()/4;
				
				if(this.inventory[0].stackSize==1)
					inventory[0]=inventory[0].getItem().getContainerItemStack(inventory[0]);
				else
					--inventory[0].stackSize;
			}
			
			if(furnaceBurnTime > 0 && this.waterTank.getFluidAmount()>=this.waterPerTick && this.steamTank.getFluidAmount()<=10000)
			{
				this.steamTank.fill(new FluidStack(FluidRegistry.getFluid("steam"), this.steamPerTick), true);
				furnaceBurnTime--;
				this.waterTank.drain(this.waterPerTick, true);
			}
			else
				furnaceBurnTime=0;

			if (var1 != furnaceBurnTime > 0)
			{
				var2 = true;
				BlockSteamBoiler.updateFurnaceBlockState(furnaceBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
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
	
	public int getScaledWaterLevel(int i) 
	{
		return this.waterTank.getFluid() != null ? (int) (((float) this.waterTank.getFluid().amount / (float) (this.waterTank.getCapacity())) * i) : 0;
	}
	
	public int getScaledSteamLevel(int i) 
	{
		return this.steamTank.getFluid() != null ? (int) (((float) this.steamTank.getFluid().amount / (float) (this.steamTank.getCapacity())) * i) : 0;
	}
	
	@Override
	public int[] getAccessibleSlotsFromSide(int par1)
	{
		return new int[]{0,1};
	}
	
	@Override
	public boolean canInsertItem(int par1, ItemStack itemstack, int par3)
	{
		if(par1 == 1 && FluidContainerRegistry.isContainer(itemstack))
			return true;
		if(par1 == 0 && TileEntityFurnace.getItemBurnTime(itemstack)>0)
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
		if(i == 0 || i == 1)
			return true;
		return false;
	}

	@Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
    {
		if(resource.getFluid() == FluidRegistry.WATER)
			return this.waterTank.fill(resource, doFill);
		
		return 0;
    }

    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
    {
        if (resource == null || !resource.isFluidEqual(this.steamTank.getFluid()))
        {
            return null;
        }
        return this.steamTank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
    {
        return this.steamTank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid)
    {
    	if(fluid == FluidRegistry.WATER)
    		return true;
    	return false;
    }

    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid)
    {
    	if(fluid == FluidRegistry.getFluid("steam"))
    		return true;
    	return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from)
    {
        return new FluidTankInfo[] {this.steamTank.getInfo(), this.waterTank.getInfo()};
    }
}
