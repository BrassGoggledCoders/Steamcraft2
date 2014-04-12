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
 * File created @ [Mar 12, 2014, 6:57:40 PM]
 */
package steamcraft.api;

import steamcraft.api.coord.CoordInteger;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public interface ICropBlock
{
	/** Is the crop harvestable? */
	public abstract boolean isMature(World world, CoordInteger coord);
	
	/** Handles the harvesting of a crop by a machine (remember to make it replant the crops). */
	public ItemStack[] machineHarvest(World world, CoordInteger coord);
}	
