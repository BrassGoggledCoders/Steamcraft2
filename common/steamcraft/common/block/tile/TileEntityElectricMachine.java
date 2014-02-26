/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 6, 2014, 7:20:17 PM]
 */
package common.steamcraft.common.block.tile;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;

import java.util.EnumSet;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.Type;
import cofh.api.energy.IEnergyContainerItem;

import common.steamcraft.common.core.helper.CompatHelper;
import common.steamcraft.common.util.EnergyUtils;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Decebaldecebal
 *
 */
public abstract class TileEntityElectricMachine extends TileEntityMachine implements IPowerReceptor //, IEnergyHandler, IEnergySink
{
	protected EnergyUtils energy;
	protected static PowerHandler powerHandler;
	
	protected TileEntityElectricMachine()
	{		
		//BC
		powerHandler = new PowerHandler(this, Type.MACHINE);
		powerHandler.configure(10, 300, 0.1F, 1000);
	}

	public static void discharge(int slotID, TileEntityElectricMachine tile)
	{
		if(CompatHelper.IC2Loaded && tile.inventory[slotID].getItem() instanceof IElectricItem)
		{
			IElectricItem item = (IElectricItem)tile.inventory[slotID].getItem();

			if(item.canProvideEnergy(tile.inventory[slotID]))
			{
				int gain = EnergyUtils.fromIC2((ElectricItem.manager.discharge(tile.inventory[slotID], EnergyUtils.toIC2(tile.energy.getEmptySpace()), 1, false, false)));
				tile.energy.modifyStoredEnergy(gain);
			}
		}
		else if(CompatHelper.TELoaded && tile.inventory[slotID].getItem() instanceof IEnergyContainerItem)
		{
			ItemStack itemStack = tile.inventory[slotID];
			IEnergyContainerItem item = (IEnergyContainerItem)tile.inventory[slotID].getItem();

			int itemEnergy = (int)Math.round(Math.min(Math.sqrt(item.getMaxEnergyStored(itemStack)), item.getEnergyStored(itemStack)));
			int toTransfer = Math.round(Math.min(itemEnergy, tile.energy.getEmptySpace()));

			tile.energy.modifyStoredEnergy(item.extractEnergy(itemStack, toTransfer, false));
		}

        if(tile.inventory[slotID].stackSize <= 0)
        {
            tile.inventory[slotID] = null;
        }
	}


	public static void charge(int slotID, TileEntityElectricMachine tile)
	{
		if(CompatHelper.IC2Loaded && tile.inventory[slotID].getItem() instanceof IElectricItem)
		{
			int sent = EnergyUtils.fromIC2(ElectricItem.manager.charge(tile.inventory[slotID], EnergyUtils.toIC2(tile.energy.getStoredEnergy()), 2, false, false));
			tile.energy.modifyStoredEnergy(-sent);
		}
		else if(CompatHelper.TELoaded && tile.inventory[slotID].getItem() instanceof IEnergyContainerItem)
		{
			ItemStack itemStack = tile.inventory[slotID];
			IEnergyContainerItem item = (IEnergyContainerItem)tile.inventory[slotID].getItem();

			int itemEnergy = (int)Math.round(Math.min(Math.sqrt(item.getMaxEnergyStored(itemStack)), item.getMaxEnergyStored(itemStack) - item.getEnergyStored(itemStack)));
			int toTransfer = Math.round(Math.min(itemEnergy, tile.energy.getStoredEnergy()));

			tile.energy.modifyStoredEnergy(-item.extractEnergy(itemStack, toTransfer, false));
		}
	}
	
	public EnumSet<ForgeDirection> getInputDirections()
	{
		return EnumSet.allOf(ForgeDirection.class);
	}

	public EnumSet<ForgeDirection> getOutputDirections()
	{
		return EnumSet.noneOf(ForgeDirection.class);
	}

	public boolean canConnect(ForgeDirection direction)
	{
		if (direction == null || direction.equals(ForgeDirection.UNKNOWN))
		{
			return false;
		}

		return this.getInputDirections().contains(direction) || this.getOutputDirections().contains(direction);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.energy.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		this.energy.writeToNBT(nbt);
	}
	
	//Gets the currently stored energy
	public int getEnergy()
	{
		return this.energy.getStoredEnergy();
	}
	
	public void setEnergy(int energy)
	{
		this.energy.setStoredEnergy(energy);
	}
	
	//Gets the requested energy per tick
	private int getEnergyRequested()
	{
		return Math.min(this.energy.getEmptySpace(), this.energy.getTransferRate());
	}
	
	/**
	 * 
	 * BuildCraft compatibility
	 * 
	 */
	
	public PowerHandler.PowerReceiver getPowerReceiver(ForgeDirection side)
	{
		if(this.getInputDirections().contains(side)) //If it can receive energy
		{
			if(getEnergyRequested() > 0)
			{
				this.powerHandler.configure(10, EnergyUtils.toBC(getEnergyRequested()), 0.1F, 1000);
				return this.powerHandler.getPowerReceiver(); 
			}
		    this.powerHandler.configure(0.0F, 0.0F, 1.0F, 1000.0F);
		    return null;
		}
	    this.powerHandler.configure(0.0F, 0.0F, 0F, 0F);
		return null;
	}
	
	@Override
	public void doWork(PowerHandler handler)
	{
		if(handler != null)
		{
			if(handler.useEnergy(0, EnergyUtils.toBC(getEnergyRequested()), false) > 0)
			{	
				int energyToReceive = EnergyUtils.fromBC(Math.round(handler.useEnergy(0, EnergyUtils.toBC(getEnergyRequested()), true)));
				this.energy.receiveEnergy(energyToReceive);
			}
		}
	}

	@Override
	public World getWorld()
	{
		return this.worldObj;
	}
	
	public boolean hasEnergy()
	{
		return this.getEnergy() > 0;
	}
	
	@SideOnly(Side.CLIENT)
	public int getEnergyScaled(int par1)
	{
		return (int) (this.getEnergy()*1000 / this.energy.getMaxEnergy() / par1);
	}
}
