/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
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
		this.theBiomeDecorator.treesPerChunk = 20;
		this.theBiomeDecorator.grassPerChunk = 30;
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random rand)
	{
		int randnumb = rand.nextInt(4);
		if(randnumb != 0)
			return new WorldGenRedwoodTree(false, 30, 20, 0, 0);
		else
			return new WorldGenMegaPineTree(false, false);
	}

	@Override
	public void decorate(World world, Random random, int chunkX, int chunkZ)
	{
		for(int l = 0; l < 3; ++l)
		{
			int k = random.nextInt(3);
			int i1 = chunkX + random.nextInt(16) + 8;
			int j1 = chunkZ + random.nextInt(16) + 8;
			int k1 = world.getHeightValue(i1, j1);
			new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0).generate(world, random, i1, k1, j1);
		}
		super.decorate(world, random, chunkX, chunkZ);
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
