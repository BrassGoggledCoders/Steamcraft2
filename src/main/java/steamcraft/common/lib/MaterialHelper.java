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

import net.minecraft.block.material.Material;
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

	// Single Tool Helper Materials
	public static ToolMaterial HTOOL_ELEC;

	// Armor Materials
	public static ArmorMaterial ARMOR_OBSIDIAN;
	public static ArmorMaterial ARMOR_ETHERIUM;
	public static ArmorMaterial ARMOR_WHALEBONE;
	public static ArmorMaterial ARMOR_STEAM;
	
	//Block Materials
	private static Material[] pickaxeMaterials = {Material.rock, Material.iron, Material.anvil, Material.circuits,
		Material.glass, Material.ice, Material.piston};
	private static Material[] axeMaterials = {Material.wood, Material.leaves, Material.plants, Material.vine, Material.circuits,
		Material.cactus, Material.gourd};
	private static Material[] shovelMaterials = {Material.grass, Material.ground, Material.clay, Material.sand, Material.snow};
	private static Material[] swordMaterials = {Material.web};

	public static void initializeMaterials()
	{
		initToolMaterials();
		initArmorMaterials();
	}

	private static void initToolMaterials()
	{
		// Tools
		TOOL_OBSIDIAN = EnumHelper.addToolMaterial("TOOL_OBSIDIAN", 3, -1, 3.0F, 6F, 7);
		TOOL_ETHERIUM = EnumHelper.addToolMaterial("TOOL_ETHERIUM", 3, 2345, 10.5F, 7F, 14);
		TOOL_STEAM = EnumHelper.addToolMaterial("TOOL_STEAM", 2, 1561, 12.0F, 3.0F, 0);

		HTOOL_ELEC = EnumHelper.addToolMaterial("HTOOL_ELEC", 0, 375, 0.0F, 7.0F, 0);

		// Drills
		DRILL_WOOD = EnumHelper.addToolMaterial("DRILL_WOOD", 0, 89, 4.0F, 0.0F, 0);
		DRILL_STONE = EnumHelper.addToolMaterial("DRILL_STONE", 1, 197, 8.0F, 0.0F, 0);
		DRILL_IRON = EnumHelper.addToolMaterial("DRILL_IRON", 2, 375, 12.0F, 0.0F, 0);
		DRILL_DIAMOND = EnumHelper.addToolMaterial("DRILL_DIAMOND", 3, 2342, 16.0F, 0.0F, 0);
		DRILL_GOLD = EnumHelper.addToolMaterial("DRILL_GOLD", 1, 48, 18.0F, 0.0F, 0);
		DRILL_STEAM = EnumHelper.addToolMaterial("DRILL_STEAM", 2, 482, 12.0F, 0.0F, 0);
		DRILL_ETHERIUM = EnumHelper.addToolMaterial("DRILL_ETHERIUM", 3, 3518, 32.0F, 0.0F, 0);
		DRILL_OBSIDIAN = EnumHelper.addToolMaterial("DRILL_OBSIDIAN", 3, -1, 8.0F, 0.0F, 0);
	}

	private static void initArmorMaterials()
	{
		ARMOR_OBSIDIAN = EnumHelper.addArmorMaterial("ARMOR_OBSIDIAN", -1, new int[] { 5, 8, 8, 5 }, 5);
		ARMOR_ETHERIUM = EnumHelper.addArmorMaterial("ARMOR_ETHERIUM", 40, new int[] { 4, 8, 7, 3 }, 18);
		ARMOR_WHALEBONE = EnumHelper.addArmorMaterial("ARMOR_WHALEBONE", 10, new int[] { 2, 6, 5, 2 }, 30);
		ARMOR_STEAM = EnumHelper.addArmorMaterial("ARMOR_STEAM", -1, new int[] { 1, 1, 1, 1 }, 0);
	}
	
	public static Material[] getMaterialForTool(String tool)
	{
		if (tool == "pickaxe")
			return pickaxeMaterials;
		if (tool == "axe")
			return axeMaterials;
		if (tool == "shovel")
			return shovelMaterials;
		if (tool == "sword")
			return swordMaterials;
		return null;
	}
}
