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
package xyz.brassgoggledcoders.steamcraft.common.worldgen.dimension;

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

	static GenLayer[] makeTheWorld(long seed)
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
