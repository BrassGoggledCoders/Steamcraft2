
package steamcraft.common.init;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;

import net.minecraftforge.common.util.EnumHelper;

/**
 * @author Decebaldecebal
 *
 */
public class InitMaterials
{
	// Tool Materials
	public static ToolMaterial TOOL_STEAM;

	// Drill Materials
	public static ToolMaterial DRILL_WOOD;
	public static ToolMaterial DRILL_STONE;
	public static ToolMaterial DRILL_IRON;
	public static ToolMaterial DRILL_DIAMOND;
	public static ToolMaterial DRILL_GOLD;
	public static ToolMaterial DRILL_STEAM;

	// Single Tool Helper Materials
	public static ToolMaterial HTOOL_ELEC;

	// Armor Materials
	public static ArmorMaterial ARMOR_WHALEBONE;
	public static ArmorMaterial ARMOR_STEAM;

	public static void initializeMaterials()
	{
		initToolMaterials();
		initArmorMaterials();
	}

	private static void initToolMaterials()
	{
		TOOL_STEAM = EnumHelper.addToolMaterial("TOOL_STEAM", 2, 1561, 12.0F, 3.0F, 0);

		HTOOL_ELEC = EnumHelper.addToolMaterial("HTOOL_ELEC", 0, 375, 0.0F, 7.0F, 0);

		// Drills
		DRILL_WOOD = EnumHelper.addToolMaterial("DRILL_WOOD", 0, 89, 4.0F, 0.0F, 0);
		DRILL_STONE = EnumHelper.addToolMaterial("DRILL_STONE", 1, 197, 8.0F, 0.0F, 0);
		DRILL_IRON = EnumHelper.addToolMaterial("DRILL_IRON", 2, 375, 12.0F, 0.0F, 0);
		DRILL_DIAMOND = EnumHelper.addToolMaterial("DRILL_DIAMOND", 3, 2342, 16.0F, 0.0F, 0);
		DRILL_GOLD = EnumHelper.addToolMaterial("DRILL_GOLD", 1, 48, 18.0F, 0.0F, 0);
		DRILL_STEAM = EnumHelper.addToolMaterial("DRILL_STEAM", 2, 482, 12.0F, 0.0F, 0);
	}

	private static void initArmorMaterials()
	{
		ARMOR_WHALEBONE = EnumHelper.addArmorMaterial("ARMOR_WHALEBONE", 10, new int[] { 2, 6, 5, 2 }, 30);
		ARMOR_STEAM = EnumHelper.addArmorMaterial("ARMOR_STEAM", -1, new int[] { 1, 1, 1, 1 }, 0);
	}

}
