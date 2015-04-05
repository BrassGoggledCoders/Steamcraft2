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
package steamcraft.common.init;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import steamcraft.common.Steamcraft;
import steamcraft.common.items.BaseItem;
import steamcraft.common.items.ItemCanister;
import steamcraft.common.items.ItemChisel;
import steamcraft.common.items.ItemCoin;
import steamcraft.common.items.ItemCustomBucket;
import steamcraft.common.items.ItemCustomFood;
import steamcraft.common.items.ItemDimensionalPocket;
import steamcraft.common.items.ItemFieldManipulator;
import steamcraft.common.items.ItemFirearm;
import steamcraft.common.items.ItemGrappleGun;
import steamcraft.common.items.ItemGunPart;
import steamcraft.common.items.ItemIngot;
import steamcraft.common.items.ItemLoreBook;
import steamcraft.common.items.ItemMachinePart;
import steamcraft.common.items.ItemMatch;
import steamcraft.common.items.ItemMonsterSpawner;
import steamcraft.common.items.ItemNugget;
import steamcraft.common.items.ItemPDA;
import steamcraft.common.items.ItemParts;
import steamcraft.common.items.ItemPowder;
import steamcraft.common.items.ItemResource;
import steamcraft.common.items.ItemRocket;
import steamcraft.common.items.ItemRocketLauncher;
import steamcraft.common.items.ItemSheet;
import steamcraft.common.items.ItemSpanner;
import steamcraft.common.items.ItemSplashLightningBottle;
import steamcraft.common.items.ItemTeaSeed;
import steamcraft.common.items.ItemTeacup;
import steamcraft.common.items.ItemTeapot;
import steamcraft.common.items.ItemTimeClock;
import steamcraft.common.items.ItemVanillaPowder;
import steamcraft.common.items.ItemVanillaSheet;
import steamcraft.common.items.ItemWatch;
import steamcraft.common.items.ItemWithCraftingDurability;
import steamcraft.common.items.armor.ItemBrassArmor;
import steamcraft.common.items.armor.ItemBrassGoggles;
import steamcraft.common.items.armor.ItemClockworkWings;
import steamcraft.common.items.armor.ItemDivingHelmet;
import steamcraft.common.items.armor.ItemMonocle;
import steamcraft.common.items.armor.ItemNormalArmor;
import steamcraft.common.items.armor.ItemSteamJetpack;
import steamcraft.common.items.compat.ItemSteamcraftCluster;
import steamcraft.common.items.compat.ItemThaumicMonocle;
import steamcraft.common.items.electric.ElectricItem;
import steamcraft.common.items.electric.ItemRayGun;
import steamcraft.common.items.electric.ItemShrinkray;
import steamcraft.common.items.modules.ItemAqualung;
import steamcraft.common.items.modules.ItemClimbingSpikes;
import steamcraft.common.items.modules.ItemEmergencyTank;
import steamcraft.common.items.modules.ItemFlippers;
import steamcraft.common.items.modules.ItemGogglesModule;
import steamcraft.common.items.modules.ItemLastResort;
import steamcraft.common.items.modules.ItemLegBraces;
import steamcraft.common.items.modules.ItemLifeVest;
import steamcraft.common.items.modules.ItemParachute;
import steamcraft.common.items.modules.ItemPistonBoots;
import steamcraft.common.items.modules.ItemReactivePistonPlating;
import steamcraft.common.items.modules.ItemRollerSkates;
import steamcraft.common.items.modules.ItemSpringHeels;
import steamcraft.common.items.modules.ItemSteelPlating;
import steamcraft.common.items.modules.ItemWatchDisplay;
import steamcraft.common.items.modules.ItemWingpackModule;
import steamcraft.common.items.tools.ItemElectricDrill;
import steamcraft.common.items.tools.ItemElectrifiedSword;
import steamcraft.common.items.tools.ItemModAxe;
import steamcraft.common.items.tools.ItemModHoe;
import steamcraft.common.items.tools.ItemModPickaxe;
import steamcraft.common.items.tools.ItemModShovel;
import steamcraft.common.items.tools.ItemModSword;
import steamcraft.common.items.tools.ItemSteamDrill;
import steamcraft.common.items.vanity.ItemTopHat;
import steamcraft.common.lib.MaterialHelper;
import steamcraft.common.lib.ModInfo;
import boilerplate.common.utils.helpers.RegistryHelper;

