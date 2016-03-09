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
package xyz.brassgoggledcoders.steamcraft.common.init;

import boilerplate.common.utils.handlers.BucketHandler;
import boilerplate.common.utils.helpers.RegistryHelper;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import xyz.brassgoggledcoders.steamcraft.common.Steamcraft;
import xyz.brassgoggledcoders.steamcraft.common.items.BaseItem;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemCanister;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemChisel;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemCoin;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemCustomBucket;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemCustomFood;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemDimensionalPocket;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemFieldManipulator;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemFirearm;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemGrappleGun;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemGunPart;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemHandbook;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemIngot;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemLoreBook;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemMachinePart;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemMatch;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemMobBottle;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemMonsterSpawner;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemNugget;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemNuggetIron;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemParts;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemPowder;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemResource;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemRocket;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemRocketLauncher;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemSheet;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemSpanner;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemSplashLightningBottle;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemTeaSeed;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemTeacup;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemTeapot;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemTimeClock;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemVanillaPowder;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemVanillaSheet;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemWatch;
import xyz.brassgoggledcoders.steamcraft.common.items.ItemWithCraftingDurability;
import xyz.brassgoggledcoders.steamcraft.common.items.armor.ItemBrassArmor;
import xyz.brassgoggledcoders.steamcraft.common.items.armor.ItemBrassGoggles;
import xyz.brassgoggledcoders.steamcraft.common.items.armor.ItemClockworkWings;
import xyz.brassgoggledcoders.steamcraft.common.items.armor.ItemDivingHelmet;
import xyz.brassgoggledcoders.steamcraft.common.items.armor.ItemMonocle;
import xyz.brassgoggledcoders.steamcraft.common.items.armor.ItemNormalArmor;
import xyz.brassgoggledcoders.steamcraft.common.items.armor.ItemSteamJetpack;
import xyz.brassgoggledcoders.steamcraft.common.items.compat.ItemSteamcraftCluster;
import xyz.brassgoggledcoders.steamcraft.common.items.compat.ItemThaumicMonocle;
import xyz.brassgoggledcoders.steamcraft.common.items.electric.ElectricItem;
import xyz.brassgoggledcoders.steamcraft.common.items.electric.ItemRayGun;
import xyz.brassgoggledcoders.steamcraft.common.items.electric.ItemShrinkray;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemAqualung;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemAutofeeder;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemClimbingSpikes;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemEmergencyTank;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemFlippers;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemFreezeBoots;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemGogglesModule;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemLastResort;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemLegBraces;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemLifeVest;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemParachute;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemPistonBoots;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemReactivePistonPlating;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemRollerSkates;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemSpringHeels;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemSteelPlating;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemSuperFreezeBoots;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemWatchDisplay;
import xyz.brassgoggledcoders.steamcraft.common.items.modules.ItemWingpackModule;
import xyz.brassgoggledcoders.steamcraft.common.items.tools.ItemElectricDrill;
import xyz.brassgoggledcoders.steamcraft.common.items.tools.ItemElectrifiedSword;
import xyz.brassgoggledcoders.steamcraft.common.items.tools.steam.ItemSteamAxe;
import xyz.brassgoggledcoders.steamcraft.common.items.tools.steam.ItemSteamDrill;
import xyz.brassgoggledcoders.steamcraft.common.items.tools.steam.ItemSteamHoe;
import xyz.brassgoggledcoders.steamcraft.common.items.tools.steam.ItemSteamPickaxe;
import xyz.brassgoggledcoders.steamcraft.common.items.tools.steam.ItemSteamShovel;
import xyz.brassgoggledcoders.steamcraft.common.items.tools.steam.ItemSteamSword;
import xyz.brassgoggledcoders.steamcraft.common.items.vanity.ItemTopHat;
import xyz.brassgoggledcoders.steamcraft.common.lib.ModInfo;

/**
 * @author Surseance
 *
 */
public class InitItems {
	// Tools
	public static Item drillCore, drillBase, drillWood, drillStone, drillIron, drillDiamond, drillGold, drillSteam;

	// Guns/Ammo
	public static Item flintlockMusket, matchlockMusket, percussionCapMusket;
	public static Item flintlockPistol, matchlockPistol, percussionCapPistol;
	public static Item flintlockRifle, matchlockRifle, percussionCapRifle;

