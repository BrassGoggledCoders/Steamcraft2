/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package steamcraft.common.worldgen.biomes;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.DeferredBiomeDecorator;

import steamcraft.common.init.InitBlocks;
import steamcraft.common.worldgen.WorldGenBlockgroup;
import steamcraft.common.worldgen.WorldGenRandomUnderground;
import steamcraft.common.worldgen.structure.WorldGenUndergroundHouse;

public class DepthsBiomeDecorator extends DeferredBiomeDecorator
{
	/** The clay generator. */
	private WorldGenerator clayGen = new WorldGenClay(6);

	public DepthsBiomeDecorator(BiomeDecorator original)
	{
		super(original);
		this.sandGen = new WorldGenSand(Blocks.sand, 7);
		this.gravelAsSandGen = new WorldGenSand(Blocks.gravel, 6);
		this.dirtGen = new WorldGenMinable(Blocks.dirt, 16);
		this.gravelGen = new WorldGenMinable(Blocks.gravel, 16);
		this.coalGen = new WorldGenMinable(Blocks.coal_ore, 20);
		this.ironGen = new WorldGenMinable(Blocks.iron_ore, 16);
		this.goldGen = new WorldGenMinable(Blocks.gold_ore, 16);
		this.redstoneGen = new WorldGenMinable(Blocks.redstone_ore, 12);
		this.diamondGen = new WorldGenMinable(Blocks.diamond_ore, 12);
		this.lapisGen = new WorldGenMinable(Blocks.lapis_ore, 16);
		this.mushroomBrownGen = new WorldGenFlowers(Blocks.brown_mushroom);
		this.mushroomRedGen = new WorldGenFlowers(Blocks.red_mushroom);
		this.bigMushroomGen = new WorldGenBigMushroom();
		this.reedGen = new WorldGenReed();
		this.waterlilyGen = new WorldGenWaterlily();
		this.flowersPerChunk = 2;
		this.grassPerChunk = 1;
		this.sandPerChunk = 1;
		this.sandPerChunk2 = 3;
		this.clayPerChunk = 1;
		this.generateLakes = true;
	}

