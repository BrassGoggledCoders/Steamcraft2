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
import steamcraft.common.items.ItemCanister;
import steamcraft.common.items.ItemChisel;
import steamcraft.common.items.ItemMechanical;
import steamcraft.common.items.ItemMisc;
import steamcraft.common.items.ItemResource;
import steamcraft.common.items.ItemTeaSeed;
import steamcraft.common.items.ItemWatch;
import steamcraft.common.items.armor.ItemCustomArmor;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.MaterialHelper;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Surseance (Johnny Eatmon)
 * 
 */
public class ConfigItems
{
	// Item Tools(sort of)
	public static Item itemMechanical;
	public static Item itemResource, itemIngot;
	public static Item itemWatch;
	public static Item itemTeaSeed;
	public static Item itemChisel;

	// Armor
	public static Item itemBrassGoggles;
	public static Item itemAqualung;
	public static Item itemLegBraces;
	public static Item itemRollerSkates;

	public static Item itemSteamJetpack;

	public static Item itemHelmetEtherium;
	public static Item itemPlateEtherium;
	public static Item itemLegsEtherium;
	public static Item itemBootsEtherium;

	public static Item itemHelmetObsidian;
	public static Item itemPlateObsidian;
	public static Item itemLegsObsidian;
	public static Item itemBootsObsidian;

	// Canisters
	public static Item itemCanisterSteam;

	public static Item itemColdKettle;
	public static Item itemHotKettle;
	public static Item itemEmptyKettle;
	public static Item itemEmptyTeacup;
	public static Item itemFullTeacup;

	public static Item itemSteamCanister;
	public static Item itemCanisterGas;
	public static Item itemCanisterEmpty;

	public static Item itemMusketRound;
	public static Item itemBucketSteam;

	public static void init()
	{
		MaterialHelper.initializeMaterials();
		initializeItems();
	}

	public static void postInit()
	{
	}

