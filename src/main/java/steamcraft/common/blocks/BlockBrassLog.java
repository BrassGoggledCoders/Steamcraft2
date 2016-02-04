
package steamcraft.common.blocks;

import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class BlockBrassLog extends BlockLog
{
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	public BlockBrassLog(Material p_i45394_1_)
	{
		super();
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getSideIcon(int p_150163_1_)
	{
		return this.blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	protected IIcon getTopIcon(int p_150161_1_)
	{
		return this.iconTop;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister ir)
	{
		this.blockIcon = ir.registerIcon(ModInfo.PREFIX + "blockBrassLogSide");
		this.iconTop = ir.registerIcon(ModInfo.PREFIX + "blockBrassLogTop");
	}
}
