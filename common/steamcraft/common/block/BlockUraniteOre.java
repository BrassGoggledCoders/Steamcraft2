/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * File created @ [Jan 30, 2014, 6:11:43 PM]
 */
package common.steamcraft.common.block;

import common.steamcraft.client.core.helper.IconHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.world.World;

import java.util.Random;

/**
 * 
 * @author MrArcane111 & Proloe
 *
 */
public class BlockUraniteOre extends BlockMod
{
	private boolean glowing;

	public BlockUraniteOre(int id)
	{
		super(id, Material.rock);
		this.setLightValue(0.625F);
		this.setHardness(10F);
		this.setResistance(6F);
		this.setStepSound(Block.soundStoneFootstep);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon)
	{
		blockIcon = IconHelper.forBlock(icon, this);
	}

	@Override
	public int tickRate(World world)
	{
		return 30;
	}

	/* 
	public void onBlockClicked(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        func_320_h(world, i, j, k);
        super.onBlockClicked(world, i, j, k, entityplayer);
    }

    public void onEntityWalking(World world, int i, int j, int k, Entity entity)
    {
        func_320_h(world, i, j, k);
        super.onEntityWalking(world, i, j, k, entity);
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        func_320_h(world, i, j, k);
        return super.blockActivated(world, i, j, k, entityplayer);
    }
    */

	@Override
	public void updateTick(World world, int i, int j, int k, Random random) {}

	@Override
	public int idDropped(int i, Random random, int j)
	{
		return ModOres.oreUranite.blockID;
	}

	@Override
	public int quantityDropped(Random random)
	{
		return 1;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random random)
	{
		this.sparkle(world, i, j, k);
	}

	// From: BlockRedstoneOre
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