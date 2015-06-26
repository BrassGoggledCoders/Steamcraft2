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

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import steamcraft.common.init.InitPackets;
import steamcraft.common.packets.CopperTankPacket;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;

/**
 * @author warlordjones
 *
 */
public class TileCopperTank extends TileEntity implements IFluidHandler
{
	private static int ticksTillFluidUpdate = 5;
	public static int capacity = 20000;

	public FluidTank tank;
	public float fluidScaled = 0;
	private int ticksSinceUpdate = 0;

	public TileCopperTank()
	{
		this.tank = new FluidTank(capacity);
	}
	
	@Override
	public void updateEntity()
	{
		if(!this.worldObj.isRemote)
		{
			ticksSinceUpdate++;
			
			if (this.ticksSinceUpdate >= ticksTillFluidUpdate)
			{
				this.ticksSinceUpdate = 0;
				this.updateClientFluid();
			}
		}
	}

	private void updateClientFluid()
	{
		if(this.tank.getFluid() != null)
		{
			InitPackets.network.sendToAllAround(new CopperTankPacket(this.xCoord, this.yCoord, this.zCoord,
					this.tank.getFluidAmount(), this.tank.getFluid().getFluid().getName()), new TargetPoint(
							this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 50));
		}
		else
		{
			InitPackets.network.sendToAllAround(new CopperTankPacket(this.xCoord, this.yCoord, this.zCoord,
					0, "water"), new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 50));
		}
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		return this.tank.fill(resource, doFill);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		if((resource == null) || !resource.isFluidEqual(this.tank.getFluid()))
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
