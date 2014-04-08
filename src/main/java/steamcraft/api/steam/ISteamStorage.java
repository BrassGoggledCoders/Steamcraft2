/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [Apr 8, 2014, 1:28:41 PM]
 */
package steamcraft.api.steam;

import java.util.List;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public abstract interface ISteamStorage
{
	/** */
	public abstract float supplySteam(float steam, int amount, List list);

	/** */
	public abstract float requestSteam(float steam, int amount, List list);
}
