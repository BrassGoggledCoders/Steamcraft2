package steamcraft.common.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenUndergroundHouse extends WorldGenerator implements IWorldGenerator
{
	protected Block[] getValidSpawnBlocks()
	{
		return new Block[] {
				Blocks.stone,
				Blocks.bedrock,
				Blocks.gravel
		};
	}

	public boolean locationIsValidSpawn(World world, int i, int j, int k)
	{
		int distanceToAir = 0;
		Block check = world.getBlock(i, j, k);

		while(check != Blocks.air)
		{
			if(distanceToAir > 3)
			{
				return false;
			}

			distanceToAir++;
			check = world.getBlock(i, j + distanceToAir, k);
		}

		j += distanceToAir - 1;

		Block block = world.getBlock(i, j, k);
		Block blockAbove = world.getBlock(i, j + 1, k);
		Block blockBelow = world.getBlock(i, j - 1, k);

		for(Block x : this.getValidSpawnBlocks())
		{
			if(blockAbove != Blocks.air)
			{
				return false;
			}
			if(block == x)
			{
				return true;
			}
			else if((block == Blocks.snow) && (blockBelow == x))
			{
				return true;
			}
		}

		return false;
	}

	public WorldGenUndergroundHouse()
	{
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
	{
	}

	public void setBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		Block b1 = world.getBlock(x, y, z);

		if(b1.isAir(world, x, y, z) || b1.isLeaves(world, x, y, z))
		{
			world.setBlock(x, y, z, block, metadata, 2);
		}
	}

	@Override
	public boolean generate(World world, Random rand, int i, int j, int k)
	{
		// check that each corner is one of the valid spawn blocks
		if(!this.locationIsValidSpawn(world, i, j, k) || !this.locationIsValidSpawn(world, i + 10, j, k) || !this.locationIsValidSpawn(world, i + 10, j, k + 9)
				|| !this.locationIsValidSpawn(world, i, j, k + 9))
		{
			return false;
		}

		k = k - 10;
		i = i - 10;

		this.setBlock(world, i + 1, j + 1, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 1, k + 2, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 1, k + 3, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 1, k + 4, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 1, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 2, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 2, k + 2, Blocks.stone_brick_stairs, 1);
		this.setBlock(world, i + 1, j + 2, k + 3, Blocks.stone_brick_stairs, 1);
		this.setBlock(world, i + 1, j + 2, k + 4, Blocks.stone_brick_stairs, 1);
		this.setBlock(world, i + 1, j + 2, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 3, k + 1, Blocks.glowstone, 0);
		this.setBlock(world, i + 1, j + 3, k + 2, Blocks.stone_brick_stairs, 5);
		this.setBlock(world, i + 1, j + 3, k + 3, Blocks.stone_brick_stairs, 5);
		this.setBlock(world, i + 1, j + 3, k + 4, Blocks.stone_brick_stairs, 5);
		this.setBlock(world, i + 1, j + 3, k + 5, Blocks.glowstone, 0);
		this.setBlock(world, i + 1, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 4, k + 2, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 4, k + 3, Blocks.stonebrick, 0);
		this.setBlock(world, i + 1, j + 4, k + 4, Blocks.stonebrick, 1);
		this.setBlock(world, i + 1, j + 4, k + 5, Blocks.stonebrick, 1);
		this.setBlock(world, i + 2, j + 1, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 2, j + 1, k + 2, Blocks.stonebrick, 1);
		this.setBlock(world, i + 2, j + 1, k + 3, Blocks.stonebrick, 1);
		this.setBlock(world, i + 2, j + 1, k + 4, Blocks.stonebrick, 0);
		this.setBlock(world, i + 2, j + 1, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 2, j + 2, k + 1, Blocks.stone_brick_stairs, 3);
		this.setBlock(world, i + 2, j + 2, k + 2, Blocks.bed, 10);
		this.setBlock(world, i + 2, j + 2, k + 3, Blocks.bed, 2);
		this.setBlock(world, i + 2, j + 2, k + 4, Blocks.chest, 5);
		this.setBlock(world, i + 2, j + 2, k + 5, Blocks.stone_brick_stairs, 2);
		this.setBlock(world, i + 2, j + 3, k + 1, Blocks.stone_brick_stairs, 7);
		world.setBlockToAir(i + 2, j + 3, k + 2);
		world.setBlockToAir(i + 2, j + 3, k + 3);
		world.setBlockToAir(i + 2, j + 3, k + 4);
		this.setBlock(world, i + 2, j + 3, k + 5, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 2, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 2, j + 4, k + 2, Blocks.stone_brick_stairs, 7);
		this.setBlock(world, i + 2, j + 4, k + 3, Blocks.stone_brick_stairs, 5);
		this.setBlock(world, i + 2, j + 4, k + 4, Blocks.stone_brick_stairs, 5);
		this.setBlock(world, i + 2, j + 4, k + 5, Blocks.stonebrick, 1);
		this.setBlock(world, i + 3, j + 1, k + 1, Blocks.cobblestone, 0);
		this.setBlock(world, i + 3, j + 1, k + 2, Blocks.stonebrick, 1);
		this.setBlock(world, i + 3, j + 1, k + 3, Blocks.stonebrick, 0);
		this.setBlock(world, i + 3, j + 1, k + 4, Blocks.stonebrick, 0);
		this.setBlock(world, i + 3, j + 1, k + 5, Blocks.cobblestone, 0);
		this.setBlock(world, i + 3, j + 2, k + 1, Blocks.stone_brick_stairs, 3);
		world.setBlockToAir(i + 3, j + 2, k + 2);
		world.setBlockToAir(i + 3, j + 2, k + 3);
		world.setBlockToAir(i + 3, j + 2, k + 4);
		this.setBlock(world, i + 3, j + 2, k + 5, Blocks.stone_brick_stairs, 2);
		this.setBlock(world, i + 3, j + 3, k + 1, Blocks.stone_brick_stairs, 7);
		world.setBlockToAir(i + 3, j + 3, k + 2);
		world.setBlockToAir(i + 3, j + 3, k + 3);
		world.setBlockToAir(i + 3, j + 3, k + 4);
		this.setBlock(world, i + 3, j + 3, k + 5, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 3, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 3, j + 4, k + 2, Blocks.stone_brick_stairs, 7);
		this.setBlock(world, i + 3, j + 4, k + 3, Blocks.stone_slab, 8);
		this.setBlock(world, i + 3, j + 4, k + 4, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 3, j + 4, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 4, j + 1, k + 1, Blocks.cobblestone, 0);
		this.setBlock(world, i + 4, j + 1, k + 2, Blocks.stonebrick, 0);
		this.setBlock(world, i + 4, j + 1, k + 3, Blocks.stonebrick, 0);
		this.setBlock(world, i + 4, j + 1, k + 4, Blocks.stonebrick, 0);
		this.setBlock(world, i + 4, j + 1, k + 5, Blocks.cobblestone, 0);
		this.setBlock(world, i + 4, j + 2, k + 1, Blocks.stone_brick_stairs, 3);
		world.setBlockToAir(i + 4, j + 2, k + 2);
		world.setBlockToAir(i + 4, j + 2, k + 3);
		world.setBlockToAir(i + 4, j + 2, k + 4);
		this.setBlock(world, i + 4, j + 2, k + 5, Blocks.stone_brick_stairs, 2);
		this.setBlock(world, i + 4, j + 3, k + 1, Blocks.stone_brick_stairs, 7);
		world.setBlockToAir(i + 4, j + 3, k + 2);
		world.setBlockToAir(i + 4, j + 3, k + 3);
		world.setBlockToAir(i + 4, j + 3, k + 4);
		this.setBlock(world, i + 4, j + 3, k + 5, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 4, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 4, j + 4, k + 2, Blocks.stone_brick_stairs, 7);
		this.setBlock(world, i + 4, j + 4, k + 3, Blocks.stone_slab, 8);
		this.setBlock(world, i + 4, j + 4, k + 4, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 4, j + 4, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 1, k + 1, Blocks.cobblestone, 0);
		this.setBlock(world, i + 5, j + 1, k + 2, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 1, k + 3, Blocks.stonebrick, 2);
		this.setBlock(world, i + 5, j + 1, k + 4, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 1, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 1, k + 6, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 1, k + 7, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 1, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 2, k + 1, Blocks.stone_brick_stairs, 3);
		world.setBlockToAir(i + 5, j + 2, k + 2);
		world.setBlockToAir(i + 5, j + 2, k + 3);
		world.setBlockToAir(i + 5, j + 2, k + 4);
		this.setBlock(world, i + 5, j + 2, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 2, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 3, k + 1, Blocks.stone_brick_stairs, 7);
		world.setBlockToAir(i + 5, j + 3, k + 2);
		world.setBlockToAir(i + 5, j + 3, k + 3);
		world.setBlockToAir(i + 5, j + 3, k + 4);
		this.setBlock(world, i + 5, j + 3, k + 5, Blocks.glowstone, 0);
		this.setBlock(world, i + 5, j + 3, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 4, k + 2, Blocks.stone_brick_stairs, 7);
		this.setBlock(world, i + 5, j + 4, k + 3, Blocks.stone_slab, 8);
		this.setBlock(world, i + 5, j + 4, k + 4, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 5, j + 4, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 5, j + 4, k + 6, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 5, j + 4, k + 7, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 5, j + 4, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 6, j + 1, k + 1, Blocks.cobblestone, 0);
		this.setBlock(world, i + 6, j + 1, k + 2, Blocks.stonebrick, 0);
		this.setBlock(world, i + 6, j + 1, k + 3, Blocks.cobblestone, 0);
		this.setBlock(world, i + 6, j + 1, k + 4, Blocks.stonebrick, 2);
		this.setBlock(world, i + 6, j + 1, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 6, j + 1, k + 6, Blocks.stonebrick, 0);
		this.setBlock(world, i + 6, j + 1, k + 7, Blocks.stonebrick, 0);
		this.setBlock(world, i + 6, j + 1, k + 8, Blocks.cobblestone, 0);
		this.setBlock(world, i + 6, j + 2, k + 1, Blocks.stone_brick_stairs, 3);
		world.setBlockToAir(i + 6, j + 2, k + 2);
		world.setBlockToAir(i + 6, j + 2, k + 3);
		world.setBlockToAir(i + 6, j + 2, k + 4);
		world.setBlockToAir(i + 6, j + 2, k + 5);
		this.setBlock(world, i + 6, j + 2, k + 8, Blocks.stone_brick_stairs, 2);
		this.setBlock(world, i + 6, j + 3, k + 1, Blocks.stone_brick_stairs, 7);
		world.setBlockToAir(i + 6, j + 3, k + 2);
		world.setBlockToAir(i + 6, j + 3, k + 3);
		world.setBlockToAir(i + 6, j + 3, k + 4);
		world.setBlockToAir(i + 6, j + 3, k + 5);
		this.setBlock(world, i + 6, j + 3, k + 8, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 6, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 6, j + 4, k + 2, Blocks.stone_brick_stairs, 7);
		this.setBlock(world, i + 6, j + 4, k + 3, Blocks.stone_slab, 8);
		this.setBlock(world, i + 6, j + 4, k + 4, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 6, j + 4, k + 5, Blocks.stone_brick_stairs, 5);
		this.setBlock(world, i + 6, j + 4, k + 6, Blocks.stone_brick_stairs, 5);
		this.setBlock(world, i + 6, j + 4, k + 7, Blocks.stone_brick_stairs, 5);
		this.setBlock(world, i + 6, j + 4, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 7, j + 1, k + 1, Blocks.cobblestone, 0);
		this.setBlock(world, i + 7, j + 1, k + 2, Blocks.stonebrick, 0);
		this.setBlock(world, i + 7, j + 1, k + 3, Blocks.cobblestone, 0);
		this.setBlock(world, i + 7, j + 1, k + 4, Blocks.cobblestone, 0);
		this.setBlock(world, i + 7, j + 1, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 7, j + 1, k + 6, Blocks.stonebrick, 0);
		this.setBlock(world, i + 7, j + 1, k + 7, Blocks.stonebrick, 1);
		this.setBlock(world, i + 7, j + 1, k + 8, Blocks.mossy_cobblestone, 0);
		this.setBlock(world, i + 7, j + 2, k + 1, Blocks.stone_brick_stairs, 3);
		world.setBlockToAir(i + 7, j + 2, k + 2);
		world.setBlockToAir(i + 7, j + 2, k + 3);
		world.setBlockToAir(i + 7, j + 2, k + 4);
		world.setBlockToAir(i + 7, j + 2, k + 5);
		world.setBlockToAir(i + 7, j + 2, k + 6);
		world.setBlockToAir(i + 7, j + 2, k + 7);
		this.setBlock(world, i + 7, j + 2, k + 8, Blocks.stone_brick_stairs, 2);
		this.setBlock(world, i + 7, j + 3, k + 1, Blocks.stone_brick_stairs, 7);
		world.setBlockToAir(i + 7, j + 3, k + 2);
		world.setBlockToAir(i + 7, j + 3, k + 3);
		world.setBlockToAir(i + 7, j + 3, k + 4);
		world.setBlockToAir(i + 7, j + 3, k + 5);
		world.setBlockToAir(i + 7, j + 3, k + 6);
		world.setBlockToAir(i + 7, j + 3, k + 7);
		this.setBlock(world, i + 7, j + 3, k + 8, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 7, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 7, j + 4, k + 2, Blocks.stone_brick_stairs, 7);
		this.setBlock(world, i + 7, j + 4, k + 3, Blocks.stone_slab, 8);
		this.setBlock(world, i + 7, j + 4, k + 4, Blocks.stone_slab, 8);
		this.setBlock(world, i + 7, j + 4, k + 5, Blocks.stone_slab, 8);
		this.setBlock(world, i + 7, j + 4, k + 6, Blocks.stone_slab, 8);
		this.setBlock(world, i + 7, j + 4, k + 7, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 7, j + 4, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 8, j + 1, k + 1, Blocks.cobblestone, 0);
		this.setBlock(world, i + 8, j + 1, k + 2, Blocks.cauldron, 0);
		this.setBlock(world, i + 8, j + 1, k + 3, Blocks.stonebrick, 2);
		this.setBlock(world, i + 8, j + 1, k + 4, Blocks.stonebrick, 2);
		this.setBlock(world, i + 8, j + 1, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 8, j + 1, k + 6, Blocks.stonebrick, 1);
		this.setBlock(world, i + 8, j + 1, k + 7, Blocks.stonebrick, 1);
		this.setBlock(world, i + 8, j + 1, k + 8, Blocks.mossy_cobblestone, 0);
		this.setBlock(world, i + 8, j + 2, k + 1, Blocks.stone_brick_stairs, 3);
		world.setBlockToAir(i + 8, j + 2, k + 3);
		world.setBlockToAir(i + 8, j + 2, k + 4);
		world.setBlockToAir(i + 8, j + 2, k + 5);
		world.setBlockToAir(i + 8, j + 2, k + 6);
		world.setBlockToAir(i + 8, j + 2, k + 7);
		this.setBlock(world, i + 8, j + 2, k + 8, Blocks.stone_brick_stairs, 2);
		this.setBlock(world, i + 8, j + 3, k + 1, Blocks.stone_brick_stairs, 7);
		world.setBlockToAir(i + 8, j + 3, k + 2);
		world.setBlockToAir(i + 8, j + 3, k + 3);
		world.setBlockToAir(i + 8, j + 3, k + 4);
		world.setBlockToAir(i + 8, j + 3, k + 5);
		world.setBlockToAir(i + 8, j + 3, k + 6);
		world.setBlockToAir(i + 8, j + 3, k + 7);
		this.setBlock(world, i + 8, j + 3, k + 8, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 8, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 8, j + 4, k + 2, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 8, j + 4, k + 3, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 8, j + 4, k + 4, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 8, j + 4, k + 5, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 8, j + 4, k + 6, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 8, j + 4, k + 7, Blocks.stone_brick_stairs, 6);
		this.setBlock(world, i + 8, j + 4, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 1, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 1, k + 2, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 1, k + 3, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 1, k + 4, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 1, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 1, k + 6, Blocks.cobblestone, 0);
		this.setBlock(world, i + 9, j + 1, k + 7, Blocks.cobblestone, 0);
		this.setBlock(world, i + 9, j + 1, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 2, k + 1, Blocks.stonebrick, 2);
		this.setBlock(world, i + 9, j + 2, k + 2, Blocks.stone_brick_stairs, 0);
		this.setBlock(world, i + 9, j + 2, k + 3, Blocks.stone_brick_stairs, 0);
		this.setBlock(world, i + 9, j + 2, k + 4, Blocks.stone_brick_stairs, 0);
		this.setBlock(world, i + 9, j + 2, k + 5, Blocks.stone_brick_stairs, 0);
		this.setBlock(world, i + 9, j + 2, k + 6, Blocks.stone_brick_stairs, 0);
		this.setBlock(world, i + 9, j + 2, k + 7, Blocks.stone_brick_stairs, 0);
		this.setBlock(world, i + 9, j + 2, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 3, k + 1, Blocks.glowstone, 0);
		this.setBlock(world, i + 9, j + 3, k + 2, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 9, j + 3, k + 3, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 9, j + 3, k + 4, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 9, j + 3, k + 5, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 9, j + 3, k + 6, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 9, j + 3, k + 7, Blocks.stone_brick_stairs, 4);
		this.setBlock(world, i + 9, j + 3, k + 8, Blocks.glowstone, 0);
		this.setBlock(world, i + 9, j + 4, k + 1, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 4, k + 2, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 4, k + 3, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 4, k + 4, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 4, k + 5, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 4, k + 6, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 4, k + 7, Blocks.stonebrick, 0);
		this.setBlock(world, i + 9, j + 4, k + 8, Blocks.stonebrick, 0);
		this.setBlock(world, i + 6, j + 2, k + 6, Blocks.wooden_door, 0);
		this.setBlock(world, i + 6, j + 2, k + 7, Blocks.wooden_door, 0);
		world.setBlockMetadataWithNotify(i + 6, j + 3, k + 6, 8, 2);
		world.setBlockMetadataWithNotify(i + 6, j + 3, k + 7, 9, 2);
		world.setBlockMetadataWithNotify(i + 8, j + 2, k + 2, 2, 2);

		return true;
	}
}
