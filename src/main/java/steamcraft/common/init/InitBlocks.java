/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package steamcraft.common.init;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;

import cpw.mods.fml.common.registry.GameRegistry;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

import boilerplate.common.baseclasses.BaseItemBlockWithMetadata;
import boilerplate.common.compathandler.FMPCompatHandler;
import boilerplate.common.utils.helpers.RegistryHelper;
import org.apache.commons.lang3.text.WordUtils;
import steamcraft.client.renderers.tile.TileHatchRenderer.TileHatch;
import steamcraft.common.Steamcraft;
import steamcraft.common.blocks.BaseBlock;
import steamcraft.common.blocks.BlockBoulder;
import steamcraft.common.blocks.BlockBrassLeaves;
import steamcraft.common.blocks.BlockBrassLog;
import steamcraft.common.blocks.BlockCastIronFence;
import steamcraft.common.blocks.BlockCastIronGate;
import steamcraft.common.blocks.BlockCastIronLamp;
import steamcraft.common.blocks.BlockCastIronRailing;
import steamcraft.common.blocks.BlockCompressedStone;
import steamcraft.common.blocks.BlockCongealedSlime;
import steamcraft.common.blocks.BlockCrystal;
import steamcraft.common.blocks.BlockCustomDoubleSlab;
import steamcraft.common.blocks.BlockCustomFence;
import steamcraft.common.blocks.BlockCustomLeaves;
import steamcraft.common.blocks.BlockCustomLog;
import steamcraft.common.blocks.BlockCustomMushroom;
import steamcraft.common.blocks.BlockCustomSlab;
import steamcraft.common.blocks.BlockCustomStairs;
import steamcraft.common.blocks.BlockCustomWall;
import steamcraft.common.blocks.BlockDynamite;
import steamcraft.common.blocks.BlockEngravedSolid;
import steamcraft.common.blocks.BlockEngravedVanilla;
import steamcraft.common.blocks.BlockEtherium;
import steamcraft.common.blocks.BlockFissurePortal;
import steamcraft.common.blocks.BlockHatch;
import steamcraft.common.blocks.BlockInfestedDirt;
import steamcraft.common.blocks.BlockInfestedGrass;
import steamcraft.common.blocks.BlockInvertedCastIronLamp;
import steamcraft.common.blocks.BlockLamp;
import steamcraft.common.blocks.BlockLeafPile;
import steamcraft.common.blocks.BlockMeltingIce;
import steamcraft.common.blocks.BlockMetal;
import steamcraft.common.blocks.BlockMotionSensor;
import steamcraft.common.blocks.BlockMud;
import steamcraft.common.blocks.BlockPlankStack;
import steamcraft.common.blocks.BlockPolishedPlanks;
import steamcraft.common.blocks.BlockSiren;
import steamcraft.common.blocks.BlockSkyrail;
import steamcraft.common.blocks.BlockSlate;
import steamcraft.common.blocks.BlockSpiderEgg;
import steamcraft.common.blocks.BlockSteamcraftFluid;
import steamcraft.common.blocks.BlockSteamcraftOre;
import steamcraft.common.blocks.BlockStonebrickWall;
import steamcraft.common.blocks.BlockTeaPlant;
import steamcraft.common.blocks.BlockThin;
import steamcraft.common.blocks.BlockTimeBomb;
import steamcraft.common.blocks.BlockTintedRock;
import steamcraft.common.blocks.BlockTrunk;
import steamcraft.common.blocks.BlockUranium;
import steamcraft.common.blocks.machines.BlockArmorEditor;
import steamcraft.common.blocks.machines.BlockBattery;
import steamcraft.common.blocks.machines.BlockBloomery;
import steamcraft.common.blocks.machines.BlockCapacitor;
import steamcraft.common.blocks.machines.BlockCharger;
import steamcraft.common.blocks.machines.BlockCopperPipe;
import steamcraft.common.blocks.machines.BlockCopperTank;
import steamcraft.common.blocks.machines.BlockCopperWire;
import steamcraft.common.blocks.machines.BlockDropHammerAnvil;
import steamcraft.common.blocks.machines.BlockIntake;
import steamcraft.common.blocks.machines.BlockLightningRod;
import steamcraft.common.blocks.machines.BlockNuclearBoiler;
import steamcraft.common.blocks.machines.BlockRefinery;
import steamcraft.common.blocks.machines.BlockSawmill;
import steamcraft.common.blocks.machines.BlockStasisField;
import steamcraft.common.blocks.machines.BlockSteamBoiler;
import steamcraft.common.blocks.machines.BlockSteelPipe;
import steamcraft.common.blocks.machines.BlockSteelWire;
import steamcraft.common.blocks.machines.BlockTeslaCoil;
import steamcraft.common.blocks.machines.BlockTurbine;
import steamcraft.common.tiles.EmptyTiles.TileCastIronLamp;
import steamcraft.common.tiles.EmptyTiles.TileCrystal;
import steamcraft.common.tiles.TileArmorEditor;
import steamcraft.common.tiles.TileBloomery;
import steamcraft.common.tiles.TileCopperPipe;
import steamcraft.common.tiles.TileCopperTank;
import steamcraft.common.tiles.TileIntake;
import steamcraft.common.tiles.TileNuclearBoiler;
import steamcraft.common.tiles.TileSteamBoiler;
import steamcraft.common.tiles.TileSteelPipe;
import steamcraft.common.tiles.TileTimeBomb;
import steamcraft.common.tiles.energy.TileBattery;
import steamcraft.common.tiles.energy.TileCapacitor;
import steamcraft.common.tiles.energy.TileCharger;
import steamcraft.common.tiles.energy.TileCopperWire;
import steamcraft.common.tiles.energy.TileLightningRod;
import steamcraft.common.tiles.energy.TileStasisField;
import steamcraft.common.tiles.energy.TileSteelWire;
import steamcraft.common.tiles.energy.TileTeslaCoil;
import steamcraft.common.tiles.energy.TileTurbine;

