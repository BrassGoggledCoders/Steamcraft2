package steamcraft.common.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.common.tiles.TileSawmill;

public class BlockSawmill extends BaseContainerBlock
{
	/*
	 * TODO - Only Works on X Axis - Does not support dark oak/acacia wood. - Model - Should take time to saw - Plank blocks share number of planks & meta.
	 */
	public BlockSawmill(Material mat)
	{
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileSawmill();
	}

}
