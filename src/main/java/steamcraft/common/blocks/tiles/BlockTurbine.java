package steamcraft.common.blocks.tiles;

import steamcraft.common.tiles.TileTurbine;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockTurbine extends BlockContainerMod
{

	public BlockTurbine(Material mat)
	{
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileTurbine();
	}

}
