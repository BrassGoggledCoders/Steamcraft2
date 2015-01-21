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
import steamcraft.common.lib.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 * 
 */
public class BlockCastIronRailing extends BaseBlock
{
	@SideOnly(Side.CLIENT)
	public IIcon icon;

	public BlockCastIronRailing(Material mat)
	{
		super(mat);
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return RenderIDs.blockCastIronRailingRI;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
	{
		return p_149646_1_.getBlock(p_149646_2_, p_149646_3_, p_149646_4_) == this ? false : super.shouldSideBeRendered(p_149646_1_, p_149646_2_,
				p_149646_3_, p_149646_4_, p_149646_5_);
	}

	@SuppressWarnings("all")
	@Override
	public void addCollisionBoxesToList(World p_149743_1_, int p_149743_2_, int p_149743_3_, int p_149743_4_, AxisAlignedBB p_149743_5_,
			List p_149743_6_, Entity p_149743_7_)
	{
		boolean flag = this.canPaneConnectTo(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_ - 1, NORTH);
		boolean flag1 = this.canPaneConnectTo(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_ + 1, SOUTH);
		boolean flag2 = this.canPaneConnectTo(p_149743_1_, p_149743_2_ - 1, p_149743_3_, p_149743_4_, WEST);
		boolean flag3 = this.canPaneConnectTo(p_149743_1_, p_149743_2_ + 1, p_149743_3_, p_149743_4_, EAST);

		if((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1))
		{
			if(flag2 && !flag3)
			{
				this.setBlockBounds(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
				super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
			}
			else if(!flag2 && flag3)
			{
				this.setBlockBounds(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
				super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
			}
		}
		else
		{
			this.setBlockBounds(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
			super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
		}

		if((!flag || !flag1) && (flag2 || flag3 || flag || flag1))
		{
			if(flag && !flag1)
			{
				this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
				super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
			}
			else if(!flag && flag1)
			{
				this.setBlockBounds(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
				super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
			}
		}
		else
		{
			this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
			super.addCollisionBoxesToList(p_149743_1_, p_149743_2_, p_149743_3_, p_149743_4_, p_149743_5_, p_149743_6_, p_149743_7_);
		}
	}

	@Override
	public void setBlockBoundsForItemRender()
	{
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
	{
		float f = 0.4375F;
		float f1 = 0.5625F;
		float f2 = 0.4375F;
		float f3 = 0.5625F;
		boolean flag = this.canPaneConnectTo(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_ - 1, NORTH);
		boolean flag1 = this.canPaneConnectTo(p_149719_1_, p_149719_2_, p_149719_3_, p_149719_4_ + 1, SOUTH);
		boolean flag2 = this.canPaneConnectTo(p_149719_1_, p_149719_2_ - 1, p_149719_3_, p_149719_4_, WEST);
		boolean flag3 = this.canPaneConnectTo(p_149719_1_, p_149719_2_ + 1, p_149719_3_, p_149719_4_, EAST);

		if((!flag2 || !flag3) && (flag2 || flag3 || flag || flag1))
		{
			if(flag2 && !flag3)
				f = 0.0F;
			else if(!flag2 && flag3)
				f1 = 1.0F;
		}
		else
		{
			f = 0.0F;
			f1 = 1.0F;
		}

		if((!flag || !flag1) && (flag2 || flag3 || flag || flag1))
		{
			if(flag && !flag1)
				f2 = 0.0F;
			else if(!flag && flag1)
				f3 = 1.0F;
		}
		else
		{
			f2 = 0.0F;
			f3 = 1.0F;
		}

		this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
		this.blockIcon = p_149651_1_.registerIcon(ModInfo.PREFIX + "blockCastIronRailing");
		this.icon = p_149651_1_.registerIcon(ModInfo.PREFIX + "/metal/blockCastIron");
	}

	public boolean canPaneConnectTo(IBlockAccess world, int x, int y, int z, ForgeDirection dir)
	{
		return world.isSideSolid(x, y, z, dir.getOpposite(), false) || (world.getBlock(x, y, z) instanceof BlockCastIronGate)
				|| (world.getBlock(x, y, z) instanceof BlockCastIronFence) || (world.getBlock(x, y, z) instanceof BlockCastIronRailing);
	}
}