/**
 * @author Surseance
 *
 */
public class InitItems
{
	// Metals
	public static Item itemResource, itemIngot, itemSheet, itemPowder, itemVanillaPowder, itemVanillaSheet, itemNugget;

	// Tea
	public static Item itemTeaSeed, itemTeaLeaf;
	public static Item itemColdKettle, itemHotKettle, itemEmptyKettle, itemTeapot;
	public static Item itemEmptyTeacup, itemFullTeacup, itemTeacup;

	// Armor
	public static Item itemClockworkWings;
	public static Item itemSteamJetpack;
	public static Item itemSteamWingpack;

	public static Item helmetBrass, chestplateBrass, legsBrass, bootsBrass;

	public static Item helmetEtherium, chestplateEtherium, legsEtherium, bootsEtherium;

	public static Item helmetObsidian, chestplateObsidian, legsObsidian, bootsObsidian;

	public static Item helmetWhalebone, chestplateWhalebone, legsWhalebone, bootsWhalebone;

	public static Item itemBrassGoggles, itemDivingHelmet, itemMonocle;

	// Tools
	public static Item drillCore, drillBase, drillWood, drillStone, drillIron, drillDiamond, drillGold, drillSteam, drillEtherium, drillObsidian;

	public static Item pickaxeSteam, swordSteam, shovelSteam, axeSteam, hoeSteam;

	public static Item pickaxeEtherium, swordEtherium, shovelEtherium, axeEtherium, hoeEtherium;

	public static Item pickaxeObsidian, swordObsidian, shovelObsidian, axeObsidian, hoeObsidian;

	public static Item itemElectricSword;

	public static Item itemSpanner, itemHammer, itemDrawplate, itemGrindstone;

	// Containers
	public static Item itemCanisterSteam;

	public static Item itemElectricJarSmall, itemElectricJarMedium, itemElectricJarLarge, itemElectricJarHuge;

	// Guns/Ammo
	public static Item flintlockMusket, matchlockMusket, percussionCapMusket;
	public static Item flintlockRifle, matchlockRifle, percussionCapRifle;
	public static Item flintlockPistol, matchlockPistol, percussionCapPistol;

	public static Item itemMusketBall, itemRifleBullet, itemPercussionCap;

	public static Item itemRayGun, itemShrinkray;

	public static Item itemClockworkRocketLauncher, itemRocket;

	public static Item itemGrappleGun;

	// Misc
	public static Item itemWatch;
	public static Item itemChisel;
	public static Item itemSlimeRubber;
	public static Item itemMatch;
	public static Item itemMachinePart, itemGunPart;
	public static Item itemCopperParts, itemBrassParts, itemSteelParts, itemIronParts;
	public static Item itemPDA;
	public static Item itemTimeClock;
	public static Item itemSpyglass;
	public static Item itemLoreBook;
	public static Item itemFieldManipulator;
	public static Item itemCraftingChip;
	public static Item itemSplashLightningBottle;
	public static Item itemMonsterSpawner;
	public static Item itemBoilingWaterBucket, itemBoilingMudBucket;
	public static Item itemCoin;
	public static Item itemShieldedCanister, itemEmptyShieldedCanister;
	public static Item itemWhalebone, itemWhaleMeat, itemWhaleBlubber, itemWhaleOilBucket, itemCookedWhaleMeat;
	public static Item itemDimPocket;

	// Compat
	public static Item itemThaumicMonocle, itemSteamcraftCluster;

	// Vanity
	public static Item itemTopHat;

	// Modules
	public static Item itemAqualung, itemPistonPlating, itemTank, itemWatchDisplay, itemClimbingSpikes
			, itemLegBraces, itemSpringHeels, itemRollerSkates, itemParachute, itemFlippers, itemLifeVest,
			itemPistonBoots, itemSteelPlating, itemLastResort, itemGogglesModule, itemWingpackModule;

	/* Init */
	public static void init()
	{
		MaterialHelper.initializeMaterials();
		initializeItems();
	}

	private static void initializeItems()
	{
		initializeTools();
		initializeArmor();
		initializeGuns();
		initializeMetals();
		initializeOthers();
		initializeModCompatItems();
	}

