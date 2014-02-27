/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * File created @ [Jan 30, 2014, 6:11:43 PM]
 */
package common.steamcraft.common.block;

import net.minecraft.block.Block;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

import common.steamcraft.common.lib2.BlockIDs;
import common.steamcraft.common.lib2.BlockNames;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * 
 * @author MrArcane111
 *
 */
public class ModOres 
{
	public static Block oreBornite;
	public static Block oreBrimstone;
	public static Block orePhosphate;
	public static Block oreUranite;
	public static Block oreVolucite;
	public static Block oreAluminum, oreCopper, oreTin, oreZinc;
	
	public static void initOres()
	{
		oreBornite = new BlockOreMod(BlockIDs.oreBorniteID, "bornite").setUnlocalizedName(BlockNames.ORE_BORNITE);
		oreBrimstone = new BlockOreMod(BlockIDs.oreBrimstoneID, "brimstone").setUnlocalizedName(BlockNames.ORE_BRIMSTONE);
		orePhosphate = new BlockOreMod(BlockIDs.orePhosphateID, "phosphate").setUnlocalizedName(BlockNames.ORE_PHOSPHATE);
		oreUranite = new BlockUraniteOre(BlockIDs.oreUraniteID).setUnlocalizedName(BlockNames.ORE_URANITE);
		oreVolucite = new BlockOreMod(BlockIDs.oreVoluciteID, "volucite").setUnlocalizedName(BlockNames.ORE_VOLUCITE);
		oreAluminum = new BlockOreMod(BlockIDs.oreAluminumID, "aluminum").setUnlocalizedName(BlockNames.ORE_ALUMINUM);
		oreCopper = new BlockOreMod(BlockIDs.oreCopperID, "copper").setUnlocalizedName(BlockNames.ORE_COPPER);
		oreTin = new BlockOreMod(BlockIDs.oreTinID, "tin").setUnlocalizedName(BlockNames.ORE_TIN);
		oreZinc = new BlockOreMod(BlockIDs.oreZincID, "zinc").setUnlocalizedName(BlockNames.ORE_ZINC);
		registerOres();
		initOreHarvestLevels();
		registerOreDictionary();
	}
	
	public static void registerOres()
	{
		GameRegistry.registerBlock(oreBornite, BlockNames.ORE_BORNITE);
		GameRegistry.registerBlock(oreBrimstone, BlockNames.ORE_BRIMSTONE);
		GameRegistry.registerBlock(orePhosphate, BlockNames.ORE_PHOSPHATE);
		GameRegistry.registerBlock(oreUranite, BlockNames.ORE_URANITE);
		GameRegistry.registerBlock(oreVolucite, BlockNames.ORE_VOLUCITE);
		GameRegistry.registerBlock(oreAluminum, BlockNames.ORE_ALUMINUM);
		GameRegistry.registerBlock(oreCopper, BlockNames.ORE_COPPER);
		GameRegistry.registerBlock(oreTin, BlockNames.ORE_TIN);
		GameRegistry.registerBlock(oreZinc, BlockNames.ORE_ZINC);
	}
	
	public static void initOreHarvestLevels()
	{
		MinecraftForge.setBlockHarvestLevel(oreBornite, 0, "pickaxe", 1); 
		MinecraftForge.setBlockHarvestLevel(oreBrimstone, 0, "pickaxe", 1); 
		MinecraftForge.setBlockHarvestLevel(orePhosphate, 0, "pickaxe", 2); 
		MinecraftForge.setBlockHarvestLevel(oreUranite, 0, "pickaxe", 2); 
		MinecraftForge.setBlockHarvestLevel(oreVolucite, 0, "pickaxe", 4);
		MinecraftForge.setBlockHarvestLevel(oreAluminum, 0, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(oreCopper, 0, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(oreTin, 0, "pickaxe", 1);
		MinecraftForge.setBlockHarvestLevel(oreZinc, 0, "pickaxe", 1);
	}
	
	public static void registerOreDictionary()
	{
		OreDictionary.registerOre("oreBornite", oreBornite); 
		OreDictionary.registerOre("oreBrimstone", oreBrimstone); 
		OreDictionary.registerOre("orePhosphate", orePhosphate); 
		OreDictionary.registerOre("oreUranium", oreUranite); 
		OreDictionary.registerOre("oreVolucite", oreVolucite);
		OreDictionary.registerOre("oreAluminum", oreAluminum);
		OreDictionary.registerOre("oreCopper", oreCopper);
		OreDictionary.registerOre("oreTin", oreTin);
		OreDictionary.registerOre("oreZinc", oreZinc);
	}
}