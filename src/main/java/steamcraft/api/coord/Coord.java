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
 * File created @ [Mar 12, 2014, 5:36:45 PM]
 */
package steamcraft.api.coord;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class Coord
{
	public int x, y, z;

	public Coord(int i, int j, int k) 
	{
		x = i;
		y = j;
		z = k;
	}

	public Coord(Coord coord) 
	{
		x = coord.x;
		y = coord.y;
		z = coord.z;
	}

	public Coord copy() 
	{
		return new Coord(x, y, z);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) 
			return false;
		if (!this.getClass().equals(obj.getClass())) 
			return false;

		return ((Coord) obj).x == x && ((Coord) obj).y == y && ((Coord) obj).z == z;
	}
}
