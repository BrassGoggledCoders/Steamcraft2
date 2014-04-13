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
 * File created @ [Apr 4, 2014, 11:14:50 PM]
 */
package steamcraft.common.items.armor;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class ItemCustomArmor extends ItemArmor
{
	private Icon[] icon = new Icon[10];
	EnumArmorMaterial mat;
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int damage)
	{
		return this.icon[damage];
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister ir)
	{
		this.itemIcon = ir.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type)
	{
		//This is only for brass armor(first four armors), the other stuff should return their file somehow...
		//Maybe based on material
		return slot==2 ? LibInfo.PREFIX + "textures/armor/brass_2.png" :  LibInfo.PREFIX + "textures/armor/brass_1.png";
	}

	public ItemCustomArmor(int id, EnumArmorMaterial armorMat, int renderIndex, int armorType)
	{
		super(id, armorMat, renderIndex, armorType);
		this.mat = armorMat;
		this.setCreativeTab(Steamcraft.tabSC2);
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		//if(!ClientHelper.isShiftKeyDown())
		//{
		//	list.add(ClientHelper.shiftForInfo);
		//	return;
		//}

		if (stack != null)
		{
			if (stack.getItem() == ConfigItems.itemBrassGoggles)
			{
				list.add("It is a violation of ");
				list.add("the law of steampunk");
				list.add("to fly without these");
				//TODO: Make this work to help seeing underwater + at night
				list.add("Helps with seeing things");
			}
			else if(stack.getItem() == ConfigItems.itemAqualung)
			{
				list.add("This allows underwater breathing");
				list.add("for as long as the durability lasts");
			}
			else if(stack.getItem() == ConfigItems.itemLegBraces)
			{
				list.add("A set of mechanical pistons");
				list.add("and rods to help reduce damage");
				list.add("substained from falling");
			}
			else if(stack.getItem() == ConfigItems.itemRollerSkates)
			{
				list.add("Increases movement speed");
			}
			/*
			else if(stack.getItem() == ModArmors.pnematicBoots)
			{
				list.add("A set of pistons strapped");
				list.add("to your feet increase the");
				list.add("height of jumps");
				list.add("Also allows automatic step up");
				list.add("one-block");
			}
			else if(stack.getItem() == ModArmors.brassWings)
			{
				list.add("Hand-Powered Flight - uses Hunger");
				//TODO: Implement this!
				list.add("Can also be used to glide - without using power");
			}
			else if(stack.getItem() == ModArmors.jetpack)
			{
				list.add("Steam-powered Flight!");
				list.add("Uses power from canisters");
			}
			/*
			 * else if(stack.getItem() == ModArmors.steamWings)
			 * {
			 * 	list.add("A combination of the jetpack and wings");
			 * 	list.add("Uses fuel. No flight limit.");
			 * 	list.add("Is also able to glide");
			 *  }
			else if(stack.getItem() == ModArmors.climbingClaws)
			{
				list.add("Allows you to grip onto and slide down walls");
			}
			else if(stack.getItem() == ModArmors.jetBoots)
			{
				list.add("Pseudo Flight");
				list.add("It's really a very large jump");
				list.add("Uses power from canisters");
			}
			else if(stack.getItem() == ModArmors.stilts)
			{
				list.add("Be three blocks tall!");
				list.add("A little inconvient indoors");
				list.add("Good for wading through water");
			}
			else if(stack.getItem() == ModArmors.reactivePistonPlate)
			{
				list.add("Pistons attached to this chestplate");
				list.add("push back mobs that attack you")
			}

			 */
			else
			{
				list.add("This armour has no documented abilities.");
			}
		}
	}

	@Override
	public void onArmorTickUpdate(World world, EntityPlayer player, ItemStack stack)
	{
		if(stack.getItem() == ConfigItems.itemAqualung)
		{
			if(player.getAir() <= 0)
			{
				player.setAir(300);
				stack.damageItem(4, player); //tweak the damage taken a bit
			}
		}
		else if (stack.getItem() == ConfigItems.itemLegBraces) 
		{
			if (player.fallDistance > 3.0F) 
			{
				player.fallDistance *= 0.888F;
				stack.damageItem(1, player);
			}
		}
	}
}
