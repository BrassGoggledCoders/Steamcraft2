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
package steamcraft.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import steamcraft.client.renderers.tile.TileCrystalRenderer.TileCrystal;
import steamcraft.client.renderers.tile.TileHatchRenderer.TileHatch;
import steamcraft.common.blocks.BaseBlock;
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
import steamcraft.common.blocks.BlockHatch;
import steamcraft.common.blocks.BlockLamp;
import steamcraft.common.blocks.BlockMetal;
import steamcraft.common.blocks.BlockPlankStack;
import steamcraft.common.blocks.BlockSlate;
import steamcraft.common.blocks.BlockTeaPlant;
import steamcraft.common.blocks.BlockTimeBomb;
import steamcraft.common.blocks.BlockUranium;
import steamcraft.common.blocks.FluidSteam;
import steamcraft.common.blocks.machines.BlockArmorEditor;
import steamcraft.common.blocks.machines.BlockBattery;
import steamcraft.common.blocks.machines.BlockBloomery;
import steamcraft.common.blocks.machines.BlockCharger;
import steamcraft.common.blocks.machines.BlockCopperPipe;
import steamcraft.common.blocks.machines.BlockCopperWire;
import steamcraft.common.blocks.machines.BlockDropHammerAnvil;
import steamcraft.common.blocks.machines.BlockIntake;
import steamcraft.common.blocks.machines.BlockLightningRod;
import steamcraft.common.blocks.machines.BlockSawmill;
import steamcraft.common.blocks.machines.BlockSteamBoiler;
import steamcraft.common.blocks.machines.BlockTeslaCoil;
import steamcraft.common.blocks.machines.BlockTurbine;
import steamcraft.common.tiles.TileArmorEditor;
import steamcraft.common.tiles.TileBattery;
import steamcraft.common.tiles.TileBloomery;
import steamcraft.common.tiles.TileCharger;
import steamcraft.common.tiles.TileCopperPipe;
import steamcraft.common.tiles.TileCopperWire;
import steamcraft.common.tiles.TileDropHammer;
import steamcraft.common.tiles.TileIntake;
import steamcraft.common.tiles.TileLightningRod;
import steamcraft.common.tiles.TilePlankStack;
import steamcraft.common.tiles.TileSawmill;
import steamcraft.common.tiles.TileSteamBoiler;
import steamcraft.common.tiles.TileTeslaCoil;
import steamcraft.common.tiles.TileTimeBomb;
import steamcraft.common.tiles.TileTurbine;
import boilerplate.common.baseclasses.BaseItemBlockWithMetadata;
import boilerplate.common.utils.helpers.RegistryHelper;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Surseance
 * 
 */
public class InitBlocks
{
	// Engraved Blocks
	public static Block blockEngraved, blockEngravedVanilla;

	// Cast Iron
	public static Block blockCastIronLamp;
	public static Block blockCastIronFence, blockCastIronGate, blockCastIronRailing;

	public static Block blockLamp, blockLampOn;

	/* Ores */

	public static Block blockCustomOre;
	public static Block blockSlate;

	// Metals
	public static Block blockMetal, blockUranium, blockEtherium;

	/* Machines */

	public static Block blockSteamBoiler, blockIntake, blockTurbine, blockBattery, blockCharger;
	public static Block blockLightningRod, blockTeslaCoil;

	public static Block blockDropHammerAnvil;
	public static Block blockSaw;

	public static Block blockBloomery;

	public static Block blockArmorEditor;

	public static Block blockCopperPipe, blockCopperWire;
	public static Block blockCopperTank;

	/* Fluids */

	public static Fluid steamFluid;
	public static Block blockSteam;

	/* Others */

	public static Block blockCrystal;

	public static Block blockPlankStack;

	// Wood
	public static Block blockBrassLog, blockBrassLeaves;

	public static Block blockTeaPlant, blockHatch;

	public static Block blockBrassPlaque;

	public static Block blockTimeBomb;

	public static Block blockFlesh;

	public static void init()
	{
		initializeDecorative();
		initializeOres();
		initializeMachines();
		initializeFluids();
		initializeOthers();
	}

