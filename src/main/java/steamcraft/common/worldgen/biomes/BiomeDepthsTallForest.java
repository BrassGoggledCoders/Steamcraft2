
package steamcraft.common.worldgen.biomes;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

import steamcraft.common.worldgen.trees.WorldGenRedwoodTree;

public class BiomeDepthsTallForest extends BiomeDepthsBase
{
	public BiomeDepthsTallForest(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.tallforest.name"));
		this.theBiomeDecorator.treesPerChunk = 60;
		this.theBiomeDecorator.grassPerChunk = 10;
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random rand)
	{
		int randnumb = rand.nextInt(4);
		if (randnumb != 0)
			return new WorldGenRedwoodTree(false, 30, 20, 0, 0);
		else
			return new WorldGenMegaPineTree(false, false);
	}

	@Override
	public void decorate(World p_76728_1_, Random p_76728_2_, int p_76728_3_, int p_76728_4_)
	{

		genTallFlowers.func_150548_a(3);

		for (int k = 0; k < 7; ++k)
		{
			int l = p_76728_3_ + p_76728_2_.nextInt(16);
			int i1 = p_76728_4_ + p_76728_2_.nextInt(16);
			int j1 = p_76728_2_.nextInt(p_76728_1_.getHeightValue(l, i1) + 32);
			genTallFlowers.generate(p_76728_1_, p_76728_2_, l, j1, i1);
		}
		for (int l = 0; l < 3; ++l)
		{
			int k = p_76728_2_.nextInt(3);
			int i1 = p_76728_3_ + p_76728_2_.nextInt(16);
			int j1 = p_76728_4_ + p_76728_2_.nextInt(16);
			int k1 = p_76728_1_.getHeightValue(i1, j1);
			new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0).generate(p_76728_1_, p_76728_2_, i1, k1, j1);
		}

		super.decorate(p_76728_1_, p_76728_2_, p_76728_3_, p_76728_4_);
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random p_76730_1_)
	{
		return new WorldGenTallGrass(Blocks.tallgrass, 2);
	}
}
