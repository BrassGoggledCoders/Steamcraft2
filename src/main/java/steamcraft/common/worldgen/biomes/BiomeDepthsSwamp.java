package steamcraft.common.worldgen.biomes;

import net.minecraft.util.StatCollector;

public class BiomeDepthsSwamp extends BiomeDepthsBase
{

	public BiomeDepthsSwamp(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.sf.name"));
		this.theBiomeDecorator.treesPerChunk = 500;
	}
}
