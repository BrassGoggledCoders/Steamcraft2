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
package steamcraft.common.lib;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Surseance
 *
 */
public class LoggerSteamcraft
{
	private static final Logger logger = LogManager.getLogger("Steamcraft");

	public static void log(org.apache.logging.log4j.Level level, String message)
	{
		logger.log(level, message);
	}

	public static void warning(String message)
	{
		logger.log(Level.WARN, message);
	}

	public static void info(String message)
	{
		logger.log(Level.INFO, message);
	}

	public static void fatal(String message)
	{
		logger.log(Level.FATAL, message);
	}

	public static void error(String message)
	{
		logger.log(Level.ERROR, message);
	}
}
