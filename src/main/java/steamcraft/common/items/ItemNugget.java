package steamcraft.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemNugget extends BaseItemWithMetadata
{
	IIcon[] itemIcon = new IIcon[8];
    public ItemNugget() {
	super();
	setUnlocalizedName("itemNugget");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item,  CreativeTabs tab,  List list)
    {
    	for (int var4 = 0; var4 < 8; ++var4)
		{
			list.add(new ItemStack(ConfigItems.itemNugget, 1, var4));
		}
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir)
    {
		itemIcon[0] = ir.registerIcon(LibInfo.PREFIX + "itemNuggetAluminum");
		itemIcon[1] = ir.registerIcon(LibInfo.PREFIX + "itemNuggetCopper");
		itemIcon[2] = ir.registerIcon(LibInfo.PREFIX + "itemNuggetTin");
		itemIcon[3] = ir.registerIcon(LibInfo.PREFIX + "itemNuggetZinc");
		itemIcon[4] = ir.registerIcon(LibInfo.PREFIX + "itemNuggetBrass");
		itemIcon[5] = ir.registerIcon(LibInfo.PREFIX + "itemNuggetBronze");
		itemIcon[6] = ir.registerIcon(LibInfo.PREFIX + "itemNuggetSteel");
		itemIcon[7] = ir.registerIcon(LibInfo.PREFIX + "itemNuggetCastIron");
    }
    @Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage( int itemDamage)
	{
		return itemIcon[itemDamage];
	}

}