	private static void initializeOres()
	{
		// Ores
		blockCustomOre = new BlockCustomOre().setBlockName("blockCustomOre");
		blockSlate = new BlockSlate().setBlockName("blockSlate");

		GameRegistry.registerBlock(blockCustomOre, BaseItemBlockWithMetadata.class, "BlockCustomOre");
		GameRegistry.registerBlock(blockSlate, BaseItemBlockWithMetadata.class, "BlockSlate");

		// Metals
		blockMetal = new BlockMetal().setBlockName("blockMetal");;
		blockUranium = new BlockUranium(Material.iron).setBlockName("blockUranium");
		blockEtherium = new BlockEtherium(Material.iron).setBlockName("blockEtherium");

		GameRegistry.registerBlock(blockMetal, BaseItemBlockWithMetadata.class, "BlockMetal");
		GameRegistry.registerBlock(blockUranium, "BlockUranium");
		GameRegistry.registerBlock(blockEtherium, "BlockEtherium");
	}

	private static void initializeDecorative()
	{
		// Engraved Blocks
		blockEngraved = new BlockEngravedSolid().setBlockName("blockEngravedSolid");;
		blockEngravedVanilla = new BlockEngravedVanilla().setBlockName("blockEngravedVanilla");;

		GameRegistry.registerBlock(blockEngraved, BaseItemBlockWithMetadata.class, "BlockEngravedSolid");
		GameRegistry.registerBlock(blockEngravedVanilla, BaseItemBlockWithMetadata.class, "BlockEngravedVanilla");

		// Cast Iron
		blockCastIronLamp = new BlockCastIronLamp().setBlockName("blockCastIronLamp");

		GameRegistry.registerBlock(blockCastIronLamp, "BlockCastIronLamp");

		blockCastIronFence = new BlockCastIronFence().setBlockName("blockCastIronFence");
		blockCastIronGate = new BlockCastIronGate().setBlockName("blockCastIronGate");;
		blockCastIronRailing = new BlockCastIronRailing(Material.anvil).setBlockName("blockCastIronRailing");

		GameRegistry.registerBlock(blockCastIronFence, "BlockCastIronFence");
		GameRegistry.registerBlock(blockCastIronGate, "BlockCastIronGate");
		GameRegistry.registerBlock(blockCastIronRailing, "BlockCastIronRailing");

		blockLamp = new BlockLamp(false).setBlockName("blockLamp");
		blockLampOn = new BlockLamp(true).setBlockName("blockLamp");

		GameRegistry.registerBlock(blockLamp, "BlockLamp");
		GameRegistry.registerBlock(blockLampOn, "BlockLampOn");
	}

