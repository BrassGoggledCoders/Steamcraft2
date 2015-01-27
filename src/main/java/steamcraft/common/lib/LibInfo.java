package steamcraft.common.lib;

import boilerplate.common.Boilerplate;
import boilerplate.common.utils.StringUtils;

public class LibInfo
{
	public static String[] metals = { "Aluminum", "Copper", "Tin", "Zinc", "Brass", "Bronze", "Steel", "CastIron" };
	public static String[] metalsV = { "Iron", "Gold" };
	public static String[] brassBlocksMembers = { "Longeye", "DocArcane", "CaptianNivea", "DrVesuvius", "PeaGreen", "elShoggotho", "Ainuryn", "Piyok0",
			"Ryan_T15", "AdmiralSocrates" };
	public static String[] devs = { "warlordjones", "decebaldecebal", "Snurly" };
	public static String[] contributors = { "domi1819", "frothiny", "MrIbby", "HCSarise" };
	public static String[] otherCoolPeople = { "ClockwerkKaiser" };
	public static String[] names = StringUtils.merge(brassBlocksMembers, devs, contributors, Boilerplate.donors, otherCoolPeople);
}
