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
package steamcraft.common.tiles.energy;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

import steamcraft.common.tiles.TileSteamBoiler;

/**
 * @author decebaldecebal
 *
 */
public class TileTurbine extends TileEntity implements IFluidHandler, IEnergyProvider
{
	private static byte steamPerTick = TileSteamBoiler.steamPerTick / 2;
	private static byte RFPerTick = 20; // Same as RC ratio of 1 MJ/5 steam
	private static byte RFOutPerTick = 100;

	private final FluidTank steamTank = new FluidTank(new FluidStack(FluidRegistry.getFluid("steam"), 0), 500);

	private final EnergyStorage buffer = new EnergyStorage(10000, RFPerTick);

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.buffer.readFromNBT(tag);

		this.steamTank.setFluid(new FluidStack(FluidRegistry.getFluid("steam"), tag.getShort("steamLevel")));
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		this.buffer.writeToNBT(tag);

		tag.setShort("steamLevel", (short) this.steamTank.getFluidAmount());
	}

	@Override
	public void updateEntity()
	{
		if(!this.worldObj.isRemote)
		{
			if(this.steamTank.getFluidAmount() >= steamPerTick)
				if(this.buffer.receiveEnergy(RFPerTick, false) == RFPerTick)
					this.steamTank.drain(steamPerTick, true);
			
			System.out.println("Turbine buffer: " + buffer.getEnergyStored());
			if(this.buffer.getEnergyStored() > 0)
			{
				int usedEnergy = Math.min(buffer.getEnergyStored(), RFOutPerTick);
				int outputEnergy = usedEnergy;

				usedEnergy -= this.outputEnergy(ForgeDirection.UP, usedEnergy);
				usedEnergy -= this.outputEnergy(ForgeDirection.DOWN, usedEnergy);

				this.buffer.modifyEnergyStored(usedEnergy - outputEnergy);
			}
		}
	}

	private int outputEnergy(ForgeDirection dir, int usedEnergy)
	{
		if(usedEnergy > 0)
		{
			TileEntity tileEntity = this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY,
					this.zCoord + dir.offsetZ);

			if(tileEntity instanceof IEnergyReceiver)
			{
				return (byte) ((IEnergyReceiver) tileEntity).receiveEnergy(dir.getOpposite(), usedEnergy, false);
			}
		}
		return 0;
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
		return (fluid == FluidRegistry.getFluid("steam")) && ((from != ForgeDirection.DOWN) || (from != ForgeDirection.UP));
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[] { this.steamTank.getInfo() };
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return (from == ForgeDirection.DOWN) || (from == ForgeDirection.UP);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		if(this.canConnectEnergy(from))
			return this.buffer.extractEnergy(maxExtract, simulate);
		else
			return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return this.buffer.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return this.buffer.getMaxEnergyStored();
	}
}
