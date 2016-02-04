
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
