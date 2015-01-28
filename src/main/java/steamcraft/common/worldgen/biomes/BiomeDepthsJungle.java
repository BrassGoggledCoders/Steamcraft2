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

import net.minecraft.util.StatCollector;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;

public class BiomeDepthsJungle extends BiomeDepthsBase
{

	public BiomeDepthsJungle(int p_i1971_1_)
	{
		super(p_i1971_1_);
		this.setBiomeName(StatCollector.translateToLocal("biome.steamcraft2.innerearth.jungle.name"));
		this.theBiomeDecorator.treesPerChunk = 500;
	}

	@Override
	public WorldGenAbstractTree func_150567_a(Random p_150567_1_)
	{
		return new WorldGenMegaJungle(false, 10, 20, 3, 3);
	}
}
