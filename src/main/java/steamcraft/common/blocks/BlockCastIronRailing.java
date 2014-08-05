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

import static net.minecraftforge.common.util.ForgeDirection.EAST;
import static net.minecraftforge.common.util.ForgeDirection.NORTH;
import static net.minecraftforge.common.util.ForgeDirection.SOUTH;
import static net.minecraftforge.common.util.ForgeDirection.WEST;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import steamcraft.client.lib.RenderIDs;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCastIronRailing extends BaseBlock
{
	@SideOnly(Side.CLIENT)
	public IIcon icon, blockIcon;

	public BlockCastIronRailing(Material mat)
	{
		super(mat);
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether
	 * or not to render the shared face of two adjacent blocks and also whether
	 * the player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False
	 * (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType()
	{
		return RenderIDs.blockCastIronRailingRI;
	}

	/**
	 * Returns true if the given side of this block type should be rendered, if
	 * the adjacent block is at the given coordinates. Args: blockAccess, x, y,
	 * z, side
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
	{
		return p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_) == this ? false : super.shouldSideBeRendered(p_149646_1_, p_149646_2_,
				p_149646_3_, p_149646_4_, p_149646_5_);
	}

	/**
	 * Adds all intersecting collision boxes to a list. (Be sure to only add
	 * boxes to the list if they intersect the mask.) Parameters: World, X, Y,
	 * Z, mask, list, colliding entity
	 */
	@Override
	public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_,
			List p_149743_6_, Entity p_149743_7_)
	{
		boolean flag = canPaneConnectTo(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_ - 1, NORTH);
		boolean flag1 = canPaneConnectTo(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_ + 1, SOUTH);
		boolean flag2 = canPaneConnectTo(p_149743_1_, p_149743_2_ - 1, p_149743_3_, p_149743_4_, WEST);
		boolean flag3 = canPaneConnectTo(p_149743_1_, p_149743_2_ + 1, p_149743_3_, p_149743_4_, EAST);

		if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1))
		{
			if (flag2 && !flag3)
			{
				setBlockBounds(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
				super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
			}
			else if (!flag2 && flag3)
			{
				setBlockBounds(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
				super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
			}
		}
		else
		{
			setBlockBounds(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
			super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
		}

		if ((!flag || !flag1) && (flag2 || flag3 || flag || flag1))
		{
			if (flag && !flag1)
			{
				setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
				super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
			}
			else if (!flag && flag1)
			{
				setBlockBounds(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
				super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
			}
		}
		else
		{
			setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
		}
	}

	/**
	 * Sets the block's bounds for rendering it as an item
	 */
	@Override
	public void setBlockBoundsForItemRender()
	{
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
	{
		float f = 0.4375F;
		float f1 = 0.5625F;
		float f2 = 0.4375F;
		float f3 = 0.5625F;
		boolean flag = canPaneConnectTo(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_ - 1, NORTH);
		boolean flag1 = canPaneConnectTo(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_ + 1, SOUTH);
		boolean flag2 = canPaneConnectTo(p_149719_1_, p_149719_2_ - 1, p_149719_3_, p_149719_4_, WEST);
		boolean flag3 = canPaneConnectTo(p_149719_1_, p_149719_2_ + 1, p_149719_3_, p_149719_4_, EAST);

		if ((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1))
		{
			if (flag2 && !flag3)
				f = 0.0F;
			else if (!flag2 && flag3)
				f1 = 1.0F;
		}
		else
		{
			f = 0.0F;
			f1 = 1.0F;
		}

		if ((!flag || !flag1) && (flag2 || flag3 || flag || flag1))
		{
			if (flag && !flag1)
				f2 = 0.0F;
			else if (!flag && flag1)
				f3 = 1.0F;
		}
		else
		{
			f2 = 0.0F;
			f3 = 1.0F;
		}

		setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
	}

	@SideOnly(Side.CLIENT)
	public IIcon func_150097_e()
	{
		return blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		blockIcon = p_149651_1_.registerIcon(LibInfo.PREFIX + "blockCastIronRailing");
		icon = p_149651_1_.registerIcon(LibInfo.PREFIX + "/metal/blockCastIron");
	}

	public boolean canPaneConnectTo(IBlockAccess world, int x, int y, int z, ForgeDirection dir)
	{
		return world.isSideSolid(x, y, z, dir.getOpposite(), false) || (world.getBlock(x, y, z) instanceof BlockCastIronGate)
				|| (world.getBlock(x, y, z) instanceof BlockCastIronFence) || (world.getBlock(x, y, z) instanceof BlockCastIronRailing);
	}
}