/**
 * @author Surseance
 *
 */
public class InitBlocks
{
	public static Block blockArmorEditor;
	public static Block blockBloomery;

	public static Block blockBoilingMud;

	public static Block blockBoilingWater;
	public static Block blockBoulder, blockSpiderEgg, blockMushroom;

	// Wood
	public static Block blockBrassLog, blockBrassLeaves;

	public static Block blockBrassPlaque;
	public static Block blockCastIronFence, blockCastIronGate, blockCastIronRailing;
	// Cast Iron
	public static Block blockCastIronLamp, blockCastIronLampOn, blockInvertedCastIronLamp, blockInvertedCastIronLampOff;

	public static Block blockCongealedSlime;
	public static Block blockCopperPipe, blockSteelPipe, blockCopperWire, blockSteelWire;

	public static Block blockCopperTank;

	public static Block blockCrystal;

	/* Ores */
	public static Block blockCustomOre;
	public static Block blockDropHammerAnvil;

	public static Block blockDynamite;

	// Engraved Blocks
	public static Block blockEngraved, blockEngravedVanilla;

	/* Fluids */

	public static Block blockFissurePortal, blockCompressedStone;
	public static Block blockFlesh;

	public static Block blockInfestedGrass, blockInfestedDirt;
	public static Block blockLamp, blockLampOn;

	public static Block blockLightningRod, blockTeslaCoil;
	public static Block blockMangroveFence, blockMangroveDoor, blockMangroveSlab, blockMangroveDoubleSlab, blockMangroveStairs;

	public static Block blockMeltingIce, blockGhostIce;
	// Metals
	public static Block blockMetal, blockUranium, blockEtherium;

	public static Block blockMoltenZinc, blockMoltenBrass;
	public static Block blockPath, blockMud, blockLeafCover, blockMoss;

	/* Others */

	public static Block blockPetrifiedFence, blockPetrifiedDoor, blockPetrifiedSlab, blockPetrifiedDoubleSlab, blockPetrifiedStairs;

	public static Block blockPlankStack;

	public static Block blockPolishedPlanks;

	public static Block blockPress, blockSteamHammer, blockGrindstone, blockWireMill;

	public static Block blockRedwoodFence, blockRedwoodDoor, blockRedwoodSlab, blockRedwoodDoubleSlab, blockRedwoodStairs;

	public static Block blockRedwoodLog, blockRedwoodLeaves, blockMangroveLog, blockMangroveLeaves, blockRedwoodPlanks, blockMangrovePlanks,
			blockWillowLog, blockWillowLeaves, blockWillowPlanks, blockPetrifiedLog, blockDeadLeaves, blockPetrifiedPlanks;

	public static Block blockRefinery;

	public static Block blockSaw;

	public static Block blockSkyrail;

	// TODO Meta?
	public static Block blockSlate, blockRawBlueSlateStairs, blockRawBlackSlateStairs, blockRawRedSlateStairs, blockCobbleBlueSlateStairs,
			blockCobbleBlackSlateStairs, blockCobbleRedSlateStairs, blockBrickBlueSlateStairs, blockBrickBlackSlateStairs, blockBrickRedSlateStairs;

	public static Block blockStandardSiren, blockStandardSirenOn, blockAllClearSiren, blockAllClearSirenOn, blockIntruderSiren, blockIntruderSirenOn,
			blockNuclearSiren, blockNuclearSirenOn, blockMotionSensor, blockMotionSensorOn;

	public static Block blockStasisField;

	public static Block blockSteam;

