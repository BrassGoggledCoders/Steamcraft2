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
package xyz.brassgoggledcoders.steamcraft.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.steamcraft.client.gui.GuiHandbook;
import xyz.brassgoggledcoders.steamcraft.client.lib.GuiIDs;
import xyz.brassgoggledcoders.steamcraft.common.Steamcraft;
import boilerplate.api.IOpenableGUI;

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
