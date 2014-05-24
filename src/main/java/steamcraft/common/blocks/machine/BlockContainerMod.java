/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ 23-May-2014
 */
package steamcraft.common.blocks.machine;

import java.util.Random;

import boilerplate.steamcraft.api.machines.IMachine;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockContainerMod.
 */
public abstract class BlockContainerMod extends BlockContainer
{
	
	/** The keep inventory. */
	protected static boolean keepInventory = true;

	/** The random. */
	protected final Random random = new Random();

	/** The owner. */
	public static String owner = "[SC2]";

	/**
	 * Instantiates a new block container mod.
	 *
	 * @param mat the mat
	 */
	protected BlockContainerMod(final Material mat)
	{
		super(mat);
		setCreativeTab(Steamcraft.tabSC2);
		setHardness(5.0F);
	}

	/**
	 * Gets the owner.
	 *
	 * @return the owner
	 */
	public String getOwner()
	{
		return owner;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#onBlockPlacedBy(net.minecraft.world.World, int, int, int, net.minecraft.entity.EntityLivingBase, net.minecraft.item.ItemStack)
	 */
	@Override
	public void onBlockPlacedBy(final World world, final int x, final int y,
			final int z, final EntityLivingBase entityLiving, final ItemStack is)
	{
		super.onBlockPlacedBy(world, x, y, z, entityLiving, is);

		if (entityLiving instanceof EntityPlayer)
		{
			owner = owner
					+ ((EntityPlayer) entityLiving).getCommandSenderName();
		}
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.BlockContainer#breakBlock(net.minecraft.world.World, int, int, int, net.minecraft.block.Block, int)
	 */
	@Override
	public void breakBlock(final World world, final int x, final int y,
			final int z, final Block block, final int metadata)
	{
		super.breakBlock(world, x, y, z, block, metadata);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#getLightValue(net.minecraft.world.IBlockAccess, int, int, int)
	 */
	@Override
	public int getLightValue(final IBlockAccess world, final int x,
			final int y, final int z)
	{
		final TileEntity te = world.getTileEntity(x, y, z);

		if (te instanceof IMachine && ((IMachine) te).isActive())
		{
			return super.getLightValue(world, x, y, z) + 8;
		}

		return super.getLightValue(world, x, y, z);
	}
}