	/* Machines */
	public static Block blockSteamBoiler, blockNuclearBoiler, blockIntake, blockTurbine, blockBattery, blockCharger, blockCapacitor;
	public static Block blockStonebrickWall, blockStoneslabWall, blockBrickWall;
	public static Block blockTeaPlant, blockHatch;
	public static Block blockTimeBomb;

	public static Block blockTintedRock;

	public static Block blockTrunk;

	public static Block blockWhaleOil;

	public static Block blockWillowFence, blockWillowDoor, blockWillowSlab, blockWillowDoubleSlab, blockWillowStairs;

	public static Fluid boilingMudFluid;

	public static Fluid boilingWaterFluid;

	public static Fluid moltenZincFluid, moltenBrassFluid;

	public static Fluid steamFluid;

	public static Fluid whaleOilFluid;

	public static void init()
	{
		initializeDecorative();
		initializeFluids();
		initializeMachines();
		initializeOres();
		initializeOthers();
		initializeTerrain();
	}

	private static void initializeDecorative()
	{
		// Engraved Blocks
		blockEngraved = new BlockEngravedSolid().setBlockName("blockEngravedSolid");
		blockEngravedVanilla = new BlockEngravedVanilla().setBlockName("blockEngravedVanilla");

		registerBlock(blockEngraved, BaseItemBlockWithMetadata.class, "BlockEngravedSolid", 10);
		registerBlock(blockEngravedVanilla, BaseItemBlockWithMetadata.class, "BlockEngravedVanilla", 5);

		// Cast Iron
		blockCastIronLamp = new BlockCastIronLamp(false).setBlockName("blockCastIronLamp");
		blockCastIronLampOn = new BlockCastIronLamp(true).setBlockName("blockCastIronLampOn");

		blockInvertedCastIronLamp = new BlockInvertedCastIronLamp(true).setBlockName("blockInvertedCastIronLamp");
		blockInvertedCastIronLampOff = new BlockInvertedCastIronLamp(false).setBlockName("blockInvertedCastIronLampOff");

		RegistryHelper.registerContainerBlock(blockCastIronLamp, TileCastIronLamp.class, "BlockCastIronLamp");
		RegistryHelper.registerContainerBlock(blockCastIronLampOn, TileCastIronLamp.class, "BlockCastIronLampOn");

		RegistryHelper.registerContainerBlock(blockInvertedCastIronLamp, TileCastIronLamp.class, "BlockInvertedCastIronLamp");
		RegistryHelper.registerContainerBlock(blockInvertedCastIronLampOff, TileCastIronLamp.class, "BlockInvertedCastIronLampOff");

		blockCastIronFence = new BlockCastIronFence().setBlockName("blockCastIronFence");
		blockCastIronGate = new BlockCastIronGate().setBlockName("blockCastIronGate");
		blockCastIronRailing = new BlockCastIronRailing(Material.anvil).setBlockName("blockCastIronRailing");

		registerBlock(blockCastIronFence, "BlockCastIronFence");
		registerBlock(blockCastIronGate, "BlockCastIronGate");
		registerBlock(blockCastIronRailing, "BlockCastIronRailing");

		blockLamp = new BlockLamp(false).setBlockName("blockLamp");
		blockLampOn = new BlockLamp(true).setBlockName("blockLamp");

		registerBlock(blockLamp, "BlockLamp");
		registerBlock(blockLampOn, "BlockLampOn");

		blockStonebrickWall = new BlockStonebrickWall().setBlockName("blockStonebrickWall");
		registerBlock(blockStonebrickWall, "BlockStonebrickWall");
		blockStoneslabWall = new BlockCustomWall(Blocks.stone_slab, 0).setBlockName("blockStoneslabWall");
		registerBlock(blockStoneslabWall, "BlockStoneslabWall");
		blockBrickWall = new BlockCustomWall(Blocks.brick_block, 0).setBlockName("blockBrickWall");
		registerBlock(blockBrickWall, "BlockBrickWall");
	}

	private static void initializeFluids()
	{
		registerFluid("steam", steamFluid, Material.lava, (BlockSteamcraftFluid) blockSteam, true, 110, -100, 500, 12);
		registerFluid("boilingwater", boilingWaterFluid, Material.lava, (BlockSteamcraftFluid) blockBoilingWater, false, 373, 900, 800, 0);
		registerFluid("boilingmud", boilingMudFluid, Material.lava, (BlockSteamcraftFluid) blockBoilingMud, false, 373, 900, 800, 0);
	}

