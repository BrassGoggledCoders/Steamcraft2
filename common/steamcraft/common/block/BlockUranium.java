package common.steamcraft.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
			double d1 = i + random.nextFloat();
			double d2 = j + random.nextFloat();
			double d3 = k + random.nextFloat();
			
			if(l == 0 && !world.isBlockOpaqueCube(i, j + 1, k))
			{
				d2 = j + 1 + d;
			}
			if(l == 1 && !world.isBlockOpaqueCube(i, j - 1, k))
			{
				d2 = j + 0 - d;
			}
			if(l == 2 && !world.isBlockOpaqueCube(i, j, k + 1))
			{
				d3 = k + 1 + d;
			}
			if(l == 3 && !world.isBlockOpaqueCube(i, j, k - 1))
			{
				d3 = k + 0 - d;
			}
			if(l == 4 && !world.isBlockOpaqueCube(i + 1, j, k))
			{
				d1 = i + 1 + d;
			}
			if(l == 5 && !world.isBlockOpaqueCube(i - 1, j, k))
			{
				d1 = i + 0 - d;
			}
			if(d1 < i || d1 > i + 1 || d2 < 0.0D || d2 > j + 1 || d3 < k || d3 > k + 1)
			{
				world.spawnParticle("reddust", d1, d2, d3, -1.0D, 1.0D, -1.0D);
			}
		}
	}
}