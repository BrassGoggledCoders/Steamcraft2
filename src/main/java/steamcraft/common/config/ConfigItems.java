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
 * File created @ [Mar 14, 2014, 1:29:42 PM]
 */
package steamcraft.common.config;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import steamcraft.common.Steamcraft;
import steamcraft.common.items.BaseItem;
import steamcraft.common.items.ItemBrassGoggles;
import steamcraft.common.items.ItemCanister;
import steamcraft.common.items.ItemChisel;
import steamcraft.common.items.ItemElectricJar;
import steamcraft.common.items.ItemFirearm;
import steamcraft.common.items.ItemIngot;
import steamcraft.common.items.ItemMachinePart;
import steamcraft.common.items.ItemMatch;
import steamcraft.common.items.ItemMultiParts;
import steamcraft.common.items.ItemNugget;
import steamcraft.common.items.ItemParts;
import steamcraft.common.items.ItemPipe;
import steamcraft.common.items.ItemPowder;
import steamcraft.common.items.ItemRayGun;
import steamcraft.common.items.ItemResource;
import steamcraft.common.items.ItemSheet;
import steamcraft.common.items.ItemShrinkray;
import steamcraft.common.items.ItemTeaSeed;
import steamcraft.common.items.ItemWatch;
import steamcraft.common.items.armor.ItemBrassArmor;
import steamcraft.common.items.armor.ItemClockworkWings;
import steamcraft.common.items.armor.ItemNormalArmor;
import steamcraft.common.items.armor.ItemSteamJetpack;
import steamcraft.common.items.tools.ItemDrill;
import steamcraft.common.items.tools.ItemHammer;
import steamcraft.common.items.tools.ItemModAxe;
import steamcraft.common.items.tools.ItemModHoe;
import steamcraft.common.items.tools.ItemModPickaxe;
import steamcraft.common.items.tools.ItemModShovel;
import steamcraft.common.items.tools.ItemModSword;
import steamcraft.common.items.tools.ItemSteamDrill;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.MaterialHelper;
import boilerplate.common.RegistryHelper;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 *
 * @author Surseance (Johnny Eatmon)
 */
public class ConfigItems
{
	// Item Tools(sort of)
	public static Item itemResource, itemIngot, itemSheet, itemPowder;

	public static Item itemWatch;

	public static Item itemTeaSeed;

	public static Item itemChisel;

	/* Armor */

	// Jetpacks
	public static Item itemClockworkWings;
	public static Item itemSteamJetpack;
	public static Item itemSteamWingpack;

	// Brass
	public static Item helmetBrass, chestplateBrass, legsBrass, bootsBrass;
	public static Item brassGoggles;

	// Etherium
	public static Item helmetEtherium;
	public static Item chestplateEtherium;
	public static Item legsEtherium;
	public static Item bootsEtherium;

	// Obsidian
	public static Item helmetObsidian;
	public static Item chestplateObsidian;
	public static Item legsObsidian;
	public static Item bootsObsidian;

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

	/* Tools */

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

	// Containers
	public static Item itemCanisterSteam;
	public static Item itemElectricJar;

	// Guns/Ammo
	public static Item flintlockMusket,matchlockMusket,percussionCapMusket;
	public static Item flintlockRifle, matchlockRifle,percussionCapRifle;
	public static Item flintlockPistol,matchlockPistol,percussionCapPistol;

	public static Item itemMusketBall, itemRifleBullet, itemPercussionCap;
	public static Item itemGunPart;

	public static Item itemColdKettle;
	public static Item itemHotKettle;
	public static Item itemEmptyKettle;

	public static Item itemEmptyTeacup;
	public static Item itemFullTeacup;

	public static Item itemMusketRound;

	public static Item itemBucketSteam;

	public static Item itemPipe;

	public static Item itemPoppySeed;

	public static Item itemRayGun, itemShrinkray;

	// Other
	public static Item chisel;
	public static Item spanner;
	public static Item itemHammer;
	public static Item itemMatch;

	public static Item itemNugget;

	public static Item itemMachinePart;
	public static Item itemCopperParts, itemBrassParts, itemSteelParts, itemIronParts;
	public static Item itemCopperMultiParts, itemBrassMultiParts, itemSteelMultiParts, itemIronMultiParts;

	/* Init */
	public static void init()
	{
		MaterialHelper.initializeMaterials();
		initializeItems();
	}

