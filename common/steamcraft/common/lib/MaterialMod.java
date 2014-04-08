package common.steamcraft.common.lib2;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraftforge.common.EnumHelper;

public class MaterialMod
{
	// Creates an instance of this class that is static
	public static final MaterialMod INSTANCE = new MaterialMod();

	// Tool Materials
	// name, int harvest level, int max uses, float efficiency, float damage, int enchantability
	public static EnumToolMaterial OBSIDIAN_TOOL = EnumHelper.addToolMaterial("OBSIDIAN_TOOL", 4, -1, 3.0F, 6.5F, 7);
	public static EnumToolMaterial ETHERIUM_TOOL = EnumHelper.addToolMaterial("ETHERIUM_TOOL", 4, 2345, 10.5F, 9.5F, 14);
	/*
	 * Nerfed the damage on steam tools
	 * Other tools damage should be nerfed as well in my opinion
	 * 
	 */
	public static EnumToolMaterial STEAM_TOOL = EnumHelper.addToolMaterial("STEAM_TOOL", 2, 321, 12.0F, 4.0F, 0);
	// DRILLS!
	public static EnumToolMaterial D_WOOD = EnumHelper.addToolMaterial("D_WOOD", 0, 89, 4.0F, 0.0F, 0);
	public static EnumToolMaterial D_STONE = EnumHelper.addToolMaterial("D_STONE", 0, 197, 8.0F, 0.0F, 0);
	public static EnumToolMaterial D_IRON = EnumHelper.addToolMaterial("D_IRON", 0, 375, 12.0F, 0.0F, 0);
	public static EnumToolMaterial D_EMERALD = EnumHelper.addToolMaterial("D_EMERALD", 0, 2342, 16.0F, 0.0F, 0);
	public static EnumToolMaterial D_GOLD = EnumHelper.addToolMaterial("D_GOLD", 0, 48, 24.0F, 0.0F, 0);
	public static EnumToolMaterial D_STEAM = EnumHelper.addToolMaterial("D_STEAM", 0, 482, 24.0F, 0.0F, 0);
	public static EnumToolMaterial D_ETHERIUM = EnumHelper.addToolMaterial("D_ETHERIUM", 0, 3518, 34.0F, 0.0F, 0);
	public static EnumToolMaterial D_OBSIDIAN = EnumHelper.addToolMaterial("D_OBSIDIAN", 0, -1, 6.0F, 0.0F, 0);

	// Armor Materials
	// name, int durability, int[] reduction amounts, int enchantability
	public static EnumArmorMaterial OBSIDIAN_ARMOR = EnumHelper.addArmorMaterial("OBSIDIAN_ARMOR", -1, new int [] {1, 4, 3, 1}, 7);
	public static EnumArmorMaterial ETHERIUM_ARMOR = EnumHelper.addArmorMaterial("ETHERIUM_ARMOR", 40, new int [] {4, 9, 8, 4}, 14);
	public static EnumArmorMaterial STEAM_ARMOR = EnumHelper.addArmorMaterial("STEAM_ARMOR", 12, new int [] {1, 2, 2, 1}, 9);
	// Damage reduction (each 1 point is a half a shield on gui) of the piece index passed (0 = helmet, 1 = plate, 2 = legs and 3 = boots)
}