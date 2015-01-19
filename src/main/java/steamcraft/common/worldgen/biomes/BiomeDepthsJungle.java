package steamcraft.common.worldgen.biomes;

import java.util.Random;

import net.minecraft.util.StatCollector;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;

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
		return p_150567_1_.nextInt(10) == 0 ? this.worldGeneratorBigTree : (p_150567_1_.nextInt(2) == 0 ? new WorldGenShrub(3, 0)
				: p_150567_1_.nextInt(3) == 0 ? new WorldGenMegaJungle(false, 10, 20, 3, 3) : new WorldGenTrees(false, 4 + p_150567_1_.nextInt(7), 3, 3, true));
	}
}
