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

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.client.lib.RenderIDs;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.tiles.energy.TileSteelWire;

/**
 * @author Decebaldecebal
 *
 */
public class BlockSteelWire extends BlockCopperWire
{
	public BlockSteelWire(Material mat)
	{
		super(mat);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_)
	{
		return new TileSteelWire();
	}

	@Override
	public int getRenderType()
	{
		return RenderIDs.blockSteelWireRI;
	}

	@Override
	public void onNeighborChange(IBlockAccess world, int x, int y, int z, int tileX, int tileY, int tileZ)
	{
		if (world.getBlock(tileX, tileY, tileZ) != InitBlocks.blockSteelWire)
		{
			TileSteelWire tile = (TileSteelWire) world.getTileEntity(x, y, z);
			tile.updateConnections(); // only on server
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(final Item item, final CreativeTabs tab, final List l)
	{
		l.add(new ItemStack(InitBlocks.blockSteelWire, 1, 0));
		l.add(new ItemStack(InitBlocks.blockSteelWire, 1, 1));
	}
}
