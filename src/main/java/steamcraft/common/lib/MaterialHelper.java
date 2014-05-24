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
 * File created @ [Apr 23, 2014, 10:24:48 PM]
 */
package steamcraft.common.lib;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class MaterialHelper.
 *
 * @author Decebaldecebal
 */
public class MaterialHelper
{
	// Tool Materials
	/** The obsidian tool. */
	public static ToolMaterial OBSIDIAN_TOOL;
	
	/** The etherium tool. */
	public static ToolMaterial ETHERIUM_TOOL;
	
	/** The steam tool. */
	public static ToolMaterial STEAM_TOOL;

	/** The d wood. */
	public static ToolMaterial D_WOOD = EnumHelper.addToolMaterial("D_WOOD", 0,
			89, 4.0F, 0.0F, 0);
	
	/** The d stone. */
	public static ToolMaterial D_STONE = EnumHelper.addToolMaterial("D_STONE",
			0, 197, 8.0F, 0.0F, 0);
	
	/** The d iron. */
	public static ToolMaterial D_IRON = EnumHelper.addToolMaterial("D_IRON", 0,
			375, 12.0F, 0.0F, 0);
	
	/** The d emerald. */
	public static ToolMaterial D_EMERALD = EnumHelper.addToolMaterial(
			"D_EMERALD", 0, 2342, 16.0F, 0.0F, 0);
	
	/** The d gold. */
	public static ToolMaterial D_GOLD = EnumHelper.addToolMaterial("D_GOLD", 0,
			48, 24.0F, 0.0F, 0);
	
	/** The d steam. */
	public static ToolMaterial D_STEAM = EnumHelper.addToolMaterial("D_STEAM",
			0, 482, 24.0F, 0.0F, 0);
	
	/** The d etherium. */
	public static ToolMaterial D_ETHERIUM = EnumHelper.addToolMaterial(
			"D_ETHERIUM", 0, 3518, 34.0F, 0.0F, 0);
	
	/** The d obsidian. */
	public static ToolMaterial D_OBSIDIAN = EnumHelper.addToolMaterial(
			"D_OBSIDIAN", 0, -1, 6.0F, 0.0F, 0);

	// Armor Materials
	/** The obsidian armor. */
	public static ArmorMaterial OBSIDIAN_ARMOR;
	
	/** The etherium armor. */
	public static ArmorMaterial ETHERIUM_ARMOR;
	
	/** The steam armor. */
	public static ArmorMaterial STEAM_ARMOR;

	/**
	 * Initialize materials.
	 */
	public static void initializeMaterials()
	{
		initToolMaterials();
		initArmorMaterials();
	}

	/**
	 * Inits the tool materials.
	 */
	private static void initToolMaterials()
	{
		// name, int harvest level, int max uses, float efficiency, float
		// damage, int enchantability
		// Tools
		OBSIDIAN_TOOL = EnumHelper.addToolMaterial("OBSIDIAN_TOOL", 4, -1,
				3.0F, 6.5F, 7);
		ETHERIUM_TOOL = EnumHelper.addToolMaterial("ETHERIUM_TOOL", 4, 2345,
				10.5F, 9.5F, 14);
		STEAM_TOOL = EnumHelper.addToolMaterial("STEAM_TOOL", 2, 321, 12.0F,
				4.0F, 0);

		// DRILLS!
		D_WOOD = EnumHelper.addToolMaterial("D_WOOD", 0, 89, 4.0F, 0.0F, 0);
		D_STONE = EnumHelper.addToolMaterial("D_STONE", 0, 197, 8.0F, 0.0F, 0);
		D_IRON = EnumHelper.addToolMaterial("D_IRON", 0, 375, 12.0F, 0.0F, 0);
		D_EMERALD = EnumHelper.addToolMaterial("D_EMERALD", 0, 2342, 16.0F,
				0.0F, 0);
		D_GOLD = EnumHelper.addToolMaterial("D_GOLD", 0, 48, 24.0F, 0.0F, 0);
		D_STEAM = EnumHelper.addToolMaterial("D_STEAM", 0, 482, 24.0F, 0.0F, 0);
		D_ETHERIUM = EnumHelper.addToolMaterial("D_ETHERIUM", 0, 3518, 34.0F,
				0.0F, 0);
		D_OBSIDIAN = EnumHelper.addToolMaterial("D_OBSIDIAN", 0, -1, 6.0F,
				0.0F, 0);
	}

	/**
	 * Inits the armor materials.
	 */
	private static void initArmorMaterials()
	{
		// name, int durability, int[] reduction amounts, int enchantability
		OBSIDIAN_ARMOR = EnumHelper.addArmorMaterial("OBSIDIAN_ARMOR", -1,
				new int[] { 1, 4, 3, 1 }, 7);
		ETHERIUM_ARMOR = EnumHelper.addArmorMaterial("ETHERIUM_ARMOR", 40,
				new int[] { 4, 9, 8, 4 }, 14);
		STEAM_ARMOR = EnumHelper.addArmorMaterial("STEAM_ARMOR", 12, new int[] {
				1, 2, 2, 1 }, 9);

		// Damage reduction (each 1 point is a half a shield on gui) of the
		// piece index passed (0 = helmet, 1 = plate, 2 = legs and 3 = boots)
	}
}
