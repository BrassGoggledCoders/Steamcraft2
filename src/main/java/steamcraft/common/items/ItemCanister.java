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

// TODO: Auto-generated Javadoc
/**
 * The Class ItemCanister.
 *
 * @author Surseance (Johnny Eatmon), Decebaldecebal
 */
public class ItemCanister extends Item
{

	/** The gas. */
	public String gas;

	/** The Constant MAX_VISIBLE. */
	public static final int MAX_VISIBLE = 2500; // Visual representation of
												// gas/steam through item damage

	/** The Constant MAX_STEAM. */
												public static final int MAX_STEAM = 150000; // Isn't this an excessively
												// large number?
	/** The Constant MAX_STEAM_RATE. */
												public static final int MAX_STEAM_RATE = 20; // Maximum amount of steam that
													// can be inserted into this
													// canister per tick

	/** The Constant MAX_GAS. */
													public static final int MAX_GAS = 150000; // Isn't this an excessively large
												// number?
	/** The Constant MAX_GAS_RATE. */
												public static final int MAX_GAS_RATE = 20; // Maximum amount of gas that can
												// be inserted into this
												// canister per tick

	/* (non-Javadoc)
												 * @see net.minecraft.item.Item#registerIcons(net.minecraft.client.renderer.texture.IIconRegister)
												 */
												@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(final IIconRegister ir)
	{
		itemIcon = ir.registerIcon(LibInfo.PREFIX + "itemCanister");
	}

