package steamcraft.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseItem extends Item
{
	public BaseItem()
	{
		super();
		setCreativeTab(Steamcraft.tabSC2);
	}
    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister par1IconRegister)
    {
            itemIcon = par1IconRegister.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5));
    }
	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#getIconFromDamage(int)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage( int itemDamage)
	{
		return itemIcon;
	}
}
