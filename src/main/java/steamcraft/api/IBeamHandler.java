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
 * File created @ [Mar 13, 2014, 5:37:07 PM]
 */
package steamcraft.api;

import steamcraft.api.coord.CoordInteger;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public interface IBeamHandler
{
	/** Used by the ray-gun to determine when a block is hit. */
	public abstract boolean onBlockHit(World world, CoordInteger coord1, CoordInteger coord2);
	
	/** Used by the ray-gun to determine when an entity is hit. */
	public abstract boolean onEntityHit(World world, Entity[] entityArray, CoordInteger coord);
}
