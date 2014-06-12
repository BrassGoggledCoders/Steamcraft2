package steamcraft.common.blocks.machine;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.common.tiles.TileMultiBlock;

public class BlockDropHammer extends BlockContainerMod
{

	public BlockDropHammer(Material mat)
	{
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		System.out.print("apple");
		return new TileMultiBlock();
	}
}
