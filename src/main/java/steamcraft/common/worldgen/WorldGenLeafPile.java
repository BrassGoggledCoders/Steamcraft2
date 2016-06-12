
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

		for (int i1 = x - l; i1 <= (x + l); ++i1)
		{
			for (int j1 = z - l; j1 <= (z + l); ++j1)
			{
				int k1 = i1 - x;
				int l1 = j1 - z;

				if (((k1 * k1) + (l1 * l1)) <= (l * l))
				{
					for (int i2 = y - b0; i2 <= (y + b0); ++i2)
					{
						if (world.isAirBlock(i1, j1 + 1, k1) && InitBlocks.blockLeafCover.canPlaceBlockAt(world, i1, j1 + 1, k1))
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