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
package xyz.brassgoggledcoders.steamcraft.common.config;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class ConfigBalance
{
	public static String configId = "configBalance";

	public static Configuration config;

	public static final String CATEGORY_RECIPE_OPTIONS = "recipes";

	public static final String CATEGORY_ENERGY = "energy";

	public static boolean cheaperCoreRecipe;
	public static int numberOfDustsFromOre, numberOfDustsFromMetal;

	public static int lightningRodHitChance, lightningRodEnergyProduction;

	public static void initialize(File configFile)
	{
		if (config == null)
			config = new Configuration(configFile);

		config.load();

		loadConfiguration();
	}

	public static void loadConfiguration()
	{
		cheaperCoreRecipe = config.get(CATEGORY_RECIPE_OPTIONS, "Use diamond instead of nether star in Overcharged Core Recipe", false)
				.getBoolean(false);
		numberOfDustsFromOre = config.get(CATEGORY_RECIPE_OPTIONS, "Number of dusts produced from hammering an ore block", 2).getInt();
		numberOfDustsFromMetal = config.get(CATEGORY_RECIPE_OPTIONS, "Number of dusts produced from grinding an ingot/sheet", 1).getInt();

		lightningRodHitChance = config.get(CATEGORY_ENERGY, "Chance of Lightning hitting Lightning Rods every tick. It's 1/value here", 3000)
				.getInt();
		lightningRodEnergyProduction = config.get(CATEGORY_ENERGY, "Energy produced per lightning strike by Lightning Rod", 10000).getInt();
		// lightningRodProduceEnergyOnNaturalStrike =
		// config.get(CATEGORY_ENERGY)

		if (config.hasChanged())
			config.save();
	}
}
