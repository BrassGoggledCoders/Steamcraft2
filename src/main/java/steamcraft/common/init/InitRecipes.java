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

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import boilerplate.common.utils.recipe.RecipeUtils;
import steamcraft.common.config.ConfigBalance;
import steamcraft.common.lib.LibInfo;

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

	private static void initializeCraftingRecipes()
	{
		initMetalsRecipes();
		initToolsRecipes();
		initGunRecipes();
		initArmorRecipes();
		initBlockRecipes();
		initModuleRecipes();
		initOtherRecipes();
		initCompatRecipes();
	}

	private static void initGunRecipes()
	{
		// Gun Parts
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 0), "WWW", " WW", 'W', "plankWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 1), "III", "   ", "III", 'I', "ingotIron"));
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.itemGunPart, 1, 2), new ItemStack(InitItems.itemGunPart, 1, 1), InitItems.itemChisel);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 3), "  I", " II", 'I', "ingotIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 4), "III", " II", 'I', "ingotBrass"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 5), " II", " II", "  I", 'I', "ingotCastIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 6), "IGI", "GSG", "IGI", 'I', "plateBrass", 'G',
				Blocks.glass_pane, 'S', "gemEmerald"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 7), "IGI", "GSG", "IGI", 'I', "plateBrass", 'G',
				"paneGlass", 'S', "gemDiamond"));
		GameRegistry.addRecipe(new ItemStack(InitItems.itemGunPart, 1, 8), "SSS", "SJS", "SSS", 'J', InitItems.itemElectricJarSmall, 'S',
				new ItemStack(InitItems.itemResource, 1, 2));
		if (!ConfigBalance.cheaperCoreRecipe)
			GameRegistry.addRecipe(new ItemStack(InitItems.itemGunPart, 1, 9), "SPS", "PNP", "SPS", 'S', new ItemStack(InitItems.itemResource, 1, 2),
					'P', new ItemStack(InitItems.itemGunPart, 1, 8), 'N', Items.nether_star);
		else
			GameRegistry.addRecipe(new ItemStack(InitItems.itemGunPart, 1, 9), "SPS", "PNP", "SPS", 'S', new ItemStack(InitItems.itemResource, 1, 2),
					'P', new ItemStack(InitItems.itemGunPart, 1, 8), 'N', Items.nether_star);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 10), " II", "   ", " II", 'I', "ingotIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGunPart, 1, 11), " WW", "  W", 'W', "plankWood"));
		// Actual Guns
		GameRegistry.addRecipe(new ItemStack(InitItems.flintlockMusket), " FL", " B ", " S ", 'S', new ItemStack(InitItems.itemGunPart, 1, 0), 'B',
				new ItemStack(InitItems.itemGunPart, 1, 1), 'L', new ItemStack(InitItems.itemGunPart, 1, 3), 'F', Items.flint);
		GameRegistry.addRecipe(new ItemStack(InitItems.matchlockMusket), " FL", " B ", " S ", 'S', new ItemStack(InitItems.itemGunPart, 1, 0), 'B',
				new ItemStack(InitItems.itemGunPart, 1, 1), 'L', new ItemStack(InitItems.itemGunPart, 1, 3), 'F', InitItems.itemMatch);
		GameRegistry.addRecipe(new ItemStack(InitItems.percussionCapMusket), " FL", " B ", " S ", 'S', new ItemStack(InitItems.itemGunPart, 1, 0),
				'B', new ItemStack(InitItems.itemGunPart, 1, 1), 'L', new ItemStack(InitItems.itemGunPart, 1, 3), 'F', InitItems.itemPercussionCap);
		GameRegistry.addRecipe(new ItemStack(InitItems.flintlockRifle), " FL", " B ", " S ", 'S', new ItemStack(InitItems.itemGunPart, 1, 0), 'B',
				new ItemStack(InitItems.itemGunPart, 1, 2), 'L', new ItemStack(InitItems.itemGunPart, 1, 3), 'F', Items.flint);
		GameRegistry.addRecipe(new ItemStack(InitItems.matchlockRifle), " FL", " B ", " S ", 'S', new ItemStack(InitItems.itemGunPart, 1, 0), 'B',
				new ItemStack(InitItems.itemGunPart, 1, 2), 'L', new ItemStack(InitItems.itemGunPart, 1, 3), 'F', InitItems.itemMatch);
		GameRegistry.addRecipe(new ItemStack(InitItems.percussionCapRifle), " FL", " B ", " S ", 'S', new ItemStack(InitItems.itemGunPart, 1, 0), 'B',
				new ItemStack(InitItems.itemGunPart, 1, 2), 'L', new ItemStack(InitItems.itemGunPart, 1, 3), 'F', InitItems.itemPercussionCap);
		GameRegistry.addRecipe(new ItemStack(InitItems.flintlockPistol), " FL", " B ", " S ", 'S', new ItemStack(InitItems.itemGunPart, 1, 11), 'B',
				new ItemStack(InitItems.itemGunPart, 1, 10), 'L', new ItemStack(InitItems.itemGunPart, 1, 3), 'F', Items.flint);
		GameRegistry.addRecipe(new ItemStack(InitItems.matchlockPistol), " FL", " B ", " S ", 'S', new ItemStack(InitItems.itemGunPart, 1, 11), 'B',
				new ItemStack(InitItems.itemGunPart, 1, 10), 'L', new ItemStack(InitItems.itemGunPart, 1, 3), 'F', InitItems.itemMatch);
		GameRegistry.addRecipe(new ItemStack(InitItems.percussionCapPistol), " FL", " B ", " S ", 'S', new ItemStack(InitItems.itemGunPart, 1, 11),
				'B', new ItemStack(InitItems.itemGunPart, 1, 10), 'L', new ItemStack(InitItems.itemGunPart, 1, 3), 'F', InitItems.itemPercussionCap);
		// Ammo
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemMusketBall, 4), "II", "II", 'I', "ingotIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemRifleBullet, 4), " S ", " C ", " G ", 'S', "ingotSteel", 'C',
				"plateBrass", 'G', Items.gunpowder));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemPercussionCap), "GG", "II", 'I', "ingotBrass", 'G', Items.gunpowder));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemRocket, 1, 0), " I ", "PTP", "PTP", 'I', "ingotIron", 'P',
				"plateBrass", 'T', Blocks.tnt));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemRocket, 1, 1), " F ", "PTP", "PTP", 'F', Items.flint_and_steel, 'P',
				"plateBrass", 'T', Blocks.tnt));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemRocket, 1, 2), " U ", "PTP", "PTP", 'U', "ingotUranium", 'P',
				"plateBrass", 'T', Blocks.tnt));
		// Other Guns
		GameRegistry.addRecipe(new ItemStack(InitItems.itemRayGun), "FSP", 'F', new ItemStack(InitItems.itemGunPart, 1, 6), 'S',
				new ItemStack(InitItems.itemGunPart, 1, 4), 'P', new ItemStack(InitItems.itemGunPart, 1, 8));
		GameRegistry.addRecipe(new ItemStack(InitItems.itemShrinkray), "FSP", 'F', new ItemStack(InitItems.itemGunPart, 1, 7), 'S',
				new ItemStack(InitItems.itemGunPart, 1, 5), 'P', new ItemStack(InitItems.itemGunPart, 1, 9));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemClockworkRocketLauncher), "CCC", "  S", "CCC", 'C', "plateCastIron",
				'S', new ItemStack(InitItems.itemGunPart, 1, 5)));
	}

	private static void initMetalsRecipes()
	{
		// Metal recipes and hammering ingots to plates
		for (int meta = 0; meta < 8; meta++)
		{
			RecipeUtils.addMetalRecipes(InitBlocks.blockMetal, InitItems.itemIngot, InitItems.itemNugget, meta);

			GameRegistry
					.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemSheet, 1, meta), "ingot" + LibInfo.metals[meta], "craftingHammer"));
		}
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemVanillaSheet, 1, 0), "ingotIron", "craftingHammer"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemVanillaSheet, 1, 1), "ingotGold", "craftingHammer"));
		// Vanilla iron plate
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemVanillaSheet, 1, 0), "ingotIron", "craftingHammer"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemVanillaSheet, 1, 1), "ingotGold", "craftingHammer"));
		// Our Dusts
		for (int meta = 0; meta < 4; meta++)
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemPowder, ConfigBalance.numberOfDustsFromOre, meta),
					"ore" + LibInfo.metals[meta], "craftingHammer"));
		// Vanilla Dusts
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemVanillaPowder, ConfigBalance.numberOfDustsFromOre, 0), "oreIron",
				"craftingHammer"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemVanillaPowder, ConfigBalance.numberOfDustsFromOre, 1), "oreGold",
				"craftingHammer"));
		// Plates/Ingots back to dust
		for (int meta = 0; meta < 8; meta++)
		{
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemPowder, ConfigBalance.numberOfDustsFromMetal, meta),
					"ingot" + LibInfo.metals[meta], new ItemStack(InitItems.itemGrindstone, 1, OreDictionary.WILDCARD_VALUE)));
			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemPowder, ConfigBalance.numberOfDustsFromMetal, meta),
					"plate" + LibInfo.metals[meta], new ItemStack(InitItems.itemGrindstone, 1, OreDictionary.WILDCARD_VALUE)));
		}
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemVanillaPowder, ConfigBalance.numberOfDustsFromMetal, 0),
				"ingotIron", new ItemStack(InitItems.itemGrindstone, 1, OreDictionary.WILDCARD_VALUE)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemVanillaPowder, ConfigBalance.numberOfDustsFromMetal, 1),
				"ingotGold", new ItemStack(InitItems.itemGrindstone, 1, OreDictionary.WILDCARD_VALUE)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemVanillaPowder, ConfigBalance.numberOfDustsFromMetal, 0),
				"plateIron", new ItemStack(InitItems.itemGrindstone, 1, OreDictionary.WILDCARD_VALUE)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemVanillaPowder, ConfigBalance.numberOfDustsFromMetal, 1),
				"plateGold", new ItemStack(InitItems.itemGrindstone, 1, OreDictionary.WILDCARD_VALUE)));
	}

	private static void initToolsRecipes()
	{
		// Drills
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.drillBase), "   ", "IGI", "III", 'I', "ingotSteel", 'G', "gearSteel"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(InitItems.drillCore), " D ", "DDD", " B ", 'B', InitItems.drillBase, 'D', "ingotAluminum"));

		String[] drillMaterials = new String[] { "gemDiamond", "crystalEtherium", "ingotGold", "ingotIron", "shardObsidian", "ingotBrass", "stone",
				"plankWood" };
		Item[] drills = new Item[] { InitItems.drillDiamond, InitItems.drillEtherium, InitItems.drillGold, InitItems.drillIron,
				InitItems.drillObsidian, InitItems.drillSteam, InitItems.drillStone, InitItems.drillWood };

		for (int i = 0; i < drillMaterials.length; i++)
			GameRegistry
					.addRecipe(new ShapedOreRecipe(new ItemStack(drills[i]), " M ", "MMM", " C ", 'C', InitItems.drillCore, 'M', drillMaterials[i]));

		// Steam
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.swordSteam), "II ", "IP ", "CSC", 'I', "plateSteel", 'P', "ingotBrass",
				'S', Items.stick, 'C', "partClockworkMechanism"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.pickaxeSteam), "IPI", "CSC", " I ", 'R', "partCastIronRod", 'P',
				"ingotBrass", 'I', "ingotSteel", 'S', Items.stick, 'C', "partClockworkMechanism"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.shovelSteam), "CPC", " S ", " R ", 'R', "partCastIronRod", 'P',
				"ingotBrass", 'I', "ingotSteel", 'S', Items.stick, 'C', "partClockworkMechanism"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.axeSteam), "PIC", "PRC", " R ", 'R', "partCastIronRod", 'P', "ingotBrass",
				'I', "ingotSteel", 'S', "stickWood", 'C', "partClockworkMechanism"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.hoeSteam), "PIC", " S ", " R ", 'R', "partCastIronRod", 'P', "ingotBrass",
				'I', "ingotSteel", 'S', Items.stick, 'C', "partClockworkMechanism"));

		// Etherium
		RecipeUtils.addToolSet(new ItemStack(InitItems.itemResource, 1, 0),
				new ItemStack[] { new ItemStack(InitItems.pickaxeEtherium), new ItemStack(InitItems.shovelEtherium),
						new ItemStack(InitItems.axeEtherium), new ItemStack(InitItems.hoeEtherium), new ItemStack(InitItems.swordEtherium) });
		// Obsidian
		RecipeUtils.addToolSet(new ItemStack(InitItems.itemResource, 1, 2),
				new ItemStack[] { new ItemStack(InitItems.pickaxeObsidian), new ItemStack(InitItems.shovelObsidian),
						new ItemStack(InitItems.axeObsidian), new ItemStack(InitItems.hoeObsidian), new ItemStack(InitItems.swordObsidian) });
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemSpanner), "P P", " P ", " P ", 'P', "plateBronze"));
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

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemClockworkWings), "BCB", "WCW", "W W", 'B',
				new ItemStack(InitItems.itemIngot, 1, 4), 'C', "partClockworkMechanism", 'W', Blocks.wool));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemSteamJetpack), "CSC", "BSB", "B B", 'B',
				new ItemStack(InitItems.itemIngot, 1, 4), 'S', new ItemStack(InitItems.itemIngot, 1, 6), 'C',
				new ItemStack(InitItems.itemCanisterSteam, 1, OreDictionary.WILDCARD_VALUE)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemSteamWingpack), "LWL", "RBR", "SPS", 'P', InitItems.itemSteamJetpack,
				'W', InitItems.itemClockworkWings, 'R', "partCastIronRod", 'B', new ItemStack(InitItems.itemGunPart, 1, 8), 'L', Items.leather, 'S',
				Items.string));

		GameRegistry
				.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemBrassGoggles), "BBB", "GBG", "BBB", 'B', "ingotBrass", 'G', "paneGlass"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemMonocle), " B ", "BGB", " BS", 'B', "ingotBrass", 'G', "paneGlass",
				'S', Items.string));
		GameRegistry
				.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemDivingHelmet), "PPP", "PGP", "PPP", 'P', "plateBrass", 'G', "paneGlass"));
	}

	private static void initBlockRecipes()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockSteamBoiler), "PPP", "TCT", "PFP", 'F', Blocks.furnace, 'P',
				"plateBrass", 'T', InitBlocks.blockCopperTank, 'C', InitBlocks.blockCopperPipe));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockSteamBoiler), "PPP", "TCT", "PFP", 'F', Blocks.furnace, 'P',
				"plateSteel", 'T', InitBlocks.blockCopperTank, 'C', InitBlocks.blockCopperPipe));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockIntake), "PGP", "GBG", "PGP", 'G', "partGrating", 'P', "plateBrass",
				'B', Items.bucket));
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.blockBloomery), "N N", "NFN", "NNN", 'F', Blocks.furnace, 'N', Blocks.nether_brick);
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(InitBlocks.blockCopperPipe, 2), "PPP", "GGG", "PPP", 'P', "plateCopper", 'G', "blockGlass"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockCopperTank), "PPP", "P P", "PPP", 'P', "plateCopper"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockBattery), "CCC", "WCW", "CCC", 'C', "ingotCastIron", 'W',
				InitBlocks.blockCopperWire));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemMachinePart, 1, 9), "CRC", "CRC", "WAW", 'C', "plateSteel", 'W',
				InitBlocks.blockCopperWire, 'R', new ItemStack(InitItems.itemMachinePart, 1, 8), 'A', new ItemStack(InitItems.itemResource, 1, 7)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockCapacitor), "PPP", "CCC", "PPP", 'P', "plateSteel", 'C',
				new ItemStack(InitItems.itemMachinePart, 1, 9)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockTeslaCoil), "WRW", "WRW", "III", 'I', "ingotSteel", 'W',
				"partWireCoil", 'R', "partCastIronRod"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockTurbine), "S S", "FGF", "SFS", 'S', "ingotIron", 'F', "partFan", 'G',
				"partGenerator"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockLightningRod), " R ", "CRC", "SSS", 'S', "ingotSteel", 'R',
				"partCastIronRod", 'C', "plateCopper"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockCharger), "PPP", "IWI", "PPP", 'I', "ingotSteel", 'P', "plateSteel",
				'W', InitBlocks.blockCopperWire));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockArmorEditor), "PPP", "WWW", "PCP", 'W', Blocks.crafting_table, 'P',
				"plateBrass", 'C', Blocks.chest));

		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.blockUranium), "UUU", "UUU", "UUU", 'U', new ItemStack(InitItems.itemResource, 1, 4));
		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.blockEtherium), "UUU", "UUU", "UUU", 'U', new ItemStack(InitItems.itemResource, 1, 0));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockCastIronFence, 12), "RRR", "RRR", 'R', "partCastIronRod"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(InitBlocks.blockCastIronGate), "RIR", "RIR", 'R', "partCastIronRod", 'I', "ingotCastIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockCastIronRailing, 16), "III", "III", 'I', "ingotCastIron"));

		GameRegistry.addShapelessRecipe(new ItemStack(InitBlocks.blockRedwoodPlanks, 4), new ItemStack(InitBlocks.blockRedwoodLog));
		GameRegistry.addShapelessRecipe(new ItemStack(InitBlocks.blockWillowPlanks, 4), new ItemStack(InitBlocks.blockWillowLog));
		GameRegistry.addShapelessRecipe(new ItemStack(InitBlocks.blockMangrovePlanks, 4), new ItemStack(InitBlocks.blockMangroveLog));
		GameRegistry.addShapelessRecipe(new ItemStack(InitBlocks.blockPetrifiedPlanks, 4), new ItemStack(InitBlocks.blockPetrifiedLog));

		GameRegistry.addRecipe(new ItemStack(InitItems.itemRedwoodStick, 4), "P", "P", 'P', InitBlocks.blockRedwoodPlanks);
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockRedwoodSlab, 6), "PPP", 'P', InitBlocks.blockRedwoodPlanks);
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockRedwoodStairs, 4), "P  ", "PP ", "PPP", 'P', InitBlocks.blockRedwoodPlanks);
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockRedwoodFence, 2), "SSS", "SSS", 'S', InitItems.itemRedwoodStick);

		GameRegistry.addRecipe(new ItemStack(InitItems.itemMangroveStick, 4), "P", "P", 'P', InitBlocks.blockMangrovePlanks);
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockMangroveSlab, 6), "PPP", 'P', InitBlocks.blockMangrovePlanks);
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockMangroveStairs, 4), "P  ", "PP ", "PPP", 'P', InitBlocks.blockMangrovePlanks);
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockMangroveFence, 2), "SSS", "SSS", 'S', InitItems.itemMangroveStick);

		GameRegistry.addRecipe(new ItemStack(InitItems.itemWillowStick, 4), "P", "P", 'P', InitBlocks.blockWillowPlanks);
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockWillowSlab, 6), "PPP", 'P', InitBlocks.blockWillowPlanks);
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockWillowStairs, 4), "P  ", "PP ", "PPP", 'P', InitBlocks.blockWillowPlanks);
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockWillowFence, 2), "SSS", "SSS", 'S', InitItems.itemWillowStick);

		GameRegistry.addRecipe(new ItemStack(InitItems.itemPetrifiedStick, 4), "P", "P", 'P', InitBlocks.blockPetrifiedPlanks);
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockPetrifiedSlab, 6), "PPP", 'P', InitBlocks.blockPetrifiedPlanks);
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockPetrifiedStairs, 4), "P  ", "PP ", "PPP", 'P', InitBlocks.blockPetrifiedPlanks);
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockPetrifiedFence, 2), "SSS", "SSS", 'S', InitItems.itemPetrifiedStick);

		GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.blockPolishedPlanks, 8), "PPP", "PWP", "PPP", 'P', new ItemStack(Blocks.planks, 1, 0),
				'W', new ItemStack(/* InitItems.itemWhaleOilBucket */Items.water_bucket));
		GameRegistry.addShapelessRecipe(new ItemStack(InitBlocks.blockPolishedPlanks, 1, 1), new ItemStack(InitBlocks.blockPolishedPlanks, 1, 0));
		GameRegistry.addShapelessRecipe(new ItemStack(InitBlocks.blockPolishedPlanks, 1, 2), new ItemStack(InitBlocks.blockPolishedPlanks, 1, 1));
		GameRegistry.addShapelessRecipe(new ItemStack(InitBlocks.blockPolishedPlanks, 1, 0), new ItemStack(InitBlocks.blockPolishedPlanks, 1, 2));

		GameRegistry.addRecipe(new ShapedOreRecipe(InitBlocks.blockStandardSiren, "CIC", "ISI", "CRC", 'S',
				new ItemStack(InitItems.itemMachinePart, 1, 7), 'I', "ingotIron", 'C', "ingotCastIron", 'R', Items.redstone));
		GameRegistry.addRecipe(new ShapedOreRecipe(InitBlocks.blockNuclearSiren, "CIC", "ISI", "CRC", 'S',
				new ItemStack(InitItems.itemMachinePart, 1, 7), 'I', "ingotUranium", 'C', "ingotCastIron", 'R', Items.redstone));
		GameRegistry.addRecipe(new ShapedOreRecipe(InitBlocks.blockAllClearSiren, "CIC", "ISI", "CRC", 'S',
				new ItemStack(InitItems.itemMachinePart, 1, 7), 'I', Blocks.wool, 'C', "ingotCastIron", 'R', Items.redstone));
		GameRegistry.addRecipe(new ShapedOreRecipe(InitBlocks.blockIntruderSiren, "CIC", "ISI", "CRC", 'S',
				new ItemStack(InitItems.itemMachinePart, 1, 7), 'I', Items.gold_nugget, 'C', "ingotCastIron", 'R', Items.redstone));

		GameRegistry.addRecipe(new ShapedOreRecipe(InitBlocks.blockStasisField, "PPP", "PCP", "PPP", 'P', "plateCastIron", 'C',
				new ItemStack(InitItems.itemGunPart, 1, 8)));

		for (int i = 0; i < LibInfo.metals.length; i++)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(InitBlocks.blockMetal, 1, i), new ItemStack(InitBlocks.blockEngraved, 1, i));
		}

		GameRegistry.addShapelessRecipe(new ItemStack(InitBlocks.blockUranium), new ItemStack(InitBlocks.blockEngraved, 1, 8));
		GameRegistry.addShapelessRecipe(new ItemStack(InitBlocks.blockEtherium), new ItemStack(InitBlocks.blockEngraved, 1, 9));

		Block[] unengravedV = { Blocks.diamond_block, Blocks.gold_block, Blocks.iron_block, Blocks.lapis_block, Blocks.stone };

		for (int i = 0; i < unengravedV.length; i++)
		{
			GameRegistry.addShapelessRecipe(new ItemStack(unengravedV[i]), new ItemStack(InitBlocks.blockEngravedVanilla, 1, i));
		}

		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockStonebrickWall, 6, 0), "NNN", "NNN", 'N', new ItemStack(Blocks.stonebrick, 1, 0));
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockStonebrickWall, 6, 1), "NNN", "NNN", 'N', new ItemStack(Blocks.stonebrick, 1, 1));
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockStonebrickWall, 6, 2), "NNN", "NNN", 'N', new ItemStack(Blocks.stonebrick, 1, 2));
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockStonebrickWall, 6, 3), "NNN", "NNN", 'N', new ItemStack(Blocks.stonebrick, 1, 3));
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockStoneslabWall, 6), "NNN", "NNN", 'N', new ItemStack(Blocks.stone_slab, 1, 0));
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockBrickWall, 6), "NNN", "NNN", 'N', new ItemStack(Blocks.brick_block, 1, 0));

		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockSkyrail), " N ", "GGG", "R R", 'N', new ItemStack(Blocks.golden_rail, 1, 0), 'G',
				Items.gold_ingot, 'R', Items.redstone);
	}

	private static void initModuleRecipes()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemAqualung), "LLI", "L L", "LLL", 'L', Items.leather, 'I', "ingotIron"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(InitItems.itemClimbingSpikes), " L ", "I I", 'L', Items.leather_boots, 'I', "ingotIron"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(InitItems.itemTank), "GGG", "GWG", "GGG", 'G', Blocks.glass_pane, 'W', Items.water_bucket));
		GameRegistry
				.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemFlippers), " B ", "L L", 'L', Items.leather, 'B', Items.leather_boots));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemLegBraces), "PPP", "P P", "P P", 'P', "plateCopper"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemLifeVest), "W W", "WWW", "WWW", 'W', Blocks.wool));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemParachute), "WWW", "W W", "S S", 'W', Blocks.wool, 'S', Items.string));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemPistonBoots), "   ", "CBC", "P P", 'P', Blocks.piston, 'B',
				Items.leather_boots, 'C', "partClockworkMechanism"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemPistonPlating), "RPR", "PCP", "RPR", 'C', Items.iron_chestplate, 'P',
				Blocks.piston, 'R', Items.redstone));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemRollerSkates), "   ", " L ", "WRW", 'W', Blocks.log, 'L',
				Items.leather_boots, 'R', "partCastIronRod"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemSpringHeels), "   ", " L ", "S S", 'S',
				new ItemStack(InitItems.itemSteelParts, 1, 2), 'L', Items.leather_boots));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemSteelPlating), "SSS", "SSS", "SSS", 'S', "plateSteel"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemWatchDisplay), "GGG", "RCR", "III", 'I', "ingotIron", 'G', "paneGlass",
				'R', Items.redstone, 'C', Items.clock));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemWingpackModule),
				new Object[] { "B B", " X ", "B B", 'X', InitItems.itemSteamWingpack, 'B', "plateBrass" }));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemGogglesModule),
				new Object[] { "BBB", " X ", "BBB", 'X', InitItems.itemBrassGoggles, 'B', "plateBrass" }));
				// GameRegistry.addRecipe(new ShapedOreRecipe(new
				// ItemStack(InitItems.itemLastResort), "TTT", "TCT", "TTT",
				// 'T',
				// Blocks.tnt, 'C', InitBlocks.blockCopperWire));

		// GameRegistry.addShapedRecipe(new ItemStack(InitBlocks.blockTimeBomb),
		// new Object[] { " W ", "PTP", " P ", 'W', InitItems.itemWatch, 'P',
		// Items.paper,
		// 'T', Blocks.tnt });

	}

	private static void initOtherRecipes()
	{
		GameRegistry.addShapedRecipe(new ItemStack(InitItems.itemResource, 1, 2), "XXX", "XXX", "XXX", 'X', new ItemStack(Blocks.obsidian));
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.itemResource, 1, 7), new ItemStack(Items.potionitem),
				new ItemStack(InitItems.itemResource, 1, 1), new ItemStack(InitItems.itemResource, 1, 1));

		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(InitItems.itemSteelParts, 1, 0), " P ", "PIP", " P ", 'P', "plateSteel", 'I', "ingotSteel"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemSteelParts, 1, 1), " P ", "P P", " P ", 'P', "plateSteel"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(InitItems.itemSteelParts, 1, 2), "PPP", " I ", "PPP", 'P', "plateSteel", 'I', "ingotSteel"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(InitItems.itemCopperParts, 1, 0), " P ", "PIP", " P ", 'P', "plateCopper", 'I', "ingotCopper"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemCopperParts, 1, 1), " P ", "P P", " P ", 'P', "plateCopper"));
		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(InitItems.itemIronParts, 1, 0), " P ", "PIP", " P ", 'I', "ingotIron", 'P', "plateIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemIronParts, 1, 1), " P ", "P P", " P ", 'P', "plateIron"));

		GameRegistry.addRecipe(
				new ShapedOreRecipe(new ItemStack(InitItems.itemMachinePart, 1, 0), " X ", "X  ", 'X', new ItemStack(InitItems.itemIngot, 1, 7)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemMachinePart, 1, 1), "CPC", "S G", "CPC", 'P',
				new ItemStack(InitItems.itemSheet, 1, 4), 'G', "gearIron", 'S', new ItemStack(InitItems.itemCopperParts, 1, 1), 'C',
				new ItemStack(InitItems.itemMachinePart, 1, 0)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemMachinePart, 1, 2), "P P", " P ", "P P", 'P', "plateIron"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemMachinePart, 1, 3), new ItemStack(Items.water_bucket), "ingotIron",
				"ingotCopper", "ingotZinc"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemMachinePart, 2, 3), "partMagnet", "ingotIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemMachinePart, 1, 4), "PRP", "MWM", "PRP", 'P', "plateIron", 'W',
				"partWireCoil", 'M', "partMagnet", 'R', "partCastIronRod"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemMachinePart, 1, 5), " P ", "PPP", " P ", 'P', "plateIron"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemMachinePart, 1, 6), " W ", "WSW", " W ", 'S', "partCastIronRod", 'W',
				new ItemStack(InitBlocks.blockCopperWire)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemMachinePart, 1, 7), "L L", " L ", "MCM", 'M', "partMagnet", 'C',
				new ItemStack(InitBlocks.blockCopperWire), 'L', Items.leather));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemMachinePart, 1, 8), "XRX", 'X', "plateCopper", 'R',
				new ItemStack(InitItems.itemSlimeRubber)));

		// Wires
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockCopperWire, 1, 0), "CD", 'C', "ingotCopper", 'D',
				new ItemStack(InitItems.itemDrawplate, 1, OreDictionary.WILDCARD_VALUE)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockCopperWire, 3, 1), "WWW", "CCC", "WWW", 'C',
				new ItemStack(InitBlocks.blockCopperWire, 1, 0), 'W', new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockSteelWire, 1, 0), "CD", 'C', "ingotSteel", 'D',
				new ItemStack(InitItems.itemDrawplate, 1, OreDictionary.WILDCARD_VALUE)));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockSteelWire, 3, 1), "WWW", "CCC", "WWW", 'C',
				new ItemStack(InitBlocks.blockSteelWire, 1, 0), 'W', new ItemStack(Blocks.wool, 1, OreDictionary.WILDCARD_VALUE)));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemDrawplate), "S S", " S ", "S S", 'S', "plateCastIron"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemCanisterSteam, 1, InitItems.itemCanisterSteam.getMaxDamage()), "PPP",
				"GGG", "PPP", 'G', "paneGlass", 'P', "plateBrass"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemHammer), "III", " H ", " C ", 'I', "ingotCastIron", 'H', Items.stick,
				'C', "partCastIronRod"));
		// Brass Powder
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemPowder, 3, 4), "dustCopper", "dustCopper", "dustZinc"));
		// Bronze Powder
		GameRegistry
				.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemPowder, 4, 5), "dustCopper", "dustCopper", "dustCopper", "dustTin"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(InitItems.itemMatch), new ItemStack(InitItems.itemResource, 1, 3), "stickWood"));

		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockSlate, 4, 6), "SS", "SS", 'S', new ItemStack(InitBlocks.blockSlate, 1, 0));
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockSlate, 4, 7), "SS", "SS", 'S', new ItemStack(InitBlocks.blockSlate, 1, 1));
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockSlate, 4, 8), "SS", "SS", 'S', new ItemStack(InitBlocks.blockSlate, 1, 2));
		// Slate Stairs
		Block[] stairs = new Block[] { InitBlocks.blockRawBlueSlateStairs, InitBlocks.blockRawBlackSlateStairs, InitBlocks.blockRawRedSlateStairs,
				InitBlocks.blockCobbleBlueSlateStairs, InitBlocks.blockCobbleBlackSlateStairs, InitBlocks.blockCobbleRedSlateStairs,
				InitBlocks.blockBrickBlueSlateStairs, InitBlocks.blockBrickBlackSlateStairs, InitBlocks.blockBrickRedSlateStairs };
		for (int i = 0; i < 9; i++)
			GameRegistry.addRecipe(new ItemStack(stairs[i], 4), "X  ", "XX ", "XXX", 'X', new ItemStack(InitBlocks.blockSlate, 1, i));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemChisel), " I", " S", 'I', "ingotIron", 'S', "stickWood"));

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemWatch), "SPS", "PMP", "SPS", 'P', "paneGlass", 'M',
				"partClockworkMechanism", 'S', "plateBrass"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemTimeClock), "DGD", "GWG", "DGD", 'G', Items.ghast_tear, 'W',
				InitItems.itemWatch, 'D', Items.diamond));

		GameRegistry.addRecipe(new ItemStack(InitItems.itemTeapot, 1, 0), "BB ", "B B", "BB ", 'B', Items.brick);
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.itemTeapot, 1, 1), Items.potionitem, new ItemStack(InitItems.itemTeapot, 1, 0));
		GameRegistry.addRecipe(new ItemStack(InitItems.itemTeapot, 1, 12), "SLS", "LPL", "SLS", 'P', new ItemStack(InitItems.itemTeapot, 1, 2), 'L',
				InitItems.itemTeaLeaf, 'S', Items.sugar);

		GameRegistry.addRecipe(new ItemStack(InitItems.itemTeacup), "B B", "BBB", 'B', Items.clay_ball);

		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemElectricJarSmall), "SIS", "GIG", "GGG", 'S', InitItems.itemSlimeRubber,
				'I', "ingotCopper", 'G', Blocks.glass));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemElectricJarMedium), "I I", "IJI", "I I", 'S',
				InitItems.itemSlimeRubber, 'I', "ingotGold", 'G', Blocks.glass, 'J', InitItems.itemElectricJarSmall));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemElectricJarLarge), "CSC", "SJS", "CSC", 'S', "plateSteel", 'C',
				"plateCastIron", 'G', Blocks.glass, 'J', InitItems.itemElectricJarMedium));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemElectricJarHuge), "GGG", "GJG", "GGG", 'S', InitItems.itemSlimeRubber,
				'I', "ingotSteel", 'G', Items.diamond, 'J', InitItems.itemElectricJarLarge));
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockFlesh), "ZZ", "ZZ", 'Z', Items.rotten_flesh);
		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockCongealedSlime), "RRR", "RRR", "RRR", 'R', InitItems.itemSlimeRubber);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemSpyglass), "BGB", "W W", " G ", 'B', "ingotBrass", 'G', "paneGlass",
				'W', "plankWood"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitItems.itemFieldManipulator, 4, 0), "BRB", "RER", "BRB", 'B', "nuggetBrass", 'E',
				Items.ender_pearl, 'R', Items.redstone));

		GameRegistry.addRecipe(new ItemStack(InitBlocks.blockPath, 6), "PPP", "XXX", 'P', Blocks.stone_slab, 'X', Blocks.stonebrick);
		GameRegistry.addRecipe(new ItemStack(InitItems.itemEmptyShieldedCanister), "XXX", "X X", "XXX", 'X', new ItemStack(InitItems.itemSheet, 1, 7),
				'U', new ItemStack(InitItems.itemResource, 1, 5));
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.itemShieldedCanister), InitItems.itemEmptyShieldedCanister,
				new ItemStack(InitItems.itemResource, 1, 5));
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.itemResource, 1, 5), InitItems.itemShieldedCanister);
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(InitBlocks.blockCastIronLamp),
				new Object[] { "III", "PGP", "PRP", 'I', "ingotCastIron", 'P', "plateCastIron", 'G', Blocks.glowstone, 'R', "partCastIronRod" }));
		GameRegistry.addShapelessRecipe(new ItemStack(InitBlocks.blockInvertedCastIronLamp),
				new Object[] { InitBlocks.blockCastIronLamp, Blocks.redstone_torch });
		GameRegistry.addShapelessRecipe(new ItemStack(InitBlocks.blockCastIronLamp),
				new Object[] { InitBlocks.blockInvertedCastIronLamp, Blocks.redstone_torch });
		GameRegistry.addShapelessRecipe(new ItemStack(InitItems.itemSplashLightningBottle), InitItems.itemElectricJarSmall, Items.gunpowder);
		GameRegistry.addRecipe(new ItemStack(InitItems.itemGrindstone), "FS", "SF", 'F', Items.flint, 'S', Blocks.stone);
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.gunpowder, 2), "dustSulphur", new ItemStack(Items.coal, 1, 1),
				"dustSulphur", new ItemStack(Items.coal, 1, 1)));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.dye, 8, 15), "powderPhosphorus", Items.wheat_seeds, "powderPhosphorus",
				Items.wheat_seeds));
	}

	private static void initializeSmeltingRecipes()
	{
		// Ores ---> Ingots
		for (int meta = 0; meta < 4; meta++)
		{
			GameRegistry.addSmelting(new ItemStack(InitBlocks.blockCustomOre, 1, meta), new ItemStack(InitItems.itemIngot, 1, meta), 0.3F);
		}
		// Dusts ---> Ingots
		for (int meta = 0; meta < 8; meta++)
			GameRegistry.addSmelting(new ItemStack(InitItems.itemPowder, 1, meta), new ItemStack(InitItems.itemIngot, 1, meta), 0.3F);
		GameRegistry.addSmelting(new ItemStack(InitItems.itemVanillaPowder, 1, 0), new ItemStack(Items.iron_ingot), 0.3F);
		GameRegistry.addSmelting(new ItemStack(InitItems.itemVanillaPowder, 1, 1), new ItemStack(Items.gold_ingot), 0.3F);
		// Misc
		GameRegistry.addSmelting(Blocks.iron_block, new ItemStack(InitBlocks.blockMetal, 1, 7), 0.5F);
		GameRegistry.addSmelting(Items.iron_ingot, new ItemStack(InitItems.itemIngot, 1, 7), 0.2F);
		GameRegistry.addSmelting(new ItemStack(InitItems.itemResource, 1, 4), new ItemStack(InitItems.itemResource, 1, 5), 0.5F);

		GameRegistry.addSmelting(new ItemStack(InitBlocks.blockSlate, 1, 3), new ItemStack(InitBlocks.blockSlate, 1, 0), 0.3F);
		GameRegistry.addSmelting(new ItemStack(InitBlocks.blockSlate, 1, 4), new ItemStack(InitBlocks.blockSlate, 1, 1), 0.3F);
		GameRegistry.addSmelting(new ItemStack(InitBlocks.blockSlate, 1, 5), new ItemStack(InitBlocks.blockSlate, 1, 2), 0.3F);

		GameRegistry.addSmelting(new ItemStack(InitItems.itemTeapot, 1, 1), new ItemStack(InitItems.itemTeapot, 1, 2), 0);

		GameRegistry.addSmelting(new ItemStack(Items.slime_ball), new ItemStack(InitItems.itemSlimeRubber), 0);

		GameRegistry.addSmelting(InitBlocks.blockBrassLog, new ItemStack(InitItems.itemIngot, 2, 4), 0);
	}

	private static void initCompatRecipes()
	{
		// Thaumcraft
		if (Loader.isModLoaded("Thaumcraft"))
		{
			GameRegistry.addSmelting(new ItemStack(InitItems.itemSteamcraftCluster, 1, 0), new ItemStack(InitItems.itemIngot, 1, 0), 0);
			GameRegistry.addSmelting(new ItemStack(InitItems.itemSteamcraftCluster, 1, 1), new ItemStack(InitItems.itemIngot, 1, 3), 0);
		}

	}

}