	public static void registerFluid(String fluidName, Fluid fluid, Material material, BlockSteamcraftFluid fluidBlock, boolean isGaseous, int temp,
			int density, int viscosity, int luminosity)
	{
		fluid = new Fluid(fluidName).setUnlocalizedName(fluidName + "Fluid").setDensity(density).setTemperature(temp).setViscosity(viscosity)
				.setLuminosity(luminosity).setGaseous(isGaseous);
		if (!FluidRegistry.isFluidRegistered(fluidName))
			FluidRegistry.registerFluid(fluid);
		fluidBlock = (BlockSteamcraftFluid) new BlockSteamcraftFluid(fluid, material, fluidName).setBlockName(fluidName + "FluidBlock");
		registerBlock(fluidBlock, "Block" + WordUtils.capitalize(fluidName));
		if (fluid.getBlock() == null)
			fluid.setBlock(fluidBlock);
		else
			fluidBlock.dontOverwriteIcons();

	}

	private static void initializeMachines()
	{
		// Steam related
		blockSteamBoiler = new BlockSteamBoiler().setBlockName("blockSteamBoiler");
		blockNuclearBoiler = new BlockNuclearBoiler().setBlockName("blockNuclearBoiler");
		blockIntake = new BlockIntake().setBlockName("blockIntake");

		RegistryHelper.registerContainerBlockWithDesc(blockSteamBoiler, TileSteamBoiler.class, "BlockSteamBoiler");
		RegistryHelper.registerContainerBlockWithDesc(blockNuclearBoiler, TileNuclearBoiler.class, "BlockNuclearBoiler");
		RegistryHelper.registerContainerBlockWithDesc(blockIntake, TileIntake.class, "BlockIntake");

		// Energy related
		blockTurbine = new BlockTurbine().setBlockName("blockTurbine");
		blockBattery = new BlockBattery().setBlockName("blockBattery");
		blockCapacitor = new BlockCapacitor().setBlockName("blockCapacitor");
		blockLightningRod = new BlockLightningRod(Material.iron).setBlockName("blockLightningRod");
		blockTeslaCoil = new BlockTeslaCoil(Material.iron).setBlockName("blockTeslaCoil");
		blockCharger = new BlockCharger().setBlockName("blockCharger");
		blockSaw = new BlockSawmill(Material.anvil).setBlockName("blockSawmill");

		RegistryHelper.registerContainerBlockWithDesc(blockTurbine, TileTurbine.class, "BlockTurbine");
		RegistryHelper.registerContainerBlockWithDesc(blockBattery, TileBattery.class, "BlockBattery");
		RegistryHelper.registerContainerBlockWithDesc(blockCapacitor, TileCapacitor.class, "BlockCapacitor");
		RegistryHelper.registerContainerBlockWithDesc(blockLightningRod, TileLightningRod.class, "BlockLightningRod");
		RegistryHelper.registerContainerBlockWithDesc(blockTeslaCoil, TileTeslaCoil.class, "BlockTeslaCoil");
		RegistryHelper.registerContainerBlockWithDesc(blockCharger, TileCharger.class, "BlockCharger");
		// RegistryHelper.registerContainerBlockWithDesc(blockSaw,
		// TileSawmill.class, "BlockSawmill");

		// Bloomery
		blockBloomery = new BlockBloomery(Material.rock).setBlockName("blockBloomery");

		RegistryHelper.registerContainerBlockWithDesc(blockBloomery, TileBloomery.class, "BlockBloomery");

		// Armor Editor
		blockArmorEditor = new BlockArmorEditor(Material.iron).setBlockName("blockArmorEditor");

		RegistryHelper.registerContainerBlock(blockArmorEditor, TileArmorEditor.class, "BlockArmorEditor");

		// Pipes
		blockCopperPipe = new BlockCopperPipe(Material.iron).setBlockName("blockCopperPipe");

		RegistryHelper.registerContainerBlockWithDesc(blockCopperPipe, TileCopperPipe.class, "BlockCopperPipe");

		blockSteelPipe = new BlockSteelPipe(Material.iron).setBlockName("blockSteelPipe");

		RegistryHelper.registerContainerBlockWithDesc(blockSteelPipe, TileSteelPipe.class, "BlockSteelPipe");

		// Wires
		blockCopperWire = new BlockCopperWire(Material.iron).setBlockName("blockCopperWire");

		RegistryHelper.registerContainerBlockWithDescAndMeta(blockCopperWire, TileCopperWire.class, "BlockCopperWire");

		blockSteelWire = new BlockSteelWire(Material.iron).setBlockName("blockSteelWire");

		RegistryHelper.registerContainerBlockWithDescAndMeta(blockSteelWire, TileSteelWire.class, "BlockSteelWire");

		// Tanks
		blockCopperTank = new BlockCopperTank(Material.iron).setBlockName("blockCopperTank");

		RegistryHelper.registerContainerBlockWithDesc(blockCopperTank, TileCopperTank.class, "BlockCopperTank");

		blockRefinery = new BlockRefinery().setBlockName("blockRefinery");
		// RegistryHelper.registerContainerBlockWithDesc(blockRefinery,
		// TileRefinery.class, "BlockRefinery");

		blockStasisField = new BlockStasisField().setBlockName("blockStasisField");
		RegistryHelper.registerContainerBlockWithDesc(blockStasisField, TileStasisField.class, "BlockStasisField");
	}

