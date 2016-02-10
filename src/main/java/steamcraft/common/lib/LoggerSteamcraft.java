
package steamcraft.common.lib;

import net.minecraftforge.classloading.FMLForgePlugin;

import boilerplate.common.utils.ILogger;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Surseance
 *
 */
// TODO: genericise
public class LoggerSteamcraft implements ILogger
{
	private static final Logger logger = LogManager.getLogger("Steamcraft");

	public static Logger getLogger()
	{
		return logger;
	}

	@Override
	public void warning(String message)
	{
		logger.log(Level.WARN, message);
	}

	@Override
	public void info(String message)
	{
		logger.log(Level.INFO, message);
	}

	@Override
	public void fatal(String message)
	{
		logger.log(Level.FATAL, message);
	}

	@Override
	public void error(String message)
	{
		logger.log(Level.ERROR, message);
	}

	@Override
	public void devWarning(String message)
	{
		if (FMLForgePlugin.RUNTIME_DEOBF)
			logger.log(Level.WARN, message);
	}

	@Override
	public void devInfo(String message)
	{
		if (FMLForgePlugin.RUNTIME_DEOBF)
			logger.log(Level.INFO, message);
	}

	@Override
	public void devFatal(String message)
	{
		if (FMLForgePlugin.RUNTIME_DEOBF)
			logger.log(Level.FATAL, message);
	}

	@Override
	public void devError(String message)
	{
		if (FMLForgePlugin.RUNTIME_DEOBF)
			logger.log(Level.ERROR, message);
	}
}
