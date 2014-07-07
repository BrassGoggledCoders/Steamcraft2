package steamcraft.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.utils.ItemStackUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTeapot extends BaseItemWithMetadata
{

	public ItemTeapot()
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
		this.itemIcon = icon.registerIcon(LibInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
				//ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(mod_Steamcraft.ach_TimeForACuppa);
			if(player.inventory.hasItem(ConfigItems.itemTeacup))
			{
				ItemStack itemstack = player.inventory.getStackInSlot(ItemStackUtils.getStackPosition(player.inventory, ConfigItems.itemTeacup));
				if(itemstack.getItemDamage() == 0 && stack.getItemDamage() > 2)
				{
					player.inventory.consumeInventoryItem(itemstack.getItem());
					player.inventory.addItemStackToInventory(new ItemStack(ConfigItems.itemTeacup, 1, 10));
					if(stack.getItemDamage() == 3)
					stack.setItemDamage(0);
					else
					stack.setItemDamage(stack.getItemDamage() - 1);
				}
			}

		return stack;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List l)
	{
		for (int var4 = 0; var4 < 13; ++var4)
			l.add(new ItemStack(ConfigItems.itemTeapot, 1, var4));
	}
	@Override
	// TODO: Make module-sensitive
	public void addInformation(ItemStack stack, EntityPlayer player, List l, boolean flag)
	{
		if(stack.getItemDamage() == 0)
		{
			l.add("Empty");
		}
		else if(stack.getItemDamage() == 1)
		{
			l.add("Filled with Water");
		}
		else if(stack.getItemDamage() == 2)
		{
			l.add("Filled with Boiling Water");
		}
		else
		{
			l.add("Filled with Tea");
			l.add(stack.getItemDamage()-2 + " cups remaining");
			l.add("Right-click with this to fill");
			l.add("teacups in your inventory");
		}
	}
	@Override
	public String getUnlocalizedName(ItemStack is)
	{
		return super.getUnlocalizedName();
	}
}
