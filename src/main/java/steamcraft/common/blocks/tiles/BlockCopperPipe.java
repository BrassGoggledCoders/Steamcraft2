package steamcraft.common.blocks.tiles;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import steamcraft.client.lib.RenderIDs;
import steamcraft.common.tiles.TileCopperPipe;

public class BlockCopperPipe extends BlockContainerMod implements ITileEntityProvider
{

	public BlockCopperPipe(Material mat)
	{
		super(mat);

	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileCopperPipe();
	}
	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.block.Block#getRenderType()
	 */
	/*@Override
	public int getRenderType()
	{
		return RenderIDs.blockCopperPipeRI;
	}*/

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.block.Block#isOpaqueCube()
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.block.Block#renderAsNormalBlock()
	 */
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int x, int y, int z)
	{
		this.setBlockBounds(0.33F, 0.2F, 0.33F, 0.67F, 0.53F, 0.67F);
		float minx = (float)this.minX;
		float maxx = (float)this.maxX;
		float miny = (float)this.minY;
		float maxy = (float)this.maxY;
		float minz = (float)this.minZ;
		float maxz = (float)this.maxZ;

		if (par1IBlockAccess.getBlock(x-1, y, z) == this)
			minx = 0;

		if (par1IBlockAccess.getBlock(x+1, y, z) == this)
			maxx = 1;

		if (par1IBlockAccess.getBlock(x, y-1, z) == this)
			miny = 0;


		if (par1IBlockAccess.getBlock(x, y+1, z) == this)
			maxy = 1;


		if (par1IBlockAccess.getBlock(x, y, z-1) == this)
			minz = 0;

		if (par1IBlockAccess.getBlock(x, y, z+1) == this)
			maxz = 1;


		this.setBlockBounds(minx, miny, minz, maxx, maxy, maxz);
	}


}
