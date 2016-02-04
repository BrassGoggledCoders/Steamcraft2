
package steamcraft.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.init.InitItems;
import steamcraft.common.lib.ModInfo;

/**
 * @author warlordjones
 *
 */
public class ItemResource extends BaseItemWithMetadata
{
	IIcon[] itemIcon = new IIcon[5];

	public ItemResource()
	{
		super();
		this.setMaxStackSize(64);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.itemIcon[0] = ir.registerIcon(ModInfo.PREFIX + "itemSulphur");
		this.itemIcon[1] = ir.registerIcon(ModInfo.PREFIX + "itemPhosphorus");
		this.itemIcon[2] = ir.registerIcon(ModInfo.PREFIX + "itemSulfuricAcid");
		this.itemIcon[3] = ir.registerIcon(ModInfo.PREFIX + "itemUranium");
		this.itemIcon[4] = ir.registerIcon(ModInfo.PREFIX + "itemPellet");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		for (int var4 = 0; var4 < this.itemIcon.length; ++var4)
			l.add(new ItemStack(InitItems.itemResource, 1, var4));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int itemDamage)
	{
		return this.itemIcon[itemDamage];
	}
}
