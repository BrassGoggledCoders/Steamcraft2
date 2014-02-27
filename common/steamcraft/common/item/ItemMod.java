package common.steamcraft.common.item;

import common.steamcraft.common.lib2.CreativeTabsMod;
import common.steamcraft.common.lib2.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemMod extends Item
{
	public ItemMod(int id)
	{
		super(id);
		this.setCreativeTab(CreativeTabsMod.tabSCItems);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon)
	{
		itemIcon = icon.registerIcon(LibInfo.SC2_PREFIX + (getUnlocalizedName().substring(5))); // Cannot do a 'this' reflection!
	}
	
	public boolean isSteamPowered() {
		return false;
	}
}
