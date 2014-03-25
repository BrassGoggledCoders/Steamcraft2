package common.steamcraft.common.block;

import common.steamcraft.common.lib2.CreativeTabsMod;
import common.steamcraft.common.lib2.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import java.util.Random;

public class BlockBrassLog extends BlockRotatedPillar
{
	@SideOnly(Side.CLIENT)
	private Icon tree_side;
	@SideOnly(Side.CLIENT)
	private Icon tree_top;

	protected BlockBrassLog(int i)
	{
		super(i, Material.iron);
		this.setHardness(5.0F);
		this.setStepSound(Block.soundMetalFootstep);
		this.setUnlocalizedName("logBrass");
		this.setCreativeTab(CreativeTabsMod.tabSCBlocks);
	}

	@Override
	public int idDropped(int i, Random random, int j)
	{
		return this.blockID;
	}

	@Override
	public void breakBlock(World world, int i, int j, int k, int side, int meta)
	{
		byte b0 = 4;
		int num = b0 + 1;

		if(world.checkChunksExist(i - num, j - num, k - num, i + num, j + num, k + num))
		{
			for(int x = -b0; x <= b0; ++x)
			{
				for(int y = -b0; y <= b0; ++y)
				{
					for(int z = -b0; z <= b0; ++z)
					{
						int bid = world.getBlockId(i + x, j + y, k + z);

						if(Block.blocksList[bid] != null)
						{
							Block.blocksList[bid].beginLeavesDecay(world, i + x, j + y, k + z);
						}
					}
				}
			}
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected Icon getSideIcon(int i)
	{
		return this.tree_side;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected Icon getEndIcon(int i)
	{
		return this.tree_top;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon)
	{
		this.tree_side = icon.registerIcon(LibInfo.SC2_PREFIX + this.getUnlocalizedName().substring(5) + "side");
		this.tree_top = icon.registerIcon(LibInfo.SC2_PREFIX + this.getUnlocalizedName().substring(5) + "top");
	}

	@Override
	public boolean canSustainLeaves(World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public boolean isWood(World world, int x, int y, int z)
	{
		return true;
	}
}