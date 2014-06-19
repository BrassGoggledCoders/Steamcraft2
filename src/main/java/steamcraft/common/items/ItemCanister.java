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
 *
 * @author Decebaldecebal
 */
public class ItemCanister extends BaseItem
{
	public static final int MAX_VISIBLE = 2500; //Visual represntation of gas/steam through item damage

	public static final int MAX_STEAM = 10000; //Isn't this an excesivly large number?
	public static final int MAX_STEAM_RATE = 20; //Maximum amount of steam that can be inserted into this canister per tick

	public ItemCanister()
	{
		super();
		this.maxStackSize = 1;
		this.setNoRepair();
		this.setMaxDamage(MAX_VISIBLE);
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		l.add(getChargedItem());
		//list.add(new ItemStack(ConfigItems, 1, this.getMaxDamage()));
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag)
	{
			list.add(String.format("%d/%d", new Object[] {get(itemStack), MAX_STEAM}));
	}

	private ItemStack getChargedItem()
	{
		ItemStack charged = new ItemStack(ConfigItems.itemCanisterSteam);
		NBTTagCompound tag = new NBTTagCompound();

			tag.setInteger("steam", MAX_STEAM);

		charged.setTagCompound(tag);

		return charged.copy();
	}

	private static NBTTagCompound getOrCreateNBT(ItemStack stack)
	{
		if (stack.getTagCompound() == null)
		{
			NBTTagCompound tag = new NBTTagCompound();
				tag.setInteger("steam", (MAX_VISIBLE - stack.getItemDamage()) * MAX_STEAM / MAX_VISIBLE);

			stack.setTagCompound(new NBTTagCompound());
		}

		return stack.getTagCompound();
	}

	public static int get(ItemStack stack)
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("steam");
	}

	public static int getEmptySpace(ItemStack stack)
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		return MAX_STEAM - compound.getInteger("steam");
	}

	public int add(ItemStack stack, int steam)
	{
		int steamToAdd = Math.min(getEmptySpace(stack), Math.min(steam, MAX_STEAM_RATE));
		set(stack, get(stack)+steamToAdd);
		return steamToAdd;
	}

	public static void set(ItemStack stack, int steam)
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		int steamToAdd = Math.min(steam, MAX_STEAM);
		compound.setInteger("steam", steamToAdd);
		stack.setItemDamage(MAX_VISIBLE - steamToAdd * MAX_VISIBLE / MAX_STEAM);
	}

	public boolean isFull(ItemStack stack)
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("steam") >= MAX_STEAM;
	}

	public boolean isEmpty(ItemStack stack) {
		NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("steam") <= 0;
	}
}