	public static Item helmetBrass, chestplateBrass, legsBrass, bootsBrass;

	public static Item helmetWhalebone, chestplateWhalebone, legsWhalebone, bootsWhalebone;

	// Modules
	public static Item itemAqualung, itemPistonPlating, itemTank, itemWatchDisplay, itemClimbingSpikes, itemLegBraces, itemSpringHeels, itemRollerSkates, itemParachute, itemFlippers, itemLifeVest, itemPistonBoots, itemSteelPlating, itemLastResort, itemGogglesModule, itemWingpackModule, itemFreezeBoots, itemSuperFreezeBoots, itemAutofeeder;

	public static Item itemBoilingWaterBucket, itemBoilingMudBucket;

	public static Item itemMoltenZincBucket, itemMoltenBrassBucket;

	public static Item itemBrassGoggles, itemDivingHelmet, itemMonocle;

	// Containers
	public static Item itemCanisterSteam, itemReinforcedCanisterSteam;

	public static Item itemChisel;

	public static Item itemClockworkRocketLauncher, itemRocket;

	// Armor
	public static Item itemClockworkWings;

	public static Item itemCoin;

	public static Item itemColdKettle, itemHotKettle, itemEmptyKettle, itemTeapot;

	public static Item itemIronParts, itemSteelParts;

	public static Item itemCraftingChip;

	public static Item itemDimPocket;

	public static Item itemElectricJarSmall, itemElectricJarMedium, itemElectricJarLarge, itemElectricJarHuge;
	public static Item itemElectricSword;
	public static Item itemEmptyTeacup, itemFullTeacup, itemTeacup;

	public static Item itemFieldManipulator;

	public static Item itemGrappleGun;

	public static Item itemHandbook;

	public static Item itemLoreBook;

	public static Item itemMachinePart, itemGunPart;
	public static Item itemMatch;
	public static Item itemMonsterSpawner;
	public static Item itemMusketBall, itemRifleBullet, itemPercussionCap;
	public static Item itemRayGun, itemShrinkray;
	public static Item itemRedwoodStick, itemWillowStick, itemMangroveStick, itemPetrifiedStick;
	// Metals
	public static Item itemResource, itemIngot, itemSheet, itemPowder, itemVanillaPowder, itemVanillaSheet, itemNugget, itemNuggetIron;

	public static Item itemSlimeRubber;
	public static Item itemSpanner, itemHammer, itemDrawplate, itemGrindstone, itemBugHammer;
	public static Item itemSplashLightningBottle;
	public static Item itemSpyglass;
	public static Item itemSteamJetpack;
	public static Item itemSteamWingpack;
	// Tea
	public static Item itemTeaSeed, itemTeaLeaf;
	// Compat
	public static Item itemThaumicMonocle, itemSteamcraftCluster;
	public static Item itemTimeClock;
	// Vanity
	public static Item itemTopHat;
	// Misc
	public static Item itemWatch;

	public static Item itemWhalebone, itemWhaleMeat, itemWhaleBlubber, itemWhaleOilBucket, itemCookedWhaleMeat;

	public static Item pickaxeSteam, swordSteam, shovelSteam, axeSteam, hoeSteam;

	public static Item itemEmptyMobBottle, itemMobBottle;

	/* Init */
	public static void init() {
		InitMaterials.initializeMaterials();
		initializeItems();
	}

	private static void initializeItems() {
		initializeArmor();
		initializeGuns();
		initializeMetals();
		initializeModCompatItems();
		initializeOthers();
		initializeTools();
	}

