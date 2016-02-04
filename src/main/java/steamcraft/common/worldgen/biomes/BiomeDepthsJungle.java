
package steamcraft.common.worldgen.biomes;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeDepthsJungle extends BiomeDepthsBase
{

	public BiomeDepthsJungle(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.jungle.name"));
		this.theBiomeDecorator.treesPerChunk = 50;
		this.theBiomeDecorator.grassPerChunk = 10;
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
	{
		return new WorldGenMegaJungle(false, 10, 20, 3, 3);
	}

	@Override
	public void decorate(World world, Random random, int x, int z)
	{
		for (int l = 0; l < 30; ++l)
		{
			int i1 = x + random.nextInt(16) + 8;
			int j1 = z + random.nextInt(16) + 8;
			int k1 = random.nextInt(100);
			new WorldGenVines().generate(world, random, i1, k1, j1);
		}
		super.decorate(world, random, x, z);
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random p_76730_1_)
	{
		return p_76730_1_.nextInt(4) == 0 ? new WorldGenTallGrass(Blocks.tallgrass, 2) : new WorldGenTallGrass(Blocks.tallgrass, 1);
	}
}
