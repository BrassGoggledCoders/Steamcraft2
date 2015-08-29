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

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.client.lib.RenderIDs;
import steamcraft.client.renderers.tile.TileHatchRenderer.TileHatch;
import steamcraft.common.Steamcraft;
import steamcraft.common.blocks.machines.BaseContainerBlock;

/**
 * @author Surseance
 *
 */
public class BlockHatch extends BaseContainerBlock
{
	public BlockHatch()
	{
		super(Material.anvil);
		float f = 0.5F;
		float f1 = 1.0F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
		this.setBlockName("blockHatch");
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	public TileEntity createNewTileEntity(final World world, final int metadata)
	{
		return new TileHatch();
	}

	@Override
	public int getRenderType()
	{
		return RenderIDs.blockHatchRI;
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
	public boolean canCreatureSpawn(final EnumCreatureType type, final IBlockAccess world, final int x, final int y, final int z)
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean addDestroyEffects(final World world, final int x, final int y, final int z, final int metadata,
			final EffectRenderer effectRenderer)
	{
		return super.addDestroyEffects(world, x, y, z, metadata, effectRenderer);
	}

	@Override
	public boolean getBlocksMovement(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_)
	{
		return !isOpen(p_149655_1_.getBlockMetadata(p_149655_2_, p_149655_3_, p_149655_4_));
	}

	/**
	 * Returns the bounding box of the wired rectangular prism to render.
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World p_149633_1_, int p_149633_2_, int p_149633_3_, int p_149633_4_)
	{
		this.setBlockBoundsBasedOnState(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
		return super.getSelectedBoundingBoxFromPool(p_149633_1_, p_149633_2_, p_149633_3_, p_149633_4_);
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		this.setBlockBoundsBasedOnState(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
		return super.getCollisionBoundingBoxFromPool(p_149668_1_, p_149668_2_, p_149668_3_, p_149668_4_);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
	{
		this.changeBounds(p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_));
	}

	/**
	 * Sets the block's bounds for rendering it as an item
	 */
	@Override
	public void setBlockBoundsForItemRender()
	{
		float f = 0.1875F;
		this.setBlockBounds(0.0F, 0.5F - (f / 2.0F), 0.0F, 1.0F, 0.5F + (f / 2.0F), 1.0F);
	}

	public void changeBounds(int p_150117_1_)
	{
		float f = 0.1875F;

		if ((p_150117_1_ & 8) != 0)
		{
			this.setBlockBounds(0.0F, 1.0F - f, 0.0F, 1.0F, 1.0F, 1.0F);
		}
		else
		{
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
		}

		if (isOpen(p_150117_1_))
		{
			if ((p_150117_1_ & 3) == 0)
			{
				this.setBlockBounds(0.0F, 0.0F, 1.0F - f, 1.0F, 1.0F, 1.0F);
			}

			if ((p_150117_1_ & 3) == 1)
			{
				this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f);
			}

			if ((p_150117_1_ & 3) == 2)
			{
				this.setBlockBounds(1.0F - f, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			}

			if ((p_150117_1_ & 3) == 3)
			{
				this.setBlockBounds(0.0F, 0.0F, 0.0F, f, 1.0F, 1.0F);
			}
		}
	}

	public void func_150120_a(World p_150120_1_, int p_150120_2_, int p_150120_3_, int p_150120_4_, boolean p_150120_5_)
	{
		int l = p_150120_1_.getBlockMetadata(p_150120_2_, p_150120_3_, p_150120_4_);
		boolean flag1 = (l & 4) > 0;

		if (flag1 != p_150120_5_)
		{
			p_150120_1_.setBlockMetadataWithNotify(p_150120_2_, p_150120_3_, p_150120_4_, l ^ 4, 2);
			p_150120_1_.playAuxSFXAtEntity((EntityPlayer) null, 1003, p_150120_2_, p_150120_3_, p_150120_4_, 0);
		}
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which
	 * neighbor changed (coordinates passed are their own) Args: x, y, z,
	 * neighbor Block
	 */
	@Override
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
	{
		if (!p_149695_1_.isRemote)
		{
			int l = p_149695_1_.getBlockMetadata(p_149695_2_, p_149695_3_, p_149695_4_);
			int i1 = p_149695_2_;
			int j1 = p_149695_4_;

			if ((l & 3) == 0)
			{
				j1 = p_149695_4_ + 1;
			}

			if ((l & 3) == 1)
			{
				--j1;
			}

			if ((l & 3) == 2)
			{
				i1 = p_149695_2_ + 1;
			}

			if ((l & 3) == 3)
			{
				--i1;
			}

			boolean flag = p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_, p_149695_4_);

			if (flag || p_149695_5_.canProvidePower())
			{
				this.func_150120_a(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_, flag);
			}
		}
	}

	/**
	 * Ray traces through the blocks collision from start vector to end vector
	 * returning a ray trace hit. Args: world, x, y, z, startVec, endVec
	 */
	@Override
	public MovingObjectPosition collisionRayTrace(World p_149731_1_, int p_149731_2_, int p_149731_3_, int p_149731_4_, Vec3 p_149731_5_,
			Vec3 p_149731_6_)
	{
		this.setBlockBoundsBasedOnState(p_149731_1_, p_149731_2_, p_149731_3_, p_149731_4_);
		return super.collisionRayTrace(p_149731_1_, p_149731_2_, p_149731_3_, p_149731_4_, p_149731_5_, p_149731_6_);
	}

	/**
	 * Called when a block is placed using its ItemBlock. Args: World, X, Y, Z,
	 * side, hitX, hitY, hitZ, block metadata
	 */
	@Override
	public int onBlockPlaced(World p_149660_1_, int p_149660_2_, int p_149660_3_, int p_149660_4_, int p_149660_5_, float p_149660_6_,
			float p_149660_7_, float p_149660_8_, int p_149660_9_)
	{
		int j1 = 0;

		if (p_149660_5_ == 2)
		{
			j1 = 0;
		}

		if (p_149660_5_ == 3)
		{
			j1 = 1;
		}

		if (p_149660_5_ == 4)
		{
			j1 = 2;
		}

		if (p_149660_5_ == 5)
		{
			j1 = 3;
		}

		if ((p_149660_5_ != 1) && (p_149660_5_ != 0) && (p_149660_7_ > 0.5F))
		{
			j1 |= 8;
		}

		return j1;
	}

	/**
	 * checks to see if you can place this block can be placed on that side of a
	 * block: BlockLever overrides
	 */
	@Override
	public boolean canPlaceBlockOnSide(World p_149707_1_, int p_149707_2_, int p_149707_3_, int p_149707_4_, int p_149707_5_)
	{
		return true;
	}

	public static boolean isOpen(int p_150118_0_)
	{
		return (p_150118_0_ & 4) != 0;
	}
}