	/**
	 * Instantiates a new item canister.
	 *
	 * @param id the id
	 * @param gas the gas
	 */
	public ItemCanister(final int id, final String gas)
	{
		setNoRepair();
		this.gas = gas;
		setMaxDamage(MAX_VISIBLE);
		setCreativeTab(Steamcraft.tabSC2);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#getSubItems(net.minecraft.item.Item, net.minecraft.creativetab.CreativeTabs, java.util.List)
	 */
	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(final Item item, final CreativeTabs tabs,
			final List list)
	{
		list.add(new ItemStack(item, 1, getMaxDamage()));
		list.add(new ItemStack(item, 1, 1));
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#addInformation(net.minecraft.item.ItemStack, net.minecraft.entity.player.EntityPlayer, java.util.List, boolean)
	 */
	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack itemStack,
			final EntityPlayer player, final List list, final boolean flag)
	{
		if (itemStack.getItem() == ConfigItems.itemCanisterSteam)
		{
			list.add(String.format("%d/%d", new Object[] { getSteam(itemStack),
					MAX_STEAM }));
		}
		else
		{
			list.add(String.format("%d/%d", new Object[] { getGas(itemStack),
					MAX_GAS }));
		}
	}

	/**
	 * Gets the charged item.
	 *
	 * @return the charged item
	 */
	@SuppressWarnings("all")
	private ItemStack getChargedItem()
	{
		final ItemStack charged = new ItemStack(this, 1, 0);
		final NBTTagCompound tag = new NBTTagCompound();

		if (charged.getItem() == ConfigItems.itemCanisterSteam)
		{
			tag.setInteger("steam", MAX_STEAM);
		}
		else
		{
			tag.setInteger("gas", MAX_GAS);
		}

		charged.setTagCompound(tag);

		return charged.copy();
	}

	/**
	 * Gets the or create nbt.
	 *
	 * @param stack the stack
	 * @return the or create nbt
	 */
	private static NBTTagCompound getOrCreateNBT(final ItemStack stack)
	{
		if (stack.getTagCompound() == null)
		{
			final NBTTagCompound tag = new NBTTagCompound();

			if (stack.getItem() == ConfigItems.itemCanisterSteam)
			{
				tag.setInteger("steam", (MAX_VISIBLE - stack.getItemDamage())
						* MAX_STEAM / MAX_VISIBLE);
			}
			else
			{
				tag.setInteger("gas", (MAX_VISIBLE - stack.getItemDamage())
						* MAX_GAS / MAX_VISIBLE);
			}

			stack.setTagCompound(new NBTTagCompound());
		}

		return stack.getTagCompound();
	}

	/*
	 * For steam canisters
	 */

	/**
	 * Gets the steam.
	 *
	 * @param stack the stack
	 * @return the steam
	 */
	public static int getSteam(final ItemStack stack)
	{
		final NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("steam");
	}

	/**
	 * Gets the empty space steam.
	 *
	 * @param stack the stack
	 * @return the empty space steam
	 */
	public static int getEmptySpaceSteam(final ItemStack stack)
	{
		final NBTTagCompound compound = getOrCreateNBT(stack);
		return MAX_STEAM - compound.getInteger("steam");
	}

	/**
	 * Adds the steam.
	 *
	 * @param stack the stack
	 * @param steam the steam
	 * @return the int
	 */
	public static int addSteam(final ItemStack stack, final int steam)
	{
		final int steamToAdd = Math.min(getEmptySpaceSteam(stack),
				Math.min(steam, MAX_STEAM_RATE));
		setSteam(stack, getSteam(stack) + steamToAdd);
		return steamToAdd;
	}

	/**
	 * Sets the steam.
	 *
	 * @param stack the stack
	 * @param steam the steam
	 */
	public static void setSteam(final ItemStack stack, final int steam)
	{
		final NBTTagCompound compound = getOrCreateNBT(stack);
		final int steamToAdd = Math.min(steam, MAX_STEAM);
		compound.setInteger("steam", steamToAdd);
		stack.setItemDamage(MAX_VISIBLE - steamToAdd * MAX_VISIBLE / MAX_STEAM);
	}

	/**
	 * Checks if is full steam.
	 *
	 * @param stack the stack
	 * @return true, if is full steam
	 */
	public static boolean isFullSteam(final ItemStack stack)
	{
		final NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("steam") >= MAX_STEAM;
	}

	/**
	 * Checks if is empty steam.
	 *
	 * @param stack the stack
	 * @return true, if is empty steam
	 */
	public static boolean isEmptySteam(final ItemStack stack)
	{
		final NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("steam") <= 0;
	}

	/*
	 * For gas canisters
	 */

	/**
	 * Gets the gas.
	 *
	 * @param stack the stack
	 * @return the gas
	 */
	public static int getGas(final ItemStack stack)
	{
		final NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("gas");
	}

	/**
	 * Gets the empty space gas.
	 *
	 * @param stack the stack
	 * @return the empty space gas
	 */
	public static int getEmptySpaceGas(final ItemStack stack)
	{
		final NBTTagCompound compound = getOrCreateNBT(stack);
		return MAX_STEAM - compound.getInteger("gas");
	}

	/**
	 * Adds the gas.
	 *
	 * @param stack the stack
	 * @param gas the gas
	 * @return the int
	 */
	public static int addGas(final ItemStack stack, final int gas)
	{
		final int gasToAdd = Math.min(getEmptySpaceGas(stack),
				Math.min(gas, MAX_GAS_RATE));
		setGas(stack, getGas(stack) + gasToAdd);
		return gasToAdd;
	}

	/**
	 * Sets the gas.
	 *
	 * @param stack the stack
	 * @param gas the gas
	 */
	public static void setGas(final ItemStack stack, final int gas)
	{
		final NBTTagCompound compound = getOrCreateNBT(stack);
		final int gasToAdd = Math.min(gas, MAX_STEAM);
		compound.setInteger("gas", gasToAdd);
		stack.setItemDamage(MAX_VISIBLE - gasToAdd * MAX_VISIBLE / MAX_GAS);
	}

	/**
	 * Checks if is full gas.
	 *
	 * @param stack the stack
	 * @return true, if is full gas
	 */
	public static boolean isFullGas(final ItemStack stack)
	{
		final NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("gas") >= MAX_GAS;
	}

	/**
	 * Checks if is empty gas.
	 *
	 * @param stack the stack
	 * @return true, if is empty gas
	 */
	public static boolean isEmptyGas(final ItemStack stack)
	{
		final NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("gas") <= 0;
	}
}
