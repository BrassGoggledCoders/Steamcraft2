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
import steamcraft.common.items.ItemIngot;
import steamcraft.common.items.ItemMechanical;
import steamcraft.common.items.ItemPipe;
import steamcraft.common.items.ItemRayGun;
import steamcraft.common.items.ItemResource;
import steamcraft.common.items.ItemShrinkray;
import steamcraft.common.items.ItemTeaSeed;
import steamcraft.common.items.ItemWatch;
import steamcraft.common.items.armor.ItemBrassArmor;
import steamcraft.common.items.armor.ItemNormalArmor;
import steamcraft.common.items.armor.ItemSteamJetpack;
import steamcraft.common.items.equipment.ItemCoreDrill;
import steamcraft.common.items.equipment.ItemDrill;
import steamcraft.common.items.equipment.ItemHammer;
import steamcraft.common.items.equipment.ItemModAxe;
import steamcraft.common.items.equipment.ItemModHoe;
import steamcraft.common.items.equipment.ItemModPickaxe;
import steamcraft.common.items.equipment.ItemModShovel;
import steamcraft.common.items.equipment.ItemModSword;
import steamcraft.common.items.equipment.ItemSteamDrill;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.MaterialHelper;
import cpw.mods.fml.common.registry.GameRegistry;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigItems.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class ConfigItems
{
	// Item Tools(sort of)
	/** The item mechanical. */
	public static Item itemMechanical;

	/** The item ingot. */
	public static Item itemResource, itemIngot;

	/** The item watch. */
	public static Item itemWatch;

	/** The item tea seed. */
	public static Item itemTeaSeed;

	/** The item chisel. */
	public static Item itemChisel;

	// Armor
	public static Item itemHelmetBrass, itemChestplateBrass, itemLegsBrass, itemBootsBrass;
	/**
	 * The item brass goggles. public static Item itemBrassGoggles;
	 *
	 * /** The item aqualung. public static Item itemAqualung;
	 *
	 * /** The item leg braces. public static Item itemLegBraces;
	 *
	 * /** The item roller skates public static Item itemRollerSkates;
	 *
	 * /** The item steam jetpack.
	 */
	public static Item itemSteamJetpack;

	/** The item helmet etherium. */
	public static Item itemHelmetEtherium;

	/** The item plate etherium. */
	public static Item itemChestplateEtherium;

	/** The item legs etherium. */
	public static Item itemLegsEtherium;

	/** The item boots etherium. */
	public static Item itemBootsEtherium;

	/** The item helmet obsidian. */
	public static Item itemObsidianHelmet;

	/** The item plate obsidian. */
	public static Item itemChestplateObsidian;

	/** The item legs obsidian. */
	public static Item itemLegsObsidian;

	/** The item boots obsidian. */
	public static Item itemBootsObsidian;

	// Canisters
	/** The item canister steam. */
	public static Item itemCanisterSteam;

	/** The item cold kettle. */
	public static Item itemColdKettle;

	/** The item hot kettle. */
	public static Item itemHotKettle;

	/** The item empty kettle. */
	public static Item itemEmptyKettle;

	/** The item empty teacup. */
	public static Item itemEmptyTeacup;

	/** The item full teacup. */
	public static Item itemFullTeacup;

	/** The item steam canister. */
	public static Item itemSteamCanister;

	/** The item canister gas. */
	public static Item itemCanisterGas;

	/** The item canister empty. */
	public static Item itemCanisterEmpty;

	/** The item musket round. */
	public static Item itemMusketRound;

	/** The item bucket steam. */
	public static Item itemBucketSteam;

	public static Item itemPipe;

	public static Item itemPoppySeed;

	public static Item itemRayGun, itemShrinkray;

	public static Item itemBrassGoggles;

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
	public static Item itemPickaxeEtherium;
	public static Item itemSwordEtherium;
	public static Item itemShovelEtherium;
	public static Item itemAxeEtherium;
	public static Item itemHoeEtherium;

	// Obsidian
	public static Item itemPickaxeObsidian;
	public static Item itemSwordObsidian;
	public static Item itemShovelObsidian;
	public static Item itemAxeObsidian;
	public static Item itemHoeObsidian;

	// Other
	public static Item chisel;
	public static Item spanner;

	public static Item itemHammer;

	/**
	 * Inits the.
	 */
	public static void init()
	{
		MaterialHelper.initializeMaterials();
		initializeItems();
		registerItems();
	}

	/**
	 * Post init.
	 */
	public static void postInit()
	{
	}

	/**
	 * Initialize items.
	 */
	public static void initializeItems()
	{
		/*
		 * itemAqualung = new ItemCustomArmor(Config.itemAqualungId,
		 * MaterialHelper.STEAM_ARMOR, 0, 1).setUnlocalizedName("itemAqualung");
		 * GameRegistry.registerItem(itemAqualung, "ItemAqualung", LibInfo.ID);
		 * itemLegBraces = new ItemCustomArmor(Config.itemLegBracesId,
		 * MaterialHelper.STEAM_ARMOR, 0,
		 * 2).setUnlocalizedName("itemLegBraces");
		 * GameRegistry.registerItem(itemLegBraces, "ItemLegBraces",
		 * LibInfo.ID); itemRollerSkates = new
		 * ItemCustomArmor(Config.itemRollerSkatesId,
		 * MaterialHelper.STEAM_ARMOR, 0,
		 * 3).setUnlocalizedName("itemRollerSkates");
		 * GameRegistry.registerItem(itemRollerSkates, "ItemRollerSkates",
		 * LibInfo.ID);
		 *
		 *
		 *
		 * //More Items itemTeacupEmpty = new
		 * ItemTeacup(Config.itemTeacupEmptyId, 0, 0.0F,
		 * false).setUnlocalizedName("itemTeacupEmpty");
		 * GameRegistry.registerItem(itemTeacupEmpty, "ItemTeacupEmpty",
		 * LibInfo.ID); itemTeacupFull = new ItemTeacup(Config.itemTeacupFullId,
		 * 4, 0.9F, false).setUnlocalizedName("itemTeacupFull");
		 * GameRegistry.registerItem(itemTeacupFull, "ItemTeacupFull",
		 * LibInfo.ID); itemKettleEmpty = new
		 * ItemKettle(Config.itemKettleEmptyId,
		 * 300).setUnlocalizedName("itemKettleEmpty");
		 * GameRegistry.registerItem(itemTeacupFull, "ItemKettleEmpty",
		 * LibInfo.ID); itemKettleHot = new ItemKettle(Config.itemKettleHotId,
		 * 300).setUnlocalizedName("itemKettleHot");
		 * GameRegistry.registerItem(itemTeacupFull, "ItemKettleHot",
		 * LibInfo.ID); itemKettleCold = new ItemKettle(Config.itemKettleColdId,
		 * 300).setUnlocalizedName("itemKettleCold");
		 * GameRegistry.registerItem(itemTeacupFull, "ItemKettleCold",
		 * LibInfo.ID); itemGunParts = new
		 * ItemGunParts(Config.itemGunPartsId).setUnlocalizedName
		 * ("itemGunParts"); GameRegistry.registerItem(itemGunParts,
		 * "ItemGunParts", LibInfo.ID);
		 */

		// Armor
		itemHelmetBrass = new ItemBrassArmor(MaterialHelper.ARMOR_STEAM, 0, 0).setUnlocalizedName("itemHelmetBrass");
		itemChestplateBrass = new ItemBrassArmor(MaterialHelper.ARMOR_STEAM, 0, 0).setUnlocalizedName("itemChestplateBrass");
		itemLegsBrass = new ItemBrassArmor(MaterialHelper.ARMOR_STEAM, 0, 0).setUnlocalizedName("itemLegsBrass");
		itemBootsBrass = new ItemBrassArmor(MaterialHelper.ARMOR_STEAM, 0, 0).setUnlocalizedName("itemBootsBrass");

		itemSteamJetpack = new ItemSteamJetpack(MaterialHelper.ARMOR_STEAM, 0, 1).setUnlocalizedName("itemSteamJetpack");

		// Canisters
		itemCanisterEmpty = new Item().setUnlocalizedName("itemCanisterEmpty");
		itemCanisterSteam = new ItemCanister("steam", 150000, 20).setUnlocalizedName("itemCanisterSteam");
		itemCanisterGas = new ItemCanister("compressedgas", 150000, 20).setUnlocalizedName("itemCanisterGas");

		itemIngot = new ItemIngot();
		itemMechanical = new ItemMechanical().setUnlocalizedName("ItemMechanical");
		itemResource = new ItemResource().setUnlocalizedName("itemResource");
		itemWatch = new ItemWatch().setUnlocalizedName("itemWatch");
		itemTeaSeed = new ItemTeaSeed().setUnlocalizedName("itemTeaSeed");
		itemChisel = new ItemChisel().setUnlocalizedName("itemChisel");
		itemBrassGoggles = new ItemBrassGoggles(ItemArmor.ArmorMaterial.CHAIN, 0, 0);
		itemPipe = new ItemPipe();
		itemPoppySeed = new Item().setUnlocalizedName("itemPoppySeed").setCreativeTab(Steamcraft.tabSC2);
		itemRayGun = new ItemRayGun(LibInfo.PREFIX + "raygun");
		itemShrinkray = new ItemShrinkray(LibInfo.PREFIX + "shrinkray");
		itemHammer = new ItemHammer().setUnlocalizedName("itemHammer");

		drillCore = new ItemCoreDrill().setUnlocalizedName("itemDrillCore");
		drillBase = new BaseItem().setUnlocalizedName("itemDrillBase");
		drillWood = new ItemDrill(MaterialHelper.DRILL_WOOD).setUnlocalizedName("itemDrillWood").setCreativeTab(Steamcraft.tabSC2);
		drillStone = new ItemDrill(MaterialHelper.DRILL_STONE).setUnlocalizedName("itemDrillStone");
		drillIron = new ItemDrill(MaterialHelper.DRILL_IRON).setUnlocalizedName("itemDrillIron");
		drillDiamond = new ItemDrill(MaterialHelper.DRILL_EMERALD).setUnlocalizedName("itemDrillDiamond");
		drillGold = new ItemDrill(MaterialHelper.DRILL_GOLD).setUnlocalizedName("itemDrillGold");
		drillSteam = new ItemSteamDrill(MaterialHelper.DRILL_STEAM).setUnlocalizedName("itemDrillSteam");
		drillEtherium = new ItemDrill(MaterialHelper.DRILL_ETHERIUM).setUnlocalizedName("itemDrillEtherium");
		drillObsidian = new ItemDrill(MaterialHelper.DRILL_OBSIDIAN).setUnlocalizedName("itemDrillObsidian");

		pickaxeSteam = new ItemModPickaxe(MaterialHelper.TOOL_STEAM).setUnlocalizedName("itemPickaxeSteam");
		swordSteam = new ItemModSword(MaterialHelper.TOOL_STEAM).setUnlocalizedName("itemSwordSteam");
		shovelSteam = new ItemModShovel(MaterialHelper.TOOL_STEAM).setUnlocalizedName("itemShovelSteam");
		axeSteam = new ItemModAxe(MaterialHelper.TOOL_STEAM).setUnlocalizedName("itemAxeSteam");
		hoeSteam = new ItemModHoe(MaterialHelper.TOOL_STEAM).setUnlocalizedName("itemHoeSteam");

		itemPickaxeEtherium = new ItemModPickaxe(MaterialHelper.TOOL_ETHERIUM).setUnlocalizedName("itemPickaxeEtherium");
		itemSwordEtherium = new ItemModSword(MaterialHelper.TOOL_ETHERIUM).setUnlocalizedName("itemSwordEtherium");
		itemShovelEtherium = new ItemModShovel(MaterialHelper.TOOL_ETHERIUM).setUnlocalizedName("itemShovelEtherium");
		itemAxeEtherium = new ItemModAxe(MaterialHelper.TOOL_ETHERIUM).setUnlocalizedName("itemAxeEtherium");
		itemHoeEtherium = new ItemModHoe(MaterialHelper.TOOL_ETHERIUM).setUnlocalizedName("itemHoeEtherium");

		itemPickaxeObsidian = new ItemModPickaxe(MaterialHelper.TOOL_OBSIDIAN).setUnlocalizedName("itemPickaxeObsidian");
		itemSwordObsidian = new ItemModSword(MaterialHelper.TOOL_OBSIDIAN).setUnlocalizedName("itemSwordObsidian");
		itemShovelObsidian = new ItemModShovel(MaterialHelper.TOOL_OBSIDIAN).setUnlocalizedName("itemShovelObsidian");
		itemAxeObsidian = new ItemModAxe(MaterialHelper.TOOL_OBSIDIAN).setUnlocalizedName("itemAxeObsidian");
		itemHoeObsidian = new ItemModHoe(MaterialHelper.TOOL_OBSIDIAN).setUnlocalizedName("itemHoeObsidian");

		spanner = new /* Really? */ItemChisel().setUnlocalizedName("spanner");

		itemHelmetEtherium = new ItemNormalArmor(MaterialHelper.ARMOR_ETHERIUM, 0, 0).setUnlocalizedName("itemHelmetEtherium");
		itemChestplateEtherium = new ItemNormalArmor(MaterialHelper.ARMOR_ETHERIUM, 0, 1).setUnlocalizedName("itemChestplateEtherium");
		itemLegsEtherium = new ItemNormalArmor(MaterialHelper.ARMOR_ETHERIUM, 0, 2).setUnlocalizedName("itemLegsEtherium");
		itemBootsEtherium = new ItemNormalArmor(MaterialHelper.ARMOR_ETHERIUM, 0, 3).setUnlocalizedName("itemBootsEtherium");

		itemObsidianHelmet = new ItemNormalArmor(MaterialHelper.ARMOR_OBSIDIAN, 0, 0).setUnlocalizedName("itemHelmetObsidian");
		itemChestplateObsidian = new ItemNormalArmor(MaterialHelper.ARMOR_OBSIDIAN, 0, 1).setUnlocalizedName("itemChestplateObsidian");
		itemLegsObsidian = new ItemNormalArmor(MaterialHelper.ARMOR_OBSIDIAN, 0, 2).setUnlocalizedName("itemLegsObsidian");
		itemBootsObsidian = new ItemNormalArmor(MaterialHelper.ARMOR_OBSIDIAN, 0, 3).setUnlocalizedName("itemBootsObsidian");
	}

	private static void registerItems()
	{
		GameRegistry.registerItem(itemCanisterEmpty, "ItemCanisterEmpty", LibInfo.ID);
		GameRegistry.registerItem(itemCanisterSteam, "ItemCanisterSteam", LibInfo.ID);
		GameRegistry.registerItem(itemCanisterGas, "ItemCanisterGas", LibInfo.ID);

		GameRegistry.registerItem(itemIngot, "ItemIngot", LibInfo.ID);
		GameRegistry.registerItem(itemMechanical, "ItemMechanical", LibInfo.ID);
		GameRegistry.registerItem(itemResource, "ItemResource", LibInfo.ID);
		GameRegistry.registerItem(itemWatch, "ItemWatch", LibInfo.ID);
		GameRegistry.registerItem(itemTeaSeed, "ItemTeaSeed", LibInfo.ID);
		GameRegistry.registerItem(itemChisel, "ItemChisel", LibInfo.ID);
		GameRegistry.registerItem(itemPipe, "ItemPipe", LibInfo.ID);
		GameRegistry.registerItem(itemPoppySeed, "ItemPoppySeed", LibInfo.ID);
		GameRegistry.registerItem(itemRayGun, "ItemRayGun", LibInfo.ID);
		GameRegistry.registerItem(itemShrinkray, "ItemShrinkray", LibInfo.ID);
		GameRegistry.registerItem(itemBrassGoggles, "ItemBrassGoggles", LibInfo.ID);
		GameRegistry.registerItem(itemHammer, "ItemHammer", LibInfo.ID);

		// Drills
		GameRegistry.registerItem(drillCore, "ItemDrillCore", LibInfo.ID);
		GameRegistry.registerItem(drillBase, "ItemDrillBase", LibInfo.ID);
		GameRegistry.registerItem(drillWood, "ItemDrillWood", LibInfo.ID);
		GameRegistry.registerItem(drillStone, "ItemDrillStone", LibInfo.ID);
		GameRegistry.registerItem(drillIron, "ItemDrillIron", LibInfo.ID);
		GameRegistry.registerItem(drillDiamond, "ItemDrillDiamond", LibInfo.ID);
		GameRegistry.registerItem(drillGold, "ItemDrillGold", LibInfo.ID);
		GameRegistry.registerItem(drillSteam, "ItemDrillSteam", LibInfo.ID);
		GameRegistry.registerItem(drillEtherium, "ItemDrillEtherium", LibInfo.ID);
		GameRegistry.registerItem(drillObsidian, "ItemDrillObsidian", LibInfo.ID);

		GameRegistry.registerItem(itemSteamJetpack, "ItemSteamJetpack", LibInfo.ID);

		//Tools
		GameRegistry.registerItem(pickaxeSteam, "ItemPickAxeSteam", LibInfo.ID);
		GameRegistry.registerItem(swordSteam, "ItemSwordSteam", LibInfo.ID);
		GameRegistry.registerItem(shovelSteam, "ItemShovelSteam", LibInfo.ID);
		GameRegistry.registerItem(axeSteam, "ItemAxeSteam", LibInfo.ID);
		GameRegistry.registerItem(hoeSteam, "ItemHoeSteam", LibInfo.ID);

		// RegistryHelper.registerToolSet(itemAxeEtherium, itemHoeEtherium,
		// itemPickaxeEtherium, itemShovelEtherium, itemSwordEtherium,
		// "Etherium", LibInfo.ID);
		// RegistryHelper.registerToolSet(itemAxeObsidian, itemHoeObsidian,
		// itemPickaxeObsidian, itemShovelObsidian, itemSwordObsidian,
		// "Obsidian", LibInfo.ID);
		// RegistryHelper.registerArmorSet(itemHelmetBrass, itemChestplateBrass,
		// itemLegsBrass, itemBootsBrass, "Brass", LibInfo.ID);
		// RegistryHelper.registerArmorSet(itemObsidianHelmet,
		// itemChestplateObsidian, itemLegsObsidian, itemBootsObsidian,
		// "Obsidian", LibInfo.ID);
		// RegistryHelper.registerArmorSet(itemHelmetEtherium,
		// itemChestplateEtherium, itemLegsEtherium, itemBootsEtherium,
		// "Etherium", LibInfo.ID);
	}
}
