
package steamcraft.common.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.IFluidBlock;

public class WorldGenSpout extends WorldGenerator
{
	private final Block block;

	public WorldGenSpout(Block block)
	{
		this.block = block;
	}

	// Adapted from BCs oilspout gen code
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		// Find ground level
		int groundLevel = this.getTopBlock(world, x, z);
		if (groundLevel < 5)
		{
			return false;
		}

		int wellX = x;
		int wellZ = z;
		int wellHeight = 6 + random.nextInt(3);
		int maxHeight = groundLevel + wellHeight;
		if (maxHeight >= (world.getActualHeight() - 1))
		{
			return false;
		}

		new WorldGenLakes(this.block).generate(world, random, x, y, z);

		int wellY = 50 + random.nextInt(10);

		for (int i = wellY + 1; i <= maxHeight; ++i)
		{
			world.setBlock(wellX, i, wellZ, this.block, 0, 3);
		}
		for (int i = wellY; i <= (maxHeight - (wellHeight / 2)); ++i)
		{
			world.setBlock(wellX + 1, i, wellZ, this.block, 0, 3);
			world.setBlock(wellX - 1, i, wellZ, this.block, 0, 3);
			world.setBlock(wellX, i, wellZ + 1, this.block, 0, 3);
			world.setBlock(wellX, i, wellZ - 1, this.block, 0, 3);
		}
		return true;
	}

	private int getTopBlock(World world, int x, int z)
	{
		Chunk chunk = world.getChunkFromBlockCoords(x, z);
		int y = chunk.getTopFilledSegment() + 15;

		int trimmedX = x & 15;
		int trimmedZ = z & 15;

		for (; y > 0; --y)
		{
			Block block = chunk.getBlock(trimmedX, y, trimmedZ);

			if (block.isAir(world, x, y, z))
			{
				continue;
			}

			if (block instanceof BlockStaticLiquid)
			{
				return y;
			}

			if (block instanceof BlockFluidBase)
			{
				return y;
			}

			if (block instanceof IFluidBlock)
			{
				return y;
			}

			if (!block.getMaterial().blocksMovement())
			{
				continue;
			}

			if (block instanceof BlockFlower)
			{
				continue;
			}

			return y - 1;
		}

		return -1;
	}
}
