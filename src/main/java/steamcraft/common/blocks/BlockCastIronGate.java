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
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitBlocks;

/**
 * @author Surseance
 * 
 */
public class BlockCastIronGate extends BlockDirectional
{
	public BlockCastIronGate()
	{
		super(Material.iron);
		this.setHardness(7.0F);
		this.setResistance(20.0F);
		this.setStepSound(Block.soundTypeMetal);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int side, final int metadata)
	{
		return InitBlocks.blockCastIronFence.getBlockTextureFromSide(side);
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
	{
		return !p_149742_1_.getBlock(p_149742_2_, p_149742_3_ - 1, p_149742_4_).getMaterial().isSolid() ? false : super.canPlaceBlockAt(p_149742_1_,
				p_149742_2_, p_149742_3_, p_149742_4_);
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been cleared to be reused)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		int l = p_149668_1_.getBlockMetadata(p_149668_2_, p_149668_3_, p_149668_4_);
		/**
		 * Returns if the fence gate is open according to its metadata.
		 */
		return isFenceGateOpen(l) ? null : ((l != 2) && (l != 0) ? AxisAlignedBB.getBoundingBox(p_149668_2_ + 0.375F, p_149668_3_, p_149668_4_,
				p_149668_2_ + 0.625F, p_149668_3_ + 1.5F, p_149668_4_ + 1) : AxisAlignedBB.getBoundingBox(p_149668_2_, p_149668_3_, p_149668_4_ + 0.375F,
				p_149668_2_ + 1, p_149668_3_ + 1.5F, p_149668_4_ + 0.625F));
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
	{
		int l = getDirection(p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_));

		if((l != 2) && (l != 0))
		{
			this.setBlockBounds(0.375F, 0.0F, 0.0F, 0.625F, 1.0F, 1.0F);
		}
		else
		{
			this.setBlockBounds(0.0F, 0.0F, 0.375F, 1.0F, 1.0F, 0.625F);
		}
	}

	/**
	 * Is this block (a) opaque and (b) a full 1m cube? This determines whether or not to render the shared face of two adjacent blocks and also whether the
	 * player can attach torches, redstone wire, etc to this block.
	 */
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean getBlocksMovement(IBlockAccess p_149655_1_, int p_149655_2_, int p_149655_3_, int p_149655_4_)
	{
		/**
		 * Returns if the fence gate is open according to its metadata.
		 */
		return isFenceGateOpen(p_149655_1_.getBlockMetadata(p_149655_2_, p_149655_3_, p_149655_4_));
	}

	/**
	 * The type of render function that is called for this block
	 */
	@Override
	public int getRenderType()
	{
		return 21;
	}

	/**
	 * Called when the block is placed in the world.
	 */
	@Override
	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
	{
		int l = (MathHelper.floor_double(((p_149689_5_.rotationYaw * 4.0F) / 360.0F) + 0.5D) & 3) % 4;
		p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, l, 2);
	}

	/**
	 * Called upon block activation (right click on the block.)
	 */
	@Override
	public boolean onBlockActivated(World p_149727_1_, int p_149727_2_, int p_149727_3_, int p_149727_4_, EntityPlayer p_149727_5_, int p_149727_6_,
			float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		int i1 = p_149727_1_.getBlockMetadata(p_149727_2_, p_149727_3_, p_149727_4_);

		if(isFenceGateOpen(i1))
		{
			p_149727_1_.setBlockMetadataWithNotify(p_149727_2_, p_149727_3_, p_149727_4_, i1 & -5, 2);
		}
		else
		{
			int j1 = (MathHelper.floor_double(((p_149727_5_.rotationYaw * 4.0F) / 360.0F) + 0.5D) & 3) % 4;
			int k1 = getDirection(i1);

			if(k1 == ((j1 + 2) % 4))
			{
				i1 = j1;
			}

			p_149727_1_.setBlockMetadataWithNotify(p_149727_2_, p_149727_3_, p_149727_4_, i1 | 4, 2);
		}

		p_149727_1_.playAuxSFXAtEntity(p_149727_5_, 1003, p_149727_2_, p_149727_3_, p_149727_4_, 0);
		return true;
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are their own) Args: x, y, z, neighbor
	 * Block
	 */
	@Override
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
	{
		if(!p_149695_1_.isRemote)
		{
			int l = p_149695_1_.getBlockMetadata(p_149695_2_, p_149695_3_, p_149695_4_);
			boolean flag = p_149695_1_.isBlockIndirectlyGettingPowered(p_149695_2_, p_149695_3_, p_149695_4_);

			if(flag || p_149695_5_.canProvidePower())
			{
				if(flag && !isFenceGateOpen(l))
				{
					p_149695_1_.setBlockMetadataWithNotify(p_149695_2_, p_149695_3_, p_149695_4_, l | 4, 2);
					p_149695_1_.playAuxSFXAtEntity((EntityPlayer) null, 1003, p_149695_2_, p_149695_3_, p_149695_4_, 0);
				}
				else if(!flag && isFenceGateOpen(l))
				{
					p_149695_1_.setBlockMetadataWithNotify(p_149695_2_, p_149695_3_, p_149695_4_, l & -5, 2);
					p_149695_1_.playAuxSFXAtEntity((EntityPlayer) null, 1003, p_149695_2_, p_149695_3_, p_149695_4_, 0);
				}
			}
		}
	}

	/**
	 * Returns if the fence gate is open according to its metadata.
	 */
	public static boolean isFenceGateOpen(int p_149896_0_)
	{
		return (p_149896_0_ & 4) != 0;
	}

	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given coordinates. Args: blockAccess, x, y, z, side
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
	{
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister p_149651_1_)
	{
	}
}