	private static void initializeOres()
	{
		// Ores
		blockCustomOre = new BlockSteamcraftOre().setBlockName("blockSteamcraftOre");
		blockSlate = new BlockSlate().setBlockName("blockSlate");

		blockRawBlueSlateStairs = new BlockCustomStairs(blockSlate, 0).setBlockName("blockRawBlueSlateStairs");
		registerBlock(blockRawBlueSlateStairs, "BlockRawBlueSlateStairs");
		blockRawBlackSlateStairs = new BlockCustomStairs(blockSlate, 1).setBlockName("blockRawBlackSlateStairs");
		registerBlock(blockRawBlackSlateStairs, "BlockRawBlackSlateStairs");
		blockRawRedSlateStairs = new BlockCustomStairs(blockSlate, 2).setBlockName("blockRawRedSlateStairs");
		registerBlock(blockRawRedSlateStairs, "BlockRawRedSlateStairs");
		blockCobbleBlueSlateStairs = new BlockCustomStairs(blockSlate, 3).setBlockName("blockCobbleBlueSlateStairs");
		registerBlock(blockCobbleBlueSlateStairs, "BlockCobbleBlueSlateStairs");
		blockCobbleBlackSlateStairs = new BlockCustomStairs(blockSlate, 4).setBlockName("blockCobbleBlackSlateStairs");
		registerBlock(blockCobbleBlackSlateStairs, "BlockCobbleBlackSlateStairs");
		blockCobbleRedSlateStairs = new BlockCustomStairs(blockSlate, 5).setBlockName("blockCobbleRedSlateStairs");
		registerBlock(blockCobbleRedSlateStairs, "BlockCobbleRedSlateStairs");
		blockBrickBlueSlateStairs = new BlockCustomStairs(blockSlate, 6).setBlockName("blockBrickBlueSlateStairs");
		registerBlock(blockBrickBlueSlateStairs, "BlockBrickBlueSlateStairs");
		blockBrickBlackSlateStairs = new BlockCustomStairs(blockSlate, 7).setBlockName("blockBrickBlackSlateStairs");
		registerBlock(blockBrickBlackSlateStairs, "BlockBrickBlackSlateStairs");
		blockBrickRedSlateStairs = new BlockCustomStairs(blockSlate, 8).setBlockName("blockBrickRedSlateStairs");
		registerBlock(blockBrickRedSlateStairs, "BlockBrickRedSlateStairs");

		registerBlock(blockCustomOre, BaseItemBlockWithMetadata.class, "BlockSteamcraftOre", 7);
		registerBlock(blockSlate, BaseItemBlockWithMetadata.class, "BlockSlate", 9);

		// Metals
		blockMetal = new BlockMetal().setBlockName("blockMetal");
		blockUranium = new BlockUranium(Material.iron).setBlockName("blockUranium");
		blockEtherium = new BlockEtherium(Material.iron).setBlockName("blockEtherium");

		registerBlock(blockMetal, BaseItemBlockWithMetadata.class, "BlockMetal", 8);
		registerBlock(blockUranium, "BlockUranium");
		registerBlock(blockEtherium, "BlockEtherium");
	}

