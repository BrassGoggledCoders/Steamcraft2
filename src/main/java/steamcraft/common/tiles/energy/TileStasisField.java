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

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;

import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author warlordjones, MrIbby
 *
 */
public class TileStasisField extends TileEntity implements IEnergyReceiver
{
	private static int energy = 2000;
	public static short transferRate = 80;

	public EnergyStorage buffer = new EnergyStorage(energy, transferRate);

	@Override
	public void updateEntity()
	{
		if(!this.worldObj.isRemote)
		{
			AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox((double) this.xCoord - 2, (double) this.yCoord - 2,
					(double) this.zCoord - 2, this.xCoord + 2, this.yCoord + 2, this.zCoord + 2);
			List list = this.worldObj.getEntitiesWithinAABB(EntityItem.class, axisalignedbb);

			for(Object obj : list)
			{
				EntityItem item = (EntityItem) obj;
				int itemNumber = item.getEntityItem().stackSize;
				if(this.buffer.getEnergyStored() >= 10 * itemNumber)
				{
					item.age = 5500;
					this.buffer.extractEnergy(10 * itemNumber, false);
				}
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
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
