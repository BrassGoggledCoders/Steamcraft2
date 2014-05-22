/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [Apr 13, 2014, 7:31:28 PM]
 */
package steamcraft.common.items.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.MaterialHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Decebaldecebal
 *
 */
public class ItemNormalArmor extends ItemArmor
{
	ArmorMaterial material;

	public ItemNormalArmor(int id, ArmorMaterial mat, int renderIndex, int armorType)
	{
		super(mat, renderIndex, armorType);
		mat = material;
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon)
	{
		this.itemIcon = icon.registerIcon(LibInfo.PREFIX + "armor/" + this.getUnlocalizedName().substring(5));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type)
	{
		if(material == MaterialHelper.ETHERIUM_ARMOR)
			return slot==2 ? LibInfo.PREFIX + "textures/armor/etherium_2.png" :  LibInfo.PREFIX + "textures/armor/etherium_1.png";
		else
			return slot==2 ? LibInfo.PREFIX + "textures/armor/obsidian_2.png" :  LibInfo.PREFIX + "textures/armor/obsidian_1.png";
	}
}
