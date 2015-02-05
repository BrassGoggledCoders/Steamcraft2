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
package steamcraft.common.worldgen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import steamcraft.common.init.InitBlocks;

public class WorldGenLeafPile extends WorldGenerator
{

	@Override
	public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
	{
		for(int l = 0; l < 64; ++l)
		{
			int i1 = (p_76484_3_ + p_76484_2_.nextInt(8)) - p_76484_2_.nextInt(8);
			int j1 = (p_76484_4_ + p_76484_2_.nextInt(4)) - p_76484_2_.nextInt(4);
			int k1 = (p_76484_5_ + p_76484_2_.nextInt(8)) - p_76484_2_.nextInt(8);

			if(p_76484_1_.isAirBlock(i1, j1, k1)
					&& InitBlocks.blockLeafCover.canPlaceBlockAt(p_76484_1_, i1, j1, k1))
			{
				p_76484_1_.setBlock(i1, j1, k1, InitBlocks.blockLeafCover, p_76484_2_.nextInt(6), 2);
			}
		}

		return true;
	}
}