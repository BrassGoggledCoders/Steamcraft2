
package steamcraft.common.worldgen.dimension;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomesDeeps extends GenLayer
{
	public GenLayerBiomesDeeps(long seed, GenLayer genlayer)
	{
		super(seed);
		this.parent = genlayer;
	}

	public GenLayerBiomesDeeps(long seed)
	{
		super(seed);
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth)
	{
		int[] dest = IntCache.getIntCache(width * depth);

		for (int dz = 0; dz < depth; dz++)
		{
			for (int dx = 0; dx < width; dx++)
			{
				this.initChunkSeed(dx + x, dz + z);
				dest[(dx + (dz * width))] = WorldChunkManagerDeeps.myBiomesToSpawnIn
						.get(this.nextInt(WorldChunkManagerDeeps.myBiomesToSpawnIn.size())).biomeID;
			}
		}
		return dest;
	}
}
