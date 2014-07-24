package steamcraft.common.config;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.config.Configuration;
import steamcraft.client.renderers.tile.TileCopperPipeRenderer;
import steamcraft.common.lib.LoggerSteamcraft;

public class ConfigGeneral
{
	public static Configuration config;

	public static final String CATEGORY_GENERAL = "general";
	public static final String CATEGORY_CLIENT = "client";

	public static boolean partyPooper;

	public static void initialize(File configFile)
	{
		config = new Configuration(configFile);
		try
		{
			config.load();

			partyPooper = config.get(CATEGORY_GENERAL, "Remove all little fun things from mod :(", false).getBoolean(false);
			
			TileCopperPipeRenderer.drawFluid = config.get(CATEGORY_CLIENT, "Render fluid inside copper pipes", true).getBoolean(true);
			TileCopperPipeRenderer.drawInside = config.get(CATEGORY_CLIENT, "Render the insides of copper pipes", true).getBoolean(true);
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
