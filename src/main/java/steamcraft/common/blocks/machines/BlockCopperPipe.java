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
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import steamcraft.common.tiles.TileCopperPipe;

/**
 * @author warlordjones
 *
 */
public class BlockCopperPipe extends BlockContainerMod
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
}
