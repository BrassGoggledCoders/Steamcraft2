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
 * File created @ [Jun 28, 2014, 4:58:53 PM]
 */
package steamcraft.common.items;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cofh.api.energy.IEnergyContainerItem;

/**
 * @author decebaldecebal
 *
 */
public class BaseElectricItem extends BaseItem implements IEnergyContainerItem
{
	protected int maxEnergy;
	protected short maxReceive;
	protected short maxSend;
	
	public BaseElectricItem(int maxEnergy, int maxReceive, int maxSend)
	{
		super();
		this.maxEnergy = maxEnergy*1000;
		this.maxReceive = (short)maxReceive;
		this.maxSend = (short)maxSend;
		this.setMaxStackSize(1);
		this.setMaxDamage(20);
		this.setHasSubtypes(false);
	}
	
	@SuppressWarnings("all")
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list)
    {
		list.add(getUnchargedItem());
		list.add(getChargedItem());
    }
	
	public ItemStack getUnchargedItem() 
	{
		ItemStack uncharged = new ItemStack(this, 1, 20);
		
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("energy", 0);
		
		uncharged.setTagCompound(tag);
		
		return uncharged.copy();
	}
	
	public ItemStack getChargedItem() 
	{
		ItemStack charged = new ItemStack(this, 1, 0);
		
		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("energy", this.maxEnergy);
		
		charged.setTagCompound(tag);
		
		return charged.copy();
	}
	
	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityplayer, List list, boolean flag) 
	{
		list.add("Energy: " + this.getEnergyStored(stack)/1000 + "k / " + this.maxEnergy/1000 + "k");
		list.add("Transfer(in/out): " + this.maxReceive + " / " + this.maxSend);
	}
	
	@Override
    public void onCreated(ItemStack stack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	stack = this.getUnchargedItem();
    }
	
	public void setEnergy(ItemStack stack, int energy)
	{
		NBTTagCompound tag = stack.getTagCompound();
		
		if(energy < 0)
			energy = 0;
		
		if(energy > this.maxEnergy)
			energy = this.maxEnergy;
		
		stack.setItemDamage(20 - (energy*20 / this.maxEnergy));
		
		tag.setInteger("energy", energy);
		
		stack.setTagCompound(tag);
	}
	
	@Override
	public int receiveEnergy(ItemStack itemStack, int maxReceive, boolean simulate)
	{
		int received = Math.min(this.maxEnergy - this.getEnergyStored(itemStack), maxReceive);
		received = Math.min(received, this.maxReceive);
		
		if(!simulate)
			this.setEnergy(itemStack, this.getEnergyStored(itemStack) + received);
		
		return received;
	}
	
	@Override
	public int extractEnergy(ItemStack itemStack, int maxExtract, boolean simulate)
	{
		int extracted = Math.min(this.getEnergyStored(itemStack), maxExtract);
		extracted = Math.min(extracted, this.maxSend);
		
		if(!simulate)
			this.setEnergy(itemStack, this.getEnergyStored(itemStack) - extracted);
		
		return extracted;
	}
	
	@Override
	public int getEnergyStored(ItemStack itemStack)
	{
		return itemStack.getTagCompound().getInteger("energy");
	}
	
	@Override
	public int getMaxEnergyStored(ItemStack container)
	{
		return this.maxEnergy;
	}
}
