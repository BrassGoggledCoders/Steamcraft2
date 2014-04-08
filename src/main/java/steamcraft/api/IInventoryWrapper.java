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
 * File created @ [Mar 12, 2014, 6:42:04 PM]
 */
package steamcraft.api;

import net.minecraft.inventory.IInventory;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public interface IInventoryWrapper
{
	/** Gets an available inventory (has to implement IInvetory). */
	public abstract IInventory getInventory();
}
