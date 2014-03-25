/*
 * @author warlordjones
 * 
 * Using this source for addon development or examples/education is cool with me. 
 * Taking this source code and claiming it is yours isn't cool!

 */
package common.steamcraft.common.item;

import java.util.List;

import common.steamcraft.common.lib2.LibInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;

public class ItemMetalIngots extends ItemMod {
    public static final String[] names = new String[] { "aluminum", "copper", "tin", "zinc",
	    "castIron", "brass", "bronze", "steel"};
    @SideOnly(Side.CLIENT)
    private Icon[] icons;
    int number_parts = 8;

    public ItemMetalIngots(final int id) {
	super(id);
	setHasSubtypes(true);
    }

    @Override
    public Icon getIconFromDamage(final int par1) {
	return icons[par1];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(final int par1,
	    final CreativeTabs par2CreativeTabs, final List par3List) {
	for (int x = 0; x < number_parts; x++)
	    par3List.add(new ItemStack(this, 1, x));
    }

    @Override
    public String getUnlocalizedName(final ItemStack par1ItemStack) {
	final int i = MathHelper
		.clamp_int(par1ItemStack.getItemDamage(), 0, 15);
	return super.getUnlocalizedName() + "." + ItemMetalIngots.names[i];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IconRegister par1IconRegister) {
	icons = new Icon[number_parts];
	for (int i = 0; i < icons.length; i++)
	    icons[i] = par1IconRegister.registerIcon(LibInfo.SC2_PREFIX
		    + this.getUnlocalizedName().substring(5) + i);
    }
}