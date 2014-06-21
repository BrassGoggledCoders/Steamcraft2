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
 * File created @ [Apr 5, 2014, 8:29:50 PM]
 */
package steamcraft.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.tiles.TileCastIronLamp;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockCastIronLamp.
 * 
 * @author Surseance (Johnny Eatmon)
 */
public class BlockCastIronLamp extends BlockContainer {

	/** The powered. */
	public boolean powered;

	/** The instance. */
	public static Block instance;

	/**
	 * Instantiates a new block cast iron lamp.
	 * 
	 * @param flag
	 *            the flag
	 */
	public BlockCastIronLamp(final boolean flag) {
		super(Material.circuits);
		setHardness(0.0F);
		setResistance(7.5F);
		powered = flag;
		setTickRandomly(true);
		disableStats();
		final float f = 0.25F;
		setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		setCreativeTab(Steamcraft.tabSC2);

		if (flag) {
			setLightLevel(1.0F);
			setCreativeTab((CreativeTabs) null);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.ITileEntityProvider#createNewTileEntity(net.minecraft
	 * .world.World, int)
	 */
	@Override
	public TileEntity createNewTileEntity(final World world, final int metadata) {
		return new TileCastIronLamp();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#shouldSideBeRendered(net.minecraft.world.
	 * IBlockAccess, int, int, int, int)
	 */
	@Override
	public boolean shouldSideBeRendered(final IBlockAccess world, final int x,
			final int y, final int z, final int side) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#tickRate(net.minecraft.world.World)
	 */
	@Override
	public int tickRate(final World world) {
		return 10;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#getCollisionBoundingBoxFromPool(net.minecraft
	 * .world.World, int, int, int)
	 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world,
			final int x, final int y, final int z) {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#getSelectedBoundingBoxFromPool(net.minecraft
	 * .world.World, int, int, int)
	 */
	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(final World world,
			final int x, final int y, final int z) {
		setBlockBoundsBasedOnState(world, x, y, z);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#getRenderType()
	 */
	@Override
	public int getRenderType() {
		return ConfigBlocks.blockCastIronLampRI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#renderAsNormalBlock()
	 */
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#isOpaqueCube()
	 */
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#onBlockPlaced(net.minecraft.world.World,
	 * int, int, int, int, float, float, float, int)
	 */
	@Override
	public int onBlockPlaced(final World world, final int i, final int j,
			final int k, final int side, final float hitX, final float hitY,
			final float hitZ, final int metadata) {
		if (side == 0) {
			world.setBlockMetadataWithNotify(i, j, k, 6, 2);
		}
		if (side == 1) {
			world.setBlockMetadataWithNotify(i, j, k, 5, 2);
		}
		if (side == 2) {
			world.setBlockMetadataWithNotify(i, j, k, 4, 2);
		}
		if (side == 3) {
			world.setBlockMetadataWithNotify(i, j, k, 3, 2);
		}
		if (side == 4) {
			world.setBlockMetadataWithNotify(i, j, k, 2, 2);
		}
		if (side == 5) {
			world.setBlockMetadataWithNotify(i, j, k, 1, 2);
		}

		return metadata;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#canPlaceBlockAt(net.minecraft.world.World,
	 * int, int, int)
	 */
	@Override
	public boolean canPlaceBlockAt(final World world, final int x, final int y,
			final int z) {
		return (world.getBlock(x, y - 1, z) != Blocks.air) ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.BlockContainer#onBlockAdded(net.minecraft.world.World
	 * , int, int, int)
	 */
	@Override
	public void onBlockAdded(final World world, final int x, final int y,
			final int z) {
		if (!world.isRemote) {
			if (powered && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
				world.scheduleBlockUpdate(x, y, z, this, 4);
			} else if (!powered
					&& world.isBlockIndirectlyGettingPowered(x, y, z)) {
				world.setBlock(x, y, z, ConfigBlocks.blockCastIronLampA, 0, 2);
			}
			if (world.getBlockMetadata(x, y, z) == 0) {
				super.onBlockAdded(world, x, y, z);
			}

			world.notifyBlocksOfNeighborChange(x, y - 1, z, this);
			world.notifyBlocksOfNeighborChange(x, y + 1, z, this);
			world.notifyBlocksOfNeighborChange(x - 1, y, z, this);
			world.notifyBlocksOfNeighborChange(x + 1, y, z, this);
			world.notifyBlocksOfNeighborChange(x, y, z - 1, this);
			world.notifyBlocksOfNeighborChange(x, y, z + 1, this);
			world.notifyBlocksOfNeighborChange(x, y, z, this);
		}

		dropIfCantStay(world, x, y, z);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.BlockContainer#breakBlock(net.minecraft.world.World,
	 * int, int, int, net.minecraft.block.Block, int)
	 */
	@Override
	public void breakBlock(final World world, final int x, final int y,
			final int z, final Block block, final int metadata) {
		if (powered) {
			world.notifyBlocksOfNeighborChange(x, y - 1, z, this);
			world.notifyBlocksOfNeighborChange(x, y + 1, z, this);
			world.notifyBlocksOfNeighborChange(x - 1, y, z, this);
			world.notifyBlocksOfNeighborChange(x + 1, y, z, this);
			world.notifyBlocksOfNeighborChange(x, y, z - 1, this);
			world.notifyBlocksOfNeighborChange(x, y, z + 1, this);
		}

		super.breakBlock(world, x, y, z, block, metadata);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#updateTick(net.minecraft.world.World, int,
	 * int, int, java.util.Random)
	 */
	@Override
	public void updateTick(final World world, final int x, final int y,
			final int z, final Random random) {
		super.updateTick(world, x, y, z, random);

		if (world.getBlockMetadata(x, y, z) == 0) {
			onBlockAdded(world, x, y, z);
		}

		if (!world.isRemote && powered
				&& !world.isBlockIndirectlyGettingPowered(x, y, z)) {
			world.setBlock(x, y, z, ConfigBlocks.blockCastIronLampI, 0, 2);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#onNeighborBlockChange(net.minecraft.world.World
	 * , int, int, int, net.minecraft.block.Block)
	 */
	@Override
	public void onNeighborBlockChange(final World world, final int x,
			final int y, final int z, final Block block) {
		if (!world.isRemote) {
			if (powered && !world.isBlockIndirectlyGettingPowered(x, y, z)) {
				world.scheduleBlockUpdate(x, y, z, this, tickRate(world));
			} else if (!powered
					&& world.isBlockIndirectlyGettingPowered(x, y, z)) {
				world.setBlock(x, y, z, ConfigBlocks.blockCastIronLampA, 0, 2);
			}

			if (dropIfCantStay(world, x, y, z)) {
				final int metadata = world.getBlockMetadata(x, y, z);
				boolean flag = false;

				if (metadata == 1) {
					flag = true;
				}
				if (metadata == 2) {
					flag = true;
				}
				if (metadata == 3) {
					flag = true;
				}
				if (metadata == 4) {
					flag = true;
				}
				if (metadata == 5) {
					flag = true;
				}

				if (flag) {
					this.dropBlockAsItem(world, x, y, z,
							world.getBlockMetadata(x, y, z), 1);
					world.setBlockToAir(x, y, z);
				}
			}
		}

		super.onNeighborBlockChange(world, x, y, z, block);
	}

	/**
	 * Drop if cant stay.
	 * 
	 * @param world
	 *            the world
	 * @param x
	 *            the x
	 * @param y
	 *            the y
	 * @param z
	 *            the z
	 * @return true, if successful
	 */
	private boolean dropIfCantStay(final World world, final int x, final int y,
			final int z) {
		if (!canPlaceBlockAt(world, x, y, z)) {
			this.dropBlockAsItem(world, x, y, z,
					world.getBlockMetadata(x, y, z), 1);
			world.setBlockToAir(x, y, z);
			return false;
		} else {
			return true;
		}
	}

	// @Override
	/**
	 * Id dropped.
	 * 
	 * @param id
	 *            the id
	 * @param random
	 *            the random
	 * @param metadata
	 *            the metadata
	 * @return the int
	 */
	public int idDropped(final int id, final Random random, final int metadata) {
		return 0;// ConfigBlocks.blockCastIronLampI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.minecraft.block.Block#canProvidePower()
	 */
	@Override
	public boolean canProvidePower() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.minecraft.block.Block#randomDisplayTick(net.minecraft.world.World,
	 * int, int, int, java.util.Random)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(final World world, final int x, final int j,
			final int z, final Random random) {
		if (!powered) {
			return;
		} else {
			final int l = world.getBlockMetadata(x, j, z);
			final double d = x + 0.5F + (random.nextFloat() - 0.5F)
					* 0.20000000000000001D;
			final double d1 = j + 1.0F + (random.nextFloat() - 0.5F)
					* 0.20000000000000001D;
			final double d2 = z + 0.5F + (random.nextFloat() - 0.5F)
					* 0.20000000000000001D;
			final double d3 = 0.2199999988079071D;
			final double d4 = 0.27000001072883606D;

			if (l == 1) {
				world.spawnParticle("reddust", d - d4, d1 + d3, d2, -1.0D,
						0.7D, 1.0D);
			} else if (l == 2) {
				world.spawnParticle("reddust", d + d4, d1 + d3, d2, -1.0D,
						0.7D, 1.0D);
			} else if (l == 3) {
				world.spawnParticle("reddust", d, d1 + d3, d2 - d4, -1.0D,
						0.7D, 1.0D);
			} else if (l == 4) {
				world.spawnParticle("reddust", d, d1 + d3, d2 + d4, -1.0D,
						0.7D, 1.0D);
			} else {
				world.spawnParticle("reddust", d, d1, d2, -1.0D, 0.7D, 1.0D);
			}
		}
	}
}
