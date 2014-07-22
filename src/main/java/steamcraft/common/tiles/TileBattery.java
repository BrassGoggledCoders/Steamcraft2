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
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author decebaldecebal
 *
 */
public class TileBattery extends BaseTileWithInventory implements IEnergyHandler
{
	private static int initialEnergy = 400000;
	private static short initialTransferRate = 80;

	private byte ticksSinceUpdate = 0;

	public int totalEnergy = 0;
	public int maxEnergy = 0;
	public short transferRate = initialTransferRate;

	public EnergyStorage buffer = new EnergyStorage(initialEnergy, initialTransferRate);

	public TileBattery()
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
		return ((this.totalEnergy+buffer.getEnergyStored()) / ((this.maxEnergy+initialEnergy)/1000)) / par1;
	}

	@Override
	public void updateEntity()
	{
		if (!worldObj.isRemote)
		{
			ticksSinceUpdate++;

			if(ticksSinceUpdate > 50)
			{
				ticksSinceUpdate = 0;
				updateEnergyFromInv();
			}

			if(buffer.getEnergyStored() > 0)
				this.chargeItems();

			short outputEnergy = (short) this.extractEnergy(ForgeDirection.UNKNOWN, this.transferRate, true);

			if(outputEnergy > 0)
			{
				for (ForgeDirection direction : EnumSet.allOf(ForgeDirection.class))
					if(outputEnergy > 0)
					{
						TileEntity tileEntity = worldObj.getTileEntity(xCoord - direction.offsetX, yCoord - direction.offsetY, zCoord - direction.offsetZ);

						if(tileEntity instanceof IEnergyHandler)
						{
							outputEnergy -= this.extractEnergy(ForgeDirection.UNKNOWN, ((IEnergyHandler) tileEntity).receiveEnergy(direction.getOpposite(), outputEnergy, false), false);
						}
					}
					else
						break;
			}
		}
	}

	private void chargeItems()
	{
		for(ItemStack stack : inventory)
		{
			if(stack!=null)
			{
				IEnergyItem item = (IEnergyItem) stack.getItem();

				buffer.modifyEnergyStored(-item.receiveEnergy(stack, buffer.getEnergyStored(), false));

				if(buffer.getEnergyStored() < 0)
					break;
			}
		}
	}

	public void updateEnergyFromInv()
	{
		this.maxEnergy = 0;
		this.totalEnergy = 0;
		this.transferRate = initialTransferRate;

		for(ItemStack stack : inventory)
		{
			if(stack!=null)
			{
				IEnergyItem item = (IEnergyItem) stack.getItem();

				this.maxEnergy += item.getMaxEnergyStored(stack);
				this.totalEnergy += item.getEnergyStored(stack);
				this.transferRate += item.getMaxSend();
			}
		}

		buffer.setMaxTransfer(this.transferRate);
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

		if(maxExtract != 0)
			for(ItemStack stack : inventory)
			{
				if(stack!=null)
				{
					IEnergyItem item = (IEnergyItem) stack.getItem();

					if(maxExtract > 0)
					{
						maxExtract -= item.extractEnergy(stack, maxExtract, simulate);
					}
					else
						break;
				}
			}

		usedEnergy -= maxExtract;

		return usedEnergy;
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return buffer.getEnergyStored() + this.totalEnergy;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return buffer.getMaxEnergyStored() + this.maxEnergy;
	}

	@Override
	public String getInventoryName()
	{
		return "Battery Bank";
	}
}
