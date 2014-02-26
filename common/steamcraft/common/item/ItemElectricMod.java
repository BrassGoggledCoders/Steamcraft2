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
 * File created @ [Feb 8, 2014, 3:11:43 PM]
 */
package common.steamcraft.common.item;

import ic2.api.item.IElectricItemManager;
import ic2.api.item.ISpecialElectricItem;

import java.util.List;

import common.steamcraft.common.core.helper.CompatHelper;
import mekanism.api.EnumColor;
import mekanism.api.energy.IEnergizedItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import universalelectricity.compatibility.Compatibility;
import universalelectricity.core.electricity.ElectricityDisplay;
import universalelectricity.core.item.IItemElectric;
import cofh.api.energy.IEnergyContainerItem;

/**
 * 
 * @author Someone else
 *
 */
public class ItemElectricMod extends ItemMod implements IEnergizedItem, IItemElectric, ISpecialElectricItem, IEnergyContainerItem {
	public int MAX_ELECTRICITY;
	public int VOLTAGE;
	public static double ENERGY_PER_REDSTONE = 10000.0D;	
	int toolTier;

	public ItemElectricMod(int id, int maxEnergy, int voltage, int tier) {
		super(id);
		this.MAX_ELECTRICITY = maxEnergy;
		this.VOLTAGE = voltage;
		this.toolTier = tier;
		this.setMaxStackSize(1);
		this.setMaxDamage(100);
		this.setNoRepair();
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityplayer, List list, boolean flag) {
		list.add(EnumColor.AQUA + "Energy: " + EnumColor.GREY + getEnergyDisplay(getEnergy(stack)));
		list.add(EnumColor.AQUA + "Voltage: " + EnumColor.GREY + getVoltage(stack) + "V");
	}

