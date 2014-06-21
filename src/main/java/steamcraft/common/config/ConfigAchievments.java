package steamcraft.common.config;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class ConfigAchievments {

	public static Achievement raygunAchieve, shrinkrayAchieve;
	public static AchievementPage sc2AchievePage;

	public static void init()
	{
		raygunAchieve = new Achievement("achievement.raygun", "raygunachieve", 0, 0, ConfigItems.itemRayGun, null).registerStat().initIndependentStat();
		shrinkrayAchieve = new Achievement("achievement.shrinkray", "shrinkrayachieve", 0, 0, ConfigItems.itemShrinkray, null).registerStat().initIndependentStat();


		sc2AchievePage = new AchievementPage("Steamcraft 2", raygunAchieve, shrinkrayAchieve);
		AchievementPage.registerAchievementPage(sc2AchievePage);
	}

}
