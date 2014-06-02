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

import java.util.HashMap;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemCustomArmor.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class ItemBrassArmor extends ItemArmor
{

	/** The icon. */
	private  IIcon[] icon = new IIcon[3];

	/** The mat. */
	ItemArmor.ArmorMaterial mat;

	@SuppressWarnings("rawtypes")
	HashMap modules = new HashMap();


	/**
	 * Instantiates a new item custom armor.
	 *
	 * @param armorMat the armor mat
	 * @param renderIndex the render index
	 * @param armorType the armor type
	 */
	public ItemBrassArmor(ItemArmor.ArmorMaterial armorMat,
			int renderIndex, int armorType)
	{
		super(armorMat, renderIndex, armorType);
		setMaxStackSize(1);
		mat = armorMat;
		setCreativeTab(Steamcraft.tabSC2);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.ItemArmor#registerIcons(net.minecraft.client.renderer.texture.IIconRegister)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons( IIconRegister ir)
	{
		itemIcon = ir.registerIcon(LibInfo.PREFIX + "armor/"
				+ this.getUnlocalizedName().substring(5));
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#getArmorTexture(net.minecraft.item.ItemStack, net.minecraft.entity.Entity, int, java.lang.String)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture( ItemStack is,  Entity entity,
			 int slot, String type)
	{
		return type != null ? LibInfo.PREFIX + "textures/armor/" + type
				+ ".png" : null;
	}
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	this.modules.put("aqualung", true);
        return par1ItemStack;
    }

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#addInformation(net.minecraft.item.ItemStack, net.minecraft.entity.player.EntityPlayer, java.util.List, boolean)
	 */
	@SuppressWarnings("all")
	@Override
	//TODO: Make module-sensitive
	public void addInformation( ItemStack stack,
			 EntityPlayer player,  List list,  boolean flag)
	{
		// if(!ClientHelper.isShiftKeyDown())
		// {
		// list.add(ClientHelper.shiftForInfo);
		// return;
		// }

		if (stack != null)
		{
			/*
			if (stack.getItem() == ConfigItems.itemBrassGoggles)
			{
				list.add("It is a violation of");
				list.add("the law of steampunk");
				list.add("to fly without these.");
				// TODO: Make this work to help seeing underwater + at night
				list.add("Helps with seeing things.");
			}
			else if (stack.getItem() == ConfigItems.itemAqualung)
			{
				list.add("This allows underwater breathing");
				list.add("for as long as the durability lasts.");
			}
			else if (stack.getItem() == ConfigItems.itemLegBraces)
			{
				list.add("A set of mechanical pistons");
				list.add("and rods to help reduce damage");
				list.add("substained from falling.");
			}
			else if (stack.getItem() == ConfigItems.itemRollerSkates)
			{
				list.add("Increases movement speed.");
			}
			else if (stack.getItem() == ConfigItems.itemSteamJetpack)
			{
				list.add("Steam-powered Flight!");
				list.add("Uses steam from canisters.");
			}
			 * else if(stack.getItem() == ConfigItems.itemPneumaticBoots) {
			 * list.add("A set of pistons strapped");
			 * list.add("to your feet increase the");
			 * list.add("height of jumps");
			 * list.add("Also allows automatic step up"); list.add("one-block");
			 * } else if(stack.getItem() == ModArmors.brassWings) {
			 * list.add("Hand-Powered Flight - uses Hunger"); //TODO: Implement
			 * this!
			 * list.add("Can also be used to glide - without using power"); } /*
			 * else if(stack.getItem() == ModArmors.steamWings) {
			 * list.add("A combination of the jetpack and wings");
			 * list.add("Uses fuel. No flight limit.");
			 * list.add("Is also able to glide"); } else if(stack.getItem() ==
			 * ModArmors.climbingClaws) {
			 * list.add("Allows you to grip onto and slide down walls"); } else
			 * if(stack.getItem() == ModArmors.jetBoots) {
			 * list.add("Pseudo Flight");
			 * list.add("It's really a very large jump");
			 * list.add("Uses power from canisters"); } else if(stack.getItem()
			 * == ModArmors.stilts) { list.add("Be three blocks tall!");
			 * list.add("A little inconvient indoors");
			 * list.add("Good for wading through water"); } else
			 * if(stack.getItem() == ModArmors.reactivePistonPlate) {
			 * list.add("Pistons attached to this chestplate");
			 * list.add("push back mobs that attack you") }
			 *
			else
			{
				list.add("This armour has no documented abilities.");
			}*/
		}
	}

	/* (non-Javadoc)
	 * @see net.minecraft.item.Item#onArmorTick(net.minecraft.world.World, net.minecraft.entity.player.EntityPlayer, net.minecraft.item.ItemStack)
	 */
	@Override
	public void onArmorTick( World world,  EntityPlayer player,
			 ItemStack is)
	{
		if (modules.get("aqualung") != null && (boolean) this.modules.get("aqualung"))
		{
			if (player.getAir() <= 0)
			{
				player.setAir(300);
				is.damageItem(4, player); // tweak the damage taken a bit
			}
		}
		/*
		else if (is.getItem() == ConfigItems.itemLegBraces)
		{
			if (player.fallDistance > 3.0F)
			{
				player.fallDistance *= 0.888F;
				is.damageItem(1, player);
			}
		}*/
	}
}
