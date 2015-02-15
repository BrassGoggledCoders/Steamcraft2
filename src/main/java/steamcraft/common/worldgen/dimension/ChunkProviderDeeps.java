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
package steamcraft.common.worldgen.dimension;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.SpawnerAnimals;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.MapGenCaves;
import net.minecraft.world.gen.MapGenRavine;
import net.minecraft.world.gen.NoiseGenerator;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenDungeons;
import net.minecraft.world.gen.feature.WorldGenLakes;
import net.minecraft.world.gen.structure.MapGenMineshaft;

import net.minecraftforge.event.terraingen.TerrainGen;

import steamcraft.common.init.InitBiomes;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.worldgen.biomes.BiomeDepthsBase;
import steamcraft.common.worldgen.structure.MapGenUndercity;
import steamcraft.common.worldgen.structure.MapGenWitchHut;

public class ChunkProviderDeeps implements IChunkProvider
{
	/** RNG. */
	private final Random rand;
	private NoiseGeneratorOctaves noiseGen1;
	private NoiseGeneratorOctaves noiseGen2;
	private NoiseGeneratorOctaves noiseGen3;
	private NoiseGeneratorPerlin noiseGen4;
	/** A NoiseGeneratorOctaves used in generating terrain */
	private NoiseGeneratorOctaves noiseGen5;
	/** A NoiseGeneratorOctaves used in generating terrain */
	private NoiseGeneratorOctaves noiseGen6;
	private NoiseGeneratorOctaves mobSpawnerNoise;
	/** Reference to the World object. */
	private final World worldObj;
	private final WorldType worldType;
	private final double[] adouble;
	private final float[] parabolicField;
	private double[] stoneNoise = new double[256];
	private MapGenBase caveGenerator = new MapGenCaves();
	private MapGenBase ravineGenerator = new MapGenRavine();
	private MapGenMineshaft mineShaftGenerator = new MapGenMineshaft();
	private MapGenUndercity undercityGenerator = new MapGenUndercity();
	/** The biomes that are used to generate the chunk */
	private BiomeGenBase[] biomesForGeneration;
	private double[] field_147427_d;
	private double[] field_147428_e;
	private double[] field_147425_f;
	private double[] field_147426_g;

	public ChunkProviderDeeps(World p_i2006_1_, long p_i2006_2_)
	{
		this.worldObj = p_i2006_1_;
		this.worldType = p_i2006_1_.getWorldInfo().getTerrainType();
		this.rand = new Random(p_i2006_2_);
		this.noiseGen1 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen2 = new NoiseGeneratorOctaves(this.rand, 16);
		this.noiseGen3 = new NoiseGeneratorOctaves(this.rand, 8);
		this.noiseGen4 = new NoiseGeneratorPerlin(this.rand, 4);
		this.noiseGen5 = new NoiseGeneratorOctaves(this.rand, 10);
		this.noiseGen6 = new NoiseGeneratorOctaves(this.rand, 16);
		this.mobSpawnerNoise = new NoiseGeneratorOctaves(this.rand, 8);
		this.adouble = new double[825];
		this.parabolicField = new float[25];

		for(int j = -2; j <= 2; ++j)
		{
			for(int k = -2; k <= 2; ++k)
			{
				float f = 10.0F / MathHelper.sqrt_float((j * j) + (k * k) + 0.2F);
				this.parabolicField[j + 2 + ((k + 2) * 5)] = f;
			}
		}

		NoiseGenerator[] noiseGens = { this.noiseGen1, this.noiseGen2, this.noiseGen3, this.noiseGen4, this.noiseGen5, this.noiseGen6, this.mobSpawnerNoise };
		noiseGens = TerrainGen.getModdedNoiseGenerators(p_i2006_1_, this.rand, noiseGens);
		this.noiseGen1 = (NoiseGeneratorOctaves) noiseGens[0];
		this.noiseGen2 = (NoiseGeneratorOctaves) noiseGens[1];
		this.noiseGen3 = (NoiseGeneratorOctaves) noiseGens[2];
		this.noiseGen4 = (NoiseGeneratorPerlin) noiseGens[3];
		this.noiseGen5 = (NoiseGeneratorOctaves) noiseGens[4];
		this.noiseGen6 = (NoiseGeneratorOctaves) noiseGens[5];
		this.mobSpawnerNoise = (NoiseGeneratorOctaves) noiseGens[6];
	}

