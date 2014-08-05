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
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraftforge.common.util.ForgeDirection;
import steamcraft.common.InitBlocks;

/**
 * @author warlordjones
 * 
 */
public class WorldGenBrassTree extends WorldGenBigTree
{
	static final byte[] otherCoordPairs = new byte[] { (byte) 2, (byte) 0, (byte) 0, (byte) 1, (byte) 2, (byte) 1 };

	Random rand = new Random();
	World worldObj;
	int[] basePos = new int[] { 0, 0, 0 };
	int heightLimit;
	int height;
	double heightAttenuation = 0.618D;
	double branchDensity = 1.0D;
	double branchSlope = 0.381D;
	double scaleWidth = 1.0D;
	double leafDensity = 1.0D;

	int trunkSize = 1;

	int heightLimitLimit = 12;

	int leafDistanceLimit = 4;
	int[][] leafNodes;

	public WorldGenBrassTree(boolean par1)
	{
		super(false);
	}

	void generateLeafNodeList()
	{
		height = (int) (heightLimit * heightAttenuation);

		if (height >= heightLimit)
			height = heightLimit - 1;

		int i = (int) (1.382D + Math.pow((leafDensity * heightLimit) / 13.0D, 2.0D));

		if (i < 1)
			i = 1;

		int[][] aint = new int[i * heightLimit][4];
		int j = (basePos[1] + heightLimit) - leafDistanceLimit;
		int k = 1;
		int l = basePos[1] + height;
		int i1 = j - basePos[1];
		aint[0][0] = basePos[0];
		aint[0][1] = j;
		aint[0][2] = basePos[2];
		aint[0][3] = l;
		--j;

		while (i1 >= 0)
		{
			int j1 = 0;
			float f = layerSize(i1);

			if (f < 0.0F)
			{
				--j;
				--i1;
			}
			else
			{
				for (double d0 = 0.5D; j1 < i; ++j1)
				{
					double d1 = scaleWidth * f * (rand.nextFloat() + 0.328D);
					double d2 = rand.nextFloat() * 2.0D * Math.PI;
					int k1 = MathHelper.floor_double((d1 * Math.sin(d2)) + basePos[0] + d0);
					int l1 = MathHelper.floor_double((d1 * Math.cos(d2)) + basePos[2] + d0);
					int[] aint1 = new int[] { k1, j, l1 };
					int[] aint2 = new int[] { k1, j + leafDistanceLimit, l1 };

					if (checkBlockLine(aint1, aint2) == -1)
					{
						int[] aint3 = new int[] { basePos[0], basePos[1], basePos[2] };
						double d3 = Math.sqrt(Math.pow(Math.abs(basePos[0] - aint1[0]), 2.0D) + Math.pow(Math.abs(basePos[2] - aint1[2]), 2.0D));
						double d4 = d3 * branchSlope;

						if ((aint1[1] - d4) > l)
							aint3[1] = l;
						else
							aint3[1] = (int) (aint1[1] - d4);

						if (checkBlockLine(aint3, aint1) == -1)
						{
							aint[k][0] = k1;
							aint[k][1] = j;
							aint[k][2] = l1;
							aint[k][3] = aint3[1];
							++k;
						}
					}
				}

				--j;
				--i1;
			}
		}

		leafNodes = new int[k][4];
		System.arraycopy(aint, 0, leafNodes, 0, k);
	}

	void func_150529_a(int p_150529_1_, int p_150529_2_, int p_150529_3_, float p_150529_4_, byte p_150529_5_, Block p_150529_6_)
	{
		int l = (int) (p_150529_4_ + 0.618D);
		byte b1 = otherCoordPairs[p_150529_5_];
		byte b2 = otherCoordPairs[p_150529_5_ + 3];
		int[] aint = new int[] { p_150529_1_, p_150529_2_, p_150529_3_ };
		int[] aint1 = new int[] { 0, 0, 0 };
		int i1 = -l;
		int j1 = -l;

		for (aint1[p_150529_5_] = aint[p_150529_5_]; i1 <= l; ++i1)
		{
			aint1[b1] = aint[b1] + i1;
			j1 = -l;

			while (j1 <= l)
			{
				double d0 = Math.pow(Math.abs(i1) + 0.5D, 2.0D) + Math.pow(Math.abs(j1) + 0.5D, 2.0D);

				if (d0 > (p_150529_4_ * p_150529_4_))
					++j1;
				else
				{
					aint1[b2] = aint[b2] + j1;
					Block block1 = worldObj.getBlock(aint1[0], aint1[1], aint1[2]);

					if (!block1.isAir(worldObj, aint1[0], aint1[1], aint1[2]) && !block1.isLeaves(worldObj, aint1[0], aint1[1], aint1[2]))
						++j1;
					else
					{
						setBlockAndNotifyAdequately(worldObj, aint1[0], aint1[1], aint1[2], p_150529_6_, 0);
						++j1;
					}
				}
			}
		}
	}

