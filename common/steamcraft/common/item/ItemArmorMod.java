package common.steamcraft.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import common.steamcraft.client.core.helper.ClientHelper;
import common.steamcraft.common.lib2.CreativeTabsMod;
import common.steamcraft.common.lib2.LibInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @authors someone Edited by decebaldecebal.
 *
 */

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
	
	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		if(!ClientHelper.isShiftKeyDown())
		{
			list.add(ClientHelper.shiftForInfo);
			return;
		}
		if(stack != null)
		{
			if(stack.getItem() == ModArmors.brassGoggles)
			{
				list.add("It is a violation of ");
				list.add("the law of steampunk");
				list.add("to fly without these");
				//TODO: Make this work to help seeing underwater + at night
				list.add("Helps with seeing things");
			}
			else if(stack.getItem() == ModArmors.aqualung)
			{
				list.add("This allows underwater breathing");
				list.add("for as long as the durability lasts");
			}
			else if(stack.getItem() == ModArmors.legBraces)
			{
				list.add("A set of mechanical pistons");
				list.add("and rods to help reduce damage");
				list.add("substained from falling");
			}
			else if(stack.getItem() == ModArmors.rollerSkates)
			{
				list.add("Increases movement speed");
			}
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
		if(stack.getItem() == ModArmors.aqualung)
		{
			if(player.getAir() <= 0)
			{
				player.setAir(300);
				stack.damageItem(4, player); //tweak the damage taken a bit
			}
		}
		else if (stack.getItem() == ModArmors.legBraces) 
        {
            if (player.fallDistance > 3.0F) 
            {
                player.fallDistance *= 0.888F;
                stack.damageItem(1, player);
            }
        }
    }
}