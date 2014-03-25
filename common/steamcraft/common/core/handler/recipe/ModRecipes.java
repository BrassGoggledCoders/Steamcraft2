package common.steamcraft.common.core.handler.recipe;

import java.util.Random;

import common.steamcraft.common.block.ModBlocks;
import common.steamcraft.common.block.ModOres;
import common.steamcraft.common.item.ModItems;
import common.steamcraft.common.item.ModTools;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

public class ModRecipes {

	public static void initRecipes() 
	{
		//Blocks
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockAluminum), new Object[] {"III", "III", "III", "I", new ItemStack(ModItems.ingotsMetal, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockCopper), new Object[] {"III", "III", "III", "I", new ItemStack(ModItems.ingotsMetal, 1, 1)});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockTin), new Object[] {"III", "III", "III", "I", new ItemStack(ModItems.ingotsMetal, 1, 2)});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockZinc), new Object[] {"III", "III", "III", "I", new ItemStack(ModItems.ingotsMetal, 1, 3)});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockCastIron), new Object[] {"III", "III", "III", "I", new ItemStack(ModItems.ingotsMetal, 1, 4)});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockBrass), new Object[] {"III", "III", "III", "I", new ItemStack(ModItems.ingotsMetal, 1, 5)});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockBronze), new Object[] {"III", "III", "III", "I", new ItemStack(ModItems.ingotsMetal, 1, 6)});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockSteel), new Object[] {"III", "III", "III", "I", new ItemStack(ModItems.ingotsMetal, 1, 7)});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockUranium), new Object[] {"III", "III", "III", "I", new ItemStack(ModItems.uraniumOre)});
		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockVolucite), new Object[] {"III", "III", "III", "I", new ItemStack(ModItems.etherium)});

		GameRegistry.addRecipe(new ItemStack(ModBlocks.blockObsidianTile), new Object[] {"XX", "XX", 'X', new ItemStack(Block.obsidian)});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.castIronLampOff), new Object[] {"ccc", "flf", " f ", 'c', new ItemStack(ModItems.ingotsMetal, 1, 4), 'f', ModBlocks.railingCastIron, 'l', ModItems.lightBulb});
		//Items
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotsMetal,9, 0), new Object[]{new ItemStack(ModBlocks.blockAluminum)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotsMetal,9, 1), new Object[]{new ItemStack(ModBlocks.blockCopper)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotsMetal,9, 2), new Object[]{new ItemStack(ModBlocks.blockTin)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotsMetal,9, 3), new Object[]{new ItemStack(ModBlocks.blockZinc)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotsMetal,9, 4), new Object[]{new ItemStack(ModBlocks.blockCastIron)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotsMetal,9, 5), new Object[]{new ItemStack(ModBlocks.blockBrass)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotsMetal,9, 6), new Object[]{new ItemStack(ModBlocks.blockBronze)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.ingotsMetal,9, 7), new Object[]{new ItemStack(ModBlocks.blockSteel)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.uraniumOre, 9), new Object[]{new ItemStack(ModBlocks.blockUranium)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.etherium, 9), new Object[]{new ItemStack(ModBlocks.blockVolucite)});
		
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.lightBulb), new Object[] {"ppp", "p p", "pcp", 'p', Block.thinGlass, 'c', ModItems.copperWire});
		//Duplicate Recipes are deliberate, until I find a better way to be able to use copper or iron partPiles ;)
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.brassWatch), new Object[] {"bgb", "gcg", "bgb", 'b', new ItemStack(ModItems.ingotsMetal, 1, 5), 'g', Block.thinGlass, 'c', new ItemStack(ModItems.pileParts, 1, 2)});
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.brassWatch), new Object[] {"bgb", "gcg", "bgb", 'b', new ItemStack(ModItems.ingotsMetal, 1, 5), 'g', Block.thinGlass, 'c', new ItemStack(ModItems.pileParts, 1, 2)});
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.emptyCanister), new Object[]{" a ", "a a", " a ", 'a', new ItemStack(ModItems.ingotsMetal, 1, 0)});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.drillBase), new Object[]{"   ", "ccc", " c ", 'c', new ItemStack(ModItems.ingotsMetal, 1, 4)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.guideBook), new Object[] {Item.book, ModItems.lightBulb});
		GameRegistry.addRecipe(new ItemStack(ModTools.chisel), new Object[] {"   ", " i ", " s ", 'i', new ItemStack(Item.ingotIron), 's', new ItemStack(Item.stick)});
			//Parts
		//There MUST be a neater/shorter way to do this???
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pileParts, 1, 0), new Object[] {new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsBrass,1,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pileParts, 1, 1),new Object[] {new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsSteel,1,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pileParts, 1, 2),new Object[] {new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsCopper,1,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pileParts, 1, 3),new Object[] {new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE),new ItemStack(ModItems.partsIron,1,OreDictionary.WILDCARD_VALUE)});
		
	}
	public static void initSmelting()
	{
		//Ores ---> Ingots
		GameRegistry.addSmelting(ModOres.oreAluminum.blockID, new ItemStack(ModItems.ingotsMetal, 1, 0), 0.7F);
		GameRegistry.addSmelting(ModOres.oreCopper.blockID, new ItemStack(ModItems.ingotsMetal, 1, 1), 0.7F);
		GameRegistry.addSmelting(ModOres.oreTin.blockID, new ItemStack(ModItems.ingotsMetal, 1, 2), 0.7F);
		GameRegistry.addSmelting(ModOres.oreZinc.blockID, new ItemStack(ModItems.ingotsMetal, 1, 3), 0.7F);
		//Misc
		GameRegistry.addSmelting(Block.blockIron.blockID, new ItemStack(ModBlocks.blockCastIron), 0.2F);
		GameRegistry.addSmelting(ModItems.uraniumOre.itemID, new ItemStack(ModItems.uraniumPellet), 0.5F);
	}

}
