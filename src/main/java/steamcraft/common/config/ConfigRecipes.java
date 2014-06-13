package steamcraft.common.config;

import boilerplate.common.utils.recipe.RecipeUtils;


public class ConfigRecipes {

	public static void init()
	{
		initRecipes();
		initSmelting();
	}
	public static void initRecipes()
	{
		//RecipeUtils.addMetalRecipes();
		//Blocks
		/*GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockAluminum), new Object[] {"III", "III", "III", 'I', new ItemStack(ConfigItems.ingotsMetal, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockCopper), new Object[] {"III", "III", "III", 'I', new ItemStack(ConfigItems.ingotsMetal, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockTin), new Object[] {"III", "III", "III", 'I', new ItemStack(ConfigItems.ingotsMetal, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockZinc), new Object[] {"III", "III", "III", 'I', new ItemStack(ConfigItems.ingotsMetal, 1, 3)});
		GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockCastIron), new Object[] {"III", "III", "III", 'I', new ItemStack(ConfigItems.ingotsMetal, 1, 4)});
		GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockBrass), new Object[] {"III", "III", "III", 'I', new ItemStack(ConfigItems.ingotsMetal, 1, 5)});
		GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockBronze), new Object[] {"III", "III", "III", 'I', new ItemStack(ConfigItems.ingotsMetal, 1, 6)});
		GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockSteel), new Object[] {"III", "III", "III", 'I', new ItemStack(ConfigItems.ingotsMetal, 1, 7)});
		GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockUranium), new Object[] {"III", "III", "III", 'I', new ItemStack(ConfigItems.uraniumOre)});
		GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockVolucite), new Object[] {"III", "III", "III", 'I', new ItemStack(ConfigItems.etherium)});

		GameRegistry.addRecipe(new ItemStack(ConfigBlocks.blockObsidianTile), new Object[] {"XX", "XX", 'X', new ItemStack(Block.obsidian)});
		GameRegistry.addShapedRecipe(new ItemStack(ConfigBlocks.castIronLampOff), new Object[] {"ccc", "flf", " f ", 'c', new ItemStack(ConfigItems.ingotsMetal, 1, 4), 'f', ConfigBlocks.railingCastIron, 'l', ConfigItems.lightBulb});
		//Items
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.ingotsMetal,9, 0), new Object[]{new ItemStack(ConfigBlocks.blockAluminum)});
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.ingotsMetal,9, 1), new Object[]{new ItemStack(ConfigBlocks.blockCopper)});
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.ingotsMetal,9, 2), new Object[]{new ItemStack(ConfigBlocks.blockTin)});
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.ingotsMetal,9, 3), new Object[]{new ItemStack(ConfigBlocks.blockZinc)});
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.ingotsMetal,9, 4), new Object[]{new ItemStack(ConfigBlocks.blockCastIron)});
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.ingotsMetal,9, 5), new Object[]{new ItemStack(ConfigBlocks.blockBrass)});
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.ingotsMetal,9, 6), new Object[]{new ItemStack(ConfigBlocks.blockBronze)});
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.ingotsMetal,9, 7), new Object[]{new ItemStack(ConfigBlocks.blockSteel)});
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.uraniumOre, 9), new Object[]{new ItemStack(ConfigBlocks.blockUranium)});
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.etherium, 9), new Object[]{new ItemStack(ConfigBlocks.blockVolucite)});

		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.lightBulb), new Object[] {"ppp", "p p", "pcp", 'p', Block.thinGlass, 'c', ConfigItems.copperWire});
		//Duplicate Recipes are deliberate, until I find a better way to be able to use copper or iron partPiles ;)
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.brassWatch), new Object[] {"bgb", "gcg", "bgb", 'b', new ItemStack(ConfigItems.ingotsMetal, 1, 5), 'g', Block.thinGlass, 'c', new ItemStack(ConfigItems.pileParts, 1, 2)});
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.brassWatch), new Object[] {"bgb", "gcg", "bgb", 'b', new ItemStack(ConfigItems.ingotsMetal, 1, 5), 'g', Block.thinGlass, 'c', new ItemStack(ConfigItems.pileParts, 1, 2)});
		GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.canisterEmpty), new Object[]{" a ", "a a", " a ", 'a', new ItemStack(ConfigItems.ingotsMetal, 1, 0)});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.drillBase), new Object[]{"   ", "ccc", " c ", 'c', new ItemStack(ConfigItems.ingotsMetal, 1, 4)});
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.guideBook), new Object[] {Item.book, ConfigItems.lightBulb});
		GameRegistry.addRecipe(new ItemStack(ModTools.chisel), new Object[] {"   ", " i ", " s ", 'i', new ItemStack(Item.ingotIron), 's', new ItemStack(Item.stick)});
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemPoppySeed), new Object[] {new ItemStack(Blocks.double_plant, 1, 2), new ItemStack(Blocks.double_plant, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(ConfigItems.itemPipe, 1, 1), new Object[] {new ItemStack(ConfigItems.itemPipe), new ItemStack(ConfigItems.itemPoppySeed)});
			//Parts
		//There MUST be a neater/shorter way to do this???
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.pileParts, 1, 0), new Object[] {new ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsBrass,1,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.pileParts, 1, 1),new Object[] {new ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsSteel,1,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.pileParts, 1, 2),new Object[] {new ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsCopper,1,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(ConfigItems.pileParts, 1, 3),new Object[] {new ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ConfigItems.partsIron,1,OreDictionary.WILDCARD_VALUE)});
		*/
		//GameRegistry.addShapedRecipe(new ItemStack(ConfigItems.itemRayGun), new Object[] {" BB", "EBE", "  B", 'E', Items.emerald, 'B', Items.iron_ingot});
	}
	public static void initSmelting()
	{
		//Ores ---> Ingots
		/*GameRegistry.addSmelting(ModOres.oreAluminum.blockID, new ItemStack(ConfigItems.ingotsMetal, 1, 0), 0.7F);
		GameRegistry.addSmelting(ModOres.oreCopper.blockID, new ItemStack(ConfigItems.ingotsMetal, 1, 1), 0.7F);
		GameRegistry.addSmelting(ModOres.oreTin.blockID, new ItemStack(ConfigItems.ingotsMetal, 1, 2), 0.7F);
		GameRegistry.addSmelting(ModOres.oreZinc.blockID, new ItemStack(ConfigItems.ingotsMetal, 1, 3), 0.7F);
		//Misc
		GameRegistry.addSmelting(Block.blockIron.blockID, new ItemStack(ConfigBlocks.blockCastIron), 0.2F);
		GameRegistry.addSmelting(ConfigItems.uraniumOre.itemID, new ItemStack(ConfigItems.uraniumPellet), 0.5F);*/
	}

}
