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
 * File created @ [Feb 4, 2014, 5:13:56 PM]
 */
package common.steamcraft.common.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 *
 * @author MrArcane111, decebaldecebal & general3214
 *
 */
public class ItemCanister extends ItemMod
{
	public String gas;
	public static final float MAX_STEAM = 150000.0F;
	public static final int MAX_STEAM_VISIBLE = 2500;

	public ItemCanister(int id, int durability, String energy) {
		super(id);
		this.maxStackSize = 1;
		this.setNoRepair();
		this.setMaxDamage(2501);
		//gas = energy;
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tab, List list) 
	{
		list.add(new ItemStack(itemID, 1, getMaxDamage()));
		list.add(new ItemStack(itemID, 1, 1));
	}
	
	private static NBTTagCompound getOrCreateNBT(ItemStack stack) {
		if (stack.getTagCompound() == null) {
			stack.setTagCompound(new NBTTagCompound());
			stack.getTagCompound().setFloat("Steam", (ModItems.canisterSteam.getMaxDamage() - stack.getItemDamage()) * 60.0F);
		}

		return stack.getTagCompound();
	}

	public static float getSteam(ItemStack stack) {
		NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getFloat("Steam");
	}

	public static void setSteam(ItemStack stack, float steam) {
		NBTTagCompound compound = getOrCreateNBT(stack);
		float steamToAdd = Math.max(Math.min(steam, 150000.0F), 0.0F);
		compound.setFloat("Steam", steamToAdd);
		stack.setItemDamage(ModItems.canisterSteam.getMaxDamage() - Math.round(steamToAdd / 60.0F));
	}

	public static boolean isFull(ItemStack stack) {
		NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getFloat("Steam") >= 150000.0F;
	}

	public static boolean isEmpty(ItemStack stack) {
		NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getFloat("Steam") <= 0.0F;
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
		list.add(String.format("%d/%d", new Object[] { Integer.valueOf(getMaxDamage() - itemStack.getItemDamage()), Integer.valueOf(2500) }));
	}
}
