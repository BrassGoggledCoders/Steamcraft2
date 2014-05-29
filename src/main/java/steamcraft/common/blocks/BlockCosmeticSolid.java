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
 * File created @ [3/14/14, 20:57]
 */
package steamcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.Utils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockCosmeticSolid.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class BlockCosmeticSolid extends Block
{
	
	/** The icon. */
	private final IIcon[] icon = new IIcon[5];

	/** The powered. */
	private boolean powered;

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#getIcon(int, int)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(final int side, final int metadata)
	{
		if (metadata == 8) // This is for the brass wood. It's kinda messed up.
							// Maybe a separate file would do?
		{
			final int i = metadata & 11;
			// int j = metadata & 3;

			return i == 0 && (side == 1 || side == 0) ? icon[0] : (i == 4
					&& (side == 5 || side == 4) ? icon[0] : (i == 8
					&& (side == 2 || side == 3) ? icon[0] : icon[0]));
		}

		return icon[metadata];
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#registerBlockIcons(net.minecraft.client.renderer.texture.IIconRegister)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister ir)
	{
		icon[0] = ir.registerIcon(LibInfo.PREFIX + "blockBrassLog");
		icon[1] = ir.registerIcon(LibInfo.PREFIX + "blockBrassLogTop");
		icon[2] = ir.registerIcon(LibInfo.PREFIX + "blockBrassLeaves");
		icon[3] = ir.registerIcon(LibInfo.PREFIX + "blockLampOff");
		icon[4] = ir.registerIcon(LibInfo.PREFIX + "blockLampOn");
	}

	/**
	 * Instantiates a new block cosmetic solid.
	 */
	public BlockCosmeticSolid()
	{
		super(Material.iron);
		setHardness(3.0F);
		setResistance(10.0F);
		setStepSound(Block.soundTypeMetal);
		// TODO:this.setUnlocalizedName("blockCosmeticSolid");
		setTickRandomly(true);
		setCreativeTab(Steamcraft.tabSC2);

		if (powered)
		{
			setLightLevel(0.98F);
		}
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#damageDropped(int)
	 */
	@Override
	public int damageDropped(final int id)
	{
		return id;
	}

	// TODO: Drop and sub item methods removed

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#onEntityCollidedWithBlock(net.minecraft.world.World, int, int, int, net.minecraft.entity.Entity)
	 */
	@Override
	public void onEntityCollidedWithBlock(final World world, final int x,
			final int y, final int z, final Entity entity)
	{
		if ((world.getBlock(x, y, z) == this)
				&& (world.getBlockMetadata(x, y, z) == 6))
		{
			entity.attackEntityFrom(DamageSource.magic, 1);
		}
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#randomDisplayTick(net.minecraft.world.World, int, int, int, java.util.Random)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(final World world, final int x, final int y,
			final int z, final Random random)
	{
		if ((world.getBlock(x, y, z) == this)
				&& (world.getBlockMetadata(x, y, z) == 6))
		{
			Utils.sparkle(world, x, y, z, "reddust");
		}
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#isLeaves(net.minecraft.world.IBlockAccess, int, int, int)
	 */
	@Override
	public boolean isLeaves(final IBlockAccess world, final int x, final int y,
			final int z)
	{
		if ((world.getBlock(x, y, z) == this)
				&& (world.getBlockMetadata(x, y, z) == 10))
		{
			return blockMaterial == Material.leaves;
		}
		else
		{
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#onBlockPlaced(net.minecraft.world.World, int, int, int, int, float, float, float, int)
	 */
	@Override
	// Something is wrong with the functionality of this method. You'll see when
	// you place a Brass Log.
	public int onBlockPlaced(final World world, final int x, final int y,
			final int z, final int side, final float hitX, final float hitY,
			final float hitZ, final int metadata)
	{
		if ((world.getBlock(x, y, z) == this)
				&& (world.getBlockMetadata(x, y, z) == 8))
		{
			final int meta = metadata & 3;
			byte byte0 = 0;

			switch (side)
			{
			case 0:
			case 1:
				byte0 = 0;
				break;
			case 2:
			case 3:
				byte0 = 8;
				break;
			case 4:
			case 5:
				byte0 = 4;
			}

			return meta | byte0;
		}
		else
		{
			return metadata;
		}
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#breakBlock(net.minecraft.world.World, int, int, int, net.minecraft.block.Block, int)
	 */
	@Override
	public void breakBlock(final World world, final int x, final int y,
			final int z, Block block, final int meta)
	{
		if ((world.getBlock(x, y, z) == this)
				&& (world.getBlockMetadata(x, y, z) == 8))
		{
			final byte byte0 = 4;
			final int factor = byte0 + 1;

			if (world.checkChunksExist(x - factor, y - factor, z - factor, x
					+ factor, y + factor, z + factor))
			{
				for (int ix = -byte0; ix <= byte0; ++ix)
				{
					for (int iy = -byte0; iy <= byte0; ++iy)
					{
						for (int iz = -byte0; iz <= byte0; ++iz)
						{
							block = world.getBlock(x + ix, y + iy, z + iz);

							if (block instanceof BlockLeaves)
							{
								block.beginLeavesDecay(world, x + ix, y + iy, z
										+ iz);
							}
						}
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#canSustainLeaves(net.minecraft.world.IBlockAccess, int, int, int)
	 */
	@Override
	public boolean canSustainLeaves(final IBlockAccess world, final int x,
			final int y, final int z)
	{
		if ((world.getBlock(x, y, z) == this)
				&& (world.getBlockMetadata(x, y, z) == 8))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see net.minecraft.block.Block#isWood(net.minecraft.world.IBlockAccess, int, int, int)
	 */
	@Override
	public boolean isWood(final IBlockAccess world, final int x, final int y,
			final int z)
	{
		if ((world.getBlock(x, y, z) == this)
				&& (world.getBlockMetadata(x, y, z) == 8))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	// I don't know why it says blockCastIronLamp, it's just supposed to be that
	// lamp block.
	// Make sure you do a metadata check before implementing this.

	/*
	 * @Override public void onBlockAdded(World world, int x, int y, int z) { if
	 * (!world.isRemote) { if ((this.powered) &&
	 * (!world.isBlockIndirectlyGettingPowered(x, y, z))) {
	 * world.scheduleBlockUpdate(x, y, z, this.blockID, 4); world.setBlock(x, y,
	 * z, ConfigBlocks.blockCastIronLamp.blockID, 1, 12); } else if
	 * ((!this.powered) && (world.isBlockIndirectlyGettingPowered(x, y, z))) {
	 * world.setBlock(x, y, z, ConfigBlocks.blockCastIronLamp.blockID, 1, 11); }
	 * } }
	 * 
	 * @Override public void onNeighborBlockChange(World world, int x, int y,
	 * int z, int bid) { if (!world.isRemote) { if ((this.powered) &&
	 * (!world.isBlockIndirectlyGettingPowered(x, y, z))) {
	 * world.scheduleBlockUpdate(x, y, z, this.blockID, 4); world.setBlock(x, y,
	 * z, ConfigBlocks.blockCastIronLamp.blockID, 1, 12); } else if
	 * ((!this.powered) && (world.isBlockIndirectlyGettingPowered(x, y, z))) {
	 * world.setBlock(x, y, z, ConfigBlocks.blockCastIronLamp.blockID, 1, 11); }
	 * } }
	 * 
	 * @Override public void updateTick(World world, int x, int y, int z, Random
	 * random) { if ((!world.isRemote && this.powered) &&
	 * (!world.isBlockIndirectlyGettingPowered(x, y, z))) { world.setBlock(x, y,
	 * z, ConfigBlocks.blockCastIronLamp.blockID, 1, 12); } }
	 */
}
