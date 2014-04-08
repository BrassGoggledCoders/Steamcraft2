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
 * File created @ [Mar 13, 2014, 5:34:26 PM]
 */
package steamcraft.api;

import java.util.Set;

import steamcraft.api.coord.CoordInteger;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public interface IBlockType
{
	/** Allows subtypes ( by metadata or renderers entity) to have different type settings. */
	public abstract Set<String> getBlockFlags(World world, CoordInteger coord);
	
	/** Retrieves block item flags. */
	public abstract Set<String> getItemFlags(ItemStack is);
}
