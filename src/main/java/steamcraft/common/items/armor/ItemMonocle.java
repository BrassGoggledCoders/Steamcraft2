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
package steamcraft.common.items.armor;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance
 * 
 */
public class ItemMonocle extends BaseArmor
{
	public ItemMonocle(final ItemArmor.ArmorMaterial armorMat, final int armorType, final int renderIndex)
	{
		super(armorMat, armorType, renderIndex);
		this.setMaxStackSize(1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type)
	{
		return LibInfo.PREFIX + "textures/armor/monocle.png";
	}
}
