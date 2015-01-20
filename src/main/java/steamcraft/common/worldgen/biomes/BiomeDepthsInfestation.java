package steamcraft.common.worldgen.biomes;

import net.minecraft.util.StatCollector;
import steamcraft.common.InitBlocks;

public class BiomeDepthsInfestation extends BiomeDepthsBase
{
	public BiomeDepthsInfestation(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.infestation.name"));
		this.topBlock = InitBlocks.blockInfestedGrass;
		this.fillerBlock = InitBlocks.blockInfestedDirt;
	}
}
