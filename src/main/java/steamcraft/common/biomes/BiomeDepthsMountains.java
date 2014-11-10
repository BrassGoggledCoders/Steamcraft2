package steamcraft.common.biomes;

import net.minecraft.util.StatCollector;

public class BiomeDepthsMountains extends BiomeDepthsBase
{

	public BiomeDepthsMountains(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.f.name"));
		this.setHeight(new Height(1.3f, 1.5F));
	}
}
