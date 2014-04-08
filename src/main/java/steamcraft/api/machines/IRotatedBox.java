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
 * File created @ [Mar 12, 2014, 5:42:21 PM]
 */
package steamcraft.api.machines;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public interface IRotatedBox
{
	/** Gets the rotation from metadata */
	public int getRotation(int metadata); // 0, 1, 2, 3...
	
	/** Returns false if only the top face is rotated and all the others remain the same. */
	public boolean isItem3D();
}
