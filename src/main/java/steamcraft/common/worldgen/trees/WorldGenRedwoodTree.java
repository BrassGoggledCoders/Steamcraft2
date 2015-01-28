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
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenHugeTrees;
import steamcraft.common.InitBlocks;

public class WorldGenRedwoodTree extends WorldGenHugeTrees
{

	public WorldGenRedwoodTree(boolean doBlockNotify, int baseHeight, int p_i45456_3_, int woodMeta, int leafMeta)
	{
		super(doBlockNotify, baseHeight, p_i45456_3_, woodMeta, leafMeta);
	}

	@Override
	public boolean generate(World p_76484_1_, Random p_76484_2_, int p_76484_3_, int p_76484_4_, int p_76484_5_)
	{
		int l = this.func_150533_a(p_76484_2_);

		if(!this.func_150537_a(p_76484_1_, p_76484_2_, p_76484_3_, p_76484_4_, p_76484_5_, l))
		{
			return false;
		}
		else
		{
			this.func_150543_c(p_76484_1_, p_76484_3_, p_76484_5_, p_76484_4_ + l, 2, p_76484_2_);

			for(int i1 = (p_76484_4_ + l) - 2 - p_76484_2_.nextInt(4); i1 > (p_76484_4_ + (l / 2)); i1 -= 2 + p_76484_2_.nextInt(4))
			{
				float f = p_76484_2_.nextFloat() * (float) Math.PI * 2.0F;
				int j1 = p_76484_3_ + (int) (0.5F + (MathHelper.cos(f) * 4.0F));
				int k1 = p_76484_5_ + (int) (0.5F + (MathHelper.sin(f) * 4.0F));
				int l1;

				for(l1 = 0; l1 < 5; ++l1)
				{
					j1 = p_76484_3_ + (int) (1.5F + (MathHelper.cos(f) * l1));
					k1 = p_76484_5_ + (int) (1.5F + (MathHelper.sin(f) * l1));
					this.setBlockAndNotifyAdequately(p_76484_1_, j1, (i1 - 3) + (l1 / 2), k1, InitBlocks.blockRedwoodLog, this.woodMetadata);
				}

				l1 = 1 + p_76484_2_.nextInt(2);
				int i2 = i1;

				for(int j2 = i1 - l1; j2 <= i2; ++j2)
				{
					int k2 = j2 - i2;
					this.func_150534_b(p_76484_1_, j1, j2, k1, 1 - k2, p_76484_2_);
				}
			}

			for(int l2 = 0; l2 < l; ++l2)
			{
				Block block = p_76484_1_.getBlock(p_76484_3_, p_76484_4_ + l2, p_76484_5_);

				if(block.isAir(p_76484_1_, p_76484_3_, p_76484_4_ + l2, p_76484_5_) || block.isLeaves(p_76484_1_, p_76484_3_, p_76484_4_ + l2, p_76484_5_))
				{
					this.setBlockAndNotifyAdequately(p_76484_1_, p_76484_3_, p_76484_4_ + l2, p_76484_5_, InitBlocks.blockRedwoodLog, this.woodMetadata);
				}

				if(l2 < (l - 1))
				{
					block = p_76484_1_.getBlock(p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_);

					if(block.isAir(p_76484_1_, p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_)
							|| block.isLeaves(p_76484_1_, p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_))
					{
						this.setBlockAndNotifyAdequately(p_76484_1_, p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_, InitBlocks.blockRedwoodLog, this.woodMetadata);
					}

					block = p_76484_1_.getBlock(p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_ + 1);

					if(block.isAir(p_76484_1_, p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_ + 1)
							|| block.isLeaves(p_76484_1_, p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_ + 1))
					{
						this.setBlockAndNotifyAdequately(p_76484_1_, p_76484_3_ + 1, p_76484_4_ + l2, p_76484_5_ + 1, InitBlocks.blockRedwoodLog,
								this.woodMetadata);
					}

					block = p_76484_1_.getBlock(p_76484_3_, p_76484_4_ + l2, p_76484_5_ + 1);

					if(block.isAir(p_76484_1_, p_76484_3_, p_76484_4_ + l2, p_76484_5_ + 1)
							|| block.isLeaves(p_76484_1_, p_76484_3_, p_76484_4_ + l2, p_76484_5_ + 1))
					{
						this.setBlockAndNotifyAdequately(p_76484_1_, p_76484_3_, p_76484_4_ + l2, p_76484_5_ + 1, InitBlocks.blockRedwoodLog, this.woodMetadata);
					}
				}
			}

			return true;
		}
	}

