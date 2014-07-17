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
			cheaperCoreRecipe = config.get(CATEGORY_RECIPE_OPTIONS, "Use diamond instead of nether star in Overcharged Core Recipe", false).getBoolean(false);
			numberOfDustsFromOreHammering = config.get(CATEGORY_RECIPE_OPTIONS, "Number of dusts produced from hammering an ore block", 2).getInt();
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
