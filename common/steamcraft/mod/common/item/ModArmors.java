package common.steamcraft.mod.common.item;

import common.steamcraft.mod.common.lib.SC2_ItemIDs;
import common.steamcraft.mod.common.lib.SC2_Material;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;

public class ModArmors 
{
	// Steam
	public static Item brassGoggles;
	public static Item aqualung;
	public static Item legBraces;
	public static Item rollerSkates;
	
	// Etherium
	public static Item helmetEtherium;
	public static Item plateEtherium;
	public static Item legsEtherium;
	public static Item bootsEtherium;
	
	// Obsidian
	public static Item helmetObsidian;
	public static Item plateObsidian;
	public static Item legsObsidian;
	public static Item bootsObsidian;
	
	// ==== Experimental! ==== //
	public static Item brassMonocle;
	public static Item brassWings;
	//public static Item cape;
	//public static Item utilityBelt;
	
	public static void initArmors()
	{
		// 0 = Helm, 1 = Chest, 2 = Leggings, 3 = Boots
		brassGoggles = new ItemSC2Armor(SC2_ItemIDs.brassGogglesID, SC2_Material.STEAM_ARMOR, 0, 0, "brass_1").setUnlocalizedName("brassgoggles");
		aqualung = new ItemSC2Armor(SC2_ItemIDs.aqualungID, SC2_Material.STEAM_ARMOR, 0, 1, "brass_1").setUnlocalizedName("aqualung");
		legBraces = new ItemSC2Armor(SC2_ItemIDs.legBracesID, SC2_Material.STEAM_ARMOR, 0, 2, "brass_2").setUnlocalizedName("pneumaticbraces");
		rollerSkates = new ItemSC2Armor(SC2_ItemIDs.rollerSkatesID, SC2_Material.STEAM_ARMOR, 0, 3, "brass_1").setUnlocalizedName("rollerskates");
		
		helmetEtherium = new ItemSC2Armor(SC2_ItemIDs.helmetEtheriumID, SC2_Material.ETHERIUM_ARMOR, 0, 0, "etherium_1").setUnlocalizedName("etheriumhelmet");
		plateEtherium = new ItemSC2Armor(SC2_ItemIDs.plateEtheriumID, SC2_Material.ETHERIUM_ARMOR, 0, 1, "etherium_1").setUnlocalizedName("etheriumplate");
		legsEtherium = new ItemSC2Armor(SC2_ItemIDs.legsEtheriumID, SC2_Material.ETHERIUM_ARMOR, 0, 2, "etherium_2").setUnlocalizedName("etheriumlegs");
		bootsEtherium = new ItemSC2Armor(SC2_ItemIDs.bootsEtheriumID, SC2_Material.ETHERIUM_ARMOR, 0, 3, "etherium_1").setUnlocalizedName("etheriumboots");
		
		helmetObsidian = new ItemSC2Armor(SC2_ItemIDs.helmetObsidianID, SC2_Material.OBSIDIAN_ARMOR, 0, 0, "obsidian_1").setUnlocalizedName("obsidianhelmet");
		plateObsidian = new ItemSC2Armor(SC2_ItemIDs.plateObsidianID, SC2_Material.OBSIDIAN_ARMOR, 0, 1, "obsidian_1").setUnlocalizedName("obsidianplate");
		legsObsidian = new ItemSC2Armor(SC2_ItemIDs.legsObsidianID, SC2_Material.OBSIDIAN_ARMOR, 0, 2, "obsidian_2").setUnlocalizedName("obsidianlegs");
		bootsObsidian = new ItemSC2Armor(SC2_ItemIDs.bootsObsidianID, SC2_Material.OBSIDIAN_ARMOR, 0, 3, "obsidian_1").setUnlocalizedName("obsidianboots");
		
		brassMonocle = new ItemBrassMonocle(SC2_ItemIDs.brassMonocleID, SC2_Material.STEAM_ARMOR, 0, 0, "brassmonocle");
		brassWings = new ItemBrassWings(SC2_ItemIDs.brassWingsID, SC2_Material.STEAM_ARMOR, 0, 1, "brasswings");
		//cape = new ItemCape(SC2_ItemIDs.capeID, EnumArmorMaterial.CLOTH, 0, 1, "capebelt");
	
		
		initArmorNames();
	}

	public static void initArmorNames()
	{
		LanguageRegistry.addName(brassGoggles, "Brass Goggles");
		LanguageRegistry.addName(aqualung, "Aqualung");
		LanguageRegistry.addName(legBraces, "Pneumatic Leg Braces");
		LanguageRegistry.addName(rollerSkates, "Roller Skates");
		
		LanguageRegistry.addName(helmetEtherium, "Etherium Helmet");
		LanguageRegistry.addName(plateEtherium, "Etherium Chestplate");
		LanguageRegistry.addName(legsEtherium, "Etherium Leggings");
		LanguageRegistry.addName(bootsEtherium, "Etherium Boots");
		
		LanguageRegistry.addName(helmetObsidian, "Obsidian Helmet");
		LanguageRegistry.addName(plateObsidian, "Obsidian Chestplate");
		LanguageRegistry.addName(legsObsidian, "Obsidian Leggings");
		LanguageRegistry.addName(bootsObsidian, "Obsidian Boots");
		
		LanguageRegistry.addName(brassMonocle, "Brass Monocle");
		LanguageRegistry.addName(brassWings, "Brass Wings");
		//LanguageRegistry.addName(cape, "Leather Cloak");
	}
}