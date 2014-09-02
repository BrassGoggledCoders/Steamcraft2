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

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import steamcraft.common.Steamcraft;
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
	public static ArrayList<String> moduleNames = new ArrayList<String>();
	public static ArrayList<IArmorModule> modules = new ArrayList<IArmorModule>();

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
		if(stack != null)
		{
			list.add("Modules:");
			for(int i = 0; i < modules.size(); i++)
				list.add(modules.get(i).getName());
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack is)
	{
		for(int i = 0; i < modules.size(); i++)
		{
			if(modules.get(i).getArmorEffectType() != null && modules.get(i).getArmorEffectType() == EnumArmorEffectType.ONTICK)
				modules.get(i).getArmorEffect(world, player, is);
		}
	}
	public void writeToNBT(NBTTagCompound comp)
	{
	 //super.writeToNBT(comp);
	 NBTTagList tagList = new NBTTagList();
	 for(int i = 0; i < modules.size(); i++)
	 {
	  IArmorModule module = modules.get(i);
	  if(module != null)
	  {
	   NBTTagCompound tag = new NBTTagCompound();
	   tag.setString("ModuleName" + i, module.getName());
	   tagList.appendTag(tag);
	  }
	 }
	 comp.setTag("ModuleNameList", tagList);
	}

	public void readFromNBT(NBTTagCompound comp)
	{
	 //super.readFromNBT(comp);
	 NBTTagList tagList = comp.getTagList("ModuleNameList", Constants.NBT.TAG_LIST);
	 for(int i = 0; i < tagList.tagCount(); i++)
	 {
	  NBTTagCompound tag = tagList.getCompoundTagAt(i);
	  String s = tag.getString("ModuleName" + i);

	  modules.add(i, modules.get(i));
	 }
	}
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int par4, boolean par5)
	{

		if(!stack.hasTagCompound())
		stack.setTagCompound(new NBTTagCompound());

		stack.readFromNBT(stack.stackTagCompound);
	}
}
