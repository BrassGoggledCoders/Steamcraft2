
package steamcraft.common.worldgen.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BiomeDepthsOcean extends BiomeDepthsBase
{
	public BiomeDepthsOcean(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setHeight(new Height(1F, 1F));
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.ocean.name"));
		this.heightVariation = 0;
		this.rootHeight = 0;
	}

	@Override
	public void genTerrainBlocks(World p_150573_1_, Random p_150573_2_, Block[] p_150573_3_, byte[] p_150573_4_, int p_150573_5_, int p_150573_6_,
			double p_150573_7_)
	{
		super.genTerrainBlocks(p_150573_1_, p_150573_2_, p_150573_3_, p_150573_4_, p_150573_5_, p_150573_6_, p_150573_7_);
	}
}
