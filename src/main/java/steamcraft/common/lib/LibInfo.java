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
	public static final String VERSION = "Beta R2";

	public static final List<String> AUTHORS = Arrays.asList("warlordjones", "decebaldecebal", "Falkok15", "wierdude1999", "seanvanpelt2 & others");

	public static final String CLIENT_PROXY = "steamcraft.client.ClientProxy";
	public static final String COMMON_PROXY = "steamcraft.common.CommonProxy";

	public static final String PREFIX = "steamcraft:";

	public static final String VERSION_URL = "https://gist.githubusercontent.com/warlordjones/42644dbc6db776152367/raw/99d70c9382a61b88cb61a605a8f0dfd9bbb49079/gistfile1.txt";

	/** Capes!. */
	// public static final String DEV_LIST =
	// "https://www.dropbox.com/s/m7tn0tx7y7w630s/devs.txt";

	// public static final String DONATOR_LIST =
	// "https://www.dropbox.com/s/pihl7ja8m9z2ybs/donators.txt";

	// public static final String DEV_CAPE_IMG_URL =
	// "https://dl.dropboxusercontent.com/u/34970176/SteamCraft%202/steamcraft_cape_MID_1.png";

	// public static final String DONATOR_CAPE_IMG_URL =
	// "http://imgur.com/7O5dSL2";
	// 0 1 2 3 4 5 6 7
	public static String[] metals = { "Aluminum", "Copper", "Tin", "Zinc", "Brass", "Bronze", "Steel", "CastIron" };
}
