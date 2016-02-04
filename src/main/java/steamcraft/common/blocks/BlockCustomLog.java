
package steamcraft.common.blocks;

import net.minecraft.block.BlockLog;
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
public class BlockCustomLog extends BlockLog
{
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	String type;

	public BlockCustomLog(String type)
	{
		super();
		this.type = type;
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
		this.blockIcon = ir.registerIcon(ModInfo.PREFIX + "block" + this.type + "LogSide");
		this.iconTop = ir.registerIcon(ModInfo.PREFIX + "block" + this.type + "LogTop");
	}
}
