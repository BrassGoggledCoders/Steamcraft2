package steamcraft.common.items;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import steamcraft.common.Steamcraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseItemWithMetadata extends BaseItem
{
    @SideOnly(Side.CLIENT)
    public IIcon[] itemIcon = new IIcon[0];
	public BaseItemWithMetadata()
	{
		super();
		setHasSubtypes(true);
		setCreativeTab(Steamcraft.tabSC2);
	}
	/* (non-Javadoc)
	 * @see net.minecraft.item.ItemBlock#getUnlocalizedName(net.minecraft.item.ItemStack)
	 */
	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		return super.getUnlocalizedName() + "." + is.getItemDamage();
	}
	/*@Override
    public IIcon getIconFromDamage(int i)
	{
        if(itemIcon.length != 0)
		return itemIcon[i];
        else return itemIcon[0];
    }*/
}