	private static void initializeTools()
	{
		// Drills
		drillCore = new BaseItem().setUnlocalizedName("itemDrillCore");
		drillBase = new BaseItem().setUnlocalizedName("itemDrillBase");

		drillWood = new ItemElectricDrill(MaterialHelper.DRILL_WOOD, 40, 80).setUnlocalizedName("itemDrillWood");
		drillStone = new ItemElectricDrill(MaterialHelper.DRILL_STONE, 80, 80).setUnlocalizedName("itemDrillStone");
		drillIron = new ItemElectricDrill(MaterialHelper.DRILL_IRON, 100, 200).setUnlocalizedName("itemDrillIron");
		drillDiamond = new ItemElectricDrill(MaterialHelper.DRILL_DIAMOND, 200, 300).setUnlocalizedName("itemDrillDiamond");
		drillGold = new ItemElectricDrill(MaterialHelper.DRILL_GOLD, 80, 500).setUnlocalizedName("itemDrillGold");
		drillEtherium = new ItemElectricDrill(MaterialHelper.DRILL_ETHERIUM, 400, 1000).setUnlocalizedName("itemDrillEtherium");
		drillObsidian = new ItemElectricDrill(MaterialHelper.DRILL_OBSIDIAN, 250, 500).setUnlocalizedName("itemDrillObsidian");
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

		// Steam
		pickaxeSteam = new ItemModPickaxe(MaterialHelper.TOOL_STEAM).setUnlocalizedName("itemPickaxeSteam");
		swordSteam = new ItemModSword(MaterialHelper.TOOL_STEAM).setUnlocalizedName("itemSwordSteam");
		shovelSteam = new ItemModShovel(MaterialHelper.TOOL_STEAM).setUnlocalizedName("itemShovelSteam");
		axeSteam = new ItemModAxe(MaterialHelper.TOOL_STEAM).setUnlocalizedName("itemAxeSteam");
		hoeSteam = new ItemModHoe(MaterialHelper.TOOL_STEAM).setUnlocalizedName("itemHoeSteam");

		RegistryHelper.registerToolSet(swordSteam, shovelSteam, pickaxeSteam, axeSteam, hoeSteam, "Steam", ModInfo.ID);

		// Etherium
		pickaxeEtherium = new ItemModPickaxe(MaterialHelper.TOOL_ETHERIUM).setUnlocalizedName("itemPickaxeEtherium");
		swordEtherium = new ItemModSword(MaterialHelper.TOOL_ETHERIUM).setUnlocalizedName("itemSwordEtherium");
		shovelEtherium = new ItemModShovel(MaterialHelper.TOOL_ETHERIUM).setUnlocalizedName("itemShovelEtherium");
		axeEtherium = new ItemModAxe(MaterialHelper.TOOL_ETHERIUM).setUnlocalizedName("itemAxeEtherium");
		hoeEtherium = new ItemModHoe(MaterialHelper.TOOL_ETHERIUM).setUnlocalizedName("itemHoeEtherium");

		RegistryHelper.registerToolSet(swordEtherium, shovelEtherium, pickaxeEtherium, axeEtherium, hoeEtherium, "Etherium", ModInfo.ID);

		// Obsidian
		pickaxeObsidian = new ItemModPickaxe(MaterialHelper.TOOL_OBSIDIAN).setUnlocalizedName("itemPickaxeObsidian");
		swordObsidian = new ItemModSword(MaterialHelper.TOOL_OBSIDIAN).setUnlocalizedName("itemSwordObsidian");
		shovelObsidian = new ItemModShovel(MaterialHelper.TOOL_OBSIDIAN).setUnlocalizedName("itemShovelObsidian");
		axeObsidian = new ItemModAxe(MaterialHelper.TOOL_OBSIDIAN).setUnlocalizedName("itemAxeObsidian");
		hoeObsidian = new ItemModHoe(MaterialHelper.TOOL_OBSIDIAN).setUnlocalizedName("itemHoeObsidian");

		RegistryHelper.registerToolSet(swordObsidian, shovelObsidian, pickaxeObsidian, axeObsidian, hoeObsidian, "Obsidian", ModInfo.ID);

		itemElectricSword = new ItemElectrifiedSword(MaterialHelper.HTOOL_ELEC, 400, 400).setUnlocalizedName("itemElectricSword");
		registerItem(itemElectricSword, "ItemElectricSword");
	}

