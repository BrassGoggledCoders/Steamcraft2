package common.steamcraft.common.core.handler.recipe;

import java.util.Random;

import common.steamcraft.common.block.ModBlocks;
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
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.blockObsidianTile), new Object[] {new ItemStack(Block.obsidian, 4)});
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.castIronLampOff), new Object[] {"ccc", "flf", " f ", 'c', ModBlocks.blockCastIron, 'f', ModBlocks.railingCastIron, 'l', ModItems.lightBulb});
		//Items
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.lightBulb), new Object[] {"ppp", "p p", "pcp", 'p', Block.thinGlass, 'c', ModItems.copperWire});
		//Duplicate Recipes are deliberate, until I find a better way to be able to use copper or iron partPiles ;)
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.brassWatch), new Object[] {"bgb", "gcg", "bgb", 'b', new ItemStack(ModItems.ingotsMetal, 1, 5), 'g', Block.thinGlass, 'c', new ItemStack(ModItems.pileParts, 1, 2)});
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.brassWatch), new Object[] {"bgb", "gcg", "bgb", 'b', new ItemStack(ModItems.ingotsMetal, 1, 5), 'g', Block.thinGlass, 'c', new ItemStack(ModItems.pileParts, 1, 2)});
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.emptyCanister), new Object[]{" a ", "a a", " a ", 'a', new ItemStack(ModItems.ingotsMetal, 1, 0)});
		//ModItems.reactorCore
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.guideBook), new Object[] {Item.book, ModItems.lightBulb});
		GameRegistry.addShapedRecipe(new ItemStack(ModTools.chisel), new Object[] {"   ", " i ", " s ", 'i', new ItemStack(Item.ingotIron), 's', new ItemStack(Item.stick)});
			//Parts
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pileParts, 1, 0), new Object[] {new ItemStack(ModItems.partsBrass,9,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pileParts, 1, 1),new Object[] {new ItemStack(ModItems.partsSteel,9,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pileParts, 1, 2),new Object[] {new ItemStack(ModItems.partsCopper,9,OreDictionary.WILDCARD_VALUE)});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pileParts, 1, 3),new Object[] {new ItemStack(ModItems.partsIron,9,OreDictionary.WILDCARD_VALUE)});
		//Smelting
		GameRegistry.addSmelting(ModItems.uraniumOre.itemID, new ItemStack(ModItems.uraniumPellet), 0.3F);
	}

}
