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
	IIcon blockIcon;

	public BaseBlock(Material p_i45394_1_)
	{
		super(p_i45394_1_);
		setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		blockIcon = ir.registerIcon(LibInfo.PREFIX + getUnlocalizedName().substring(5));
	}

	@Override
	public IIcon getIcon(int side, int meta)
	{
		return blockIcon;
	}
}
