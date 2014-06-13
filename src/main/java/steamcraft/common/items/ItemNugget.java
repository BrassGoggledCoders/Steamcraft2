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
    @SideOnly(Side.CLIENT)
    private IIcon[] icon = new IIcon[8];

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
		icon[0] = ir.registerIcon(LibInfo.PREFIX + "itemNuggetAluminum");
		icon[1] = ir.registerIcon(LibInfo.PREFIX + "itemNuggetCopper");
		icon[2] = ir.registerIcon(LibInfo.PREFIX + "itemNuggetTin");
		icon[3] = ir.registerIcon(LibInfo.PREFIX + "itemNuggetZinc");
		icon[4] = ir.registerIcon(LibInfo.PREFIX + "itemNuggetBrass");
		icon[5] = ir.registerIcon(LibInfo.PREFIX + "itemNuggetBronze");
		icon[6] = ir.registerIcon(LibInfo.PREFIX + "itemNuggetSteel");
		icon[7] = ir.registerIcon(LibInfo.PREFIX + "itemNuggetCastIron");
    }

}
