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
import steamcraft.common.blocks.BlockBrassLeaves;
import steamcraft.common.blocks.BlockBrassLog;
import steamcraft.common.blocks.BlockCastIronFence;
import steamcraft.common.blocks.BlockCastIronGate;
import steamcraft.common.blocks.BlockCastIronLamp;
import steamcraft.common.blocks.BlockCastIronRailing;
import steamcraft.common.blocks.BlockCrystal;
import steamcraft.common.blocks.BlockCustomOre;
import steamcraft.common.blocks.BlockEngravedSolid;
import steamcraft.common.blocks.BlockFluidSteam;
import steamcraft.common.blocks.BlockLamp;
import steamcraft.common.blocks.BlockMetal;
import steamcraft.common.blocks.BlockSlate;
import steamcraft.common.blocks.FluidSteam;
import steamcraft.common.blocks.tiles.BlockArmorEditor;
import steamcraft.common.blocks.tiles.BlockCharger;
import steamcraft.common.blocks.tiles.BlockCopperPipe;
import steamcraft.common.blocks.tiles.BlockDropHammerAnvil;
import steamcraft.common.blocks.tiles.BlockIntake;
import steamcraft.common.blocks.tiles.BlockSteamBoiler;
import steamcraft.common.blocks.tiles.BlockTurbine;
import steamcraft.common.itemblocks.ItemBlockCustomOre;
import steamcraft.common.itemblocks.ItemBlockEngravedSolid;
import steamcraft.common.itemblocks.ItemBlockMetal;
import steamcraft.common.itemblocks.ItemBlockSlate;
import steamcraft.common.itemblocks.ItemBlockWithDesc;
import steamcraft.common.tiles.TileArmorEditor;
import steamcraft.common.tiles.TileCopperPipe;
import steamcraft.common.tiles.TileCrystal;
import steamcraft.common.tiles.TileDropHammer;
import steamcraft.common.tiles.TileIntake;
import steamcraft.common.tiles.TileSteamBoiler;
import steamcraft.common.tiles.TileTurbine;
import boilerplate.common.RegistryHelper;
import cpw.mods.fml.common.registry.GameRegistry;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigBlocks.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class ConfigBlocks
{

	/** The block custom ore. */
	public static Block blockCustomOre;

	/** The block engraved. */
	public static Block blockEngraved;

	/** The block cast iron fence. */
	public static Block blockCastIronFence, blockCastIronRailing;

	/** The block cast iron gate. */
	public static Block blockCastIronGate;

	/** The block crystal. */
	public static Block blockCrystal;

	/** The block cast iron lamp i. */
	public static Block blockCastIronLampI;

	/** The block cast iron lamp a. */
	public static Block blockCastIronLampA;

	/** The block metal. */
	public static Block blockMetal;

	/** The block hatch. */
	public static Block blockHatch;

	/** The block steam pipe. */
	public static Block blockSteamPipe;

	/** The block lamp i. */
	public static Block blockLampI;

	/** The block lamp a. */
	public static Block blockLampA;

	/** The block tea plant. */
	public static Block blockTeaPlant;

	/** The block smog. */
	public static Block blockSmog;

	/** The block slate. */
	public static Block blockSlate;

	/** The block steam boiler. */
	public static Block blockSteamBoiler;

	/** The block drop hammer frame. */
	public static Block blockDropHammerAnvil;

	/** The steam fluid. */
	public static Fluid steamFluid, steamcraftSteamFluid;

	/** The block steam. */
	public static Block blockSteam;

	/** The block obsidian tile stairs. */
	public static BlockStairs blockObsidianTileStairs;

	/** The block brass leaves. */
	public static Block blockBrassLog, blockBrassLeaves;

	/** The block lamp. */
	public static Block blockLamp;

	/** The block intake. */
	public static Block blockIntake, blockCopperPipe, blockArmorEditor, blockTurbine, blockCharger;

	// Render IDs
	/** The block lightning rod ri. */
	public static int blockCastIronLampRI, blockCrystalRI, blockDropHammerRI, blockHatchRI, blockLightningRodRI;

	/**
	 * Inits the.
	 */
	public static void init()
	{
		initializeBlocks();
		registerBlocks();
	}

	/**
	 * Initialize blocks.
	 */
	public static void initializeBlocks()
	{
		blockCustomOre = new BlockCustomOre();
		blockSlate = new BlockSlate().setBlockName("blockSlate");
		blockLamp = new BlockLamp();
		blockEngraved = new BlockEngravedSolid();
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
		//blockDropHammerFrame = new BlockDropHammerFrame(Material.iron).setBlockName("blockDropHammerFrame");
		blockMetal = new BlockMetal();
		blockBrassLog = new BlockBrassLog(Material.wood);
		blockBrassLeaves = new BlockBrassLeaves(Material.iron);
		blockIntake = new BlockIntake(Material.iron);
		blockCopperPipe = new BlockCopperPipe(Material.iron).setBlockName("blockCopperPipe");
		blockArmorEditor = new BlockArmorEditor(Material.iron);
		blockTurbine = new BlockTurbine(Material.iron).setBlockName("blockTurbine");
		blockCharger = new BlockCharger(Material.iron).setBlockName("blockCharger");

		steamFluid = new FluidSteam("steam");
		if (!FluidRegistry.registerFluid(steamFluid) && !FluidRegistry.isFluidRegistered("steam"))
		steamFluid = FluidRegistry.getFluid("steam");

		blockSteam = new BlockFluidSteam(steamFluid, Material.lava);
		/*steamcraftSteamFluid = new FluidSteam("steam");
		//if (!FluidRegistry.registerFluid(steamFluid) && !FluidRegistry.isFluidRegistered("steam"))
		FluidRegistry.registerFluid(steamcraftSteamFluid);
		steamFluid = FluidRegistry.getFluid("steam");
		if (steamFluid.getBlock() == null) {
			blockSteam = new BlockFluidSteam(steamFluid, Material.lava);
			steamFluid.setBlock(blockSteam);
		} else {
			blockSteam = steamFluid.getBlock();
		}*/
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
		GameRegistry.registerBlock(blockCastIronFence, "BlockCastIronFence");
		GameRegistry.registerBlock(blockCastIronGate, "BlockCastIronGate");
		// GameRegistry.registerBlock(blockCastIronLampI, "BlockCastIronLampI");
		// GameRegistry.registerBlock(blockCastIronLampA, "BlockCastIronLampA");
		// GameRegistry.registerBlock(blockFluidSteam, "BlockFluidSteam");
		//GameRegistry.registerBlock(blockDropHammerFrame, "BlockDropHammerFrame");
		RegistryHelper.registerContainerBlock(blockCrystal, TileCrystal.class, "BlockCrystal");
		RegistryHelper.registerContainerBlock(blockSteamBoiler, TileSteamBoiler.class, "BlockSteamBoiler");

		GameRegistry.registerBlock(blockIntake, ItemBlockWithDesc.class, "BlockIntake");
		GameRegistry.registerTileEntity(TileIntake.class, "TEIntake");

		RegistryHelper.registerContainerBlock(blockArmorEditor, TileArmorEditor.class, "BlockArmorEditor");
		RegistryHelper.registerContainerBlock(blockDropHammerAnvil, TileDropHammer.class, "BlockDropHammerAnvil");
		RegistryHelper.registerContainerBlock(blockCopperPipe, TileCopperPipe.class, "BlockCopperPipe");
		RegistryHelper.registerContainerBlock(blockTurbine, TileTurbine.class, "BlockTurbine");
		//RegistryHelper.registerContainerBlock(blockCharger, TileCharger.class, "BlockCharger");
		GameRegistry.registerBlock(blockMetal, ItemBlockMetal.class, "BlockMetal");

		GameRegistry.registerBlock(blockSteam, "blockSteam");
		// RegistryHelper.registerContainerBlock(blockHatch, TileHatch.class,
		// "BlockHatch");
	}
}
