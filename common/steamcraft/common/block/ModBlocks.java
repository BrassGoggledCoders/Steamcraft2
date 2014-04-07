/**
 * This class was created by !=MrArcane111> or his SC2 development team. 
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
 * File created @ [Jan 30, 2014, 5:51:27 PM]
 */
package common.steamcraft.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import common.steamcraft.common.SC2;
import common.steamcraft.common.block.machines.ModMachines;
import common.steamcraft.common.lib2.BlockIDs;
import common.steamcraft.common.lib2.CreativeTabsMod;
import common.steamcraft.common.lib2.LibInfo;
import cpw.mods.fml.common.registry.GameRegistry;

import common.steamcraft.common.block.machines.ModMachines;
import common.steamcraft.common.lib2.BlockIDs;
import common.steamcraft.common.lib2.CreativeTabsMod;
import common.steamcraft.common.lib2.LibInfo;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author MrArcane111
 *
 */
public class ModBlocks {
	public static Block blockAluminum;
	public static Block blockCopper;
	public static Block blockTin;
	public static Block blockZinc;
	public static Block blockBronze;
	public static Block blockSteel;
	public static Block blockCastIron;
	public static Block blockVolucite;
	public static Block blockBrass;
	public static Block blockUranium;
	public static Block blockObsidianTile;
	
	public static BlockStairs stairsObsidianTile;
	
	public static Block decorIron;
	public static Block decorGold;
	public static Block decorDiamond;
	public static Block decorCastIron;
	public static Block decorBrass;
	public static Block decorVolucite;
	public static Block decorLapis;
	public static Block decorStone;
	public static Block decorUranium;
	
	public static Block railingCastIron;
	public static Block gateCastIron;
	public static Block lampOn;
	public static Block lampOff;
	public static Block woodBrass;
	public static Block leavesBrass;
	public static Block teaPlant;
	public static Block castIronLampOff;
	public static Block castIronLampOn;
	public static Block cryoIce;
	public static Block blackSlate, blueSlate;
	
	public static Block etheriumCrystal;
	
	public static Block smog;
	
	public static Block hammerHead;
	
	public static Block steamBlock;
	
	public static Fluid steamFluid;
	
