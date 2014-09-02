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

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.items.modules.ItemAqualung;
import steamcraft.common.lib.LibInfo;
import boilerplate.steamapi.item.IArmorModule;
import boilerplate.steamapi.item.IArmorModule.EnumArmorEffectType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 *
 */
public class ItemBrassArmor extends BaseArmor
{
	private LinkedHashMap<String, IArmorModule> modules;

	public ItemBrassArmor(ItemArmor.ArmorMaterial armorMat, int renderIndex, int armorType)
	{
		super(armorMat, renderIndex, armorType);
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
		modules = new LinkedHashMap<String, IArmorModule>();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		this.itemIcon = ir.registerIcon(LibInfo.PREFIX + "armor/" + this.getUnlocalizedName().substring(5));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type)
	{
		return type != null ? LibInfo.PREFIX + "textures/armor/" + type + ".png" : null;
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		// if(!ClientHelper.isShiftKeyDown())
		// {
		// list.add(ClientHelper.shiftForInfo);
		// return;
		// }

		if(stack != null)
		{
			list.add("Modules:");
			for(int i = 0; i < getModuleMap((ItemBrassArmor) stack.getItem()).size(); i++)
				list.add(((IArmorModule) this.getEntry(i, (ItemBrassArmor) stack.getItem()).getValue()).getName());
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack is)
	{
		for(int i = 0; i < getModuleMap((ItemBrassArmor) is.getItem()).size(); i++)
			if(((IArmorModule) this.getEntry(i, (ItemBrassArmor) is.getItem()).getValue()).getArmorEffectType() == EnumArmorEffectType.ONTICK)
				((IArmorModule) this.getEntry(i, (ItemBrassArmor) is.getItem()).getValue()).getArmorEffect(world, player, is);
	}
	@SuppressWarnings("all")
	private Entry getEntry(int id, ItemBrassArmor armor)
	{
		Iterator iterator = getModuleMap(armor).entrySet().iterator();
		int n = 0;
		while(iterator.hasNext())
		{
			Entry entry = (Entry) iterator.next();
			if(n == id)
			{
				return entry;
			}
			n++;
		}
		return null;
	}

	public LinkedHashMap<String, IArmorModule> getModuleMap(ItemBrassArmor armor)
	{
		return armor.modules;
	}
}