package common.steamcraft.common.item;

import common.steamcraft.common.lib2.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemKettle extends Item
{
	public ItemKettle(int id, int damage)
	{
		super(id);
		this.maxStackSize = 1;
		this.setMaxDamage(damage);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister icon)
	{
		this.itemIcon = icon.registerIcon(LibInfo.MOD_ID + ":" + (this.getUnlocalizedName().substring(5)));
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(stack.getItem().itemID == ModItems.hotKettle.itemID && stack.getItemDamage() < stack.getItem().getMaxDamage() - 5)
		{
			if(getStackPosition(player.inventory, ModItems.emptyTeacup) > -1)
			{
				//ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(mod_Steamcraft.ach_TimeForACuppa);
				player.inventory.setInventorySlotContents(getStackPosition(player.inventory, ModItems.emptyTeacup), new ItemStack(ModItems.fullTeacup, 1));
				stack.damageItem((stack.getItem().getMaxDamage() / 3) - 1, player);
			}
			if(stack.getItemDamage() >= stack.getItem().getMaxDamage() - 5)
			{
				stack = new ItemStack(ModItems.emptyKettle, 1, stack.getItemDamage());
			}
		}
		
		return stack;
	}

	public int getStackPosition(InventoryPlayer inventory, Item item)
	{
		for(int i = 0; i < inventory.getSizeInventory(); i++)
		{
			if(inventory.getStackInSlot(i) != null && item == inventory.getStackInSlot(i).getItem())
			{
				return i;
			}
		}
		
		return -1;
	}
}