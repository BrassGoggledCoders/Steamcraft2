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

import java.util.EnumSet;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

/**
 * @author decebaldecebal
 *
 */
public class TileTurbine extends TileEntity implements IFluidHandler, IEnergyHandler
{
	private static byte steamPerTick = TileSteamBoiler.steamPerTick/2;
	private static byte RFPerTick = 20; //Same as RC ratio of 1 MJ/5 steam
	
	private FluidTank steamTank = new FluidTank(new FluidStack(FluidRegistry.getFluid("steam"), 0), 500);
	
	private EnergyStorage buffer = new EnergyStorage(10000, RFPerTick);

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		buffer.readFromNBT(tag);

		steamTank.setFluid(new FluidStack(FluidRegistry.getFluid("steam"), tag.getShort("steamLevel")));
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		buffer.writeToNBT(tag);
		
		tag.setShort("steamLevel", (short) steamTank.getFluidAmount());
	}
	
	@Override
	public void updateEntity()
	{
		if (!worldObj.isRemote)
		{
			if(steamTank.getFluidAmount() >= steamPerTick)
				if(this.buffer.receiveEnergy(RFPerTick, false) == RFPerTick)
					this.steamTank.drain(steamPerTick, true);
		
			if(buffer.getEnergyStored() >= RFPerTick)
			{
				byte usedEnergy = 0;
				byte outputEnergy = RFPerTick;
				
				for (ForgeDirection direction : EnumSet.allOf(ForgeDirection.class))
					if(outputEnergy > 0)
					{
						TileEntity tileEntity = worldObj.getTileEntity(xCoord - direction.offsetX, yCoord - direction.offsetY, zCoord - direction.offsetZ);
						
						if(tileEntity instanceof IEnergyHandler) 
						{
							 usedEnergy += ((IEnergyHandler) tileEntity).receiveEnergy(direction.getOpposite(), outputEnergy, false);
							 outputEnergy -= usedEnergy;
						}
					}
				
				this.buffer.modifyEnergyStored(-usedEnergy);
			}
		}
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		if(resource.getFluid() == FluidRegistry.getFluid("steam"))
			return this.steamTank.fill(resource, doFill);
		return 0;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return null;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		if(fluid == FluidRegistry.getFluid("steam"))
			return true;
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[] { steamTank.getInfo() };
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return 0;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		return buffer.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return buffer.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return buffer.getMaxEnergyStored();
	}
}
