/*
 * 
 */
package steamcraft.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.client.lib.RenderIDs;
import steamcraft.common.blocks.machines.BlockContainerMod;
import steamcraft.common.tiles.TileTeslaCoil;

public class BlockTeslaCoil extends BlockContainerMod
{
	public BlockTeslaCoil(Material mat)
	{
		super(mat);
		//setBlockBounds(0.3F, 0, 0.3F, 0.7F, 2.0F, 0.7F);
	}
	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileTeslaCoil();
	}
	@Override
	public int getRenderType()
	{
		return RenderIDs.blockTeslaCoilRI;
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
}