	private static void initializeArmor() {
		// Jetpacks
		itemSteamJetpack = new ItemSteamJetpack(InitMaterials.ARMOR_STEAM, 0, 1, (byte) 5).setUnlocalizedName("itemSteamJetpack");
		itemClockworkWings = new ItemClockworkWings(InitMaterials.ARMOR_STEAM, 0, 1).setUnlocalizedName("itemClockworkWings");
		itemSteamWingpack = new ItemSteamJetpack(InitMaterials.ARMOR_STEAM, 0, 1, (byte) 4).setUnlocalizedName("itemSteamWingpack");

		registerItem(itemClockworkWings, "ItemClockworkWings");
		registerItem(itemSteamJetpack, "ItemSteamJetpack");
		registerItem(itemSteamWingpack, "ItemSteamWingpack");

		// Brass
		helmetBrass = new ItemBrassArmor(InitMaterials.ARMOR_STEAM, 0, 0).setUnlocalizedName("itemHelmetBrass");
		chestplateBrass = new ItemBrassArmor(InitMaterials.ARMOR_STEAM, 0, 1).setUnlocalizedName("itemChestplateBrass");
		legsBrass = new ItemBrassArmor(InitMaterials.ARMOR_STEAM, 0, 2).setUnlocalizedName("itemLegsBrass");
		bootsBrass = new ItemBrassArmor(InitMaterials.ARMOR_STEAM, 0, 3).setUnlocalizedName("itemBootsBrass");

		RegistryHelper.registerArmorSet(helmetBrass, chestplateBrass, legsBrass, bootsBrass, "Brass", ModInfo.ID);

		itemBrassGoggles = new ItemBrassGoggles(ItemArmor.ArmorMaterial.CHAIN, 0, 0).setUnlocalizedName("itemBrassGoggles");
		itemMonocle = new ItemMonocle(ItemArmor.ArmorMaterial.CHAIN, 0, 0).setUnlocalizedName("itemMonocle");
		itemDivingHelmet = new ItemDivingHelmet(ItemArmor.ArmorMaterial.CHAIN, 0, 0).setUnlocalizedName("itemDivingHelmet");

		registerItem(itemBrassGoggles, "ItemBrassGoggles");
		registerItem(itemDivingHelmet, "ItemDivingHelmet");
		registerItem(itemMonocle, "ItemMonocle");

		// Whalebone
		helmetWhalebone = new ItemNormalArmor(InitMaterials.ARMOR_WHALEBONE, 0, "whalebone").setUnlocalizedName("itemHelmetWhalebone");
		chestplateWhalebone = new ItemNormalArmor(InitMaterials.ARMOR_WHALEBONE, 1, "whalebone").setUnlocalizedName("itemChestplateWhalebone");
		legsWhalebone = new ItemNormalArmor(InitMaterials.ARMOR_WHALEBONE, 2, "whalebone").setUnlocalizedName("itemLegsWhalebone");
		bootsWhalebone = new ItemNormalArmor(InitMaterials.ARMOR_WHALEBONE, 3, "whalebone").setUnlocalizedName("itemBootsWhalebone");

		RegistryHelper.registerArmorSet(helmetWhalebone, chestplateWhalebone, legsWhalebone, bootsWhalebone, "Whalebone", ModInfo.ID);
	}

