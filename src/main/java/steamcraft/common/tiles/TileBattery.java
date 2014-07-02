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
 * File created @ [Jul 1, 2014, 3:07:44 PM]
 */
package steamcraft.common.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyContainerItem;
import cofh.api.energy.IEnergyHandler;

/**
 * @author decebaldecebal
 *
 */
public class TileBattery extends BaseTileWithInventory implements IEnergyHandler
{
	private static int initialEnergy = 1000000;
	private static short initialTransferRate = 400;
	
	private short ticksTillUpdate = 0;
	
	private int totalEnergy = 0;
	private int maxEnergy = 0;
	private short transferRate = initialTransferRate;
	
	private EnergyStorage buffer = new EnergyStorage(initialEnergy, initialTransferRate);
	
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
	
	@Override
	public void updateEntity()
	{
		if (!worldObj.isRemote)
		{
			ticksTillUpdate++;
			
			if(ticksTillUpdate > 200)
			{
				ticksTillUpdate = 0;
				updateEnergyFromInv();
			}
		}
	}
	
	private void updateEnergyFromInv()
	{
		this.maxEnergy = 0;
		this.totalEnergy = 0;
		
		byte i = 0;
		
		for(ItemStack stack : inventory)
		{
			if(stack!=null)
			{
				i++;
				
				IEnergyContainerItem item = (IEnergyContainerItem) stack.getItem();
				
				this.maxEnergy += item.getMaxEnergyStored(stack);
				this.totalEnergy += item.getEnergyStored(stack);
			}
		}
		
		switch(i)
		{
			case 0:
			case 1:
				this.transferRate = initialTransferRate;
			case 2:
			case 3:
				this.transferRate = 2500;
			default:
				this.transferRate = 10000;
		}
	}
	
	private int getEnergyFromItems()
	{
		return 0;
	}
	
	private int getMaxEnergyFromItems()
	{
		return 0;
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return 0;
	}
	
	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return buffer.getEnergyStored() + getEnergyFromItems();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return buffer.getMaxEnergyStored() + getMaxEnergyFromItems();
	}
}
