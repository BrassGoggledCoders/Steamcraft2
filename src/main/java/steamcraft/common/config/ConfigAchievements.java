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

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

/**
 * @author warlordjones
 *
 */
public class ConfigAchievements
{
	public static Achievement raygunAchieve, shrinkrayAchieve, boilerAchieve, oreAchieve, ingotAchieve, sheetAchieve, intakeAchieve;

	public static AchievementPage sc2AchievePage;

	public static void init()
	{
		ingotAchieve = new Achievement("achievement.ingot", "ingotachieve", 0, 0, ConfigItems.itemIngot, null).registerStat().initIndependentStat();
		sheetAchieve = new Achievement("achievement.sheet", "sheetachieve", 2, 0, ConfigItems.itemSheet, ingotAchieve).registerStat();
		boilerAchieve = new Achievement("achievement.boiler", "boilerachieve", 4, 0, ConfigBlocks.blockSteamBoiler, sheetAchieve).registerStat()/* .setSpecial() */;
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
