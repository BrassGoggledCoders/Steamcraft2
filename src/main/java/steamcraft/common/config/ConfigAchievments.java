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
public class ConfigAchievments {

	/** The shrinkray achieve. */
	public static Achievement raygunAchieve, shrinkrayAchieve;
	
	/** The sc2 achieve page. */
	public static AchievementPage sc2AchievePage;

	/**
	 * Inits the.
	 */
	public static void init() {
		raygunAchieve = new Achievement("achievement.raygun", "raygunachieve",
				0, 0, ConfigItems.itemRayGun, null).registerStat()
				.initIndependentStat();
		shrinkrayAchieve = new Achievement("achievement.shrinkray",
				"shrinkrayachieve", 0, 2, ConfigItems.itemShrinkray, null)
				.registerStat().initIndependentStat();

		sc2AchievePage = new AchievementPage("Steamcraft 2", raygunAchieve,
				shrinkrayAchieve);
		AchievementPage.registerAchievementPage(sc2AchievePage);
	}

}
