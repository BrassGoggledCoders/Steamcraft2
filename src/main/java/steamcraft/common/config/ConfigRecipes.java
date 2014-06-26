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

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import boilerplate.common.utils.recipe.RecipeUtils;
import cpw.mods.fml.common.registry.GameRegistry;

/**
*
* @author Surseance (Johnny Eatmon)
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
		initArmorRecipes();
		initBlockRecipes();
		initOtherRecipes();
		// Blocks
		/*
		 *
		 * GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockObsidianTile),
		 * new Object[] {"XX", "XX", 'X', new ItemStack(Block.obsidian)});
		 * GameRegistry.addShapedRecipe(new
		 * ItemStack(ConfigBlocks.castIronLampOff), new Object[] {"ccc", "flf",
		 * " f ", 'c', new ItemStack(ConfigItems.ingotsMetal, 1, 4), 'f',
		 * ConfigBlocks.railingCastIron, 'l', ConfigItems.lightBulb}); //Items
		 * GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.uraniumOre,
		 * 9), new Object[]{new ItemStack(ConfigBlocks.blockUranium)});
		 * GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.etherium,
		 * 9), new Object[]{new ItemStack(ConfigBlocks.blockVolucite)});
		 *
		 * GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.lightBulb),
		 * new Object[] {"ppp", "p p", "pcp", 'p', Block.thinGlass, 'c',
		 * ConfigItems.copperWire}); //Duplicate Recipes are deliberate, until I
		 * find a better way to be able to use copper or iron partPiles ;)
		 * GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.brassWatch),
		 * new Object[] {"bgb", "gcg", "bgb", 'b', new
		 * ItemStack(ConfigItems.ingotsMetal, 1, 5), 'g', Block.thinGlass, 'c',
		 * new ItemStack(ConfigItems.pileParts, 1, 2)});
		 * GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.brassWatch),
		 * new Object[] {"bgb", "gcg", "bgb", 'b', new
		 * ItemStack(ConfigItems.ingotsMetal, 1, 5), 'g', Block.thinGlass, 'c',
		 * new ItemStack(ConfigItems.pileParts, 1, 2)});
		 * GameRegistry.addShapedRecipe(new
		 * ItemStack(ConfigItems.canisterEmpty), new Object[]{" a ", "a a",
		 * " a ", 'a', new ItemStack(ConfigItems.ingotsMetal, 1, 0)});
		 * GameRegistry.addShapedRecipe(new ItemStack(ModTools.drillBase), new
		 * Object[]{"   ", "ccc", " c ", 'c', new
		 * ItemStack(ConfigItems.ingotsMetal, 1, 4)});
		 * GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.guideBook),
		 * new Object[] {Item.book, ConfigItems.lightBulb});
		 * GameRegistry.addRecipe(new ItemStack(ModTools.chisel), new Object[]
		 * {"   ", " i ", " s ", 'i', new ItemStack(Item.ingotIron), 's', new
		 * ItemStack(Item.stick)}); GameRegistry.addRecipe(new
		 * ItemStack(ConfigItems.itemPoppySeed), new Object[] {new
		 * ItemStack(Blocks.double_plant, 1, 2), new
		 * ItemStack(Blocks.double_plant, 1, 2)}); GameRegistry.addRecipe(new
		 * ItemStack(ConfigItems.itemPipe, 1, 1), new Object[] {new
		 * ItemStack(ConfigItems.itemPipe), new
		 * ItemStack(ConfigItems.itemPoppySeed)}); //Parts //There MUST be a
		 * neater/shorter way to do this??? GameRegistry.addShapelessRecipe(new
		 * ItemStack(ConfigItems.pileParts, 1, 0), new Object[] {new
		 * ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE)});
		 * GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.pileParts,
		 * 1, 1),new Object[] {new
		 * ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE)});
		 * GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.pileParts,
		 * 1, 2),new Object[] {new
		 * ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE)});
		 * GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.pileParts,
		 * 1, 3),new Object[] {new
		 * ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new
		 * ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE)});
		 */
	}

	private static void initMetalsRecipes()
	{
		for (int meta = 0; meta < 8; meta++)
		{
			RecipeUtils.addMetalRecipes(ConfigBlocks.blockMetal, ConfigItems.itemIngot, ConfigItems.itemNugget, meta);

			GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.itemSheet, 1, meta), new Object[] {
					new ItemStack(ConfigItems.itemIngot, 1, meta), new ItemStack(ConfigItems.itemHammer, 1, OreDictionary.WILDCARD_VALUE) });
			GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.itemPowder, 2, meta), new Object[] {
					new ItemStack(ConfigBlocks.blockCustomOre, 1, meta), new ItemStack(ConfigItems.itemHammer, 1, OreDictionary.WILDCARD_VALUE) });
		}
	}

	private static void initToolsRecipes()
	{
		//Drills

		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.drillBase), new Object[]{"   ","IGI","III", 'I', new ItemStack(ConfigItems.itemIngot,1, 7), 'G', new ItemStack(ConfigItems.itemSteelParts, 1, 0)});
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.drillCore), new Object[]{" D ","DDD"," B ", 'B', ConfigItems.drillBase, 'D', new ItemStack(ConfigItems.itemIngot, 1, 6)});

		ItemStack[] drillMaterials = new ItemStack[]{new ItemStack(Items.diamond, 1), new ItemStack(ConfigItems.itemResource, 1, 0), new ItemStack(Items.gold_ingot, 1), new ItemStack(Items.iron_ingot, 1), new ItemStack(ConfigItems.itemResource, 1, 3), new ItemStack(ConfigItems.itemIngot, 1, 4), new ItemStack(Blocks.stone, 1), new ItemStack(Blocks.planks, 1)};
		Item[] drills = new Item[]{ConfigItems.drillDiamond, ConfigItems.drillEtherium, ConfigItems.drillGold, ConfigItems.drillIron, ConfigItems.drillObsidian, ConfigItems.drillSteam, ConfigItems.drillStone, ConfigItems.drillWood};

		for(int i=0; i<drillMaterials.length;i++)
		GameRegistry.addShapedRecipe(new ItemStack(drills[i]), new Object[]{" M ","MMM"," C ", 'C', ConfigItems.drillCore, 'M', drillMaterials[i]});

		//Steam
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.swordSteam), new Object[]{" P "," P "," I ", 'I', new ItemStack(ConfigItems.itemIngot, 1, 4), 'P', new ItemStack(ConfigItems.itemSheet, 1, 6),'S', Items.stick});
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.pickaxeSteam), new Object[]{"PPP"," S "," I ", 'I', new ItemStack(ConfigItems.itemIngot, 1, 4), 'P', new ItemStack(ConfigItems.itemSheet, 1, 6),'S', Items.stick});
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.shovelSteam), new Object[]{" P "," S "," I ", 'I', new ItemStack(ConfigItems.itemIngot, 1, 4), 'P', new ItemStack(ConfigItems.itemSheet, 1, 6),'S', Items.stick});
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.axeSteam), new Object[]{"PP ","PI "," S ", 'I', new ItemStack(ConfigItems.itemIngot, 1, 4), 'P', new ItemStack(ConfigItems.itemSheet, 1, 6),'S', Items.stick});
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.hoeSteam), new Object[]{"PP "," I "," S ", 'I', new ItemStack(ConfigItems.itemIngot, 1, 4), 'P', new ItemStack(ConfigItems.itemSheet, 1, 6), 'S', Items.stick});

		//Etherium
		RecipeUtils.addToolSet(new ItemStack(ConfigItems.itemResource, 1, 0), new ItemStack[]{new ItemStack(ConfigItems.pickaxeEtherium), new ItemStack(ConfigItems.axeEtherium), new ItemStack(ConfigItems.shovelEtherium), new ItemStack(ConfigItems.shovelEtherium), new ItemStack(ConfigItems.hoeEtherium), new ItemStack(ConfigItems.swordEtherium)});
		//Obsidian
		RecipeUtils.addToolSet(new ItemStack(ConfigItems.itemResource, 1, 3), new ItemStack[]{new ItemStack(ConfigItems.pickaxeObsidian), new ItemStack(ConfigItems.axeObsidian), new ItemStack(ConfigItems.shovelObsidian), new ItemStack(ConfigItems.shovelObsidian), new ItemStack(ConfigItems.hoeObsidian), new ItemStack(ConfigItems.swordObsidian)});
	}

	private static void initArmorRecipes()
	{
		//Etherium
		RecipeUtils.addArmorSet(new ItemStack(ConfigItems.itemResource, 1, 0), new ItemStack[]{new ItemStack(ConfigItems.helmetEtherium),
			new ItemStack(ConfigItems.chestplateEtherium), new ItemStack(ConfigItems.legsEtherium), new ItemStack(ConfigItems.bootsEtherium)});

		//Obsidian
		RecipeUtils.addArmorSet(new ItemStack(ConfigItems.itemResource, 1, 3), new ItemStack[]{new ItemStack(ConfigItems.helmetObsidian),
			new ItemStack(ConfigItems.chestplateObsidian), new ItemStack(ConfigItems.legsObsidian), new ItemStack(ConfigItems.bootsObsidian)});
	}

	private static void initBlockRecipes()
	{
		GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockSteamBoiler), new Object[]{"PPP","PFP","PPP", 'F', Blocks.furnace, 'P', new ItemStack(ConfigItems.itemSheet, 1, 4)});
		GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockIntake), new Object[]{"PGP","G G","PGP", 'G', new ItemStack(ConfigItems.itemMachinePart, 1, 5), 'P', new ItemStack(ConfigItems.itemSheet, 1, 4)});
		GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockIntake), new Object[]{"UUU","UUU","UUU", 'U', new ItemStack(ConfigItems.itemResource, 1, 4)});
		//GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.blockCopperTank), new Object[]{"PPP","P P","PPP", 'P', new ItemStack(ConfigItems.itemSheet, 1, 1)});
	}

	private static void initOtherRecipes()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.itemResource, 4, 3), new Object[]{Blocks.obsidian});

		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemSteelParts, 1, 0), new Object[]{" P ", "PPP", " P ", 'P', new ItemStack(ConfigItems.itemSheet, 1, 6)});

		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemMachinePart, 1, 5), new Object[]{"P P", " P ", "P P", 'P', new ItemStack(ConfigItems.itemSheet, 1, 6)});

		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemCanisterSteam), new Object[]{"PPP","GGG","PPP", 'G', Blocks.glass_pane, 'P', new ItemStack(ConfigItems.itemSheet, 1, 4)});

		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemHammer), new Object[] { "III", " H ", " H ", 'I',
			new ItemStack(ConfigItems.itemIngot, 1, 7), 'H', Items.stick });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemRayGun), new Object[] { " BB", "EBE", "  B", 'E', Items.emerald, 'B',
				Items.iron_ingot });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemShrinkray), new Object[] { " BB", "EBE", "  B", 'E', Items.diamond, 'B',
				Items.iron_ingot });

		//Brass Powder
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ConfigItems.itemPowder, 3, 4), new Object[] {"dustCopper", "dustCopper", "dustZinc"}));
		//Bronze Powder
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ConfigItems.itemPowder, 3, 5), new Object[] {"dustCopper", "dustCopper", "dustTin"}));
		//Steel Powder
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemPowder, 2, 6), new Object[]{" C ", "CIC", " C ", 'C', new ItemStack(Items.coal), 'I', new ItemStack(Items.iron_ingot)});
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.itemMatch), new Object[]{new ItemStack(ConfigItems.itemResource, 1, 4), new ItemStack(Items.stick)});
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
		GameRegistry.addSmelting(Blocks.iron_block, new ItemStack(ConfigBlocks.blockMetal, 1, 7), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ConfigItems.itemResource, 1, 6), new ItemStack(ConfigItems.itemResource, 1,5), 0.5F);
	}

}