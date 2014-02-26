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
 * File created @ [Jan 30, 2014, 5:44:59 PM]
 */
package common.steamcraft.common.core.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Vazkii from Thaumic Tinkerer
 * (https://github.com/ThaumicTinkerer/ItemNBTHelper)
 *
 */
public class ItemNBTHelper {
	/** Checks if an ItemStack has a Tag Compound. */
	public static boolean detectNBT(ItemStack stack) {
		return stack.hasTagCompound();
	}

	/** Tries to initialize an NBT Tag Compound in an ItemStack; this will not do anything if the stack already has a tag compound. */
	public static void initNBT(ItemStack stack) 
	{
		if (!detectNBT(stack))
			injectNBT(stack, new NBTTagCompound());
	}

	/** Injects an NBT Tag Compound into an ItemStack; no checks are made previously. */
	public static void injectNBT(ItemStack stack, NBTTagCompound nbt) {
		stack.setTagCompound(nbt);
	}

	/** Gets the NBTTagCompound in an ItemStack. Tries to initiate it previously in case there isn't one present. */
	public static NBTTagCompound getNBT(ItemStack stack) {
		initNBT(stack);
		return stack.getTagCompound();
	}

	// =================== SETTER METHODS =================== //

	public static void setBoolean(ItemStack stack, String tag, boolean b) {
		getNBT(stack).setBoolean(tag, b);
	}

	public static void setByte(ItemStack stack, String tag, byte b) {
		getNBT(stack).setByte(tag, b);
	}

	public static void setShort(ItemStack stack, String tag, short s) {
		getNBT(stack).setShort(tag, s);
	}

	public static void setInt(ItemStack stack, String tag, int i) {
		getNBT(stack).setInteger(tag, i);
	}

	public static void setLong(ItemStack stack, String tag, long l) {
		getNBT(stack).setLong(tag, l);
	}

	public static void setFloat(ItemStack stack, String tag, float f) {
		getNBT(stack).setFloat(tag, f);
	}

	public static void setDouble(ItemStack stack, String tag, double d) {
		getNBT(stack).setDouble(tag, d);
	}

	public static void setCompound(ItemStack stack, String tag, NBTTagCompound nbt) {
		getNBT(stack).setCompoundTag(tag, nbt);
	}

	public static void setCompound(ItemStack stack, NBTTagCompound nbt) {
		setCompound(stack, nbt.getName(), nbt);
	}

	public static void setString(ItemStack stack, String tag, String s) {
		getNBT(stack).setString(tag, s);
	}

	// =================== GETTER METHODS =================== //

	public static boolean verifyExistance(ItemStack stack, String tag) {
		return getNBT(stack).hasKey(tag);
	}

	public static boolean getBoolean(ItemStack stack, String tag, boolean defaultExpected) {
		return verifyExistance(stack, tag) ? getNBT(stack).getBoolean(tag) : defaultExpected;
	}

	public static byte getByte(ItemStack stack, String tag, byte defaultExpected) {
		return verifyExistance(stack, tag) ? getNBT(stack).getByte(tag) : defaultExpected;
	}

	public static short getShort(ItemStack stack, String tag, short defaultExpected) {
		return verifyExistance(stack, tag) ? getNBT(stack).getShort(tag) : defaultExpected;
	}

	public static int getInt(ItemStack stack, String tag, int defaultExpected) {
		return verifyExistance(stack, tag) ? getNBT(stack).getInteger(tag) : defaultExpected;
	}

	public static long getLong(ItemStack stack, String tag, long defaultExpected) {
		return verifyExistance(stack, tag) ? getNBT(stack).getLong(tag) : defaultExpected;
	}

	public static float getFloat(ItemStack stack, String tag, float defaultExpected) {
		return verifyExistance(stack, tag) ? getNBT(stack).getFloat(tag) : defaultExpected;
	}

	public static double getDouble(ItemStack stack, String tag, double defaultExpected) {
		return verifyExistance(stack, tag) ? getNBT(stack).getDouble(tag) : defaultExpected;
	}

	/** If nullifyOnFail is true it'll return null if it doesn't find any compounds, otherwise it'll return a new one. */
	public static NBTTagCompound getCompound(ItemStack stack, String tag, boolean nullifyOnFail) {
		return verifyExistance(stack, tag) ? getNBT(stack).getCompoundTag(tag) : nullifyOnFail ? null : new NBTTagCompound();
	}

	public static String getString(ItemStack stack, String tag, String defaultExpected) {
		return verifyExistance(stack, tag) ? getNBT(stack).getString(tag) : defaultExpected;
	}
}
