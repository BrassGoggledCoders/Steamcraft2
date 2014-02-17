package common.steamcraft.common.item;

import common.steamcraft.common.lib.ItemIDs;
import common.steamcraft.common.lib.MaterialMod;
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
		brassGoggles = new ItemArmorMod(ItemIDs.brassGogglesID, MaterialMod.STEAM_ARMOR, 0, 0, "brass_1").setUnlocalizedName("brassgoggles");
		aqualung = new ItemArmorMod(ItemIDs.aqualungID, MaterialMod.STEAM_ARMOR, 0, 1, "brass_1").setUnlocalizedName("aqualung");
		legBraces = new ItemArmorMod(ItemIDs.legBracesID, MaterialMod.STEAM_ARMOR, 0, 2, "brass_2").setUnlocalizedName("pneumaticbraces");
		rollerSkates = new ItemArmorMod(ItemIDs.rollerSkatesID, MaterialMod.STEAM_ARMOR, 0, 3, "brass_1").setUnlocalizedName("rollerskates");
		
		helmetEtherium = new ItemArmorMod(ItemIDs.helmetEtheriumID, MaterialMod.ETHERIUM_ARMOR, 0, 0, "etherium_1").setUnlocalizedName("etheriumhelmet");
		plateEtherium = new ItemArmorMod(ItemIDs.plateEtheriumID, MaterialMod.ETHERIUM_ARMOR, 0, 1, "etherium_1").setUnlocalizedName("etheriumplate");
		legsEtherium = new ItemArmorMod(ItemIDs.legsEtheriumID, MaterialMod.ETHERIUM_ARMOR, 0, 2, "etherium_2").setUnlocalizedName("etheriumlegs");
		bootsEtherium = new ItemArmorMod(ItemIDs.bootsEtheriumID, MaterialMod.ETHERIUM_ARMOR, 0, 3, "etherium_1").setUnlocalizedName("etheriumboots");
		
		helmetObsidian = new ItemArmorMod(ItemIDs.helmetObsidianID, MaterialMod.OBSIDIAN_ARMOR, 0, 0, "obsidian_1").setUnlocalizedName("obsidianhelmet");
		plateObsidian = new ItemArmorMod(ItemIDs.plateObsidianID, MaterialMod.OBSIDIAN_ARMOR, 0, 1, "obsidian_1").setUnlocalizedName("obsidianplate");
		legsObsidian = new ItemArmorMod(ItemIDs.legsObsidianID, MaterialMod.OBSIDIAN_ARMOR, 0, 2, "obsidian_2").setUnlocalizedName("obsidianlegs");
		bootsObsidian = new ItemArmorMod(ItemIDs.bootsObsidianID, MaterialMod.OBSIDIAN_ARMOR, 0, 3, "obsidian_1").setUnlocalizedName("obsidianboots");
		
		brassMonocle = new ItemBrassMonocle(ItemIDs.brassMonocleID, MaterialMod.STEAM_ARMOR, 0, 0, "brassmonocle");
		brassWings = new ItemBrassWings(ItemIDs.brassWingsID, MaterialMod.STEAM_ARMOR, 0, 1, "brasswings");
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