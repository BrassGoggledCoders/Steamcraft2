package steamcraft.common.dimension;

import java.util.List;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class ChunkProviderDeeps implements IChunkProvider
{
	/*
	 * private final Random terrainRNG; private final World theWorld; private final BiomeGenBase theBiome; private final double[] field_147434_q; private
	 * double[] noiseField; double[] noiseData1; double[] noiseData2; double[] noiseData3; double[] noiseData4; double[] noiseData5; public
	 * ChunkProviderDeeps(World par1World, long par2) { theBiome = Steamcraft.biomeDepths; this.theWorld = par1World; this.terrainRNG = new Random(par2);
	 * this.field_147434_q = new double[825]; } public Block[] getBlocksToGenerateInChunk(int p_147424_1_, int p_147424_2_, Block[] blockArray) { byte b0 = 4;
	 * byte b1 = 32; int k = b0 + 1; byte b2 = 17; int l = b0 + 1; this.noiseField = this.initializeNoiseField(this.noiseField, p_147424_1_ * b0, 0, p_147424_2_
	 * * b0, k, b2, l); for(int i1 = 0; i1 < b0; ++i1) { for(int j1 = 0; j1 < b0; ++j1) { for(int k1 = 0; k1 < 16; ++k1) { double d0 = 0.125D; double d1 =
	 * this.noiseField[((i1 + 0) * l + j1 + 0) * b2 + k1 + 0]; double d2 = this.noiseField[((i1 + 0) * l + j1 + 1) * b2 + k1 + 0]; double d3 =
	 * this.noiseField[((i1 + 1) * l + j1 + 0) * b2 + k1 + 0]; double d4 = this.noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1 + 0]; double d5 =
	 * (this.noiseField[((i1 + 0) * l + j1 + 0) * b2 + k1 + 1] - d1) * d0; double d6 = (this.noiseField[((i1 + 0) * l + j1 + 1) * b2 + k1 + 1] - d2) * d0;
	 * double d7 = (this.noiseField[((i1 + 1) * l + j1 + 0) * b2 + k1 + 1] - d3) * d0; double d8 = (this.noiseField[((i1 + 1) * l + j1 + 1) * b2 + k1 + 1] - d4)
	 * * d0; for(int l1 = 0; l1 < 8; ++l1) { double d9 = 0.25D; double d10 = d1; double d11 = d2; double d12 = (d3 - d1) * d9; double d13 = (d4 - d2) * d9;
	 * for(int i2 = 0; i2 < 4; ++i2) { int j2 = i2 + i1 * 4 << 11 | 0 + j1 * 4 << 7 | k1 * 8 + l1; short short1 = 128; double d14 = 0.25D; double d15 = d10;
	 * double d16 = (d11 - d10) * d14; for(int k2 = 0; k2 < 4; ++k2) { Block block = null; if(k1 * 8 + l1 < b1) { block = Blocks.lava; } if(d15 > 0.0D) { block
	 * = Blocks.netherrack; } blockArray[j2] = block; j2 += short1; d15 += d16; } d10 += d12; d11 += d13; } d1 += d5; d2 += d6; d3 += d7; d4 += d8; } } } }
	 * return blockArray; } /** generates a subset of the level's terrain data. Takes 7 arguments: the [empty] noise array, the position, and the size. private
	 * double[] initializeNoiseField(double[] p_73164_1_, int p_73164_2_, int p_73164_3_, int p_73164_4_, int p_73164_5_, int p_73164_6_, int p_73164_7_) {
	 * ChunkProviderEvent.InitNoiseField event = new ChunkProviderEvent.InitNoiseField(this, p_73164_1_, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_,
	 * p_73164_6_, p_73164_7_); MinecraftForge.EVENT_BUS.post(event); if(event.getResult() == Result.DENY) return event.noisefield; if(p_73164_1_ == null) {
	 * p_73164_1_ = new double[p_73164_5_ * p_73164_6_ * p_73164_7_]; } double d0 = 684.412D; double d1 = 2053.236D; this.noiseData4 =
	 * this.netherNoiseGen6.generateNoiseOctaves(this.noiseData4, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, 1.0D, 0.0D, 1.0D);
	 * this.noiseData5 = this.netherNoiseGen7.generateNoiseOctaves(this.noiseData5, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, 1, p_73164_7_, 100.0D, 0.0D,
	 * 100.0D); this.noiseData1 = this.netherNoiseGen3.generateNoiseOctaves(this.noiseData1, p_73164_2_, p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_,
	 * p_73164_7_, d0 / 80.0D, d1 / 60.0D, d0 / 80.0D); this.noiseData2 = this.netherNoiseGen1.generateNoiseOctaves(this.noiseData2, p_73164_2_, p_73164_3_,
	 * p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0, d1, d0); this.noiseData3 = this.netherNoiseGen2.generateNoiseOctaves(this.noiseData3, p_73164_2_,
	 * p_73164_3_, p_73164_4_, p_73164_5_, p_73164_6_, p_73164_7_, d0, d1, d0); int k1 = 0; int l1 = 0; double[] adouble1 = new double[p_73164_6_]; int i2;
	 * for(i2 = 0; i2 < p_73164_6_; ++i2) { adouble1[i2] = Math.cos(i2 * Math.PI * 6.0D / p_73164_6_) * 2.0D; double d2 = i2; if(i2 > p_73164_6_ / 2) { d2 =
	 * p_73164_6_ - 1 - i2; } if(d2 < 4.0D) { d2 = 4.0D - d2; adouble1[i2] -= d2 * d2 * d2 * 10.0D; } } for(i2 = 0; i2 < p_73164_5_; ++i2) { for(int k2 = 0; k2
	 * < p_73164_7_; ++k2) { double d3 = (this.noiseData4[l1] + 256.0D) / 512.0D; if(d3 > 1.0D) { d3 = 1.0D; } double d4 = 0.0D; double d5 = this.noiseData5[l1]
	 * / 8000.0D; if(d5 < 0.0D) { d5 = -d5; } d5 = d5 * 3.0D - 3.0D; if(d5 < 0.0D) { d5 /= 2.0D; if(d5 < -1.0D) { d5 = -1.0D; } d5 /= 1.4D; d5 /= 2.0D; d3 =
	 * 0.0D; } else { if(d5 > 1.0D) { d5 = 1.0D; } d5 /= 6.0D; } d3 += 0.5D; d5 = d5 * p_73164_6_ / 16.0D; ++l1; for(int j2 = 0; j2 < p_73164_6_; ++j2) { double
	 * d6 = 0.0D; double d7 = adouble1[j2]; double d8 = this.noiseData2[k1] / 512.0D; double d9 = this.noiseData3[k1] / 512.0D; double d10 =
	 * (this.noiseData1[k1] / 10.0D + 1.0D) / 2.0D; if(d10 < 0.0D) { d6 = d8; } else if(d10 > 1.0D) { d6 = d9; } else { d6 = d8 + (d9 - d8) * d10; } d6 -= d7;
	 * double d11; if(j2 > p_73164_6_ - 4) { d11 = (j2 - (p_73164_6_ - 4)) / 3.0F; d6 = d6 * (1.0D - d11) + -10.0D * d11; } if(j2 < d4) { d11 = (d4 - j2) /
	 * 4.0D; if(d11 < 0.0D) { d11 = 0.0D; } if(d11 > 1.0D) { d11 = 1.0D; } d6 = d6 * (1.0D - d11) + -10.0D * d11; } p_73164_1_[k1] = d6; ++k1; } } } return
	 * p_73164_1_; } /** loads or generates the chunk at the chunk location specified
	 * @Override public Chunk loadChunk(int par1, int par2) { return this.provideChunk(par1, par2); } /** Will return back a chunk, if it doesn't exist and its
	 * not a MP client it will generates all the blocks for the specified chunk from the map seed and chunk seed
	 * @Override public Chunk provideChunk(int par1, int par2) { this.terrainRNG.setSeed(par1 * 341873128712L + par2 * 132897987541L); Block[] chunkBlockList =
	 * new Block[65536]; chunkBlockList = this.getBlocksToGenerateInChunk(par1, par2, chunkBlockList); BiomeGenBase[] abiomegenbase =
	 * this.theWorld.getWorldChunkManager().loadBlockGeneratorData((BiomeGenBase[]) null, par1 * 16, par2 * 16, 16, 16); Chunk chunk = new Chunk(this.theWorld,
	 * chunkBlockList, par1, par2); byte[] abyte = chunk.getBiomeArray(); for(int k = 0; k < abyte.length; ++k) { abyte[k] = (byte) abiomegenbase[k].biomeID; }
	 * chunk.generateSkylightMap(); // chunk.resetRelightChecks(); return chunk; }
	 * @Override public void populate(IChunkProvider par1IChunkProvider, int par2, int par3) { BlockFalling.fallInstantly = true; int k = par2 * 16; int l =
	 * par3 * 16; BiomeGenBase biomegenbase = Steamcraft.biomeDepths; biomegenbase.decorate(this.theWorld, this.theWorld.rand, k, l); BlockFalling.fallInstantly
	 * = false; }
	 */

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
		return null;
		// return this.theBiome.getSpawnableList(par1EnumCreatureType);
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

	@Override
	public Chunk provideChunk(int p_73154_1_, int p_73154_2_)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chunk loadChunk(int p_73158_1_, int p_73158_2_)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void populate(IChunkProvider p_73153_1_, int p_73153_2_, int p_73153_3_)
	{
		// TODO Auto-generated method stub

	}
}
