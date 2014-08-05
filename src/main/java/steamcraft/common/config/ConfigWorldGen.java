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
package steamcraft.common.config;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.config.Configuration;
import steamcraft.common.lib.LoggerSteamcraft;

public class ConfigWorldGen
{
	public static Configuration config;

	public static final String CATEGORY_GEN_OPTIONS = "general";
	public static final String CATEGORY_GEN_CLUSTERS = "clusters per chunk";
	public static final String CATEGORY_GEN_HEIGHT = "heights";

	// Generation
	public static boolean generationEnabled;

	public static boolean overworldGenerationEnabled;
	public static boolean netherGenerationEnabled;
	public static boolean endGenerationEnabled;

	public static boolean uraniumOreGenEnabled, brimstoneOreGenEnabled, borniteOreGenEnabled, phosphateOreGenEnabled, aluminumOreGenEnabled,
			copperOreGenEnabled, tinOreGenEnabled, zincOreGenEnabled;

	public static boolean netherEtheriumCrystalGenEnabled;
	public static boolean endEtheriumCrystalGenEnabled;

	public static boolean slateGenEnabled;

	public static boolean brassTreeGenEnabled;

	// Clusters
	public static int uraniumCluster, brimstoneCluster, borniteCluster, phosphateCluster, aluminumCluster, copperCluster, tinCluster, zincCluster;

	public static int netherEtheriumCrystalCluster;
	public static int endEtheriumCrystalCluster;

	public static int slateCluster;

	public static int brassTreeGenCluster;

	// Heights Max
	public static int uraniumHeightMax, brimstoneHeightMax, borniteHeightMax, phosphateHeightMax, aluminumHeightMax, copperHeightMax, tinHeightMax,
			zincHeightMax;

	public static int netherEtheriumCrystalHeightMax;
	public static int endEtheriumCrystalHeightMax;

	public static int slateHeightMax;

	// Heights Min
	public static int uraniumHeightMin, brimstoneHeightMin, borniteHeightMin, phosphateHeightMin, aluminumHeightMin, copperHeightMin, tinHeightMin,
			zincHeightMin;

	public static int netherEtheriumCrystalHeightMin;
	public static int endEtheriumCrystalHeightMin;

	public static int slateHeightMin;

