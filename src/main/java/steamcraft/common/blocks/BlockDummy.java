package steamcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import steamcraft.common.blocks.machines.BlockLightningRod;

public class BlockDummy extends Block
{

	public BlockDummy(Material blockMaterial)
	{
		super(blockMaterial);

	}
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		if(world.getBlock(x, y-1, z) instanceof BlockLightningRod)
		{
			this.setBlockBounds(0.3F, 0, 0.3F, 0.7F, 1.0F, 0.7F);
		}
		else world.setBlockToAir(x, y, z);
	}
	@Override
	public int getRenderType()
	{
		return 0;
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
