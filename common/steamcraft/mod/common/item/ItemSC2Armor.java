package common.steamcraft.mod.common.item;

import common.steamcraft.mod.common.lib.SC2_CreativeTabs;
import common.steamcraft.mod.common.lib.SC2_Info;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemSC2Armor extends ItemArmor
{
	EnumArmorMaterial material;
    public String textureFile;

	public ItemSC2Armor(int id, EnumArmorMaterial mat, int renderIndex, int armorType, String texture)
	{
		super(id, mat, renderIndex, armorType);
		mat = material;
        textureFile = texture;
		this.setMaxStackSize(1);
		this.setCreativeTab(SC2_CreativeTabs.tabSCItems);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon)
	{
		this.itemIcon = icon.registerIcon(SC2_Info.SC2_PREFIX + this.getUnlocalizedName().substring(5));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack armor, Entity entity, int slot, int layer)
	{
		return textureFile != null ? SC2_Info.SC2_PREFIX + "textures/armor/" + textureFile + ".png" : null;
	}
}