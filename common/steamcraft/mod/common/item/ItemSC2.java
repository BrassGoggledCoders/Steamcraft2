package common.steamcraft.mod.common.item;

import common.steamcraft.mod.common.lib.SC2_CreativeTabs;
import common.steamcraft.mod.common.lib.SC2_Info;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemSC2 extends Item
{
	public ItemSC2(int id)
	{
		super(id);
		this.setCreativeTab(SC2_CreativeTabs.tabSCItems);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon)
	{
		itemIcon = icon.registerIcon(SC2_Info.SC2_PREFIX + (getUnlocalizedName().substring(5))); // Cannot do a 'this' reflection!
	}
	
	public boolean isSteamPowered() {
		return false;
	}
}
