package steamcraft.common.items;

import java.util.List;

import javax.swing.Icon;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemSheet extends BaseItemWithMetadata
{
    @SideOnly(Side.CLIENT)
    private IIcon[] icon = new IIcon[8];

    public ItemSheet() {
	super();
	setUnlocalizedName("itemSheet");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, final CreativeTabs tab, final List list)
    {
    	for (int var4 = 0; var4 < 8; ++var4)
		{
			list.add(new ItemStack(ConfigItems.itemSheet, 1, var4));
		}
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir)
    {
		icon[0] = ir.registerIcon(LibInfo.PREFIX + "itemSheetAluminum");
		icon[1] = ir.registerIcon(LibInfo.PREFIX + "itemSheetCopper");
		icon[2] = ir.registerIcon(LibInfo.PREFIX + "itemSheetTin");
		icon[3] = ir.registerIcon(LibInfo.PREFIX + "itemSheetZinc");
		icon[4] = ir.registerIcon(LibInfo.PREFIX + "itemSheetBrass");
		icon[5] = ir.registerIcon(LibInfo.PREFIX + "itemSheetBronze");
		icon[6] = ir.registerIcon(LibInfo.PREFIX + "itemSheetSteel");
		icon[7] = ir.registerIcon(LibInfo.PREFIX + "itemSheetCastIron");
    }

}
