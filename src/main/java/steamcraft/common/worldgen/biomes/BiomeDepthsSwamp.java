
package steamcraft.common.worldgen.biomes;

import java.util.Random;

import boilerplate.common.worldgen.WorldGenBlockgroup;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenVines;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.worldgen.WorldGenLeafPile;
import steamcraft.common.worldgen.trees.WorldGenDeadTree;
import steamcraft.common.worldgen.trees.WorldGenMangroveTree;
import steamcraft.common.worldgen.trees.WorldGenWillowTree;

public class BiomeDepthsSwamp extends BiomeDepthsBase
{

	public BiomeDepthsSwamp(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.swamp.name"));
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 1, 1, 1));
		this.waterColorMultiplier = 14745518;
		this.theBiomeDecorator.treesPerChunk = 15;
		this.theBiomeDecorator.flowersPerChunk = 1;
		this.theBiomeDecorator.deadBushPerChunk = 1;
		this.theBiomeDecorator.mushroomsPerChunk = 8;
		this.theBiomeDecorator.bigMushroomsPerChunk = 1;
		this.theBiomeDecorator.reedsPerChunk = 10;
		this.theBiomeDecorator.clayPerChunk = 1;
		this.theBiomeDecorator.waterlilyPerChunk = 4;
		this.theBiomeDecorator.sandPerChunk2 = 0;
		this.theBiomeDecorator.sandPerChunk = 0;
		this.theBiomeDecorator.grassPerChunk = 5;
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random rand)
	{
		int randNum = rand.nextInt(3);
		if (randNum == 0)
			return new WorldGenMangroveTree(false);
		else if (randNum == 2)
			return new WorldGenDeadTree(false);
		else
			return new WorldGenWillowTree(false);
	}

	@Override
	public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_,
			double p_150573_7_)
	{
		double d1 = plantNoise.func_151601_a(p_150573_5_ * 0.25D, p_150573_6_ * 0.25D);

		if (d1 > 0.0D)
		{
			int k = p_150573_5_ & 15;
			int l = p_150573_6_ & 15;
			int i1 = p_150573_3_.length / 256;

			for (int j1 = 255; j1 >= 0; --j1)
			{
				int k1 = (((l * 16) + k) * i1) + j1;

				if ((p_150573_3_[k1] == null) || (p_150573_3_[k1].getMaterial() != Material.air))
				{
					if ((j1 == 62) && (p_150573_3_[k1] != Blocks.water))
					{
						p_150573_3_[k1] = Blocks.water;

						if (d1 < 0.12D)
						{
							p_150573_3_[k1 + 1] = Blocks.waterlily;
						}
					}

					break;
				}
			}
		}

		this.genBiomeTerrain(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
	}

	@Override
	public void decorate(World world, Random random, int x, int z)
	{
		for (int l = 0; l < 30; ++l)
		{
			int i1 = x + random.nextInt(16);
			int j1 = z + random.nextInt(16);
			int k1 = random.nextInt(100);
			new WorldGenVines().generate(world, random, i1, k1, j1);
		}
		for (int l = 0; l < 5; ++l)
		{
			int i1 = x + random.nextInt(16);
			int j1 = z + random.nextInt(16);
			int k1 = random.nextInt(100);
			new WorldGenBlockgroup(InitBlocks.blockMud, 10).generate(world, random, i1, k1, j1);
		}
		for (int l = 0; l < 15; l++)
		{
			int X = x + random.nextInt(16);
			int Z = z + random.nextInt(16);
			int Y = random.nextInt(100);
			new WorldGenLeafPile(InitBlocks.blockLeafCover).generate(world, random, X, Y, Z);
		}
		for (int l = 0; l < 15; l++)
		{
			int X = x + random.nextInt(16);
			int Z = z + random.nextInt(16);
			int Y = random.nextInt(100);
			new WorldGenLeafPile(InitBlocks.blockMoss).generate(world, random, X, Y, Z);
		}
		super.decorate(world, random, x, z);
	}

	/**
	 * Provides the basic grass color based on the biome temperature and
	 * rainfall
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeGrassColor(int p_150558_1_, int p_150558_2_, int p_150558_3_)
	{
		return 9756748;
	}

	/**
	 * Provides the basic foliage color based on the biome temperature and
	 * rainfall
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public int getBiomeFoliageColor(int p_150571_1_, int p_150571_2_, int p_150571_3_)
	{
		return 9756748;
	}
}
