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

public class ItemPowder extends BaseItemWithMetadata
{
	//@SideOnly(Side.CLIENT)
	IIcon[] itemIcon = new IIcon[8];
    public ItemPowder() {
	super();
	setUnlocalizedName("itemPowder");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item,  CreativeTabs tab,  List list)
    {
    	for (int var4 = 0; var4 < 8; ++var4)
		{
			list.add(new ItemStack(ConfigItems.itemPowder, 1, var4));
		}
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir)
    {
		itemIcon[0] = ir.registerIcon(LibInfo.PREFIX + "itemPowderAluminum");
		itemIcon[1] = ir.registerIcon(LibInfo.PREFIX + "itemPowderCopper");
		itemIcon[2] = ir.registerIcon(LibInfo.PREFIX + "itemPowderTin");
		itemIcon[3] = ir.registerIcon(LibInfo.PREFIX + "itemPowderZinc");
		itemIcon[4] = ir.registerIcon(LibInfo.PREFIX + "itemPowderBrass");
		itemIcon[5] = ir.registerIcon(LibInfo.PREFIX + "itemPowderBronze");
		itemIcon[6] = ir.registerIcon(LibInfo.PREFIX + "itemPowderSteel");
		itemIcon[7] = ir.registerIcon(LibInfo.PREFIX + "itemPowderCastIron");
    }
    @Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage( int itemDamage)
	{
		return itemIcon[itemDamage];
	}

}
