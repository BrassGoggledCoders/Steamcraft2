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
package steamcraft.common.lib;

import boilerplate.common.Boilerplate;
import boilerplate.common.utils.StringUtils;

public class LibInfo
{
	public static String[] metals = { "Aluminum", "Copper", "Tin", "Zinc", "Brass", "Bronze", "Steel", "CastIron" };
	public static String[] metalsV = { "Iron", "Gold" };
	private static String[] brassBlocksMembers = { "Longeye", "DocArcane", "CaptianNivea", "DrVesuvius", "PeaGreen", "elShoggotho", "Ainuryn", "Piyok0",
			"Ryan_T15", "AdmiralSocrates" };
	public static String[] contributors = { "domi1819", "frothiny", "MrIbby", "HCSarise" };
	public static String[] bugsquashers = { "ClockwerkKaiser" };
	public static String[] devsandreporters = StringUtils.merge(bugsquashers, Boilerplate.devs);
	public static String[] names = StringUtils.merge(brassBlocksMembers, Boilerplate.devs, contributors, Boilerplate.donors, bugsquashers);
	public static float pixel = 1F / 16F / 2F;
	public static float tPixel = 1F / 32F;
}
