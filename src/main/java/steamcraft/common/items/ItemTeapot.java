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
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import boilerplate.common.utils.ItemStackUtils;
import steamcraft.common.init.InitItems;
import steamcraft.common.lib.ModInfo;

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
		this.itemIcon = icon.registerIcon(ModInfo.PREFIX + this.getUnlocalizedName().substring(5));
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(player.inventory.hasItem(InitItems.itemTeacup))
		{
			ItemStack itemstack = player.inventory.getStackInSlot(ItemStackUtils.getStackPosition(player.inventory, InitItems.itemTeacup));
			if((itemstack.getItemDamage() == 0) && (stack.getItemDamage() > 2))
			{
				player.inventory.consumeInventoryItem(itemstack.getItem());
				player.inventory.addItemStackToInventory(new ItemStack(InitItems.itemTeacup, 1, 10));
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
		for(int var4 = 0; var4 < 4; ++var4)
			l.add(new ItemStack(InitItems.itemTeapot, 1, var4));

		l.add(new ItemStack(InitItems.itemTeapot, 1, 12));
	}

	@SuppressWarnings("all")
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List l, boolean flag)
	{
		if(stack.getItemDamage() == 0)
			l.add("Empty");
		else if(stack.getItemDamage() == 1)
			l.add("Filled with Water");
		else if(stack.getItemDamage() == 2)
			l.add("Filled with Boiling Water");
		else
		{
			l.add("Filled with Tea");
			l.add((stack.getItemDamage() - 2) + " cups remaining");
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