	public static void initializeItems()
	{
		/*
		 * Items itemMechanical = new
		 * ItemMechanical(Config.itemMechanicalId).setUnlocalizedName
		 * ("itemMechanical"); GameRegistry.registerItem(itemMechanical,
		 * "ItemMechanical", LibInfo.ID); itemResource = new
		 * ItemResource(Config.
		 * itemResourceId).setUnlocalizedName("itemResource");
		 * GameRegistry.registerItem(itemResource, "ItemResource", LibInfo.ID);
		 * itemIngot = new
		 * ItemIngot(Config.itemIngotId).setUnlocalizedName("itemIngot");
		 * itemWatch = new
		 * ItemWatch(Config.itemWatchId).setUnlocalizedName("itemWatch");
		 * GameRegistry.registerItem(itemWatch, "ItemWatch", LibInfo.ID);
		 * itemTeaSeed = new
		 * ItemTeaSeed(Config.itemTeaSeedId).setUnlocalizedName("itemTeaSeed");
		 * GameRegistry.registerItem(itemTeaSeed, "ItemTeaSeed", LibInfo.ID);
		 * itemChisel = new
		 * ItemChisel(Config.itemChiselId).setUnlocalizedName("itemChisel");
		 * GameRegistry.registerItem(itemChisel, "ItemChisel", LibInfo.ID);
		 * 
		 * //Armor itemBrassGoggles = new ItemCustomArmor(Config.itemGogglesId,
		 * MaterialHelper.STEAM_ARMOR, 0, 0).setUnlocalizedName("itemGoggles");
		 * GameRegistry.registerItem(itemBrassGoggles, "ItemBrassGoggles",
		 * LibInfo.ID); itemAqualung = new
		 * ItemCustomArmor(Config.itemAqualungId, MaterialHelper.STEAM_ARMOR, 0,
		 * 1).setUnlocalizedName("itemAqualung");
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
		 * LibInfo.ID); itemSteamJetpack = new
		 * ItemSteamJetpack(Config.itemSteamJetpackId,
		 * MaterialHelper.STEAM_ARMOR, 0,
		 * 1).setUnlocalizedName("itemSteamJetpack");
		 * GameRegistry.registerItem(itemSteamJetpack, "ItemSteamJetpack",
		 * LibInfo.ID);
		 * 
		 * itemHelmetEtherium = new ItemNormalArmor(Config.itemHelmetEtheriumId,
		 * MaterialHelper.ETHERIUM_ARMOR, 0,
		 * 0).setUnlocalizedName("itemHelmetEtherium");
		 * GameRegistry.registerItem(itemRollerSkates, "ItemHelmetEtherium",
		 * LibInfo.ID); itemPlateEtherium = new
		 * ItemNormalArmor(Config.itemPlateEtheriumId,
		 * MaterialHelper.ETHERIUM_ARMOR, 0,
		 * 1).setUnlocalizedName("itemPlateEtherium");
		 * GameRegistry.registerItem(itemRollerSkates, "ItemPlateEtherium",
		 * LibInfo.ID); itemLegsEtherium = new
		 * ItemNormalArmor(Config.itemLegsEtheriumId,
		 * MaterialHelper.ETHERIUM_ARMOR, 0,
		 * 2).setUnlocalizedName("itemLegsEtherium");
		 * GameRegistry.registerItem(itemRollerSkates, "ItemLegsEtherium",
		 * LibInfo.ID); itemBootsEtherium = new
		 * ItemNormalArmor(Config.itemBootsEtheriumId,
		 * MaterialHelper.ETHERIUM_ARMOR, 0,
		 * 3).setUnlocalizedName("itemBootsEtherium");
		 * GameRegistry.registerItem(itemRollerSkates, "ItemBootsEtherium",
		 * LibInfo.ID);
		 * 
		 * itemHelmetObsidian = new ItemNormalArmor(Config.itemHelmetObsidianId,
		 * MaterialHelper.OBSIDIAN_ARMOR, 0,
		 * 0).setUnlocalizedName("itemHelmetObsidian");
		 * GameRegistry.registerItem(itemRollerSkates, "ItemHelmetObsidian",
		 * LibInfo.ID); itemPlateObsidian = new
		 * ItemNormalArmor(Config.itemPlateObsidianId,
		 * MaterialHelper.OBSIDIAN_ARMOR, 0,
		 * 1).setUnlocalizedName("itemPlateObsidian");
		 * GameRegistry.registerItem(itemRollerSkates, "ItemPlateObsidian",
		 * LibInfo.ID); itemLegsObsidian = new
		 * ItemNormalArmor(Config.itemLegsObsidianId,
		 * MaterialHelper.OBSIDIAN_ARMOR, 0,
		 * 2).setUnlocalizedName("itemLegsObsidian");
		 * GameRegistry.registerItem(itemRollerSkates, "ItemLegsObsidian",
		 * LibInfo.ID); itemBootsObsidian = new
		 * ItemNormalArmor(Config.itemBootsObsidianId,
		 * MaterialHelper.OBSIDIAN_ARMOR, 0,
		 * 3).setUnlocalizedName("itemBootsObsidian");
		 * GameRegistry.registerItem(itemRollerSkates, "ItemBootsObsidian",
		 * LibInfo.ID);
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

		// Canisters
		itemCanisterEmpty = new ItemMisc()
				.setUnlocalizedName("itemCanisterEmpty");
		GameRegistry.registerItem(itemCanisterEmpty, "ItemCanisterEmpty",
				LibInfo.ID);
		itemCanisterSteam = new ItemCanister(Config.itemCanisterSteamId,
				"steam").setUnlocalizedName("itemCanisterSteam");
		GameRegistry.registerItem(itemCanisterSteam, "ItemCanisterSteam",
				LibInfo.ID);
		itemCanisterGas = new ItemCanister(Config.itemCanisterGasId,
				"compressedgas").setUnlocalizedName("itemCanisterGas");
		GameRegistry.registerItem(itemCanisterGas, "ItemCanisterGas",
				LibInfo.ID);

		itemMechanical = new ItemMechanical()
				.setUnlocalizedName("ItemMechanical");
		GameRegistry.registerItem(itemMechanical, "ItemMechanical", LibInfo.ID);
		itemResource = new ItemResource().setUnlocalizedName("ItemResource");
		GameRegistry.registerItem(itemResource, "ItemResource", LibInfo.ID);
		itemWatch = new ItemWatch().setUnlocalizedName("ItemWatch");
		GameRegistry.registerItem(itemWatch, "ItemWatch", LibInfo.ID);

		itemTeaSeed = new ItemTeaSeed().setUnlocalizedName("ItemTeaSeed");
		GameRegistry.registerItem(itemTeaSeed, "ItemTeaSeed", LibInfo.ID);
		itemChisel = new ItemChisel().setUnlocalizedName("ItemChisel");
		GameRegistry.registerItem(itemChisel, "ItemChisel", LibInfo.ID);

		itemBrassGoggles = new ItemCustomArmor(ItemArmor.ArmorMaterial.CHAIN,
				0, 0).setUnlocalizedName("ItemCustomArmor");
		GameRegistry.registerItem(itemBrassGoggles, "ItemCustomArmor",
				LibInfo.ID);
	}
}
