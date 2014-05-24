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
import net.minecraftforge.fluids.Fluid;
import steamcraft.common.blocks.BlockCastIronFence;
import steamcraft.common.blocks.BlockCastIronGate;
import steamcraft.common.blocks.BlockCastIronLamp;
import steamcraft.common.blocks.BlockCosmeticSolid;
import steamcraft.common.blocks.BlockCosmeticSolidItem;
import steamcraft.common.blocks.BlockCrystal;
import steamcraft.common.blocks.BlockCustomOre;
import steamcraft.common.blocks.BlockCustomOreItem;
import steamcraft.common.blocks.BlockEngravedSolid;
import steamcraft.common.blocks.BlockEngravedSolidItem;
import steamcraft.common.blocks.BlockMetal;
import steamcraft.common.blocks.BlockMetalItem;
import steamcraft.common.blocks.BlockSlate;
import steamcraft.common.blocks.BlockSlateItem;
import steamcraft.common.blocks.machine.BlockSteamBoiler;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.TileCrystal;
import steamcraft.common.tiles.TileHatch;
import steamcraft.common.tiles.TileSteamBoiler;
import cpw.mods.fml.client.registry.RenderingRegistry;
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

	/** The block cosmetic. */
	public static Block blockCosmetic;

	/** The block engraved. */
	public static Block blockEngraved;

	/** The block cast iron fence. */
	public static Block blockCastIronFence;

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

	/** The block fluid steam. */
	public static Block blockFluidSteam;

	/** The block slate. */
	public static Block blockSlate;

	/** The block steam boiler. */
	public static Block blockSteamBoiler;

	/** The steam fluid. */
	public static Fluid steamFluid;

	/** The block steam. */
	public static Block blockSteam;

	/** The block obsidian tile stairs. */
	public static BlockStairs blockObsidianTileStairs;

	/** The block crystal ri. */
	public static int blockCrystalRI = RenderingRegistry
			.getNextAvailableRenderId();

	/** The block cast iron lamp ri. */
	public static int blockCastIronLampRI = RenderingRegistry
			.getNextAvailableRenderId();

	/** The block lightning rod ri. */
	public static int blockLightningRodRI = RenderingRegistry
			.getNextAvailableRenderId();

	/** The block smog ri. */
	public static int blockSmogRI = RenderingRegistry
			.getNextAvailableRenderId();

	/** The block hatch ri. */
	public static int blockHatchRI = RenderingRegistry
			.getNextAvailableRenderId();

	/**
	 * Inits the.
	 */
	public static void init()
	{
		initializeBlocks();
		registerBlocks();
		registerTileEntities();
	}

	/**
	 * Initialize blocks.
	 */
	public static void initializeBlocks()
	{
		blockCustomOre = new BlockCustomOre().setBlockName("blockCustomOre");
		blockCosmetic = new BlockCosmeticSolid()
				.setBlockName("blockCosmeticSolid");
		blockEngraved = new BlockEngravedSolid()
				.setBlockName("blockEngravedSolid");
		blockCastIronFence = new BlockCastIronFence()
				.setBlockName("blockCastIronFence");
		blockCastIronGate = new BlockCastIronGate()
				.setBlockName("blockCastIronGate");
		blockCrystal = new BlockCrystal().setBlockName("blockCrystal");
		blockCastIronLampI = new BlockCastIronLamp(false)
				.setBlockName("blockCastIronLampOff");
		blockCastIronLampA = new BlockCastIronLamp(true)
				.setBlockName("blockCastIronLampOn");
		blockSteamBoiler = new BlockSteamBoiler()
				.setBlockName("blockSteamBoiler");
		blockMetal = new BlockMetal().setBlockName("blockMetal");
		blockSlate = new BlockSlate().setUnlocalizedName("blockSlate");

		// blockFluidSteam = new BlockFluidSteam(steamFluid, Material.water);
		// Fluid steamFluid = new
		// Fluid("steam").setGaseous(true).setTemperature(700).setDensity(-100).setViscosity(500).setLuminosity(1);
		// FluidRegistry.registerFluid(steamFluid);
	}

	/**
	 * Register blocks.
	 */
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(blockCustomOre, BlockCustomOreItem.class,
				"BlockCustomOre"); // If you want different
									// hardness/resistances/light values,
		GameRegistry.registerBlock(blockCosmetic, BlockCosmeticSolidItem.class,
				"BlockCosmeticSolid"); // then use the actual methods and check
										// by metadata;
		GameRegistry.registerBlock(blockEngraved, BlockEngravedSolidItem.class,
				"BlockEngravedSolid"); // same goes for this too
		GameRegistry.registerBlock(blockCastIronFence, "BlockCastIronFence");
		GameRegistry.registerBlock(blockCastIronGate, "BlockCastIronGate");
		GameRegistry.registerBlock(blockCrystal, "BlockCrystal");
		// GameRegistry.registerBlock(blockCastIronLampI, "BlockCastIronLampI");
		// GameRegistry.registerBlock(blockCastIronLampA, "BlockCastIronLampA");
		// GameRegistry.registerBlock(blockFluidSteam, "BlockFluidSteam");
		GameRegistry.registerBlock(blockSteamBoiler, "BlockSteamBoiler");
		GameRegistry.registerBlock(blockMetal, BlockMetalItem.class,
				"BlockMetal");
		// GameRegistry.registerBlock(blockHatch, "BlockHatch");
		 GameRegistry.registerBlock(blockSlate,
		 BlockSlateItem.class,"BlockSlate");
	}

	/**
	 * Register tile entities.
	 */
	private static void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileCrystal.class, LibInfo.ID
				+ "TECrystal");
		GameRegistry
				.registerTileEntity(TileHatch.class, LibInfo.ID + "TEHatch");
		GameRegistry.registerTileEntity(TileSteamBoiler.class, LibInfo.ID
				+ "TESteamBoiler");
	}
}