	private void doBaseGeneration(int chunkCoordX, int chunkCoordZ, Block[] p_147424_3_)
	{
		byte b0 = 63;
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, chunkCoordX * 16,
				chunkCoordZ * 16, 16, 16);
		this.func_147423_a(chunkCoordX * 4, 0, chunkCoordZ * 4);

		for(int k = 0; k < 4; ++k)
		{
			int l = k * 5;
			int i1 = (k + 1) * 5;

			for(int j1 = 0; j1 < 4; ++j1)
			{
				int k1 = (l + j1) * 33;
				int l1 = (l + j1 + 1) * 33;
				int i2 = (i1 + j1) * 33;
				int j2 = (i1 + j1 + 1) * 33;

				for(int k2 = 0; k2 < 32; ++k2)
				{
					double d0 = 0.125D;
					double d1 = this.adouble[k1 + k2];
					double d2 = this.adouble[l1 + k2];
					double d3 = this.adouble[i2 + k2];
					double d4 = this.adouble[j2 + k2];
					double d5 = (this.adouble[k1 + k2 + 1] - d1) * d0;
					double d6 = (this.adouble[l1 + k2 + 1] - d2) * d0;
					double d7 = (this.adouble[i2 + k2 + 1] - d3) * d0;
					double d8 = (this.adouble[j2 + k2 + 1] - d4) * d0;

					for(int l2 = 0; l2 < 8; ++l2)
					{
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;

						for(int i3 = 0; i3 < 4; ++i3)
						{
							int j3 = ((i3 + (k * 4)) << 12) | ((0 + (j1 * 4)) << 8) | ((k2 * 8) + l2);
							short short1 = 256;
							j3 -= short1;
							double d14 = 0.25D;
							double d16 = (d11 - d10) * d14;
							double d15 = d10 - d16;

							for(int k3 = 0; k3 < 4; ++k3)
							{
								if((d15 += d16) > 0.0D)
								{
									// if(this.worldObj.getBiomeGenForCoords(chunkCoordX, chunkCoordZ) == InitBiomes.biomeDepthsO)
									// p_147424_3_[j3 += short1] = Blocks.water;
									// else
									p_147424_3_[j3 += short1] = Blocks.stone;
								}
								else if(((k2 * 8) + l2) < b0)
								{
									if(this.worldObj.getBiomeGenForCoords(chunkCoordX, chunkCoordZ) == InitBiomes.biomeDepthsSCH)
										p_147424_3_[j3 += short1] = Blocks.lava;
									else
										p_147424_3_[j3 += short1] = Blocks.water;
								}
								else
								{
									p_147424_3_[j3 += short1] = null;
								}
							}

							d10 += d12;
							d11 += d13;
						}

						d1 += d5;
						d2 += d6;
						d3 += d7;
						d4 += d8;
					}
				}
			}
		}
	}

	private void replaceBlocksForBiome(int p_147422_1_, int p_147422_2_, Block[] p_147422_3_, byte[] p_147422_4_, BiomeGenBase[] p_147422_5_)
	{

		double d0 = 0.03125D;
		this.stoneNoise = this.noiseGen4.func_151599_a(this.stoneNoise, p_147422_1_ * 16, p_147422_2_ * 16, 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

		for(int k = 0; k < 16; ++k)
		{
			for(int l = 0; l < 16; ++l)
			{
				BiomeGenBase biomegenbase = p_147422_5_[l + (k * 16)];
				biomegenbase.genTerrainBlocks(this.worldObj, this.rand, p_147422_3_, p_147422_4_, (p_147422_1_ * 16) + k, (p_147422_2_ * 16) + l,
						this.stoneNoise[l
								+ (k * 16)]);
			}
		}
	}

	/**
	 * loads or generates the chunk at the chunk location specified
	 */
	@Override
	public Chunk loadChunk(int p_73158_1_, int p_73158_2_)
	{
		return this.provideChunk(p_73158_1_, p_73158_2_);
	}

	/**
	 * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the specified chunk from the map seed and
	 * chunk seed
	 */
	@Override
	public Chunk provideChunk(int chunkCoordX, int chunkCoordZ)
	{
		this.rand.setSeed((chunkCoordX * 341873128712L) + (chunkCoordZ * 132897987541L));
		Block[] ablock = new Block[65536];
		byte[] abyte = new byte[65536];
		this.doBaseGeneration(chunkCoordX, chunkCoordZ, ablock);
		this.biomesForGeneration = this.worldObj.getWorldChunkManager().getBiomesForGeneration(this.biomesForGeneration, chunkCoordX * 16,
				chunkCoordZ * 16, 16, 16);
		this.replaceBlocksForBiome(chunkCoordX, chunkCoordZ, ablock, abyte, this.biomesForGeneration);
		this.caveGenerator.func_151539_a(this, this.worldObj, chunkCoordX, chunkCoordZ, ablock);
		this.ravineGenerator.func_151539_a(this, this.worldObj, chunkCoordX, chunkCoordZ, ablock);
		this.mineShaftGenerator.func_151539_a(this, this.worldObj, chunkCoordX, chunkCoordZ, ablock);

		this.undercityGenerator.func_151539_a(this, this.worldObj, chunkCoordX, chunkCoordZ, ablock);

		if(this.worldObj.getBiomeGenForCoords(chunkCoordZ * 16, chunkCoordX * 16) == InitBiomes.biomeDepthsSW)
			new MapGenWitchHut().func_151539_a(this, this.worldObj, chunkCoordX, chunkCoordZ, ablock);

		Chunk chunk = new Chunk(this.worldObj, ablock, abyte, chunkCoordX, chunkCoordZ);
		byte[] abyte1 = chunk.getBiomeArray();

		for(int k = 0; k < abyte1.length; ++k)
		{
			abyte1[k] = (byte) this.biomesForGeneration[k].biomeID;
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	private void func_147423_a(int p_147423_1_, int p_147423_2_, int p_147423_3_)
	{
		double d0 = 684.412D;
		double d1 = 684.412D;
		double d2 = 512.0D;
		double d3 = 512.0D;
		this.field_147426_g = this.noiseGen6.generateNoiseOctaves(this.field_147426_g, p_147423_1_, p_147423_3_, 5, 5, 200.0D, 200.0D, 0.5D);
		this.field_147427_d = this.noiseGen3.generateNoiseOctaves(this.field_147427_d, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5,
				8.555150000000001D, 4.277575000000001D, 8.555150000000001D);
		this.field_147428_e = this.noiseGen1.generateNoiseOctaves(this.field_147428_e, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 684.412D,
				684.412D, 684.412D);
		this.field_147425_f = this.noiseGen2.generateNoiseOctaves(this.field_147425_f, p_147423_1_, p_147423_2_, p_147423_3_, 5, 33, 5, 684.412D,
				684.412D, 684.412D);
		boolean flag1 = false;
		boolean flag = false;
		int l = 0;
		int i1 = 0;
		double d4 = 8.5D;

		for(int j1 = 0; j1 < 5; ++j1)
		{
			for(int k1 = 0; k1 < 5; ++k1)
			{
				float f = 0.0F;
				float f1 = 0.0F;
				float f2 = 0.0F;
				byte b0 = 2;
				BiomeGenBase biomegenbase = this.biomesForGeneration[j1 + 2 + ((k1 + 2) * 10)];

				for(int l1 = -b0; l1 <= b0; ++l1)
				{
					for(int i2 = -b0; i2 <= b0; ++i2)
					{
						BiomeGenBase biomegenbase1 = this.biomesForGeneration[j1 + l1 + 2 + ((k1 + i2 + 2) * 10)];
						float f3 = biomegenbase1.rootHeight;
						float f4 = biomegenbase1.heightVariation;

						if((this.worldType == WorldType.AMPLIFIED) && (f3 > 0.0F))
						{
							f3 = 1.0F + (f3 * 2.0F);
							f4 = 1.0F + (f4 * 4.0F);
						}

						float f5 = this.parabolicField[l1 + 2 + ((i2 + 2) * 5)] / (f3 + 2.0F);

						if(biomegenbase1.rootHeight > biomegenbase.rootHeight)
						{
							f5 /= 2.0F;
						}

						f += f4 * f5;
						f1 += f3 * f5;
						f2 += f5;
					}
				}

				f /= f2;
				f1 /= f2;
				f = (f * 0.9F) + 0.1F;
				f1 = ((f1 * 4.0F) - 1.0F) / 8.0F;
				double d12 = this.field_147426_g[i1] / 8000.0D;

				if(d12 < 0.0D)
				{
					d12 = -d12 * 0.3D;
				}

				d12 = (d12 * 3.0D) - 2.0D;

				if(d12 < 0.0D)
				{
					d12 /= 2.0D;

					if(d12 < -1.0D)
					{
						d12 = -1.0D;
					}

					d12 /= 1.4D;
					d12 /= 2.0D;
				}
				else
				{
					if(d12 > 1.0D)
					{
						d12 = 1.0D;
					}

					d12 /= 8.0D;
				}

				++i1;
				double d13 = f1;
				double d14 = f;
				d13 += d12 * 0.2D;
				d13 = (d13 * 8.5D) / 8.0D;
				double d5 = 8.5D + (d13 * 4.0D);

				for(int j2 = 0; j2 < 33; ++j2)
				{
					double d6 = ((j2 - d5) * 12.0D * 128.0D) / 256.0D / d14;

					if(d6 < 0.0D)
					{
						d6 *= 4.0D;
					}

					double d7 = this.field_147428_e[l] / 512.0D;
					double d8 = this.field_147425_f[l] / 512.0D;
					double d9 = ((this.field_147427_d[l] / 10.0D) + 1.0D) / 2.0D;
					double d10 = MathHelper.denormalizeClamp(d7, d8, d9) - d6;

					if(j2 > 29)
					{
						double d11 = (j2 - 29) / 3.0F;
						d10 = (d10 * (1.0D - d11)) + (-10.0D * d11);
					}

					this.adouble[l] = d10;
					++l;
				}
			}
		}
	}

	/**
	 * Checks to see if a chunk exists at x, y
	 */
	@Override
	public boolean chunkExists(int p_73149_1_, int p_73149_2_)
	{
		return true;
	}

	/**
	 * Populates chunk with ores etc etc
	 */
	@Override
	public void populate(IChunkProvider provider, int chunkX, int chunkZ)
	{
		BlockFalling.fallInstantly = true;
		int blockX = (chunkX * 16) + 8;
		int blockZ = (chunkZ * 16) + 8;
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(blockX + 16, blockZ + 16);
		BiomeDepthsBase biomegendepths = (BiomeDepthsBase) biomegenbase;
		this.rand.setSeed(this.worldObj.getSeed());
		long i1 = ((this.rand.nextLong() / 2L) * 2L) + 1L;
		long j1 = ((this.rand.nextLong() / 2L) * 2L) + 1L;
		this.rand.setSeed(((chunkX * i1) + (chunkZ * j1)) ^ this.worldObj.getSeed());
		boolean flag = false;

		this.mineShaftGenerator.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);

		this.undercityGenerator.generateStructuresInChunk(this.worldObj, this.rand, chunkX, chunkZ);

		int k1;
		int l1;
		int i2;

		if((biomegenbase == InitBiomes.biomeDepthsSCH))
		{
			k1 = blockX + this.rand.nextInt(16);
			l1 = this.rand.nextInt(256);
			i2 = blockZ + this.rand.nextInt(16);
			(new WorldGenLakes(Blocks.lava)).generate(this.worldObj, this.rand, k1, l1, i2);
			k1 = blockX + this.rand.nextInt(16);
			l1 = this.rand.nextInt(256);
			i2 = blockZ + this.rand.nextInt(16);
			(new WorldGenLakes(Blocks.lava)).generate(this.worldObj, this.rand, k1, l1, i2);
		}
		else if(biomegenbase == InitBiomes.biomeDepthsSC)
		{
			k1 = blockX + this.rand.nextInt(16);
			l1 = this.rand.nextInt(256);
			i2 = blockZ + this.rand.nextInt(16);
			(new WorldGenLakes(InitBlocks.blockBoilingWater)).generate(this.worldObj, this.rand, k1, l1, i2);

			k1 = blockX + this.rand.nextInt(16);
			l1 = this.rand.nextInt(256);
			i2 = blockZ + this.rand.nextInt(16);
			(new WorldGenLakes(InitBlocks.blockBoilingMud)).generate(this.worldObj, this.rand, k1, l1, i2);
		}
		else
		{
			k1 = blockX + this.rand.nextInt(16);
			l1 = this.rand.nextInt(256);
			i2 = blockZ + this.rand.nextInt(16);
			(new WorldGenLakes(Blocks.water)).generate(this.worldObj, this.rand, k1, l1, i2);
		}

		k1 = blockX + this.rand.nextInt(16);
		l1 = this.rand.nextInt(256);
		i2 = blockZ + this.rand.nextInt(16);

		if((l1 < 63) || (this.rand.nextInt(5) == 0))
		{
			(new WorldGenLakes(Blocks.lava)).generate(this.worldObj, this.rand, k1, l1, i2);
		}
		for(k1 = 0; k1 < 8; ++k1)
		{
			l1 = blockX + this.rand.nextInt(16);
			i2 = this.rand.nextInt(256);
			int j2 = blockZ + this.rand.nextInt(16);
			(new WorldGenDungeons()).generate(this.worldObj, this.rand, l1, i2, j2);
		}

		biomegendepths.decorate(this.worldObj, this.rand, blockX, blockZ);
		SpawnerAnimals.performWorldGenSpawning(this.worldObj, biomegenbase, blockX + 8, blockZ + 8, 16, 16, this.rand);
		blockX += 8;
		blockZ += 8;

		BlockFalling.fallInstantly = false;
	}

	/**
	 * Two modes of operation: if passed true, save all Chunks in one go. If passed false, save up to two chunks. Return true if all chunks have been saved.
	 */
	@Override
	public boolean saveChunks(boolean p_73151_1_, IProgressUpdate p_73151_2_)
	{
		return true;
	}

	/**
	 * Save extra data not associated with any Chunk. Not saved during autosave, only during world unload. Currently unimplemented.
	 */
	@Override
	public void saveExtraData()
	{
	}

	/**
	 * Unloads chunks that are marked to be unloaded. This is not guaranteed to unload every such chunk.
	 */
	@Override
	public boolean unloadQueuedChunks()
	{
		return false;
	}

	/**
	 * Returns if the IChunkProvider supports saving.
	 */
	@Override
	public boolean canSave()
	{
		return true;
	}

	/**
	 * Converts the instance data to a readable string.
	 */
	@Override
	public String makeString()
	{
		return "RandomLevelSource";
	}

	/**
	 * Returns a list of creatures of the specified type that can spawn at the given location.
	 */
	@Override
	public List getPossibleCreatures(EnumCreatureType p_73155_1_, int p_73155_2_, int p_73155_3_, int p_73155_4_)
	{
		BiomeGenBase biomegenbase = this.worldObj.getBiomeGenForCoords(p_73155_2_, p_73155_4_);
		return biomegenbase.getSpawnableList(p_73155_1_);
	}

	@Override
	public ChunkPosition func_147416_a(World p_147416_1_, String p_147416_2_, int p_147416_3_, int p_147416_4_, int p_147416_5_)
	{
		return null;
	}

	@Override
	public int getLoadedChunkCount()
	{
		return 0;
	}

	@Override
	public void recreateStructures(int p_82695_1_, int p_82695_2_)
	{
	}
}
