/**
 * This class was created by BrassGoggledCoders modding team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 */
package steamcraft.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import steamcraft.common.InitItems;
import steamcraft.common.lib.ModInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance
 * 
 */
public class ItemGunPart extends BaseItemWithMetadata
{
	IIcon[] itemIcon = new IIcon[12];

	public ItemGunPart()
	{
		super();
		this.setMaxStackSize(64);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.itemIcon[0] = ir.registerIcon(ModInfo.PREFIX + "itemStock");
		this.itemIcon[1] = ir.registerIcon(ModInfo.PREFIX + "itemMusketBarrel");
		this.itemIcon[2] = ir.registerIcon(ModInfo.PREFIX + "itemRifleBarrel");
		this.itemIcon[3] = ir.registerIcon(ModInfo.PREFIX + "itemLock");
		this.itemIcon[4] = ir.registerIcon(ModInfo.PREFIX + "itemBrassGunFrame");
		this.itemIcon[5] = ir.registerIcon(ModInfo.PREFIX + "itemCastIronGunFrame");
		this.itemIcon[6] = ir.registerIcon(ModInfo.PREFIX + "itemGreenFocus");
		this.itemIcon[7] = ir.registerIcon(ModInfo.PREFIX + "itemBlueFocus");
		this.itemIcon[8] = ir.registerIcon(ModInfo.PREFIX + "itemPowerCore");
		this.itemIcon[9] = ir.registerIcon(ModInfo.PREFIX + "itemOverchargedPowerCore");
		this.itemIcon[10] = ir.registerIcon(ModInfo.PREFIX + "itemPistolBarrel");
		this.itemIcon[11] = ir.registerIcon(ModInfo.PREFIX + "itemShortStock");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		for(int var4 = 0; var4 < this.itemIcon.length; ++var4)
			l.add(new ItemStack(InitItems.itemGunPart, 1, var4));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int itemDamage)
	{
		return this.itemIcon[itemDamage];
	}
}
