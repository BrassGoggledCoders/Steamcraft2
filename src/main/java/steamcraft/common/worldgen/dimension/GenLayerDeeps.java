package steamcraft.common.worldgen.dimension;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerIsland;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayerDeeps extends GenLayer
{
	public GenLayerDeeps(long seed)
	{
		super(seed);
	}

	public static GenLayer[] makeTheWorld(long seed)
	{
		GenLayer biomes = new GenLayerIsland(1L);
		biomes = new GenLayerFuzzyZoom(2000L, biomes);

		biomes = new GenLayerBiomesDeeps(100L, biomes);
		biomes = GenLayerZoom.magnify(2000L, biomes, 8);

		GenLayerSmooth smooth = new GenLayerSmooth(3000L, biomes);
		GenLayerSmooth smooth1 = new GenLayerSmooth(3000L, smooth);
		GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, smooth1);
		biomes.initWorldGenSeed(seed);
		genlayervoronoizoom.initWorldGenSeed(seed);

		return new GenLayer[] { biomes, genlayervoronoizoom };
	}
}