	public static void initBlocks() {
		blockCastIron = new BlockMod(BlockIDs.blockCastIronID, Material.iron).setHardness(7F).setResistance(20F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("blockCastIron");
		blockVolucite = new BlockMod(BlockIDs.blockVoluciteID, Material.rock).setHardness(50F).setResistance(6000000F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("blockVolucite");
		blockBrass = new BlockMod(BlockIDs.blockBrassID, Material.iron).setHardness(5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("blockBrass");
		blockAluminum = new BlockMod(BlockIDs.blockAluminumID, Material.iron).setHardness(7F).setResistance(20F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("blockAluminum");
		blockCopper = new BlockMod(BlockIDs.blockCopperID, Material.iron).setHardness(7F).setResistance(20F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("blockCopper");
		blockTin = new BlockMod(BlockIDs.blockTinID, Material.iron).setHardness(7F).setResistance(20F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("blockTin");
		blockZinc = new BlockMod(BlockIDs.blockZincID, Material.iron).setHardness(7F).setResistance(20F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("blockZinc");
		blockBronze = new BlockMod(BlockIDs.blockBronzeID, Material.iron).setHardness(7F).setResistance(20F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("blockBronze");
		blockSteel = new BlockMod(BlockIDs.blockSteelID, Material.iron).setHardness(7F).setResistance(20F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("blockSteel");
		blockUranium = new BlockUranium(BlockIDs.blockUraniumID);
		blockObsidianTile = new BlockMod(BlockIDs.blockObsidianTileID, Material.rock).setUnlocalizedName("blockObsidianTile");
		
		//stairsSlateTile = new (BlockStairs) BlockSC(SC2_BlockIDs.stairsSlateTileID, blockSlateTile, 0);
		
		decorIron = new BlockMod(BlockIDs.decorIronID, Material.iron).setHardness(5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("decorIron");
		decorGold = new BlockMod(BlockIDs.decorGoldID, Material.iron).setHardness(3F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("decorGold");
		decorDiamond = new BlockMod(BlockIDs.decorDiamondID, Material.rock).setHardness(5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("decorDiamond");
		decorCastIron = new BlockMod(BlockIDs.decorCastIronID, Material.iron).setHardness(7F).setResistance(20F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("decorCastIron");
		decorVolucite = new BlockMod(BlockIDs.decorVoluciteID, Material.rock).setHardness(50F).setResistance(6000000F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("decorVolucite");
		decorBrass = new BlockMod(BlockIDs.decorBrassID, Material.iron).setHardness(5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("decorBrass");
		decorLapis = new BlockMod(BlockIDs.decorLapisID, Material.rock).setHardness(3F).setResistance(5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decorLapis");
		decorStone = new BlockMod(BlockIDs.decorStoneID, Material.rock).setHardness(2F).setResistance(10F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("decorStone");
		decorUranium = new BlockUranium(BlockIDs.decorUraniumID).setHardness(10F).setResistance(6F).setLightValue(0.625F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("decorUranium");
		
		gateCastIron = new BlockCastIronGate(BlockIDs.gateCastIronID, Material.iron).setUnlocalizedName("gateCastIron");
		railingCastIron = new BlockFence(BlockIDs.railingCastIronID, LibInfo.SC2_PREFIX + "blockCastIron", Material.iron).setHardness(7F).setResistance(20F).setStepSound(Block.soundMetalFootstep).setCreativeTab(CreativeTabsMod.tabSCBlocks).setUnlocalizedName("fenceCastIron");
		lampOn = new BlockLamp(BlockIDs.lampOnID, true).setUnlocalizedName("lampOn").setCreativeTab(null);
		lampOff = new BlockLamp(BlockIDs.lampOffID, false).setUnlocalizedName("lampOff");
		woodBrass = new BlockBrassLog(BlockIDs.woodBrassID);
		leavesBrass = new BlockMod(BlockIDs.leavesBrassID, Material.wood).setHardness(2F).setLightOpacity(1).setLightValue(0.9375F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("leavesBrass");
		teaPlant = new BlockTeaPlant(BlockIDs.teaPlantID).setUnlocalizedName("teaPlant");
		castIronLampOff = new BlockCastIronLamp(BlockIDs.castIronLampOffID, false).setCreativeTab(CreativeTabsMod.tabSCBlocks).setUnlocalizedName("castIronLampOff");
		castIronLampOn = new BlockCastIronLamp(BlockIDs.castIronLampOnID, true).setUnlocalizedName("castIronLampOn");
		cryoIce = new BlockCryoIce(BlockIDs.cryoIceID, Material.ice).setUnlocalizedName("cryoIce");
		
		etheriumCrystal = new BlockEtheriumCrystal(BlockIDs.etheriumCrystalID).setUnlocalizedName("etheriumCrystal");
		
		smog = new BlockSmog(BlockIDs.smogID, Material.air).setUnlocalizedName("smog");
		
		blackSlate = new BlockSlate(BlockIDs.blackSlateID, Material.rock).setUnlocalizedName("blackSlate");
		blueSlate = new BlockSlate(BlockIDs.blueSlateID, Material.rock).setUnlocalizedName("blueSlate");
				
		ModMachines.initMachines();
		ModOres.initOres();
		ModPowerBlocks.initPowerBlocks();
		registerBlocks();
		
<<<<<<< HEAD
		Fluid steamFluid = new Fluid("steam").setGaseous(true).setTemperature(700).setDensity(-100).setViscosity(500).setLuminosity(1);
=======
		steamFluid = new Fluid("steam").setGaseous(true);
>>>>>>> refs/heads/master
		FluidRegistry.registerFluid(steamFluid);
		
		steamBlock = new BlockFluidSteam(steamFluid, Material.water);
		GameRegistry.registerBlock(steamBlock, "steamFluidBlock");
		steamFluid.setUnlocalizedName(steamBlock.getUnlocalizedName());
		steamFluid.setBlockID(steamBlock);
		
		//For some weird reason, our version of steam has NULL icons, so the GUISteamBoiler function drawCutIcon will crash
		steamFluid.setIcons(steamBlock.getIcon(0, 0), steamBlock.getIcon(1, 0));
	}
	
	public static void registerBlocks() {
		if(BlockIDs.blockCastIronID != 0)
		GameRegistry.registerBlock(blockCastIron, "blockCastIron");
		if(BlockIDs.blockVoluciteID != 0)
		GameRegistry.registerBlock(blockVolucite, "blockVolucite");
		if(BlockIDs.blockBrassID != 0)
		GameRegistry.registerBlock(blockBrass, "blockBrass");
		if(BlockIDs.blockSteelID != 0)
		GameRegistry.registerBlock(blockSteel, "blockSteel");
		if(BlockIDs.blockAluminumID != 0)
		GameRegistry.registerBlock(blockAluminum, "blockAluminum");
		if(BlockIDs.blockCopperID != 0)
		GameRegistry.registerBlock(blockCopper, "blockCopper");
		if(BlockIDs.blockTinID != 0)
		GameRegistry.registerBlock(blockTin, "blockTin");
		if(BlockIDs.blockZincID != 0)
		GameRegistry.registerBlock(blockZinc, "blockZinc");
		if(BlockIDs.lampOnID != 0)
		GameRegistry.registerBlock(lampOn, "lampOn");
		if(BlockIDs.lampOffID != 0)
		GameRegistry.registerBlock(lampOff, "lampOff");
		if(BlockIDs.woodBrassID != 0)
		GameRegistry.registerBlock(woodBrass, "woodBrass");
		if(BlockIDs.leavesBrassID != 0)
		GameRegistry.registerBlock(leavesBrass, "leavesBrass");
		if(BlockIDs.railingCastIronID != 0)
		GameRegistry.registerBlock(railingCastIron, "railingCastIron");
		if(BlockIDs.gateCastIronID != 0)
		GameRegistry.registerBlock(gateCastIron, "gateCastIron");
		if(BlockIDs.decorIronID != 0)
		GameRegistry.registerBlock(decorIron, "decorIron");
		if(BlockIDs.decorGoldID != 0)
		GameRegistry.registerBlock(decorGold, "decorGold");
		if(BlockIDs.decorDiamondID != 0)
		GameRegistry.registerBlock(decorDiamond, "decorDiamond");
		if(BlockIDs.decorCastIronID != 0)
		GameRegistry.registerBlock(decorCastIron, "decorCastIron");
		if(BlockIDs.decorVoluciteID != 0)
		GameRegistry.registerBlock(decorVolucite, "decorVolucite");
		if(BlockIDs.decorBrassID != 0)
		GameRegistry.registerBlock(decorBrass, "decorBrass");
		if(BlockIDs.decorLapisID != 0)
		GameRegistry.registerBlock(decorLapis, "decorLapis");
		if(BlockIDs.decorStoneID != 0)
		GameRegistry.registerBlock(decorStone, "decorStone");
		if(BlockIDs.blockUraniumID != 0)
		GameRegistry.registerBlock(blockUranium, "blockUranium");
		if(BlockIDs.decorUraniumID != 0)
		GameRegistry.registerBlock(decorUranium, "decorUranium");
		if(BlockIDs.teaPlantID != 0)
		GameRegistry.registerBlock(teaPlant, "teaPlant");
		if(BlockIDs.castIronLampOffID != 0)
		GameRegistry.registerBlock(castIronLampOff, "castironlampidle");
		if(BlockIDs.castIronLampOnID != 0)
		GameRegistry.registerBlock(castIronLampOn, "castironlampactive");
		if(BlockIDs.blockObsidianTileID != 0)
		GameRegistry.registerBlock(blockObsidianTile, "slatetiles");
		if(BlockIDs.etheriumCrystalID != 0)
		GameRegistry.registerBlock(etheriumCrystal, "etheriumCrystal");
		if(BlockIDs.smogID != 0)
		GameRegistry.registerBlock(smog, "smog");
		if(BlockIDs.cryoIceID != 0)
		GameRegistry.registerBlock(cryoIce, "cryoIce");
		if(BlockIDs.blueSlateID != 0)
		GameRegistry.registerBlock(blueSlate, "blueSlate");
		if(BlockIDs.blackSlateID != 0)
		GameRegistry.registerBlock(blackSlate, "blackSlate");
	}
}