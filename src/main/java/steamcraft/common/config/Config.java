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

import net.minecraftforge.common.config.Configuration;

// TODO: Auto-generated Javadoc
/**
 * The Class Config.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class Config
{

	/** The config. */
	public static Configuration config;

	/** The Constant CATEGORY_ENTITIES. */
	public static final String CATEGORY_ENTITIES = "Entities";

	/** The Constant CATEGORY_GEN_OPTIONS. */
	public static final String CATEGORY_GEN_OPTIONS = "generation options";

	public static final String CATEGORY_GENERAL = "general";

	// Entities
	/** The ent bullet id. */
	//TODO: Remove (unneeded)
	public static int entBulletId;

	/* POWER TO THE END USER! Good heavens... */
	/** The phosphate chance. */
	public static int uraniumChance, brimstoneChance, borniteChance, phosphateChance;

	/** The aluminum chance. */
	public static int aluminumChance;

	/** The copper chance. */
	public static int copperChance;

	/** The tin chance. */
	public static int tinChance;

	/** The zinc chance. */
	public static int zincChance;

	/** The nether etherium crystal chance. */
	public static int netherEtheriumCrystalChance;

	/** The end etherium crystal chance. */
	public static int endEtheriumCrystalChance;

	/** The slate chance. */
	public static int slateChance;

	/** The uranium height. */
	public static int uraniumHeight;

	/** The brimstone height. */
	public static int brimstoneHeight;

	/** The bornite height. */
	public static int borniteHeight;

	/** The phosphate height. */
	public static int phosphateHeight;

	/** The aluminum height. */
	public static int aluminumHeight;

	/** The copper height. */
	public static int copperHeight;

	/** The tin height. */
	public static int tinHeight;

	/** The zinc height. */
	public static int zincHeight;

	/** The nether etherium crystal height. */
	public static int netherEtheriumCrystalHeight;

	/** The end etherium crystal height. */
	public static int endEtheriumCrystalHeight;

	/** The slate height. */
	public static int slateHeight;

	/** The generation enabled. */
	public static boolean generationEnabled;

	/** The overworld generation enabled. */
	public static boolean overworldGenerationEnabled;

	/** The nether generation enabled. */
	public static boolean netherGenerationEnabled;

	/** The end generation enabled. */
	public static boolean endGenerationEnabled;

	/** The ore generation enabled. */
	public static boolean oreGenerationEnabled;

	/** The uranium ore gen enabled. */
	public static boolean uraniumOreGenEnabled;

	/** The brimstone ore gen enabled. */
	public static boolean brimstoneOreGenEnabled;

	/** The bornite ore gen enabled. */
	public static boolean borniteOreGenEnabled;

	/** The phosphate ore gen enabled. */
	public static boolean phosphateOreGenEnabled;

	/** The aluminum ore gen enabled. */
	public static boolean aluminumOreGenEnabled;

	/** The copper ore gen enabled. */
	public static boolean copperOreGenEnabled;

	/** The tin ore gen enabled. */
	public static boolean tinOreGenEnabled;

	/** The zinc ore gen enabled. */
	public static boolean zincOreGenEnabled;

	/** The nether etherium crystal gen enabled. */
	public static boolean netherEtheriumCrystalGenEnabled;

	/** The end etherium crystal gen enabled. */
	public static boolean endEtheriumCrystalGenEnabled;

	/** The slate gen enabled. */
	public static boolean slateGenEnabled;

	public static boolean partyPooper;

	/**
	 * Initialize.
	 *
	 * @param file
	 *            the file
	 */
	public static void initialize(File file)
	{
		config = new Configuration(file);
		config.addCustomCategoryComment("Entities", "Entity Ids");

		int eIdx = 300;
		entBulletId = config.get("Entities", "bullet", eIdx++).getInt();

		uraniumChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Uranium Ore Generation", 5).getInt();
		brimstoneChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Brimstone Ore Generation", 15).getInt();
		borniteChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Bornite Ore Generation", 15).getInt();
		phosphateChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Phosphate Ore Generation", 7).getInt();
		aluminumChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Aluminum Ore Generation", 20).getInt();
		copperChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Copper Ore Generation", 17).getInt();
		tinChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Tin Ore Generation", 17).getInt();
		zincChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Zinc Ore Generation", 15).getInt();
		slateChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Slate Generation", 20).getInt();
		netherEtheriumCrystalChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Etheruim Crystal Generation in the Nether", 5).getInt();
		endEtheriumCrystalChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Etheruim Crystal Generation in the End", 10).getInt();
		uraniumHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Uranium Ore Generation", 24).getInt();
		brimstoneHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Brimstone Ore Generation", 64).getInt();
		borniteHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Bornite Ore Generation", 48).getInt();
		phosphateHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Phosphate Ore Generation", 36).getInt();
		aluminumHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Aluminum Ore Generation", 40).getInt();
		copperHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Copper Ore Generation", 45).getInt();
		tinHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Tin Ore Generation", 45).getInt();
		zincHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Zinc Ore Generation", 36).getInt();
		slateHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Slate Generation", 65).getInt();
		netherEtheriumCrystalHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Etherium Crystal Generation in the Nether", 100).getInt();
		endEtheriumCrystalHeight = config.get(CATEGORY_GEN_OPTIONS, "Height of Etherium Crystal Generation in the End", 100).getInt();
		generationEnabled = config.get(CATEGORY_GEN_OPTIONS, " ALL SC2 World Gen, everywhere", true).getBoolean(true);
		overworldGenerationEnabled = config.get(CATEGORY_GEN_OPTIONS, "SC2 World Gen in the Overworld", true).getBoolean(true);
		netherGenerationEnabled = config.get(CATEGORY_GEN_OPTIONS, "SC2 World Gen in the Nether", true).getBoolean(true);
		endGenerationEnabled = config.get(CATEGORY_GEN_OPTIONS, "SC2 World Gen in the End", true).getBoolean(true);
		/*
		 * oreGenerationEnabled = config.get(CATEGORY_GEN_OPTIONS,
		 * "SC2 Ore Gen", true).getBoolean(true);
		 */
		uraniumOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Uranium Ore Generation", true).getBoolean(true);
		brimstoneOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Brimstone Ore Generation", true).getBoolean(true);
		borniteOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Bornite Ore Generation", true).getBoolean(true);
		phosphateOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Phosphate Ore Generation", true).getBoolean(true);
		aluminumOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Aluminum Ore Generation", true).getBoolean(true);
		copperOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Copper Ore Generation", true).getBoolean(true);
		tinOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Tin Ore Generation", true).getBoolean(true);
		zincOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Zinc Ore Generation", true).getBoolean(true);
		slateGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Slate Generation", true).getBoolean(true);
		netherEtheriumCrystalGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Etherium Crystal Generation in the Nether", true).getBoolean(true);
		endEtheriumCrystalGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Etherium Crystal Generation in the End", true).getBoolean(true);

		partyPooper = config.get(CATEGORY_GENERAL, "Remove all little fun things from mod", false).getBoolean(false);
	}

	/**
	 * Save.
	 */
	public static void save()
	{
		config.save();
	}
}