	@Override
	public void decorateChunk(World world, Random random, BiomeGenBase biome, int chunkX, int chunkZ)
	{

		this.generateOres(world, chunkX, chunkZ, random);
		int i;
		int j;
		int k;

		for (i = 0; i < this.sandPerChunk2; ++i)
		{
			j = chunkX + random.nextInt(16);
			k = chunkZ + random.nextInt(16);
			this.sandGen.generate(world, random, j, world.getTopSolidOrLiquidBlock(j, k), k);
		}
		for (i = 0; i < this.clayPerChunk; ++i)
		{
			j = chunkX + random.nextInt(16);
			k = chunkZ + random.nextInt(16);
			this.clayGen.generate(world, random, j, world.getTopSolidOrLiquidBlock(j, k), k);
		}
		for (i = 0; i < this.sandPerChunk; ++i)
		{
			j = chunkX + random.nextInt(16);
			k = chunkZ + random.nextInt(16);
			this.gravelAsSandGen.generate(world, random, j, world.getTopSolidOrLiquidBlock(j, k), k);
		}
		int l;
		int i1;
		i = this.treesPerChunk;

		if (random.nextInt(10) == 0)
		{
			++i;
		}
		for (j = 0; j < i; ++j)
		{
			k = chunkX + random.nextInt(16);
			l = chunkZ + random.nextInt(16);
			i1 = world.getHeightValue(k, l);
			WorldGenAbstractTree worldgenabstracttree = biome.func_150567_a(random);
			worldgenabstracttree.setScale(1.0D, 1.0D, 1.0D);

			if (worldgenabstracttree.generate(world, random, k - 3, i1, l - 3))
			{
				worldgenabstracttree.func_150524_b(world, random, k - 3, i1, l - 3);
			}
		}
		for (j = 0; j < this.bigMushroomsPerChunk; ++j)
		{
			k = chunkX + random.nextInt(16);
			l = chunkZ + random.nextInt(16);
			this.bigMushroomGen.generate(world, random, k, world.getHeightValue(k, l), l);
		}
		/*
		 * for(j = 0; j < this.flowersPerChunk; ++j) { k = chunkX; l = chunkZ;
		 * i1 = nextInt(random, world.getHeightValue(k, l) + 32); String s =
		 * biome.func_150572_a(random, k, i1, l); BlockFlower blockflower =
		 * BlockFlower.func_149857_e(s); if(blockflower.getMaterial() !=
		 * Material.air) { this.yellowFlowerGen.func_150550_a(blockflower,
		 * BlockFlower.func_149856_f(s)); this.yellowFlowerGen.generate(world,
		 * random, k, i1, l); } }
		 */
		for (j = 0; j < this.grassPerChunk; ++j)
		{
			k = chunkX + random.nextInt(16);
			l = chunkZ + random.nextInt(16);
			i1 = this.nextInt(random, world.getHeightValue(k, l) * 2);
			WorldGenerator worldgenerator = biome.getRandomWorldGenForGrass(random);
			worldgenerator.generate(world, random, k, i1, l);
		}
		for (j = 0; j < this.deadBushPerChunk; ++j)
		{
			k = chunkX + random.nextInt(16);
			l = chunkZ + random.nextInt(16);
			i1 = this.nextInt(random, world.getHeightValue(k, l) * 2);
			(new WorldGenDeadBush(Blocks.deadbush)).generate(world, random, k, i1, l);
		}
		for (j = 0; j < this.waterlilyPerChunk; ++j)
		{
			k = chunkX + random.nextInt(16);
			l = chunkZ + random.nextInt(16);

			for (i1 = this.nextInt(random, world.getHeightValue(k, l) * 2); (i1 > 0) && world.isAirBlock(k, i1 - 1, l); --i1)
			{
				;
			}

			this.waterlilyGen.generate(world, random, k, i1, l);
		}
		for (j = 0; j < this.mushroomsPerChunk; ++j)
		{
			if (random.nextInt(4) == 0)
			{
				k = chunkX + random.nextInt(16);
				l = chunkZ + random.nextInt(16);
				i1 = world.getHeightValue(k, l);
				this.mushroomBrownGen.generate(world, random, k, i1, l);
			}

			if (random.nextInt(8) == 0)
			{
				k = chunkX + random.nextInt(16);
				l = chunkZ + random.nextInt(16);
				i1 = this.nextInt(random, world.getHeightValue(k, l) * 2);
				this.mushroomRedGen.generate(world, random, k, i1, l);
			}
		}

		if (random.nextInt(4) == 0)
		{
			j = chunkX + random.nextInt(16);
			k = chunkZ + random.nextInt(16);
			l = this.nextInt(random, world.getHeightValue(j, k) * 2);
			this.mushroomBrownGen.generate(world, random, j, l, k);
		}

		if (random.nextInt(8) == 0)
		{
			j = chunkX + random.nextInt(16);
			k = chunkZ + random.nextInt(16);
			l = this.nextInt(random, world.getHeightValue(j, k) * 2);
			this.mushroomRedGen.generate(world, random, j, l, k);
		}
		for (j = 0; j < this.reedsPerChunk; ++j)
		{
			k = chunkX + random.nextInt(16);
			l = chunkZ + random.nextInt(16);
			i1 = this.nextInt(random, world.getHeightValue(k, l) * 2);
			this.reedGen.generate(world, random, k, i1, l);
		}

		for (j = 0; j < 10; ++j)
		{
			k = chunkX + random.nextInt(16);
			l = chunkZ + random.nextInt(16);
			i1 = this.nextInt(random, world.getHeightValue(k, l) * 2);
			this.reedGen.generate(world, random, k, i1, l);
		}
		if (random.nextInt(32) == 0)
		{
			j = chunkX + random.nextInt(16);
			k = chunkZ + random.nextInt(16);
			l = this.nextInt(random, world.getHeightValue(j, k) * 2);
			(new WorldGenPumpkin()).generate(world, random, j, l, k);
		}
		if (this.generateLakes)
		{
			for (j = 0; j < 50; ++j)
			{
				k = chunkX + random.nextInt(16);
				l = random.nextInt(100);
				i1 = chunkZ + random.nextInt(16);
				new WorldGenLiquids(Blocks.flowing_water).generate(world, random, k, l, i1);
			}

			for (j = 0; j < 20; ++j)
			{
				k = chunkX + random.nextInt(16);
				l = random.nextInt(100);
				i1 = chunkZ + random.nextInt(16);
				new WorldGenLiquids(Blocks.flowing_lava).generate(world, random, k, l, i1);
			}
		}
		/* Start Custom */
		int rarity = 6 + random.nextInt(9);

		for (int a = 0; a < rarity; ++a)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(28) + 4;
			int z = chunkZ + random.nextInt(16);

			if (world.getBlock(x, y, z).isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				new WorldGenBlockgroup(InitBlocks.blockCompressedStone, 10).generate(world, random, x, y, z);
			}
		}
		for (int a = 0; a < 10; ++a)
		{
			int x = chunkX + random.nextInt(16);
			int y = random.nextInt(60);
			int z = chunkZ + random.nextInt(16);

			if (world.getBlock(x, y, z).isReplaceableOreGen(world, x, y, z, Blocks.stone))
			{
				new WorldGenMinable(Blocks.emerald_ore, 3).generate(world, random, x, y, z);
			}
		}

		int X2 = chunkX + random.nextInt(16);
		int Z2 = chunkZ + random.nextInt(16);
		int Y2 = random.nextInt(50);

