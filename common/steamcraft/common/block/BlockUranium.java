package common.steamcraft.common.block;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import java.util.Random;

public class BlockUranium extends BlockMod
{
	private boolean glowing;

	public BlockUranium(int id)
	{
		super(id, Material.rock);
		this.setHardness(10F);
		this.setResistance(6F);
		this.setLightValue(0.625F);
		this.setStepSound(Block.soundMetalFootstep);
		this.setUnlocalizedName("blockUranium");
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
	{
		entity.attackEntityFrom(DamageSource.magic, 1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random random)
	{
		this.sparkle(world, i, j, k);
	}

	private void sparkle(World world, int i, int j, int k)
	{
		Random random = world.rand;
		double d = 0.0625D;
		
		for(int l = 0; l < 6; l++)
		{
			double d1 = (float)i + random.nextFloat();
			double d2 = (float)j + random.nextFloat();
			double d3 = (float)k + random.nextFloat();
			
			if(l == 0 && !world.isBlockOpaqueCube(i, j + 1, k))
			{
				d2 = (double)(j + 1) + d;
			}
			if(l == 1 && !world.isBlockOpaqueCube(i, j - 1, k))
			{
				d2 = (double)(j + 0) - d;
			}
			if(l == 2 && !world.isBlockOpaqueCube(i, j, k + 1))
			{
				d3 = (double)(k + 1) + d;
			}
			if(l == 3 && !world.isBlockOpaqueCube(i, j, k - 1))
			{
				d3 = (double)(k + 0) - d;
			}
			if(l == 4 && !world.isBlockOpaqueCube(i + 1, j, k))
			{
				d1 = (double)(i + 1) + d;
			}
			if(l == 5 && !world.isBlockOpaqueCube(i - 1, j, k))
			{
				d1 = (double)(i + 0) - d;
			}
			if(d1 < (double)i || d1 > (double)(i + 1) || d2 < 0.0D || d2 > (double)(j + 1) || d3 < (double)k || d3 > (double)(k + 1))
			{
				world.spawnParticle("reddust", d1, d2, d3, -1.0D, 1.0D, -1.0D);
			}
		}
	}
}