package common.steamcraft.common.core.handler.recipe;

import java.util.Random;

import common.steamcraft.common.block.ModBlocks;
import common.steamcraft.common.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class ModRecipes {

	public static void initRecipes() 
	{
		//Blocks
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.castIronLampOff), new Object[] {"ccc", "flf", " f ", 'c', ModBlocks.blockCastIron, 'f', ModBlocks.railingCastIron, 'l', ModItems.lightBulb});
		//Items
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.lightBulb), new Object[] {"ppp", "p p", "pcp", 'p', Block.thinGlass, 'c', ModItems.copperWire});
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.guideBook), new Object[] {Item.book, ModItems.lightBulb});
			//Parts
		Random random = new Random();
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.pileParts, 1, random.nextInt(4)), new Object[] {ModItems.partsBrass, ModItems.partsBrass, ModItems.partsBrass, ModItems.partsBrass, ModItems.partsBrass, ModItems.partsBrass,ModItems.partsBrass, ModItems.partsBrass, ModItems.partsBrass});
	}

}
