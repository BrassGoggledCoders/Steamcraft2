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
 * File created @ [Mar 12, 2014, 5:27:16 PM]
 */
package steamcraft.api;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.EnumHelper;
import steamcraft.common.lib.network.LoggerSteamcraft;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class SteamcraftApi
{
	// name, int harvest level, int max uses, float efficiency, float damage, int enchantability
	public static EnumToolMaterial toolMatObsidian = EnumHelper.addToolMaterial("T_OBSIDIAN", 4, -1, 3.0F, 6.5F, 7);
	public static EnumToolMaterial toolMatEtherium = EnumHelper.addToolMaterial("T_ETHERIUM", 4, 2345, 10.5F, 9.5F, 14);
	public static EnumToolMaterial toolMatSteam = EnumHelper.addToolMaterial("T_STEAM", 2, 321, 12.0F, 4.0F, 0);
	
	public static EnumToolMaterial drillMatWood = EnumHelper.addToolMaterial("D_WOOD", 0, 89, 4.0F, 0.0F, 0);
	public static EnumToolMaterial drillMatStone = EnumHelper.addToolMaterial("D_STONE", 0, 197, 8.0F, 0.0F, 0);
	public static EnumToolMaterial drillMatIron = EnumHelper.addToolMaterial("D_IRON", 0, 375, 12.0F, 0.0F, 0);
	public static EnumToolMaterial drillMatEmerald = EnumHelper.addToolMaterial("D_EMERALD", 0, 2342, 16.0F, 0.0F, 0);
	public static EnumToolMaterial drillMatGold = EnumHelper.addToolMaterial("D_GOLD", 0, 48, 24.0F, 0.0F, 0);
	public static EnumToolMaterial drillMatSteam = EnumHelper.addToolMaterial("D_STEAM", 0, 482, 24.0F, 0.0F, 0);
	public static EnumToolMaterial drillMatEtherium = EnumHelper.addToolMaterial("D_ETHERIUM", 0, 3518, 34.0F, 0.0F, 0);
	public static EnumToolMaterial drillMatObsidian = EnumHelper.addToolMaterial("D_OBSIDIAN", 0, -1, 6.0F, 0.0F, 0);

	// name, int durability, int[] reduction amounts, int enchantability
	// damage reduction (each 1 point is a half a shield on gui) of the piece index passed (0 = helmet, 1 = plate, 2 = legs and 3 = boots)
	public static EnumArmorMaterial armorMatObsidian = EnumHelper.addArmorMaterial("A_OBSIDIAN", -1, new int [] {1, 4, 3, 1}, 7);
	public static EnumArmorMaterial armorMatEtherium = EnumHelper.addArmorMaterial("A_ETHERIUM", 40, new int [] {4, 9, 8, 4}, 14);
	public static EnumArmorMaterial armorMatSteam = EnumHelper.addArmorMaterial("A_STEAM", 12, new int [] {1, 2, 2, 1}, 9);
	
	//enchants
	//any special crafting recipes
	//steam pipe stuff
	//conveyor belt stuff

	public static ItemStack getSC2Item(Item item, int metadata)
	{
		ItemStack is = null;

		try {
			String itemClass = "steamcraft.common.config.ConfigItems";
			Object obj = Class.forName(itemClass).equals(item);
			
			if (obj instanceof Item)
				is = new ItemStack((Item)obj, 1, metadata);
			else if (obj instanceof ItemStack)
				is = (ItemStack)obj;
		} catch (Exception e) {
			LoggerSteamcraft.log(Level.SEVERE, "[SC2] Could not find item: " + item.getUnlocalizedName());
		}

		return is;
	}

	public static ItemStack getSC2Block(Block block, int metadata)
	{
		ItemStack is = null;

		try {
			String blockClass = "steamcraft.common.config.ConfigBlocks";
			Object obj = Class.forName(blockClass).equals(block);
			
			if (obj instanceof Block)
				is = new ItemStack((Block)obj, 1, metadata);
			else if (obj instanceof ItemStack)
				is = (ItemStack)obj;
		} catch (Exception e) {
			LoggerSteamcraft.log(Level.SEVERE, "[SC2] Could not find block: " + block.getUnlocalizedName());
		}

		return is;
	}
}
