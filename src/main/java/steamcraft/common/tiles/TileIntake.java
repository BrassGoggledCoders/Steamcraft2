package steamcraft.common.tiles;

import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class TileIntake extends TileEntity
{
	public FluidTank waterTank;

	public TileIntake()
	{
		this.waterTank = new FluidTank(new FluidStack(FluidRegistry.WATER, 0), 5000);
	}
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		if(worldObj.getBlock(xCoord, yCoord-1, zCoord).getMaterial() == Material.water)
		waterTank.fill(new FluidStack(FluidRegistry.WATER, 10), true);
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

}
