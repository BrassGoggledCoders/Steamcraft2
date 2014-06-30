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
 * File created @ [Mar 12, 2014, 5:03:06 PM]
 */
package steamcraft.common.config;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import steamcraft.common.blocks.BaseBlock;
import steamcraft.common.blocks.BlockBrassLeaves;
import steamcraft.common.blocks.BlockBrassLog;
import steamcraft.common.blocks.BlockCastIronFence;
import steamcraft.common.blocks.BlockCastIronGate;
import steamcraft.common.blocks.BlockCastIronLamp;
import steamcraft.common.blocks.BlockCastIronRailing;
import steamcraft.common.blocks.BlockCrystal;
import steamcraft.common.blocks.BlockCustomOre;
import steamcraft.common.blocks.BlockEngravedSolid;
import steamcraft.common.blocks.BlockEngravedVanilla;
import steamcraft.common.blocks.BlockEtherium;
import steamcraft.common.blocks.BlockFluidSteam;
import steamcraft.common.blocks.BlockLamp;
import steamcraft.common.blocks.BlockMetal;
import steamcraft.common.blocks.BlockSlate;
import steamcraft.common.blocks.BlockUranium;
import steamcraft.common.blocks.FluidSteam;
import steamcraft.common.blocks.tiles.BlockArmorEditor;
import steamcraft.common.blocks.tiles.BlockBloomery;
import steamcraft.common.blocks.tiles.BlockCharger;
import steamcraft.common.blocks.tiles.BlockCopperPipe;
import steamcraft.common.blocks.tiles.BlockDropHammerAnvil;
import steamcraft.common.blocks.tiles.BlockIntake;
import steamcraft.common.blocks.tiles.BlockSteamBoiler;
import steamcraft.common.blocks.tiles.BlockTurbine;
import steamcraft.common.itemblocks.ItemBlockCustomOre;
import steamcraft.common.itemblocks.ItemBlockEngravedSolid;
import steamcraft.common.itemblocks.ItemBlockEngravedVanilla;
import steamcraft.common.itemblocks.ItemBlockMetal;
import steamcraft.common.itemblocks.ItemBlockSlate;
import steamcraft.common.tiles.TileArmorEditor;
import steamcraft.common.tiles.TileBloomery;
import steamcraft.common.tiles.TileCopperPipe;
import steamcraft.common.tiles.TileCrystal;
import steamcraft.common.tiles.TileDropHammer;
import steamcraft.common.tiles.TileIntake;
import steamcraft.common.tiles.TileSteamBoiler;
import steamcraft.common.tiles.TileTurbine;
import boilerplate.common.RegistryHelper;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Surseance (Johnny Eatmon)
 * 
 */
public class ConfigBlocks
{
	public static Block blockCustomOre;

	public static Block blockEngraved, blockEngravedVanilla;

	public static Block blockCastIronFence, blockCastIronRailing;

	public static Block blockCastIronGate;

	public static Block blockCrystal;

	public static Block blockCastIronLampI;
	public static Block blockCastIronLampA;

	public static Block blockMetal, blockUranium, blockEtherium;

	public static Block blockHatch;

	public static Block blockSteamPipe, blockCopperWire, blockCopperTank;

	public static Block blockLampI;
	public static Block blockLampA;

	public static Block blockTeaPlant;

	public static Block blockSmog;

	public static Block blockSlate;

	public static Block blockSteamBoiler;

	public static Block blockDropHammerAnvil;

	public static Fluid steamFluid, steamcraftSteamFluid;

	public static Block blockSteam;

	public static BlockStairs blockObsidianTileStairs;

	public static Block blockBrassLog, blockBrassLeaves;

	public static Block blockLamp;

	public static Block blockIntake, blockCopperPipe, blockArmorEditor, blockTurbine, blockCharger, blockBloomery;


	public static void init()
	{
		initializeBlocks();
		registerBlocks();
	}

