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

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import steamcraft.common.lib.LibInfo;

/**
 * @author decebaldecebal
 *
 */
public class TileIntake extends TileEntity implements IFluidHandler
{
	private static byte waterPerTick = 25; //Same as TE Aqueous Accumulator
	private static byte exportAmountPerTick = 25;

	private FluidTank waterTank;
	private short tickSinceLastConsume = 0;

	public TileIntake()
	{
		waterTank = new FluidTank(new FluidStack(FluidRegistry.WATER, 0), 5000);
	}

	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if ((worldObj.getBlock(xCoord, yCoord - 1, zCoord) == Blocks.water) && (worldObj.getBlockMetadata(xCoord, yCoord - 1, zCoord) == 0))
		{
			waterTank.fill(new FluidStack(FluidRegistry.WATER, waterPerTick), true);
			tickSinceLastConsume++;

			if (tickSinceLastConsume == 200)
			{
				tickSinceLastConsume = 0;
				worldObj.setBlockToAir(xCoord, yCoord - 1, zCoord);
			}
		}

		if ((worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) != null)
				&& (worldObj.getTileEntity(xCoord, yCoord + 1, zCoord) instanceof IFluidHandler))
		{
			IFluidHandler export = (IFluidHandler) worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
			waterTank.drain(export.fill(ForgeDirection.DOWN,
					new FluidStack(FluidRegistry.WATER, Math.min(waterTank.getFluidAmount(), exportAmountPerTick)), true), true);
		}
		Random random = new Random();
		if(waterTank.getFluidAmount() > 0)
		worldObj.spawnParticle("dripWater", xCoord + random.nextDouble(), yCoord + random.nextDouble(), zCoord + random.nextDouble(), random.nextDouble(), -0.5D, random.nextDouble());
		if(random.nextInt(100) == 0)
		worldObj.playSound(xCoord, yCoord, zCoord, LibInfo.PREFIX + "intake", 1F, 1F, true);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		waterTank.setFluid(new FluidStack(FluidRegistry.getFluid("water"), tag.getShort("waterLevel")));
		tickSinceLastConsume = tag.getShort("consumeTicks");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setShort("waterLevel", (short) waterTank.getFluidAmount());
		tag.setShort("consumeTicks", tickSinceLastConsume);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		if ((resource == null) || !resource.isFluidEqual(waterTank.getFluid()))
			return null;

		return waterTank.drain(resource.amount, doDrain);
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return waterTank.drain(maxDrain, doDrain);
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		if (fluid == FluidRegistry.WATER)
			return true;

		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[] { waterTank.getInfo() };
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
