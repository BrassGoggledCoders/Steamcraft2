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
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class ItemCanister extends Item
{
	public String gas;
	public static final float MAX_STEAM = 160000.0F;
	public static final int MAX_STEAM_VISIBLE = 2500;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.itemIcon = ir.registerIcon(LibInfo.PREFIX + "itemCanister");
	}

	public ItemCanister(int id, int durability, String energy) 
	{
		this.setMaxStackSize(1);
		this.setNoRepair();
		this.setMaxDamage(2501);
		//gas = energy;
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tabs, List list) 
	{
		list.add(new ItemStack(item, 1, getMaxDamage()));
		list.add(new ItemStack(item, 1, 1));
	}

	private static NBTTagCompound getOrCreateNBT(ItemStack is) 
	{
		if (is.getTagCompound() == null) 
		{
			is.setTagCompound(new NBTTagCompound());
			is.getTagCompound().setFloat("Steam", (ConfigItems.itemSteamCanister.getMaxDamage() - is.getItemDamage()) * 60.0F);
		}

		return is.getTagCompound();
	}

	public static float getSteam(ItemStack is) 
	{
		NBTTagCompound tagCompound = getOrCreateNBT(is);
		return tagCompound.getFloat("Steam");
	}

	public static void setSteam(ItemStack is, float steam) 
	{
		NBTTagCompound tagCompound = getOrCreateNBT(is);
		float steamToAdd = Math.max(Math.min(steam, 150000.0F), 0.0F);
		tagCompound.setFloat("Steam", steamToAdd);
		is.setItemDamage(ConfigItems.itemSteamCanister.getMaxDamage() - Math.round(steamToAdd / 60.0F));
	}

	public static boolean isFull(ItemStack is) 
	{
		NBTTagCompound tagCompound = getOrCreateNBT(is);
		return tagCompound.getFloat("Steam") >= 150000.0F;
	}

	public static boolean isEmpty(ItemStack is) 
	{
		NBTTagCompound tagCompound = getOrCreateNBT(is);
		return tagCompound.getFloat("Steam") <= 0.0F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack is, EntityPlayer player, List list, boolean flag) 
	{
		list.add(String.format("%d/%d", new Object[] { Integer.valueOf(getMaxDamage() - is.getItemDamage()), Integer.valueOf(2500) }));
	}
}
