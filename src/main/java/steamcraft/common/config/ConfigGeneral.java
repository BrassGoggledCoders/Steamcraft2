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

import net.minecraftforge.common.config.Configuration;
import steamcraft.common.lib.LoggerSteamcraft;

public class ConfigGeneral {
	public static Configuration config;

	public static final String CATEGORY_GENERAL = "general";
	public static final String CATEGORY_CLIENT = "client";

	public static boolean partyPooper;
	public static boolean drawFluid;
	public static boolean drawInside;
	public static double armorSpawnChance;
	public static boolean golemFireDrop;

	public static int deepsDimensionID;

	public static boolean unnaturalLightningStrikes, naturalLightningStrikes, weather2LightningStrikes;

	public static int depthsBiomeID, depthsFBiomeID, depthsMBiomeID, depthsSBiomeID, depthsIBiomeID, depthsSCBiomeID, depthsSCHBiomeID, depthsSWBiomeID, depthsTFBiomeID, depthsJBiomeID;// ,
																																															// depthsOBiomeID,
																																															// depthsBBiomeID;

	public static double spyglassZoom;

	public static String[] oredictMetals;

	public static void initialize(File configFile) {
		config = new Configuration(configFile);
		try {
			config.load();

			partyPooper = config.get(CATEGORY_GENERAL, "Remove all little fun things from mod :(", false).getBoolean();
			golemFireDrop = config.get(CATEGORY_GENERAL, "Iron Golems drop cast iron when dying from fire (Kinda breaks iron farms that use fire drop)", true).getBoolean();
			armorSpawnChance = config.get(CATEGORY_GENERAL, "Spawn Chance of our Armor on Zombies/Skeletons (0.0-1.0)", 0.2D).getDouble();

			drawFluid = config.get(CATEGORY_CLIENT, "Render fluid inside blocks", true).getBoolean();
			drawInside = config.get(CATEGORY_CLIENT, "Render the insides of blocks", true).getBoolean();
			spyglassZoom = config.get(CATEGORY_CLIENT, "FOV modifier the spyglass applies.", 0.3D).getDouble();

			String comment = "Lightning Strike behavior. It will simulate strikes during stormy weather with a chance that can be set in the balance config (recommended).";
			unnaturalLightningStrikes = config.get(CATEGORY_GENERAL, comment, true).getBoolean();
			comment = "Lightning Strike behavior. It will activate when any bolt hits it, which is a lot rarer than unnaturally.";
			naturalLightningStrikes = config.get(CATEGORY_GENERAL, comment, true).getBoolean();
			comment = "Lightning Strike behavior. It will activate when any Weather 2 bolt hits it.";
			weather2LightningStrikes = config.get(CATEGORY_GENERAL, comment, true).getBoolean();

			deepsDimensionID = config.get(CATEGORY_GENERAL, "Dimension ID for the Deeps dimension", -8).getInt();
			int biomeID = 245;
			depthsBiomeID = config.get(CATEGORY_GENERAL, "Biome ID for Depths Biome", biomeID++).getInt();
			depthsFBiomeID = config.get(CATEGORY_GENERAL, "Biome ID for Depths Forest Biome", biomeID++).getInt();
			depthsMBiomeID = config.get(CATEGORY_GENERAL, "Biome ID for Depths Mountains Biome", biomeID++).getInt();
			depthsSBiomeID = config.get(CATEGORY_GENERAL, "Biome ID for Depths Mushroom Forest Biome", biomeID++).getInt();
			depthsIBiomeID = config.get(CATEGORY_GENERAL, "Biome ID for Depths Infestation Biome", biomeID++).getInt();
			depthsSCBiomeID = config.get(CATEGORY_GENERAL, "Biome ID for Depths Scalded Biome", biomeID++).getInt();
			depthsSCHBiomeID = config.get(CATEGORY_GENERAL, "Biome ID for Depths Scorched Biome", biomeID++).getInt();
			depthsSWBiomeID = config.get(CATEGORY_GENERAL, "Biome ID for Depths Swamp Biome", biomeID++).getInt();
			depthsTFBiomeID = config.get(CATEGORY_GENERAL, "Biome ID for Depths Tall Forest Biome", biomeID++).getInt();
			depthsJBiomeID = config.get(CATEGORY_GENERAL, "Biome ID for Depths Jungle Biome", biomeID++).getInt();

			oredictMetals = config.getStringList(CATEGORY_GENERAL, "List of oredictionary names of ingots/plates that the grindstone will be able to convert to dust", new String[] { "Platinum", "Nickel", "Lead", "Silver", "" }, "");
		} catch (Exception e) {
			LoggerSteamcraft.error("Failed to load configuration file:" + e);
		} finally {
			if (config.hasChanged())
				config.save();
		}
	}

}
