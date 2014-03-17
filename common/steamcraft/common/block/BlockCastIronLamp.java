package common.steamcraft.common.block;

import common.steamcraft.common.block.tile.TileEntityLamp;
import common.steamcraft.common.lib2.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockCastIronLamp extends BlockContainer
{
	public boolean powered;
	private static List torchUpdates = new ArrayList();
	public static Block instance;

	protected BlockCastIronLamp(int i, boolean flag)
	{
		super(i, Material.circuits);
		this.powered = flag;
		this.setTickRandomly(true);
		float f = 0.25F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 1.0F, 0.5F + f);
		this.setHardness(0.0F);
		this.disableStats();
		this.setCreativeTab((CreativeTabs) null);

		if(this.powered)
		{
			this.setLightValue(1.0F);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon)
	{
		this.blockIcon = icon.registerIcon(LibInfo.SC2_PREFIX + "ironlamp");
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iBlockAccess, int i, int j, int k, int l)
	{
		return false;
	}

	@Override
	public int tickRate(World world)
	{
		return 1;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	{
		return null;
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k)
	{
		this.setBlockBoundsBasedOnState(world, i, j, k);
		return super.getSelectedBoundingBoxFromPool(world, i, j, k);
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
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityLamp();
	}

	@Override
	public int onBlockPlaced(World world, int i, int j, int k, int l, float hitX, float hitY, float hitZ, int meta)
	{
		if(l == 0 && canPlaceBottom(world, i, j + 1 , k))
		{
			world.setBlockMetadataWithNotify(i, j, k, 6, 2);
		}
		if(l == 1 && canPlaceTop(world, i, j - 1, k))
		{
			world.setBlockMetadataWithNotify(i, j, k, 5, 2);
		}
		if(l == 2 && canPlaceSide(world, i, j , k + 1))
		{
			world.setBlockMetadataWithNotify(i, j, k, 4, 2);
		}
		if(l == 3 && canPlaceSide(world, i, j , k - 1))
		{
			world.setBlockMetadataWithNotify(i, j, k, 3, 2);
		}
		if(l == 4 && canPlaceSide(world, i + 1, j , k))
		{
			world.setBlockMetadataWithNotify(i, j, k, 2, 2);
		}
		if(l == 5 && canPlaceSide(world, i - 1, j , k))
		{
			world.setBlockMetadataWithNotify(i, j, k, 1, 2);
		}

		return meta;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int i, int j, int k)
	{
		if(canPlaceSide(world, i - 1, j , k))
		{
			return true;
		}
		if(canPlaceSide(world, i + 1, j , k))
		{
			return true;
		}
		if(canPlaceSide(world, i, j , k - 1))
		{
			return true;
		}
		if(canPlaceSide(world, i, j , k + 1))
		{
			return true;
		}
		if(canPlaceBottom(world, i, j + 1 , k))
		{
			return true;
		}

		return canPlaceTop(world, i, j - 1, k);
	}

	private boolean canPlaceTop(World world, int i, int j, int k)
	{
		return (world.isBlockNormalCube(i, j, k) 
				|| world.getBlockId(i, j, k) == Block.fence.blockID
				|| world.getBlockId(i, j, k) == ModBlocks.railingCastIron.blockID
				|| world.getBlockId(i, j, k) == Block.glass.blockID);
	}

	private boolean canPlaceSide(World world, int i, int j, int k)
	{
		return (world.isBlockNormalCube(i, j, k) 
				|| world.getBlockId(i, j, k) == Block.glass.blockID
				|| world.getBlockId(i, j, k) == Block.stairsBrick.blockID
				|| world.getBlockId(i, j, k) == Block.stairsCobblestone.blockID
				|| world.getBlockId(i, j, k) == Block.stairsNetherBrick.blockID
				|| world.getBlockId(i, j, k) == Block.stairsNetherQuartz.blockID
				|| world.getBlockId(i, j, k) == Block.stairsSandStone.blockID
				|| world.getBlockId(i, j, k) == Block.stairsStoneBrick.blockID
				|| world.getBlockId(i, j, k) == Block.stairsWoodBirch.blockID
				|| world.getBlockId(i, j, k) == Block.stairsWoodJungle.blockID
				|| world.getBlockId(i, j, k) == Block.stairsWoodOak.blockID
				|| world.getBlockId(i, j, k) == Block.stairsWoodSpruce.blockID);
	}

	private boolean canPlaceBottom(World world, int i, int j, int k)
	{
		return (world.isBlockNormalCube(i, j, k) 
				|| world.getBlockId(i, j, k) == Block.glass.blockID
				|| world.getBlockId(i, j, k) == Block.stairsBrick.blockID
				|| world.getBlockId(i, j, k) == Block.stairsCobblestone.blockID
				|| world.getBlockId(i, j, k) == Block.stairsNetherBrick.blockID
				|| world.getBlockId(i, j, k) == Block.stairsNetherQuartz.blockID
				|| world.getBlockId(i, j, k) == Block.stairsSandStone.blockID
				|| world.getBlockId(i, j, k) == Block.stairsStoneBrick.blockID
				|| world.getBlockId(i, j, k) == Block.stairsWoodBirch.blockID
				|| world.getBlockId(i, j, k) == Block.stairsWoodJungle.blockID
				|| world.getBlockId(i, j, k) == Block.stairsWoodOak.blockID
				|| world.getBlockId(i, j, k) == Block.stairsWoodSpruce.blockID);
	}

	@Override
	public void onBlockAdded(World world, int i, int j, int k)
	{
		if(!world.isRemote)
		{
			if(this.powered && !world.isBlockIndirectlyGettingPowered(i, j, k))
			{
				world.scheduleBlockUpdate(i, j, k, this.blockID, 4);
			} else if(!this.powered && world.isBlockIndirectlyGettingPowered(i, j, k))
			{
				world.setBlock(i, j, k, ModBlocks.castIronLampOn.blockID, 0, 2);
			}
			if(world.getBlockMetadata(i, j, k) == 0)
			{
				super.onBlockAdded(world, i, j, k);
			}

			world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
			world.notifyBlocksOfNeighborChange(i, j + 1, k, blockID);
			world.notifyBlocksOfNeighborChange(i - 1, j, k, blockID);
			world.notifyBlocksOfNeighborChange(i + 1, j, k, blockID);
			world.notifyBlocksOfNeighborChange(i, j, k - 1, blockID);
			world.notifyBlocksOfNeighborChange(i, j, k + 1, blockID);
			world.notifyBlocksOfNeighborChange(i, j, k, blockID);

			if(world.getBlockMetadata(i, j, k) == 0)
			{	
				if(canPlaceBottom(world, i, j + 1, k))
				{
					world.setBlockMetadataWithNotify(i, j, k, 6, 2);
				} else if(canPlaceSide(world, i - 1, j , k))
				{
					world.setBlockMetadataWithNotify(i, j, k, 1, 2);
				} else if(canPlaceSide(world, i + 1, j , k))
				{
					world.setBlockMetadataWithNotify(i, j, k, 2, 2);
				} else if(canPlaceSide(world, i, j , k - 1))
				{
					world.setBlockMetadataWithNotify(i, j, k, 3, 2);
				} else if(canPlaceSide(world, i, j , k + 1))
				{
					world.setBlockMetadataWithNotify(i, j, k, 4, 2);
				} else if(canPlaceTop(world, i, j - 1, k))
				{
					world.setBlockMetadataWithNotify(i, j, k, 5, 2);
				}
			}
		}

		this.dropIfCantStay(world, i, j, k);
	}

	@Override
	public void breakBlock(World world, int i, int j, int k, int l, int m)
	{
		if(this.powered)
		{
			world.notifyBlocksOfNeighborChange(i, j - 1, k, blockID);
			world.notifyBlocksOfNeighborChange(i, j + 1, k, blockID);
			world.notifyBlocksOfNeighborChange(i - 1, j, k, blockID);
			world.notifyBlocksOfNeighborChange(i + 1, j, k, blockID);
			world.notifyBlocksOfNeighborChange(i, j, k - 1, blockID);
			world.notifyBlocksOfNeighborChange(i, j, k + 1, blockID);
		}

		super.breakBlock(world, i, j, k, l, m);
	}

	@Override
	public void updateTick(World world, int i, int j, int k, Random random)
	{
		super.updateTick(world, i, j, k, random);

		if(world.getBlockMetadata(i, j, k) == 0)
		{
			onBlockAdded(world, i, j, k);
		}
		if(!world.isRemote && this.powered && !world.isBlockIndirectlyGettingPowered(i, j, k))
		{
			world.setBlock(i, j, k, ModBlocks.castIronLampOff.blockID, 0, 2);
		}
	}

	@Override
	public void onNeighborBlockChange(World world, int i, int j, int k, int l)
	{
		if(!world.isRemote)
		{
			if(this.powered && !world.isBlockIndirectlyGettingPowered(i, j, k))
			{
				world.scheduleBlockUpdate(i, j, k, this.blockID, tickRate(world));
			} else if(!this.powered && world.isBlockIndirectlyGettingPowered(i, j, k))
			{
				world.setBlock(i, j, k, ModBlocks.castIronLampOn.blockID, 0, 2);
			}
			if(dropIfCantStay(world, i, j, k))
			{
				int meta = world.getBlockMetadata(i, j, k);
				boolean flag = false;

				if(!canPlaceSide(world, i - 1, j , k) && meta == 1)
				{
					flag = true;
				}
				if(!canPlaceSide(world, i + 1, j , k) && meta == 2)
				{
					flag = true;
				}
				if(!canPlaceSide(world, i, j , k - 1) && meta == 3)
				{
					flag = true;
				}
				if(!canPlaceSide(world, i, j , k + 1) && meta == 4)
				{
					flag = true;
				}
				if(!canPlaceTop(world, i, j - 1, k) && meta == 5)
				{
					flag = true;
				}
				if(flag)
				{
					this.dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k), 1);
					world.setBlockToAir(i, j, k);
				}
			}
		}

		super.onNeighborBlockChange(world, i, j, k, l);
	}

	private boolean dropIfCantStay(World world, int i, int j, int k)
	{
		if(!canPlaceBlockAt(world, i, j, k))
		{
			this.dropBlockAsItem(world, i, j, k, world.getBlockMetadata(i, j, k), 1);
			world.setBlockToAir(i, j, k);
			return false;
		} else
		{
			return true;
		}
	}

	@Override
	public int idDropped(int i, Random random, int j)
	{
		return ModBlocks.castIronLampOff.blockID;
	}

	@Override
	public boolean canProvidePower()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random random)
	{
		if(!powered)
		{
			return;
		} else
		{
			int l = world.getBlockMetadata(i, j, k);
			double d = (double)((float)i + 0.5F) + (double)(random.nextFloat() - 0.5F) * 0.20000000000000001D;
			double d1 = (double)((float)j + 1.0F) + (double)(random.nextFloat() - 0.5F) * 0.20000000000000001D;
			double d2 = (double)((float)k + 0.5F) + (double)(random.nextFloat() - 0.5F) * 0.20000000000000001D;
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