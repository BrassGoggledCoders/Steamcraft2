
package steamcraft.common.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.baseclasses.blocks.BaseContainerBlock;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.tiles.energy.TileTurbine;

/**
 * @author decebaldecebal
 *
 */
public class BlockTurbine extends BaseContainerBlock
{
	@SideOnly(Side.CLIENT)
	private IIcon iconTop, iconBottom;

	public BlockTurbine()
	{
		super(Material.iron);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2)
	{
		return new TileTurbine();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta)
	{
		switch (side)
		{
		case 1:
			return this.iconTop; // top
		case 0:
			return this.iconTop;
		default:
			return this.blockIcon; // sides
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon)
	{
		this.blockIcon = icon.registerIcon(ModInfo.PREFIX + "blockTurbine");
		this.iconTop = icon.registerIcon(ModInfo.PREFIX + "blockTurbineTop");
	}

}
