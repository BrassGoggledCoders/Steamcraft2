/*
 *
 */
package steamcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseBlock.
 */
public class BaseBlock extends Block
{

	/** The icon. */
	IIcon icon;

	/**
	 * Instantiates a new base block.
	 *
	 * @param p_i45394_1_
	 *            the p_i45394_1_
	 */
	public BaseBlock(Material p_i45394_1_)
	{
		super(p_i45394_1_);
		setCreativeTab(Steamcraft.tabSC2);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.minecraft.block.Block#registerBlockIcons(net.minecraft.client.renderer
	 * .texture.IIconRegister)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		icon = ir.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.block.Block#getIcon(int, int)
	 */
	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return icon;
	}
}
