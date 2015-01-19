package steamcraft.common.worldgen.dimension;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerIsland;
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
		biomes = GenLayerZoom.magnify(2000L, biomes, 5);

		/*
		 * TODO GenLayerIsland genlayerisland = new GenLayerIsland(1L); GenLayerFuzzyZoom genlayerfuzzyzoom = new GenLayerFuzzyZoom(2000L, genlayerisland);
		 * GenLayerAddIsland genlayeraddisland = new GenLayerAddIsland(1L, genlayerfuzzyzoom); GenLayerZoom genlayerzoom = new GenLayerZoom(2001L,
		 * genlayeraddisland); genlayeraddisland = new GenLayerAddIsland(2L, genlayerzoom); genlayeraddisland = new GenLayerAddIsland(50L, genlayeraddisland);
		 * genlayeraddisland = new GenLayerAddIsland(70L, genlayeraddisland); GenLayer shore = new GenLayerShore(1000L, biomes); GenLayer smooth = new
		 * GenLayerSmooth(1000L, shore); GenLayer smooth1 = new GenLayerSmooth(1000L, smooth); GenLayerRemoveTooMuchOcean genlayerremovetoomuchocean = new
		 * GenLayerRemoveTooMuchOcean(2L, smooth1);
		 */

		GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);
		biomes.initWorldGenSeed(seed);
		// genlayervoronoizoom.initWorldGenSeed(seed);

		return new GenLayer[] { null, biomes, null };
	}
}
