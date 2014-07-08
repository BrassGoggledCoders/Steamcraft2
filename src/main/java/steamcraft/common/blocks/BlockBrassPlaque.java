package steamcraft.common.blocks;

import net.minecraft.block.BlockSign;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.world.World;
import steamcraft.common.tiles.TileBrassPlaque;

public class BlockBrassPlaque extends BlockSign
{
	public BlockBrassPlaque(Class<? extends TileEntitySign> tile, boolean editable)
	{
		super(tile, editable);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileBrassPlaque();
	}

}
