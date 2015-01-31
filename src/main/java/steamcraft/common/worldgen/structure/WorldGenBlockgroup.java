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
package steamcraft.common.worldgen.structure;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBlockgroup extends WorldGenerator
{
	private final Block blockToGen;
	private final int numberOfBlocks;

	public WorldGenBlockgroup(Block block, int p_i2011_1_)
	{
		this.blockToGen = block;
		this.numberOfBlocks = p_i2011_1_;
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		int l = random.nextInt(this.numberOfBlocks);
		byte b0 = 1;

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
						Block block = world.getBlock(i1, i2, j1);

						if((block == Blocks.dirt) || (block == Blocks.clay))
						{
							world.setBlock(i1, i2, j1, this.blockToGen, 0, 2);
						}
					}
				}
			}

			return true;
		}
		return false;
	}
}