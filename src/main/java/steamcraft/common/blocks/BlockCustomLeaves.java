
package steamcraft.common.blocks;

import net.minecraft.block.BlockLeaves;
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
public class BlockCustomLeaves extends BlockLeaves
{
	String type;

	public BlockCustomLeaves(String type)
	{
		super();
		this.type = type;
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(final IIconRegister ir)
	{
		this.blockIcon = ir.registerIcon(ModInfo.PREFIX + "block" + this.type + "Leaves");
	}

	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_)
	{
		return this.blockIcon;
	}

	@Override
	public String[] func_150125_e()
	{
		return new String[] { this.type };
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
}
