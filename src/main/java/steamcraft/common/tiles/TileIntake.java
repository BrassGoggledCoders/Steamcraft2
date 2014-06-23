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
 * File created @ [Jun 23, 2014, 10:51:48 PM]
 */
package steamcraft.common.tiles;

import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author decebaldecebal
 *
 */
public class TileIntake extends TileEntity implements IFluidHandler
{
	private static byte waterPerTick = 10;
	private static byte exportAmountPerTick = 10;
	
	private FluidTank waterTank;

	public TileIntake()
	{
		this.waterTank = new FluidTank(new FluidStack(FluidRegistry.WATER, 0), 5000);
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if (this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord).getMaterial() == Material.water)
			this.waterTank.fill(new FluidStack(FluidRegistry.WATER, waterPerTick), true);
		
		if(this.worldObj.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord)!=null
			&& this.worldObj.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof IFluidHandler)
		{
			IFluidHandler export = (IFluidHandler)this.worldObj.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
			waterTank.drain(export.fill(ForgeDirection.DOWN, new FluidStack(FluidRegistry.WATER, Math.min(waterTank.getFluidAmount(), exportAmountPerTick)), true), true);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.waterTank.setFluid(new FluidStack(FluidRegistry.getFluid("water"), tag.getShort("waterLevel")));
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setShort("waterLevel", (short) this.waterTank.getFluidAmount());
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		if (resource == null || !resource.isFluidEqual(this.waterTank.getFluid()))
		{
			return null;
		}
		
		return this.waterTank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return this.waterTank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		if (fluid == FluidRegistry.WATER)
		{
			return true;
		}
		
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[] { this.waterTank.getInfo() };
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return false;
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		return 0;
	}
}