		for (int i2 = 0; i2 < 3; i2++)
		{
			new WorldGenUndergroundHouse().generate(world, random, X2, Y2, Z2);
		}
		for (int i3 = 0; i3 < 3; i3++)
		{
			int oreXCoord = chunkX + random.nextInt(16);
			int oreYCoord = 10 + random.nextInt(50);
			int oreZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(InitBlocks.blockBoulder, 0, 1, Blocks.stone).generate(world, random, oreXCoord, oreYCoord, oreZCoord);
		}
		for (int i3 = 0; i3 < 3; i3++)
		{
			int oreXCoord = chunkX + random.nextInt(16);
			int oreYCoord = 10 + random.nextInt(50);
			int oreZCoord = chunkZ + random.nextInt(16);

			new WorldGenMinable(InitBlocks.blockTintedRock, 0, 4, Blocks.stone).generate(world, random, oreXCoord, oreYCoord, oreZCoord);
		}
		for (int i3 = 0; i3 < 3; i3++)
		{
			int oreXCoord = chunkX + random.nextInt(16);
			int oreYCoord = 10 + random.nextInt(50);
			int oreZCoord = chunkZ + random.nextInt(16);

			if (InitBlocks.blockDynamite != null)
				new WorldGenMinable(InitBlocks.blockDynamite, 0, 4, Blocks.stone).generate(world, random, oreXCoord, oreYCoord, oreZCoord);
		}

		{
			int xCoord = chunkX + random.nextInt(16);
			int yCoord = 10 + random.nextInt(40);
			int zCoord = chunkZ + random.nextInt(16);
			new WorldGenRandomUnderground(Blocks.web).generate(world, random, xCoord, yCoord, zCoord);
		}

		{
			int xCoord = chunkX + random.nextInt(16);
			int yCoord = 10 + random.nextInt(40);
			int zCoord = chunkZ + random.nextInt(16);
			new WorldGenRandomUnderground(Blocks.skull).generate(world, random, xCoord, yCoord, zCoord);
		}

		{
			int xCoord = chunkX + random.nextInt(16);
			int yCoord = 10 + random.nextInt(40);
			int zCoord = chunkZ + random.nextInt(16);
			new WorldGenRandomUnderground(Blocks.vine).generate(world, random, xCoord, yCoord, zCoord);
		}
		{
			int xCoord = chunkX + random.nextInt(16);
			int yCoord = 10 + random.nextInt(40);
			int zCoord = chunkZ + random.nextInt(16);
			new WorldGenRandomUnderground(InitBlocks.blockMushroom, 0).generate(world, random, xCoord, yCoord, zCoord);
		}
		{
			int xCoord = chunkX + random.nextInt(16);
			int yCoord = 10 + random.nextInt(40);
			int zCoord = chunkZ + random.nextInt(16);
			new WorldGenRandomUnderground(InitBlocks.blockMushroom, 1).generate(world, random, xCoord, yCoord, zCoord);
		}
		{
			int xCoord = chunkX + random.nextInt(16);
			int yCoord = 10 + random.nextInt(40);
			int zCoord = chunkZ + random.nextInt(16);
			new WorldGenRandomUnderground(InitBlocks.blockMushroom, 2).generate(world, random, xCoord, yCoord, zCoord);
		}
	}

	/**
	 * Standard ore generation helper. Generates most ores.
	 */
	private void genStandardOre1(World world, int chunkX, int chunkZ, Random random, int p_76795_1_, WorldGenerator p_76795_2_, int maxY)
	{
		for (int l = 0; l < p_76795_1_; ++l)
		{
			int i1 = (chunkX + random.nextInt(16)) - 8;
			int j1 = random.nextInt(maxY);
			int k1 = (chunkZ + random.nextInt(16)) - 8;
			p_76795_2_.generate(world, random, i1, j1, k1);
		}
	}

	/**
	 * Standard ore generation helper. Generates Lapis Lazuli.
	 */
	private void genStandardOre2(World world, int chunkX, int chunkZ, Random random, int p_76793_1_, WorldGenerator generator, int maxY)
	{
		for (int l = 0; l < p_76793_1_; ++l)
		{
			int i1 = (chunkX + random.nextInt(16)) - 8;
			int j1 = random.nextInt(maxY);
			int k1 = (chunkZ + random.nextInt(16)) - 8;
			generator.generate(world, random, i1, j1, k1);
		}
	}

	private void generateOres(World world, int chunkX, int chunkZ, Random random)
	{
		this.genStandardOre1(world, chunkX, chunkZ, random, 20, this.dirtGen, 256);
		this.genStandardOre1(world, chunkX, chunkZ, random, 10, this.gravelGen, 256);
		this.genStandardOre1(world, chunkX, chunkZ, random, 20, this.coalGen, 128);
		this.genStandardOre1(world, chunkX, chunkZ, random, 20, this.ironGen, 64);
		this.genStandardOre1(world, chunkX, chunkZ, random, 2, this.goldGen, 32);
		this.genStandardOre1(world, chunkX, chunkZ, random, 8, this.redstoneGen, 16);
		this.genStandardOre1(world, chunkX, chunkZ, random, 1, this.diamondGen, 16);
		this.genStandardOre2(world, chunkX, chunkZ, random, 1, this.lapisGen, 16);
	}

	private int nextInt(Random random, int i)
	{
		if (i <= 1)
			return 0;
		return random.nextInt(i);
	}
}
