package steamcraft.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import boilerplate.common.utils.ItemStackUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTeapot extends BaseItem
{
	public ItemTeapot()
	{
		super();
		this.maxStackSize = 1;
		this.setMaxDamage(10);
		this.setNoRepair();
		this.setFull3D();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister icon)
	{
		this.itemIcon = icon.registerIcon(LibInfo.ID + ":" + this.getUnlocalizedName().substring(5));
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
				//ModLoader.getMinecraftInstance().thePlayer.triggerAchievement(mod_Steamcraft.ach_TimeForACuppa);
			if(player.inventory.hasItem(ConfigItems.itemTeacup))
			{
				ItemStack itemstack = player.inventory.getStackInSlot(ItemStackUtils.getStackPosition(player.inventory, ConfigItems.itemTeacup));
				if(itemstack.getItemDamage() < 2)
				{
					int meta = itemstack.getItemDamage() + 1;
					player.inventory.consumeInventoryItem(itemstack.getItem());
					player.inventory.addItemStackToInventory(new ItemStack(ConfigItems.itemTeacup, 1, meta));
					stack.damageItem(1, player);
				}
			}

		return stack;
	}
}
