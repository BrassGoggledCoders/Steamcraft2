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
package steamcraft.common.items.tools;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import steamcraft.common.items.electric.ItemElectricTool;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemElectricDrill extends ItemElectricTool
{
	private Random random = new Random();
	protected int maxEnergy = 80;
	protected short maxReceive = 80;
	protected short maxSend = 80;
	protected int energyPerBlock = 1000;

	public ItemElectricDrill(ToolMaterial mat)
	{
		super(1, mat, ItemDrill.blocksEffectiveAgainst);
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

	@Override
	public ItemStack getUnchargedItem()
	{
		ItemStack uncharged = new ItemStack(this, 1, 20);

		NBTTagCompound tag = new NBTTagCompound();
		tag.setInteger("energy", 0);

		uncharged.setTagCompound(tag);

		return uncharged.copy();
	}

	@Override
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
		list.add("Efficiency: " + toolMaterial.getEfficiencyOnProperMaterial());
	}

	@Override
	public void onCreated(ItemStack stack, World par2World, EntityPlayer par3EntityPlayer)
	{
		stack = getUnchargedItem();
	}

	@Override
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
	public boolean onBlockDestroyed(ItemStack stack, World world, Block p_150894_3_, int x, int y, int z, EntityLivingBase living)
	{
		if (living instanceof EntityPlayer)
		{
			extractEnergy(stack, energyPerBlock, false);

			stack.damageItem(1, living);
			world.playSoundAtEntity(living, LibInfo.PREFIX + "drill.steam", 0.6F, 1.0F);
			world.spawnParticle("smoke", x + 0.5, y + 0.5, z + 0.5, random.nextGaussian(), random.nextGaussian(), random.nextGaussian());
			return true;
		}

		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon)
	{
		itemIcon = icon.registerIcon(LibInfo.PREFIX + "tools/" + this.getUnlocalizedName().substring(5));
	}

	@SuppressWarnings("all")
	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack)
	{
		for (Block element : ItemDrill.blocksEffectiveAgainst)
			if (element == block)
				return true;

		return false;
	}
}
