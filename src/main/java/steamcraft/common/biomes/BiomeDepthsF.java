package steamcraft.common.biomes;

import net.minecraft.util.StatCollector;

public class BiomeDepthsF extends BiomeDepthsBase
{

	public BiomeDepthsF(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.f.name"));
		this.theBiomeDecorator.treesPerChunk = 50;
	}
}
