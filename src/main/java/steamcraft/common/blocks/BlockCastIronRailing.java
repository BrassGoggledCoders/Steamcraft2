/*
 *
 */
package steamcraft.common.blocks;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class BlockCastIronRailing.
 */
public class BlockCastIronRailing extends BlockPane
{
	public IIcon blockIcon;
	public IIcon sideIcon;
	/**
	 * Instantiates a new block cast iron railing.
	 *
	 * @param p_i45394_1_
	 *            the p_i45394_1_
	 */
	public BlockCastIronRailing(Material mat)
	{
		super("", "", mat, false);
		setBlockName("blockCastIronRailing");
		setCreativeTab(Steamcraft.tabSC2);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.block.Block#getRenderType()
	 */
	@Override
	public int getRenderType()
	{
		return 18;
	}
	 @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister p_149651_1_)
    {
        this.blockIcon = p_149651_1_.registerIcon(LibInfo.PREFIX + "blockCastIronRailing");
        this.sideIcon = p_149651_1_.registerIcon(LibInfo.PREFIX + "blockCastIronRailingSide");
    }

}
