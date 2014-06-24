/*
 *
 */
package steamcraft.common.config;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

// TODO: Auto-generated Javadoc
/**
 * The Class ConfigAchievments.
 */
public class ConfigAchievements
{

	/** The shrinkray achieve. */
	public static Achievement raygunAchieve, shrinkrayAchieve, boilerAchieve, oreAchieve, ingotAchieve, sheetAchieve;

	/** The sc2 achieve page. */
	public static AchievementPage sc2AchievePage;

	/**
	 * Inits the.
	 */
	public static void init()
	{
		raygunAchieve = new Achievement("achievement.raygun", "raygunachieve", 0, 0, ConfigItems.itemRayGun, null).registerStat().initIndependentStat();
		shrinkrayAchieve = new Achievement("achievement.shrinkray", "shrinkrayachieve", 0, 2, ConfigItems.itemShrinkray, null).registerStat().initIndependentStat();
		boilerAchieve = new Achievement("achievement.boiler", "boilerachieve", 0, 4, ConfigBlocks.blockSteamBoiler, null).registerStat().initIndependentStat();
		//oreAchieve = new Achievement("achievement.ore", "oreachieve", 0, 6, ConfigBlocks.blockCustomOre, null).registerStat().initIndependentStat();
		ingotAchieve = new Achievement("achievement.ingot", "ingotachieve", 0, 8, ConfigItems.itemIngot, null).registerStat().initIndependentStat();
		sheetAchieve = new Achievement("achievement.sheet", "sheetachieve", 0, 10, ConfigItems.itemSheet, null).registerStat().initIndependentStat();

		sc2AchievePage = new AchievementPage("Steamcraft 2", raygunAchieve, shrinkrayAchieve, boilerAchieve, /*oreAchieve,*/ ingotAchieve, sheetAchieve);
		AchievementPage.registerAchievementPage(sc2AchievePage);
	}

}
