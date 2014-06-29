package steamcraft.common.blocks.tiles;

import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
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
	@Override
	public int getRenderType()
	{
		return RenderIDs.blockCopperPipeRI;
	}

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


}