	private static void initializeGuns() {
		// Ammo
		itemMusketBall = new BaseItem().setUnlocalizedName("itemMusketBall");
		itemRifleBullet = new BaseItem().setUnlocalizedName("itemRifleBullet");
		itemPercussionCap = new BaseItem().setUnlocalizedName("itemPercussionCap");

		registerItem(itemMusketBall, "ItemMusketBall");
		registerItem(itemRifleBullet, "ItemRifleBullet");
		registerItem(itemPercussionCap, "ItemPercussionCap");

		// Guns
		flintlockMusket = new ItemFirearm(5, 60, itemMusketBall, null, "steamcraft:musket", "steamcraft:reload").setUnlocalizedName("itemFlintlockMusket");
		matchlockMusket = new ItemFirearm(6, 40, itemMusketBall, itemMatch, "steamcraft:musket", "steamcraft:reload").setUnlocalizedName("itemMatchlockMusket");
		percussionCapMusket = new ItemFirearm(7, 20, itemMusketBall, itemPercussionCap, "steamcraft:musket", "steamcraft:reload").setUnlocalizedName("itemPercussionMusket");
		flintlockRifle = new ItemFirearm(8, 60, itemRifleBullet, null, "steamcraft:rifle", "steamcraft:reload").setUnlocalizedName("itemFlintlockRifle");
		matchlockRifle = new ItemFirearm(9, 40, itemRifleBullet, itemMatch, "steamcraft:rifle", "steamcraft:reload").setUnlocalizedName("itemMatchlockRifle");
		percussionCapRifle = new ItemFirearm(10, 20, itemRifleBullet, itemPercussionCap, "steamcraft:rifle", "steamcraft:reload").setUnlocalizedName("itemPercussionRifle");
		flintlockPistol = new ItemFirearm(3, 40, itemMusketBall, null, "steamcraft:Pistol", "steamcraft:reload").setUnlocalizedName("itemFlintlockPistol");
		matchlockPistol = new ItemFirearm(3, 30, itemMusketBall, itemMatch, "steamcraft:pistol", "steamcraft:reload").setUnlocalizedName("itemMatchlockPistol");
		percussionCapPistol = new ItemFirearm(3, 20, itemMusketBall, itemPercussionCap, "steamcraft:pistol", "steamcraft:reload").setUnlocalizedName("itemPercussionPistol");

		registerItem(flintlockMusket, "ItemFlintlockMusket");
		registerItem(matchlockMusket, "ItemMatchlockMusket");
		registerItem(percussionCapMusket, "ItemPercussionCapMusket");
		registerItem(flintlockRifle, "ItemFlintlockRifle");
		registerItem(matchlockRifle, "ItemMatchlockRifle");
		registerItem(percussionCapRifle, "ItemPercussionCapRifle");
		registerItem(flintlockPistol, "ItemFlintlockPistol");
		registerItem(matchlockPistol, "ItemMatchlockPistol");
		registerItem(percussionCapPistol, "ItemPercussionCapPistol");

		itemRayGun = new ItemRayGun(ModInfo.PREFIX + "raygun", 100, 200).setUnlocalizedName("itemRaygun");
		itemShrinkray = new ItemShrinkray(ModInfo.PREFIX + "shrinkray", 500, 1000).setUnlocalizedName("itemShrinkray");

		registerItem(itemRayGun, "ItemRayGun");
		registerItem(itemShrinkray, "ItemShrinkray");
		// registerItem(itemLightningGun, "ItemLightningGun");

		itemRocket = new ItemRocket().setUnlocalizedName("itemClockworkRocket").setCreativeTab(Steamcraft.tabSC2);
		itemClockworkRocketLauncher = new ItemRocketLauncher(50, itemRocket, "steamcraft:rocket", "steamcraft:reload");

		registerItem(itemRocket, "ItemRocket");
		registerItem(itemClockworkRocketLauncher, "ItemClockworkRocketLauncher");
	}

	private static void initializeMetals() {
		itemIngot = new ItemIngot().setUnlocalizedName("itemIngot");
		itemNugget = new ItemNugget().setUnlocalizedName("itemNugget");
		itemNuggetIron = new ItemNuggetIron().setUnlocalizedName("itemNuggetIron");
		itemSheet = new ItemSheet().setUnlocalizedName("itemSheet");
		itemPowder = new ItemPowder().setUnlocalizedName("itemPowder");
		itemVanillaPowder = new ItemVanillaPowder().setUnlocalizedName("itemVanillaPowder");
		itemVanillaSheet = new ItemVanillaSheet().setUnlocalizedName("itemVanillaSheet");

		registerItem(itemIngot, "ItemIngot");
		registerItem(itemNugget, "ItemMetalNugget");
		registerItem(itemNuggetIron, "ItemIronNugget");
		registerItem(itemSheet, "ItemMetalSheet");
		registerItem(itemPowder, "ItemMetalPowder");
		registerItem(itemVanillaPowder, "ItemVanillaMetalPowder");
		registerItem(itemVanillaSheet, "ItemVanillaMetalSheet");

		itemMachinePart = new ItemMachinePart().setUnlocalizedName("itemMachinePart");
		itemGunPart = new ItemGunPart().setUnlocalizedName("itemGunPart");
		itemIronParts = ((ItemParts) new ItemParts().setUnlocalizedName("itemPartsIron")).setMaterial("Iron");
		itemSteelParts = ((ItemParts) new ItemParts().setUnlocalizedName("itemPartsSteel")).setMaterial("Steel");

		registerItem(itemMachinePart, "ItemMachinePart");
		registerItem(itemGunPart, "ItemGunPart");
		registerItem(itemIronParts, "ItemIronParts");
		registerItem(itemSteelParts, "ItemSteelParts");
	}

	public static void initializeModCompatItems() {
		/**
		 * Registration of these are handled in @link={CompatibilityLayer.class}
		 */
		itemSteamcraftCluster = new ItemSteamcraftCluster().setUnlocalizedName("itemSteamcraftCluster");

		itemThaumicMonocle = new ItemThaumicMonocle().setUnlocalizedName("itemThaumicMonocle");
	}

