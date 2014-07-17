package steamcraft.common.config;

import java.io.File;
import java.util.logging.Level;

import steamcraft.common.lib.LoggerSteamcraft;
import net.minecraftforge.common.config.Configuration;

public class ConfigGeneral
{
	public static Configuration config;

	public static final String CATEGORY_GENERAL = "general";

	public static boolean partyPooper;

	public static void initialize(File configFile)
	{
		config = new Configuration(configFile);
		try
		{
			config.load();

			partyPooper = config.get(CATEGORY_GENERAL, "Remove all little fun things from mod :(", false).getBoolean(false);
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
