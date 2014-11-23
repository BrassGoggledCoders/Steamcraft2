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
	public static final String CATEGORY_GEN_CLUSTERS = "clusters size";

	// Generation
	public static boolean generationEnabled;

	public static boolean overworldGenerationEnabled;
	public static boolean netherGenerationEnabled;
	public static boolean endGenerationEnabled;
	public static boolean deepsGenerationEnabled;

	public static boolean brassTreeGenEnabled;

	public static boolean aluminumOreGenEnabled, copperOreGenEnabled, tinOreGenEnabled, zincOreGenEnabled,
			uraniumOreGenEnabled, brimstoneOreGenEnabled, phosphateOreGenEnabled;

	public static boolean netherEtheriumCrystalGenEnabled;
	public static boolean endEtheriumCrystalGenEnabled;

	public static boolean slateGenEnabled;

	// Cluster size
	public static int brassTreeGenCluster;

	public static int aluminumCluster, copperCluster, tinCluster, zincCluster, uraniumCluster, brimstoneCluster, phosphateCluster;

	public static int netherEtheriumCrystalCluster;
	public static int endEtheriumCrystalCluster;

	public static int slateCluster;

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
			deepsGenerationEnabled = config.get(CATEGORY_GEN_OPTIONS, "All Deeps Generation", true).getBoolean(true);

			brassTreeGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Brass Tree Gen", true).getBoolean(true);

			aluminumOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Aluminum Ore", true).getBoolean(true);
			copperOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Copper Ore", true).getBoolean(true);
			tinOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Tin Ore", true).getBoolean(true);
			zincOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Zinc Ore", true).getBoolean(true);
			uraniumOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Uranium Ore", true).getBoolean(true);
			brimstoneOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Brimstone Ore", true).getBoolean(true);
			phosphateOreGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Phosphate Ore", true).getBoolean(true);
			slateGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Slate", true).getBoolean(true);

			netherEtheriumCrystalGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Etherium Crystal (Nether)", true).getBoolean(true);
			endEtheriumCrystalGenEnabled = config.get(CATEGORY_GEN_OPTIONS, "Etherium Crystal (End)", true).getBoolean(true);

			// Cluster size
			brassTreeGenCluster = config.get(CATEGORY_GEN_CLUSTERS, "Brass Tree (Forest Type Biomes Only)", 1).getInt();

			aluminumCluster = config.get(CATEGORY_GEN_CLUSTERS, "Aluminum Ore", 4).getInt();
			copperCluster = config.get(CATEGORY_GEN_CLUSTERS, "Copper Ore", 7).getInt();
			tinCluster = config.get(CATEGORY_GEN_CLUSTERS, "Tin Ore", 7).getInt();
			zincCluster = config.get(CATEGORY_GEN_CLUSTERS, "Zinc Ore", 5).getInt();
			uraniumCluster = config.get(CATEGORY_GEN_CLUSTERS, "Uranium Ore", 2).getInt();
			brimstoneCluster = config.get(CATEGORY_GEN_CLUSTERS, "Brimstone Ore", 3).getInt();
			phosphateCluster = config.get(CATEGORY_GEN_CLUSTERS, "Phosphate Ore", 3).getInt();
			slateCluster = config.get(CATEGORY_GEN_CLUSTERS, "Slate", 30).getInt();

			netherEtheriumCrystalCluster = config.get(CATEGORY_GEN_CLUSTERS, "Etheruim Crystal (Nether)", 5).getInt();
			endEtheriumCrystalCluster = config.get(CATEGORY_GEN_CLUSTERS, "Etheruim Crystal (End)", 10).getInt();
		}
		catch(Exception e)
		{
			LoggerSteamcraft.log(Level.SEVERE, "Failed to load configuration file:" + e);
		}
		finally
		{
			if(config.hasChanged())
				config.save();
		}
	}

}
