/*
 * 
 */
package steamcraft.common.blocks;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEngravedVanilla extends BlockEngravedSolid
{
	/** The icon. */
	private IIcon[] icon = new IIcon[5];

	/*
	 * (non-Javadoc)
	 * @see net.minecraft.block.Block#getIcon(int, int)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata)
	{
		return this.icon[metadata];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		this.icon[0] = ir.registerIcon(LibInfo.PREFIX + "blockEngravedDiamond");
		this.icon[1] = ir.registerIcon(LibInfo.PREFIX + "blockEngravedGold");
		this.icon[2] = ir.registerIcon(LibInfo.PREFIX + "blockEngravedIron");
		this.icon[3] = ir.registerIcon(LibInfo.PREFIX + "blockEngravedLapis");
		this.icon[4] = ir.registerIcon(LibInfo.PREFIX + "blockEngravedStone");
	}

	public BlockEngravedVanilla()
	{
		super();
		this.setBlockName("blockEngravedVanilla");
	}

	/*
	 * (non-Javadoc)
	 * @see net.minecraft.item.Item#getSubItems(net.minecraft.item.Item, net.minecraft.creativetab.CreativeTabs, java.util.List)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List l)
	{
		for (int var4 = 0; var4 < this.icon.length; ++var4)
			l.add(new ItemStack(ConfigBlocks.blockEngravedVanilla, 1, var4));
	}

	/*
	 * (non-Javadoc)
	 * @see net.minecraft.block.Block#damageDropped(int)
	 */
	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}
}
