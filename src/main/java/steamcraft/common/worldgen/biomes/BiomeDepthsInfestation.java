
package steamcraft.common.worldgen.biomes;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import steamcraft.common.init.InitBlocks;
import steamcraft.common.worldgen.WorldGenBlockgroup;

public class BiomeDepthsInfestation extends BiomeDepthsBase
{
	public BiomeDepthsInfestation(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.infestation.name"));
		this.topBlock = InitBlocks.blockInfestedGrass;
		this.fillerBlock = InitBlocks.blockInfestedDirt;
	}

	@Override
	public void decorate(World world, Random random, int x, int z)
	{
		for (int l = 0; l < 5; ++l)
		{
			int i1 = x + random.nextInt(16);
			int j1 = z + random.nextInt(16);
			int k1 = random.nextInt(100);
			new WorldGenBlockgroup(Blocks.dirt, 8).generate(world, random, i1, k1, j1);
		}
		super.decorate(world, random, x, z);
	}

}
