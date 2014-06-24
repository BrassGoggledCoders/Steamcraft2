package steamcraft.common.blocks.tiles;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.common.tiles.TileCopperPipe;


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

}
