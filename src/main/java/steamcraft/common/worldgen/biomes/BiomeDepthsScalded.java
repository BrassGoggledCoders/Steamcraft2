package steamcraft.common.worldgen.biomes;

import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;

public class BiomeDepthsScalded extends BiomeDepthsBase
{
	public BiomeDepthsScalded(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.sc.name"));
		this.topBlock = Blocks.dirt;
		this.fillerBlock = Blocks.dirt;
	}
}
