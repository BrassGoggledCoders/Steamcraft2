package common.steamcraft.mod.common.item;

import common.steamcraft.mod.common.lib.CreativeTabsMod;
import common.steamcraft.mod.common.lib.LibInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemArmorMod extends ItemArmor
{
	EnumArmorMaterial material;
    public String textureFile;

	public ItemArmorMod(int id, EnumArmorMaterial mat, int renderIndex, int armorType, String texture)
	{
		super(id, mat, renderIndex, armorType);
		mat = material;
        textureFile = texture;
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabsMod.tabSCItems);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon)
	{
		this.itemIcon = icon.registerIcon(LibInfo.SC2_PREFIX + this.getUnlocalizedName().substring(5));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack armor, Entity entity, int slot, int layer)
	{
		return textureFile != null ? LibInfo.SC2_PREFIX + "textures/armor/" + textureFile + ".png" : null;
	}
}