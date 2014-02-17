/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 * 
 * File created @ [Jan 30, 2014, 5:53:46 PM]
 */
package common.steamcraft.common.core.helper;

import net.minecraft.entity.player.EntityPlayer;

/**
 * @author Mikemoo from OpenBlocksLib
 * (https://github.com/OpenMods/OpenModsLib)
 *
 */
public class ExperienceHelper {
	public static int getPlayerXP(EntityPlayer player) {
		return (int)(getExperienceForLevel(player.experienceLevel) + player.experience * player.xpBarCap());
	}

	public static void drainPlayerXP(EntityPlayer player, int amount) {
		addPlayerXP(player, -amount);
	}

	public static void addPlayerXP(EntityPlayer player, int amount) {
		int experience = getPlayerXP(player) + amount;
		player.experienceTotal = experience;
		player.experienceLevel = getLevelForExperience(experience);
		int expForLevel = getExperienceForLevel(player.experienceLevel);
		player.experience = (float)(experience - expForLevel) / (float)player.xpBarCap();
	}

	public static int getExperienceForLevel(int level) {
		if (level == 0) 
			return 0;
		if (level > 0 && level < 16)
			return level * 17;
		else if (level > 15 && level < 31)
			return (int)(1.5 * Math.pow(level, 2) - 29.5 * level + 360);
		else
			return (int)(3.5 * Math.pow(level, 2) - 151.5 * level + 2220);
	}

	public static int getLevelForExperience(int experience) {
		int i = 0;
	
		while (getExperienceForLevel(i) <= experience)
			i++;
		
		return i - 1;
	}
}
