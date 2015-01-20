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
package steamcraft.common.blocks;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
		this.blockIcon = ir.registerIcon(LibInfo.PREFIX + "block" + type + "LogSide");
		this.iconTop = ir.registerIcon(LibInfo.PREFIX + "block" + type + "LogTop");
	}
}
