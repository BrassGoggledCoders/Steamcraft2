package steamcraft.common.blocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class BlockBrassLog extends BlockLog
{
	/** The icon. */
	private final IIcon[] icon = new IIcon[2];

	public BlockBrassLog(Material p_i45394_1_) {
		super();
		setCreativeTab(Steamcraft.tabSC2);
		setBlockName("blockBrassLog");
	}
	@Override
    @SideOnly(Side.CLIENT)
    protected IIcon getSideIcon(int p_150163_1_)
    {
        return icon[0];
    }

	@Override
    @SideOnly(Side.CLIENT)
    protected IIcon getTopIcon(int p_150161_1_)
    {
        return icon[1];
    }
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister ir)
	{
		icon[0] = ir.registerIcon(LibInfo.PREFIX + "blockBrassLogSide");
		icon[1] = ir.registerIcon(LibInfo.PREFIX + "blockBrassLogTop");
	}
}
