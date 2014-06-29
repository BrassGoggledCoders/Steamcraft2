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
	public static Achievement raygunAchieve, shrinkrayAchieve, boilerAchieve, oreAchieve, ingotAchieve, sheetAchieve, intakeAchieve;

	/** The sc2 achieve page. */
	public static AchievementPage sc2AchievePage;

	/**
	 * Inits the.
	 */
	public static void init()
	{
		ingotAchieve = new Achievement("achievement.ingot", "ingotachieve", 0, 0, ConfigItems.itemIngot, null).registerStat().initIndependentStat();
		sheetAchieve = new Achievement("achievement.sheet", "sheetachieve", 2, 0, ConfigItems.itemSheet, ingotAchieve).registerStat();
		boilerAchieve = new Achievement("achievement.boiler", "boilerachieve", 4, 0, ConfigBlocks.blockSteamBoiler, sheetAchieve).registerStat()/*.setSpecial()*/;
		intakeAchieve = new Achievement("achievement.intake", "intakeachieve", 4, 0, ConfigBlocks.blockIntake, boilerAchieve).registerStat();

		raygunAchieve = new Achievement("achievement.raygun", "raygunachieve", 0, 2, ConfigItems.itemRayGun, ingotAchieve).registerStat();
		shrinkrayAchieve = new Achievement("achievement.shrinkray", "shrinkrayachieve", 0, -2, ConfigItems.itemShrinkray, ingotAchieve).registerStat();
		// oreAchieve = new Achievement("achievement.ore", "oreachieve", 0, 6,
		// ConfigBlocks.blockCustomOre,
		// null).registerStat().initIndependentStat();

		sc2AchievePage = new AchievementPage("Steamcraft 2", raygunAchieve, shrinkrayAchieve, boilerAchieve, ingotAchieve, sheetAchieve);
		AchievementPage.registerAchievementPage(sc2AchievePage);
	}

}
