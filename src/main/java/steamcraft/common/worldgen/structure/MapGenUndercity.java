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
package steamcraft.common.worldgen.structure;

import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.structure.MapGenStructure;
import net.minecraft.world.gen.structure.StructureStart;

import steamcraft.common.lib.LoggerSteamcraft;

public class MapGenUndercity extends MapGenStructure
{
	private double field_82673_e = 0.004D;

	public MapGenUndercity()
	{
	}

	@Override
	public String func_143025_a()
	{
		return "Undercity";
	}

	public MapGenUndercity(Map p_i2034_1_)
	{
		for(Object obj : p_i2034_1_.entrySet())
		{
			Entry entry = (Entry) obj;

			if((entry.getKey()).equals("chance"))
			{
				this.field_82673_e = MathHelper.parseDoubleWithDefault((String) entry.getValue(), this.field_82673_e);
			}
		}
	}

	@Override
	protected boolean canSpawnStructureAtCoords(int p_75047_1_, int p_75047_2_)
	{
		return (this.rand.nextDouble() < this.field_82673_e) && (this.rand.nextInt(80) < Math.max(Math.abs(p_75047_1_), Math.abs(p_75047_2_)));
	}

	@Override
	protected StructureStart getStructureStart(int p_75049_1_, int p_75049_2_)
	{
		return new StructureUndercityStart(this.worldObj, this.rand, p_75049_1_, p_75049_2_);
	}

	@Override
	public void func_151539_a(IChunkProvider p_151539_1_, World p_151539_2_, int chunkX, int chunkZ, Block[] p_151539_5_)
	{
		int k = this.range;
		this.worldObj = p_151539_2_;
		this.rand.setSeed(p_151539_2_.getSeed());
		long l = this.rand.nextLong();
		long i1 = this.rand.nextLong();

		for(int j1 = chunkX - k; j1 <= chunkX + k; ++j1)
		{
			for(int k1 = chunkZ - k; k1 <= chunkZ + k; ++k1)
			{
				long l1 = j1 * l;
				long i2 = k1 * i1;
				this.rand.setSeed(l1 ^ i2 ^ p_151539_2_.getSeed());
				LoggerSteamcraft.info("Gen at: " + j1 + " " + k1);
				this.func_151538_a(p_151539_2_, j1, k1, chunkX, chunkZ, p_151539_5_);
			}
		}
	}
}