package common.steamcraft.common.item;

import net.minecraft.item.Item;

import common.steamcraft.common.lib2.ItemIDs;
import common.steamcraft.common.lib2.MaterialMod;

public class ModArmors 
{
	// Steam
	public static Item brassGoggles;
	public static Item aqualung;
	public static Item legBraces;
	public static Item rollerSkates;
	public static Item pnematicBoots;
	public static Item jetpack, jetBoots;
	public static Item stilts;
	public static Item reactivePistonPlate;
	
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
	public static Item brassWings, steamWings;
	//public static Item cape;
	//public static Item utilityBelt;
	
	public static void initArmors()
	{
		// 0 = Helm, 1 = Chest, 2 = Leggings, 3 = Boots
		//No more Magic Numbers!!
		int helmetSlot = 0;
		int chestSlot = 1;
		int legSlot = 2;
		int bootSlot = 3;

		brassGoggles = new ItemArmorMod(ItemIDs.brassGogglesID, MaterialMod.STEAM_ARMOR, 0, helmetSlot, "brass_1").setUnlocalizedName("brassGoggles");
		aqualung = new ItemArmorMod(ItemIDs.aqualungID, MaterialMod.STEAM_ARMOR, 0, chestSlot, "brass_1").setUnlocalizedName("aqualung");
		rollerSkates = new ItemArmorMod(ItemIDs.rollerSkatesID, MaterialMod.STEAM_ARMOR, 0, bootSlot, "brass_1").setUnlocalizedName("rollerSkates");
		legBraces = new ItemArmorMod(ItemIDs.legBracesID, MaterialMod.STEAM_ARMOR, 0, legSlot, "brass_2").setUnlocalizedName("legBraces");
		pnematicBoots = new ItemArmorMod(ItemIDs.pnematicBootsID, MaterialMod.STEAM_ARMOR, 0, bootSlot, "brass_2").setUnlocalizedName("pnematicBoots");
		jetpack = new ItemJetpack(ItemIDs.jetpackID, MaterialMod.STEAM_ARMOR, 0, chestSlot, "brass_2").setUnlocalizedName("jetpack");
		
		helmetEtherium = new ItemArmorMod(ItemIDs.helmetEtheriumID, MaterialMod.ETHERIUM_ARMOR, 0, helmetSlot, "etherium_1").setUnlocalizedName("helmetEtherium");
		plateEtherium = new ItemArmorMod(ItemIDs.plateEtheriumID, MaterialMod.ETHERIUM_ARMOR, 0, chestSlot, "etherium_1").setUnlocalizedName("plateEtherium");
		legsEtherium = new ItemArmorMod(ItemIDs.legsEtheriumID, MaterialMod.ETHERIUM_ARMOR, 0, legSlot, "etherium_2").setUnlocalizedName("legsEtherium");
		bootsEtherium = new ItemArmorMod(ItemIDs.bootsEtheriumID, MaterialMod.ETHERIUM_ARMOR, 0, bootSlot, "etherium_1").setUnlocalizedName("bootsEtherium");
		
		helmetObsidian = new ItemArmorMod(ItemIDs.helmetObsidianID, MaterialMod.OBSIDIAN_ARMOR, 0, helmetSlot, "obsidian_1").setUnlocalizedName("helmetObsidian");
		plateObsidian = new ItemArmorMod(ItemIDs.plateObsidianID, MaterialMod.OBSIDIAN_ARMOR, 0, chestSlot, "obsidian_1").setUnlocalizedName("plateObsidian");
		legsObsidian = new ItemArmorMod(ItemIDs.legsObsidianID, MaterialMod.OBSIDIAN_ARMOR, 0, legSlot, "obsidian_2").setUnlocalizedName("legsObsidian");
		bootsObsidian = new ItemArmorMod(ItemIDs.bootsObsidianID, MaterialMod.OBSIDIAN_ARMOR, 0, bootSlot, "obsidian_1").setUnlocalizedName("bootsObsidian");
		
		brassMonocle = new ItemBrassMonocle(ItemIDs.brassMonocleID, MaterialMod.STEAM_ARMOR, 0, helmetSlot, "brassMonocle");
		brassWings = new ItemBrassWings(ItemIDs.brassWingsID, MaterialMod.STEAM_ARMOR, 0, chestSlot, "brassWings");
		//cape = new ItemCape(SC2_ItemIDs.capeID, EnumArmorMaterial.CLOTH, 0, chestSlot, "capebelt");
	}
}