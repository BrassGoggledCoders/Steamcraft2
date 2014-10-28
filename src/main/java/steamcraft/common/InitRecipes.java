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
package steamcraft.common;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import steamcraft.common.config.ConfigBalance;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.utils.recipe.RecipeUtils;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Surseance
 * 
 */
public class InitRecipes
{
	public static void init()
	{
		initializeCraftingRecipes();
		initializeSmeltingRecipes();
	}

	public static void initializeCraftingRecipes()
	{
		initMetalsRecipes();
		initToolsRecipes();
		initGunRecipes();
		initArmorRecipes();
		initBlockRecipes();
		initModuleRecipes();
		initOtherRecipes();
	}

	private static void initGunRecipes()
	{
		// Gun Parts
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 0), new Object[] { "WWW", " WW", 'W', "plankWood" }));
		GameRegistry
				.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 1), new Object[] { "III", "   ", "III", 'I', "ingotIron" }));
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.itemGunPart, 1, 2), new Object[] { new ItemStack(InitItems.itemGunPart, 1, 1),
				InitItems.itemChisel });
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 3), new Object[] { "  I", " II", 'I', "ingotIron" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 4), new Object[] { "III", " II", 'I', "ingotBrass" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 5), new Object[] { " II", " II", "  I", 'I',
				"ingotCastIron" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 6), new Object[] { "IGI", "GSG", "IGI", 'I', "plateBrass",
				'G', Blocks.glass_pane, 'S', "gemEmerald" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 7), new Object[] { "IGI", "GSG", "IGI", 'I', "plateBrass",
				'G', "paneGlass", 'S', "gemDiamond" }));
		GameRegistry.addRecipe(new ItemStack(InitItems.itemGunPart, 1, 8), new Object[] { "SSS", "SJS", "SSS", 'J',
				InitItems.itemElectricJarSmall, 'S', new ItemStack(InitItems.itemResource, 1, 2) });
		if(!ConfigBalance.cheaperCoreRecipe)
			GameRegistry.addRecipe(new ItemStack(InitItems.itemGunPart, 1, 9), new Object[] { "SPS", "PNP", "SPS", 'S',
					new ItemStack(InitItems.itemResource, 1, 2), 'P', new ItemStack(InitItems.itemGunPart, 1, 8), 'N', Items.nether_star });
		else
			GameRegistry.addRecipe(new ItemStack(InitItems.itemGunPart, 1, 9), new Object[] { "SPS", "PNP", "SPS", 'S',
					new ItemStack(InitItems.itemResource, 1, 2), 'P', new ItemStack(InitItems.itemGunPart, 1, 8), 'N', Items.nether_star });
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 10),
				new Object[] { " II", "   ", " II", 'I', "ingotIron" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 11), new Object[] { " WW", "  W", 'W', "plankWood" }));
		// Actual Guns
		GameRegistry.addRecipe(new ItemStack(InitItems.flintlockMusket), new Object[] { " FL", " B ", " S ", 'S',
				new ItemStack(InitItems.itemGunPart, 1, 0), 'B', new ItemStack(InitItems.itemGunPart, 1, 1), 'L',
				new ItemStack(InitItems.itemGunPart, 1, 3), 'F', Items.flint });
		GameRegistry.addRecipe(new ItemStack(InitItems.matchlockMusket), new Object[] { " FL", " B ", " S ", 'S',
				new ItemStack(InitItems.itemGunPart, 1, 0), 'B', new ItemStack(InitItems.itemGunPart, 1, 1), 'L',
				new ItemStack(InitItems.itemGunPart, 1, 3), 'F', InitItems.itemMatch });
		GameRegistry.addRecipe(new ItemStack(InitItems.percussionCapMusket), new Object[] { " FL", " B ", " S ", 'S',
				new ItemStack(InitItems.itemGunPart, 1, 0), 'B', new ItemStack(InitItems.itemGunPart, 1, 1), 'L',
				new ItemStack(InitItems.itemGunPart, 1, 3), 'F', InitItems.itemPercussionCap });
		GameRegistry.addRecipe(new ItemStack(InitItems.flintlockRifle), new Object[] { " FL", " B ", " S ", 'S',
				new ItemStack(InitItems.itemGunPart, 1, 0), 'B', new ItemStack(InitItems.itemGunPart, 1, 2), 'L',
				new ItemStack(InitItems.itemGunPart, 1, 3), 'F', Items.flint });
		GameRegistry.addRecipe(new ItemStack(InitItems.matchlockRifle), new Object[] { " FL", " B ", " S ", 'S',
				new ItemStack(InitItems.itemGunPart, 1, 0), 'B', new ItemStack(InitItems.itemGunPart, 1, 2), 'L',
				new ItemStack(InitItems.itemGunPart, 1, 3), 'F', InitItems.itemMatch });
		GameRegistry.addRecipe(new ItemStack(InitItems.percussionCapRifle), new Object[] { " FL", " B ", " S ", 'S',
				new ItemStack(InitItems.itemGunPart, 1, 0), 'B', new ItemStack(InitItems.itemGunPart, 1, 2), 'L',
				new ItemStack(InitItems.itemGunPart, 1, 3), 'F', InitItems.itemPercussionCap });
		GameRegistry.addRecipe(new ItemStack(InitItems.flintlockPistol), new Object[] { " FL", " B ", " S ", 'S',
				new ItemStack(InitItems.itemGunPart, 1, 11), 'B', new ItemStack(InitItems.itemGunPart, 1, 10), 'L',
				new ItemStack(InitItems.itemGunPart, 1, 3), 'F', Items.flint });
		GameRegistry.addRecipe(new ItemStack(InitItems.matchlockPistol), new Object[] { " FL", " B ", " S ", 'S',
				new ItemStack(InitItems.itemGunPart, 1, 11), 'B', new ItemStack(InitItems.itemGunPart, 1, 10), 'L',
				new ItemStack(InitItems.itemGunPart, 1, 3), 'F', InitItems.itemMatch });
		GameRegistry.addRecipe(new ItemStack(InitItems.percussionCapPistol), new Object[] { " FL", " B ", " S ", 'S',
				new ItemStack(InitItems.itemGunPart, 1, 11), 'B', new ItemStack(InitItems.itemGunPart, 1, 10), 'L',
				new ItemStack(InitItems.itemGunPart, 1, 3), 'F', InitItems.itemPercussionCap });
		// Ammo
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemMusketBall, 4), new Object[] { "II", "II", 'I', "ingotIron" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemRifleBullet, 4), new Object[] { " S ", " C ", " G ", 'S',
				"ingotSteel", 'C', "plateBrass", 'G', Items.gunpowder }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemPercussionCap), new Object[] { "GG", "II", 'I', "ingotBrass", 'G',
				Items.gunpowder }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemRocket, 1, 0), new Object[] { " I ", "PTP", "PTP", 'I', "ingotIron", 'P',
				"plateBrass", 'T', Blocks.tnt }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemRocket, 1, 1), new Object[] { " F ", "PTP", "PTP", 'F', Items.flint_and_steel,
				'P',
				"plateBrass", 'T', Blocks.tnt }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemRocket, 1, 2), new Object[] { " U ", "PTP", "PTP", 'U', "ingotUranium", 'P',
				"plateBrass", 'T', Blocks.tnt }));
		// Other Guns
		GameRegistry.addRecipe(new ItemStack(InitItems.itemRayGun, 1, 20), new Object[] { "FSP", 'F', new ItemStack(InitItems.itemGunPart, 1, 6), 'S',
				new ItemStack(InitItems.itemGunPart, 1, 4), 'P', new ItemStack(InitItems.itemGunPart, 1, 8) });
		GameRegistry.addRecipe(new ItemStack(InitItems.itemShrinkray, 1, 20), new Object[] { "FSP", 'F', new ItemStack(InitItems.itemGunPart, 1, 7), 'S',
				new ItemStack(InitItems.itemGunPart, 1, 5), 'P', new ItemStack(InitItems.itemGunPart, 1, 9) });
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemClockworkRocketLauncher), new Object[] { "CCC", "  S", "CCC", 'C',
				"plateCastIron", 'S',
				new ItemStack(InitItems.itemGunPart, 1, 5) }));
	}

	private static void initMetalsRecipes()
	{
		for(int meta = 0; meta < 8; meta++)
		{
			RecipeUtils.addMetalRecipes(InitBlocks.blockMetal, InitItems.itemIngot, InitItems.itemNugget, meta);

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemSheet, 1, meta), new Object[] { "ingot" + LibInfo.metals[meta],
					new ItemStack(InitItems.itemHammer, 1, OreDictionary.WILDCARD_VALUE) }));
		}
		for(int meta = 0; meta < 4; meta++)
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemPowder, ConfigBalance.numberOfDustsFromOreHammering, meta),
					new Object[] { "ore" + LibInfo.metals[meta], new ItemStack(InitItems.itemHammer, 1, OreDictionary.WILDCARD_VALUE) }));
	}

	private static void initToolsRecipes()
	{
		// Drills
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.drillBase), new Object[] { "   ", "IGI", "III", 'I', "ingotSteel", 'G',
				new ItemStack(InitItems.itemSteelParts, 1, 0) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.drillCore), new Object[] { " D ", "DDD", " B ", 'B', InitItems.drillBase,
				'D', "ingotAluminum" }));

		String[] drillMaterials = new String[] { "gemDiamond", "crystalEtherium", "ingotGold", "ingotIron", "shardObsidian", "ingotBrass", "stone",
				"plankWood" };
		Item[] drills = new Item[] { InitItems.drillDiamond, InitItems.drillEtherium, InitItems.drillGold, InitItems.drillIron,
				InitItems.drillObsidian, InitItems.drillSteam, InitItems.drillStone, InitItems.drillWood };

		for(int i = 0; i < drillMaterials.length; i++)
			GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(drills[i]), new Object[] { " M ", "MMM", " C ", 'C', InitItems.drillCore, 'M',
					drillMaterials[i] }));

		// Steam
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.swordSteam), new Object[] { "II ", "IP ", "CSC", 'I', "plateSteel", 'P',
				"ingotBrass", 'S', Items.stick, 'C', "partClockworkMechanism" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.pickaxeSteam), new Object[] { "IPI", "CSC", " I ", 'R', "partCastIronRod",
				'P', "ingotBrass", 'I', "ingotSteel", 'S', Items.stick, 'C', "partClockworkMechanism" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.shovelSteam), new Object[] { "CPC", " S ", " R ", 'R', "partCastIronRod",
				'P', "ingotBrass", 'I', "ingotSteel", 'S', Items.stick, 'C', "partClockworkMechanism" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.axeSteam), new Object[] { "PIC", "PRC", " R ", 'R', "partCastIronRod",
				'P', "ingotBrass", 'I', "ingotSteel", 'S', "stickWood", 'C', "partClockworkMechanism" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.hoeSteam), new Object[] { "PIC", " S ", " R ", 'R', "partCastIronRod",
				'P', "ingotBrass", 'I', "ingotSteel", 'S', Items.stick, 'C', "partClockworkMechanism" }));

		// Etherium
		RecipeUtils.addToolSet(new ItemStack(InitItems.itemResource, 1, 0), new ItemStack[] { new ItemStack(InitItems.pickaxeEtherium),
				new ItemStack(InitItems.shovelEtherium), new ItemStack(InitItems.axeEtherium), new ItemStack(InitItems.hoeEtherium),
				new ItemStack(InitItems.swordEtherium) });
		// Obsidian
		RecipeUtils.addToolSet(new ItemStack(InitItems.itemResource, 1, 2), new ItemStack[] { new ItemStack(InitItems.pickaxeObsidian),
				new ItemStack(InitItems.shovelObsidian), new ItemStack(InitItems.axeObsidian), new ItemStack(InitItems.hoeObsidian),
				new ItemStack(InitItems.swordObsidian) });
		GameRegistry.addShapedRecipe(new ItemStack(InitItems.itemSpanner), new Object[] { "I I", " I ", " I ", 'I',
				new ItemStack(InitItems.itemIngot, 1, 5) });
	}

	private static void initArmorRecipes()
	{
		// Etherium
		RecipeUtils.addArmorSet(new ItemStack(InitItems.itemResource, 1, 0), new ItemStack[] { new ItemStack(InitItems.helmetEtherium),
				new ItemStack(InitItems.chestplateEtherium), new ItemStack(InitItems.legsEtherium), new ItemStack(InitItems.bootsEtherium) });

		// Obsidian
		RecipeUtils.addArmorSet(new ItemStack(InitItems.itemResource, 1, 2), new ItemStack[] { new ItemStack(InitItems.helmetObsidian),
				new ItemStack(InitItems.chestplateObsidian), new ItemStack(InitItems.legsObsidian), new ItemStack(InitItems.bootsObsidian) });

		// Brass
		RecipeUtils.addArmorSet(new ItemStack(InitItems.itemSheet, 1, 4), new ItemStack[] { new ItemStack(InitItems.helmetBrass),
				new ItemStack(InitItems.chestplateBrass), new ItemStack(InitItems.legsBrass), new ItemStack(InitItems.bootsBrass) });

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemClockworkWings), new Object[] { "BCB", "WCW", "W W", 'B',
				new ItemStack(InitItems.itemIngot, 1, 4), 'C', "partClockworkMechanism", 'W', Blocks.wool }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemSteamJetpack), new Object[] { "CSC", "BSB", "B B", 'B',
				new ItemStack(InitItems.itemIngot, 1, 4), 'S', new ItemStack(InitItems.itemIngot, 1, 6), 'C',
				new ItemStack(InitItems.itemCanisterSteam, 1, OreDictionary.WILDCARD_VALUE) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemSteamWingpack), new Object[] { "LWL", "RBR", "SPS", 'P',
				InitItems.itemSteamJetpack, 'W', InitItems.itemClockworkWings, 'R', "partCastIronRod", 'B', new ItemStack(InitItems.itemGunPart, 1, 8), 'L',
				Items.leather,
				'S', Items.string }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.brassGoggles), new Object[] { "BBB", "GBG", "BBB", 'B', "ingotBrass", 'G',
				"paneGlass" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemMonocle), new Object[] { " B ", "BGB", " BS", 'B', "ingotBrass", 'G',
				"paneGlass", 'S', Items.string }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemDivingHelmet), new Object[] { "PPP", "PGP", "PPP", 'P', "plateBrass", 'G',
				"paneGlass" }));
	}

	private static void initBlockRecipes()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockSteamBoiler), new Object[] { "PPP", "TCT", "PFP", 'F',
				Blocks.furnace, 'P', "plateBrass", 'T', InitBlocks.blockCopperTank, 'C', InitBlocks.blockCopperPipe }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockIntake), new Object[] { "PGP", "GBG", "PGP", 'G', "partGrating",
				'P', "plateBrass", 'B', Items.bucket }));
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.blockBloomery), new Object[] { "N N", "NFN", "NNN", 'F', Blocks.furnace, 'N',
				Blocks.nether_brick });
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockCopperPipe),
				new Object[] { "PPP", "   ", "PPP", 'P', "plateCopper" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockCopperTank),
				new Object[] { "PPP", "P P", "PPP", 'P', "plateCopper" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockBattery), new Object[] { "CCC", "WCW", "CCC", 'C', "ingotCastIron",
				'W', InitBlocks.blockCopperWire }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockTeslaCoil), new Object[] { "WRW", "WRW", "III", 'I',
				"ingotCastIron", 'W', "partWireCoil", 'R', "partCastIronRod" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockTurbine), new Object[] { "S S", "FGF", "SFS", 'S', "ingotSteel",
				'F', "partFan", 'G', "partGenerator" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockLightningRod), new Object[] { " R ", "CRC", "SSS", 'S',
				"ingotSteel", 'R', "partCastIronRod", 'C', "plateCopper" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockCharger), new Object[] { "PPP", "IWI", "PPP", 'I',
				"ingotSteel", 'S', "plateSteel", 'W', InitBlocks.blockCopperWire }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockArmorEditor), new Object[] { "PPP", "WWW", "PCP", 'W',
				Blocks.crafting_table, 'P', "plateBrass", 'C', Blocks.chest }));

		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.blockUranium), new Object[] { "UUU", "UUU", "UUU", 'U',
				new ItemStack(InitItems.itemResource, 1, 4) });
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.blockEtherium), new Object[] { "UUU", "UUU", "UUU", 'U',
				new ItemStack(InitItems.itemResource, 1, 0) });
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockCastIronFence),
				new Object[] { "RRR", "RRR", 'R', "partCastIronRod" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockCastIronGate), new Object[] { "RIR", "RIR", 'R', "partCastIronRod",
				'I', "ingotCastIron" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockCastIronRailing),
				new Object[] { "III", "III", 'I', "ingotCastIron" }));
	}

	private static void initModuleRecipes()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemAqualung), new Object[] { "LLI", "L L", "LLL", 'L',
				Items.leather, 'I', "ingotIron" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemClimbingSpikes), new Object[] { " L ", "I I", 'L',
				Items.leather_boots, 'I', "ingotIron" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemTank), new Object[] { "GGG", "GWG", "GGG", 'G',
				Blocks.glass_pane, 'W', Items.water_bucket }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemFlippers), new Object[] { " B ", "L L", 'L',
				Items.leather, 'B', Items.leather_boots }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemLegBraces), new Object[] { "PPP", "P P", "P P", 'P',
				"plateCopper" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemLifeVest), new Object[] { "W W", "WWW", "WWW", 'W',
				Blocks.wool }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemParachute), new Object[] { "WWW", "W W", "S S", 'W',
				Blocks.wool, 'S', Items.string }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemPistonBoots), new Object[] { "   ", "CBC", "P P", 'P',
				Blocks.piston, 'B', Items.leather_boots, 'C', "partClockworkMechanism" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemPistonPlating), new Object[] { "RPR", "PCP", "RPR", 'C',
				Items.iron_chestplate, 'P', Blocks.piston, 'R', Items.redstone }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemRollerSkates), new Object[] { "   ", " L ", "WRW", 'W',
				Blocks.log, 'L', Items.leather_boots, 'R', "partCastIronRod" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemSpringHeels), new Object[] { "   ", " L ", "S S", 'S',
				new ItemStack(InitItems.itemSteelParts, 1, 2), 'L', Items.leather_boots }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemSteelPlating), new Object[] { "SSS", "SSS", "SSS", 'S',
				"plateSteel" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemWatchDisplay), new Object[] { "GGG", "RCR", "III", 'I',
				"ingotIron", 'G', "paneGlass", 'R', Items.redstone, 'C', Items.clock }));
		// GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemLastResort), new Object[] { "TTT", "TCT", "TTT", 'T',
		// Blocks.tnt, 'C', InitBlocks.blockCopperWire }));

	}

	private static void initOtherRecipes()
	{
		GameRegistry.addShapedRecipe(new ItemStack(InitItems.itemResource, 1, 2), new Object[] { "XXX", "XXX", "XXX", 'X', new ItemStack(Blocks.obsidian) });

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemSteelParts, 1, 0), new Object[] { " P ", "PPP", " P ", 'P',
				"plateSteel" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemSteelParts, 1, 1), new Object[] { " P ", "P P", " P ", 'P',
				"plateSteel" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemCopperParts, 1, 0), new Object[] { " P ", "PPP", " P ", 'P',
				new ItemStack(InitItems.itemSheet, 1, 1) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemCopperParts, 1, 1), new Object[] { " P ", "P P", " P ", 'P',
				new ItemStack(InitItems.itemSheet, 1, 1) }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemIronParts, 1, 0),
				new Object[] { " P ", "PPP", " P ", 'P', "ingotIron" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemIronParts, 1, 1),
				new Object[] { " P ", "P P", " P ", 'P', "ingotIron" }));

		GameRegistry.addShapedRecipe(new ItemStack(InitItems.itemMachinePart, 1, 0), "  X", " X ", "X  ", 'X', new ItemStack(InitItems.itemIngot, 1,
				7));
		GameRegistry.addShapedRecipe(new ItemStack(InitItems.itemMachinePart, 1, 1), "CPC", "S G", "CPC", 'P', new ItemStack(InitItems.itemSheet, 1,
				4), 'G', new ItemStack(InitItems.itemIronParts, 1, 0), 'S', new ItemStack(InitItems.itemCopperParts, 1, 1), 'C', new ItemStack(
				InitItems.itemMachinePart, 1, 0));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemMachinePart, 1, 2), new Object[] { "P P", " P ", "P P", 'P',
				"ingotSteel" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemMachinePart, 1, 3), new Object[] {
				new ItemStack(InitItems.itemElectricJarSmall), "partWireCoil", Items.iron_ingot }));
		GameRegistry
				.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemMachinePart, 2, 3), new Object[] { "partMagnet", Items.iron_ingot }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemMachinePart, 1, 4), new Object[] { "PRP", "MWM", "PRP", 'P',
				"plateSteel", 'W', "partWireCoil", 'M', "partMagnet", 'R', "partCastIronRod" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemMachinePart, 1, 5), new Object[] { " P ", "PPP", " P ", 'P',
				"plateSteel" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemMachinePart, 1, 6), new Object[] { "partCastIronRod",
				new ItemStack(InitBlocks.blockCopperWire), new ItemStack(InitBlocks.blockCopperWire), new ItemStack(InitBlocks.blockCopperWire) }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockCopperWire), new Object[] { "CD", 'C', "ingotCopper", 'D',
				InitItems.itemDrawplate }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemDrawplate), new Object[] { "S S", " S ", "S S", 'S', "plateCastIron" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemCanisterSteam, 1, InitItems.itemCanisterSteam.getMaxDamage()),
				new Object[] { "PPP", "GGG", "PPP", 'G', "paneGlass", 'P', "plateBrass" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemHammer), new Object[] { "III", " H ", " C ", 'I', "ingotCastIron",
				'H', Items.stick, 'C', "partCastIronRod" }));
		// Brass Powder
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemPowder, 3, 4), new Object[] { "dustCopper", "dustCopper",
				"dustZinc" }));
		// Bronze Powder
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemPowder, 3, 5),
				new Object[] { "dustCopper", "dustCopper", "dustTin" }));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemMatch), new Object[] { new ItemStack(InitItems.itemResource, 1, 3),
				"stickWood" }));

		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockSlate, 4, 6), "SS", "SS", 'S', new ItemStack(InitBlocks.blockSlate, 1, 0));
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockSlate, 4, 7), "SS", "SS", 'S', new ItemStack(InitBlocks.blockSlate, 1, 1));
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockSlate, 4, 8), "SS", "SS", 'S', new ItemStack(InitBlocks.blockSlate, 1, 2));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemChisel), new Object[] { " I", " S", 'I', Items.iron_ingot, 'S',
				"stickWood" }));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemWatch), new Object[] { "SPS", "PMP", "SPS", 'P', "paneGlass", 'M',
				"partClockworkMechanism", 'S', "plateBrass" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemTimeClock), new Object[] { "DGD", "GWG", "DGD", 'G', Items.ghast_tear, 'W',
				InitItems.itemWatch, 'D', Items.diamond }));

		GameRegistry.addRecipe(new ItemStack(InitItems.itemTeapot, 1, 0), "BB ", "B B", "BB ", 'B', Items.brick);
		GameRegistry.addRecipe(new ItemStack(InitItems.itemTeapot, 1, 1), "PW", 'W', Items.water_bucket, 'P', new ItemStack(InitItems.itemTeapot, 1,
				0));
		GameRegistry.addRecipe(new ItemStack(InitItems.itemTeapot, 1, 3), "SLS", "LPL", "SLS", 'P', new ItemStack(InitItems.itemTeapot, 1, 2), 'L',
				InitItems.itemTeaLeaf, 'S', Items.sugar);

		GameRegistry.addRecipe(new ItemStack(InitItems.itemTeacup, 1, 1), "B B", " B ", 'B', Items.brick);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemElectricJarSmall, 1, 20), new Object[] { "SIS", "GIG", "GGG", 'S',
				InitItems.itemSlimeRubber, 'I', "ingotCopper", 'G', Blocks.glass }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemElectricJarMedium, 1, 20), new Object[] { "I I", "IJI", "I I", 'S',
				InitItems.itemSlimeRubber, 'I', "ingotGold", 'G', Blocks.glass, 'J', InitItems.itemElectricJarSmall }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemElectricJarLarge, 1, 20), new Object[] { "CSC", "SJS", "CSC", 'S', "plateSteel",
				'C',
				"plateCastIron", 'G', Blocks.glass, 'J', InitItems.itemElectricJarMedium }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemElectricJarHuge, 1, 20), new Object[] { "GGG", "GJG", "GGG", 'S',
				InitItems.itemSlimeRubber, 'I', "ingotSteel", 'G', Items.diamond, 'J', InitItems.itemElectricJarHuge }));
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockFlesh), "ZZ", "ZZ", 'Z', Items.rotten_flesh);
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockCongealedSlime), "RRR", "RRR", "RRR", 'R', InitItems.itemSlimeRubber);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemSpyglass), new Object[] { "BGB", "W W", " G ", 'B', "ingotBrass", 'G',
				"paneGlass", 'W', "plankWood" }));
	}

	public static void initializeSmeltingRecipes()
	{
		// Ores ---> Ingots
		for(int meta = 0; meta < 4; meta++)
		{
			GameRegistry.addSmelting(new ItemStack(InitBlocks.blockCustomOre, 1, meta), new ItemStack(InitItems.itemIngot, 1, meta), 0.3F);
		}
		// Dusts ---> Ingots
		for(int meta = 0; meta < 8; meta++)
		{
			GameRegistry.addSmelting(new ItemStack(InitItems.itemPowder, 1, meta), new ItemStack(InitItems.itemIngot, 1, meta), 0.3F);
		}
		// Misc
		GameRegistry.addSmelting(Blocks.iron_block, new ItemStack(InitBlocks.blockMetal, 1, 7), 0.5F);
		GameRegistry.addSmelting(Items.iron_ingot, new ItemStack(InitItems.itemIngot, 1, 7), 0.2F);
		GameRegistry.addSmelting(new ItemStack(InitItems.itemResource, 1, 5), new ItemStack(InitItems.itemResource, 1, 4), 0.5F);

		GameRegistry.addSmelting(new ItemStack(InitBlocks.blockSlate, 1, 3), new ItemStack(InitBlocks.blockSlate, 1, 0), 0.3F);
		GameRegistry.addSmelting(new ItemStack(InitBlocks.blockSlate, 1, 4), new ItemStack(InitBlocks.blockSlate, 1, 1), 0.3F);
		GameRegistry.addSmelting(new ItemStack(InitBlocks.blockSlate, 1, 5), new ItemStack(InitBlocks.blockSlate, 1, 2), 0.3F);

		GameRegistry.addSmelting(new ItemStack(InitItems.itemTeapot, 1, 1), new ItemStack(InitItems.itemTeapot, 1, 2), 0);

		GameRegistry.addSmelting(new ItemStack(InitBlocks.blockBrassLeaves), new ItemStack(InitItems.itemNugget, 6, 4), 0);

		GameRegistry.addSmelting(new ItemStack(Items.slime_ball), new ItemStack(InitItems.itemSlimeRubber), 0);
	}
}