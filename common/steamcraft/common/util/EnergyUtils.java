package common.steamcraft.common.util;

import ic2.api.item.IElectricItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cofh.api.energy.IEnergyContainerItem;


/**
 * 
 * This class holds utilities related to power management and interaction.
 * Use this class to store and interact with energy.
 * 
 * @author Decebaldecebal
 *
 */
public class EnergyUtils 
{
	protected int storedEnergy;
	protected int maxEnergy;
	protected int transferRate;
	
	/* 
	 * Constructors
	 * 
	 */
	public EnergyUtils(int maxEnergy, int transferRate)
	{
		this.maxEnergy = maxEnergy;
		this.transferRate = transferRate;
	}
	
	/*
	 * Get/set data 
	 * 
	 */
	
	public void readFromNBT(NBTTagCompound nbt)
	{	
		this.setStoredEnergy(nbt.getInteger("storedEn"));
	}
	
	public void writeToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("storedEn", this.getStoredEnergy());
	}
	
	/* 
	 * Set values
	 * 
	 */
	
	public void setMaxEnergy(int maxEnergy)
	{
		this.maxEnergy = maxEnergy;

		if (storedEnergy > maxEnergy)
		{
			storedEnergy = maxEnergy;
		}
	}
	
	public void setStoredEnergy(int energy)
	{
		this.storedEnergy = energy;
		
		if(this.storedEnergy > this.maxEnergy)
			this.storedEnergy = this.maxEnergy;
		else if(this.storedEnergy < 0)
			this.storedEnergy = 0;
	}

	public void setTransferRate(int transferRate)
	{
		this.transferRate = transferRate;
	}
	
	/* 
	 * Get values 
	 * 
	 */
	
	public int getStoredEnergy()
	{
		return this.storedEnergy;
	}
	
	public int getMaxEnergy()
	{
		return this.maxEnergy;
	}
	
	public int getTransferRate()
	{
		return this.transferRate;
	}
	
	public int getEmptySpace()
	{
		return this.getMaxEnergy() - this.getStoredEnergy();
	}
	
	
	/* Utilities */
	
	//Modifies the energy stored directly.Does not check transfer limits.
	public void modifyStoredEnergy(int energy)
	{
		this.storedEnergy += energy;
		
		if(this.storedEnergy > this.maxEnergy)
			this.storedEnergy = this.maxEnergy;
		else if(this.storedEnergy < 0)
			this.storedEnergy = 0;
	}
	
	//Should be called when receiving energy.Checks transfer limits.
	public int receiveEnergy(int energy, boolean doReceive)
	{
		int receivedEnergy = Math.min(this.getEmptySpace(), Math.min(this.transferRate, energy));
		
		if(doReceive)
			this.storedEnergy += receivedEnergy;
		
		return receivedEnergy;
	}
	
	//Should be called when extracting energy.Checks transfer limits.
	public int extractEnergy(int energy, boolean doExtract)
	{
		int receivedEnergy = Math.min(this.storedEnergy, Math.min(this.transferRate, energy));
		
		if(doExtract)
			this.storedEnergy -= receivedEnergy;
		
		return receivedEnergy;
	}
	
	public boolean isFull()
	{
		return this.getStoredEnergy() >= this.getMaxEnergy();
	}
	
	public boolean isEmpty()
	{
		return this.getStoredEnergy() > 0;
	}
	
	/**
	 * 
	 * Convertions between different power system's units.
	 *
	 */
	
	public class EnergyUnits
	{
		/**
		 * Same values that MFR uses
		 * How much energy is received is SC Power Units from other mods energy units.
		 * Need to finds some name for the SC unit
		 * To make things easier, the SC Unit is equivalent to RF from TE
		 */	
		public static final int ENERGY_IC2 = 4;
		public static final int ENERGY_BC = 10;
	}
	
	/**
	 * 
	 * Energy conversion
	 * 
	 */
	public static int toIC2(int energy)
	{
		return energy/EnergyUnits.ENERGY_IC2;
	}
	
	public static int toBC(int energy)
	{
		return energy/EnergyUnits.ENERGY_BC;
	}
	
	public static int fromIC2(int energy)
	{
		return energy*EnergyUnits.ENERGY_IC2;
	}
	
	public static int fromBC(int energy)
	{
		return energy*EnergyUnits.ENERGY_BC;
	}
	
	
	/**
	 * 
	 * Utilities functions
	 * 
	 */
	public static boolean canBeDischarged(ItemStack itemstack)
	{
		return (itemstack.getItem() instanceof IElectricItem && ((IElectricItem)itemstack.getItem()).canProvideEnergy(itemstack)) ||  
				(itemstack.getItem() instanceof IEnergyContainerItem && ((IEnergyContainerItem)itemstack.getItem()).extractEnergy(itemstack, 1, true) != 0);
				//itemstack.itemID == Item.redstone.itemID;
	}

	public static boolean canBeCharged(ItemStack itemstack)
	{
		return itemstack.getItem() instanceof IElectricItem ||
				(itemstack.getItem() instanceof IEnergyContainerItem && ((IEnergyContainerItem)itemstack.getItem()).receiveEnergy(itemstack, 1, true) != 0);
	}
}
