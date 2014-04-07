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
	public static final int MAX_VISIBLE = 2500; //Visual represntation of gas/steam through item damage
	
	public static final int MAX_STEAM = 150000; //Isn't this an excesivly large number?
	public static final int MAX_STEAM_RATE = 20; //Maximum amount of steam that can be inserted into this canister per tick
	
	public static final int MAX_GAS = 150000; //Isn't this an excesivly large number?
	public static final int MAX_GAS_RATE = 20; //Maximum amount of gas that can be inserted into this canister per tick

	public ItemCanister(int id, String gas) 
	{
		super(id);
		this.maxStackSize = 1;
		this.setNoRepair();
		this.gas = gas;
		
		this.setMaxDamage(MAX_VISIBLE);
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int itemID, CreativeTabs tab, List list) 
	{	
		list.add(getChargedItem());
		list.add(new ItemStack(itemID, 1, this.getMaxDamage()));
	}

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) 
	{
		if(itemStack.getItem() == ModItems.canisterSteam)
			list.add(String.format("%d/%d", new Object[] {getSteam(itemStack), MAX_STEAM}));
		else
			list.add(String.format("%d/%d", new Object[] {getGas(itemStack), MAX_GAS}));
	}
	
	private ItemStack getChargedItem()
	{
		ItemStack charged = new ItemStack(this, 1, 0);
		NBTTagCompound tag = new NBTTagCompound();
		
		if(charged.getItem() == ModItems.canisterSteam)
			tag.setInteger("steam", MAX_STEAM);
		else
			tag.setInteger("gas", MAX_GAS);
		
		charged.setTagCompound(tag);
		
		return charged.copy();
	}
	
	private static NBTTagCompound getOrCreateNBT(ItemStack stack) 
	{
		if (stack.getTagCompound() == null)
		{
			NBTTagCompound tag = new NBTTagCompound();
			
			if(stack.getItem() == ModItems.canisterSteam)
				tag.setInteger("steam", (MAX_VISIBLE - stack.getItemDamage()) * MAX_STEAM / MAX_VISIBLE);
			else
				tag.setInteger("gas", (MAX_VISIBLE - stack.getItemDamage()) * MAX_GAS / MAX_VISIBLE);
			
			stack.setTagCompound(new NBTTagCompound());
		}

		return stack.getTagCompound();
	}
	
	/*
	 * For steam canisters 
	 */
	
	public static int getSteam(ItemStack stack) 
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("steam");
	}

	public static int getEmptySpaceSteam(ItemStack stack)
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		return MAX_STEAM - compound.getInteger("steam");
	}
	
	public static int addSteam(ItemStack stack, int steam)
	{
		int steamToAdd = Math.min(getEmptySpaceSteam(stack), Math.min(steam, MAX_STEAM_RATE));
		setSteam(stack, getSteam(stack)+steamToAdd);
		return steamToAdd;
	}
	
	public static void setSteam(ItemStack stack, int steam)
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		int steamToAdd = Math.min(steam, MAX_STEAM);
		compound.setInteger("steam", steamToAdd);
		stack.setItemDamage(MAX_VISIBLE - steamToAdd * MAX_VISIBLE / MAX_STEAM);
	}

	public static boolean isFullSteam(ItemStack stack) 
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("steam") >= MAX_STEAM;
	}

	public static boolean isEmptySteam(ItemStack stack) {
		NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("steam") <= 0;
	}

	/*
	 * For gas canisters 
	 */
	
	public static int getGas(ItemStack stack) 
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("gas");
	}

	public static int getEmptySpaceGas(ItemStack stack)
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		return MAX_STEAM - compound.getInteger("gas");
	}
	
	public static int addGas(ItemStack stack, int gas)
	{
		int gasToAdd = Math.min(getEmptySpaceGas(stack), Math.min(gas, MAX_GAS_RATE));
		setGas(stack, getGas(stack)+gasToAdd);
		return gasToAdd;
	}
	
	public static void setGas(ItemStack stack, int gas)
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		int gasToAdd = Math.min(gas, MAX_STEAM);
		compound.setInteger("gas", gasToAdd);
		stack.setItemDamage(MAX_VISIBLE - gasToAdd * MAX_VISIBLE / MAX_GAS);
	}

	public static boolean isFullGas(ItemStack stack) 
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("gas") >= MAX_GAS;
	}

	public static boolean isEmptyGas(ItemStack stack) 
	{
		NBTTagCompound compound = getOrCreateNBT(stack);
		return compound.getInteger("gas") <= 0;
	}
}
