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
 * File created @ [Mar 12, 2014, 8:36:24 PM]
 */
package steamcraft.api.coord;

import steamcraft.api.INBT;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class CoordDouble implements ICoord, INBT 
{
	@Override
	public String toString() 
	{
		return "(" + dx + "," + dy + "," + dz + ")";
	}

	public double dx, dy, dz;

	public CoordDouble() 
	{
		dx = 0;
		dy = 0;
		dz = 0;
	}

	public CoordDouble(double x, double y, double z) 
	{
		dx = x;
		dy = y;
		dz = z;
	}

	public CoordDouble(float fx, float fy, float fz) 
	{
		dx = fx;
		dy = fy;
		dz = fz;
	}

	public CoordDouble(int ix, int iy, int iz) 
	{
		dx = ix;
		dy = iy;
		dz = iz;
	}

	public CoordDouble(long lx, long ly, long lz) 
	{
		dx = lx;
		dy = ly;
		dz = lz;
	}

	public CoordDouble(CoordDouble coord)
	{
		dx = coord.dx;
		dy = coord.dy;
		dz = coord.dz;
	}

	public CoordDouble(CoordFloat coord) 
	{
		dx = coord.fx;
		dy = coord.fy;
		dz = coord.fz;
	}

	public CoordDouble(CoordInteger coord) 
	{
		dx = coord.ix;
		dy = coord.iy;
		dz = coord.iz;
	}

	public CoordDouble copy() 
	{
		return new CoordDouble(dx, dy, dz);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) 
			return false;
		if (!this.getClass().equals(obj.getClass()))
			return false;

		return ((CoordDouble) obj).dx == dx && ((CoordDouble) obj).dy == dy && ((CoordDouble) obj).dz == dz;
	}

	@Override
	public int hashCode() 
	{
		return new Double(dx + 17).hashCode() ^ new Double((dy - 156)).hashCode() ^ new Double(dz).hashCode();
	}

	public CoordDouble setTo(double x, double y, double z)
	{
		this.dx = x;
		this.dy = y;
		this.dz = z;
		return this;
	}

	public CoordDouble setTo(CoordDouble coord) 
	{
		this.dx = coord.dx;
		this.dy = coord.dy;
		this.dz = coord.dz;
		return this;
	}

	public CoordDouble setTo(float fx, float fy, float fz) 
	{
		this.dx = fx;
		this.dy = fy;
		this.dz = fz;
		return this;
	}

	public CoordDouble setTo(CoordFloat coord) 
	{
		this.dx = coord.fx;
		this.dy = coord.fy;
		this.dz = coord.fz;
		return this;
	}

	public CoordDouble setTo(int ix, int iy, int iz)
	{
		this.dx = ix;
		this.dy = iy;
		this.dz = iz;
		return this;
	}

	public CoordDouble setTo(CoordInteger coord) 
	{
		this.dx = coord.ix;
		this.dy = coord.iy;
		this.dz = coord.iz;
		return this;
	}

	public CoordDouble setTo(long lx, long ly, long lz) 
	{
		this.dx = lx;
		this.dy = ly;
		this.dz = lz;
		return this;
	}

	@Override
	public CoordDouble getInverted() 
	{
		return new CoordDouble(-dx, -dy, -dz);
	}

	public boolean equalsXZ(CoordDouble coord)
	{
		return dx == coord.dx && dz == coord.dz;
	}

	public CoordDouble add(CoordDouble coord) 
	{
		dx += coord.dx;
		dy += coord.dy;
		dz += coord.dz;
		return this;
	}

	public CoordDouble add(int ix, int iy, int iz) 
	{
		this.dx += ix;
		this.dy += iy;
		this.dz += iz;
		return this;
	}

	public CoordDouble add(int ix, int iy) 
	{
		this.dx += ix;
		this.dy += iy;
		return this;
	}

	public CoordDouble add(double dx, double dy, double dz) 
	{
		this.dx += dx;
		this.dy += dy;
		this.dz += dz;
		return this;
	}

	public CoordDouble add(double dx, double dy) 
	{
		this.dx += dx;
		this.dy += dy;
		return this;
	}

	public CoordDouble add(float fx, float fy, float fz) 
	{
		this.dx += fx;
		this.dy += fy;
		this.dz += fz;
		return this;
	}

	public CoordDouble add(float fx, float fy) 
	{
		this.dx += fx;
		this.dy += fy;
		return this;
	}

	public CoordDouble add(long lx, long ly, long lz) 
	{
		this.dx += lx;
		this.dy += ly;
		this.dz += lz;
		return this;
	}

	public CoordDouble add(long lx, long ly) 
	{
		this.dx += lx;
		this.dy += ly;
		return this;
	}

	@Override
	public CoordDouble offset(CoordInteger coord) 
	{
		return new CoordDouble(dx + coord.ix, dy + coord.iy, dz + coord.iz);
	}

	@Override
	public CoordDouble offset(CoordFloat coord) 
	{
		return new CoordDouble(dx + coord.fx, dy + coord.fy, dz + coord.fz);
	}

	@Override
	public CoordDouble offset(CoordDouble coord) 
	{
		return new CoordDouble(dx + coord.dx, dy + coord.dy, dz + coord.dz);
	}

	@Override
	public CoordDouble offset(int ixm, int iym, int izm) 
	{
		return new CoordDouble(dx + ixm, dy + iym, dz + izm);
	}

	@Override
	public CoordDouble offset(float fxm, float fym, float fzm) 
	{
		return new CoordDouble(dx + fxm, dy + fym, dz + fzm);
	}

	@Override
	public CoordDouble offset(double dxm, double dym, double dzm) 
	{
		return new CoordDouble(dx + dxm, dy + dym, dz + dzm);
	}

	@Override
	public CoordDouble offset(long lxm, long lym, long lzm) 
	{
		return new CoordDouble(dx + lxm, dy + lym, dz + lzm);
	}

	public CoordDouble setX(double newX) 
	{
		return new CoordDouble(newX, dy, dz);
	}

	public CoordDouble setY(double newY) 
	{
		return new CoordDouble(dx, newY, dz);
	}

	public CoordDouble setZ(double newZ) 
	{
		return new CoordDouble(dx, dy, newZ);
	}

	/** Remember the distance formula from Algebra 2? */
	public double distanceTo(CoordDouble coord) 
	{
		return Math.sqrt((dx - coord.dx) * (dx - coord.dx) + (dy - coord.dy) * (dy - coord.dy) + (dz - coord.dz) * (dz - coord.dz));
	}

	public CoordDouble getVectorTo(CoordDouble coord)
	{
		return new CoordDouble(coord.dx - dx, coord.dy - dy, coord.dz - dz);
	}

	public static CoordDouble getVector(CoordDouble coord1, CoordDouble coord2) 
	{
		return new CoordDouble(coord2.dx - coord1.dx, coord2.dy - coord1.dy, coord2.dz - coord1.dz);
	}

	@Override
	public CoordInteger round() 
	{
		return new CoordInteger(Math.round(dx), Math.round(dy), Math.round(dz));
	}

	@Override
	public CoordInteger floor()
	{
		return new CoordInteger(Math.round(Math.floor(dx)), Math.round(Math.floor(dy)), Math.round(Math.floor(dz)));
	}

	@Override
	public CoordInteger ceil() 
	{
		return new CoordInteger(Math.round(Math.ceil(dx)), Math.round(Math.ceil(dy)), Math.round(Math.ceil(dz)));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) 
	{
		tagCompound.setDouble("dx", dx);
		tagCompound.setDouble("dy", dy);
		tagCompound.setDouble("dz", dz);
		return tagCompound;
	}

	@Override
	public INBT readFromNBT(NBTTagCompound tagCompound) 
	{
		dx = tagCompound.getDouble("dx");
		dy = tagCompound.getDouble("dy");
		dz = tagCompound.getDouble("dz");
		return this;
	}
}