	private static void initializeOthers() {
		// Containers
		itemCanisterSteam = new ItemCanister(10000, 20).setUnlocalizedName("itemCanisterSteam");
		registerItem(itemCanisterSteam, "ItemCanisterSteam");
		FluidContainerRegistry.registerFluidContainer(new FluidStack(FluidRegistry.getFluid("steam"), 10000), ((ItemCanister) itemCanisterSteam).getFilledCanister(), new ItemStack(itemCanisterSteam));

		itemReinforcedCanisterSteam = new ItemCanister(20000, 40).setUnlocalizedName("itemReinforcedCanisterSteam");
		registerItem(itemReinforcedCanisterSteam, "ItemReinforcedCanisterSteam");
		FluidContainerRegistry.registerFluidContainer(new FluidStack(FluidRegistry.getFluid("steam"), 20000), ((ItemCanister) itemReinforcedCanisterSteam).getFilledCanister(), new ItemStack(itemReinforcedCanisterSteam));

		itemElectricJarSmall = new ElectricItem(80, 80, 40).setUnlocalizedName("itemElectricJarSmall");
		itemElectricJarMedium = new ElectricItem(400, 400, 125).setUnlocalizedName("itemElectricJarMedium");
		itemElectricJarLarge = new ElectricItem(1000, 2000, 750).setUnlocalizedName("itemElectricJarLarge");
		itemElectricJarHuge = new ElectricItem(4000, 10000, 3000).setUnlocalizedName("itemElectricJarHuge");

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
		MinecraftForge.addGrassSeed(new ItemStack(itemTeaSeed), 8);

		itemChisel = new ItemChisel().setUnlocalizedName("itemChisel").setFull3D();
		registerItem(itemChisel, "ItemChisel");

		itemHammer = new ItemWithCraftingDurability().setUnlocalizedName("itemHammer").setFull3D();
		registerItem(itemHammer, "ItemHammer");

		itemBugHammer = new ItemWithCraftingDurability().setHasEffect().setUnlocalizedName("itemBugHammer").setFull3D();
		registerItem(itemBugHammer, "ItemBugHammer");

		itemDrawplate = new ItemWithCraftingDurability().setUnlocalizedName("itemDrawplate");
		registerItem(itemDrawplate, "ItemDrawplate");

		itemGrindstone = new ItemWithCraftingDurability().setUnlocalizedName("itemGrindstone");
		registerItem(itemGrindstone, "ItemGrindstone");

		itemSpanner = new ItemSpanner().setUnlocalizedName("itemSpanner").setFull3D();
		registerItem(itemSpanner, "ItemSpanner");

		itemMatch = new ItemMatch().setUnlocalizedName("itemMatch");
		registerItem(itemMatch, "ItemMatch");

		itemTopHat = new ItemTopHat().setUnlocalizedName("itemTopHat");
		// registerItem(itemTopHat, "ItemTopHat");

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
		registerItem(itemLastResort, "ItemLastResort");

		itemGogglesModule = new ItemGogglesModule().setUnlocalizedName("itemGogglesModule");
		registerItem(itemGogglesModule, "ItemGogglesModule");

		itemWingpackModule = new ItemWingpackModule().setUnlocalizedName("itemWingpackModule");
		registerItem(itemWingpackModule, "ItemWingpackModule");

		itemFreezeBoots = new ItemFreezeBoots().setUnlocalizedName("itemFreezeBoots");
		registerItem(itemFreezeBoots, "ItemFreezeBoots");

		itemSuperFreezeBoots = new ItemSuperFreezeBoots().setUnlocalizedName("itemSuperFreezeBoots");
		registerItem(itemSuperFreezeBoots, "ItemSuperFreezeBoots");

		itemAutofeeder = new ItemAutofeeder().setUnlocalizedName("itemAutofeeder");
		registerItem(itemAutofeeder, "ItemAutofeeder");

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

		itemHandbook = new ItemHandbook().setUnlocalizedName("itemHandbook");
		registerItem(itemHandbook, "ItemHandbook");

		itemSpyglass = new BaseItem().setUnlocalizedName("itemSpyglass").setFull3D();
		registerItem(itemSpyglass, "ItemSpyglass");

		// TODO Add a version system to allow updates. Check version on right
		// click
		itemLoreBook = new ItemLoreBook(StatCollector.translateToLocal("lore.steamcraft2.0.author"), StatCollector.translateToLocal("lore.steamcraft2.0.title"), new String[] { StatCollector.translateToLocal("lore.steamcraft2.0.page1") }).setUnlocalizedName("itemLoreBook");
		registerItem(itemLoreBook, "ItemLoreBook");

		itemFieldManipulator = new ItemFieldManipulator().setUnlocalizedName("itemFieldManipulator");
		registerItem(itemFieldManipulator, "ItemFieldManipulator");

		// itemCraftingChip = new ItemChip(new ContainerWorkbench(null, null, 0,
		// 0, 0)).setUnlocalizedName("itemCraftingChip");
		// registerItem(itemCraftingChip, "ItemCraftingChip");

		itemSplashLightningBottle = new ItemSplashLightningBottle().setUnlocalizedName("itemSplashLightningBottle");
		registerItem(itemSplashLightningBottle, "ItemSplashLightningBottle");

		itemMonsterSpawner = new ItemMonsterSpawner().setUnlocalizedName("itemMonsterSpawner");
		registerItem(itemMonsterSpawner, "ItemSteamcraftMonsterSpawner");

		itemBoilingWaterBucket = new ItemCustomBucket(FluidRegistry.getFluid("boilingwater").getBlock()).setUnlocalizedName("itemBoilingWaterBucket");
		itemBoilingMudBucket = new ItemCustomBucket(FluidRegistry.getFluid("boilingmud").getBlock()).setUnlocalizedName("itemMudBucket");
		itemWhaleOilBucket = new ItemCustomBucket(FluidRegistry.getFluid("whaleoil").getBlock()).setUnlocalizedName("itemWhaleOilBucket");
		itemMoltenZincBucket = new ItemCustomBucket(FluidRegistry.getFluid("moltenzinc").getBlock()).setUnlocalizedName("itemMoltenZincBucket");
		itemMoltenBrassBucket = new ItemCustomBucket(FluidRegistry.getFluid("moltenbrass").getBlock()).setUnlocalizedName("itemMoltenBrassBucket");
		GameRegistry.registerItem(itemBoilingWaterBucket, "ItemBoilingWaterBucket");
		GameRegistry.registerItem(itemBoilingMudBucket, "ItemBoilingMudBucket");
		GameRegistry.registerItem(itemWhaleOilBucket, "ItemWhaleOilBucket");
		GameRegistry.registerItem(itemMoltenZincBucket, "ItemMoltenZincBucket");
		GameRegistry.registerItem(itemMoltenBrassBucket, "ItemMoltenBrassBucket");
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("boilingwater", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(itemBoilingWaterBucket), new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("boilingmud", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(itemBoilingMudBucket), new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("whaleoil", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(itemWhaleOilBucket), new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("moltenzinc", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(itemMoltenZincBucket), new ItemStack(Items.bucket));
		FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("moltenbrass", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(itemMoltenBrassBucket), new ItemStack(Items.bucket));
		BucketHandler.getInstance().bucketMap.put(InitBlocks.blockBoilingWater, itemBoilingWaterBucket);
		BucketHandler.getInstance().bucketMap.put(InitBlocks.blockBoilingMud, itemBoilingMudBucket);
		BucketHandler.getInstance().bucketMap.put(InitBlocks.blockWhaleOil, itemWhaleOilBucket);
		BucketHandler.getInstance().bucketMap.put(InitBlocks.blockMoltenZinc, itemMoltenZincBucket);
		BucketHandler.getInstance().bucketMap.put(InitBlocks.blockMoltenBrass, itemMoltenBrassBucket);
		MinecraftForge.EVENT_BUS.register(BucketHandler.getInstance());

		itemWhalebone = new BaseItem().setUnlocalizedName("itemWhalebone");
		registerItem(itemWhalebone, "ItemWhalebone");

		itemWhaleMeat = new ItemCustomFood(3, 0.4F, false).setUnlocalizedName("itemWhaleMeat");
		registerItem(itemWhaleMeat, "ItemWhaleMeat");

		itemCookedWhaleMeat = new ItemCustomFood(6, 0.8F, true).setUnlocalizedName("itemCookedWhaleMeat");
		registerItem(itemCookedWhaleMeat, "ItemCookedWhaleMeat");

		itemWhaleBlubber = new BaseItem().setUnlocalizedName("itemWhaleBlubber");
		registerItem(itemWhaleBlubber, "ItemWhaleBlubber");

		itemCoin = new ItemCoin().setUnlocalizedName("itemCoin");
		registerItem(itemCoin, "ItemCoin");

		// Also known as a pokkit by some wierdo called Longeye ;)
		itemDimPocket = new ItemDimensionalPocket().setUnlocalizedName("itemDimPocket");
		// TODO registerItem(itemDimPocket, "ItemDimPocket");

		itemRedwoodStick = new BaseItem().setUnlocalizedName("itemRedwoodStick");
		registerItem(itemRedwoodStick, "ItemRedwoodStick");
		itemMangroveStick = new BaseItem().setUnlocalizedName("itemMangroveStick");
		registerItem(itemMangroveStick, "ItemMangroveStick");
		itemWillowStick = new BaseItem().setUnlocalizedName("itemWillowStick");
		registerItem(itemWillowStick, "ItemWillowStick");
		itemPetrifiedStick = new BaseItem().setUnlocalizedName("itemPetrifiedStick");
		registerItem(itemPetrifiedStick, "ItemPetrifiedStick");

		itemEmptyMobBottle = new BaseItem().setUnlocalizedName("itemEmptyMobBottle");
		// registerItem(itemEmptyMobBottle, "ItemEmptyMobBottle");
		itemMobBottle = new ItemMobBottle().setUnlocalizedName("itemMobBottle");
		// registerItem(itemMobBottle, "ItemMobBottle");
	}

	private static void initializeTools() {
		// Drills
		drillCore = new BaseItem().setUnlocalizedName("itemDrillCore");
		drillBase = new BaseItem().setUnlocalizedName("itemDrillBase");

		drillWood = new ItemElectricDrill(InitMaterials.DRILL_WOOD, 40, 80).setUnlocalizedName("itemDrillWood");
		drillStone = new ItemElectricDrill(InitMaterials.DRILL_STONE, 80, 80).setUnlocalizedName("itemDrillStone");
		drillIron = new ItemElectricDrill(InitMaterials.DRILL_IRON, 100, 200).setUnlocalizedName("itemDrillIron");
		drillDiamond = new ItemElectricDrill(InitMaterials.DRILL_DIAMOND, 250, 300).setUnlocalizedName("itemDrillDiamond");
		drillGold = new ItemElectricDrill(InitMaterials.DRILL_GOLD, 80, 200).setUnlocalizedName("itemDrillGold");
		drillSteam = new ItemSteamDrill(InitMaterials.DRILL_STEAM).setUnlocalizedName("itemDrillSteam");

		registerItem(drillCore, "ItemDrillCore");
		registerItem(drillBase, "ItemDrillBase");

		registerItem(drillWood, "ItemDrillWood");
		registerItem(drillStone, "ItemDrillStone");
		registerItem(drillIron, "ItemDrillIron");
		registerItem(drillDiamond, "ItemDrillDiamond");
		registerItem(drillGold, "ItemDrillGold");
		registerItem(drillSteam, "ItemDrillSteam");

		// Steam
		pickaxeSteam = new ItemSteamPickaxe(InitMaterials.TOOL_STEAM).setUnlocalizedName("itemPickaxeSteam");
		swordSteam = new ItemSteamSword(InitMaterials.TOOL_STEAM).setUnlocalizedName("itemSwordSteam");
		shovelSteam = new ItemSteamShovel(InitMaterials.TOOL_STEAM).setUnlocalizedName("itemShovelSteam");
		axeSteam = new ItemSteamAxe(InitMaterials.TOOL_STEAM).setUnlocalizedName("itemAxeSteam");
		hoeSteam = new ItemSteamHoe(InitMaterials.TOOL_STEAM).setUnlocalizedName("itemHoeSteam");

		RegistryHelper.registerToolSet(swordSteam, shovelSteam, pickaxeSteam, axeSteam, hoeSteam, "Steam", ModInfo.ID);

		itemElectricSword = new ItemElectrifiedSword(InitMaterials.HTOOL_ELEC, 400, 400).setUnlocalizedName("itemElectricSword");
		registerItem(itemElectricSword, "ItemElectricSword");
	}

	private static void registerItem(Item item, String name) {
		// TODO if(ConfigGeneral.nameBlacklist != null &&
		// !ConfigGeneral.nameBlacklist.contains(item.getUnlocalizedName()))
		GameRegistry.registerItem(item, name, ModInfo.ID);
	}
}
