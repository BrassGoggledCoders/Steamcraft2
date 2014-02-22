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
 * File created @ [Jan 30, 2014, 5:51:27 PM]
 */
package common.steamcraft.mod.common.block;

import common.steamcraft.mod.common.block.machines.ModMachines;
import common.steamcraft.mod.common.lib.BlockIDs;
import common.steamcraft.mod.common.lib.CreativeTabsMod;
import common.steamcraft.mod.common.lib.LibInfo;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;

/**
 * @author MrArcane111
 *
 */
public class ModBlocks 
{
	public static Block blockCastIron;
	public static Block blockVolucite;
	public static Block blockBrass;
	public static Block blockUranium;
	public static Block blockSlateTile;
	public static Block blockAluminum, blockCopper, blockTin, blockZinc;
	public static Block blockBronze, blockSteel;
	
	public static BlockStairs stairsSlateTile;
	
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
	public static Block brassWood;
	public static Block brassLeaves;
	public static Block teaPlant;
	public static Block castIronLampIdle;
	public static Block castIronLampActive;
	public static Block smog;
	
	public static void initBlocks()
	{
		blockCastIron = new BlockMod(BlockIDs.blockCastIronID, Material.iron).setHardness(7F).setResistance(20F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("castironblock");
		blockVolucite = new BlockMod(BlockIDs.blockVoluciteID, Material.rock).setHardness(50F).setResistance(6000000F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("voluciteblock");
		blockBrass = new BlockMod(BlockIDs.blockBrassID, Material.iron).setHardness(5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("brassblock");
		blockUranium = new BlockUranium(BlockIDs.blockUraniumID);
		blockAluminum = new BlockMod(BlockIDs.blockAluminumID, Material.iron).setHardness(5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("aluminumblock");
		blockCopper = new BlockMod(BlockIDs.blockCopperID, Material.iron).setHardness(5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("copperblock");
		blockTin = new BlockMod(BlockIDs.blockTinID, Material.iron).setHardness(5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("tinblock");
		blockZinc = new BlockMod(BlockIDs.blockZincID, Material.iron).setHardness(5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("zincblock");
		blockBronze = new BlockMod(BlockIDs.blockBronzeID, Material.iron).setHardness(5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("bronzeblock");
		blockSteel = new BlockMod(BlockIDs.blockSteelID, Material.iron).setHardness(5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("steelblock");
		blockSlateTile = new BlockMod(BlockIDs.blockSlateTileID, Material.rock).setUnlocalizedName("slatetiles");
		
		//stairsSlateTile = new (BlockStairs) BlockSC(SC2_BlockIDs.stairsSlateTileID, blockSlateTile, 0);
		
		decorIron = new BlockMod(BlockIDs.decorIronID, Material.iron).setHardness(5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("engriron");
		decorGold = new BlockMod(BlockIDs.decorGoldID, Material.iron).setHardness(3F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("engrgold");
		decorDiamond = new BlockMod(BlockIDs.decorDiamondID, Material.rock).setHardness(5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("engrdiamond");
		decorCastIron = new BlockMod(BlockIDs.decorCastIronID, Material.iron).setHardness(7F).setResistance(20F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("engrcastiron");
		decorVolucite = new BlockMod(BlockIDs.decorVoluciteID, Material.rock).setHardness(50F).setResistance(6000000F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("engrvolucite");
		decorBrass = new BlockMod(BlockIDs.decorBrassID, Material.iron).setHardness(5F).setResistance(10F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("engrbrass");
		decorLapis = new BlockMod(BlockIDs.decorLapisID, Material.rock).setHardness(3F).setResistance(5F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("engrlapis");
		decorStone = new BlockMod(BlockIDs.decorStoneID, Material.rock).setHardness(2F).setResistance(10F).setStepSound(Block.soundStoneFootstep).setUnlocalizedName("carvedstone");
		decorUranium = new BlockUranium(BlockIDs.decorUraniumID).setHardness(10F).setResistance(6F).setLightValue(0.625F).setStepSound(Block.soundMetalFootstep).setUnlocalizedName("engruranium");
		
		gateCastIron = new BlockCastIronGate(BlockIDs.gateCastIronID, Material.iron);
		railingCastIron = new BlockFence(BlockIDs.railingCastIronID, LibInfo.SC2_PREFIX + "castironblock", Material.iron).setHardness(7F).setResistance(20F).setStepSound(Block.soundMetalFootstep).setCreativeTab(CreativeTabsMod.tabSCBlocks);//.setUnlocalizedName("castironblock");
		lampOn = new BlockLamp(BlockIDs.lampOnID, true).setUnlocalizedName("lampblockon").setCreativeTab(null);
		lampOff = new BlockLamp(BlockIDs.lampOffID, false).setUnlocalizedName("lampblockoff");
		brassWood = new BlockBrassLog(BlockIDs.woodBrassID);
		brassLeaves = new BlockMod(BlockIDs.leavesLampID, Material.wood).setHardness(2F).setLightOpacity(1).setLightValue(0.9375F).setStepSound(Block.soundGlassFootstep).setUnlocalizedName("brassleaves");
		teaPlant = new BlockTeaPlant(BlockIDs.teaPlantID);
		castIronLampIdle = new BlockCastIronLamp(BlockIDs.castIronLampIdleID, false).setCreativeTab(CreativeTabsMod.tabSCBlocks);
		castIronLampActive = new BlockCastIronLamp(BlockIDs.castIronLampActiveID, true);
		smog = new BlockSmog(BlockIDs.smogID, Material.air).setUnlocalizedName("smog");

		ModMachines.initMachines();
		ModOres.initOres();
		ModPowerBlocks.initPowerBlocks();
		registerBlocks();
	}
	
	public static void registerBlocks()
	{	
		GameRegistry.registerBlock(blockCastIron, "blockcastiron");
		GameRegistry.registerBlock(blockVolucite, "blockvolucite");
		GameRegistry.registerBlock(blockBrass, "blockbrass");
		GameRegistry.registerBlock(lampOn, "lampon");
		GameRegistry.registerBlock(lampOff, "lampoff");
		GameRegistry.registerBlock(brassWood, "woodbrass");
		GameRegistry.registerBlock(brassLeaves, "leaveslamp");
		GameRegistry.registerBlock(railingCastIron, "railingcastiron");
		GameRegistry.registerBlock(gateCastIron, "gatecastiron");
		GameRegistry.registerBlock(decorIron, "decoriron");
		GameRegistry.registerBlock(decorGold, "decorgold");
		GameRegistry.registerBlock(decorDiamond, "decordiamond");
		GameRegistry.registerBlock(decorCastIron, "decorcastiron");
		GameRegistry.registerBlock(decorVolucite, "decorvolucite");
		GameRegistry.registerBlock(decorBrass, "decorbrass");
		GameRegistry.registerBlock(decorLapis, "decorlapis");
		GameRegistry.registerBlock(decorStone, "decorstone");
		GameRegistry.registerBlock(blockUranium, "blockuranium");
		GameRegistry.registerBlock(decorUranium, "decoruranium");
		GameRegistry.registerBlock(teaPlant, "teaplant");
		GameRegistry.registerBlock(castIronLampIdle, "castironlampidle");
		GameRegistry.registerBlock(castIronLampActive, "castironlampactive");
		GameRegistry.registerBlock(blockSlateTile, "slatetiles");
		GameRegistry.registerBlock(blockAluminum, "aluminumblock");
		GameRegistry.registerBlock(blockCopper, "copperblock");
		GameRegistry.registerBlock(blockTin, "tinblock");
		GameRegistry.registerBlock(blockZinc, "zincblock");
		GameRegistry.registerBlock(blockBronze, "bronzeblock");
		GameRegistry.registerBlock(blockSteel, "steelblock");
		GameRegistry.registerBlock(smog, "smog");
	}
}