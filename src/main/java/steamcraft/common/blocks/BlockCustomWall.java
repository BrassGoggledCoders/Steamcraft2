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
package steamcraft.common.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWall;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitBlocks;

/**
 * @author warlordjones
 *
 */
public class BlockCustomWall extends BlockWall
{
	Block block;
	int metadata;
	boolean stonebrick;

	public BlockCustomWall(Block block, int meta, boolean stonebrick)
	{
		super(block);
		this.block = block;
		this.metadata = meta;
		this.stonebrick = true;
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.block.getIcon(0, this.metadata);
	}

	/**
	 * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		list.add(new ItemStack(item, 1, 0));
	}

	/**
	 * Return whether an adjacent block can connect to a wall.
	 */
	@Override
	public boolean canConnectWallTo(IBlockAccess world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		if(this.stonebrick)
		{
			if((block == InitBlocks.blockStonebrickWall) || (block == InitBlocks.blockStonebrickWallMossy) || (block == InitBlocks.blockStonebrickWallCracked)
					|| (block == InitBlocks.blockStonebrickWallChiseled))
				return true;
		}
		return super.canConnectWallTo(world, x, y, z);
	}
}
