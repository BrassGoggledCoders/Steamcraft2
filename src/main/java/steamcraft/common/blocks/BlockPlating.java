
package steamcraft.common.blocks;

import java.util.List;

import boilerplate.common.baseclasses.blocks.BaseMetadataBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitBlocks;

public class BlockPlating extends BaseMetadataBlock
{
	Block block;

	public BlockPlating(Block block)
	{
		super(block.getMaterial());
		this.setCreativeTab(Steamcraft.tabSC2);
		this.block = block;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(final Item item, final CreativeTabs tab, final List l)
	{
		if (this.block == InitBlocks.blockMetal)
		{
			for (int var4 = 0; var4 < 8; ++var4)
				l.add(new ItemStack(InitBlocks.blockMetalPlate, 1, var4));
		}
		else if (this.block == InitBlocks.blockMossyMetal)
		{
			for (int var4 = 0; var4 < 8; ++var4)
				l.add(new ItemStack(InitBlocks.blockMossyMetalPlate, 1, var4));
		}
		if (this.block == InitBlocks.blockRustyMetal)
		{
			for (int var4 = 0; var4 < 8; ++var4)
				l.add(new ItemStack(InitBlocks.blockRustyMetalPlate, 1, var4));
		}
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta)
	{
		return this.block.getIcon(side, meta);
	}

	/**
	 * Returns a bounding box from the pool of bounding boxes (this means this
	 * box can change after the pool has been cleared to be reused)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return Blocks.carpet.getCollisionBoundingBoxFromPool(world, x, y, z);
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
		this.func_150089_b(0);
	}

	protected void func_150089_b(int p_150089_1_)
	{
		byte b0 = 0;
		float f = (1 * (1 + b0)) / 16.0F;
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
	}

	/**
	 * Checks to see if its valid to put this block at the specified
	 * coordinates. Args: world, x, y, z
	 */
	@Override
	public boolean canPlaceBlockAt(World p_149742_1_, int p_149742_2_, int p_149742_3_, int p_149742_4_)
	{
		return super.canPlaceBlockAt(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_)
				&& this.canBlockStay(p_149742_1_, p_149742_2_, p_149742_3_, p_149742_4_);
	}

	/**
	 * Lets the block know when one of its neighbor changes. Doesn't know which
	 * neighbor changed (coordinates passed are their own) Args: x, y, z,
	 * neighbor Block
	 */
	@Override
	public void onNeighborBlockChange(World p_149695_1_, int p_149695_2_, int p_149695_3_, int p_149695_4_, Block p_149695_5_)
	{
		this.func_150090_e(p_149695_1_, p_149695_2_, p_149695_3_, p_149695_4_);
	}

	private boolean func_150090_e(World p_150090_1_, int p_150090_2_, int p_150090_3_, int p_150090_4_)
	{
		if (!this.canBlockStay(p_150090_1_, p_150090_2_, p_150090_3_, p_150090_4_))
		{
			this.dropBlockAsItem(p_150090_1_, p_150090_2_, p_150090_3_, p_150090_4_,
					p_150090_1_.getBlockMetadata(p_150090_2_, p_150090_3_, p_150090_4_), 0);
			p_150090_1_.setBlockToAir(p_150090_2_, p_150090_3_, p_150090_4_);
			return false;
		}
		else
		{
			return true;
		}
	}

	/**
	 * Can this block stay at this position. Similar to canPlaceBlockAt except
	 * gets checked often with plants.
	 */
	@Override
	public boolean canBlockStay(World p_149718_1_, int p_149718_2_, int p_149718_3_, int p_149718_4_)
	{
		return !p_149718_1_.isAirBlock(p_149718_2_, p_149718_3_ - 1, p_149718_4_);
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

	/**
	 * Determines the damage on the item the block drops. Used in cloth and
	 * wood.
	 */
	@Override
	public int damageDropped(int p_149692_1_)
	{
		return p_149692_1_;
	}
}
