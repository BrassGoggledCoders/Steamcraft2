package common.steamcraft.mod.common.block;

import net.minecraft.block.Block;

import common.steamcraft.mod.common.block.tile.TileEntityChemicalFurnace;
import common.steamcraft.mod.common.block.tile.TileEntityCompressor;
import common.steamcraft.mod.common.block.tile.TileEntityGenerator;
import common.steamcraft.mod.common.block.tile.TileEntityNukeFurnace;
import common.steamcraft.mod.common.block.tile.TileEntitySteamFurnace;
import common.steamcraft.mod.common.lib.SC2_BlockIDs;
import common.steamcraft.mod.common.lib.SC2_CreativeTabs;

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
	public static Block generator;

	public static void initMachines() {
		steamOvenIdle = new BlockSteamFurnace(SC2_BlockIDs.steamOvenIdleID, false).setCreativeTab(SC2_CreativeTabs.tabSCBlocks);
		steamOvenActive = new BlockSteamFurnace(SC2_BlockIDs.steamOvenActiveID, true);
		chemOvenIdle = new BlockChemicalFurnace(SC2_BlockIDs.chemOvenIdleID, false).setCreativeTab(SC2_CreativeTabs.tabSCBlocks);
		chemOvenActive = new BlockChemicalFurnace(SC2_BlockIDs.chemOvenActiveID, true);
		nukeOvenIdle = new BlockNukeFurnace(SC2_BlockIDs.nukeOvenIdleID, false).setCreativeTab(SC2_CreativeTabs.tabSCBlocks);
		nukeOvenActive = new BlockNukeFurnace(SC2_BlockIDs.nukeOvenActiveID, true);
		compressor = new BlockCompressor(SC2_BlockIDs.compressorID).setCreativeTab(SC2_CreativeTabs.tabSCBlocks).setUnlocalizedName("compressorMachine");
		conveyor = new BlockConveyorBelt(SC2_BlockIDs.coveyorID);
		generator = new BlockGenerator(SC2_BlockIDs.generatorID).setCreativeTab(SC2_CreativeTabs.tabSCBlocks).setUnlocalizedName("simpleCoalGenerator");

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
		GameRegistry.registerBlock(generator, "simpleCoalGenerator");
	}

	public static void initMachineNames() {
		LanguageRegistry.addName(steamOvenIdle, "Steam Furnace");
		LanguageRegistry.addName(chemOvenIdle, "Chemical Furnace");
		LanguageRegistry.addName(nukeOvenIdle, "Nuke Furnace");
		LanguageRegistry.addName(compressor, "Compressor");
		LanguageRegistry.addName(conveyor, "Conveyor Belt");
		LanguageRegistry.addName(generator, "Simple Coal Generator");
	}
	
	public static void initMachineTileEntities() {
		GameRegistry.registerTileEntity(TileEntityNukeFurnace.class, "TileEntityNukeFurnace");
		GameRegistry.registerTileEntity(TileEntityChemicalFurnace.class, "TileEntityChemicalFurnace");
		GameRegistry.registerTileEntity(TileEntitySteamFurnace.class, "TileEntitySteamFurnace");
		GameRegistry.registerTileEntity(TileEntityCompressor.class, "TileEntityCompressor");
		GameRegistry.registerTileEntity(TileEntityGenerator.class, "TileEntityGenerator");
	}
}