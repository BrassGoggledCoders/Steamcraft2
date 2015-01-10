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

import java.util.Arrays;
import java.util.List;

/**
 * A neat, tidy class for holding various bits of mod information.
 * 
 * @author Surseance
 * 
 */
public class LibInfo
{
	public static final String ID = "steamcraft2";
	public static final String NAME = "Steamcraft 2";
	public static final String VERSION = "Beta R3.3";

	public static final List<String> AUTHORS = Arrays.asList("warlordjones", "decebaldecebal", "Falkok15", "wierdude1999", "domi1819", "seanvanpelt2 & others");

	public static final String CLIENT_PROXY = "steamcraft.client.ClientProxy";
	public static final String COMMON_PROXY = "steamcraft.common.CommonProxy";
	public static final String CONFIG_GUI = "steamcraft.client.gui.GuiFactorySteamcraft";

	public static final String PREFIX = "steamcraft:";

	// Good Idea, Ibby ;)
	public static final String VERSION_URL = "https://github.com/BrassGoggledCoders/SteamCraft2/raw/master/versionchecker.txt";

	public static String[] metals = { "Aluminum", "Copper", "Tin", "Zinc", "Brass", "Bronze", "Steel", "CastIron" };
	public static String[] metalsV = { "Iron", "Gold" };
}
