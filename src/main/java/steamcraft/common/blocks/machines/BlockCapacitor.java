
package steamcraft.common.blocks.machines;

import boilerplate.common.baseclasses.blocks.BaseContainerBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.Steamcraft;
import steamcraft.common.tiles.energy.TileCapacitor;

/**
 * @author decebaldecebal
 *
 */
public class BlockCapacitor extends BaseContainerBlock
{
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	public BlockCapacitor()
	{
		super(Material.iron);
	}

	/*
	 * @Override public int getRenderType() { return RenderIDs.blockBatteryRI; }
	 *
	 * @Override public boolean isOpaqueCube() { return false; }
	 *
	 * @Override public boolean renderAsNormalBlock() { return false; }
	 */

	@Override
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if (world.isRemote)
			return true;
		else
		{
			TileEntity tile = world.getTileEntity(par2, par3, par4);

			if ((tile == null) || player.isSneaking())
				return false;

			player.openGui(Steamcraft.instance, GuiIDs.CAPACITOR, world, par2, par3, par4);
			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileCapacitor();
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int par2, int par3, int par4, int par5)
	{
		TileCapacitor tile = (TileCapacitor) world.getTileEntity(par2, par3, par4);
		return tile.buffer.getEnergyStored() / 1000;
	}
}
