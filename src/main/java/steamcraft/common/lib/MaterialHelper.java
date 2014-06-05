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

/**
 * 
 * @author Decebaldecebal
 */
public class MaterialHelper
{
	// Tool Materials
	public static ToolMaterial TOOL_OBSIDIAN;
	public static ToolMaterial TOOL_ETHERIUM;
	public static ToolMaterial TOOL_STEAM;

	// Drill Materials
	public static ToolMaterial DRILL_WOOD = EnumHelper.addToolMaterial("D_WOOD", 0, 89, 4.0F, 0.0F, 0);
	public static ToolMaterial DRILL_STONE = EnumHelper.addToolMaterial("D_STONE", 0, 197, 8.0F, 0.0F, 0);
	public static ToolMaterial DRILL_IRON = EnumHelper.addToolMaterial("D_IRON", 0, 375, 12.0F, 0.0F, 0);
	public static ToolMaterial DRILL_EMERALD = EnumHelper.addToolMaterial("D_EMERALD", 0, 2342, 16.0F, 0.0F, 0);
	public static ToolMaterial DRILL_GOLD = EnumHelper.addToolMaterial("D_GOLD", 0, 48, 24.0F, 0.0F, 0);
	public static ToolMaterial DRILL_STEAM = EnumHelper.addToolMaterial("D_STEAM", 0, 482, 24.0F, 0.0F, 0);
	public static ToolMaterial DRILL_ETHERIUM = EnumHelper.addToolMaterial("D_ETHERIUM", 0, 3518, 34.0F, 0.0F, 0);
	public static ToolMaterial DRILL_OBSIDIAN = EnumHelper.addToolMaterial("D_OBSIDIAN", 0, -1, 6.0F, 0.0F, 0);

	// Armor Materials
	public static ArmorMaterial ARMOR_OBSIDIAN;
	public static ArmorMaterial ARMOR_ETHERIUM;
	public static ArmorMaterial ARMOR_STEAM;

	public static void initializeMaterials()
	{
		initToolMaterials();
		initArmorMaterials();
	}

	private static void initToolMaterials()
	{
		// Tools
		
		// name, int harvest level, int max uses, float efficiency, float, damage, int enchantability
		TOOL_OBSIDIAN = EnumHelper.addToolMaterial("TOOL_OBSIDIAN", 4, -1, 3.0F, 6.5F, 7);
		TOOL_ETHERIUM = EnumHelper.addToolMaterial("TOOL_ETHERIUM", 4, 2345, 10.5F, 9.5F, 14);
		TOOL_STEAM = EnumHelper.addToolMaterial("TOOL_STEAM", 2, 321, 12.0F, 4.0F, 0);

		// DRILLS!
		DRILL_WOOD = EnumHelper.addToolMaterial("DRILL_WOOD", 0, 89, 4.0F, 0.0F, 0);
		DRILL_STONE = EnumHelper.addToolMaterial("DRILL_STONE", 0, 197, 8.0F, 0.0F, 0);
		DRILL_IRON = EnumHelper.addToolMaterial("DRILL_IRON", 0, 375, 12.0F, 0.0F, 0);
		DRILL_EMERALD = EnumHelper.addToolMaterial("DRILL_EMERALD", 0, 2342, 16.0F, 0.0F, 0);
		DRILL_GOLD = EnumHelper.addToolMaterial("DRILL_GOLD", 0, 48, 24.0F, 0.0F, 0);
		DRILL_STEAM = EnumHelper.addToolMaterial("DRILL_STEAM", 0, 482, 24.0F, 0.0F, 0);
		DRILL_ETHERIUM = EnumHelper.addToolMaterial("DRILL_ETHERIUM", 0, 3518, 34.0F, 0.0F, 0);
		DRILL_OBSIDIAN = EnumHelper.addToolMaterial("DRILL_OBSIDIAN", 0, -1, 6.0F, 0.0F, 0);
	}

	private static void initArmorMaterials()
	{
		// name, int durability, int[] reduction amounts, int enchantability
		ARMOR_OBSIDIAN = EnumHelper.addArmorMaterial("ARMOR_OBSIDIAN", -1, new int[] { 1, 4, 3, 1 }, 7);
		ARMOR_ETHERIUM = EnumHelper.addArmorMaterial("ARMOR_ETHERIUM", 40, new int[] { 4, 9, 8, 4 }, 14);
		ARMOR_STEAM = EnumHelper.addArmorMaterial("ARMOR_STEAM", 12, new int[] { 1, 2, 2, 1 }, 9);

		// Damage reduction (each 1 point is a half a shield on gui) of the
		// piece index passed (0 = helmet, 1 = plate, 2 = legs and 3 = boots)
	}
}
