package steamcraft.common.dimension;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import steamcraft.common.Steamcraft;

public class ChunkProviderDeeps implements IChunkProvider
{
	private final Random terrainRNG;
	private final World theWorld;
	private final BiomeGenBase theBiome;
	private final double[] field_147434_q;

	public ChunkProviderDeeps(World par1World, long par2)
	{
		theBiome = Steamcraft.biomeDepths;
		this.theWorld = par1World;
		this.terrainRNG = new Random(par2);
		this.field_147434_q = new double[825];
	}

	public void getBlocksToGenerateInChunk(int p_147424_1_, int p_147424_2_, Block[] p_147424_3_)
	{
		byte b0 = 63;
		// this.func_147423_a(p_147424_1_ * 4, 0, p_147424_2_ * 4);

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
					double d1 = this.field_147434_q[k1 + k2];
					double d2 = this.field_147434_q[l1 + k2];
					double d3 = this.field_147434_q[i2 + k2];
					double d4 = this.field_147434_q[j2 + k2];
					double d5 = (this.field_147434_q[k1 + k2 + 1] - d1) * d0;
					double d6 = (this.field_147434_q[l1 + k2 + 1] - d2) * d0;
					double d7 = (this.field_147434_q[i2 + k2 + 1] - d3) * d0;
					double d8 = (this.field_147434_q[j2 + k2 + 1] - d4) * d0;

					for(int l2 = 0; l2 < 8; ++l2)
					{
						double d9 = 0.25D;
						double d10 = d1;
						double d11 = d2;
						double d12 = (d3 - d1) * d9;
						double d13 = (d4 - d2) * d9;

						for(int i3 = 0; i3 < 4; ++i3)
						{
							int j3 = i3 + k * 4 << 12 | 0 + j1 * 4 << 8 | k2 * 8 + l2;
							short short1 = 256;
							j3 -= short1;
							double d14 = 0.25D;
							double d16 = (d11 - d10) * d14;
							double d15 = d10 - d16;

							for(int k3 = 0; k3 < 4; ++k3)
							{
								if((d15 += d16) > 0.0D)
								{
									p_147424_3_[j3 += short1] = Blocks.stone;
								}
								else if(k2 * 8 + l2 < b0)
								{
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

	/**
	 * loads or generates the chunk at the chunk location specified
	 */
	@Override
	public Chunk loadChunk(int par1, int par2)
	{
		return this.provideChunk(par1, par2);
	}

	/**
	 * Will return back a chunk, if it doesn't exist and its not a MP client it will generates all the blocks for the specified chunk from the map seed and
	 * chunk seed
	 */
	@Override
	public Chunk provideChunk(int par1, int par2)
	{
		this.terrainRNG.setSeed(par1 * 341873128712L + par2 * 132897987541L);
		Block[] chunkBlockList = new Block[65536];
		this.getBlocksToGenerateInChunk(par1, par2, chunkBlockList);
		BiomeGenBase[] abiomegenbase = this.theWorld.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[]) null, par1 * 16, par2 * 16, 16, 16);
		Chunk chunk = new Chunk(this.theWorld, chunkBlockList, par1, par2);

		byte[] abyte = chunk.getBiomeArray();

		for(int k = 0; k < abyte.length; ++k)
		{
			abyte[k] = (byte) abiomegenbase[k].biomeID;
		}

		chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public void populate(IChunkProvider par1IChunkProvider, int par2, int par3)
	{
		BlockFalling.fallInstantly = true;

		int k = par2 * 16;
		int l = par3 * 16;
		BiomeGenBase biomegenbase = Steamcraft.biomeDepths;
		biomegenbase.decorate(this.theWorld, this.theWorld.rand, k, l);

		BlockFalling.fallInstantly = false;
	}

	/* Required but unused junk */
	@Override
	public boolean chunkExists(int par1, int par2)
	{
		return true;
	}

	@Override
	public boolean saveChunks(boolean par1, IProgressUpdate par2IProgressUpdate)
	{
		return true;
	}

	@Override
	public void saveExtraData()
	{

	}

	@Override
	public boolean unloadQueuedChunks()
	{
		return false;
	}

	@Override
	public boolean canSave()
	{
		return true;
	}

	@Override
	public String makeString()
	{
		return "RandomLevelSource";
	}

	@Override
	public List getPossibleCreatures(EnumCreatureType par1EnumCreatureType, int par2, int par3, int par4)
	{
		return this.theBiome.getSpawnableList(par1EnumCreatureType);
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
	public void recreateStructures(int par1, int par2)
	{
	}
}
