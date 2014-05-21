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
 * File created @ [Apr 8, 2014, 7:37:07 PM]
 */
package steamcraft.common.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class ItemKettle extends Item
{
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon)
	{
		this.itemIcon = icon.registerIcon(LibInfo.PREFIX + "kettle");
	}
	
	public ItemKettle(int id, int damage)
	{
		super(id);
		this.maxStackSize = 1;
		this.setMaxDamage(damage);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
	{
		if ((is.getItem().itemID == ConfigItems.itemKettleHot.itemID) && (is.getItemDamage() < is.getItem().getMaxDamage() - 5))
		{
			if (this.getStackPosition(player.inventory, ConfigItems.itemKettleEmpty) > -1)
			{
				// ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(mod_Steamcraft.ach_TimeForACuppa);
				player.inventory.setInventorySlotContents(this.getStackPosition(player.inventory, ConfigItems.itemTeacupEmpty), new ItemStack(ConfigItems.itemTeacupFull, 1));
				is.damageItem((is.getItem().getMaxDamage() / 3) - 1, player);
			}
			
			if (is.getItemDamage() >= is.getItem().getMaxDamage() - 5)
			{
				is = new ItemStack(ConfigItems.itemKettleEmpty, 1, is.getItemDamage());
			}
		}

		return is;
	}

	private int getStackPosition(InventoryPlayer inventory, Item item)
	{
		for (int i = 0; i < inventory.getSizeInventory(); i++)
		{
			if ((inventory.getStackInSlot(i) != null) && (item == inventory.getStackInSlot(i).getItem()))
			{
				return i;
			}
		}

		return -1;
	}
	
	
}
