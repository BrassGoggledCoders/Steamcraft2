package steamcraft.common.compat;

import java.util.ArrayList;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

import steamcraft.common.compat.chisel.ChiselCompat;
import steamcraft.common.lib.LoggerSteamcraft;

/**
 * Created by Skylar on 9/2/2015.
 */
public class CompatibilityHandler
{
	private static ArrayList<ModCompat> MODCOMPAT_ENABLED = new ArrayList<ModCompat>();

	public static ArrayList<ModCompat> getModCompat()
	{
		if (MODCOMPAT_ENABLED.isEmpty())
		{
			MODCOMPAT_ENABLED.add(new ChiselCompat());
		}
		return MODCOMPAT_ENABLED;
	}

	public static void preInit(FMLPreInitializationEvent event)
	{
		for (ModCompat modCompat : getModCompat())
		{
			if (!modCompat.areRequirementsMet() && modCompat.getIsActive())
			{
				modCompat.setIsActive(false);
				LoggerSteamcraft.error("Requirements are not met for " + modCompat.getName() + ". Deactivating");
			}
			if (modCompat.getIsActive())
			{
				LoggerSteamcraft.info("Loading " + modCompat.getName() + " module");
			}
		}

		for (ModCompat modCompat : getModCompat())
		{
			if (modCompat.getIsActive())
				modCompat.preInit(event);
		}
	}

	public static void init(FMLInitializationEvent event)
	{
		for (ModCompat modCompat : getModCompat())
		{
			if (modCompat.getIsActive())
				modCompat.init(event);
		}
	}

	public static void postInit(FMLPostInitializationEvent event)
	{
		for (ModCompat modCompat : getModCompat())
		{
			if (modCompat.getIsActive())
				modCompat.postInit(event);
		}
	}
}
