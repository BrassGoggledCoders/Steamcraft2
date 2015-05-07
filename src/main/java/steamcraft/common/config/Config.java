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

/**
 * @author Surseance
 *
 */
public class Config
{
	public static File configGeneral;
	public static File configGen;
	public static File configBalance;

	public static void initialise(File configFolder)
	{
		configGeneral = new File(configFolder, "general.cfg");
		configGen = new File(configFolder, "generation.cfg");
		configBalance = new File(configFolder, "balance.cfg");

		ConfigGeneral.initialize(configGeneral);
		ConfigWorldGen.initialize(configGen);
		ConfigBalance.initialize(configBalance);
	}
}
