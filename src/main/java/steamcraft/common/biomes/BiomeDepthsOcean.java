package steamcraft.common.biomes;

import net.minecraft.util.StatCollector;

public class BiomeDepthsOcean extends BiomeDepthsBase
{
	public BiomeDepthsOcean(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setHeight(new Height(1F, 1F));
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.o.name"));
	}
}
