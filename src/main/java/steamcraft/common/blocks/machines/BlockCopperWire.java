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
package steamcraft.common.blocks.machines;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import steamcraft.common.tiles.TileCopperWire;

/**
 * @author warlordjones
 * 
 */
public class BlockCopperWire extends BlockContainerMod
{
	static float pixel = 1 / 16f;

	public BlockCopperWire(Material p_i45394_1_)
	{
		super(p_i45394_1_);
		setBlockBounds(6 * pixel, 6 * pixel, 6 * pixel, 1 - (6 * pixel), 1 - (6 * pixel), 1 - (6 * pixel));
		useNeighborBrightness = true;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileCopperWire();
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public int getRenderType()
	{
		return -1;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLiving, ItemStack is)
	{
		super.onBlockPlacedBy(world, x, y, z, entityLiving, is);

		TileCopperWire tile = (TileCopperWire) world.getTileEntity(x, y, z);

		if (tile != null)
			tile.updateConnections();
	}

	@Override
	public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ)
	{
		TileCopperWire tile = (TileCopperWire) world.getTileEntity(x, y, z);
		tile.updateConnections();

		TileEntity t = world.getTileEntity(tileX, tileY, tileZ);
		if ((t != null) && (t instanceof TileCopperWire))
		{
			tile = (TileCopperWire) t;
			tile.updateConnections();
		}
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
	{
		if (!world.isRemote)
		{
			TileCopperWire tile = (TileCopperWire) world.getTileEntity(x, y, z);

			if (tile != null)
				tile.removeFromNetwork();
		}

		super.breakBlock(world, x, y, z, block, metadata);
	}

	@Override
	public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return getBoundingBox(world, x, y, z);
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		return getBoundingBox(world, x, y, z);
	}

	private AxisAlignedBB getBoundingBox(World world, int x, int y, int z)
	{
		TileCopperWire wire = (TileCopperWire) world.getTileEntity(x, y, z);

		if (wire != null)
		{
			float minX = (6 * pixel) - (wire.connections[5] != null ? 6 * pixel : 0);
			float maxX = (1 - (6 * pixel)) + (wire.connections[4] != null ? 6 * pixel : 0);

			float minY = (6 * pixel) - (wire.connections[1] != null ? 6 * pixel : 0);
			float maxY = (1 - (6 * pixel)) + (wire.connections[0] != null ? 6 * pixel : 0);

			float minZ = (6 * pixel) - (wire.connections[3] != null ? 6 * pixel : 0);
			float maxZ = (1 - (6 * pixel)) + (wire.connections[2] != null ? 6 * pixel : 0);

			setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
		}

		return AxisAlignedBB.getBoundingBox(x + minX, y + minY, z + minZ, x + maxX, y + maxY, z + maxZ);
	}
}
