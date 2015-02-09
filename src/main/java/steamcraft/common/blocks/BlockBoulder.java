package steamcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

import steamcraft.common.entities.EntityFallingBoulder;

public class BlockBoulder extends BaseBlock
{
	public static boolean fallInstantly;

	public BlockBoulder()
	{
		super(Material.rock);
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(2F);
		this.setResistance(20F);
	}

	/**
	 * Called whenever the block is added into the world. Args: world, x, y, z
	 */
	@Override
	public void onBlockAdded(World p_149726_1_, int p_149726_2_, int p_149726_3_, int p_149726_4_)
	{
		p_149726_1_.scheduleBlockUpdate(p_149726_2_, p_149726_3_, p_149726_4_, this, this.tickRate(p_149726_1_));
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are their own) Args: x, y, z, neighbor
	 * Block
	 */
	@Override
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
	{
		p_149695_1_.scheduleBlockUpdate(p_149695_2_, p_149695_3_, p_149695_4_, this, this.tickRate(p_149695_1_));
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
	{
		if(!p_149674_1_.isRemote)
		{
			this.func_149830_m(p_149674_1_, p_149674_2_, p_149674_3_, p_149674_4_);
		}
	}

	private void func_149830_m(World p_149830_1_, int p_149830_2_, int p_149830_3_, int p_149830_4_)
	{
		if(func_149831_e(p_149830_1_, p_149830_2_, p_149830_3_ - 1, p_149830_4_) && (p_149830_3_ >= 0))
		{
			byte b0 = 32;

			if(!fallInstantly
					&& p_149830_1_.checkChunksExist(p_149830_2_ - b0, p_149830_3_ - b0, p_149830_4_ - b0, p_149830_2_ + b0, p_149830_3_ + b0, p_149830_4_ + b0))
			{
				if(!p_149830_1_.isRemote)
				{
					EntityFallingBoulder EntityFallingBoulder = new EntityFallingBoulder(p_149830_1_, p_149830_2_ + 0.5F, p_149830_3_ + 0.5F,
							p_149830_4_ + 0.5F);
					this.func_149829_a(EntityFallingBoulder);
					p_149830_1_.spawnEntityInWorld(EntityFallingBoulder);
				}
			}
			else
			{
				p_149830_1_.setBlockToAir(p_149830_2_, p_149830_3_, p_149830_4_);

				while(func_149831_e(p_149830_1_, p_149830_2_, p_149830_3_ - 1, p_149830_4_) && (p_149830_3_ > 0))
				{
					--p_149830_3_;
				}

				if(p_149830_3_ > 0)
				{
					p_149830_1_.setBlock(p_149830_2_, p_149830_3_, p_149830_4_, this);
				}
			}
		}
	}

	protected void func_149829_a(EntityFallingBoulder p_149829_1_)
	{
		p_149829_1_.func_145806_a(true);
	}

	/**
	 * How many world ticks before ticking
	 */
	@Override
	public int tickRate(World p_149738_1_)
	{
		return 2;
	}

	public static boolean func_149831_e(World p_149831_0_, int p_149831_1_, int p_149831_2_, int p_149831_3_)
	{
		Block block = p_149831_0_.getBlock(p_149831_1_, p_149831_2_, p_149831_3_);

		if(block.isAir(p_149831_0_, p_149831_1_, p_149831_2_, p_149831_3_))
		{
			return true;
		}
		else if(block == Blocks.fire)
		{
			return true;
		}
		else
		{
			Material material = block.getMaterial();
			return material == Material.water ? true : material == Material.lava;
		}
	}

	public void func_149828_a(World p_149828_1_, int p_149828_2_, int p_149828_3_, int p_149828_4_, int p_149828_5_)
	{
	}
}
