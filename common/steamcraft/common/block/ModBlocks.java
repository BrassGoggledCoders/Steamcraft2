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
 * File created @ [Jan 30, 2014, 5:51:27 PM]
 */
package common.steamcraft.common.block;

import common.steamcraft.common.block.machines.ModMachines;
import common.steamcraft.common.lib2.BlockIDs;
import common.steamcraft.common.lib2.CreativeTabsMod;
import common.steamcraft.common.lib2.LibInfo;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

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
	
	public static Block etheriumCrystal;
	
	public static Block smog;
	
	public static Block hammerHead;
	
	public static Block steamBlock;
	
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
		leavesBrass = new BlockMod(BlockIDs.leavesLampID, Material.wood).setHardness(2F).setLightOpacity(1).setLightValue(0.9375F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("leavesBrass");
		teaPlant = new BlockTeaPlant(BlockIDs.teaPlantID).setUnlocalizedName("teaPlant");
		castIronLampOff = new BlockCastIronLamp(BlockIDs.castIronLampOffID, false).setCreativeTab(CreativeTabsMod.tabSCBlocks).setUnlocalizedName("castIronLampOff");
		castIronLampOn = new BlockCastIronLamp(BlockIDs.castIronLampOnID, true).setUnlocalizedName("castIronLampOn");
		cryoIce = new BlockCryoIce(BlockIDs.cryoIceID, Material.ice).setUnlocalizedName("cryoIce");
		
		etheriumCrystal = new BlockEtheriumCrystal(BlockIDs.etheriumCrystalID).setUnlocalizedName("etheriumCrystal");
		
		smog = new BlockSmog(BlockIDs.smogID, Material.air).setUnlocalizedName("smog");
				
		ModMachines.initMachines();
		ModOres.initOres();
		ModPowerBlocks.initPowerBlocks();
		registerBlocks();
		
		Fluid steamFluid = new Fluid("steam").setGaseous(true);
		FluidRegistry.registerFluid(steamFluid);
		steamBlock = new BlockFluidSteam(steamFluid, Material.water);
		GameRegistry.registerBlock(steamBlock, "steamFluidBlock");
		steamFluid.setUnlocalizedName(steamBlock.getUnlocalizedName());
		steamFluid.setBlockID(steamBlock);
	}
	
	public static void registerBlocks() {	
		GameRegistry.registerBlock(blockCastIron, "blockCastIron");
		GameRegistry.registerBlock(blockVolucite, "blockVolucite");
		GameRegistry.registerBlock(blockBrass, "blockBrass");
		GameRegistry.registerBlock(blockSteel, "blockSteel");
		GameRegistry.registerBlock(blockAluminum, "blockAluminum");
		GameRegistry.registerBlock(blockCopper, "blockCopper");
		GameRegistry.registerBlock(blockTin, "blockTin");
		GameRegistry.registerBlock(blockZinc, "blockZinc");
		GameRegistry.registerBlock(lampOn, "lampOn");
		GameRegistry.registerBlock(lampOff, "lampOff");
		GameRegistry.registerBlock(woodBrass, "woodBrass");
		GameRegistry.registerBlock(leavesBrass, "leavesBrass");
		GameRegistry.registerBlock(railingCastIron, "railingCastIron");
		GameRegistry.registerBlock(gateCastIron, "gateCastIron");
		GameRegistry.registerBlock(decorIron, "decorIron");
		GameRegistry.registerBlock(decorGold, "decorGold");
		GameRegistry.registerBlock(decorDiamond, "decorDiamond");
		GameRegistry.registerBlock(decorCastIron, "decorCastIron");
		GameRegistry.registerBlock(decorVolucite, "decorVolucite");
		GameRegistry.registerBlock(decorBrass, "decorBrass");
		GameRegistry.registerBlock(decorLapis, "decorLapis");
		GameRegistry.registerBlock(decorStone, "decorStone");
		GameRegistry.registerBlock(blockUranium, "blockUranium");
		GameRegistry.registerBlock(decorUranium, "decorUranium");
		GameRegistry.registerBlock(teaPlant, "teaPlant");
		GameRegistry.registerBlock(castIronLampOff, "castironlampidle");
		GameRegistry.registerBlock(castIronLampOn, "castironlampactive");
		GameRegistry.registerBlock(blockObsidianTile, "slatetiles");
		GameRegistry.registerBlock(etheriumCrystal, "etheriumCrystal");
		GameRegistry.registerBlock(smog, "smog");
		GameRegistry.registerBlock(cryoIce, "cryoIce");
	}
}