	private static void initializeItems()
	{
		/*
		 * itemAqualung = new ItemCustomArmor(Config.itemAqualungId,
		 * MaterialHelper.STEAM_ARMOR, 0, 1).setUnlocalizedName("itemAqualung");
		 * registerItem(itemAqualung, "ItemAqualung");
		 * itemLegBraces = new ItemCustomArmor(Config.itemLegBracesId,
		 * MaterialHelper.STEAM_ARMOR, 0,
		 * 2).setUnlocalizedName("itemLegBraces");
		 * registerItem(itemLegBraces, "ItemLegBraces",
		 * LibInfo.ID); itemRollerSkates = new
		 * ItemCustomArmor(Config.itemRollerSkatesId,
		 * MaterialHelper.STEAM_ARMOR, 0,
		 * 3).setUnlocalizedName("itemRollerSkates");
		 * registerItem(itemRollerSkates, "ItemRollerSkates",
		 * LibInfo.ID);
		 *
		 *
		 *
		 * //More Items itemTeacupEmpty = new
		 * ItemTeacup(Config.itemTeacupEmptyId, 0, 0.0F,
		 * false).setUnlocalizedName("itemTeacupEmpty");
		 * registerItem(itemTeacupEmpty, "ItemTeacupEmpty",
		 * LibInfo.ID); itemTeacupFull = new ItemTeacup(Config.itemTeacupFullId,
		 * 4, 0.9F, false).setUnlocalizedName("itemTeacupFull");
		 * registerItem(itemTeacupFull, "ItemTeacupFull",
		 * LibInfo.ID); itemKettleEmpty = new
		 * ItemKettle(Config.itemKettleEmptyId,
		 * 300).setUnlocalizedName("itemKettleEmpty");
		 * registerItem(itemTeacupFull, "ItemKettleEmpty",
		 * LibInfo.ID); itemKettleHot = new ItemKettle(Config.itemKettleHotId,
		 * 300).setUnlocalizedName("itemKettleHot");
		 * registerItem(itemTeacupFull, "ItemKettleHot",
		 * LibInfo.ID); itemKettleCold = new ItemKettle(Config.itemKettleColdId,
		 * 300).setUnlocalizedName("itemKettleCold");
		 * registerItem(itemTeacupFull, "ItemKettleCold",
		 * LibInfo.ID); itemGunParts = new
		 * ItemFirearmParts(Config.itemGunPartsId).setUnlocalizedName
		 * ("itemGunParts"); registerItem(itemGunParts,
		 * "ItemFirearmParts");
		 */

		initializeTools();
		initializeArmor();
		initializeGuns();
		initializeMetals();
		initializeOthers();
	}

