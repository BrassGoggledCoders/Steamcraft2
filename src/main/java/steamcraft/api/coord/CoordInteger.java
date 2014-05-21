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
 * File created @ [Mar 12, 2014, 6:58:30 PM]
 */
package steamcraft.api.coord;

import steamcraft.api.INBT;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class CoordInteger implements ICoord, INBT
{
	@Override
	public String toString() 
	{
		return "(" + ix + "," + iy + "," + iz + ")";
	}

	public int ix, iy, iz;

	public CoordInteger()
	{
		ix = 0;
		iy = 0;
		iz = 0;
	}

	public CoordInteger(int x, int y, int z) 
	{
		ix = x;
		iy = y;
		iz = z;
	}

	public CoordInteger(int x, int y) 
	{
		ix = x;
		iy = y;
		iz = 0;
	}

	public CoordInteger(long lx, long ly, long lz) 
	{
		ix = (int)lx;
		iy = (int)ly;
		iz = (int)lz;
	}

	public CoordInteger(long lx, long ly) 
	{
		ix = (int)lx;
		iy = (int)ly;
		iz = 0;
	}

	public CoordInteger(CoordInteger coord) 
	{
		ix = coord.ix;
		iy = coord.iy;
		iz = coord.iz;
	}

	public CoordInteger copy() 
	{
		return new CoordInteger(ix, iy, iz);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) 
			return false;
		if (!this.getClass().equals(obj.getClass())) 
			return false;

		return ((CoordInteger) obj).ix == ix && ((CoordInteger) obj).iy == iy && ((CoordInteger) obj).iz == iz;
	}

	@Override
	public int hashCode() 
	{
		return (ix + 17) ^ (iy - 156) ^ iz;
	}

	public CoordInteger setTo(int x, int y, int z) 
	{
		this.ix = x;
		this.iy = y;
		this.iz = z;
		return this;
	}

	public CoordInteger setTo(int x, int y) 
	{
		this.ix = x;
		this.iy = y;
		return this;
	}

	public CoordInteger setTo(CoordInteger coord) 
	{
		this.ix = coord.ix;
		this.iy = coord.iy;
		this.iz = coord.iz;
		return this;
	}

	public CoordInteger setTo(long lx, long ly, long lz)
	{
		this.ix = (int)lx;
		this.iy = (int)ly;
		this.iz = (int)lz;
		return this;
	}

	public CoordInteger setTo(long lx, long ly) 
	{
		this.ix = (int)lx;
		this.iy = (int)ly;
		return this;
	}

	@Override
	public CoordInteger getInverted() 
	{
		return new CoordInteger(-ix, -iy, -iz);
	}

	public boolean equalsXZ(CoordInteger coord) 
	{
		return ix == coord.ix && iz == coord.iz;
	}

	public CoordInteger add(CoordInteger coord) 
	{
		ix += coord.ix;
		iy += coord.iy;
		iz += coord.iz;
		return this;
	}

	public CoordInteger add(int x, int y, int z) 
	{
		this.ix += x;
		this.iy += y;
		this.iz += z;
		return this;
	}

	public CoordInteger add(int x, int y) 
	{
		this.ix += x;
		this.iy += y;
		return this;
	}

	public CoordInteger add(long lx, long ly, long lz)
	{
		this.ix += lx;
		this.iy += ly;
		this.iz += lz;
		return this;
	}

	public CoordInteger add(long lx, long ly) 
	{
		this.ix += lx;
		this.iy += ly;
		return this;
	}

	@Override
	public CoordInteger offset(CoordInteger coord) 
	{
		return new CoordInteger(ix + coord.ix, iy + coord.iy, iz + coord.iz);
	}

	@Override
	public CoordFloat offset(CoordFloat coord) 
	{
		return new CoordFloat(ix + coord.fx, iy + coord.fy, iz + coord.fz);
	}

	@Override
	public CoordDouble offset(CoordDouble coord) 
	{
		return new CoordDouble(ix + coord.dx, iy + coord.dy, iz + coord.dz);
	}

	@Override
	public CoordInteger offset(int xm, int ym, int zm)
	{
		return new CoordInteger(ix + xm, iy + ym, iz + zm);
	}

	@Override
	public CoordFloat offset(float fxm, float fym, float fzm) 
	{
		return new CoordFloat(ix + fxm, iy + fym, iz + fzm);
	}

	@Override
	public CoordDouble offset(double dxm, double dym, double dzm) 
	{
		return new CoordDouble(ix + dxm, iy + dym, iz + dzm);
	}

	@Override
	public CoordInteger offset(long lxm, long lym, long lzm)
	{
		return new CoordInteger(ix + lxm, iy + lym, iz + lzm);
	}

	public CoordInteger offset(int xm, int ym) 
	{
		return new CoordInteger(ix + xm, iy + ym, iz);
	}

	public CoordFloat offset(float fxm, float fym) 
	{
		return new CoordFloat(ix + fxm, iy + fym, iz);
	}

	public CoordDouble offset(double dxm, double dym) 
	{
		return new CoordDouble(ix + dxm, iy + dym, iz);
	}

	public CoordInteger offset(long lxm, long lym)
	{
		return new CoordInteger(ix + lxm, iy + lym, iz);
	}

	public CoordInteger setX(int newX) 
	{
		return new CoordInteger(newX, iy, iz);
	}

	public CoordInteger setY(int newY) 
	{
		return new CoordInteger(ix, newY, iz);
	}

	public CoordInteger setZ(int newZ)
	{
		return new CoordInteger(ix, iy, newZ);
	}

	public int getBlockId(IBlockAccess world) 
	{
		return world.getBlockId(ix, iy, iz);
	}

	public int getMetadata(IBlockAccess world) 
	{
		return world.getBlockMetadata(ix, iy, iz);
	}
	
	public boolean setBlockWithMetaAndNotify(World world, int id, int metadata) 
	{
		return world.setBlock(ix, iy, iz, id, metadata, 2); // 2 = client-side update
	}

	public boolean setBlockWithMetaNoNotify(World world, int id, int metadata) 
	{
		return world.setBlock(ix, iy, iz, id, metadata, 1); // 1 = update only, no notify
	}

	public void setBlockMetaWithNotify(World world, int metadata)
	{
		world.setBlockMetadataWithNotify(ix, iy, iz, metadata, 2);
	}

	public void setMetaNoNotify(World world, int metadata) 
	{
		world.setBlockMetadataWithNotify(ix, iy, iz, metadata, 1);
	}

	public void setBlock(World world, int id) 
	{
		world.setBlock(ix, iy, iz, id);
	}

	public TileEntity getTileEntity(IBlockAccess world) 
	{
		return world.getBlockTileEntity(ix, iy, iz);
	}

	public boolean isPoweredDirectly(World world) 
	{
		return world.isBlockIndirectlyGettingPowered(ix, iy, iz);
	}

	public boolean isPoweredIndirectly(World world) 
	{
		return world.isBlockIndirectlyGettingPowered(ix, iy, iz);
	}

	@Override
	public CoordInteger round() 
	{
		return copy();
	}

	@Override
	public CoordInteger floor() 
	{
		return copy();
	}

	@Override
	public CoordInteger ceil() 
	{
		return copy();
	}

	public double distanceTo(CoordInteger coord)
	{
		return Math.sqrt((ix - coord.ix) * (ix - coord.ix) + (iy - coord.iy) * (iy - coord.iy) + (iz - coord.iz) * (iz - coord.iz));
	}

	public CoordDouble getVectorTo(CoordInteger coord) 
	{
		return new CoordDouble(coord.ix - ix, coord.iy - iy, coord.iz - iz);
	}

	public static CoordDouble getVector(CoordInteger coord1, CoordInteger coord2) 
	{
		return new CoordDouble(coord2.ix - coord1.ix, coord2.iy - coord1.iy, coord2.iz - coord1.iz);
	}

	public double distanceHorizontalTo(CoordInteger coord) 
	{
		return Math.sqrt((ix - coord.ix) * (ix - coord.ix) + (iz - coord.iz) * (iz - coord.iz));
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tagCompound)
	{
		tagCompound.setInteger("ix", ix);
		tagCompound.setInteger("iy", iy);
		tagCompound.setInteger("iz", iz);
		return tagCompound;
	}

	@Override
	public CoordInteger readFromNBT(NBTTagCompound tagCompound) 
	{
		ix = tagCompound.getInteger("ix");
		iy = tagCompound.getInteger("iy");
		iz = tagCompound.getInteger("iz");
		return this;
	}

	public CoordInteger multiply(int amount)
	{
		return new CoordInteger(ix * amount, iy * amount, iz * amount);
	}
}