	private static void initializeArmor()
	{
		// Jetpacks
		itemSteamJetpack = new ItemSteamJetpack(MaterialHelper.ARMOR_STEAM, 0, 1, (byte) 5).setUnlocalizedName("itemSteamJetpack");
		itemClockworkWings = new ItemClockworkWings(MaterialHelper.ARMOR_STEAM, 0, 1).setUnlocalizedName("itemClockworkWings");
		itemSteamWingpack = new ItemSteamJetpack(MaterialHelper.ARMOR_STEAM, 0, 1, (byte) 4).setUnlocalizedName("itemSteamWingpack");

		registerItem(itemClockworkWings, "ItemClockworkWings");
		registerItem(itemSteamJetpack, "ItemSteamJetpack");
		registerItem(itemSteamWingpack, "ItemSteamWingpack");

		// Brass
		helmetBrass = new ItemBrassArmor(MaterialHelper.ARMOR_STEAM, 0, 0).setUnlocalizedName("itemHelmetBrass");
		chestplateBrass = new ItemBrassArmor(MaterialHelper.ARMOR_STEAM, 0, 1).setUnlocalizedName("itemChestplateBrass");
		legsBrass = new ItemBrassArmor(MaterialHelper.ARMOR_STEAM, 0, 2).setUnlocalizedName("itemLegsBrass");
		bootsBrass = new ItemBrassArmor(MaterialHelper.ARMOR_STEAM, 0, 3).setUnlocalizedName("itemBootsBrass");

		RegistryHelper.registerArmorSet(helmetBrass, chestplateBrass, legsBrass, bootsBrass, "Brass", ModInfo.ID);

		itemBrassGoggles = new ItemBrassGoggles(ItemArmor.ArmorMaterial.CHAIN, 0, 0).setUnlocalizedName("itemBrassGoggles");
		itemMonocle = new ItemMonocle(ItemArmor.ArmorMaterial.CHAIN, 0, 0).setUnlocalizedName("itemMonocle");
		itemDivingHelmet = new ItemDivingHelmet(ItemArmor.ArmorMaterial.CHAIN, 0, 0).setUnlocalizedName("itemDivingHelmet");

		registerItem(itemBrassGoggles, "ItemBrassGoggles");
		registerItem(itemDivingHelmet, "ItemDivingHelmet");
		registerItem(itemMonocle, "ItemMonocle");

		// Etherium
		helmetEtherium = new ItemNormalArmor(MaterialHelper.ARMOR_ETHERIUM, 0, 0).setUnlocalizedName("itemHelmetEtherium");
		chestplateEtherium = new ItemNormalArmor(MaterialHelper.ARMOR_ETHERIUM, 0, 1).setUnlocalizedName("itemChestplateEtherium");
		legsEtherium = new ItemNormalArmor(MaterialHelper.ARMOR_ETHERIUM, 0, 2).setUnlocalizedName("itemLegsEtherium");
		bootsEtherium = new ItemNormalArmor(MaterialHelper.ARMOR_ETHERIUM, 0, 3).setUnlocalizedName("itemBootsEtherium");

		RegistryHelper.registerArmorSet(helmetEtherium, chestplateEtherium, legsEtherium, bootsEtherium, "Etherium", ModInfo.ID);

		// Obsidian
		helmetObsidian = new ItemNormalArmor(MaterialHelper.ARMOR_OBSIDIAN, 0, 0).setUnlocalizedName("itemHelmetObsidian");
		chestplateObsidian = new ItemNormalArmor(MaterialHelper.ARMOR_OBSIDIAN, 0, 1).setUnlocalizedName("itemChestplateObsidian");
		legsObsidian = new ItemNormalArmor(MaterialHelper.ARMOR_OBSIDIAN, 0, 2).setUnlocalizedName("itemLegsObsidian");
		bootsObsidian = new ItemNormalArmor(MaterialHelper.ARMOR_OBSIDIAN, 0, 3).setUnlocalizedName("itemBootsObsidian");

		RegistryHelper.registerArmorSet(helmetObsidian, chestplateObsidian, legsObsidian, bootsObsidian, "Obsidian", ModInfo.ID);

		// Whalebone
		helmetWhalebone = new ItemNormalArmor(MaterialHelper.ARMOR_WHALEBONE, 0, 0).setUnlocalizedName("itemHelmetWhalebone");
		chestplateWhalebone = new ItemNormalArmor(MaterialHelper.ARMOR_WHALEBONE, 0, 1).setUnlocalizedName("itemChestplateWhalebone");
		legsWhalebone = new ItemNormalArmor(MaterialHelper.ARMOR_WHALEBONE, 0, 2).setUnlocalizedName("itemLegsWhalebone");
		bootsWhalebone = new ItemNormalArmor(MaterialHelper.ARMOR_WHALEBONE, 0, 3).setUnlocalizedName("itemBootsWhalebone");

		RegistryHelper.registerArmorSet(helmetWhalebone, chestplateWhalebone, legsWhalebone, bootsWhalebone, "Whalebone", ModInfo.ID);
	}

