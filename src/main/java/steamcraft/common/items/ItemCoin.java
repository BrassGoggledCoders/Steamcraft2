
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
public class ItemCoin extends BaseItemWithMetadata
{
	IIcon[] itemIcon = new IIcon[13];

	@SuppressWarnings("all")
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list)
	{
		for (int var4 = 0; var4 < 13; ++var4)
			list.add(new ItemStack(InitItems.itemCoin, 1, var4));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.itemIcon[0] = ir.registerIcon(ModInfo.PREFIX + "itemCoinFarthing");
		this.itemIcon[1] = ir.registerIcon(ModInfo.PREFIX + "itemCoinHalfpenny");
		this.itemIcon[2] = ir.registerIcon(ModInfo.PREFIX + "itemCoinPenny");
		this.itemIcon[3] = ir.registerIcon(ModInfo.PREFIX + "itemCoinTuppence");
		this.itemIcon[4] = ir.registerIcon(ModInfo.PREFIX + "itemCoinGroat");
		this.itemIcon[5] = ir.registerIcon(ModInfo.PREFIX + "itemCoinThripenny");
		this.itemIcon[6] = ir.registerIcon(ModInfo.PREFIX + "itemCoinSixpence");
		this.itemIcon[7] = ir.registerIcon(ModInfo.PREFIX + "itemCoinShilling");
		this.itemIcon[8] = ir.registerIcon(ModInfo.PREFIX + "itemCoinFlorin");
		this.itemIcon[9] = ir.registerIcon(ModInfo.PREFIX + "itemCoinHalfCrown");
		this.itemIcon[10] = ir.registerIcon(ModInfo.PREFIX + "itemCoinCrown");
		this.itemIcon[11] = ir.registerIcon(ModInfo.PREFIX + "itemCoinHalfSovereign");
		this.itemIcon[12] = ir.registerIcon(ModInfo.PREFIX + "itemCoinSovereign");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int itemDamage)
	{
		return this.itemIcon[itemDamage];
	}

}
