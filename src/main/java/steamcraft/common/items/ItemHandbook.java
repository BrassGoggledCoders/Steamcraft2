
package steamcraft.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import boilerplate.api.IOpenableGUI;
import boilerplate.common.baseclasses.items.BaseItem;
import steamcraft.client.gui.GuiHandbook;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.Steamcraft;

public class ItemHandbook extends BaseItem implements IOpenableGUI
{
	int currPage;

	public ItemHandbook()
	{
		super();
		this.setMaxStackSize(1);
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (world.isRemote)
		{
			player.openGui(Steamcraft.instance, GuiIDs.HANDBOOK, world, 0, 0, 0);
		}
		return stack;
	}

	public int getCurrentPage(ItemStack stack)
	{
		if (stack.hasTagCompound())
			return stack.getTagCompound().getInteger("currPage");
		else
			return 0;
	}

	public void setCurrentPage(ItemStack stack, int currPage)
	{
		if (!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		stack.getTagCompound().setInteger("currPage", currPage);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiHandbook(player.getHeldItem());
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return null;
	}
}
