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

import net.minecraftforge.common.Configuration;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class Config
{
	public static Configuration config;
	public static final String CATEGORY_ENTITIES = "Entities";

	public static String CATEGORY_GEN_OPTIONS = "Generation";
	
	public static int blockCustomOreId;
	public static int blockCosmeticId;
	public static int blockEngravedId;
	public static int blockCastIronFenceId;
	public static int blockCastIronGateId;
	public static int blockCrystalId;
	public static int blockCastIronLampIId;
	public static int blockCastIronLampAId;
	public static int blockSteamFluidId;
	public static int blockSteamBoilerId;	
	public static int blockMetalId;
	public static int blockHatchId;
	public static int blockSlateId;
	
	public static int itemMechanicalId;
    public static int itemResourceId;
    public static int itemWatchId;
    public static int itemTeaSeedId;
    public static int itemChiselId;
    
    public static int itemGogglesId;
    public static int itemAqualungId;
    public static int itemLegBracesId;
    public static int itemRollerSkatesId;
    
    public static int itemHelmetEtheriumId;
    public static int itemPlateEtheriumId;
    public static int itemLegsEtheriumId;
    public static int itemBootsEtheriumId;
    
	public static int itemHelmetObsidianId;
	public static int itemPlateObsidianId;
	public static int itemLegsObsidianId;
	public static int itemBootsObsidianId;
    
    public static int itemTeacupEmptyId;
    public static int itemTeacupFullId;
    public static int itemKettleEmptyId;
    public static int itemKettleHotId;
    public static int itemKettleColdId;
    public static int itemGunPartsId;
    
    public static int entBulletId;

    /* POWER TO THE END USER! Good heaves... */
    public static int etheriumChance;
    public static int uraniumChance;
    public static int brimstoneChance;
    public static int borniteChance;
    public static int phosphateChance;
    public static int aluminumChance;
    public static int copperChance;
    public static int tinChance;
    public static int zincChance;
    public static int netherEtheriumCrystalChance;
    public static int endEtheriumCrystalChance;
    public static int etheriumHeight;
    public static int uraniumHeight;
    public static int brimstoneHeight;
    public static int borniteHeight;
    public static int phosphateHeight;
    public static int aluminumHeight;
    public static int copperHeight;
    public static int tinHeight;
    public static int zincHeight;
    public static int netherEtheriumCrystalHeight;
    public static int endEtheriumCrystalHeight;

    public static boolean generationEnabled;
	public static boolean overworldGenerationEnabled;
	public static boolean netherGenerationEnabled;
	public static boolean endGenerationEnabled;
	public static boolean oreGenerationEnabled;
	public static boolean etheriumOreGenEnabled;
	public static boolean uraniumOreGenEnabled;
	public static boolean brimstoneOreGenEnabled;
	public static boolean borniteOreGenEnabled;
	public static boolean phosphateOreGenEnabled;
	public static boolean aluminumOreGenEnabled;
	public static boolean copperOreGenEnabled;
	public static boolean tinOreGenEnabled;
	public static boolean zincOreGenEnabled;
	public static boolean netherEtheriumCrystalGenEnabled;
	public static boolean endEtheriumCrystalGenEnabled;

	public static void initialize(File file)
	{
		config = new Configuration(file);
		config.addCustomCategoryComment("Entities", "Entity Ids");
		
		int eIdx = 300;
		entBulletId = config.get("Entities", "bullet", eIdx++).getInt();
		
		int bIdx = 2600;
		blockCustomOreId = config.getBlock("BlockCustomOre", bIdx++).getInt();
		blockCosmeticId = config.getBlock("BlockCosmeticSolid", bIdx++).getInt();
		blockEngravedId = config.getBlock("BlockEngravedSolid", bIdx++).getInt();
		blockCastIronFenceId = config.getBlock("BlockCastIronFence", bIdx++).getInt();
		blockCastIronGateId = config.getBlock("BlockCastIronGate", bIdx++).getInt();
		blockCrystalId = config.getBlock("BlockCrystal", bIdx++).getInt();
		blockCastIronLampIId = config.getBlock("BlockCastIronLamp (OFF)", bIdx++).getInt();
		blockCastIronLampAId = config.getBlock("BlockCastIronLamp (ON)", bIdx++).getInt();
		blockSteamFluidId = config.getBlock("BlockSteamFluid", bIdx++).getInt();
		blockSteamBoilerId = config.getBlock("BlockSteamBoiler", bIdx++).getInt();
		blockMetalId = config.getBlock("BlockMetal", bIdx++).getInt();
		blockHatchId = config.getBlock("BlockHatch", bIdx++).getInt();
		blockSlateId = config.getBlock("BlockSlate", bIdx++).getInt();
		
		int iIdx = 27000;
		itemMechanicalId = config.getItem("ItemMechanical", iIdx++).getInt();
		itemResourceId = config.getItem("ItemResource", iIdx++).getInt();
		itemWatchId = config.getItem("ItemWatch", iIdx++).getInt();
		itemTeaSeedId = config.getItem("ItemTeaSeed", iIdx++).getInt();
		itemChiselId = config.getItem("ItemChisel", iIdx++).getInt();
		
		itemGogglesId = config.getItem("ItemBrassGoggles", iIdx++).getInt();
		itemAqualungId = config.getItem("ItemAqualung", iIdx++).getInt();
		itemLegBracesId = config.getItem("ItemLegBraces", iIdx++).getInt();
		itemRollerSkatesId = config.getItem("ItemRollerSkates", iIdx++).getInt();
		
		itemHelmetEtheriumId = config.getItem("ItemHelmetEtherium", iIdx++).getInt();
		itemPlateEtheriumId = config.getItem("ItemPlateEtherium", iIdx++).getInt();
		itemLegsEtheriumId = config.getItem("ItemLegsEtherium", iIdx++).getInt();
		itemBootsEtheriumId = config.getItem("ItemBootsEtherium", iIdx++).getInt();
		
		itemHelmetObsidianId = config.getItem("ItemHelmetObsidian", iIdx++).getInt();
		itemPlateObsidianId = config.getItem("ItemPlateObsidian", iIdx++).getInt();
		itemLegsObsidianId = config.getItem("ItemLegsObsidian", iIdx++).getInt();
		itemBootsObsidianId = config.getItem("ItemBootsObsidian", iIdx++).getInt();
		
		itemTeacupEmptyId = config.getItem("ItemLiquidFood", iIdx++).getInt();
		
		etheriumChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Etherium Ore Generation. (Higher value = more ore. 0 disables.)", 1).getInt();
        uraniumChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Uranium Ore Generation", 3).getInt();
        brimstoneChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Brimstone Ore Generation", 10).getInt();
        borniteChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Bornite Ore Generation", 20).getInt();
        phosphateChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Phosphate Ore Generation", 3).getInt();
        aluminumChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Aluminum Ore Generation", 10).getInt();
        copperChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Copper Ore Generation", 10).getInt();
        tinChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Tin Ore Generation", 10).getInt();
        zincChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Zinc Ore Generation", 10).getInt();
        netherEtheriumCrystalChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Etheruim Crystal Generation in the Nether", 3).getInt();
        endEtheriumCrystalChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Etheruim Crystal Generation in the End", 3).getInt();
        etheriumHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Etherium Ore Generation. Ore will generate below this Y level in world", 12).getInt();
        uraniumHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Uranium Ore Generation", 24).getInt();
        brimstoneHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Brimstone Ore Generation", 64).getInt();
        borniteHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Bornite Ore Generation", 48).getInt();
        phosphateHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Phosphate Ore Generation", 36).getInt();
        aluminumHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Aluminum Ore Generation",36).getInt();
        copperHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Copper Ore Generation", 36).getInt();
        tinHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Tin Ore Generation", 36).getInt();
        zincHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Zinc Ore Generation", 36).getInt();
        netherEtheriumCrystalHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Etherium Crystal Generation in the Nether", 36).getInt();
        endEtheriumCrystalHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Etherium Crystal Generation in the End", 36).getInt();
        generationEnabled = config.get(CATEGORY_GEN_OPTIONS, " ALL SC2 World Gen, everywhere", true).getBoolean(true);
        overworldGenerationEnabled = config.get(CATEGORY_GEN_OPTIONS, "SC2 World Gen in the Overworld", true).getBoolean(true);
        netherGenerationEnabled = config.get(CATEGORY_GEN_OPTIONS, "SC2 World Gen in the Nether", true).getBoolean(true);
        endGenerationEnabled = config.get(CATEGORY_GEN_OPTIONS, "SC2 World Gen in the End", true).getBoolean(true);
        oreGenerationEnabled = config.get(CATEGORY_GEN_OPTIONS, "SC2 Ore Gen", true).getBoolean(true);
        etheriumOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Etherium Ore Generation", true).getBoolean(true);
        uraniumOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Uranium Ore Generation", true).getBoolean(true);
        brimstoneOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Brimstone Ore Generation", true).getBoolean(true);
        borniteOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Bornite Ore Generation", true).getBoolean(true);
        phosphateOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Phosphate Ore Generation", true).getBoolean(true);
        aluminumOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Aluminum Ore Generation", true).getBoolean(true);
        copperOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Copper Ore Generation", true).getBoolean(true);
        tinOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Tin Ore Generation", true).getBoolean(true);
        zincOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Zinc Ore Generation", true).getBoolean(true);
        netherEtheriumCrystalGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Etherium Crystal Generation in the Nether", true).getBoolean(true);
        endEtheriumCrystalGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Etherium Crystal Generation in the End", true).getBoolean(true);

	}

	public static void save()
	{
		config.save();
	}

	public static void initLoot() {}

	public static void initModCompatibility() {}

	public static void registerBiomes() {}
}
