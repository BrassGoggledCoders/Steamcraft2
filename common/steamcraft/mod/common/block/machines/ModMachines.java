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
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 1, 2014, 6:02:89 PM]
 */
package common.steamcraft.mod.common.block.machines;

import net.minecraft.block.Block;

import common.steamcraft.mod.common.block.tile.TileEntityChemicalFurnace;
import common.steamcraft.mod.common.block.tile.TileEntityCoalGenerator;
import common.steamcraft.mod.common.block.tile.TileEntityCompressor;
import common.steamcraft.mod.common.block.tile.TileEntityNukeFurnace;
import common.steamcraft.mod.common.block.tile.TileEntitySteamFurnace;
import common.steamcraft.mod.common.lib.BlockIDs;
import common.steamcraft.mod.common.lib.CreativeTabsMod;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ModMachines {
	public static Block steamOvenIdle;
	public static Block steamOvenActive;
	public static Block chemOvenIdle;
	public static Block chemOvenActive;
	public static Block nukeOvenIdle;
	public static Block nukeOvenActive;
	public static Block compressor;
	public static Block conveyor;
	public static Block coalGenerator;

	public static void initMachines() {
		steamOvenIdle = new BlockSteamFurnace(BlockIDs.steamOvenIdleID, false).setCreativeTab(CreativeTabsMod.tabSCBlocks);
		steamOvenActive = new BlockSteamFurnace(BlockIDs.steamOvenActiveID, true);
		chemOvenIdle = new BlockChemicalFurnace(BlockIDs.chemOvenIdleID, false).setCreativeTab(CreativeTabsMod.tabSCBlocks);
		chemOvenActive = new BlockChemicalFurnace(BlockIDs.chemOvenActiveID, true);
		nukeOvenIdle = new BlockNukeFurnace(BlockIDs.nukeOvenIdleID, false).setCreativeTab(CreativeTabsMod.tabSCBlocks);
		nukeOvenActive = new BlockNukeFurnace(BlockIDs.nukeOvenActiveID, true);
		compressor = new BlockCompressor(BlockIDs.compressorID).setCreativeTab(CreativeTabsMod.tabSCBlocks).setUnlocalizedName("compressorMachine");
		conveyor = new BlockConveyorBelt(BlockIDs.coveyorID);
		coalGenerator = new BlockCoalGenerator(BlockIDs.generatorID).setCreativeTab(CreativeTabsMod.tabSCBlocks).setUnlocalizedName("simpleCoalGenerator");

		registerMachines();
		initMachineNames();
		initMachineTileEntities();
	}

	public static void registerMachines() {
		GameRegistry.registerBlock(steamOvenIdle, "steamovenidle");
		GameRegistry.registerBlock(steamOvenActive, "steamovenactive");
		GameRegistry.registerBlock(chemOvenIdle, "chemovenidle");
		GameRegistry.registerBlock(chemOvenActive, "chemovenactive");
		GameRegistry.registerBlock(nukeOvenIdle, "nukeovenidle");
		GameRegistry.registerBlock(nukeOvenActive, "nukeovenactive");
		GameRegistry.registerBlock(compressor, "compressorMachine");
		GameRegistry.registerBlock(conveyor, "conveyor");
		GameRegistry.registerBlock(coalGenerator, "simpleCoalGenerator");
	}

	public static void initMachineNames() {
		LanguageRegistry.addName(steamOvenIdle, "Steam Furnace");
		LanguageRegistry.addName(chemOvenIdle, "Chemical Furnace");
		LanguageRegistry.addName(nukeOvenIdle, "Nuke Furnace");
		LanguageRegistry.addName(compressor, "Compressor");
		LanguageRegistry.addName(conveyor, "Conveyor Belt");
		LanguageRegistry.addName(coalGenerator, "Simple Coal Generator");
	}
	
	public static void initMachineTileEntities() {
		GameRegistry.registerTileEntity(TileEntityNukeFurnace.class, "TileEntityNukeFurnace");
		GameRegistry.registerTileEntity(TileEntityChemicalFurnace.class, "TileEntityChemicalFurnace");
		GameRegistry.registerTileEntity(TileEntitySteamFurnace.class, "TileEntitySteamFurnace");
		GameRegistry.registerTileEntity(TileEntityCompressor.class, "TileEntityCompressor");
		GameRegistry.registerTileEntity(TileEntityCoalGenerator.class, "TileEntityCoalGenerator");
	}
}
