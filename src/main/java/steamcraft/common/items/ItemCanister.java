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

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import steamcraft.common.config.ConfigItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 *
 * @author Decebaldecebal
 */
public class ItemCanister extends BaseItem
{
	public static final int MAX_STEAM = 10000;
	public static final int MAX_STEAM_RATE = 20; //Maximum amount of steam that can be inserted into this canister per tick

	public ItemCanister()
	{
		super();
		this.maxStackSize = 1;
		this.setNoRepair();
		this.setMaxDamage(MAX_STEAM);
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(ConfigItems.itemCanisterSteam, 1, 0));
		l.add(new ItemStack(ConfigItems.itemCanisterSteam, 1, MAX_STEAM));
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag)
	{
			list.add(String.format("%d/%d", new Object[] {get(itemStack), MAX_STEAM}));
	}

	public static int get(ItemStack stack)
	{
		return stack.getItemDamage();
	}

	public static int getEmptySpace(ItemStack stack)
	{
		return MAX_STEAM - stack.getItemDamage();
	}

	public int add(ItemStack stack, int steam)
	{
		int steamToAdd = Math.min(getEmptySpace(stack), Math.min(steam, MAX_STEAM_RATE));
		set(stack, get(stack)+steamToAdd);
		return steamToAdd;
	}

	public static void set(ItemStack stack, int steam)
	{
		int steamToAdd = Math.min(steam, MAX_STEAM);
		stack.setItemDamage(MAX_STEAM - steamToAdd);
	}

	public boolean isFull(ItemStack stack)
	{
		return getDamage(stack) >= MAX_STEAM;
	}

	public boolean isEmpty(ItemStack stack) {
		return getDamage(stack) <= 0;
	}
}
