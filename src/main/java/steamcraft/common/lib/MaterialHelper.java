/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package steamcraft.common.lib;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;

import net.minecraftforge.common.util.EnumHelper;

/**
 * @author Decebaldecebal
 * 
 */
public class MaterialHelper
{
	// Tool Materials
	public static ToolMaterial TOOL_OBSIDIAN;
	public static ToolMaterial TOOL_ETHERIUM;
	public static ToolMaterial TOOL_STEAM;

	// Drill Materials
	public static ToolMaterial DRILL_WOOD;
	public static ToolMaterial DRILL_STONE;
	public static ToolMaterial DRILL_IRON;
	public static ToolMaterial DRILL_DIAMOND;
	public static ToolMaterial DRILL_GOLD;
	public static ToolMaterial DRILL_STEAM;
	public static ToolMaterial DRILL_ETHERIUM;
	public static ToolMaterial DRILL_OBSIDIAN;

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
		TOOL_OBSIDIAN = EnumHelper.addToolMaterial("TOOL_OBSIDIAN", 4, -1, 3.0F, 6F, 7);
		TOOL_ETHERIUM = EnumHelper.addToolMaterial("TOOL_ETHERIUM", 4, 2345, 10.5F, 7F, 14);
		TOOL_STEAM = EnumHelper.addToolMaterial("TOOL_STEAM", 2, 1561, 12.0F, 0.0F, 0);

		// Drills
		DRILL_WOOD = EnumHelper.addToolMaterial("DRILL_WOOD", 0, 89, 4.0F, 0.0F, 0);
		DRILL_STONE = EnumHelper.addToolMaterial("DRILL_STONE", 0, 197, 8.0F, 0.0F, 0);
		DRILL_IRON = EnumHelper.addToolMaterial("DRILL_IRON", 0, 375, 12.0F, 0.0F, 0);
		DRILL_DIAMOND = EnumHelper.addToolMaterial("DRILL_EMERALD", 0, 2342, 16.0F, 0.0F, 0);
		DRILL_GOLD = EnumHelper.addToolMaterial("DRILL_GOLD", 0, 48, 16.0F, 0.0F, 0);
		DRILL_STEAM = EnumHelper.addToolMaterial("DRILL_STEAM", 0, 482, 24.0F, 0.0F, 0);
		DRILL_ETHERIUM = EnumHelper.addToolMaterial("DRILL_ETHERIUM", 0, 3518, 34.0F, 0.0F, 0);
		DRILL_OBSIDIAN = EnumHelper.addToolMaterial("DRILL_OBSIDIAN", 0, -1, 6.0F, 0.0F, 0);
	}

	private static void initArmorMaterials()
	{
		ARMOR_OBSIDIAN = EnumHelper.addArmorMaterial("ARMOR_OBSIDIAN", -1, new int[] { 5, 8, 8, 5 }, 5);
		ARMOR_ETHERIUM = EnumHelper.addArmorMaterial("ARMOR_ETHERIUM", 40, new int[] { 4, 8, 7, 3 }, 18);
		ARMOR_STEAM = EnumHelper.addArmorMaterial("ARMOR_STEAM", -1, new int[] { 1, 1, 1, 1 }, 0);
	}
}
