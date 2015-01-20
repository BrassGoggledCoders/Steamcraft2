package steamcraft.common.worldgen.biomes;

import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;

public class BiomeDepthsScorched extends BiomeDepthsBase
{
	public BiomeDepthsScorched(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.shc.name"));
		this.topBlock = Blocks.stone;
		this.fillerBlock = Blocks.stone;
	}
}
