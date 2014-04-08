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
 * File created @ [Mar 12, 2014, 6:59:56 PM]
 */
package steamcraft.api.coord;

import steamcraft.api.INBT;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public interface ICoord extends INBT
{
	public abstract ICoord getInverted();

	public abstract ICoord offset(CoordInteger added);

	public abstract ICoord offset(CoordDouble added);

	public abstract ICoord offset(CoordFloat added);

	public abstract ICoord offset(int ix, int iy, int iz);
	
	public abstract ICoord offset(long lx, long ly, long lz);

	public abstract ICoord offset(double dx, double dy, double dz);

	public abstract ICoord offset(float fx, float fy, float fz);

	public abstract ICoord round();

	public abstract ICoord floor();

	public abstract ICoord ceil();
}
