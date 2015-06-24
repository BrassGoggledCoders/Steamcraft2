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

import java.util.EnumSet;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.util.ForgeDirection;

import boilerplate.common.baseclasses.BaseTileWithInventory;

/**
 * @author decebaldecebal
 *
 */
public class TileCapacitor extends BaseTileWithInventory implements IEnergyHandler
{
	private byte ticksSinceUpdate = 0;

	public short transferRate = 100;

	public EnergyStorage buffer = new EnergyStorage(64000000, this.transferRate);

	public TileCapacitor()
	{
		super(0);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.buffer.readFromNBT(tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		this.buffer.writeToNBT(tag);
	}

	@SideOnly(Side.CLIENT)
	public int getEnergyScaled(int par1)
	{
		return this.buffer.getEnergyStored() / (this.buffer.getMaxEnergyStored() / 1000) / par1;
	}

	@Override
	public void updateEntity()
	{

		if(!this.worldObj.isRemote)
		{
			short outputEnergy = (short) this.extractEnergy(ForgeDirection.UNKNOWN, this.transferRate, true);

			if(outputEnergy > 0)
				for(ForgeDirection direction : EnumSet.allOf(ForgeDirection.class))
					if(outputEnergy > 0)
					{
						TileEntity tileEntity = this.worldObj.getTileEntity(this.xCoord - direction.offsetX, this.yCoord - direction.offsetY,
								this.zCoord - direction.offsetZ);

						if(tileEntity instanceof IEnergyHandler)
							outputEnergy -= this.extractEnergy(ForgeDirection.UNKNOWN,
									((IEnergyHandler) tileEntity).receiveEnergy(direction.getOpposite(), outputEnergy, false), false);
					}
					else
						break;
		}
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return this.buffer.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		int usedEnergy = maxExtract;
		maxExtract -= this.buffer.extractEnergy(maxExtract, simulate);

		usedEnergy -= maxExtract;

		return usedEnergy;
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

	@Override
	public String getInventoryName()
	{
		return "Capacitor";
	}
}
