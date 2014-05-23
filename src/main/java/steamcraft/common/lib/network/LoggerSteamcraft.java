/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * File created @ [Mar 14, 2014, 10:56:24 AM]
 */
package steamcraft.common.lib.network;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

/**
 * @author Surseance (Johnny Eatmon)
 * 
 */
public class LoggerSteamcraft
{
	private static Logger logger = Logger.getLogger("SC2");

	public static void init()
	{
		logger.setParent((Logger) FMLLog.getLogger());
		;
	}

	public static void log(final Level level, final String message)
	{
		logger.log(level, "[Steamcraft] " + message);
	}
}
