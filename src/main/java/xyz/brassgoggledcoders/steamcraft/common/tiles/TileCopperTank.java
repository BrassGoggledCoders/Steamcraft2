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
package xyz.brassgoggledcoders.steamcraft.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import xyz.brassgoggledcoders.steamcraft.common.init.InitPackets;
import xyz.brassgoggledcoders.steamcraft.common.packets.CopperTankPacket;

/**
 * @author Decebaldecebal
 *
 */
public class TileCopperTank extends TileEntity implements IFluidHandler
{
	private static int ticksTillFluidUpdate = 100;
	public static int capacity = 20000;

	public FluidTank tank;
	public float fluidScaled = 0;
	private int ticksSinceUpdate = 0;
	private int lastAmount = 0;

	public TileCopperTank()
	{
		this.tank = new FluidTank(capacity);
	}

	@Override
	public void updateEntity()
	{
		if (!this.worldObj.isRemote)
		{
			if (this.tank.getFluidAmount() != this.lastAmount)
			{
				this.updateClientFluid();
				this.lastAmount = this.tank.getFluidAmount();
			}

			this.ticksSinceUpdate++;

			if (this.ticksSinceUpdate >= ticksTillFluidUpdate)
			{
				this.ticksSinceUpdate = 0;
				this.updateClientFluid();
			}
		}
	}

	private void updateClientFluid()
	{
		if (this.tank.getFluid() != null)
		{
			InitPackets.network.sendToAllAround(
					new CopperTankPacket(this.xCoord, this.yCoord, this.zCoord, this.tank.getFluidAmount(),
							this.tank.getFluid().getFluid().getName()),
					new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 50));
		}
		else
		{
			InitPackets.network.sendToAllAround(new CopperTankPacket(this.xCoord, this.yCoord, this.zCoord, 0, "water"),
					new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 50));
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		this.tank.writeToNBT(tag);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

		this.tank.readFromNBT(tag);
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		return this.tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		if ((resource == null) || !resource.isFluidEqual(this.tank.getFluid()))
			return null;

		return this.tank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return this.tank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return true;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return true;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[] { this.tank.getInfo() };
	}
}
