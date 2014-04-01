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

import mekanism.api.EnumColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cofh.api.energy.IEnergyContainerItem;

import common.steamcraft.common.util.EnergyUtils;

/**
 * Base class for electric items
 * 
 * @author Decebaldecebal
 *
 */
public class ItemElectricMod extends ItemMod implements ISpecialElectricItem, IElectricItemManager, IEnergyContainerItem
{
	protected int maxEnergy;
	protected byte tier;
	
	public ItemElectricMod(int id, int maxEnergy, byte tier) 
	{
		super(id);
		this.tier = tier;
		this.maxEnergy = maxEnergy;
		this.setMaxStackSize(1);
		this.setMaxDamage(20);
		this.setHasSubtypes(false);
	}

	@SuppressWarnings("all")
	@Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(getChargedItem());
		par3List.add(getUnchargedItem());
    }
	
	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityplayer, List list, boolean flag) 
	{
		list.add(EnumColor.GREY + "Power Tier: " + this.getTier(stack));
		list.add("");
		list.add(EnumColor.AQUA + "Energy: " + EnumColor.GREY + this.getEnergy(stack) + " / " + this.maxEnergy);
	}

	public int getEnergy(ItemStack stack)
	{
		return stack.getTagCompound().getInteger("energy");
	}
	
	public void setEnergy(ItemStack stack, int energy)
	{
		NBTTagCompound tag = stack.getTagCompound();
		
		int realEn = energy;
		
		if(realEn < 0)
			realEn = 0;
		
		if(realEn > this.maxEnergy)
			realEn = this.maxEnergy;
		
		stack.setItemDamage(20 - (realEn*20 / this.maxEnergy));
		
		tag.setInteger("energy", realEn);
		
		stack.setTagCompound(tag);
	}
	
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	par1ItemStack = this.getUnchargedItem();
    }
	
	public ItemStack getUnchargedItem() 
	{
		ItemStack charged = new ItemStack(this.itemID, 1, 20);
		
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("energy", 0);
		tag.setByte("energyTier", this.tier);
		
		charged.setTagCompound(tag);
		return charged.copy();
	}
	
	public ItemStack getChargedItem() 
	{
		ItemStack uncharged = new ItemStack(this.itemID, 1, 0);
		
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("energy", this.maxEnergy);
		tag.setInteger("energyTier", this.tier);
		
		uncharged.setTagCompound(tag);
		
		return uncharged.copy();
	}
	
	/**
	 * 
	 * TE Compatibility
	 * 
	 */
	
	@Override
	public int receiveEnergy(ItemStack itemStack, int maxReceive, boolean simulate)
	{
		/*
		int received = Math.min(this.maxEnergy - this.getEnergy(itemStack), maxReceive);
		received = Math.min(received, this.getTransferLimit(itemStack));
		
		if(!simulate)
			this.setEnergy(itemStack, this.getEnergy(itemStack) + received);
		*/
		
		return EnergyUtils.fromIC2(this.charge(itemStack, EnergyUtils.toIC2(maxReceive), this.getTier(itemStack), false, simulate));
	}
	
	@Override
	public int extractEnergy(ItemStack itemStack, int maxExtract, boolean simulate)
	{
		return EnergyUtils.fromIC2(this.discharge(itemStack, EnergyUtils.toIC2(maxExtract), this.getTier(itemStack), false, simulate));
	}
	
	@Override
	public int getEnergyStored(ItemStack itemStack)
	{
		return this.getEnergy(itemStack);
	}
	
	@Override
	public int getMaxEnergyStored(ItemStack container)
	{
		return this.maxEnergy;
	}
	
	
	/**
	 * 
	 * IC2 Compatibility
	 * 
	 */
	
	@Override
	public boolean canProvideEnergy(ItemStack itemStack)
	{
		return true;
	}
	
	@Override
	public	int getChargedItemId(ItemStack itemStack)
	{
		return this.itemID;
	}

	@Override
	public int getEmptyItemId(ItemStack itemStack)
	{
		return this.itemID;
	}
	
	@Override
	public int getMaxCharge(ItemStack itemStack)
	{
		return EnergyUtils.toIC2(this.maxEnergy);
	}
	
	@Override
	public int getTier(ItemStack itemStack)
	{
		return itemStack.getTagCompound().getByte("energyTier");
	}
	
	@Override
	public int getTransferLimit(ItemStack itemStack)
	{
		return 32;
	}

	@Override
	public IElectricItemManager getManager(ItemStack itemStack) 
	{
		return this;
	}

	/**
	 * 
	 * IC2 Custom Item Manager 
	 * 
	 */
	
	@Override
	public int charge(ItemStack itemStack, int amount, int tier,
			boolean ignoreTransferLimit, boolean simulate) 
	{
		int received = Math.min(this.maxEnergy - this.getEnergy(itemStack), EnergyUtils.fromIC2(amount));
		
		if(!ignoreTransferLimit)
			received = Math.min(received, EnergyUtils.fromIC2(this.getTransferLimit(itemStack)));
		
		if(!simulate)
			this.setEnergy(itemStack, this.getEnergy(itemStack) + received);
		
		return EnergyUtils.toIC2(received);
	}

	@Override
	public int discharge(ItemStack itemStack, int amount, int tier,
			boolean ignoreTransferLimit, boolean simulate) 
	{
		int received = Math.min(this.getEnergy(itemStack), EnergyUtils.fromIC2(amount));
		
		if(!ignoreTransferLimit)
			received = Math.min(received, EnergyUtils.fromIC2(this.getTransferLimit(itemStack)));
		
		if(!simulate)
			this.setEnergy(itemStack, this.getEnergy(itemStack) - received);
		
		return EnergyUtils.toIC2(received);
	}

	@Override
	public int getCharge(ItemStack itemStack) 
	{
		return this.getEnergy(itemStack);
	}

	@Override
	public boolean canUse(ItemStack itemStack, int amount) 
	{
		return false;
	}

	@Override
	public boolean use(ItemStack itemStack, int amount, EntityLivingBase entity) 
	{
		return false;
	}

	@Override
	public void chargeFromArmor(ItemStack itemStack, EntityLivingBase entity) 
	{		
	}

	@Override
	public String getToolTip(ItemStack itemStack) 
	{
		return null;
	}
}