	private static void initializeGuns()
	{
		// Ammo
		itemMusketBall = new BaseItem().setUnlocalizedName("itemMusketBall");
		itemRifleBullet = new BaseItem().setUnlocalizedName("itemRifleBullet");
		itemPercussionCap = new BaseItem().setUnlocalizedName("itemPercussionCap");

		registerItem(itemMusketBall, "ItemMusketBall");
		registerItem(itemRifleBullet, "ItemRifleBullet");
		registerItem(itemPercussionCap, "ItemPercussionCap");

		// Guns
		flintlockMusket = new ItemFirearm(5, 60, itemMusketBall, null, "steamcraft:musket", "steamcraft:reload")
				.setUnlocalizedName("itemFlintlockMusket");
		matchlockMusket = new ItemFirearm(6, 40, itemMusketBall, itemMatch, "steamcraft:musket", "steamcraft:reload")
				.setUnlocalizedName("itemMatchlockMusket");
		percussionCapMusket = new ItemFirearm(7, 20, itemMusketBall, itemPercussionCap, "steamcraft:musket", "steamcraft:reload")
				.setUnlocalizedName("itemPercussionMusket");
		flintlockRifle = new ItemFirearm(8, 60, itemRifleBullet, null, "steamcraft:rifle", "steamcraft:reload")
				.setUnlocalizedName("itemFlintlockRifle");
		matchlockRifle = new ItemFirearm(9, 40, itemRifleBullet, itemMatch, "steamcraft:rifle", "steamcraft:reload")
				.setUnlocalizedName("itemMatchlockRifle");
		percussionCapRifle = new ItemFirearm(10, 20, itemRifleBullet, itemPercussionCap, "steamcraft:rifle", "steamcraft:reload")
				.setUnlocalizedName("itemPercussionRifle");
		flintlockPistol = new ItemFirearm(3, 40, itemMusketBall, null, "steamcraft:Pistol", "steamcraft:reload")
				.setUnlocalizedName("itemFlintlockPistol");
		matchlockPistol = new ItemFirearm(3, 30, itemMusketBall, itemMatch, "steamcraft:pistol", "steamcraft:reload")
				.setUnlocalizedName("itemMatchlockPistol");
		percussionCapPistol = new ItemFirearm(3, 20, itemMusketBall, itemPercussionCap, "steamcraft:pistol", "steamcraft:reload")
				.setUnlocalizedName("itemPercussionPistol");

		registerItem(flintlockMusket, "ItemFlintlockMusket");
		registerItem(matchlockMusket, "ItemMatchlockMusket");
		registerItem(percussionCapMusket, "ItemPercussionCapMusket");
		registerItem(flintlockRifle, "ItemFlintlockRifle");
		registerItem(matchlockRifle, "ItemMatchlockRifle");
		registerItem(percussionCapRifle, "ItemPercussionCapRifle");
		registerItem(flintlockPistol, "ItemFlintlockPistol");
		registerItem(matchlockPistol, "ItemMatchlockPistol");
		registerItem(percussionCapPistol, "ItemPercussionCapPistol");

		itemRayGun = new ItemRayGun(ModInfo.PREFIX + "raygun", 50, ItemRayGun.energyPerUse, 200).setUnlocalizedName("itemRaygun");
		itemShrinkray = new ItemShrinkray(ModInfo.PREFIX + "shrinkray", 50, ItemShrinkray.energyPerUse, 200).setUnlocalizedName("itemShrinkray");

		registerItem(itemRayGun, "ItemRayGun");
		registerItem(itemShrinkray, "ItemShrinkray");
		// registerItem(itemLightningGun, "ItemLightningGun");

		itemRocket = new ItemRocket().setUnlocalizedName("itemClockworkRocket").setCreativeTab(Steamcraft.tabSC2);
		itemClockworkRocketLauncher = new ItemRocketLauncher(50, itemRocket, "steamcraft:rocket", "steamcraft:reload");

		registerItem(itemRocket, "ItemRocket");
		registerItem(itemClockworkRocketLauncher, "ItemClockworkRocketLauncher");
	}

