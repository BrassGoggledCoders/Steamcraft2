package steamcraft.common.dimension;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderDeeps implements IChunkProvider
{
	private final Random dimRNG;
	private final World worldObj;
	private double[] noiseField;

	double[] noiseData1;
	double[] noiseData2;
	double[] noiseData3;
	double[] noiseData4;
	double[] noiseData5;

	public ChunkProviderDeeps(World p_i2005_1_, long p_i2005_2_)
	{
		this.worldObj = p_i2005_1_;
		this.dimRNG = new Random(p_i2005_2_);
	}

	@Deprecated
	// You should provide meatadata and biome data in the below method
	public void func_147418_b(int p_147418_1_, int p_147418_2_, Block[] p_147418_3_)
	{
		replaceBiomeBlocks(p_147418_1_, p_147418_2_, p_147418_3_, new byte[p_147418_3_.length], null);
	}

	public void replaceBiomeBlocks(int p_147418_1_, int p_147418_2_, Block[] p_147418_3_, byte[] meta, BiomeGenBase[] biomes)
	{

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
	public Chunk provideChunk(int p_73154_1_, int p_73154_2_)
	{
		this.dimRNG.setSeed(p_73154_1_ * 341873128712L + p_73154_2_ * 132897987541L);
		Block[] ablock = new Block[32768];
		byte[] meta = new byte[ablock.length];
		BiomeGenBase[] abiomegenbase = this.worldObj.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[]) null, p_73154_1_ * 16, p_73154_2_ * 16, 16,
				16);
		this.replaceBiomeBlocks(p_73154_1_, p_73154_2_, ablock, meta, abiomegenbase);
		Chunk chunk = new Chunk(this.worldObj, ablock, meta, p_73154_1_, p_73154_2_);
		byte[] abyte = chunk.getBiomeArray();

		for(int k = 0; k < abyte.length; ++k)
		{
			abyte[k] = (byte) abiomegenbase[k].biomeID;
		}

		chunk.resetRelightChecks();
		return chunk;
	}

	/**
	 * generates a subset of the level's terrain data. Takes 7 arguments: the [empty] noise array, the position, and the size.
	 */
	private double[] initializeNoiseField(double[] p_73164_1_, int p_73164_2_, int p_73164_3_, int p_73164_4_, int p_73164_5_, int p_73164_6_, int p_73164_7_)
	{
		return p_73164_1_;
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
	public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_)
	{

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
		return "DeepsRandomLevelSource";
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