	@Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		itemstack = getUnchargedItem();
	}

	public ItemStack getUnchargedItem() {
		ItemStack charged = new ItemStack(this);
		charged.setItemDamage(100);
		return charged;
	}

	@Override
	public void getSubItems(int i, CreativeTabs tabs, List list) {
		ItemStack discharged = new ItemStack(this);
		discharged.setItemDamage(100);
		list.add(discharged);
		ItemStack charged = new ItemStack(this);
		this.setEnergy(charged, this.getMaxEnergy(charged));
		list.add(charged);
	}
	
	//UE
	@Override
	public float getVoltage(ItemStack itemStack) {
		return this.VOLTAGE;
	}
	
	@Override
	public float recharge(ItemStack itemStack, float energy, boolean doRecharge)
	{
		if (canReceive(itemStack)) {
			double energyNeeded = getMaxEnergy(itemStack) - getEnergy(itemStack);
			double toReceive = Math.min(energy, energyNeeded);

			if (doRecharge) {
				setEnergy(itemStack, getEnergy(itemStack) + toReceive);
			}

			return (float)(toReceive);
		}

		return 0.0F;
	}

	@Override
	public float discharge(ItemStack itemStack, float energy, boolean doDischarge) {
		if (canSend(itemStack)) {
			double energyRemaining = getEnergy(itemStack);
			double toSend = Math.min(energy, energyRemaining);

			if (doDischarge) {
				setEnergy(itemStack, getEnergy(itemStack) - toSend);
			}

			return (float)(toSend);
		}

		return 0.0F;
	}

	@Override
	public float getElectricityStored(ItemStack theItem) {
		return (float)(getEnergy(theItem));
	}

	@Override
	public float getMaxElectricityStored(ItemStack theItem) {
		return (float)(getMaxEnergy(theItem));
	}

	@Override
	public void setElectricity(ItemStack itemStack, float joules) {
		setEnergy(itemStack, joules);
	}

	@Override
	public float getTransfer(ItemStack itemStack) {
		return (float)(getMaxTransfer(itemStack));
	}

	//IC2
	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		return canSend(itemStack);
	}

	@Override
	public int getChargedItemId(ItemStack itemStack) {
		return this.itemID;
	}

	@Override
	public int getEmptyItemId(ItemStack itemStack)
	{
		return this.itemID;
	}

	@Override
	public int getMaxCharge(ItemStack itemStack) {
		return (int) (this.getEnergy(itemStack)*CompatHelper.UE_TO_IC2);
	}

	@Override
	public int getTier(ItemStack itemStack) {
		return this.toolTier;
	}

	@Override
	public int getTransferLimit(ItemStack itemStack) {
		return 32*this.toolTier;
	}
	

	public IElectricItemManager getManager(ItemStack itemStack) {
		return ItemManagerIC2.getManager(this);
	}

	//Mekanism
	@Override
	public double getEnergy(ItemStack stack) {
		if (stack.stackTagCompound == null) {
			return 0;
		}

		int electricityStored = stack.stackTagCompound.getInteger("electricity");
		stack.setItemDamage((int)Math.max(1.0D, Math.abs(electricityStored / getMaxEnergy(stack) * 100.0D - 100.0D)));

		return electricityStored;
	}

	@Override
	public void setEnergy(ItemStack itemStack, double amount) {
		if (itemStack.stackTagCompound == null) {
			itemStack.setTagCompound(new NBTTagCompound());
		}

		int electricityStored = (int) Math.max(Math.min(amount, getMaxEnergy(itemStack)), 0.0D);
		itemStack.stackTagCompound.setInteger("electricity", electricityStored);
		itemStack.setItemDamage((int)Math.max(1.0D, Math.abs(electricityStored / getMaxEnergy(itemStack) * 100.0D - 100.0D)));
	}

	@Override
	public double getMaxEnergy(ItemStack itemStack) {
		return this.MAX_ELECTRICITY;
	}

	@Override
	public double getMaxTransfer(ItemStack itemStack) {
		return getMaxEnergy(itemStack) * 0.005D;
	}

	@Override
	public boolean canReceive(ItemStack itemStack) {
		return true;
	}

	@Override
	public boolean canSend(ItemStack itemStack) {
		return true;
	}

	@Override
	public int receiveEnergy(ItemStack theItem, int energy, boolean simulate) {
		if (canReceive(theItem)) {
			double energyNeeded = getMaxEnergy(theItem) - getEnergy(theItem);
			double toReceive = Math.min(energy * Compatibility.TE_RATIO, energyNeeded);

			if (!simulate) {
				setEnergy(theItem, getEnergy(theItem) + toReceive);
			}

			return (int)Math.round(toReceive * Compatibility.TO_TE_RATIO);
		}

		return 0;
	}

	@Override
	public int extractEnergy(ItemStack theItem, int energy, boolean simulate) {
		if (canSend(theItem)) {
			double energyRemaining = getEnergy(theItem);
			double toSend = Math.min(energy * Compatibility.TE_RATIO, energyRemaining);

			if (!simulate) {
				setEnergy(theItem, getEnergy(theItem) - toSend);
			}

			return (int)Math.round(toSend * Compatibility.TO_TE_RATIO);
		}

		return 0;
	}

	@Override
	public boolean isMetadataSpecific()
	{
		return false;
	}

	//TE
	@Override
	public int getEnergyStored(ItemStack theItem) {
		return (int)Math.round(getEnergy(theItem) * Compatibility.TO_TE_RATIO);
	}

	@Override
	public int getMaxEnergyStored(ItemStack theItem)
	{
		return (int)Math.round(getMaxEnergy(theItem) * Compatibility.TO_TE_RATIO);
	}

	public static String getEnergyDisplay(double energy) {
		return ElectricityDisplay.getDisplayShort((float)(energy), ElectricityDisplay.ElectricUnit.JOULES);
	}

	public ItemStack getChargedItem() {
		ItemStack charged = new ItemStack(this);
		charged.setItemDamage(0);
		return charged;
	}
}
