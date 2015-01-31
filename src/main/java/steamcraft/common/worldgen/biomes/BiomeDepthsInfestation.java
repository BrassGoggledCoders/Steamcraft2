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
import steamcraft.common.InitBlocks;
import steamcraft.common.worldgen.structure.WorldGenBlockgroup;

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
		for(int l = 0; l < 30; ++l)
		{
			int i1 = x + random.nextInt(16) + 8;
			int j1 = z + random.nextInt(16) + 8;
			int k1 = random.nextInt(100);
			new WorldGenBlockgroup(Blocks.dirt, 40).generate(world, random, i1, k1, j1);
		}
		super.decorate(world, random, x, z);
	}

}
