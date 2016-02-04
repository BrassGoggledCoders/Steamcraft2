
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

import steamcraft.common.lib.ModInfo;

/**
 * @author decebaldecebal
 *
 */
public class TileIntake extends TileEntity implements IFluidHandler
{
	private static byte waterPerTick = 25; // Same as TE Aqueous Accumulator
	private static byte exportAmountPerTick = 25;

	private FluidTank waterTank;
	private short tickSinceLastConsume = 0;

	public TileIntake()
	{
		this.waterTank = new FluidTank(new FluidStack(FluidRegistry.WATER, 0), 5000);
	}

	@Override
	public void updateEntity()
	{
		if (!this.worldObj.isRemote)
		{
			if ((this.worldObj.getBlock(this.xCoord, this.yCoord - 1, this.zCoord) == Blocks.water)
					&& (this.worldObj.getBlockMetadata(this.xCoord, this.yCoord - 1, this.zCoord) == 0))
			{
				this.waterTank.fill(new FluidStack(FluidRegistry.WATER, waterPerTick), true);
				this.tickSinceLastConsume++;

				if (this.tickSinceLastConsume == 200)
				{
					this.tickSinceLastConsume = 0;
					this.worldObj.setBlockToAir(this.xCoord, this.yCoord - 1, this.zCoord);
				}
			}

			if ((this.worldObj.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) != null)
					&& (this.worldObj.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord) instanceof IFluidHandler))
			{
				IFluidHandler export = (IFluidHandler) this.worldObj.getTileEntity(this.xCoord, this.yCoord + 1, this.zCoord);
				this.waterTank.drain(export.fill(ForgeDirection.DOWN,
						new FluidStack(FluidRegistry.WATER, Math.min(this.waterTank.getFluidAmount(), exportAmountPerTick)), true), true);
			}
		}
		else
		{
			Random random = this.worldObj.rand;

			this.worldObj.spawnParticle("dripWater", this.xCoord + random.nextDouble(), this.yCoord + random.nextDouble(),
					this.zCoord + random.nextDouble(), random.nextDouble(), -0.5D, random.nextDouble());

			if (random.nextInt(100) == 0)
				this.worldObj.playSound(this.xCoord, this.yCoord, this.zCoord, ModInfo.PREFIX + "intake", 1F, 1F, true);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.waterTank.setFluid(new FluidStack(FluidRegistry.getFluid("water"), tag.getShort("waterLevel")));
		this.tickSinceLastConsume = tag.getShort("consumeTicks");
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setShort("waterLevel", (short) this.waterTank.getFluidAmount());
		tag.setShort("consumeTicks", this.tickSinceLastConsume);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		if ((resource == null) || !resource.isFluidEqual(this.waterTank.getFluid()))
			return null;

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
		return fluid == FluidRegistry.WATER;
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
