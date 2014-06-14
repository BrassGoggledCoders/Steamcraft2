/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [Mar 20, 2014, 10:41:55 AM]
 */
package steamcraft.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author Decebaldecebal
 */
public class ItemCanister extends BaseItem
{
	public String gas;

	public static int MAX_VISIBLE = 2500; // Visual representation of gas/steam through item damage
	
	public int max_amount;
	public static int max_rate = 20; // Maximum amount of steam/gas that can be inserted into this canister per tick

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.itemIcon = ir.registerIcon(LibInfo.PREFIX + "itemCanister");
	}

	public ItemCanister(String gas, int amount, int rate)
	{
		this.setMaxStackSize(1);
		this.setNoRepair();
		this.setMaxDamage(MAX_VISIBLE);
		this.setCreativeTab(Steamcraft.tabSC2);
		
		this.gas = gas;
		this.max_amount = amount;
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tabs, List list)
	{
		list.add(new ItemStack(item, 1, this.getMaxDamage()));
		list.add(getChargedItem());
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag)
	{
		list.add(String.format("%d/%d", new Object[] {getAmount(itemStack), max_amount }));
	}

	@SuppressWarnings("all")
	private ItemStack getChargedItem()
	{
		ItemStack charged = new ItemStack(this, 1, 0);
		NBTTagCompound tag = new NBTTagCompound();

		tag.setInteger("amount", this.max_amount);
		
		charged.setTagCompound(tag);

		return charged.copy();
	}

	private NBTTagCompound getOrCreateNBT(ItemStack stack)
	{
		if (stack.getTagCompound() == null)
		{
			NBTTagCompound tag = new NBTTagCompound();

			tag.setInteger("amount", ((MAX_VISIBLE - stack.getItemDamage()) * max_amount) / MAX_VISIBLE);

			stack.setTagCompound(new NBTTagCompound());
		}

		return stack.getTagCompound();
	}

	public int getAmount(ItemStack stack)
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("amount");
	}

	public int getEmptySpace(ItemStack stack)
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		return max_amount - compound.getInteger("amount");
	}

	public int addAmount(ItemStack stack, int amount)
	{
		int amountToAdd = Math.min(getEmptySpace(stack), Math.min(amount, max_rate));
		setAmount(stack, getAmount(stack) + amountToAdd);
		return amountToAdd;
	}

	public void setAmount(ItemStack stack, int amount)
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		int amountToAdd = Math.min(amount, max_amount);
		compound.setInteger("amount", amountToAdd);
		stack.setItemDamage(MAX_VISIBLE - ((amountToAdd * MAX_VISIBLE) / max_amount));
	}

	public boolean isFull(ItemStack stack)
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("amount") >= max_amount;
	}
	
	public boolean isEmpty(ItemStack stack)
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("amount") <= 0;
	}
}
