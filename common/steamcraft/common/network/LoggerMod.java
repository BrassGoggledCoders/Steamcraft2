/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 15, 2014, 1:57:22 PM]
 */
package common.steamcraft.common.network;

import cpw.mods.fml.common.FMLLog;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A basic logger class to handle SC2 console messages.
 * 
 * @author MrArcane111
 *
 */
public class LoggerMod {
	private static Logger logger = Logger.getLogger("SC2");

	public static void init() {
		logger.setParent(FMLLog.getLogger());
	}

	public static void log(Level level, String message) {
		logger.log(level, message);
	}
}