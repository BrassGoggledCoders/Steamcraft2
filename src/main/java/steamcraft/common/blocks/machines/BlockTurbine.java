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

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.tiles.energy.TileTurbine;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
		switch(side)
		{
			case 1:
				return this.iconTop; // top
			case 2:
				return this.iconBottom;
			default:
				return this.blockIcon; // sides
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerBlockIcons(IIconRegister icon)
	{
		this.blockIcon = icon.registerIcon(LibInfo.PREFIX + "blockTurbine");
		this.iconTop = icon.registerIcon(LibInfo.PREFIX + "blockTurbineTop");
		this.iconBottom = icon.registerIcon(LibInfo.PREFIX + "blockTurbineBottom");
		// this.iconTop = icon.registerIcon(LibInfo.PREFIX +
		// "blockSteamBoilerTop");
	}

}
