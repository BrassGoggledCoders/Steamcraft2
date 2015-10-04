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
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockPlating extends BaseBlock
{
	int metadata;
	Block block;
	float width = 0.125F;

	public BlockPlating(Block block, int metadata)
	{
		super(block.getMaterial());
		this.block = block;
		this.metadata = metadata;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, width, 1.0F);
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		int l = world.getBlockMetadata(x, y, z);
		float f = 0.125F;
		return AxisAlignedBB.getBoundingBox(x + this.minX, y + this.minY, z + this.minZ, x + this.maxX, y + (l * f), z + this.maxZ);
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
	 * Sets the block's bounds for rendering it as an item
	 */
	@Override
	public void setBlockBoundsForItemRender()
	{
		this.setBoundsFromMetadata(0);
	}

	/**
	 * Updates the blocks bounds based on its current state. Args: world, x, y,
	 * z
	 */
	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		this.setBoundsFromMetadata(world.getBlockMetadata(x, y, z));
	}

	protected void setBoundsFromMetadata(int meta)
	{
		switch (meta)
		{
		case 1:
			this.setBlockBounds(1.0F, 1.0F, width, 0.0F, 0.0F, 0.0F);
		case 2:
			this.setBlockBounds(1.0F, 1.0F, width, 0.0F, 0.0F, 0.0F);
		case 3:
			this.setBlockBounds(1.0F, 1.0F, width, 1.0F, 0.0F, 0.0F);
		case 4:
			this.setBlockBounds(1.0F, 1.0F, width, 0.0F, 0.0F, 0.0F);
		case 5:
			this.setBlockBounds(1.0F, 1.0F, width, 1.0F, 0.0F, 0.0F);
		case 6:
			this.setBlockBounds(1.0F, 1.0F, width, 0.0F, 1.0F, 0.0F);
		default:
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, width, 1.0F);
		}
	}

	/**
	 * Checks to see if its valid to put this block at the specified
	 * coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
	{
		// Block block = p_149742_1_.getBlock(p_149742_2_, p_149742_3_ - 1,
		// p_149742_4_);
		// return block.isOpaqueCube();
		return true;
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which
	 * neighbor changed (coordinates passed are their own) Args: x, y, z,
	 * neighbor Block
	 */
	@Override
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
	{
		this.checkCanStay(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_);
	}

	private boolean checkCanStay(World p_150155_1_, int p_150155_2_, int p_150155_3_, int p_150155_4_)
	{
		if (!this.canPlaceBlockAt(p_150155_1_, p_150155_2_, p_150155_3_, p_150155_4_))
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
	 * Returns true if the given side of this block type should be rendered, if
	 * the adjacent block is at the given coordinates. Args: blockAccess, x, y,
	 * z, side
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess p_149646_1_, int p_149646_2_, int p_149646_3_, int p_149646_4_, int p_149646_5_)
	{
		return (p_149646_5_ == 1) || super.shouldSideBeRendered(p_149646_1_, p_149646_2_, p_149646_3_, p_149646_4_, p_149646_5_);
	}

	@Override
	public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		System.out.print(side);
		world.setBlockMetadataWithNotify(x, y, z, side, 2);
		return metadata;
	}
}
