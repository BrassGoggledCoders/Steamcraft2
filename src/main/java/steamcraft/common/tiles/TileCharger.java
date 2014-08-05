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

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import boilerplate.common.IEnergyItem;
import boilerplate.common.baseclasses.BaseTileWithInventory;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author decebaldecebal
 * 
 */
public class TileCharger extends BaseTileWithInventory implements IEnergyHandler
{
	private static int energy = 100000;
	public static short transferRate = 80;

	public EnergyStorage buffer = new EnergyStorage(energy, transferRate);

	public TileCharger()
	{
		super((byte) 6);
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

	@SideOnly(Side.CLIENT)
	public int getEnergyScaled(int par1)
	{
		return ((buffer.getEnergyStored()) / buffer.getMaxEnergyStored()) / par1;
	}

	@Override
	public void updateEntity()
	{
		if (!worldObj.isRemote)
		{

			if (buffer.getEnergyStored() > 0)
				chargeItems();

			short inputEnergy = (short) extractEnergy(ForgeDirection.UNKNOWN, transferRate, true);

			if (inputEnergy > 0)
				for (ForgeDirection direction : EnumSet.allOf(ForgeDirection.class))
					if (inputEnergy > 0)
					{
						TileEntity tileEntity = worldObj.getTileEntity(xCoord - direction.offsetX, yCoord - direction.offsetY, zCoord
								- direction.offsetZ);

						if (tileEntity instanceof IEnergyHandler)
							inputEnergy -= receiveEnergy(ForgeDirection.UNKNOWN,
									((IEnergyHandler) tileEntity).extractEnergy(direction.getOpposite(), inputEnergy, false), false);
					}
					else
						break;
		}
	}

	private void chargeItems()
	{
		for (ItemStack stack : inventory)
			if (stack != null)
			{
				IEnergyItem item = (IEnergyItem) stack.getItem();

				buffer.modifyEnergyStored(-item.receiveEnergy(stack, buffer.getEnergyStored(), false));

				if (buffer.getEnergyStored() < 0)
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
		return buffer.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		int usedEnergy = maxExtract;
		maxExtract -= buffer.extractEnergy(maxExtract, simulate);

		usedEnergy -= maxExtract;

		return usedEnergy;
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
	public String getInventoryName()
	{
		return "Charger";
	}
}
