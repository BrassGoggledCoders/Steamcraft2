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

	public static void initialize(File configFile)
	{
		config = new Configuration(configFile);
		try
		{
			config.load();

			partyPooper = config.get(CATEGORY_GENERAL, "Remove all little fun things from mod :(", false).getBoolean(false);
			
			drawFluid = config.get(CATEGORY_CLIENT, "Render fluid inside blocks", true).getBoolean(true);
			drawInside = config.get(CATEGORY_CLIENT, "Render the insides of blocks", true).getBoolean(true);
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
