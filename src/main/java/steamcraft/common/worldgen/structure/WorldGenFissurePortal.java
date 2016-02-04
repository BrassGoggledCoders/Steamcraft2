
package steamcraft.common.worldgen.structure;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import steamcraft.common.init.InitBlocks;

public class WorldGenFissurePortal extends WorldGenerator
{
	private boolean locationIsValidSpawn(World world, int i, int j, int k)
	{

		return world.getBlock(i, j, k) == Blocks.bedrock;
	}

	public WorldGenFissurePortal()
	{
	}

	private void setBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		world.setBlock(x, y, z, block, metadata, 2);
	}

	@Override
	public boolean generate(World world, Random rand, int i, int j, int k)
	{
		// System.out.print("Gen at: " + i + " " + j + " " + k);
		if (!this.locationIsValidSpawn(world, i, j, k))
		{
			return false;
		}

		k = k - 10;
		i = i - 10;

		this.setBlock(world, i + 0, j + 0, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 0, j + 0, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 0, j + 0, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 0, j + 0, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 0, j + 0, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 0, j + 0, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 0, j + 0, k + 11, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 1, j + 0, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 0, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 0, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 0, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 0, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 0, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 0, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 0, k + 9, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 1, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 1, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 1, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 1, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 1, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 1, j + 1, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 0, k + 1, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 0, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 0, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 0, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 0, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 0, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 0, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 0, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 0, k + 9, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 0, k + 10, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 1, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 2, j + 1, k + 3, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 2, j + 1, k + 4, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 2, j + 1, k + 5, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 2, j + 1, k + 6, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 2, j + 1, k + 7, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 2, j + 1, k + 8, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 2, j + 1, k + 9, Blocks.bedrock, 0);
		world.setBlockToAir(i + 2, j + 2, k + 3);
		world.setBlockToAir(i + 2, j + 2, k + 4);
		world.setBlockToAir(i + 2, j + 2, k + 5);
		world.setBlockToAir(i + 2, j + 2, k + 6);
		world.setBlockToAir(i + 2, j + 2, k + 7);
		world.setBlockToAir(i + 2, j + 2, k + 8);
		this.setBlock(world, i + 3, j + 0, k + 0, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 0, k + 1, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 0, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 0, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 0, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 0, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 0, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 0, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 0, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 0, k + 9, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 0, k + 10, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 0, k + 11, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 1, k + 1, Blocks.bedrock, 0);
		this.setBlock(world, i + 3, j + 1, k + 2, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 3, j + 1, k + 3, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 3, j + 1, k + 4, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 3, j + 1, k + 5, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 3, j + 1, k + 6, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 3, j + 1, k + 7, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 3, j + 1, k + 8, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 3, j + 1, k + 9, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 3, j + 1, k + 10, Blocks.bedrock, 0);
		world.setBlockToAir(i + 3, j + 2, k + 2);
		world.setBlockToAir(i + 3, j + 2, k + 3);
		world.setBlockToAir(i + 3, j + 2, k + 4);
		world.setBlockToAir(i + 3, j + 2, k + 5);
		world.setBlockToAir(i + 3, j + 2, k + 6);
		world.setBlockToAir(i + 3, j + 2, k + 7);
		world.setBlockToAir(i + 3, j + 2, k + 8);
		world.setBlockToAir(i + 3, j + 2, k + 9);
		world.setBlockToAir(i + 3, j + 3, k + 4);
		world.setBlockToAir(i + 3, j + 3, k + 5);
		world.setBlockToAir(i + 3, j + 3, k + 6);
		world.setBlockToAir(i + 3, j + 3, k + 7);
		world.setBlockToAir(i + 3, j + 3, k + 8);
		this.setBlock(world, i + 4, j + 0, k + 0, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 0, k + 1, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 0, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 0, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 0, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 0, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 0, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 0, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 0, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 0, k + 9, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 0, k + 10, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 0, k + 11, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 1, k + 1, Blocks.bedrock, 0);
		this.setBlock(world, i + 4, j + 1, k + 2, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 4, j + 1, k + 3, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 4, j + 1, k + 4, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 4, j + 1, k + 5, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 4, j + 1, k + 6, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 4, j + 1, k + 7, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 4, j + 1, k + 8, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 4, j + 1, k + 9, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 4, j + 1, k + 10, Blocks.bedrock, 0);
		world.setBlockToAir(i + 4, j + 2, k + 2);
		world.setBlockToAir(i + 4, j + 2, k + 3);
		world.setBlockToAir(i + 4, j + 2, k + 4);
		world.setBlockToAir(i + 4, j + 2, k + 5);
		world.setBlockToAir(i + 4, j + 2, k + 6);
		world.setBlockToAir(i + 4, j + 2, k + 7);
		world.setBlockToAir(i + 4, j + 2, k + 8);
		world.setBlockToAir(i + 4, j + 2, k + 9);
		world.setBlockToAir(i + 4, j + 3, k + 5);
		world.setBlockToAir(i + 4, j + 3, k + 6);
		world.setBlockToAir(i + 4, j + 3, k + 7);
		this.setBlock(world, i + 5, j + 0, k + 1, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 0, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 0, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 0, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 0, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 0, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 0, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 0, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 0, k + 9, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 0, k + 10, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 1, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 1, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 5, j + 1, k + 4, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 5, j + 1, k + 5, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 5, j + 1, k + 6, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 5, j + 1, k + 7, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 5, j + 1, k + 8, InitBlocks.blockFissurePortal, 0);
		this.setBlock(world, i + 5, j + 1, k + 9, Blocks.bedrock, 0);
		world.setBlockToAir(i + 5, j + 2, k + 4);
		world.setBlockToAir(i + 5, j + 2, k + 5);
		world.setBlockToAir(i + 5, j + 2, k + 6);
		world.setBlockToAir(i + 5, j + 2, k + 7);
		world.setBlockToAir(i + 5, j + 2, k + 8);
		this.setBlock(world, i + 6, j + 0, k + 2, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 0, k + 3, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 0, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 0, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 0, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 0, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 0, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 0, k + 9, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 1, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 1, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 1, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 1, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 6, j + 1, k + 8, Blocks.bedrock, 0);
		this.setBlock(world, i + 7, j + 0, k + 4, Blocks.bedrock, 0);
		this.setBlock(world, i + 7, j + 0, k + 5, Blocks.bedrock, 0);
		this.setBlock(world, i + 7, j + 0, k + 6, Blocks.bedrock, 0);
		this.setBlock(world, i + 7, j + 0, k + 7, Blocks.bedrock, 0);
		this.setBlock(world, i + 7, j + 0, k + 8, Blocks.bedrock, 0);

		return true;
	}
}
