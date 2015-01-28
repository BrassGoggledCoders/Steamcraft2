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

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLeafPile extends BaseBlock
{
	public BlockLeafPile()
	{
		super(Material.leaves);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
		this.setTickRandomly(true);
		this.func_150154_b(0);
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been cleared to be reused)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World p_149668_1_, int p_149668_2_, int p_149668_3_, int p_149668_4_)
	{
		int l = p_149668_1_.getBlockMetadata(p_149668_2_, p_149668_3_, p_149668_4_) & 7;
		float f = 0.125F;
		return AxisAlignedBB.getBoundingBox(p_149668_2_ + this.minX, p_149668_3_ + this.minY, p_149668_4_ + this.minZ, p_149668_2_ + this.maxX, p_149668_3_
				+ (l
				* f), p_149668_4_ + this.maxZ);
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

	/**
	 * Sets the block's bounds for rendering it as an item
	 */
	@Override
	public void setBlockBoundsForItemRender()
	{
		this.func_150154_b(0);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y, z
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess p_149719_1_, int p_149719_2_, int p_149719_3_, int p_149719_4_)
	{
		this.func_150154_b(p_149719_1_.getBlockMetadata(p_149719_2_, p_149719_3_, p_149719_4_));
	}

	protected void func_150154_b(int p_150154_1_)
	{
		int j = p_150154_1_ & 7;
		float f = (2 * (1 + j)) / 16.0F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
	}

	/**
	 * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
	{
		Block block = p_149742_1_.getBlock(p_149742_2_, p_149742_3_ - 1, p_149742_4_);
		return block.isOpaqueCube();
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are their own) Args: x, y, z, neighbor
	 * Block
	 */
	@Override
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
	{
		this.func_150155_m(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_);
	}

	private boolean func_150155_m(World p_150155_1_, int p_150155_2_, int p_150155_3_, int p_150155_4_)
	{
		if(!this.canPlaceBlockAt(p_150155_1_, p_150155_2_, p_150155_3_, p_150155_4_))
		{
			p_150155_1_.setBlockToAir(p_150155_2_, p_150155_3_, p_150155_4_);
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Called when the player destroys a block with an item that can harvest it. (i, j, k) are the coordinates of the block and l is the block's subtype/damage.
	 */
	@Override
	public void harvestBlock(World p_149636_1_, EntityPlayer p_149636_2_, int p_149636_3_, int p_149636_4_, int p_149636_5_, int p_149636_6_)
	{
		super.harvestBlock(p_149636_1_, p_149636_2_, p_149636_3_, p_149636_4_, p_149636_5_, p_149636_6_);
		p_149636_1_.setBlockToAir(p_149636_3_, p_149636_4_, p_149636_5_);
	}

	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random p_149745_1_)
	{
		return 1;
	}

	/**
	 * Ticks the block if it's been scheduled
	 */
	@Override
	public void updateTick(World p_149674_1_, int p_149674_2_, int p_149674_3_, int p_149674_4_, Random p_149674_5_)
	{
		if(p_149674_1_.getSavedLightValue(EnumSkyBlock.Block, p_149674_2_, p_149674_3_, p_149674_4_) > 11)
		{
			p_149674_1_.setBlockToAir(p_149674_2_, p_149674_3_, p_149674_4_);
		}
	}

	/**
	 * Returns true if the given side of this block type should be rendered, if the adjacent block is at the given coordinates. Args: blockAccess, x, y, z, side
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
	{
		return p_149646_5_ == 1 ? true : super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
	}

	/**
	 * Determines if a new block can be replace the space occupied by this one, Used in the player's placement code to make the block act like water, and lava.
	 * 
	 * @param world
	 *            The current world
	 * @param x
	 *            X Position
	 * @param y
	 *            Y position
	 * @param z
	 *            Z position
	 * @return True if the block is replaceable by another block
	 */
	@Override
	public boolean isReplaceable(IBlockAccess world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		return meta >= 7 ? false : this.blockMaterial.isReplaceable();
	}
}
