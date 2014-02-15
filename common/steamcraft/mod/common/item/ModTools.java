package common.steamcraft.mod.common.item;

import common.steamcraft.mod.common.lib.SC2_ItemIDs;
import common.steamcraft.mod.common.lib.SC2_Material;

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
		drillCore = new ItemCoreDrill(SC2_ItemIDs.coreDrillID).setUnlocalizedName("coredrill");
		drillBase = new ItemSC2(SC2_ItemIDs.drillBaseID).setUnlocalizedName("coredrillbase");
		// int id, double maxEnergy, float voltage, int toolTier
		// TODO: FIX THESE GOD DAMN NUMBERS!!!
		drillWood = new ItemElectricDrill(SC2_ItemIDs.drillWoodID, 3333, 120, 1).setUnlocalizedName("wooddrill");
		drillStone = new ItemElectricDrill(SC2_ItemIDs.drillStoneID, 5000, 120, 1).setUnlocalizedName("stonedrill");
		drillIron = new ItemElectricDrill(SC2_ItemIDs.drillIronID, 6666, 120, 2).setUnlocalizedName("irondrill");
		drillDiamond = new ItemElectricDrill(SC2_ItemIDs.drillDiamondID, 8333, 120, 3).setUnlocalizedName("diamonddrill");
		drillGold = new ItemElectricDrill(SC2_ItemIDs.drillGoldID, 8000, 120, 1).setUnlocalizedName("golddrill");
		drillSteam = new ItemSteamDrill(SC2_ItemIDs.drillSteamID).setUnlocalizedName("steamdrill");
		drillEtherium = new ItemElectricDrill(SC2_ItemIDs.drillEtheriumID, 10000, 120, 3).setUnlocalizedName("etheriumdrill");
		drillObsidian = new ItemElectricDrill(SC2_ItemIDs.drillObsidianID, 9000, 120, 2).setUnlocalizedName("obsidiandrill");
		
		pickaxeSteam = new ItemSC2Pickaxe(SC2_ItemIDs.pickaxeSteamID, SC2_Material.STEAM_TOOL).setUnlocalizedName("steampick");
		swordSteam = new ItemSC2Sword(SC2_ItemIDs.swordSteamID, SC2_Material.STEAM_TOOL).setUnlocalizedName("steamsword");
		shovelSteam = new ItemSC2Spade(SC2_ItemIDs.shovelSteamID, SC2_Material.STEAM_TOOL).setUnlocalizedName("steamspade");
		axeSteam = new ItemSC2Axe(SC2_ItemIDs.axeSteamID, SC2_Material.STEAM_TOOL).setUnlocalizedName("steamaxe");
		hoeSteam = new ItemSC2Hoe(SC2_ItemIDs.hoeSteamID, SC2_Material.STEAM_TOOL).setUnlocalizedName("steamhoe");
		
		pickaxeEtherium = new ItemSC2Pickaxe(SC2_ItemIDs.pickaxeEtheriumID, SC2_Material.ETHERIUM_TOOL).setUnlocalizedName("etheriumpick");
		swordEtherium = new ItemSC2Sword(SC2_ItemIDs.swordEtheriumID, SC2_Material.ETHERIUM_TOOL).setUnlocalizedName("etheriumsword");
		shovelEtherium = new ItemSC2Spade(SC2_ItemIDs.shovelEtheriumID, SC2_Material.ETHERIUM_TOOL).setUnlocalizedName("etheriumspade");
		axeEtherium = new ItemSC2Axe(SC2_ItemIDs.axeEtheriumID, SC2_Material.ETHERIUM_TOOL).setUnlocalizedName("etheriumaxe");
		hoeEtherium = new ItemSC2Hoe(SC2_ItemIDs.hoeEtheriumID, SC2_Material.ETHERIUM_TOOL).setUnlocalizedName("etheriumhoe");
		
		pickaxeObsidian = new ItemSC2Pickaxe(SC2_ItemIDs.pickaxeObsidianID, SC2_Material.OBSIDIAN_TOOL).setUnlocalizedName("obsidianpick");
		swordObsidian = new ItemSC2Sword(SC2_ItemIDs.swordObsidianID, SC2_Material.OBSIDIAN_TOOL).setUnlocalizedName("obsidiansword");
		shovelObsidian = new ItemSC2Spade(SC2_ItemIDs.shovelObsidianID, SC2_Material.OBSIDIAN_TOOL).setUnlocalizedName("obsidianspade");
		axeObsidian = new ItemSC2Axe(SC2_ItemIDs.axeObsidianID, SC2_Material.OBSIDIAN_TOOL).setUnlocalizedName("obsidianaxe");
		hoeObsidian = new ItemSC2Hoe(SC2_ItemIDs.hoeObsidianID, SC2_Material.OBSIDIAN_TOOL).setUnlocalizedName("obsidianhoe");
		
		chisel = new ItemChisel(SC2_ItemIDs.chiselID, 64).setUnlocalizedName("chisel"); // TODO: Change from crafting to right-click
		spanner = new ItemChisel(SC2_ItemIDs.spannerID, 3).setUnlocalizedName("spanner");	
		
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