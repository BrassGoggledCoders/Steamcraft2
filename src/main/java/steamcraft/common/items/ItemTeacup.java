package steamcraft.common.items;

import java.util.List;

import steamcraft.common.config.ConfigItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemTeacup extends BaseItemWithMetadata
{
	public ItemTeacup()
	{
		super();
		this.setMaxStackSize(1);
		this.setNoRepair();
		this.setFull3D();
	}
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(stack.getItemDamage() > 0)
		{
			player.addPotionEffect(new PotionEffect(Potion.digSpeed.id, 50, 100));
			stack.damageItem(1, player);
		}
		return stack;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		for (int var4 = 0; var4 < 11; ++var4)
		l.add(new ItemStack(ConfigItems.itemTeacup, 1, var4));
	}
	@Override
	// TODO: Make module-sensitive
	public void addInformation(ItemStack stack, EntityPlayer player, List l, boolean flag)
	{
		if(stack.getItemDamage() == 0)
		{
			l.add("Empty");
		}
		else
		{
			l.add("Filled with Tea");
			l.add(stack.getItemDamage() + " sips remaining");
		}
	}
}
