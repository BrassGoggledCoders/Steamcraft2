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
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import steamcraft.client.lib.RenderIDs;
import steamcraft.client.renderers.tile.TileCastIronLampRenderer.TileCastIronLamp;
import steamcraft.common.InitBlocks;
import steamcraft.common.Steamcraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance
 *
 */
public class BlockCastIronLamp extends BlockContainer
{
	public final boolean powered;

	public BlockCastIronLamp(boolean powered)
	{
		super(Material.circuits);
		this.powered = powered;
		this.setHardness(0.0F);
		this.setResistance(7.5F);
		this.setTickRandomly(true);
		this.disableStats();
		final float f = 0.25F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		this.setCreativeTab(Steamcraft.tabSC2);

		if(powered)
			this.setLightLevel(1.0F);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileCastIronLamp();
	}

	@Override
	public int tickRate(final World world)
	{
		return 10;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(final World world, final int x, final int y, final int z)
	{
		return null;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(final World world, final int x, final int y, final int z)
	{
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public int getRenderType()
	{
		return RenderIDs.blockCastIronLampRI;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public int onBlockPlaced(final World world, final int i, final int j, final int k, final int side, final float hitX, final float hitY,
			final float hitZ, final int metadata)
	{
		if(side == 0)
			world.setBlockMetadataWithNotify(i, j, k, 6, 2);
		if(side == 1)
			world.setBlockMetadataWithNotify(i, j, k, 5, 2);
		if(side == 2)
			world.setBlockMetadataWithNotify(i, j, k, 4, 2);
		if(side == 3)
			world.setBlockMetadataWithNotify(i, j, k, 3, 2);
		if(side == 4)
			world.setBlockMetadataWithNotify(i, j, k, 2, 2);
		if(side == 5)
			world.setBlockMetadataWithNotify(i, j, k, 1, 2);

		return metadata;
	}

	@Override
	public boolean canPlaceBlockAt(final World world, final int x, final int y, final int z)
	{
		return world.getBlock(x, y - 1, z) != Blocks.air ? true : false;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		if(!world.isRemote)
			if(powered && !world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.scheduleBlockUpdate(x, y, z, this, 4);
				world.setBlock(x, y, z, InitBlocks.blockCastIronLamp, 1, world.getBlockMetadata(x, y, z) + 10);
			}
			else if(!powered && world.isBlockIndirectlyGettingPowered(x, y, z))
				world.setBlock(x, y, z, InitBlocks.blockCastIronLampOn, 1, world.getBlockMetadata(x, y, z) + 10);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block p_149695_5_)
	{
		if(!world.isRemote)
			if(powered && !world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.scheduleBlockUpdate(x, y, z, this, 4);
				world.setBlock(x, y, z, InitBlocks.blockCastIronLamp, 1, world.getBlockMetadata(x, y, z) + 10);
			}
			else if(!powered && world.isBlockIndirectlyGettingPowered(x, y, z))
				world.setBlock(x, y, z, InitBlocks.blockCastIronLampOn, 0, world.getBlockMetadata(x, y, z) + 10);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		if(!world.isRemote && powered && !world.isBlockIndirectlyGettingPowered(x, y, z))
			world.setBlock(x, y, z, InitBlocks.blockCastIronLamp, 0, world.getBlockMetadata(x, y, z) + 10);
	}

	@SuppressWarnings("unused")
	private boolean dropIfCantStay(final World world, final int x, final int y, final int z)
	{
		if(!this.canPlaceBlockAt(world, x, y, z))
		{
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 1);
			world.setBlockToAir(x, y, z);
			return false;
		}
		else
			return true;
	}

	@Override
	public boolean canProvidePower()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(final World world, final int x, final int j, final int z, final Random random)
	{
		if(!powered)
			return;
		else
		{
			final int l = world.getBlockMetadata(x, j, z);
			final double d = x + 0.5F + ((random.nextFloat() - 0.5F) * 0.20000000000000001D);
			final double d1 = j + 1.0F + ((random.nextFloat() - 0.5F) * 0.20000000000000001D);
			final double d2 = z + 0.5F + ((random.nextFloat() - 0.5F) * 0.20000000000000001D);
			final double d3 = 0.2199999988079071D;
			final double d4 = 0.27000001072883606D;

			if(l == 1)
				world.spawnParticle("reddust", d - d4, d1 + d3, d2, -1.0D, 0.7D, 1.0D);
			else if(l == 2)
				world.spawnParticle("reddust", d + d4, d1 + d3, d2, -1.0D, 0.7D, 1.0D);
			else if(l == 3)
				world.spawnParticle("reddust", d, d1 + d3, d2 - d4, -1.0D, 0.7D, 1.0D);
			else if(l == 4)
				world.spawnParticle("reddust", d, d1 + d3, d2 + d4, -1.0D, 0.7D, 1.0D);
			else
				world.spawnParticle("reddust", d, d1, d2, -1.0D, 0.7D, 1.0D);
		}
	}
}