	private static void initializeMetals()
	{
		itemIngot = new ItemIngot().setUnlocalizedName("itemIngot");
		itemNugget = new ItemNugget().setUnlocalizedName("itemNugget");
		itemSheet = new ItemSheet().setUnlocalizedName("itemSheet");
		itemPowder = new ItemPowder().setUnlocalizedName("itemPowder");
		itemVanillaPowder = new ItemVanillaPowder().setUnlocalizedName("itemVanillaPowder");
		itemVanillaSheet = new ItemVanillaSheet().setUnlocalizedName("itemVanillaSheet");

		registerItem(itemIngot, "ItemIngot");
		registerItem(itemNugget, "ItemMetalNugget");
		registerItem(itemSheet, "ItemMetalSheet");
		registerItem(itemPowder, "ItemMetalPowder");
		registerItem(itemVanillaPowder, "ItemVanillaMetalPowder");
		registerItem(itemVanillaSheet, "ItemVanillaMetalSheet");

		itemMachinePart = new ItemMachinePart().setUnlocalizedName("itemMachinePart");
		itemGunPart = new ItemGunPart().setUnlocalizedName("itemGunPart");
		itemCopperParts = ((ItemParts) new ItemParts().setUnlocalizedName("itemPartsCopper")).setMaterial("Copper");
		itemIronParts = ((ItemParts) new ItemParts().setUnlocalizedName("itemPartsIron")).setMaterial("Iron");
		itemBrassParts = ((ItemParts) new ItemParts().setUnlocalizedName("itemPartsBrass")).setMaterial("Brass");
		itemSteelParts = ((ItemParts) new ItemParts().setUnlocalizedName("itemPartsSteel")).setMaterial("Steel");

		registerItem(itemMachinePart, "ItemMachinePart");
		registerItem(itemGunPart, "ItemGunPart");
		registerItem(itemCopperParts, "ItemCopperParts");
		registerItem(itemIronParts, "ItemIronParts");
		registerItem(itemBrassParts, "ItemBrassParts");
		registerItem(itemSteelParts, "ItemSteelParts");
	}

