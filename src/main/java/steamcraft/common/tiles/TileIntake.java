/*
 * 
 */
package steamcraft.common.tiles;

import net.minecraft.block.material.Material;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

// TODO: Auto-generated Javadoc
/**
 * The Class TileIntake.
 */
public class TileIntake extends TileEntity {
	
	/** The water tank. */
	public FluidTank waterTank;

	/**
	 * Instantiates a new tile intake.
	 */
	public TileIntake() {
		this.waterTank = new FluidTank(new FluidStack(FluidRegistry.WATER, 0),
				5000);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.tileentity.TileEntity#updateEntity()
	 */
	@Override
	public void updateEntity() {
		super.updateEntity();
		if (worldObj.getBlock(xCoord, yCoord - 1, zCoord).getMaterial() == Material.water)
			waterTank.fill(new FluidStack(FluidRegistry.WATER, 10), true);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.tileentity.TileEntity#readFromNBT(net.minecraft.nbt.NBTTagCompound)
	 */
	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		this.waterTank.setFluid(new FluidStack(FluidRegistry.getFluid("water"),
				tag.getShort("waterLevel")));
	}

	/* (non-Javadoc)
	 * @see net.minecraft.tileentity.TileEntity#writeToNBT(net.minecraft.nbt.NBTTagCompound)
	 */
	@Override
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setShort("waterLevel", (short) this.waterTank.getFluidAmount());
	}

}