	float layerSize(int par1)
	{
		if (par1 < (heightLimit * 0.3D))
			return -1.618F;
		else
		{
			float f = heightLimit / 2.0F;
			float f1 = (heightLimit / 2.0F) - par1;
			float f2;

			if (f1 == 0.0F)
				f2 = f;
			else if (Math.abs(f1) >= f)
				f2 = 0.0F;
			else
				f2 = (float) Math.sqrt(Math.pow(Math.abs(f), 2.0D) - Math.pow(Math.abs(f1), 2.0D));

			f2 *= 0.5F;
			return f2;
		}
	}

	float leafSize(int par1)
	{
		return (par1 >= 0) && (par1 < leafDistanceLimit) ? (par1 != 0) && (par1 != (leafDistanceLimit - 1)) ? 3.0F : 2.0F : -1.0F;
	}

	void generateLeafNode(int par1, int par2, int par3)
	{
		int l = par2;

		for (int i1 = par2 + leafDistanceLimit; l < i1; ++l)
		{
			float f = leafSize(l - par2);
			func_150529_a(par1, l, par3, f, (byte) 1, InitBlocks.blockBrassLeaves);
		}
	}

	void func_150530_a(int[] p_150530_1_, int[] p_150530_2_, Block p_150530_3_)
	{
		int[] aint2 = new int[] { 0, 0, 0 };
		byte b0 = 0;
		byte b1;

		for (b1 = 0; b0 < 3; ++b0)
		{
			aint2[b0] = p_150530_2_[b0] - p_150530_1_[b0];

			if (Math.abs(aint2[b0]) > Math.abs(aint2[b1]))
				b1 = b0;
		}

		if (aint2[b1] != 0)
		{
			byte b2 = otherCoordPairs[b1];
			byte b3 = otherCoordPairs[b1 + 3];
			byte b4;

			if (aint2[b1] > 0)
				b4 = 1;
			else
				b4 = -1;

			double d0 = (double) aint2[b2] / (double) aint2[b1];
			double d1 = (double) aint2[b3] / (double) aint2[b1];
			int[] aint3 = new int[] { 0, 0, 0 };
			int i = 0;

			for (int j = aint2[b1] + b4; i != j; i += b4)
			{
				aint3[b1] = MathHelper.floor_double(p_150530_1_[b1] + i + 0.5D);
				aint3[b2] = MathHelper.floor_double(p_150530_1_[b2] + (i * d0) + 0.5D);
				aint3[b3] = MathHelper.floor_double(p_150530_1_[b3] + (i * d1) + 0.5D);
				byte b5 = 0;
				int k = Math.abs(aint3[0] - p_150530_1_[0]);
				int l = Math.abs(aint3[2] - p_150530_1_[2]);
				int i1 = Math.max(k, l);

				if (i1 > 0)
					if (k == i1)
						b5 = 4;
					else if (l == i1)
						b5 = 8;

				setBlockAndNotifyAdequately(worldObj, aint3[0], aint3[1], aint3[2], p_150530_3_, b5);
			}
		}
	}

	void generateLeaves()
	{
		int i = 0;

		for (int j = leafNodes.length; i < j; ++i)
		{
			int k = leafNodes[i][0];
			int l = leafNodes[i][1];
			int i1 = leafNodes[i][2];
			generateLeafNode(k, l, i1);
		}
	}

	boolean leafNodeNeedsBase(int par1)
	{
		return par1 >= (heightLimit * 0.2D);
	}