	private void func_150543_c(World p_150543_1_, int p_150543_2_, int p_150543_3_, int p_150543_4_, int p_150543_5_, Random p_150543_6_)
	{
		byte b0 = 2;

		for(int i1 = p_150543_4_ - b0; i1 <= p_150543_4_; ++i1)
		{
			int j1 = i1 - p_150543_4_;
			this.func_150535_a(p_150543_1_, p_150543_2_, i1, p_150543_3_, (p_150543_5_ + 1) - j1, p_150543_6_);
		}
	}

	// Top Leaves
	@Override
	protected void func_150535_a(World p_150535_1_, int p_150535_2_, int p_150535_3_, int p_150535_4_, int p_150535_5_, Random p_150535_6_)
	{
		int i1 = p_150535_5_ * p_150535_5_;

		for(int j1 = p_150535_2_ - p_150535_5_; j1 <= (p_150535_2_ + p_150535_5_ + 1); ++j1)
		{
			int k1 = j1 - p_150535_2_;

			for(int l1 = p_150535_4_ - p_150535_5_; l1 <= (p_150535_4_ + p_150535_5_ + 1); ++l1)
			{
				int i2 = l1 - p_150535_4_;
				int j2 = k1 - 1;
				int k2 = i2 - 1;

				if((((k1 * k1) + (i2 * i2)) <= i1) || (((j2 * j2) + (k2 * k2)) <= i1) || (((k1 * k1) + (k2 * k2)) <= i1) || (((j2 * j2) + (i2 * i2)) <= i1))
				{
					Block block = p_150535_1_.getBlock(j1, p_150535_3_, l1);

					if(block.isAir(p_150535_1_, j1, p_150535_3_, l1) || block.isLeaves(p_150535_1_, j1, p_150535_3_, l1))
					{
						this.setBlockAndNotifyAdequately(p_150535_1_, j1, p_150535_3_, l1, InitBlocks.blockRedwoodLeaves, this.leavesMetadata);
					}
				}
			}
		}
	}

	// Branch leaves
	@Override
	protected void func_150534_b(World p_150534_1_, int p_150534_2_, int p_150534_3_, int p_150534_4_, int p_150534_5_, Random p_150534_6_)
	{
		int i1 = p_150534_5_ * p_150534_5_;

		for(int j1 = p_150534_2_ - p_150534_5_; j1 <= (p_150534_2_ + p_150534_5_); ++j1)
		{
			int k1 = j1 - p_150534_2_;

			for(int l1 = p_150534_4_ - p_150534_5_; l1 <= (p_150534_4_ + p_150534_5_); ++l1)
			{
				int i2 = l1 - p_150534_4_;

				if(((k1 * k1) + (i2 * i2)) <= i1)
				{
					Block block = p_150534_1_.getBlock(j1, p_150534_3_, l1);

					if(block.isAir(p_150534_1_, j1, p_150534_3_, l1) || block.isLeaves(p_150534_1_, j1, p_150534_3_, l1))
					{
						this.setBlockAndNotifyAdequately(p_150534_1_, j1, p_150534_3_, l1, InitBlocks.blockRedwoodLeaves, this.leavesMetadata);
					}
				}
			}
		}
	}
}