	public static void initialize(File configFile)
	{
		config = new Configuration(configFile);
		try
		{
			config.load();

			// Generation
			generationEnabled = config.get(CATEGORY_GEN_OPTIONS, "ALL SC2 World Gen, everywhere", true).getBoolean(true);

			overworldGenerationEnabled = config.get(CATEGORY_GEN_OPTIONS, "All Overworld Generation", true).getBoolean(true);
			netherGenerationEnabled = config.get(CATEGORY_GEN_OPTIONS, "All Nether Generation", true).getBoolean(true);
			endGenerationEnabled = config.get(CATEGORY_GEN_OPTIONS, "All End Generation", true).getBoolean(true);

			uraniumOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Uranium Ore", true).getBoolean(true);
			brimstoneOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Brimstone Ore", true).getBoolean(true);
			borniteOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Bornite Ore", true).getBoolean(true);
			phosphateOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Phosphate Ore", true).getBoolean(true);
			aluminumOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Aluminum Ore", true).getBoolean(true);
			copperOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Copper Ore", true).getBoolean(true);
			tinOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Tin Ore", true).getBoolean(true);
			zincOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Zinc Ore", true).getBoolean(true);
			slateGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Slate", true).getBoolean(true);
			netherEtheriumCrystalGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Etherium Crystal (Nether)", true).getBoolean(true);
			endEtheriumCrystalGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Etherium Crystal (End)", true).getBoolean(true);

			brassTreeGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Brass Tree Gen", true).getBoolean(true);

			// Clusters
			uraniumCluster = config.get(CATEGORY_GEN_CLUSTERS, "Uranium Ore", 1).getInt();
			brimstoneCluster = config.get(CATEGORY_GEN_CLUSTERS, "Brimstone Ore", 5).getInt();
			borniteCluster = config.get(CATEGORY_GEN_CLUSTERS, "Bornite Ore", 7).getInt();
			phosphateCluster = config.get(CATEGORY_GEN_CLUSTERS, "Phosphate Ore", 7).getInt();
			aluminumCluster = config.get(CATEGORY_GEN_CLUSTERS, "Aluminum Ore", 10).getInt();
			copperCluster = config.get(CATEGORY_GEN_CLUSTERS, "Copper Ore", 15).getInt();
			tinCluster = config.get(CATEGORY_GEN_CLUSTERS, "Tin Ore", 15).getInt();
			zincCluster = config.get(CATEGORY_GEN_CLUSTERS, "Zinc Ore", 12).getInt();
			slateCluster = config.get(CATEGORY_GEN_CLUSTERS, "Slate", 5).getInt();
			netherEtheriumCrystalCluster = config.get(CATEGORY_GEN_CLUSTERS, "Etheruim Crystal (Nether)", 5).getInt();
			endEtheriumCrystalCluster = config.get(CATEGORY_GEN_CLUSTERS, "Etheruim Crystal (End)", 10).getInt();
			brassTreeGenCluster = config.get(CATEGORY_GEN_CLUSTERS, "Brass Tree (Forest Type Biomes Only)", 0.2).getInt();

			// Heights
			uraniumHeightMax = config.get(CATEGORY_GEN_HEIGHT, "Uranium Ore Max", 24).getInt();
			uraniumHeightMin = config.get(CATEGORY_GEN_HEIGHT, "Uranium Ore Min", 4).getInt();

			brimstoneHeightMax = config.get(CATEGORY_GEN_HEIGHT, "Brimstone Ore Max", 64).getInt();
			brimstoneHeightMin = config.get(CATEGORY_GEN_HEIGHT, "Brimstone Ore Min", 20).getInt();

			borniteHeightMax = config.get(CATEGORY_GEN_HEIGHT, "Bornite Ore Max", 48).getInt();
			borniteHeightMin = config.get(CATEGORY_GEN_HEIGHT, "Bornite Ore Min", 26).getInt();

			phosphateHeightMax = config.get(CATEGORY_GEN_HEIGHT, "Phosphate Ore Max", 36).getInt();
			phosphateHeightMin = config.get(CATEGORY_GEN_HEIGHT, "Phosphate Ore Min", 20).getInt();

			aluminumHeightMax = config.get(CATEGORY_GEN_HEIGHT, "Aluminum Ore Max", 40).getInt();
			aluminumHeightMin = config.get(CATEGORY_GEN_HEIGHT, "Aluminum Ore Min", 20).getInt();

			copperHeightMax = config.get(CATEGORY_GEN_HEIGHT, "Copper Ore Max", 45).getInt();
			copperHeightMin = config.get(CATEGORY_GEN_HEIGHT, "Copper Ore Min", 22).getInt();

			tinHeightMax = config.get(CATEGORY_GEN_HEIGHT, "Tin Ore Max", 45).getInt();
			tinHeightMin = config.get(CATEGORY_GEN_HEIGHT, "Tin Ore Min", 22).getInt();

			zincHeightMax = config.get(CATEGORY_GEN_HEIGHT, "Zinc Ore Max", 36).getInt();
			zincHeightMin = config.get(CATEGORY_GEN_HEIGHT, "Zinc Ore Min", 20).getInt();

			slateHeightMax = config.get(CATEGORY_GEN_HEIGHT, "Slate Max", 70).getInt();
			slateHeightMin = config.get(CATEGORY_GEN_HEIGHT, "Slate Min", 40).getInt();

			netherEtheriumCrystalHeightMax = config.get(CATEGORY_GEN_HEIGHT, "Etherium Crystal Max (Nether)", 120).getInt();
			netherEtheriumCrystalHeightMin = config.get(CATEGORY_GEN_HEIGHT, "Etherium Crystal Min (Nether)", 30).getInt();

			endEtheriumCrystalHeightMax = config.get(CATEGORY_GEN_HEIGHT, "Etherium Crystal Max (End)", 100).getInt();
			endEtheriumCrystalHeightMin = config.get(CATEGORY_GEN_HEIGHT, "Etherium Crystal Min (End)", 20).getInt();
		} catch (Exception e)
		{
			LoggerSteamcraft.log(Level.SEVERE, "Failed to load configuration file:" + e);
		} finally
		{
			if (config.hasChanged())
				config.save();
		}
	}

}