	void generateTrunk()
	{
		int i = basePos[0];
		int j = basePos[1];
		int k = basePos[1] + height;
		int l = basePos[2];
		int[] aint = new int[] { i, j, l };
		int[] aint1 = new int[] { i, k, l };
		func_150530_a(aint, aint1, InitBlocks.blockBrassLog);

		if (trunkSize == 2)
		{
			++aint[0];
			++aint1[0];
			func_150530_a(aint, aint1, InitBlocks.blockBrassLog);
			++aint[2];
			++aint1[2];
			func_150530_a(aint, aint1, InitBlocks.blockBrassLog);
			aint[0] += -1;
			aint1[0] += -1;
			func_150530_a(aint, aint1, InitBlocks.blockBrassLog);
		}
	}

	void generateLeafNodeBases()
	{
		int i = 0;
		int j = leafNodes.length;

		for (int[] aint = new int[] { basePos[0], basePos[1], basePos[2] }; i < j; ++i)
		{
			int[] aint1 = leafNodes[i];
			int[] aint2 = new int[] { aint1[0], aint1[1], aint1[2] };
			aint[1] = aint1[3];
			int k = aint[1] - basePos[1];

			if (leafNodeNeedsBase(k))
				func_150530_a(aint, aint2, InitBlocks.blockBrassLog);
		}
	}

	int checkBlockLine(int[] par1ArrayOfInteger, int[] par2ArrayOfInteger)
	{
		int[] aint2 = new int[] { 0, 0, 0 };
		byte b0 = 0;
		byte b1;

		for (b1 = 0; b0 < 3; ++b0)
		{
			aint2[b0] = par2ArrayOfInteger[b0] - par1ArrayOfInteger[b0];

			if (Math.abs(aint2[b0]) > Math.abs(aint2[b1]))
				b1 = b0;
		}

		if (aint2[b1] == 0)
			return -1;
		else
		{
			byte b2 = otherCoordPairs[b1];
			byte b3 = otherCoordPairs[b1 + 3];
			byte b4;

			if (aint2[b1] > 0)
				b4 = 1;
			else
				b4 = -1;

			double d0 = (double) aint2[b2] / (double) aint2[b1];
			double d1 = (double) aint2[b3] / (double) aint2[b1];
			int[] aint3 = new int[] { 0, 0, 0 };
			int i = 0;
			int j;

			for (j = aint2[b1] + b4; i != j; i += b4)
			{
				aint3[b1] = par1ArrayOfInteger[b1] + i;
				aint3[b2] = MathHelper.floor_double(par1ArrayOfInteger[b2] + (i * d0));
				aint3[b3] = MathHelper.floor_double(par1ArrayOfInteger[b3] + (i * d1));
				Block block = worldObj.getBlock(aint3[0], aint3[1], aint3[2]);

				if (!isReplaceable(worldObj, aint3[0], aint3[1], aint3[2]))
					break;
			}

			return i == j ? -1 : Math.abs(i);
		}
	}

	boolean validTreeLocation()
	{
		int[] aint = new int[] { basePos[0], basePos[1], basePos[2] };
		int[] aint1 = new int[] { basePos[0], (basePos[1] + heightLimit) - 1, basePos[2] };
		Block block = worldObj.getBlock(basePos[0], basePos[1] - 1, basePos[2]);

		boolean isSoil = block.canSustainPlant(worldObj, basePos[0], basePos[1] - 1, basePos[2], ForgeDirection.UP, (BlockSapling) Blocks.sapling);
		if (!isSoil)
			return false;
		else
		{
			int i = checkBlockLine(aint, aint1);

			if (i == -1)
				return true;
			else if (i < 6)
				return false;
			else
			{
				heightLimit = i;
				return true;
			}
		}
	}

	@Override
	public void setScale(double par1, double par3, double par5)
	{
		heightLimitLimit = (int) (par1 * 12.0D);

		if (par1 > 0.5D)
			leafDistanceLimit = 5;

		scaleWidth = par3;
		leafDensity = par5;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		worldObj = par1World;
		long l = par2Random.nextLong();
		rand.setSeed(l);
		basePos[0] = par3;
		basePos[1] = par4;
		basePos[2] = par5;

		if (heightLimit == 0)
			heightLimit = 5 + rand.nextInt(heightLimitLimit);

		if (!validTreeLocation())
		{
			worldObj = null; // Fix vanilla Mem leak, holds latest world
			return false;
		}
		else
		{
			generateLeafNodeList();
			generateLeaves();
			generateTrunk();
			generateLeafNodeBases();
			worldObj = null; // Fix vanilla Mem leak, holds latest world
			return true;
		}
	}
}
