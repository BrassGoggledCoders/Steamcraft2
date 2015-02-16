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

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import steamcraft.common.init.InitBlocks;

public class WorldGenLeafPile extends WorldGenerator
{
	private Block block;

	public WorldGenLeafPile(Block block)
	{
		this.block = block;
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		int l = random.nextInt(15);
		byte b0 = 1;
		boolean flag = false;

		for(int i1 = x - l; i1 <= (x + l); ++i1)
		{
			for(int j1 = z - l; j1 <= (z + l); ++j1)
			{
				int k1 = i1 - x;
				int l1 = j1 - z;

				if(((k1 * k1) + (l1 * l1)) <= (l * l))
				{
					for(int i2 = y - b0; i2 <= (y + b0); ++i2)
					{
						if(world.isAirBlock(i1, j1 + 1, k1)
								&& InitBlocks.blockLeafCover.canPlaceBlockAt(world, i1, j1 + 1, k1))
						{
							world.setBlock(i1, j1 + 1, k1, this.block, random.nextInt(6), 2);
						}
					}
				}
			}
		}

		return true;
	}
}