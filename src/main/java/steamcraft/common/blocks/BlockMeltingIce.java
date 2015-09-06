package steamcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.BlockIce;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockMeltingIce extends BlockIce
{
	boolean meltsIntoWater;

	public BlockMeltingIce(boolean meltsIntoWater)
	{
		this.meltsIntoWater = meltsIntoWater;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if (this.meltsIntoWater)
			world.setBlock(x, y, z, Blocks.water);
		else
			world.setBlock(x, y, z, Blocks.air);
	}

}
