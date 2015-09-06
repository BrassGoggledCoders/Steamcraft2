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
package steamcraft.common.worldgen.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import steamcraft.common.init.InitBlocks;

public class WorldGenDeadTree extends WorldGenAbstractTree
{

	public WorldGenDeadTree(boolean p_i45463_1_)
	{
		super(p_i45463_1_);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		int l = rand.nextInt(3) + rand.nextInt(3) + 5;
		boolean flag = true;

		if ((y >= 1) && ((y + l + 1) <= 256))
		{
			int j1;
			int k1;

			for (int i1 = y; i1 <= (y + 1 + l); ++i1)
			{
				byte b0 = 1;

				if (i1 == y)
				{
					b0 = 0;
				}

				if (i1 >= ((y + 1 + l) - 2))
				{
					b0 = 2;
				}

				for (j1 = x - b0; (j1 <= (x + b0)) && flag; ++j1)
				{
					for (k1 = z - b0; (k1 <= (z + b0)) && flag; ++k1)
					{
						if ((i1 >= 0) && (i1 < 256))
						{
							Block block = world.getBlock(j1, i1, k1);

							if (!this.isReplaceable(world, j1, i1, k1))
							{
								flag = false;
							}
						}
						else
						{
							flag = false;
						}
					}
				}
			}

			if (!flag)
			{
				return false;
			}
			else
			{
				Block block3 = world.getBlock(x, y - 1, z);

				if ((block3 == Blocks.grass) && (y < (256 - l - 1)))
				{
					block3.onPlantGrow(world, x, y - 1, z, x, y, z);
					int j3 = rand.nextInt(4);
					j1 = l - rand.nextInt(4) - 1;
					k1 = 3 - rand.nextInt(3);
					int k3 = x;
					int l1 = z;
					int i2 = 0;
					int j2;
					int k2;

					for (j2 = 0; j2 < l; ++j2)
					{
						k2 = y + j2;

						if ((j2 >= j1) && (k1 > 0))
						{
							k3 += Direction.offsetX[j3];
							l1 += Direction.offsetZ[j3];
							--k1;
						}

						Block block1 = world.getBlock(k3, k2, l1);

						if (block1.isAir(world, k3, k2, l1) || block1.isLeaves(world, k3, k2, l1))
						{
							this.setBlockAndNotifyAdequately(world, k3, k2, l1, InitBlocks.blockPetrifiedLog, 0);
							i2 = k2;
						}
					}

					for (j2 = -1; j2 <= 1; ++j2)
					{
						for (k2 = -1; k2 <= 1; ++k2)
						{
							this.doLeafGen(world, rand, k3 + j2, i2 + 1, l1 + k2);
						}
					}

					this.doLeafGen(world, rand, k3 + 2, i2 + 1, l1);
					this.doLeafGen(world, rand, k3 - 2, i2 + 1, l1);
					this.doLeafGen(world, rand, k3, i2 + 1, l1 + 2);
					this.doLeafGen(world, rand, k3, i2 + 1, l1 - 2);

					for (j2 = -3; j2 <= 3; ++j2)
					{
						for (k2 = -3; k2 <= 3; ++k2)
						{
							if ((Math.abs(j2) != 3) || (Math.abs(k2) != 3))
							{
								this.doLeafGen(world, rand, k3 + j2, i2, l1 + k2);
							}
						}
					}

					k3 = x;
					l1 = z;
					j2 = rand.nextInt(4);

					if (j2 != j3)
					{
						k2 = j1 - rand.nextInt(2) - 1;
						int l3 = 1 + rand.nextInt(3);
						i2 = 0;
						int l2;
						int i3;

						for (l2 = k2; (l2 < l) && (l3 > 0); --l3)
						{
							if (l2 >= 1)
							{
								i3 = y + l2;
								k3 += Direction.offsetX[j2];
								l1 += Direction.offsetZ[j2];
								Block block2 = world.getBlock(k3, i3, l1);

								if (block2.isAir(world, k3, i3, l1) || block2.isLeaves(world, k3, i3, l1))
								{
									this.setBlockAndNotifyAdequately(world, k3, i3, l1, InitBlocks.blockPetrifiedLog, 0);
									i2 = i3;
								}
							}

							++l2;
						}

						if (i2 > 0)
						{
							for (l2 = -1; l2 <= 1; ++l2)
							{
								for (i3 = -1; i3 <= 1; ++i3)
								{
									this.doLeafGen(world, rand, k3 + l2, i2 + 1, l1 + i3);
								}
							}

							for (l2 = -2; l2 <= 2; ++l2)
							{
								for (i3 = -2; i3 <= 2; ++i3)
								{
									if ((Math.abs(l2) != 2) || (Math.abs(i3) != 2))
									{
										this.doLeafGen(world, rand, k3 + l2, i2, l1 + i3);
									}
								}
							}
						}
					}

					return true;
				}
				else
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
	}

	private void doLeafGen(World world, Random random, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);

		if (block.isAir(world, x, y, z) || block.isLeaves(world, x, y, z))
		{
			if (random.nextBoolean())
				this.setBlockAndNotifyAdequately(world, x, y, z, InitBlocks.blockDeadLeaves, 0);
		}
	}
}