	private static void initializeTools()
	{
		// Drills
		drillCore = new BaseItem().setUnlocalizedName("itemDrillCore");
		drillBase = new BaseItem().setUnlocalizedName("itemDrillBase");

		drillWood = new ItemDrill(MaterialHelper.DRILL_WOOD).setUnlocalizedName("itemDrillWood");
		drillStone = new ItemDrill(MaterialHelper.DRILL_STONE).setUnlocalizedName("itemDrillStone");
		drillIron = new ItemDrill(MaterialHelper.DRILL_IRON).setUnlocalizedName("itemDrillIron");
		drillDiamond = new ItemDrill(MaterialHelper.DRILL_EMERALD).setUnlocalizedName("itemDrillDiamond");
		drillGold = new ItemDrill(MaterialHelper.DRILL_GOLD).setUnlocalizedName("itemDrillGold");
		drillEtherium = new ItemDrill(MaterialHelper.DRILL_ETHERIUM).setUnlocalizedName("itemDrillEtherium");
		drillObsidian = new ItemDrill(MaterialHelper.DRILL_OBSIDIAN).setUnlocalizedName("itemDrillObsidian");
		drillSteam = new ItemSteamDrill(MaterialHelper.DRILL_STEAM).setUnlocalizedName("itemDrillSteam");

		registerItem(drillCore, "ItemDrillCore");
		registerItem(drillBase, "ItemDrillBase");

		registerItem(drillWood, "ItemDrillWood");
		registerItem(drillStone, "ItemDrillStone");
		registerItem(drillIron, "ItemDrillIron");
		registerItem(drillDiamond, "ItemDrillDiamond");
		registerItem(drillGold, "ItemDrillGold");
		registerItem(drillEtherium, "ItemDrillEtherium");
		registerItem(drillObsidian, "ItemDrillObsidian");
		registerItem(drillSteam, "ItemDrillSteam");

		//Steam
		pickaxeSteam = new ItemModPickaxe(MaterialHelper.TOOL_STEAM).setUnlocalizedName("itemPickaxeSteam");
		swordSteam = new ItemModSword(MaterialHelper.TOOL_STEAM).setUnlocalizedName("itemSwordSteam");
		shovelSteam = new ItemModShovel(MaterialHelper.TOOL_STEAM).setUnlocalizedName("itemShovelSteam");
		axeSteam = new ItemModAxe(MaterialHelper.TOOL_STEAM).setUnlocalizedName("itemAxeSteam");
		hoeSteam = new ItemModHoe(MaterialHelper.TOOL_STEAM).setUnlocalizedName("itemHoeSteam");

		RegistryHelper.registerToolSet(axeSteam, hoeSteam, pickaxeSteam, shovelSteam,
				swordSteam, "Steam", LibInfo.ID);

		//Etherium
		pickaxeEtherium = new ItemModPickaxe(MaterialHelper.TOOL_ETHERIUM).setUnlocalizedName("itemPickaxeEtherium");
		swordEtherium = new ItemModSword(MaterialHelper.TOOL_ETHERIUM).setUnlocalizedName("itemSwordEtherium");
		shovelEtherium = new ItemModShovel(MaterialHelper.TOOL_ETHERIUM).setUnlocalizedName("itemShovelEtherium");
		axeEtherium = new ItemModAxe(MaterialHelper.TOOL_ETHERIUM).setUnlocalizedName("itemAxeEtherium");
		hoeEtherium = new ItemModHoe(MaterialHelper.TOOL_ETHERIUM).setUnlocalizedName("itemHoeEtherium");

		RegistryHelper.registerToolSet(axeEtherium, hoeEtherium, pickaxeEtherium, shovelEtherium,
				swordEtherium, "Etherium", LibInfo.ID);

		//Obsidian
		pickaxeObsidian = new ItemModPickaxe(MaterialHelper.TOOL_OBSIDIAN).setUnlocalizedName("itemPickaxeObsidian");
		swordObsidian = new ItemModSword(MaterialHelper.TOOL_OBSIDIAN).setUnlocalizedName("itemSwordObsidian");
		shovelObsidian = new ItemModShovel(MaterialHelper.TOOL_OBSIDIAN).setUnlocalizedName("itemShovelObsidian");
		axeObsidian = new ItemModAxe(MaterialHelper.TOOL_OBSIDIAN).setUnlocalizedName("itemAxeObsidian");
		hoeObsidian = new ItemModHoe(MaterialHelper.TOOL_OBSIDIAN).setUnlocalizedName("itemHoeObsidian");

		RegistryHelper.registerToolSet(axeObsidian, hoeObsidian, pickaxeObsidian, shovelObsidian,
				swordObsidian, "Obsidian", LibInfo.ID);
	}

