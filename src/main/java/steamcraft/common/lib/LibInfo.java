package steamcraft.common.lib;

import boilerplate.common.Boilerplate;
import boilerplate.common.utils.StringUtils;

public class LibInfo
{
	public static String[] metals = { "Aluminum", "Copper", "Tin", "Zinc", "Brass", "Bronze", "Steel", "CastIron" };
	public static String[] metalsV = { "Iron", "Gold" };
	private static String[] brassBlocksMembers = { "Longeye", "DocArcane", "CaptianNivea", "DrVesuvius", "PeaGreen", "elShoggotho", "Ainuryn", "Piyok0",
			"Ryan_T15", "AdmiralSocrates" };
	private static String[] devs = { "warlordjones", "decebaldecebal", "Snurly" };
	private static String[] contributors = { "domi1819", "frothiny", "MrIbby", "HCSarise" };
	private static String[] otherCoolPeople = { "ClockwerkKaiser" };
	public static String[] names = StringUtils.merge(brassBlocksMembers, devs, contributors, Boilerplate.donors, otherCoolPeople);
	public static float pixel = 1F / 16F / 2F;
	public static float tPixel = 1F / 32F;
}
