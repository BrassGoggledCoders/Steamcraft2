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

public class ConfigBalance
{
	public static Configuration config;

	public static final String CATEGORY_RECIPE_OPTIONS = "recipe balance options";

	public static boolean cheaperCoreRecipe;
	public static int numberOfDustsFromOreHammering;

	public static void initialize(File configFile)
	{
		config = new Configuration(configFile);
		try
		{
			config.load();
			cheaperCoreRecipe = config.get(CATEGORY_RECIPE_OPTIONS, "Use diamond instead of nether star in Overcharged Core Recipe", false)
					.getBoolean(false);
			numberOfDustsFromOreHammering = config.get(CATEGORY_RECIPE_OPTIONS, "Number of dusts produced from hammering an ore block", 2).getInt();
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
