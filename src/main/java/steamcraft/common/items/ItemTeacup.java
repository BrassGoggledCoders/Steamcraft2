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
package steamcraft.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import steamcraft.common.InitAchievements;
import steamcraft.common.InitItems;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTeacup extends BaseItemWithMetadata
{
	IIcon[] itemIcon = new IIcon[2];

	public ItemTeacup()
	{
		super();
		this.setMaxStackSize(1);
		this.setNoRepair();
		this.setFull3D();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon)
	{
		this.itemIcon[0] = icon.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5) + "Empty");
		this.itemIcon[1] = icon.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5) + "Full");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(stack.getItemDamage() > 0)
		{
			player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 50, 100));
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 50, 100));
			stack.setItemDamage(stack.getItemDamage() - 1);
			player.addStat(InitAchievements.teaAchieve, 1);
		}
		return stack;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		l.add(new ItemStack(InitItems.itemTeacup, 1, 0));
	
		l.add(new ItemStack(InitItems.itemTeacup, 1, 10));
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List l, boolean flag)
	{
		if(stack.getItemDamage() == 0)
			l.add("Empty");
		else
		{
			l.add("Filled with Tea");
			l.add(stack.getItemDamage() + " sips remaining");
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		return super.getUnlocalizedName();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta)
	{
		if(meta == 0)
			return this.itemIcon[0];
		else
			return this.itemIcon[1];
	}
}
