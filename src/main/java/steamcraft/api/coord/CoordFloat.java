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
 * File created @ [Mar 12, 2014, 8:34:09 PM]
 */
package steamcraft.api.coord;

import steamcraft.api.INBT;
import net.minecraft.nbt.NBTTagCompound;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class CoordFloat implements ICoord, INBT 
{
	@Override
	public String toString() 
	{
		return "(" + fx + "," + fy + "," + fz + ")";
	}

	public float fx, fy, fz;

	public CoordFloat()
	{
		fx = 0;
		fy = 0;
		fz = 0;
	}

	public CoordFloat(float x, float y, float z) 
	{
		fx = x;
		fy = y;
		fz = z;
	}

	public CoordFloat(int ix, int iy, int iz) 
	{
		fx = ix;
		fy = iy;
		fz = iz;
	}

	public CoordFloat(long lx, long ly, long lz) 
	{
		fx = lx;
		fy = ly;
		fz = lz;
	}

	public CoordFloat(double dx, double dy, double dz) 
	{
		fx = (float)dx;
		fy = (float)dy;
		fz = (float)dz;
	}

	public CoordFloat(CoordFloat coord) 
	{
		fx = coord.fx;
		fy = coord.fy;
		fz = coord.fz;
	}

	public CoordFloat(CoordInteger coord) 
	{
		fx = coord.ix;
		fy = coord.iy;
		fz = coord.iz;
	}

	public CoordFloat(CoordDouble coord) 
	{
		fx = (float)coord.dx;
		fy = (float)coord.dy;
		fz = (float)coord.dz;
	}

	public CoordFloat copy()
	{
		return new CoordFloat(fx, fy, fz);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) 
			return false;
		if (!this.getClass().equals(obj.getClass())) 
			return false;

		return ((CoordFloat) obj).fx == fx && ((CoordFloat) obj).fy == fy && ((CoordFloat) obj).fz == fz;
	}


	@Override
	public int hashCode() 
	{
		return new Float(fx + 17).hashCode() ^ new Float((fy - 156)).hashCode() ^ new Float(fz).hashCode();
	}

	public CoordFloat setTo(double dx, double dy, double dz) 
	{
		this.fx = (float)dx;
		this.fy = (float)dy;
		this.fz = (float)dz;
		return this;
	}

	public CoordFloat setTo(CoordDouble coord) 
	{
		this.fx = (float)coord.dx;
		this.fy = (float)coord.dy;
		this.fz = (float)coord.dz;
		return this;
	}

	public CoordFloat setTo(float x, float y, float z)
	{
		this.fx = x;
		this.fy = y;
		this.fz = z;
		return this;
	}

	public CoordFloat setTo(CoordFloat coord)
	{
		this.fx = coord.fx;
		this.fy = coord.fy;
		this.fz = coord.fz;
		return this;
	}

	public CoordFloat setTo(int ix, int iy, int iz) 
	{
		this.fx = ix;
		this.fy = iy;
		this.fz = iz;
		return this;
	}
	
	public CoordFloat setTo(CoordInteger coord)
	{
		this.fx = coord.ix;
		this.fy = coord.iy;
		this.fz = coord.iz;
		return this;
	}

	public CoordFloat setTo(long lx, long ly, long lz) 
	{
		this.fx = lx;
		this.fy = ly;
		this.fz = lz;
		return this;
	}

	@Override
	public CoordFloat getInverted() 
	{
		return new CoordFloat(-fx, -fy, -fz);
	}

	public boolean equalsXZ(CoordFloat coord)
	{
		return fx == coord.fx && fz == coord.fz;
	}

	public CoordFloat add(CoordFloat coord) 
	{
		fx += coord.fx;
		fy += coord.fy;
		fz += coord.fz;
		return this;
	}

	public CoordFloat add(int ix, int iy, int iz) 
	{
		this.fx += ix;
		this.fy += iy;
		this.fz += iz;
		return this;
	}

	public CoordFloat add(int ix, int iz)
	{
		this.fx += ix;
		this.fy += iz;
		return this;
	}

	public CoordFloat add(float x, float y, float z) 
	{
		this.fx += x;
		this.fy += y;
		this.fz += z;
		return this;
	}

	public CoordFloat add(float x, float y)
	{
		this.fx += x;
		this.fy += y;
		return this;
	}

	public CoordFloat add(double dx, double dy, double dz) 
	{
		this.fx += dx;
		this.fy += dy;
		this.fz += dz;
		return this;
	}

	public CoordFloat add(double dx, double dy) 
	{
		this.fx += dx;
		this.fy += dy;
		return this;
	}

	public CoordFloat add(long lx, long ly, long lz) 
	{
		this.fx += lx;
		this.fy += ly;
		this.fz += lz;
		return this;
	}

	public CoordFloat add(long lx, long ly) 
	{
		this.fx += lx;
		this.fy += ly;
		return this;
	}

	@Override
	public CoordFloat offset(CoordInteger coord) 
	{
		return new CoordFloat(fx + coord.ix, fy + coord.iy, fz + coord.iz);
	}

	@Override
	public CoordFloat offset(CoordFloat coord) 
	{
		return new CoordFloat(fx + coord.fx, fy + coord.fy, fz + coord.fz);
	}

	@Override
	public CoordFloat offset(CoordDouble coord)
	{
		return new CoordFloat(fx + coord.dx, fy + coord.dy, fz + coord.dz);
	}

	@Override
	public CoordFloat offset(int ixm, int iym, int izm) 
	{
		return new CoordFloat(fx + ixm, fy + iym, fz + izm);
	}

	@Override
	public CoordFloat offset(float fxm, float fym, float fzm)
	{
		return new CoordFloat(fx + fxm, fy + fym, fz + fzm);
	}

	@Override
	public CoordFloat offset(double dxm, double dym, double dzm) 
	{
		return new CoordFloat(fx + dxm, fy + dym, fz + dzm);
	}

	@Override
	public CoordFloat offset(long lxm, long lym, long lzm) 
	{
		return new CoordFloat(fx + lxm, fy + lym, fz + lzm);
	}

	public CoordFloat setX(float newX) 
	{
		return new CoordFloat(newX, fy, fz);
	}

	public CoordFloat setY(float newY) 
	{
		return new CoordFloat(fx, newY, fz);
	}

	public CoordFloat setZ(float newZ) 
	{
		return new CoordFloat(fx, fy, newZ);
	}

	public double distanceTo(CoordFloat coord)
	{
		return Math.sqrt((fx - coord.fx) * (fx - coord.fx) + (fy - coord.fy) * (fy - coord.fy) + (fz - coord.fz) * (fz - coord.fz));
	}
	
	public CoordDouble getVectorTo(CoordFloat coord) 
	{
		return new CoordDouble(coord.fx - fx, coord.fy - fy, coord.fz - fz);
	}

	public static CoordDouble getVector(CoordFloat coord1, CoordFloat coord2) 
	{
		return new CoordDouble(coord2.fx - coord1.fx, coord2.fy - coord1.fy, coord2.fz - coord1.fz);
	}

	@Override
	public CoordInteger round() 
	{
		return new CoordInteger(Math.round(fx), Math.round(fy), Math.round(fz));
	}

	@Override
	public CoordInteger floor() 
	{
		return new CoordInteger(Math.round(Math.floor(fx)), Math.round(Math.floor(fy)), Math.round(Math.floor(fz)));
	}

	@Override
	public CoordInteger ceil() 
	{
		return new CoordInteger(Math.round(Math.ceil(fx)), Math.round(Math.ceil(fy)), Math.round(Math.ceil(fz)));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) 
	{
		tagCompound.setFloat("fx", fx);
		tagCompound.setFloat("fy", fy);
		tagCompound.setFloat("fz", fz);
		return tagCompound;
	}

	@Override
	public INBT readFromNBT(NBTTagCompound tagCompound) 
	{
		fx = tagCompound.getFloat("fx");
		fy = tagCompound.getFloat("fy");
		fz = tagCompound.getFloat("fz");
		return this;
	}
}
