
package steamcraft.common.blocks.machines;

import boilerplate.common.baseclasses.blocks.BaseContainerBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import steamcraft.client.lib.GuiIDs;
import steamcraft.client.lib.RenderIDs;
import steamcraft.common.Steamcraft;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.energy.TileBattery;

/**
 * @author decebaldecebal
 *
 */
public class BlockBattery extends BaseContainerBlock
{
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	public BlockBattery()
	{
		super(Material.iron);
	}

	@Override
	public int getRenderType()
	{
		return RenderIDs.blockBatteryRI;
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
	public boolean onBlockActivated(World world, int par2, int par3, int par4, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		if (world.isRemote)
			return true;
		else
		{
			TileBattery tile = (TileBattery) world.getTileEntity(par2, par3, par4);

			if ((tile == null) || player.isSneaking())
				return false;

			player.openGui(Steamcraft.instance, GuiIDs.BATTERY, world, par2, par3, par4);
			return true;
		}
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileBattery();
	}

	@Override
	public boolean hasComparatorInputOverride()
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(World world, int par2, int par3, int par4, int par5)
	{
		TileBattery tile = (TileBattery) world.getTileEntity(par2, par3, par4);
		return Math.min(tile.getEnergyStored(ForgeDirection.UNKNOWN) / 10000, 15);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.blockIcon = ir.registerIcon(ModInfo.PREFIX + "/metal/blockCastIron");
	}

	/**
	 * Gets the block's texture. Args: side, meta
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return InitBlocks.blockMetal.getIcon(0, 7);
	}
}
