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
package steamcraft.common.items.electric;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import steamcraft.common.items.tools.ItemModTool;
import boilerplate.common.IEnergyItem;

public class ItemElectricTool extends ItemModTool implements IEnergyItem
{
	private Random random = new Random();
	protected int maxEnergy = 0;
	protected short maxReceive = 0;
	protected short maxSend = 0;
	protected int energyPerBlock = 0;

	protected ItemElectricTool(float damage, ToolMaterial toolMat, Block[] blockArray)
	{
		super(damage, toolMat, blockArray);
		maxEnergy = maxEnergy * 1000;
		maxReceive = maxReceive;
		maxSend = maxSend;
		setMaxStackSize(1);
		setMaxDamage(20);
		setHasSubtypes(false);
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
		tag.setInteger("energy", maxEnergy);

		charged.setTagCompound(tag);

		return charged.copy();
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer entityplayer, List list, boolean flag)
	{
		list.add("Energy: " + (getEnergyStored(stack) / 1000) + "k / " + (maxEnergy / 1000) + "k");
		list.add("Transfer(in/out): " + maxReceive + " / " + maxSend);
	}

	@Override
	public void onCreated(ItemStack stack, World par2World, EntityPlayer par3EntityPlayer)
	{
		stack = getUnchargedItem();
	}

	public void setEnergy(ItemStack stack, int energy)
	{
		NBTTagCompound tag = stack.getTagCompound();

		if (energy < 0)
			energy = 0;

		if (energy > maxEnergy)
			energy = maxEnergy;

		stack.setItemDamage(20 - ((energy * 20) / maxEnergy));

		tag.setInteger("energy", energy);

		stack.setTagCompound(tag);
	}

	@Override
	public int receiveEnergy(ItemStack itemStack, int maxReceive, boolean simulate)
	{
		int received = Math.min(maxEnergy - getEnergyStored(itemStack), maxReceive);
		received = Math.min(received, this.maxReceive);

		if (!simulate)
			setEnergy(itemStack, getEnergyStored(itemStack) + received);

		return received;
	}

	@Override
	public int extractEnergy(ItemStack itemStack, int maxExtract, boolean simulate)
	{
		int extracted = Math.min(getEnergyStored(itemStack), maxExtract);
		extracted = Math.min(extracted, maxSend);

		if (!simulate)
			setEnergy(itemStack, getEnergyStored(itemStack) - extracted);

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
		return maxEnergy;
	}

	@Override
	public short getMaxSend()
	{
		return maxSend;
	}

}