	private static void initializeArmor()
	{
		//Jetpacks
		itemSteamJetpack = new ItemSteamJetpack(MaterialHelper.ARMOR_STEAM, 0, 1, (byte)5).setUnlocalizedName("itemSteamJetpack");
		itemClockworkWings = new ItemClockworkWings(MaterialHelper.ARMOR_STEAM, 0, 1).setUnlocalizedName("itemClockworkWings");
		itemSteamWingpack = new ItemSteamJetpack(MaterialHelper.ARMOR_STEAM, 0, 1, (byte)4).setUnlocalizedName("itemSteamWingpack");

		registerItem(itemClockworkWings, "ItemClockworkWings");
		registerItem(itemSteamJetpack, "ItemSteamJetpack");
		registerItem(itemSteamWingpack, "ItemSteamWingpack");

		// Brass
		helmetBrass = new ItemBrassArmor(MaterialHelper.ARMOR_STEAM, 0, 0).setUnlocalizedName("itemHelmetBrass");
		chestplateBrass = new ItemBrassArmor(MaterialHelper.ARMOR_STEAM, 0, 0).setUnlocalizedName("itemChestplateBrass");
		legsBrass = new ItemBrassArmor(MaterialHelper.ARMOR_STEAM, 0, 0).setUnlocalizedName("itemLegsBrass");
		bootsBrass = new ItemBrassArmor(MaterialHelper.ARMOR_STEAM, 0, 0).setUnlocalizedName("itemBootsBrass");

		brassGoggles = new ItemBrassGoggles(ItemArmor.ArmorMaterial.CHAIN, 0, 0);

		registerItem(brassGoggles, "ItemBrassGoggles");

		//Etherium
		helmetEtherium = new ItemNormalArmor(MaterialHelper.ARMOR_ETHERIUM, 0, 0).setUnlocalizedName("itemHelmetEtherium");
		chestplateEtherium = new ItemNormalArmor(MaterialHelper.ARMOR_ETHERIUM, 0, 1).setUnlocalizedName("itemChestplateEtherium");
		legsEtherium = new ItemNormalArmor(MaterialHelper.ARMOR_ETHERIUM, 0, 2).setUnlocalizedName("itemLegsEtherium");
		bootsEtherium = new ItemNormalArmor(MaterialHelper.ARMOR_ETHERIUM, 0, 3).setUnlocalizedName("itemBootsEtherium");

		RegistryHelper.registerArmorSet(helmetEtherium, chestplateEtherium, legsEtherium, bootsEtherium,
				"Etherium", LibInfo.ID);

		//Obsidian
		helmetObsidian = new ItemNormalArmor(MaterialHelper.ARMOR_OBSIDIAN, 0, 0).setUnlocalizedName("itemHelmetObsidian");
		chestplateObsidian = new ItemNormalArmor(MaterialHelper.ARMOR_OBSIDIAN, 0, 1).setUnlocalizedName("itemChestplateObsidian");
		legsObsidian = new ItemNormalArmor(MaterialHelper.ARMOR_OBSIDIAN, 0, 2).setUnlocalizedName("itemLegsObsidian");
		bootsObsidian = new ItemNormalArmor(MaterialHelper.ARMOR_OBSIDIAN, 0, 3).setUnlocalizedName("itemBootsObsidian");

		RegistryHelper.registerArmorSet(helmetObsidian, chestplateObsidian, legsObsidian, bootsObsidian,
				"Obsidian", LibInfo.ID);
	}

	private static void initializeGuns()
	{
		//Ammo
		itemMusketBall = new BaseItem().setUnlocalizedName("itemMusketBall");
		itemRifleBullet = new BaseItem().setUnlocalizedName("itemRifleBullet");
		itemPercussionCap = new BaseItem().setUnlocalizedName("itemPercussionCap");

		registerItem(itemMusketBall, "ItemMusketBall");
		registerItem(itemRifleBullet, "ItemRifleBullet");
		registerItem(itemPercussionCap, "ItemPercussionCap");

		//Guns
		flintlockMusket = new ItemFirearm(5, 60, itemMusketBall, null, false, "steamcraft:musket", "steamcraft:reload").setUnlocalizedName("itemFlintlockMusket");
		matchlockMusket = new ItemFirearm(6, 40, itemMusketBall, itemMatch, true, "steamcraft:musket", "steamcraft:reload").setUnlocalizedName("itemMatchlockMusket");
		percussionCapMusket = new ItemFirearm(7, 20, itemMusketBall, itemPercussionCap, true, "steamcraft:musket", "steamcraft:reload").setUnlocalizedName("itemPercussionMusket");
		flintlockRifle = new ItemFirearm(8, 60, itemRifleBullet, null, false, "steamcraft:rifle", "steamcraft:reload").setUnlocalizedName("itemFlintlockRifle");
		matchlockRifle = new ItemFirearm(9, 40, itemRifleBullet, itemMatch, true, "steamcraft:rifle", "steamcraft:reload").setUnlocalizedName("itemMatchlockRifle");
		percussionCapRifle = new ItemFirearm(10, 20, itemRifleBullet, itemPercussionCap, true, "steamcraft:rifle", "steamcraft:reload").setUnlocalizedName("itemPercussionRifle");

		registerItem(flintlockMusket, "ItemFlintlockMusket");
		registerItem(matchlockMusket, "ItemMatchlockMusket");
		registerItem(percussionCapMusket, "ItemPercussionCapMusket");
		registerItem(flintlockRifle, "ItemFlintlockRifle");
		registerItem(matchlockRifle, "ItemMatchlockRifle");
		registerItem(percussionCapRifle, "ItemPercussionCapRifle");
	}

