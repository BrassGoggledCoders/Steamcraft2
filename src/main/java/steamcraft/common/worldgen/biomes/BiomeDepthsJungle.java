package steamcraft.common.worldgen.biomes;

import java.util.Random;

import net.minecraft.util.StatCollector;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;

public class BiomeDepthsJungle extends BiomeDepthsBase
{

	public BiomeDepthsJungle(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.dj.name"));
		this.theBiomeDecorator.treesPerChunk = 500;
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
	{
		return new WorldGenMegaJungle(false, 10, 20, 3, 3);
	}
}
