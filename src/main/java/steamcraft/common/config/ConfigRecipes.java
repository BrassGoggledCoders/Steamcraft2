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
package steamcraft.common.config;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.utils.recipe.RecipeUtils;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Surseance
 *
 */
public class ConfigRecipes
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
		initOtherRecipes();
		// Blocks
		/*
		 * GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockObsidianTile), new Object[] {"XX", "XX", 'X', new ItemStack(Block.obsidian)});
		 * GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.castIronLampOff), new Object[] {"ccc", "flf", " f ", 'c', new
		 * ItemStack(ConfigItems.ingotsMetal, 1, 4), 'f', ConfigBlocks.railingCastIron, 'l', ConfigItems.lightBulb}); //Items
		 * GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.uraniumOre, 9), new Object[]{new ItemStack(ConfigBlocks.blockUranium)});
		 * GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.etherium, 9), new Object[]{new ItemStack(ConfigBlocks.blockVolucite)});
		 * GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.lightBulb), new Object[] {"ppp", "p p", "pcp", 'p', Block.thinGlass, 'c',
		 * ConfigItems.copperWire}); //Duplicate Recipes are deliberate, until I find a better way to be able to use copper or iron partPiles ;)
		 * GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.brassWatch), new Object[] {"bgb", "gcg", "bgb", 'b', new ItemStack(ConfigItems.ingotsMetal, 1,
		 * 5), 'g', Block.thinGlass, 'c', new ItemStack(ConfigItems.pileParts, 1, 2)}); GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.canisterEmpty),
		 * new Object[]{" a ", "a a", " a ", 'a', new ItemStack(ConfigItems.ingotsMetal, 1, 0)}); GameRegistry.addShapedRecipe(new
		 * ItemStack(ModTools.drillBase), new Object[]{"   ", "ccc", " c ", 'c', new ItemStack(ConfigItems.ingotsMetal, 1, 4)});
		 * GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.guideBook), new Object[] {Item.book, ConfigItems.lightBulb}); GameRegistry.addRecipe(new
		 * ItemStack(ModTools.chisel), new Object[] {"   ", " i ", " s ", 'i', new ItemStack(Item.ingotIron), 's', new ItemStack(Item.stick)});
		 * GameRegistry.addRecipe(new ItemStack(ConfigItems.itemPoppySeed), new Object[] {new ItemStack(Blocks.double_plant, 1, 2), new
		 * ItemStack(Blocks.double_plant, 1, 2)}); GameRegistry.addRecipe(new ItemStack(ConfigItems.itemPipe, 1, 1), new Object[] {new
		 * ItemStack(ConfigItems.itemPipe), new ItemStack(ConfigItems.itemPoppySeed)});
		 */
	}

	private static void initGunRecipes()
	{
		// Gun Parts
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemGunPart, 1, 0), new Object[] { "WWW", " WW", 'W', Blocks.planks });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemGunPart, 1, 1), new Object[] { "III", "   ", "III", 'I', Items.iron_ingot });
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.itemGunPart, 1, 2), new Object[] { new ItemStack(ConfigItems.itemGunPart, 1, 1),
				ConfigItems.itemChisel });
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemGunPart, 1, 3), new Object[] { "  I", " II", 'I', Items.iron_ingot });
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemGunPart, 1, 4), new Object[] { "III", " II", 'I', new ItemStack(ConfigItems.itemIngot, 1, 4) });
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemGunPart, 1, 5), new Object[] { " II", " II", "  I", 'I',
				new ItemStack(ConfigItems.itemIngot, 1, 7) });
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemGunPart, 1, 6), new Object[] { "IGI", "GSG", "IGI", 'I',
				new ItemStack(ConfigItems.itemSheet, 1, 4), 'G', Blocks.glass_pane, 'S', Items.emerald });
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemGunPart, 1, 7), new Object[] { "IGI", "GSG", "IGI", 'I',
				new ItemStack(ConfigItems.itemSheet, 1, 4), 'G', Blocks.glass_pane, 'S', Items.diamond });
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemGunPart, 1, 8), new Object[] { "SSS", "SES", "SSS", 'E',
				new ItemStack(ConfigItems.itemResource, 1, 0), 'S', new ItemStack(ConfigItems.itemResource, 1, 2) });
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemGunPart, 1, 9), new Object[] { "SPS", "PNP", "SPS", 'S',
				new ItemStack(ConfigItems.itemResource, 1, 2), 'P', new ItemStack(ConfigItems.itemGunPart, 1, 8), 'N', Items.nether_star });
		// Actual Guns
		GameRegistry.addRecipe(new ItemStack(ConfigItems.flintlockMusket), new Object[] { " FL", " B ", " S ", 'S',
				new ItemStack(ConfigItems.itemGunPart, 1, 0), 'B', new ItemStack(ConfigItems.itemGunPart, 1, 1), 'L',
				new ItemStack(ConfigItems.itemGunPart, 1, 3), 'F', Items.flint });
		GameRegistry.addRecipe(new ItemStack(ConfigItems.matchlockMusket), new Object[] { " FL", " B ", " S ", 'S',
				new ItemStack(ConfigItems.itemGunPart, 1, 0), 'B', new ItemStack(ConfigItems.itemGunPart, 1, 1), 'L',
				new ItemStack(ConfigItems.itemGunPart, 1, 3), 'F', ConfigItems.itemMatch });
		GameRegistry.addRecipe(new ItemStack(ConfigItems.percussionCapMusket), new Object[] { " FL", " B ", " S ", 'S',
				new ItemStack(ConfigItems.itemGunPart, 1, 0), 'B', new ItemStack(ConfigItems.itemGunPart, 1, 1), 'L',
				new ItemStack(ConfigItems.itemGunPart, 1, 3), 'F', ConfigItems.itemPercussionCap });
		GameRegistry.addRecipe(new ItemStack(ConfigItems.flintlockRifle), new Object[] { " FL", " B ", " S ", 'S',
				new ItemStack(ConfigItems.itemGunPart, 1, 0), 'B', new ItemStack(ConfigItems.itemGunPart, 1, 2), 'L',
				new ItemStack(ConfigItems.itemGunPart, 1, 3), 'F', Items.flint });
		GameRegistry.addRecipe(new ItemStack(ConfigItems.matchlockRifle), new Object[] { " FL", " B ", " S ", 'S',
				new ItemStack(ConfigItems.itemGunPart, 1, 0), 'B', new ItemStack(ConfigItems.itemGunPart, 1, 2), 'L',
				new ItemStack(ConfigItems.itemGunPart, 1, 3), 'F', ConfigItems.itemMatch });
		GameRegistry.addRecipe(new ItemStack(ConfigItems.percussionCapRifle), new Object[] { " FL", " B ", " S ", 'S',
				new ItemStack(ConfigItems.itemGunPart, 1, 0), 'B', new ItemStack(ConfigItems.itemGunPart, 1, 2), 'L',
				new ItemStack(ConfigItems.itemGunPart, 1, 3), 'F', ConfigItems.itemPercussionCap });
		// Ammo
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemMusketBall, 4), new Object[] { "II", "II", 'I', Items.iron_ingot });
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemRifleBullet, 4), new Object[] { " S ", " C ", " G ", 'S',
				new ItemStack(ConfigItems.itemIngot, 1, 6), 'C', new ItemStack(ConfigItems.itemSheet, 1, 4), 'G', Items.gunpowder });
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemPercussionCap), new Object[] { "GG", "II", 'I', new ItemStack(ConfigItems.itemIngot, 1, 4), 'G',
				Items.gunpowder });

		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemRayGun), new Object[] { "FSP", 'F', new ItemStack(ConfigItems.itemGunPart, 1, 6), 'S',
				new ItemStack(ConfigItems.itemGunPart, 1, 4), 'P', new ItemStack(ConfigItems.itemGunPart, 1, 8) });
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemShrinkray), new Object[] { "FSP", 'F', new ItemStack(ConfigItems.itemGunPart, 1, 7), 'S',
				new ItemStack(ConfigItems.itemGunPart, 1, 5), 'P', new ItemStack(ConfigItems.itemGunPart, 1, 9) });
	}

	private static void initMetalsRecipes()
	{
		for (int meta = 0; meta < 8; meta++)
		{
			RecipeUtils.addMetalRecipes(ConfigBlocks.blockMetal, ConfigItems.itemIngot, ConfigItems.itemNugget, meta);

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ConfigItems.itemSheet, 1, meta), new Object[] {
					"ingot" + LibInfo.metals[meta], new ItemStack(ConfigItems.itemHammer, 1, OreDictionary.WILDCARD_VALUE) }));
		}
		for (int meta = 0; meta < 4; meta++)
		{

			GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ConfigItems.itemPowder, 2, meta), new Object[] { "ore" + LibInfo.metals[meta],
					new ItemStack(ConfigItems.itemHammer, 1, OreDictionary.WILDCARD_VALUE) }));
		}
	}

	private static void initToolsRecipes()
	{
		// Drills
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.drillBase), new Object[] { "   ", "IGI", "III", 'I',
				new ItemStack(ConfigItems.itemIngot, 1, 7), 'G', new ItemStack(ConfigItems.itemSteelParts, 1, 0) });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.drillCore), new Object[] { " D ", "DDD", " B ", 'B', ConfigItems.drillBase, 'D',
				new ItemStack(ConfigItems.itemIngot, 1, 0) });

		ItemStack[] drillMaterials = new ItemStack[] { new ItemStack(Items.diamond, 1), new ItemStack(ConfigItems.itemResource, 1, 0),
				new ItemStack(Items.gold_ingot, 1), new ItemStack(Items.iron_ingot, 1), new ItemStack(ConfigItems.itemResource, 1, 2),
				new ItemStack(ConfigItems.itemIngot, 1, 4), new ItemStack(Blocks.stone, 1), new ItemStack(Blocks.planks, 1) };
		Item[] drills = new Item[] { ConfigItems.drillDiamond, ConfigItems.drillEtherium, ConfigItems.drillGold, ConfigItems.drillIron,
				ConfigItems.drillObsidian, ConfigItems.drillSteam, ConfigItems.drillStone, ConfigItems.drillWood };

		for (int i = 0; i < drillMaterials.length; i++)
			GameRegistry.addShapedRecipe(new ItemStack(drills[i]), new Object[] { " M ", "MMM", " C ", 'C', ConfigItems.drillCore, 'M',
					drillMaterials[i] });

		// Steam
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.swordSteam), new Object[] { "II ", "IP ", "CSC", 'I',
				new ItemStack(ConfigItems.itemSheet, 1, 6), 'P', new ItemStack(ConfigItems.itemIngot, 1, 4), 'S', Items.stick, 'C',
				new ItemStack(ConfigItems.itemMachinePart, 1, 1) });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.pickaxeSteam), new Object[] { "IPI", "CSC", " I ", 'R',
				new ItemStack(ConfigItems.itemMachinePart, 1, 0), 'P', new ItemStack(ConfigItems.itemIngot, 1, 4), 'I',
				new ItemStack(ConfigItems.itemIngot, 1, 6), 'S', Items.stick, 'C', new ItemStack(ConfigItems.itemMachinePart, 1, 1) });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.shovelSteam), new Object[] { "CPC", " S ", " R ", 'R',
				new ItemStack(ConfigItems.itemMachinePart, 1, 0), 'P', new ItemStack(ConfigItems.itemIngot, 1, 4), 'I',
				new ItemStack(ConfigItems.itemIngot, 1, 6), 'S', Items.stick, 'C', new ItemStack(ConfigItems.itemMachinePart, 1, 1) });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.axeSteam), new Object[] { "PIC", "PRC", " R ", 'R',
				new ItemStack(ConfigItems.itemMachinePart, 1, 0), 'P', new ItemStack(ConfigItems.itemIngot, 1, 4), 'I',
				new ItemStack(ConfigItems.itemIngot, 1, 6), 'S', Items.stick, 'C', new ItemStack(ConfigItems.itemMachinePart, 1, 1) });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.hoeSteam), new Object[] { "PIC", " S ", " R ", 'R',
				new ItemStack(ConfigItems.itemMachinePart, 1, 0), 'P', new ItemStack(ConfigItems.itemIngot, 1, 4), 'I',
				new ItemStack(ConfigItems.itemIngot, 1, 6), 'S', Items.stick, 'C', new ItemStack(ConfigItems.itemMachinePart, 1, 1) });

		// Etherium
		RecipeUtils.addToolSet(new ItemStack(ConfigItems.itemResource, 1, 0), new ItemStack[] { new ItemStack(ConfigItems.pickaxeEtherium),
				new ItemStack(ConfigItems.shovelEtherium), new ItemStack(ConfigItems.axeEtherium),
				new ItemStack(ConfigItems.hoeEtherium), new ItemStack(ConfigItems.swordEtherium) });
		// Obsidian
		RecipeUtils.addToolSet(new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack[] { new ItemStack(ConfigItems.pickaxeObsidian),
				new ItemStack(ConfigItems.shovelObsidian), new ItemStack(ConfigItems.axeObsidian),
				new ItemStack(ConfigItems.hoeObsidian), new ItemStack(ConfigItems.swordObsidian) });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemSpanner), new Object[] { "I I", " I ", " I ", 'I',
				new ItemStack(ConfigItems.itemIngot, 1, 5) });
	}

	private static void initArmorRecipes()
	{
		// Etherium
		RecipeUtils.addArmorSet(new ItemStack(ConfigItems.itemResource, 1, 0), new ItemStack[] { new ItemStack(ConfigItems.helmetEtherium),
				new ItemStack(ConfigItems.chestplateEtherium), new ItemStack(ConfigItems.legsEtherium), new ItemStack(ConfigItems.bootsEtherium) });

		// Obsidian
		RecipeUtils.addArmorSet(new ItemStack(ConfigItems.itemResource, 1, 2), new ItemStack[] { new ItemStack(ConfigItems.helmetObsidian),
				new ItemStack(ConfigItems.chestplateObsidian), new ItemStack(ConfigItems.legsObsidian), new ItemStack(ConfigItems.bootsObsidian) });

		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemClockworkWings), new Object[] { "BCB", "WCW", "W W", 'B',
				new ItemStack(ConfigItems.itemIngot, 1, 4), 'C', new ItemStack(ConfigItems.itemMachinePart, 1, 1), 'W', Blocks.wool });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemSteamJetpack), new Object[] { "CSC", "BSB", "B B", 'B',
				new ItemStack(ConfigItems.itemIngot, 1, 4), 'S', new ItemStack(ConfigItems.itemIngot, 1, 6), 'C',
				new ItemStack(ConfigItems.itemCanisterSteam, 1, OreDictionary.WILDCARD_VALUE) });
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemSteamWingpack), new Object[] { "LWL", "RBR", "SPS", 'P', ConfigItems.itemSteamJetpack, 'W',
				ConfigItems.itemClockworkWings, 'R', new ItemStack(ConfigItems.itemMachinePart, 1, 0), 'B', Items.slime_ball, 'L', Items.leather, 'S',
				Items.string });

		GameRegistry.addRecipe(new ItemStack(ConfigItems.brassGoggles), new Object[] { "BBB", "GBG", "BBB", 'B', new ItemStack(ConfigItems.itemIngot, 1, 4),
				'G', Blocks.glass_pane });
	}

	private static void initBlockRecipes()
	{
		GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockSteamBoiler), new Object[] { "PPP", "TCT", "PFP", 'F', Blocks.furnace, 'P',
				new ItemStack(ConfigItems.itemSheet, 1, 4), 'T', ConfigBlocks.blockCopperTank, 'C', ConfigBlocks.blockCopperPipe });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockIntake), new Object[] { "PGP", "GBG", "PGP", 'G',
				new ItemStack(ConfigItems.itemMachinePart, 1, 2), 'P', new ItemStack(ConfigItems.itemSheet, 1, 4), 'B', Items.bucket });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockBloomery), new Object[] { "N N", "NFN", "NNN", 'F', Blocks.furnace, 'N',
				Blocks.nether_brick });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockCopperPipe), new Object[] { "PPP", "   ", "PPP", 'P',
				new ItemStack(ConfigItems.itemSheet, 1, 1) });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockCopperTank), new Object[] { "PPP", "P P", "PPP", 'P',
				new ItemStack(ConfigItems.itemSheet, 1, 1) });

		GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockUranium), new Object[] { "UUU", "UUU", "UUU", 'U',
				new ItemStack(ConfigItems.itemResource, 1, 4) });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockEtherium), new Object[] { "UUU", "UUU", "UUU", 'U',
				new ItemStack(ConfigItems.itemResource, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockCastIronFence), new Object[] { "RRR", "RRR", 'R',
				new ItemStack(ConfigItems.itemMachinePart, 1, 0) });
		GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockCastIronGate), new Object[] { "RIR", "RIR", 'R',
				new ItemStack(ConfigItems.itemMachinePart, 1, 0), 'I', new ItemStack(ConfigItems.itemIngot, 1, 7) });
	}

	private static void initOtherRecipes()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.itemResource, 4, 2), new Object[] { Blocks.obsidian });

		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemSteelParts, 1, 0), new Object[] { " P ", "PPP", " P ", 'P',
				new ItemStack(ConfigItems.itemSheet, 1, 6) });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemSteelParts, 1, 1), new Object[] { " P ", "P P", " P ", 'P',
				new ItemStack(ConfigItems.itemSheet, 1, 6) });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemCopperParts, 1, 0), new Object[] { " P ", "PPP", " P ", 'P',
				new ItemStack(ConfigItems.itemSheet, 1, 1) });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemCopperParts, 1, 1), new Object[] { " P ", "P P", " P ", 'P',
				new ItemStack(ConfigItems.itemSheet, 1, 1) });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemIronParts, 1, 0), new Object[] { " P ", "PPP", " P ", 'P', Items.iron_ingot });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemIronParts, 1, 1), new Object[] { " P ", "P P", " P ", 'P', Items.iron_ingot });

		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemMachinePart, 1, 0), "  X", " X ", "X  ", 'X', new ItemStack(ConfigItems.itemIngot, 1, 7));
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemMachinePart, 1, 1), "CPC", "S G", "CPC", 'P', new ItemStack(ConfigItems.itemSheet, 1, 4),
				'G', new ItemStack(ConfigItems.itemIronParts, 1, 0), 'S', new ItemStack(ConfigItems.itemCopperParts, 1, 1), 'C', new ItemStack(
						ConfigItems.itemMachinePart, 1, 0));
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemMachinePart, 1, 2), new Object[] { "P P", " P ", "P P", 'P',
				new ItemStack(ConfigItems.itemSheet, 1, 6) });

		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemCanisterSteam, 1, ConfigItems.itemCanisterSteam.getMaxDamage()), new Object[] { "PPP",
				"GGG", "PPP", 'G', Blocks.glass_pane, 'P',
				new ItemStack(ConfigItems.itemSheet, 1, 4) });

		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemHammer), new Object[] { "III", " H ", " C ", 'I',
				new ItemStack(ConfigItems.itemIngot, 1, 7), 'H', Items.stick, 'C', new ItemStack(ConfigItems.itemMachinePart, 1, 0) });
		// Brass Powder
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ConfigItems.itemPowder, 3, 4), new Object[] { "dustCopper", "dustCopper",
				"dustZinc" }));
		// Bronze Powder
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ConfigItems.itemPowder, 3, 5), new Object[] { "dustCopper", "dustCopper",
				"dustTin" }));
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.itemMatch), new Object[] { new ItemStack(ConfigItems.itemResource, 1, 3),
				new ItemStack(Items.stick) });

		GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockSlate, 4, 6), "SS", "SS", 'S', new ItemStack(ConfigBlocks.blockSlate, 1, 0));
		GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockSlate, 4, 7), "SS", "SS", 'S', new ItemStack(ConfigBlocks.blockSlate, 1, 1));
		GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockSlate, 4, 8), "SS", "SS", 'S', new ItemStack(ConfigBlocks.blockSlate, 1, 2));

		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemChisel), new Object[] { " I", " S", 'I', Items.iron_ingot, 'S', Items.stick });

		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemWatch), new Object[] { "SPS", "PMP", "SPS", 'P', Blocks.glass_pane, 'M',
				new ItemStack(ConfigItems.itemMachinePart, 1, 1), 'S', new ItemStack(ConfigItems.itemSheet, 1, 4) });

