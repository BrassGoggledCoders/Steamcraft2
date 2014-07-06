/**
 * This class was created by BrassGoggledCoders modding team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 */
package steamcraft.common.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import steamcraft.common.tiles.TileCopperPipe;

/**
 * @author warlordjones
 *
 */
public class BlockCopperPipe extends BlockContainerMod
{
	static float pixel = 1/16f;
	
	public BlockCopperPipe(Material mat)
	{
		super(mat);
		
		this.setBlockBounds(6*pixel, 6*pixel, 6*pixel, 1-6*pixel, 1-6*pixel, 1-6*pixel);
		this.useNeighborBrightness = true;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileCopperPipe();
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ) 
	{	
		TileCopperPipe tile = (TileCopperPipe) world.getTileEntity(x, y, z);
		tile.updateConnections();
			
		TileEntity t = world.getTileEntity(tileX, tileY, tileZ);
		if(t!=null && t instanceof TileCopperPipe)
		{
			tile = (TileCopperPipe) t;
			tile.updateConnections();
		}
	}
	
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return getBoundingBox(world, x, y, z);
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return getBoundingBox(world, x, y, z);
	}
	
	private AxisAlignedBB getBoundingBox(World world, int x, int y, int z)
	{
		TileCopperPipe pipe = (TileCopperPipe) world.getTileEntity(x, y, z);
		
		if(pipe!=null)
		{
			float minX = 6*pixel-(pipe.connections[5]!=null ? 6*pixel : 0);
			float maxX = 1-6*pixel+(pipe.connections[4]!=null ? 6*pixel : 0);
			
			float minY = 6*pixel-(pipe.connections[1]!=null ? 6*pixel : 0);
			float maxY = 1-6*pixel+(pipe.connections[0]!=null ? 6*pixel : 0);
			
			float minZ = 6*pixel-(pipe.connections[3]!=null ? 6*pixel : 0);
			float maxZ = 1-6*pixel+(pipe.connections[2]!=null ? 6*pixel : 0);
			
			this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}
		
		return AxisAlignedBB.getBoundingBox(x+this.minX, y+this.minY, z+this.minZ, x+this.maxX, y+this.maxY, z+this.maxZ);
	}
	
	/*
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int x, int y, int z)
	{
		this.setBlockBounds(0.33F, 0.2F, 0.33F, 0.67F, 0.53F, 0.67F);
		float minx = (float) this.minX;
		float maxx = (float) this.maxX;
		float miny = (float) this.minY;
		float maxy = (float) this.maxY;
		float minz = (float) this.minZ;
		float maxz = (float) this.maxZ;

		if (par1IBlockAccess.getBlock(x - 1, y, z) == this)
			minx = 0;

		if (par1IBlockAccess.getBlock(x + 1, y, z) == this)
			maxx = 1;

		if (par1IBlockAccess.getBlock(x, y - 1, z) == this)
			miny = 0;

		if (par1IBlockAccess.getBlock(x, y + 1, z) == this)
			maxy = 1;

		if (par1IBlockAccess.getBlock(x, y, z - 1) == this)
			minz = 0;

		if (par1IBlockAccess.getBlock(x, y, z + 1) == this)
			maxz = 1;

		this.setBlockBounds(minx, miny, minz, maxx, maxy, maxz);
	}
	*/
}