	private static void initializeOthers()
	{
		blockDropHammerAnvil = new BlockDropHammerAnvil(Material.anvil).setBlockName("blockDropHammerAnvil");

		// RegistryHelper.registerContainerBlock(blockDropHammerAnvil,
		// TileDropHammer.class, "BlockDropHammerAnvil");

		blockTeaPlant = new BlockTeaPlant().setBlockName("blockTeaPlant");
		registerBlock(blockTeaPlant, "BlockTeaPlant");

		blockTimeBomb = new BlockTimeBomb(Material.tnt).setBlockName("blockTimeBomb");
		RegistryHelper.registerContainerBlock(blockTimeBomb, TileTimeBomb.class, "BlockTimeBomb");

		blockHatch = new BlockHatch().setBlockName("blockHatch");
		RegistryHelper.registerContainerBlock(blockHatch, TileHatch.class, "BlockHatch");

		blockFlesh = new BaseBlock(Material.gourd).setBlockName("blockFlesh").setCreativeTab(Steamcraft.tabSC2);
		registerBlock(blockFlesh, "BlockFlesh");

		blockPlankStack = new BlockPlankStack(Material.wood).setBlockName("blockPlankStack").setCreativeTab(Steamcraft.tabSC2);
		// registerBlock(blockPlankStack, BaseItemBlockWithMetadata.class,
		// "BlockPlankStack");

		blockCongealedSlime = new BlockCongealedSlime(Material.gourd).setBlockName("blockCongealedSlime").setCreativeTab(Steamcraft.tabSC2);
		registerBlock(blockCongealedSlime, "BlockCongealedSlime");

		blockMotionSensor = new BlockMotionSensor(Material.iron, false).setBlockName("blockMotionSensor");
		blockMotionSensorOn = new BlockMotionSensor(Material.iron, true).setBlockName("blockMotionSensorOn");

		// RegistryHelper.registerContainerBlock(blockMotionSensor,
		// TileMotionSensor.class, "BlockMotionSensor");
		// RegistryHelper.registerContainerBlock(blockMotionSensorOn,
		// TileMotionSensor.class, "BlockMotionSensorOn");

		// Standard
		blockStandardSiren = new BlockSiren(Material.redstoneLight, false, "standard").setBlockName("blockStandardSiren");
		blockStandardSirenOn = new BlockSiren(Material.redstoneLight, true, "standard").setBlockName("blockStandardSiren");
		registerBlock(blockStandardSiren, "BlockStandardSiren");
		registerBlock(blockStandardSirenOn, "BlockStandardSirenOn");
		// All Clear
		blockAllClearSiren = new BlockSiren(Material.redstoneLight, false, "allclear").setBlockName("blockAllClearSiren");
		blockAllClearSirenOn = new BlockSiren(Material.redstoneLight, true, "allclear").setBlockName("blockAllClearSiren");
		registerBlock(blockAllClearSiren, "BlockAllClearSiren");
		registerBlock(blockAllClearSirenOn, "BlockAllClearSirenOn");
		// Intruder
		blockIntruderSiren = new BlockSiren(Material.redstoneLight, false, "intruderalert").setBlockName("blockIntruderSiren");
		blockIntruderSirenOn = new BlockSiren(Material.redstoneLight, true, "intruderalert").setBlockName("blockIntruderSiren");
		registerBlock(blockIntruderSiren, "BlockIntruderSiren");
		registerBlock(blockIntruderSirenOn, "BlockIntruderSirenOn");
		// Nuclear
		blockNuclearSiren = new BlockSiren(Material.redstoneLight, false, "nuclearalarm").setBlockName("blockNuclearSiren");
		blockNuclearSirenOn = new BlockSiren(Material.redstoneLight, true, "nuclearalarm").setBlockName("blockNuclearSiren");
		registerBlock(blockNuclearSiren, "BlockNuclearSiren");
		registerBlock(blockNuclearSirenOn, "BlockNuclearSirenOn");

		blockSkyrail = new BlockSkyrail().setBlockName("blockSkyrail");
		registerBlock(blockSkyrail, "BlockSkyrail");

		blockDynamite = new BlockDynamite().setBlockName("blockDynamite");
		registerBlock(blockDynamite, "BlockDynamite");

		blockMeltingIce = new BlockMeltingIce(true).setBlockName("blockMeltingIce");
		registerBlock(blockMeltingIce, "BlockMeltingIce");

		blockGhostIce = new BlockMeltingIce(false).setBlockName("blockGhostIce");
		registerBlock(blockGhostIce, "BlockGhostIce");

		blockTrunk = new BlockTrunk().setBlockName("blockTrunk");
		// registerBlock(blockTrunk, "BlockTrunk");
	}

