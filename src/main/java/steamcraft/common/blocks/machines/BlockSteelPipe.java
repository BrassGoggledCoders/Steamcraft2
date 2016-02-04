
package steamcraft.common.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import steamcraft.client.lib.RenderIDs;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.tiles.TileSteelPipe;

/**
 * @author Decebaldecebal
 *
 */
public class BlockSteelPipe extends BlockCopperPipe
{
	public BlockSteelPipe(Material mat)
	{
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_)
	{
		return new TileSteelPipe();
	}

	@Override
	public int getRenderType()
	{
		return RenderIDs.blockSteelPipeRI;
	}

	@Override
	public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ)
	{
		if (world.getBlock(tileX, tileY, tileZ) != InitBlocks.blockSteelPipe)
		{
			TileSteelPipe tile = (TileSteelPipe) world.getTileEntity(x, y, z);
			tile.updateConnections(); // only on server
		}
	}
}
