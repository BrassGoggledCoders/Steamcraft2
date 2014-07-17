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
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.common.InitItems;
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
	private IIcon[] icon = new IIcon[3];

	List<Item> modules = new ArrayList<Item>();

	public ItemBrassArmor(ItemArmor.ArmorMaterial armorMat, int renderIndex, int armorType)
	{
		super(armorMat, renderIndex, armorType);
		setMaxStackSize(1);
		setCreativeTab(Steamcraft.tabSC2);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir)
	{
		itemIcon = ir.registerIcon(LibInfo.PREFIX + "armor/" + this.getUnlocalizedName().substring(5));
	}

	@Override
	@SideOnly(Side.CLIENT)
	public String getArmorTexture(ItemStack is, Entity entity, int slot, String type)
	{
		return type != null ? LibInfo.PREFIX + "textures/armor/" + type + ".png" : null;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
		modules.add(InitItems.itemAqualung);
		return par1ItemStack;
	}

	@SuppressWarnings("all")
	@Override
	// TODO: Make module-sensitive
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean flag)
	{
		// if(!ClientHelper.isShiftKeyDown())
		// {
		// list.add(ClientHelper.shiftForInfo);
		// return;
		// }

		if (stack != null)
		{
			list.add("Modules:");
			for(int i=0; i<modules.size(); i++)
			{
				list.add(((IModule)modules.get(i)).getName());
			}
			/*
			 * if (stack.getItem() == ConfigItems.itemBrassGoggles) {
			 * list.add("It is a violation of");
			 * list.add("the law of steampunk");
			 * list.add("to fly without these."); // TODO: Make this work to
			 * help seeing underwater + at night
			 * list.add("Helps with seeing things."); } else if (stack.getItem()
			 * == ConfigItems.itemAqualung) {
			 * list.add("This allows underwater breathing");
			 * list.add("for as long as the durability lasts."); } else if
			 * (stack.getItem() == ConfigItems.itemLegBraces) {
			 * list.add("A set of mechanical pistons");
			 * list.add("and rods to help reduce damage");
			 * list.add("substained from falling."); } else if (stack.getItem()
			 * == ConfigItems.itemRollerSkates) {
			 * list.add("Increases movement speed."); } else if (stack.getItem()
			 * == ConfigItems.itemSteamJetpack) {
			 * list.add("Steam-powered Flight!");
			 * list.add("Uses steam from canisters."); } else if(stack.getItem()
			 * == ConfigItems.itemPneumaticBoots) {
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
			 * else { list.add("This armour has no documented abilities."); }
			 */
		}
	}

	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack is)
	{
		for(int i=0; i<modules.size(); i++)
		{
			if(((IModule)modules.get(i)).getArmorEffectType() == EnumArmorEffectType.ONTICK)
			{
				((IModule)modules.get(i)).getArmorEffect(world, player, is);
			}
		}
	}
}
