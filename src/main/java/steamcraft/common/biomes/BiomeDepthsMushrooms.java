package steamcraft.common.biomes;

import net.minecraft.util.StatCollector;

public class BiomeDepthsMushrooms extends BiomeDepthsBase
{
	public BiomeDepthsMushrooms(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.s.name"));
		this.theBiomeDecorator.bigMushroomsPerChunk = 300;
		this.theBiomeDecorator.mushroomsPerChunk = 400;
	}
}
