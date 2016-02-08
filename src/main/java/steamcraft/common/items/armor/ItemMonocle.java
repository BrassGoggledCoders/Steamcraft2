
package steamcraft.common.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.baseclasses.items.BaseArmor;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.ModInfo;

/**
 * @author Surseance
 *
 */
public class ItemMonocle extends BaseArmor
{
	public ItemMonocle(final ItemArmor.ArmorMaterial armorMat, final int armorType, final int renderIndex)
	{
		super(armorMat, armorType, "");
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type)
	{
		return ModInfo.PREFIX + "textures/models/armor/monocle.png";
	}
}
