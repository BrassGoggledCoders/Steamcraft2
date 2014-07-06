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
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

/**
 * @author warlordjones
 *
 */
public class TileCopperWire extends TileEntity implements IEnergyHandler
{
	private static byte RFPerTick = 100;
	private EnergyStorage buffer = new EnergyStorage(100, RFPerTick);

	@Override
	public void updateEntity()
	{
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
			System.out.print(buffer);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		buffer.readFromNBT(tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		buffer.writeToNBT(tag);
	}
	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return buffer.receiveEnergy(maxReceive, simulate);
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
