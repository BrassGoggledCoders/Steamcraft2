
package steamcraft.common.worldgen.biomes;

import net.minecraft.util.StatCollector;

public class BiomeDepthsForest extends BiomeDepthsBase
{

	public BiomeDepthsForest(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.forest.name"));
		this.theBiomeDecorator.treesPerChunk = 50;
	}
}