	public static void initializeBlocks()
	{
		blockCustomOre = new BlockCustomOre();
		blockSlate = new BlockSlate().setBlockName("blockSlate");
		blockLamp = new BlockLamp();
		blockEngraved = new BlockEngravedSolid();
		blockEngravedVanilla = new BlockEngravedVanilla();
		blockCastIronFence = new BlockCastIronFence();
		blockCastIronGate = new BlockCastIronGate();
		blockCastIronRailing = new BlockCastIronRailing(Material.iron);
		blockCrystal = new BlockCrystal();
		blockCastIronLampI = new BlockCastIronLamp(false).setBlockName("blockCastIronLampOff");
		blockCastIronLampA = new BlockCastIronLamp(true).setBlockName("blockCastIronLampOn");
		blockSteamBoiler = new BlockSteamBoiler().setBlockName("blockSteamBoiler");
		// blockDropHammer = new
		// BlockDropHammer(Material.anvil).setBlockName("blockDropHammer");
		blockDropHammerAnvil = new BlockDropHammerAnvil(Material.anvil).setBlockName("blockDropHammerAnvil");
		// blockDropHammerFrame = new
		// BlockDropHammerFrame(Material.iron).setBlockName("blockDropHammerFrame");
		blockBloomery = new BlockBloomery(Material.rock).setBlockName("blockBloomery");
		blockMetal = new BlockMetal();
		blockUranium = new BlockUranium(Material.iron);
		blockEtherium = new BlockEtherium(Material.iron).setBlockName("blockEtherium").setResistance(-1);
		blockBrassLog = new BlockBrassLog(Material.wood);
		blockBrassLeaves = new BlockBrassLeaves(Material.iron);
		blockIntake = new BlockIntake(Material.iron);
		blockCopperPipe = new BlockCopperPipe(Material.iron).setBlockName("blockCopperPipe");
		blockArmorEditor = new BlockArmorEditor(Material.iron);
		blockTurbine = new BlockTurbine(Material.iron).setBlockName("blockTurbine");
		blockCharger = new BlockCharger(Material.iron).setBlockName("blockCharger");
		blockCopperTank = new BaseBlock(Material.iron).setBlockName("blockCopperTank");
		blockCopperWire = new BaseBlock(Material.iron).setBlockName("blockCopperWire");

		steamFluid = new FluidSteam("steam");
		if (!FluidRegistry.registerFluid(steamFluid) && !FluidRegistry.isFluidRegistered("steam"))
			steamFluid = FluidRegistry.getFluid("steam");

		blockSteam = new BlockFluidSteam(steamFluid, Material.lava);
	}

	/**
	 * Register blocks.
	 */
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(blockCustomOre, ItemBlockCustomOre.class, "BlockCustomOre");
		GameRegistry.registerBlock(blockSlate, ItemBlockSlate.class, "BlockSlate");
		GameRegistry.registerBlock(blockLamp, "BlockLamp");
		GameRegistry.registerBlock(blockBrassLeaves, "BlockBrassLeaves");
		GameRegistry.registerBlock(blockBrassLog, "BlockBrassLog");
		GameRegistry.registerBlock(blockEngraved, ItemBlockEngravedSolid.class, "BlockEngravedSolid");
		GameRegistry.registerBlock(blockEngravedVanilla, ItemBlockEngravedVanilla.class, "BlockEngravedVanilla");
		GameRegistry.registerBlock(blockCastIronFence, "BlockCastIronFence");
		GameRegistry.registerBlock(blockCastIronGate, "BlockCastIronGate");
		GameRegistry.registerBlock(blockMetal, ItemBlockMetal.class, "BlockMetal");
		GameRegistry.registerBlock(blockUranium, "BlockUranium");
		GameRegistry.registerBlock(blockEtherium, "BlockEtherium");
		GameRegistry.registerBlock(blockSteam, "blockSteam");
		// GameRegistry.registerBlock(blockCastIronLampI, "BlockCastIronLampI");
		// GameRegistry.registerBlock(blockCastIronLampA, "BlockCastIronLampA");
		// GameRegistry.registerBlock(blockFuidSteam, "BlockFluidSteam");
		// GameRegistry.registerBlock(blockDropHammerFrame,
		// "BlockDropHammerFrame");

		RegistryHelper.registerContainerBlock(blockCrystal, TileCrystal.class, "BlockCrystal");
		RegistryHelper.registerContainerBlockWithDesc(blockSteamBoiler, TileSteamBoiler.class, "BlockSteamBoiler");
		RegistryHelper.registerContainerBlockWithDesc(blockIntake,TileIntake.class, "BlockIntake");
		RegistryHelper.registerContainerBlock(blockArmorEditor, TileArmorEditor.class, "BlockArmorEditor");
		RegistryHelper.registerContainerBlock(blockDropHammerAnvil, TileDropHammer.class, "BlockDropHammerAnvil");
		RegistryHelper.registerContainerBlock(blockCopperPipe, TileCopperPipe.class, "BlockCopperPipe");
		RegistryHelper.registerContainerBlockWithDesc(blockBloomery, TileBloomery.class, "BlockBloomery");
		GameRegistry.registerBlock(blockCopperTank, "BlockCopperTank");
		//GameRegistry.registerBlock(blockCopperWire, "BlockCopperWire");
		RegistryHelper.registerContainerBlock(blockTurbine, TileTurbine.class, "BlockTurbine");
		// RegistryHelper.registerContainerBlock(blockCharger,
		// TileCharger.class, "BlockCharger");
		// RegistryHelper.registerContainerBlock(blockHatch, TileHatch.class,
		// "BlockHatch");
	}
}
