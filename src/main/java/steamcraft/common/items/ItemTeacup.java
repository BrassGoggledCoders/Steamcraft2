
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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import steamcraft.common.init.InitAchievements;
import steamcraft.common.init.InitItems;
import steamcraft.common.lib.ModInfo;

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
		this.itemIcon[0] = icon.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5) + "Empty");
		this.itemIcon[1] = icon.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5) + "Full");
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (stack.getItemDamage() > 0)
		{
			player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 50, 10));
			player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 50, 10));
			player.getFoodStats().addStats(1, 0);
			world.playSoundAtEntity(player, "random.burp", 0.5F, (world.rand.nextFloat() * 0.1F) + 0.9F);
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
		if (stack.getItemDamage() == 0)
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
		if (meta == 0)
			return this.itemIcon[0];
		else
			return this.itemIcon[1];
	}
}