	private static void initializeMetals()
	{
		itemIngot = new ItemIngot();
		itemNugget = new ItemNugget();
		itemSheet = new ItemSheet();
		itemPowder = new ItemPowder();

		registerItem(itemIngot, "ItemIngot");
		registerItem(itemNugget, "ItemMetalNugget");
		registerItem(itemSheet, "ItemMetalSheet");
		registerItem(itemPowder, "ItemMetalPowder");

		// Simple
		itemMachinePart = new ItemMachinePart().setUnlocalizedName("itemMachinePart");
		itemCopperParts = ((ItemParts) new ItemParts().setUnlocalizedName("itemPartsCopper")).setMaterial("copper");
		itemIronParts = ((ItemParts) new ItemParts().setUnlocalizedName("itemPartsIron")).setMaterial("iron");
		itemBrassParts = ((ItemParts) new ItemParts().setUnlocalizedName("itemPartsBrass")).setMaterial("brass");
		itemSteelParts = ((ItemParts) new ItemParts().setUnlocalizedName("itemPartsSteel")).setMaterial("steel");

		registerItem(itemMachinePart, "ItemMachinePart");
		registerItem(itemCopperParts, "ItemCopperParts");
		registerItem(itemIronParts, "ItemIronParts");
		registerItem(itemBrassParts, "ItemBrassParts");
		registerItem(itemSteelParts, "ItemSteelParts");

		// Multi
		itemCopperMultiParts = ((ItemMultiParts) new ItemMultiParts().setUnlocalizedName("itemMultiPartsCopper")).setMaterial("copper");
		itemIronMultiParts = ((ItemMultiParts) new ItemMultiParts().setUnlocalizedName("itemMultiPartsIron")).setMaterial("iron");
		itemBrassMultiParts = ((ItemMultiParts) new ItemMultiParts().setUnlocalizedName("itemMultiPartsBrass")).setMaterial("brass");
		itemSteelMultiParts = ((ItemMultiParts) new ItemMultiParts().setUnlocalizedName("itemMultiPartsSteel")).setMaterial("steel");

		registerItem(itemCopperMultiParts, "ItemCopperMultiParts");
		registerItem(itemIronMultiParts, "ItemIronMultiParts");
		registerItem(itemBrassMultiParts, "ItemBrassMultiParts");
		registerItem(itemSteelMultiParts, "ItemSteelMultiParts");
	}

	private static void initializeOthers()
	{
		// Containers
		itemCanisterSteam = new ItemCanister().setUnlocalizedName("itemCanisterSteam");
		itemElectricJar = new ItemElectricJar().setUnlocalizedName("itemElectricJar");

		registerItem(itemCanisterSteam, "ItemCanisterSteam");
		registerItem(itemElectricJar, "ItemElectricJar");

		// Others
		itemResource = new ItemResource().setUnlocalizedName("itemResource");
		registerItem(itemResource, "ItemResource");

		itemWatch = new ItemWatch().setUnlocalizedName("itemWatch");
		registerItem(itemWatch, "ItemWatch");

		itemTeaSeed = new ItemTeaSeed().setUnlocalizedName("itemTeaSeed");
		registerItem(itemTeaSeed, "ItemTeaSeed");

		itemChisel = new ItemChisel().setUnlocalizedName("itemChisel");
		registerItem(itemChisel, "ItemChisel");

		itemPipe = new ItemPipe();
		registerItem(itemPipe, "ItemPipe");

		itemPoppySeed = new BaseItem().setUnlocalizedName("itemPoppySeed").setCreativeTab(Steamcraft.tabSC2);
		registerItem(itemPoppySeed, "ItemPoppySeed");

		itemRayGun = new ItemRayGun(LibInfo.PREFIX + "raygun");
		itemShrinkray = new ItemShrinkray(LibInfo.PREFIX + "shrinkray");

		registerItem(itemRayGun, "ItemRayGun");
		registerItem(itemShrinkray, "ItemShrinkray");

		itemHammer = new ItemHammer().setUnlocalizedName("itemHammer");
		registerItem(itemHammer, "ItemHammer");

		spanner = new ItemChisel().setUnlocalizedName("spanner");
		registerItem(spanner, "ItemSpanner");

		itemMatch = new ItemMatch().setUnlocalizedName("itemMatch");
		registerItem(itemMatch, "ItemMatch");
	}

	private static void registerItem(Item item, String name)
	{
		GameRegistry.registerItem(item, name, LibInfo.ID);
	}

	/* PostInit */
	public static void postInit()
	{
	}

	private static void registerItems()
	{
		/*registerItem(flintlockPistol, "ItemFlintlockPistol");
		registerItem(matchlockPistol, "ItemMatchlockPistol");
		registerItem(percussionCapPistol, "ItemPercussionCapPistol");*/
	}
}
