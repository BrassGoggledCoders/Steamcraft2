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

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import steamcraft.common.InitPackets;
import steamcraft.common.packets.UpdateClientsideInventoryPacket;
import boilerplate.common.baseclasses.BaseTileWithInventory;
import boilerplate.steamapi.item.IEnergyItem;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author decebaldecebal
 * 
 */
public class TileBattery extends BaseTileWithInventory implements IEnergyHandler
{
	private static int initialEnergy = 5000;
	private static short initialTransferRate = 40;

	private byte ticksSinceUpdate = 0;

	public int totalEnergy = 0;
	public int maxEnergy = 0;
	public short transferRate = initialTransferRate;

	public EnergyStorage buffer = new EnergyStorage(initialEnergy, initialTransferRate);

	public TileBattery()
	{
		super(4);
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
		// if(maxEnergy > 0)
		return (this.totalEnergy + this.buffer.getEnergyStored()) / ((this.maxEnergy + initialEnergy) / 1000) / par1;
		// else return 0;
	}

	@Override
	public void updateEntity()
	{

		if(!this.worldObj.isRemote)
		{
			int[] ids = new int[this.inventory.length];
			for(int i = 0; i < this.inventory.length; i++)
			{
				if(this.inventory[i] != null)
					Item.getIdFromItem(this.inventory[i].getItem());
			}
			InitPackets.network.sendToAllAround(new UpdateClientsideInventoryPacket(xCoord, yCoord, zCoord, ids), new TargetPoint(
					worldObj.provider.dimensionId, xCoord, yCoord, zCoord,
					10));
			this.ticksSinceUpdate++;

			if(this.ticksSinceUpdate > 50)
			{
				this.ticksSinceUpdate = 0;
				this.updateEnergyFromInv();
			}

			if(this.buffer.getEnergyStored() > 0)
				this.chargeItems();

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

	private void chargeItems()
	{
		for(ItemStack stack : this.inventory)
			if(stack != null)
			{
				IEnergyItem item = (IEnergyItem) stack.getItem();

				this.buffer.modifyEnergyStored(-item.receiveEnergy(stack, this.buffer.getEnergyStored(), false));

				if(this.buffer.getEnergyStored() < 0)
					break;
			}
	}

	public void updateEnergyFromInv()
	{
		this.maxEnergy = 0;
		this.totalEnergy = 0;
		this.transferRate = initialTransferRate;

		for(ItemStack stack : this.inventory)
			if(stack != null)
			{
				IEnergyItem item = (IEnergyItem) stack.getItem();

				this.maxEnergy += item.getMaxEnergyStored(stack);
				this.totalEnergy += item.getEnergyStored(stack);
				this.transferRate += item.getMaxSend();
			}

		this.buffer.setMaxTransfer(this.transferRate);
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

		if(maxExtract != 0)
			for(ItemStack stack : this.inventory)
				if(stack != null)
				{
					IEnergyItem item = (IEnergyItem) stack.getItem();

					if(maxExtract > 0)
						maxExtract -= item.extractEnergy(stack, maxExtract, simulate);
					else
						break;
				}

		usedEnergy -= maxExtract;

		return usedEnergy;
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return this.buffer.getEnergyStored() + this.totalEnergy;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return this.buffer.getMaxEnergyStored() + this.maxEnergy;
	}

	@Override
	public String getInventoryName()
	{
		return "Battery Bank";
	}
}
