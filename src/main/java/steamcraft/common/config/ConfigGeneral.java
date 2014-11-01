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

public class ConfigGeneral
{
	public static Configuration config;

	public static final String CATEGORY_GENERAL = "general";
	public static final String CATEGORY_CLIENT = "client";

	public static boolean partyPooper;
	public static boolean drawFluid;
	public static boolean drawInside;
	public static double armorSpawnChance;

	public static int etheriumMaterialID;

	public static int deepsDimensionID;

	public static boolean naturalLightningStrikes;

	public static int depthsBiomeID;

	public static void initialize(File configFile)
	{
		config = new Configuration(configFile);
		try
		{
			config.load();

			partyPooper = config.get(CATEGORY_GENERAL, "Remove all little fun things from mod :(", false).getBoolean();
			armorSpawnChance = config.get(CATEGORY_GENERAL, "Spawn Chance of our Armor on Zombies/Skeletons (0.0-1.0)", 0.2D).getDouble();

			drawFluid = config.get(CATEGORY_CLIENT, "Render fluid inside blocks", true).getBoolean();
			drawInside = config.get(CATEGORY_CLIENT, "Render the insides of blocks", true).getBoolean();

			etheriumMaterialID = config.get(CATEGORY_GENERAL, "Etherium Tool Material ID for Tinker's Construct Intergration", 66).getInt();

			naturalLightningStrikes = config
					.get(CATEGORY_GENERAL,
							"Lightning Strike behavior. If false, it will simulate strikes during stormy weather with a chance that can be set in the balance config. If true it will activate when any bolt hits it, which is a lot rarer than naturally.",
							false).getBoolean(false);
			deepsDimensionID = config.get(CATEGORY_GENERAL, "Dimension ID for the Deeps dimension", -8).getInt();
			depthsBiomeID = config.get(CATEGORY_GENERAL, "Biome ID for Depths Biome", 230).getInt();
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