	private static void initializeOthers()
	{
		// Containers
		itemCanisterSteam = new ItemCanister().setUnlocalizedName("itemCanisterSteam");
		registerItem(itemCanisterSteam, "ItemCanisterSteam");

		itemElectricJarSmall = new ElectricItem(100, 40, 80).setUnlocalizedName("itemElectricJarSmall");
		itemElectricJarMedium = new ElectricItem(500, 125, 400).setUnlocalizedName("itemElectricJarMedium");
		itemElectricJarLarge = new ElectricItem(2500, 750, 2000).setUnlocalizedName("itemElectricJarLarge");
		itemElectricJarHuge = new ElectricItem(10000, 3000, 10000).setUnlocalizedName("itemElectricJarHuge");

		registerItem(itemElectricJarSmall, "ItemElectricJarSmall");
		registerItem(itemElectricJarMedium, "ItemElectricJarMedium");
		registerItem(itemElectricJarLarge, "ItemElectricJarLarge");
		registerItem(itemElectricJarHuge, "ItemElectricJarHuge");

		// Others
		itemResource = new ItemResource().setUnlocalizedName("itemResource");
		registerItem(itemResource, "ItemResource");

		itemWatch = new ItemWatch().setUnlocalizedName("itemWatch");
		registerItem(itemWatch, "ItemWatch");

		itemTimeClock = new ItemTimeClock().setUnlocalizedName("itemTimeClock");
		registerItem(itemTimeClock, "ItemTimeClock");

		itemTeaSeed = new ItemTeaSeed().setUnlocalizedName("itemTeaSeed");
		registerItem(itemTeaSeed, "ItemTeaSeed");

		itemChisel = new ItemChisel().setUnlocalizedName("itemChisel");
		registerItem(itemChisel, "ItemChisel");

		itemHammer = new ItemWithCraftingDurability().setUnlocalizedName("itemHammer");
		registerItem(itemHammer, "ItemHammer");

		itemDrawplate = new ItemWithCraftingDurability().setUnlocalizedName("itemDrawplate");
		registerItem(itemDrawplate, "ItemDrawplate");

		itemGrindstone = new ItemWithCraftingDurability().setUnlocalizedName("itemGrindstone");
		registerItem(itemGrindstone, "ItemGrindstone");

		itemSpanner = new ItemSpanner().setUnlocalizedName("itemSpanner");
		registerItem(itemSpanner, "ItemSpanner");

		itemMatch = new ItemMatch().setUnlocalizedName("itemMatch");
		registerItem(itemMatch, "ItemMatch");

		itemTopHat = new ItemTopHat().setUnlocalizedName("itemTopHat");
		registerItem(itemTopHat, "ItemTopHat");

		itemAqualung = new ItemAqualung().setUnlocalizedName("itemAqualung");
		registerItem(itemAqualung, "ItemAqualung");

		itemPistonPlating = new ItemReactivePistonPlating().setUnlocalizedName("itemPistonPlating");
		registerItem(itemPistonPlating, "ItemPistonPlating");

		itemTank = new ItemEmergencyTank().setUnlocalizedName("itemEmergencyTank");
		registerItem(itemTank, "ItemEmergencyTank");

		itemWatchDisplay = new ItemWatchDisplay().setUnlocalizedName("itemWatchDisplay");
		registerItem(itemWatchDisplay, "ItemWatchDisplay");

		itemClimbingSpikes = new ItemClimbingSpikes().setUnlocalizedName("itemClimbingSpikes");
		registerItem(itemClimbingSpikes, "ItemClimbingSpikes");

		itemLegBraces = new ItemLegBraces().setUnlocalizedName("itemLegBraces");
		registerItem(itemLegBraces, "ItemLegBraces");

		itemSpringHeels = new ItemSpringHeels().setUnlocalizedName("itemSpringHeels");
		registerItem(itemSpringHeels, "ItemSpringHeels");

		itemRollerSkates = new ItemRollerSkates().setUnlocalizedName("itemRollerSkates");
		registerItem(itemRollerSkates, "ItemRollerSkates");

		itemParachute = new ItemParachute().setUnlocalizedName("itemParachute");
		registerItem(itemParachute, "ItemParachute");

		itemFlippers = new ItemFlippers().setUnlocalizedName("itemFlippers");
		registerItem(itemFlippers, "ItemFlippers");

		itemLifeVest = new ItemLifeVest().setUnlocalizedName("itemLifeVest");
		registerItem(itemLifeVest, "ItemLifeVest");

		itemPistonBoots = new ItemPistonBoots().setUnlocalizedName("itemPistonBoots");
		registerItem(itemPistonBoots, "ItemPistonBoots");

		itemSteelPlating = new ItemSteelPlating().setUnlocalizedName("itemSteelPlating");
		registerItem(itemSteelPlating, "ItemSteelPlating");

		itemLastResort = new ItemLastResort().setUnlocalizedName("itemLastResort");
		// registerItem(itemLastResort, "ItemLastResort");

		itemGogglesModule = new ItemGogglesModule().setUnlocalizedName("itemGogglesModule");
		registerItem(itemGogglesModule, "ItemGogglesModule");

		itemWingpackModule = new ItemWingpackModule().setUnlocalizedName("itemWingpackModule");
		registerItem(itemWingpackModule, "ItemWingpackModule");

		itemTeaLeaf = new BaseItem().setUnlocalizedName("itemTeaLeaves");
		registerItem(itemTeaLeaf, "ItemTeaLeaf");

		itemSlimeRubber = new BaseItem().setUnlocalizedName("itemSlimeRubber");
		registerItem(itemSlimeRubber, "ItemSlimeRubber");

		itemTeapot = new ItemTeapot().setUnlocalizedName("itemTeapot");
		itemTeacup = new ItemTeacup().setUnlocalizedName("itemTeacup");

		registerItem(itemTeapot, "ItemTeapot");
		registerItem(itemTeacup, "ItemTeacup");

		itemGrappleGun = new ItemGrappleGun().setUnlocalizedName("itemGrappleGun");
		// registerItem(itemGrappleGun, "ItemGrappleGun");

		itemPDA = new ItemPDA().setUnlocalizedName("itemPDA");
		// registerItem(itemPDA, "ItemPDA");

		itemSpyglass = new BaseItem().setUnlocalizedName("itemSpyglass");
		registerItem(itemSpyglass, "ItemSpyglass");

		// Add a version system to allow updates. Check version on right click
		itemLoreBook = new ItemLoreBook(StatCollector.translateToLocal("lore.steamcraft2.0.author"),
				StatCollector.translateToLocal("lore.steamcraft2.0.title"), new String[] { StatCollector.translateToLocal("lore.steamcraft2.0.page1") })
				.setUnlocalizedName("itemLoreBook").setCreativeTab(
						Steamcraft.tabSC2);
		registerItem(itemLoreBook, "ItemLoreBook");

		itemFieldManipulator = new ItemFieldManipulator().setUnlocalizedName("itemFieldManipulator");
		registerItem(itemFieldManipulator, "ItemFieldManipulator");

		// itemCraftingChip = new ItemChip(new ContainerWorkbench(null, null, 0, 0, 0)).setUnlocalizedName("itemCraftingChip");
		// registerItem(itemCraftingChip, "ItemCraftingChip");

		itemSplashLightningBottle = new ItemSplashLightningBottle().setUnlocalizedName("itemSplashLightningBottle");
		registerItem(itemSplashLightningBottle, "ItemSplashLightningBottle");

		itemMonsterSpawner = new ItemMonsterSpawner().setUnlocalizedName("itemMonsterSpawner");
		registerItem(itemMonsterSpawner, "ItemSteamcraftMonsterSpawner");

		itemBoilingWaterBucket = new ItemCustomBucket(InitBlocks.blockBoilingMud).setUnlocalizedName("itemBoilingWaterBucket");
		itemBoilingMudBucket = new ItemCustomBucket(InitBlocks.blockBoilingMud).setUnlocalizedName("itemMudBucket");
		GameRegistry.registerItem(itemBoilingWaterBucket, "ItemBoilingWaterBucket");
		GameRegistry.registerItem(itemBoilingMudBucket, "ItemBoilingBucket");
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("boilingwater", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(
				itemBoilingWaterBucket), new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("boilingmud", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(
				itemBoilingMudBucket), new ItemStack(Items.bucket));

		itemWhaleOilBucket = new ItemCustomBucket(InitBlocks.blockWhaleOil).setUnlocalizedName("itemWhaleOilBucket");
		GameRegistry.registerItem(itemWhaleOilBucket, "ItemWhaleOilBucket");
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("whaleoil", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(
				itemWhaleOilBucket), new ItemStack(Items.bucket));

		itemCoin = new ItemCoin().setUnlocalizedName("itemCoin");
		// registerItem(itemCoin, "ItemCoin");

		itemEmptyShieldedCanister = new BaseItem().setUnlocalizedName("itemEmptyShieldedCanister");
		registerItem(itemEmptyShieldedCanister, "ItemEmptyShieldedCanister");

		itemShieldedCanister = new BaseItem().setUnlocalizedName("itemFullShieldedCanister").setContainerItem(InitItems.itemEmptyShieldedCanister);
		registerItem(itemShieldedCanister, "ItemShieldedCanister");

		itemWhalebone = new BaseItem().setUnlocalizedName("itemWhalebone");
		registerItem(itemWhalebone, "ItemWhalebone");

		itemWhaleMeat = new ItemCustomFood(3, 0.4F, false).setUnlocalizedName("itemWhaleMeat");
		registerItem(itemWhaleMeat, "ItemWhaleMeat");

		itemCookedWhaleMeat = new ItemCustomFood(6, 0.8F, true).setUnlocalizedName("itemCookedWhaleMeat");
		registerItem(itemCookedWhaleMeat, "ItemCookedWhaleMeat");

		itemWhaleBlubber = new BaseItem().setUnlocalizedName("itemWhaleBlubber");
		registerItem(itemWhaleBlubber, "ItemWhaleBlubber");

		// Also known as a pokkit by some wierdo called Longeye ;)
		itemDimPocket = new ItemDimensionalPocket().setUnlocalizedName("itemDimPocket");
		// registerItem(itemDimPocket, "ItemDimPocket");
	}

	public static void initializeModCompatItems()
	{
		/** Registration of these are handled in @link={CompatibilityLayer.class} */
		itemSteamcraftCluster = new ItemSteamcraftCluster().setUnlocalizedName("itemSteamcraftCluster");

		itemThaumicMonocle = new ItemThaumicMonocle().setUnlocalizedName("itemThaumicMonocle");
	}

	private static void registerItem(Item item, String name)
	{
		// TODO if(ConfigGeneral.nameBlacklist != null && !ConfigGeneral.nameBlacklist.contains(item.getUnlocalizedName()))
		GameRegistry.registerItem(item, name, ModInfo.ID);
	}
}
