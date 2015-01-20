package steamcraft.common.worldgen.biomes;

import java.util.Random;

import net.minecraft.util.StatCollector;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import steamcraft.common.worldgen.WorldGenRedwoodTree;

public class BiomeDepthsTallForest extends BiomeDepthsBase
{

	public BiomeDepthsTallForest(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.tallforest.name"));
		this.theBiomeDecorator.treesPerChunk = 500;
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
	{
		return new WorldGenRedwoodTree(false, 30, 20, 0, 0);
	}
}
