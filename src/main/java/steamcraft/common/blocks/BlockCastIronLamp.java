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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.TileCastIronLamp;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class BlockCastIronLamp extends BlockContainer
{
	public boolean powered;
	private static List torchUpdates = new ArrayList();
	public static Block instance;
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		this.blockIcon = ir.registerIcon(LibInfo.PREFIX + "itemCastIronLamp");
	}
 
	public BlockCastIronLamp(int id, boolean flag)
	{
		super(id, Material.circuits);
		this.setHardness(0.0F);
		this.setResistance(7.5F);
		this.powered = flag;
		this.setTickRandomly(true);
		this.disableStats();
		float f = 0.25F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		this.setCreativeTab((CreativeTabs) null);

		if (this.powered)
			this.setLightValue(1.0F);
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1)
	{
		return new TileCastIronLamp();
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
	{
		return false;
	}

	@Override
	public int tickRate(World world)
	{
		return 10;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return null;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		this.setBlockBoundsBasedOnState(world, x, y, z);
		return super.getSelectedBoundingBoxFromPool(world, x, y, z);
	}

	@Override
	public int getRenderType()
	{
		return -1;
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
	public int onBlockPlaced(World world, int i, int j, int k, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		if (side == 0)
		{
			world.setBlockMetadataWithNotify(i, j, k, 6, 2);
		}
		if (side == 1)
		{
			world.setBlockMetadataWithNotify(i, j, k, 5, 2);
		}
		if (side == 2)
		{
			world.setBlockMetadataWithNotify(i, j, k, 4, 2);
		}
		if (side == 3)
		{
			world.setBlockMetadataWithNotify(i, j, k, 3, 2);
		}
		if (side == 4)
		{
			world.setBlockMetadataWithNotify(i, j, k, 2, 2);
		}
		if (side == 5)
		{
			world.setBlockMetadataWithNotify(i, j, k, 1, 2);
		}

		return metadata;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z)
	{
		return (world.getBlockId(x, y - 1, z) != 0) ? true : false;
	}

	@Override
	public void onBlockAdded(World world, int x, int y, int z)
	{
		if (!world.isRemote)
		{
			if (this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.scheduleBlockUpdate(x, y, z, this.blockID, 4);
			} 
			else if (!this.powered && world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.setBlock(x, y, z, ConfigBlocks.blockCastIronLampA.blockID, 0, 2);
			}
			if (world.getBlockMetadata(x, y, z) == 0)
			{
				super.onBlockAdded(world, x, y, z);
			}

			world.notifyBlocksOfNeighborChange(x, y - 1, z, this.blockID);
			world.notifyBlocksOfNeighborChange(x, y + 1, z, this.blockID);
			world.notifyBlocksOfNeighborChange(x - 1, y, z, this.blockID);
			world.notifyBlocksOfNeighborChange(x + 1, y, z, this.blockID);
			world.notifyBlocksOfNeighborChange(x, y, z - 1, this.blockID);
			world.notifyBlocksOfNeighborChange(x, y, z + 1, this.blockID);
			world.notifyBlocksOfNeighborChange(x, y, z, this.blockID);
		}

		this.dropIfCantStay(world, x, y, z);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, int bid, int metadata)
	{
		if (this.powered)
		{
			world.notifyBlocksOfNeighborChange(x, y - 1, z, this.blockID);
			world.notifyBlocksOfNeighborChange(x, y + 1, z, this.blockID);
			world.notifyBlocksOfNeighborChange(x - 1, y, z, this.blockID);
			world.notifyBlocksOfNeighborChange(x + 1, y, z, this.blockID);
			world.notifyBlocksOfNeighborChange(x, y, z - 1, this.blockID);
			world.notifyBlocksOfNeighborChange(x, y, z + 1, this.blockID);
		}

		super.breakBlock(world, x, y, z, bid, metadata);
	}

	@Override
	public void updateTick(World world, int x, int y, int z, Random random)
	{
		super.updateTick(world, x, y, z, random);

		if (world.getBlockMetadata(x, y, z) == 0)
		{
			this.onBlockAdded(world, x, y, z);
		}
		if (!world.isRemote && this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z))
		{
			world.setBlock(x, y, z, ConfigBlocks.blockCastIronLampI.blockID, 0, 2);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, int bid)
	{
		if (!world.isRemote)
		{ 
			if (this.powered && !world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.scheduleBlockUpdate(x, y, z, this.blockID, this.tickRate(world));
			} 
			else if (!this.powered && world.isBlockIndirectlyGettingPowered(x, y, z))
			{
				world.setBlock(x, y, z, ConfigBlocks.blockCastIronLampA.blockID, 0, 2);
			}
			if (dropIfCantStay(world, x, y, z))
			{
				int metadata = world.getBlockMetadata(x, y, z);
				boolean flag = false;

				if (metadata == 1)
				{
					flag = true;
				}
				if (metadata == 2)
				{
					flag = true;
				}
				if (metadata == 3)
				{
					flag = true;
				}
				if (metadata == 4)
				{
					flag = true;
				}
				if (metadata == 5)
				{
					flag = true;
				}
				if (flag)
				{
					this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 1);
					world.setBlockToAir(x, y, z);
				}
			}
		}

		super.onNeighborBlockChange(world, x, y, z, bid);
	}

	private boolean dropIfCantStay(World world, int x, int y, int z)
	{
		if (!this.canPlaceBlockAt(world, x, y, z))
		{
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 1);
			world.setBlockToAir(x, y, z);
			return false;
		} 
		else
		{
			return true;
		}
	}

	@Override
	public int idDropped(int id, Random random, int metadata)
    {
		return ConfigBlocks.blockCastIronLampI.blockID;
	}

	@Override
	public boolean canProvidePower()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int x, int j, int z, Random random)
	{
		if(!powered)
		{
			return;
		} 
		else
		{
			int l = world.getBlockMetadata(x, j, z);
			double d = (double)((float)x + 0.5F) + (double)(random.nextFloat() - 0.5F) * 0.20000000000000001D;
			double d1 = (double)((float)j + 1.0F) + (double)(random.nextFloat() - 0.5F) * 0.20000000000000001D;
			double d2 = (double)((float)z + 0.5F) + (double)(random.nextFloat() - 0.5F) * 0.20000000000000001D;
			double d3 = 0.2199999988079071D;
			double d4 = 0.27000001072883606D;

			if(l == 1)
			{
				world.spawnParticle("reddust", d - d4, d1 + d3, d2, -1.0D, 0.7D, 1.0D);
			} else if(l == 2)
			{
				world.spawnParticle("reddust", d + d4, d1 + d3, d2, -1.0D, 0.7D, 1.0D);
			} else if(l == 3)
			{
				world.spawnParticle("reddust", d, d1 + d3, d2 - d4, -1.0D, 0.7D, 1.0D);
			} else if(l == 4)
			{
				world.spawnParticle("reddust", d, d1 + d3, d2 + d4, -1.0D, 0.7D, 1.0D);
			} else
			{
				world.spawnParticle("reddust", d, d1, d2, -1.0D, 0.7D, 1.0D);
			}
		}
	}
}