/*		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemKettle, 1, 1), " CC", "CCC", " CC", 'C', new ItemStack(ConfigItems.itemIngot, 1, 7));
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemKettle, 1, 11), "LLL","WKW","LLL", 'K', new ItemStack(ConfigItems.itemKettle, 1, 1), 'W', Items.water_bucket, 'L', ConfigItems.itemTeaLeaf);

		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemTeapot, 1, 11), "KT", 'K', new ItemStack(ConfigItems.itemKettle, 1, 11), 'T', new ItemStack(ConfigItems.itemTeapot,1, 1));

		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemTeacup, 1, 1), "B B", " B ", 'B', Items.brick);*/
	}

	public static void initializeSmeltingRecipes()
	{
		// Ores ---> Ingots
		for (int meta = 0; meta < 5; meta++)
		{
			GameRegistry.addSmelting(new ItemStack(ConfigBlocks.blockCustomOre, 1, meta), new ItemStack(ConfigItems.itemIngot, 1, meta), 0.3F);
			GameRegistry.addSmelting(new ItemStack(ConfigItems.itemPowder, 1, meta), new ItemStack(ConfigItems.itemIngot, 1, meta), 0.3F);
		}
		// Misc
		GameRegistry.addSmelting(Blocks.iron_block, new ItemStack(ConfigBlocks.blockMetal, 1, 7), 0.5F);
		GameRegistry.addSmelting(Items.iron_ingot, new ItemStack(ConfigItems.itemIngot, 1, 7), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ConfigItems.itemResource, 1, 6), new ItemStack(ConfigItems.itemResource, 1, 5), 0.5F);

		GameRegistry.addSmelting(new ItemStack(ConfigBlocks.blockSlate, 1, 3), new ItemStack(ConfigBlocks.blockSlate, 1, 0), 0.3F);
		GameRegistry.addSmelting(new ItemStack(ConfigBlocks.blockSlate, 1, 4), new ItemStack(ConfigBlocks.blockSlate, 1, 1), 0.3F);
		GameRegistry.addSmelting(new ItemStack(ConfigBlocks.blockSlate, 1, 5), new ItemStack(ConfigBlocks.blockSlate, 1, 2), 0.3F);
	}

}