	private static void initializeTerrain()
	{
		blockFissurePortal = new BlockFissurePortal(Material.rock).setBlockName("blockFissurePortal");
		registerBlock(blockFissurePortal, "BlockFissurePortal");

		blockCompressedStone = new BlockCompressedStone(Material.rock);
		registerBlock(blockCompressedStone, "BlockCompressedStone");

		blockInfestedGrass = new BlockInfestedGrass(Material.grass).setBlockName("blockInfestedGrass");
		registerBlock(blockInfestedGrass, "BlockInfestedGrass");

		blockInfestedDirt = new BlockInfestedDirt(Material.ground).setBlockName("blockInfestedDirt");
		registerBlock(blockInfestedDirt, "BlockInfestedDirt");

		blockPath = new BlockThin(Material.rock).setBlockName("blockPath");
		registerBlock(blockPath, "BlockPath");

		blockLeafCover = new BlockLeafPile().setBlockName("blockLeafPile");
		registerBlock(blockLeafCover, "BlockLeafPile");

		blockMoss = new BlockLeafPile().setBlockName("blockMoss");
		registerBlock(blockMoss, "BlockMoss");

		blockPolishedPlanks = new BlockPolishedPlanks().setBlockName("blockPolishedPlanks");
		registerBlock(blockPolishedPlanks, BaseItemBlockWithMetadata.class, "BlockPolishedPlanks", 3);

		blockRedwoodLog = new BlockCustomLog("Redwood").setBlockName("blockRedwoodLog");
		registerBlock(blockRedwoodLog, "BlockRedwoodLog");

		blockRedwoodLeaves = new BlockCustomLeaves("Redwood").setBlockName("blockRedwoodLeaves");
		registerBlock(blockRedwoodLeaves, "BlockRedwoodLeaves");

		blockRedwoodPlanks = new BaseBlock(Material.wood).setBlockName("blockRedwoodPlanks");
		registerBlock(blockRedwoodPlanks, "BlockRedwoodPlanks");

		blockMangroveLog = new BlockCustomLog("Mangrove").setBlockName("blockMangroveLog");
		registerBlock(blockMangroveLog, "BlockMangroveLog");

		blockMangroveLeaves = new BlockCustomLeaves("Mangrove").setBlockName("blockMangroveLeaves");
		registerBlock(blockMangroveLeaves, "BlockMangroveLeaves");

		blockMangrovePlanks = new BaseBlock(Material.wood).setBlockName("blockMangrovePlanks");
		registerBlock(blockMangrovePlanks, "BlockMangrovePlanks");

		blockWillowLog = new BlockCustomLog("Willow").setBlockName("blockWillowLog");
		registerBlock(blockWillowLog, "BlockWillowLog");

		blockWillowLeaves = new BlockCustomLeaves("Willow").setBlockName("blockWillowLeaves");
		registerBlock(blockWillowLeaves, "BlockWillowLeaves");

		blockWillowPlanks = new BaseBlock(Material.wood).setBlockName("blockWillowPlanks");
		registerBlock(blockWillowPlanks, "BlockWillowPlanks");

		blockPetrifiedLog = new BlockCustomLog("Petrified").setBlockName("blockPetrifiedLog");
		registerBlock(blockPetrifiedLog, "BlockPetrifiedLog");

		blockDeadLeaves = new BlockCustomLeaves("Dead").setBlockName("blockDeadLeaves");
		registerBlock(blockDeadLeaves, "BlockDeadLeaves");

		blockPetrifiedPlanks = new BaseBlock(Material.wood).setBlockName("blockPetrifiedPlanks");
		registerBlock(blockPetrifiedPlanks, "BlockPetrifiedPlanks");

		blockRedwoodFence = new BlockCustomFence("blockRedwoodPlanks", Material.wood).setBlockName("blockRedwoodFence");
		registerBlock(blockRedwoodFence, "BlockRedwoodFence");

		blockMangroveFence = new BlockCustomFence("blockMangrovePlanks", Material.wood).setBlockName("blockMangroveFence");
		registerBlock(blockMangroveFence, "blockMangroveFence");

		blockWillowFence = new BlockCustomFence("blockWillowPlanks", Material.wood).setBlockName("blockWillowFence");
		registerBlock(blockWillowFence, "blockWillowFence");

		blockPetrifiedFence = new BlockCustomFence("blockPetrifiedPlanks", Material.wood).setBlockName("blockPetrifiedFence");
		registerBlock(blockPetrifiedFence, "blockPetrifiedFence");

		/*
		 * blockRedwoodDoor = new
		 * BlockCustomDoor("Redwood").setBlockName("blockRedwoodDoor");
		 * registerBlock(blockRedwoodDoor, "BlockRedwoodDoor");
		 * blockMangroveDoor = new
		 * BlockCustomDoor("Mangrove").setBlockName("blockMangroveDoor");
		 * registerBlock(blockMangroveDoor, "blockMangroveDoor");
		 * blockWillowDoor = new
		 * BlockCustomDoor("Willow").setBlockName("blockWillowDoor");
		 * registerBlock(blockWillowDoor, "blockWillowDoor"); blockPetrifiedDoor
		 * = new
		 * BlockCustomDoor("Petrified").setBlockName("blockPetrifiedDoor");
		 * registerBlock(blockPetrifiedDoor, "blockPetrifiedDoor");
		 */

		blockRedwoodStairs = new BlockCustomStairs(blockRedwoodPlanks).setBlockName("blockRedwoodStairs");
		registerBlock(blockRedwoodStairs, "BlockRedwoodStairs");

		blockMangroveStairs = new BlockCustomStairs(blockMangrovePlanks).setBlockName("blockMangroveStairs");
		registerBlock(blockMangroveStairs, "blockMangroveStairs");

		blockWillowStairs = new BlockCustomStairs(blockWillowPlanks).setBlockName("blockWillowStairs");
		registerBlock(blockWillowStairs, "blockWillowStairs");

		blockPetrifiedStairs = new BlockCustomStairs(blockPetrifiedPlanks).setBlockName("blockPetrifiedStairs");
		registerBlock(blockPetrifiedStairs, "blockPetrifiedStairs");

		blockRedwoodSlab = new BlockCustomSlab("blockRedwoodPlanks", blockRedwoodPlanks, Material.wood).setBlockName("blockRedwoodSlab");
		registerBlock(blockRedwoodSlab, "BlockRedwoodSlab");

		blockMangroveSlab = new BlockCustomSlab("blockMangrovePlanks", blockMangrovePlanks, Material.wood).setBlockName("blockMangroveSlab");
		registerBlock(blockMangroveSlab, "blockMangroveSlab");

		blockWillowSlab = new BlockCustomSlab("blockWillowPlanks", blockWillowPlanks, Material.wood).setBlockName("blockWillowSlab");
		registerBlock(blockWillowSlab, "blockWillowSlab");

		blockPetrifiedSlab = new BlockCustomSlab("blockPetrifiedPlanks", blockPetrifiedPlanks, Material.wood).setBlockName("blockPetrifiedSlab");
		registerBlock(blockPetrifiedSlab, "blockPetrifiedSlab");

		blockRedwoodDoubleSlab = new BlockCustomDoubleSlab("blockRedwoodPlanks", blockRedwoodPlanks, Material.wood, blockRedwoodSlab)
				.setBlockName("blockRedwoodDoubleSlab");
		// registerBlock(blockRedwoodDoubleSlab, "BlockRedwoodDoubleSlab");

		blockMangroveDoubleSlab = new BlockCustomDoubleSlab("blockMangrovePlanks", blockMangrovePlanks, Material.wood, blockMangroveSlab)
				.setBlockName("blockMangroveDoubleSlab");
		// registerBlock(blockMangroveDoubleSlab, "blockMangroveDoubleSlab");

		blockWillowDoubleSlab = new BlockCustomDoubleSlab("blockWillowPlanks", blockWillowPlanks, Material.wood, blockWillowSlab)
				.setBlockName("blockWillowDoubleSlab");
		// registerBlock(blockWillowDoubleSlab, "blockWillowDoubleSlab");

		blockPetrifiedDoubleSlab = new BlockCustomDoubleSlab("blockPetrifiedPlanks", blockPetrifiedPlanks, Material.wood, blockPetrifiedSlab)
				.setBlockName("blockPetrifiedDoubleSlab");
		// registerBlock(blockPetrifiedDoubleSlab, "blockPetrifiedDoubleSlab");

		blockMud = new BlockMud(Material.ground).setBlockName("blockMud");
		registerBlock(blockMud, "BlockMud");

		blockBoulder = new BlockBoulder().setBlockName("blockBoulder");
		registerBlock(blockBoulder, "BlockBoulder");

		blockSpiderEgg = new BlockSpiderEgg(Material.dragonEgg).setBlockName("blockSpiderEgg");
		// registerBlock(blockSpiderEgg, "BlockSpiderEgg");

		blockMushroom = new BlockCustomMushroom().setBlockName("blockMushroom");
		// registerBlock(blockMushroom, "BlockMushroom");

		blockTintedRock = new BlockTintedRock(Material.rock).setBlockName("blockTintedRock");
		registerBlock(blockTintedRock, "BlockTintedRock");

		blockCrystal = new BlockCrystal().setBlockName("blockCrystal");

		RegistryHelper.registerContainerBlock(blockCrystal, TileCrystal.class, "BlockCrystal");

		// Wood
		blockBrassLog = new BlockBrassLog(Material.iron).setBlockName("blockBrassLog");
		blockBrassLeaves = new BlockBrassLeaves(Material.iron).setBlockName("blockBrassLeaves");

		registerBlock(blockBrassLog, "BlockBrassLog");
		registerBlock(blockBrassLeaves, "BlockBrassLeaves");
	}

	private static String[] blacklist = new String[] { "BlockFissurePortal", "BlockLamp", "BlockMotionSensor", "BlockMotionSensorOn",
			"BlockStandardSirenOn", "BlockStandardSiren", "BlockAllClearSiren", "BlockAllClearSirenOn", "BlockNuclearSiren", "BlockNuclearSirenOn",
			"BlockIntruderSiren", "BlockIntruderSirenOn", "BlockCopperTank", "BlockInfestedDirt", "BlockInfestedGrass" };

	private static void registerBlock(Block block, Class<? extends ItemBlock> itemblock, String name, int maxMeta)
	{
		if (block.isOpaqueCube() && !Arrays.asList(blacklist).contains(name) && !block.hasTileEntity(0))
			FMPCompatHandler.registerMetaFMP(block, maxMeta);

		GameRegistry.registerBlock(block, itemblock, name);
	}

	private static void registerBlock(Block block, String name)
	{
		if (block.isOpaqueCube() && !Arrays.asList(blacklist).contains(name) && !block.hasTileEntity(0))
			FMPCompatHandler.registerFMP(block);

		GameRegistry.registerBlock(block, name);
	}

}
