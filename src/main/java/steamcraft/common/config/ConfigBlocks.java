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
import steamcraft.common.blocks.BlockFluidSteam;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.TileCrystal;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class ConfigBlocks
{
    public static Block blockCustomOre;
    public static Block blockCosmetic;
    public static Block blockEngraved;
    public static Block blockCastIronFence;
    public static Block blockCastIronGate;
    public static Block blockCrystal;
    public static Block blockCastIronLampI;
	public static Block blockCastIronLampA;

	public static Block blockLampI;
	public static Block blockLampA;
	public static Block blockTeaPlant;
	public static Block blockSmog;
	public static Block blockFluidSteam;
	
	public static Fluid steamFluid;

    public static BlockStairs blockSlateTileStairs;

    public static int blockCrystalRI = -1;
    public static int blockSmogRI = 1;

	public static void init()
	{
		initializeBlocks();
		registerBlocks();
		registerTileEntities();
	}
	
	public static void initializeBlocks()
	{
        blockCustomOre = new BlockCustomOre(Config.blockCustomOreId);
        blockCosmetic = new BlockCosmeticSolid(Config.blockCosmeticId);
        blockEngraved = new BlockEngravedSolid(Config.blockEngravedId);
        blockCastIronFence = new BlockCastIronFence(Config.blockCastIronFenceId);
        blockCastIronGate = new BlockCastIronGate(Config.blockCastIronGateId);
        blockCrystal = new BlockCrystal(Config.blockCrystalId);
        blockCastIronLampI = new BlockCastIronLamp(Config.blockCastIronLampIId, false);
        blockCastIronLampA = new BlockCastIronLamp(Config.blockCastIronLampAId, true);
        
		Fluid steamFluid = new Fluid("steam").setGaseous(true).setTemperature(700).setDensity(-100).setViscosity(500).setLuminosity(1);
		FluidRegistry.registerFluid(steamFluid);
        blockFluidSteam = new BlockFluidSteam(steamFluid, Material.water);
	}
	
	public static void registerBlocks()
	{
        GameRegistry.registerBlock(blockCustomOre, BlockCustomOreItem.class, "BlockCustomOre"); // If you want different hardness/resistances/light values,
        GameRegistry.registerBlock(blockCosmetic, BlockCosmeticSolidItem.class, "BlockCosmeticSolid"); // then use the actual methods and check by metadata;
        GameRegistry.registerBlock(blockEngraved, BlockEngravedSolidItem.class, "BlockEngravedSolid"); // same goes for this too
        GameRegistry.registerBlock(blockCastIronFence, "BlockCastIronFence");
        GameRegistry.registerBlock(blockCastIronGate, "BlockCastIronGate");
        GameRegistry.registerBlock(blockCrystal, "BlockCrystal");
        GameRegistry.registerBlock(blockCastIronLampI, "BlockCastIronLampI");
        GameRegistry.registerBlock(blockCastIronLampA, "BlockCastIronLampA");
        GameRegistry.registerBlock(blockFluidSteam, "BlockFluidSteam");
    }
	
	private static void registerTileEntities()
	{
        GameRegistry.registerTileEntity(TileCrystal.class, LibInfo.ID + "TECrystal");
	}
}
