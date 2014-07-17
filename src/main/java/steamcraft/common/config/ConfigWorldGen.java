package steamcraft.common.config;

import java.io.File;
import java.util.logging.Level;

import steamcraft.common.lib.LoggerSteamcraft;
import net.minecraftforge.common.config.Configuration;

public class ConfigWorldGen
{
	public static Configuration config;

	public static final String CATEGORY_GEN_OPTIONS = "generation options";

	//Chances
	public static int uraniumChance, brimstoneChance, borniteChance, phosphateChance, aluminumChance, copperChance, tinChance, zincChance;

	public static int netherEtheriumCrystalChance;
	public static int endEtheriumCrystalChance;

	public static int slateChance;

	//Heights
	public static int uraniumHeight, brimstoneHeight, borniteHeight, phosphateHeight, aluminumHeight, copperHeight, tinHeight, zincHeight;

	public static int netherEtheriumCrystalHeight;
	public static int endEtheriumCrystalHeight;

	public static int slateHeight;

	//Generation
	public static boolean generationEnabled;
	public static boolean overworldGenerationEnabled;
	public static boolean netherGenerationEnabled;
	public static boolean endGenerationEnabled;

	public static boolean uraniumOreGenEnabled, brimstoneOreGenEnabled, borniteOreGenEnabled, phosphateOreGenEnabled, aluminumOreGenEnabled, copperOreGenEnabled,
		tinOreGenEnabled, zincOreGenEnabled;

	public static boolean netherEtheriumCrystalGenEnabled;
	public static boolean endEtheriumCrystalGenEnabled;

	public static boolean slateGenEnabled;

	public static boolean brassTreeGenEnabled;

	public static int brassTreeGenChance;

	public static void initialize(File configFile)
	{
		config = new Configuration(configFile);
		try
		{
			config.load();
		//Chances
		uraniumChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Uranium Ore Generation. Percentage chance per chunk", 5).getInt();
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
		brassTreeGenChance = config.get(CATEGORY_GEN_OPTIONS, "Chance of Brass Tree Generation", 2).getInt();

		//Heights
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

		//Generation
		generationEnabled = config.get(CATEGORY_GEN_OPTIONS, " ALL SC2 World Gen, everywhere", true).getBoolean(true);
		overworldGenerationEnabled = config.get(CATEGORY_GEN_OPTIONS, "SC2 World Gen in the Overworld", true).getBoolean(true);
		netherGenerationEnabled = config.get(CATEGORY_GEN_OPTIONS, "SC2 World Gen in the Nether", true).getBoolean(true);
		endGenerationEnabled = config.get(CATEGORY_GEN_OPTIONS, "SC2 World Gen in the End", true).getBoolean(true);

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

		brassTreeGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Brass Tree Gen", true).getBoolean(true);

		}
		catch (Exception e)
		{
			LoggerSteamcraft.log(Level.SEVERE, "Failed to load configuration file:" + e);
		}
		finally
		{
			if (config.hasChanged()) {
				config.save();
			}
		}
	}


}
