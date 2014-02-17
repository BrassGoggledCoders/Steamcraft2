package common.steamcraft.mod.common.item;

import common.steamcraft.mod.common.lib.ItemIDs;
import common.steamcraft.mod.common.lib.MaterialMod;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class ModTools {
	// Drills
	public static Item drillCore;
	public static Item drillBase;
	public static Item drillWood;
	public static Item drillStone;
	public static Item drillIron;
	public static Item drillDiamond;
	public static Item drillGold;
	public static Item drillSteam;
	public static Item drillEtherium;
	public static Item drillObsidian;
	
	// Steam
	public static Item pickaxeSteam;
	public static Item swordSteam;
	public static Item shovelSteam;
	public static Item axeSteam;
	public static Item hoeSteam;
	
	
	// Etherium
	public static Item pickaxeEtherium;
	public static Item swordEtherium;
	public static Item shovelEtherium;
	public static Item axeEtherium;
	public static Item hoeEtherium;
	
	// Obsidian
	public static Item pickaxeObsidian;
	public static Item swordObsidian;
	public static Item shovelObsidian;
	public static Item axeObsidian;
	public static Item hoeObsidian;
	
	// Other
	public static Item chisel;
	public static Item spanner;

	public static void initTools() { 
		drillCore = new ItemCoreDrill(ItemIDs.coreDrillID).setUnlocalizedName("coredrill");
		drillBase = new ItemMod(ItemIDs.drillBaseID).setUnlocalizedName("coredrillbase");
		// int id, double maxEnergy, float voltage, int toolTier
		// TODO: FIX THESE GOD DAMN NUMBERS!!!
		drillWood = new ItemElectricDrill(ItemIDs.drillWoodID, 3000, 120, 0).setUnlocalizedName("wooddrill");
		drillStone = new ItemElectricDrill(ItemIDs.drillStoneID, 5000, 120, 1).setUnlocalizedName("stonedrill");
		drillIron = new ItemElectricDrill(ItemIDs.drillIronID, 8000, 120, 2).setUnlocalizedName("irondrill");
		drillDiamond = new ItemElectricDrill(ItemIDs.drillDiamondID, 12000, 120, 3).setUnlocalizedName("diamonddrill");
		drillGold = new ItemElectricDrill(ItemIDs.drillGoldID, 8000, 120, 3).setUnlocalizedName("golddrill");
		drillSteam = new ItemSteamDrill(ItemIDs.drillSteamID).setUnlocalizedName("steamdrill");
		drillEtherium = new ItemElectricDrill(ItemIDs.drillEtheriumID, 14000, 120, 4).setUnlocalizedName("etheriumdrill");
		drillObsidian = new ItemElectricDrill(ItemIDs.drillObsidianID, 10000, 120, 2).setUnlocalizedName("obsidiandrill");
		
		pickaxeSteam = new ItemModPickaxe(ItemIDs.pickaxeSteamID, MaterialMod.STEAM_TOOL).setUnlocalizedName("steampick");
		swordSteam = new ItemModSword(ItemIDs.swordSteamID, MaterialMod.STEAM_TOOL).setUnlocalizedName("steamsword");
		shovelSteam = new ItemModSpade(ItemIDs.shovelSteamID, MaterialMod.STEAM_TOOL).setUnlocalizedName("steamspade");
		axeSteam = new ItemAxeMod(ItemIDs.axeSteamID, MaterialMod.STEAM_TOOL).setUnlocalizedName("steamaxe");
		hoeSteam = new ItemHoeMod(ItemIDs.hoeSteamID, MaterialMod.STEAM_TOOL).setUnlocalizedName("steamhoe");
		
		pickaxeEtherium = new ItemModPickaxe(ItemIDs.pickaxeEtheriumID, MaterialMod.ETHERIUM_TOOL).setUnlocalizedName("etheriumpick");
		swordEtherium = new ItemModSword(ItemIDs.swordEtheriumID, MaterialMod.ETHERIUM_TOOL).setUnlocalizedName("etheriumsword");
		shovelEtherium = new ItemModSpade(ItemIDs.shovelEtheriumID, MaterialMod.ETHERIUM_TOOL).setUnlocalizedName("etheriumspade");
		axeEtherium = new ItemAxeMod(ItemIDs.axeEtheriumID, MaterialMod.ETHERIUM_TOOL).setUnlocalizedName("etheriumaxe");
		hoeEtherium = new ItemHoeMod(ItemIDs.hoeEtheriumID, MaterialMod.ETHERIUM_TOOL).setUnlocalizedName("etheriumhoe");
		
		pickaxeObsidian = new ItemModPickaxe(ItemIDs.pickaxeObsidianID, MaterialMod.OBSIDIAN_TOOL).setUnlocalizedName("obsidianpick");
		swordObsidian = new ItemModSword(ItemIDs.swordObsidianID, MaterialMod.OBSIDIAN_TOOL).setUnlocalizedName("obsidiansword");
		shovelObsidian = new ItemModSpade(ItemIDs.shovelObsidianID, MaterialMod.OBSIDIAN_TOOL).setUnlocalizedName("obsidianspade");
		axeObsidian = new ItemAxeMod(ItemIDs.axeObsidianID, MaterialMod.OBSIDIAN_TOOL).setUnlocalizedName("obsidianaxe");
		hoeObsidian = new ItemHoeMod(ItemIDs.hoeObsidianID, MaterialMod.OBSIDIAN_TOOL).setUnlocalizedName("obsidianhoe");
		
		chisel = new ItemChisel(ItemIDs.chiselID, 64).setUnlocalizedName("chisel"); // TODO: Change from crafting to right-click
		spanner = new ItemChisel(ItemIDs.spannerID, 3).setUnlocalizedName("spanner");	
		
		initToolNames();
	}

	public static void initToolNames() {
		LanguageRegistry.addName(drillCore, "Drill Core");
		LanguageRegistry.addName(drillBase, "Drill Base");
		LanguageRegistry.addName(drillWood, "Wooden Drill");
		LanguageRegistry.addName(drillStone, "Stone Drill");
		LanguageRegistry.addName(drillIron, "Iron Drill");
		LanguageRegistry.addName(drillDiamond, "Diamond Drill");
		LanguageRegistry.addName(drillGold, "Golden Drill");
		LanguageRegistry.addName(drillSteam, "Steam-Powered Drill");
		LanguageRegistry.addName(drillEtherium, "Etherium Drill");
		LanguageRegistry.addName(drillObsidian, "Obsidian Drill");
		
		LanguageRegistry.addName(pickaxeSteam, "Steam-Powered Pickaxe");
		LanguageRegistry.addName(swordSteam, "Stean-Powered Sword");
		LanguageRegistry.addName(axeSteam, "Steam-Powered Axe");
		LanguageRegistry.addName(hoeSteam, "Steam-Powered Hoe");
		
		LanguageRegistry.addName(pickaxeEtherium, "Etherium Pickaxe");
		LanguageRegistry.addName(swordEtherium, "Etherium Sword");
		LanguageRegistry.addName(axeEtherium, "Etherium Axe");
		LanguageRegistry.addName(hoeEtherium, "Etherium Hoe");
		
		LanguageRegistry.addName(pickaxeObsidian, "Obsidian Pickaxe");
		LanguageRegistry.addName(swordObsidian, "Obsidian Sword");
		LanguageRegistry.addName(axeObsidian, "Obsidian Axe");
		LanguageRegistry.addName(hoeObsidian, "Obsidian Hoe");
		
		LanguageRegistry.addName(chisel, "Chisel");
		LanguageRegistry.addName(spanner, "Spanner");
	}
}