/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [Apr 8, 2014, 3:26:58 PM]
 */
package steamcraft.common.tiles;

import java.util.Random;

import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

/**
 * The Class TileLightningRod.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class TileLightningRod extends TileEntity implements IEnergyHandler
{
	private EnergyStorage buffer = new EnergyStorage(30000, 10000);

	@Override
	public void updateEntity()
	{
		if(worldObj.getWorldInfo().isThundering() && worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord))
		{
			Random random = new Random();
			int chance = random.nextInt(500);
			if(chance == 0)
			{
				worldObj.addWeatherEffect((new EntityLightningBolt(worldObj, xCoord, yCoord, zCoord)));
				buffer.receiveEnergy(10000, false);
				System.out.print(buffer.getEnergyStored());
			}
		}
	}
	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return false;
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

}
