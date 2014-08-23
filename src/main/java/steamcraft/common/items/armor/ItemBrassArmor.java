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
import steamcraft.common.lib.LibInfo;
import boilerplate.steamapi.EnumArmorEffectType;
import boilerplate.steamapi.IModule;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author warlordjones
 * 
 */
public class ItemBrassArmor extends BaseArmor
{
	public static LinkedHashMap<String, IModule> modules = new LinkedHashMap<String, IModule>();

	public ItemBrassArmor(ItemArmor.ArmorMaterial armorMat, int renderIndex, int armorType)
	{
		super(armorMat, renderIndex, armorType);
		this.setMaxStackSize(1);
		this.setCreativeTab(Steamcraft.tabSC2);
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
			for(int i = 0; i < ItemBrassArmor.modules.size(); i++)
				list.add(((IModule) this.getEntry(i).getValue()).getName());
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack is)
	{
		// System.out.print(modules);
		for(int i = 0; i < ItemBrassArmor.modules.size(); i++)
			if(((IModule) this.getEntry(i).getValue()).getArmorEffectType() == EnumArmorEffectType.ONTICK)
				((IModule) this.getEntry(i).getValue()).getArmorEffect(world, player, is);
	}

	/*
	 * @Override public void onUpdate(ItemStack stack, World p_77663_2_, Entity p_77663_3_, int p_77663_4_, boolean p_77663_5_) { for(int i = 0; i <
	 * modules.size(); i++) { //stack.stackTagCompound.setString("module" + i, modules.get(i).getName()); modules.set(i,
	 * getModuleFromName(stack.stackTagCompound.getString("module" + 1))); } }
	 */

	/*
	 * private IModule getModuleFromName(String string) { for (int i = 0; i < this.modules.size(); i++) if (this.modules.get(i).getName() == string) return
	 * this.modules.get(i); else return null; return null; }
	 */
	/*
	 * @Override public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5) { // EntityPlayer player = (EntityPlayer) entity; if
	 * (!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound()); NBTTagCompound tag = stack.getTagCompound(); /*NBTTagList list = (NBTTagList)
	 * modules; tag.setTag("modules", list); modules = (List<IModule>) tag.getTagList("modules", 9); for(int i=0; i< modules.size(); i++) { //Write NBT
	 * tag.setString("module" + Integer.toString(i), modules.get(i).getName()); //Read NBT //modules.get(i) = tag.getString("module" + Integer.toString(i)); } }
	 */
	@SuppressWarnings("all")
	private Entry getEntry(int id)
	{
		Iterator iterator = modules.entrySet().iterator();
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
}