	private static void initializeMachines()
	{
		// Steam related
		blockSteamBoiler = new BlockSteamBoiler().setBlockName("blockSteamBoiler");
		blockIntake = new BlockIntake().setBlockName("blockIntake");

		RegistryHelper.registerContainerBlockWithDesc(blockSteamBoiler, TileSteamBoiler.class, "BlockSteamBoiler");
		RegistryHelper.registerContainerBlockWithDesc(blockIntake, TileIntake.class, "BlockIntake");

		// Energy related
		blockTurbine = new BlockTurbine().setBlockName("blockTurbine");
		blockBattery = new BlockBattery().setBlockName("blockBattery");
		blockLightningRod = new BlockLightningRod(Material.iron).setBlockName("blockLightningRod");
		blockTeslaCoil = new BlockTeslaCoil(Material.iron).setBlockName("blockTeslaCoil");
		blockCharger = new BlockCharger().setBlockName("blockCharger");
		blockSaw = new BlockSawmill(Material.anvil).setBlockName("blockSawmill");

		RegistryHelper.registerContainerBlockWithDesc(blockTurbine, TileTurbine.class, "BlockTurbine");
		RegistryHelper.registerContainerBlockWithDesc(blockBattery, TileBattery.class, "BlockBattery");
		RegistryHelper.registerContainerBlockWithDesc(blockLightningRod, TileLightningRod.class, "BlockLightningRod");
		RegistryHelper.registerContainerBlockWithDesc(blockTeslaCoil, TileTeslaCoil.class, "BlockTeslaCoil");
		RegistryHelper.registerContainerBlockWithDesc(blockCharger, TileCharger.class, "BlockCharger");
		RegistryHelper.registerContainerBlockWithDesc(blockSaw, TileSawmill.class, "BlockSawmill");

		// Bloomery
		blockBloomery = new BlockBloomery(Material.rock).setBlockName("blockBloomery");

		RegistryHelper.registerContainerBlockWithDesc(blockBloomery, TileBloomery.class, "BlockBloomery");

		// Armor Editor
		blockArmorEditor = new BlockArmorEditor(Material.iron).setBlockName("blockArmorEditor");;

		RegistryHelper.registerContainerBlock(blockArmorEditor, TileArmorEditor.class, "BlockArmorEditor");

		// Pipes
		blockCopperPipe = new BlockCopperPipe(Material.iron).setBlockName("blockCopperPipe");

		RegistryHelper.registerContainerBlock(blockCopperPipe, TileCopperPipe.class, "BlockCopperPipe");

		// Wires
		blockCopperWire = new BlockCopperWire(Material.iron).setBlockName("blockCopperWire");

		RegistryHelper.registerContainerBlock(blockCopperWire, TileCopperWire.class, "BlockCopperWire");

		// Tanks
		blockCopperTank = new BaseBlock(Material.iron).setBlockName("blockCopperTank");

		GameRegistry.registerBlock(blockCopperTank, "BlockCopperTank");
	}

	private static void initializeFluids()
	{
		// Steam
		steamFluid = new FluidSteam("steam").setUnlocalizedName("steamFluid");;

		if(!FluidRegistry.registerFluid(steamFluid) && !FluidRegistry.isFluidRegistered("steam"))
			steamFluid = FluidRegistry.getFluid("steam");

		blockSteam = new BlockFluidSteam(steamFluid, Material.lava).setBlockName("steamFluidBlock");;

		GameRegistry.registerBlock(blockSteam, "blockSteam");
	}

	private static void initializeOthers()
	{
		blockCrystal = new BlockCrystal().setBlockName("blockCrystal");;

		RegistryHelper.registerContainerBlock(blockCrystal, TileCrystal.class, "BlockCrystal");

		// Wood
		blockBrassLog = new BlockBrassLog(Material.wood).setBlockName("blockBrassLog");
		blockBrassLeaves = new BaseBlock(Material.iron).setBlockName("blockBrassLeaves");

		GameRegistry.registerBlock(blockBrassLog, "BlockBrassLog");
		GameRegistry.registerBlock(blockBrassLeaves, "BlockBrassLeaves");

		blockDropHammerAnvil = new BlockDropHammerAnvil(Material.anvil).setBlockName("blockDropHammerAnvil");

		RegistryHelper.registerContainerBlock(blockDropHammerAnvil, TileDropHammer.class, "BlockDropHammerAnvil");

		blockTeaPlant = new BlockTeaPlant().setBlockName("blockTeaPlant");
		GameRegistry.registerBlock(blockTeaPlant, "BlockTeaPlant");

		blockTimeBomb = new BlockTimeBomb(Material.tnt).setBlockName("blockTimeBomb");
		RegistryHelper.registerContainerBlock(blockTimeBomb, TileTimeBomb.class, "BlockTimeBomb");

		blockHatch = new BlockHatch().setBlockName("blockHatch");
		RegistryHelper.registerContainerBlock(blockHatch, TileHatch.class, "BlockHatch");

		blockFlesh = new BaseBlock(Material.gourd).setBlockName("blockFlesh").setCreativeTab(Steamcraft.tabSC2);
		GameRegistry.registerBlock(blockFlesh, "BlockFlesh");

		blockPlankStack = new BlockPlankStack(Material.wood).setBlockName("blockPlankStack").setCreativeTab(Steamcraft.tabSC2);
		RegistryHelper.registerContainerBlock(blockPlankStack, TilePlankStack.class, "BlockPlankStack");
	}

}
