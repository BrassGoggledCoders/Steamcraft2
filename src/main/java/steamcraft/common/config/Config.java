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
 * File created @ [Mar 12, 2014, 4:26:28 PM]
 */
package steamcraft.common.config;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class Config
{
	public static Configuration config;

	public static int blockCustomOreId;
	public static int blockCosmeticId;
	public static int blockEngravedId;
	public static int blockCastIronFenceId;
	public static int blockCastIronGateId;
	public static int blockCrystalId;
	public static int blockCastIronLampIId;
	public static int blockCastIronLampAId;
	
	public static int itemMechanicalId;
    public static int itemResourceId;
    public static int itemWatchId;
    public static int itemTeaSeedId;
    public static int itemChiselId;
    public static int itemGogglesId;

	public static void initialize(File file)
	{
		config = new Configuration(file);
		
		int bIdx = 2600;
		blockCustomOreId = config.getBlock("BlockCustomOre", bIdx++).getInt();
		blockCosmeticId = config.getBlock("BlockCosmeticSolid", bIdx++).getInt();
		blockEngravedId = config.getBlock("BlockEngravedSolid", bIdx++).getInt();
		blockCastIronFenceId = config.getBlock("BlockCastIronFence", bIdx++).getInt();
		blockCastIronGateId = config.getBlock("BlockCastIronGate", bIdx++).getInt();
		blockCrystalId = config.getBlock("BlockCrystal", bIdx++).getInt();
		blockCastIronLampIId = config.getBlock("BlockCastIronLamp (OFF)", bIdx++).getInt();
		blockCastIronLampAId = config.getBlock("BlockCastIronLamp (ON)", bIdx++).getInt();
		
		int iIdx = 27000;
		itemMechanicalId = config.getItem("ItemMechanical", iIdx++).getInt();
		itemResourceId = config.getItem("ItemResource", iIdx++).getInt();
		itemWatchId = config.getItem("ItemWatch", iIdx++).getInt();
		itemTeaSeedId = config.getItem("ItemTeaSeed", iIdx++).getInt();
		itemChiselId = config.getItem("ItemChisel", iIdx++).getInt();
		itemGogglesId = config.getItem("ItemCustomArmor", iIdx++).getInt();
	}

	public static void save()
	{
		config.save();
	}

	public static void initLoot() {}

	public static void initModCompatibility() {}

	public static void registerBiomes() {}
}
