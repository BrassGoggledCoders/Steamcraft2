package steamcraft.common.worldgen.biomes;

import net.minecraft.util.StatCollector;

public class BiomeDepthsMountains extends BiomeDepthsBase
{

	public BiomeDepthsMountains(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.m.name"));
		this.setHeight(new Height(1.15f, 1.35F));
	}
}
