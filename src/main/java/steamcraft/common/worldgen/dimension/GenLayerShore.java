package steamcraft.common.worldgen.dimension;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerShore extends GenLayer
{
	public GenLayerShore(long seed, GenLayer parent)
	{
		super(seed);

		this.parent = parent;
	}

	@Override
	public int[] getInts(int x, int z, int width, int length)
	{
		int[] inputBiomeIds = this.parent.getInts(x - 1, z - 1, width + 2, length + 2);
		int[] outputBiomeIds = IntCache.getIntCache(width * length);

		for(int i1 = 0; i1 < length; ++i1)
		{
			for(int j1 = 0; j1 < width; ++j1)
			{
				this.initChunkSeed(j1 + x, i1 + z);
				int landBiomeId = inputBiomeIds[j1 + 1 + ((i1 + 1) * (width + 2))];
				BiomeGenBase biomegenbase = BiomeGenBase.getBiome(landBiomeId);
				int l1;
				int i2;
				int j2;
				int k2;
			}
		}
		/*
		 * if(!isBiomeOceanic(landBiomeId) && landBiomeId != InitBiomes.biomeDepthsO.biomeID) { l1 = inputBiomeIds[j1 + 1 + (i1 + 1 - 1) * (width + 2)]; i2 =
		 * inputBiomeIds[j1 + 1 + 1 + (i1 + 1) * (width + 2)]; j2 = inputBiomeIds[j1 + 1 - 1 + (i1 + 1) * (width + 2)]; k2 = inputBiomeIds[j1 + 1 + (i1 + 1 + 1)
		 * * (width + 2)]; if(!isBiomeOceanic(l1) && !isBiomeOceanic(i2) && !isBiomeOceanic(j2) && !isBiomeOceanic(k2)) { outputBiomeIds[j1 + i1 * width] =
		 * landBiomeId; } else { outputBiomeIds[j1 + i1 * width] = InitBiomes.biomeDepthsB.biomeID; } } else { outputBiomeIds[j1 + i1 * width] = landBiomeId; }
		 * } }
		 */

		return outputBiomeIds;
	}

	private void func_151632_a(int[] inputBiomeIds, int[] outputBiomeIds, int x, int z, int width, int landBiomeId, int beachBiomeId)
	{
		if(isBiomeOceanic(landBiomeId))
		{
			outputBiomeIds[x + (z * width)] = landBiomeId;
		}
		else
		{
			int j1 = inputBiomeIds[x + 1 + (((z + 1) - 1) * (width + 2))];
			int k1 = inputBiomeIds[x + 1 + 1 + ((z + 1) * (width + 2))];
			int l1 = inputBiomeIds[((x + 1) - 1) + ((z + 1) * (width + 2))];
			int i2 = inputBiomeIds[x + 1 + ((z + 1 + 1) * (width + 2))];

			if(!isBiomeOceanic(j1) && !isBiomeOceanic(k1) && !isBiomeOceanic(l1) && !isBiomeOceanic(i2))
			{
				outputBiomeIds[x + (z * width)] = landBiomeId;
			}
			else
			{
				outputBiomeIds[x + (z * width)] = beachBiomeId;
			}
		}
	}

	protected static boolean isBiomeOceanic(int biomeId)
	{
		return /* biomeId == InitBiomes.biomeDepthsO.biomeID || */false;
	}
}