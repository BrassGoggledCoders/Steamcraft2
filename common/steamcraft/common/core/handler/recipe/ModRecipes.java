package common.steamcraft.common.core.handler.recipe;

import common.steamcraft.common.block.ModBlocks;
import common.steamcraft.common.item.ModItems;
import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;

public class ModRecipes {

	public static void initRecipes() 
	{
		//Blocks
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.castIronLampOff), "ccc", "flf", " f ", 'c', ModBlocks.blockCastIron, 'f', ModBlocks.railingCastIron, 'l', ModItems.lightBulb);
		//Items
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.lightBulb), "ppp", "p p", "pcp", 'p', Block.thinGlass, 'c', ModItems.copperWire);
	}

}
