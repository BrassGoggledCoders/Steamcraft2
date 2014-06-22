/*
 *
 */
package steamcraft.common.config;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import boilerplate.common.utils.recipe.RecipeUtils;
import cpw.mods.fml.common.registry.GameRegistry;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigRecipes.
 */
public class ConfigRecipes
{

	/**
	 * Inits the.
	 */
	public static void init()
	{
		initRecipes();
		initSmelting();
	}

	/**
	 * Inits the recipes.
	 */
	public static void initRecipes()
	{
		for (int meta = 0; meta < 8; meta++)
		{
			RecipeUtils.addMetalRecipes(ConfigBlocks.blockMetal, ConfigItems.itemIngot, ConfigItems.itemNugget, meta);
			GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.itemSheet, 1, meta), new Object[] {
					new ItemStack(ConfigItems.itemIngot, 1, meta), new ItemStack(ConfigItems.itemHammer, 1, OreDictionary.WILDCARD_VALUE) });
			GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.itemPowder, 2, meta), new Object[] {
					new ItemStack(ConfigBlocks.blockCustomOre, 1, meta), new ItemStack(ConfigItems.itemHammer, 1, OreDictionary.WILDCARD_VALUE) });
		}
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemHammer), new Object[] { "III", " H ", " H ", 'I',
				new ItemStack(ConfigItems.itemIngot, 1, 7), 'H', Items.stick });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemRayGun), new Object[] { " BB", "EBE", "  B", 'E', Items.emerald, 'B',
				Items.iron_ingot });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemShrinkray), new Object[] { " BB", "EBE", "  B", 'E', Items.diamond, 'B',
				Items.iron_ingot });

		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.itemPowder, 3, 4), new Object[] { new ItemStack(ConfigItems.itemPowder, 2, 1),
				new ItemStack(ConfigItems.itemPowder, 1, 3), new ItemStack(ConfigItems.itemPowder, 1, 3) });
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.itemPowder, 3, 5), new Object[] { new ItemStack(ConfigItems.itemPowder, 2, 1),
				new ItemStack(ConfigItems.itemPowder, 1, 2), new ItemStack(ConfigItems.itemPowder, 1, 2) });
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemPowder, 1, 6), new Object[]{" C ", "CIC", " C ", 'C', new ItemStack(Items.coal), 'I', new ItemStack(Items.iron_ingot)});

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

	/**
	 * Inits the smelting.
	 */
	public static void initSmelting()
	{
		// Ores ---> Ingots
		for (int meta = 0; meta < 5; meta++)
		{
			GameRegistry.addSmelting(new ItemStack(ConfigBlocks.blockCustomOre, 1, meta), new ItemStack(ConfigItems.itemIngot, 1, meta), 0.3F);
			GameRegistry.addSmelting(new ItemStack(ConfigItems.itemPowder, 1, meta), new ItemStack(ConfigItems.itemIngot, 1, meta), 0.3F);
		}
		// Misc
		GameRegistry.addSmelting(Blocks.iron_block, new ItemStack(ConfigBlocks.blockMetal, 1, 7), 0.2F);
		GameRegistry.addSmelting(new ItemStack(ConfigItems.itemResource, 1, 6), new ItemStack(ConfigItems.itemResource, 3, 7), 0.5F);
	}

}