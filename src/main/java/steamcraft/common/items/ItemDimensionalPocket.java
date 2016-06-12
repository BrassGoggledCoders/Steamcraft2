
package steamcraft.common.items;

import boilerplate.api.IOpenableGUI;
import boilerplate.common.baseclasses.items.BaseItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.client.gui.GuiPocket;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.Steamcraft;
import steamcraft.common.tiles.container.ContainerPocket;

public class ItemDimensionalPocket extends BaseItem implements IOpenableGUI
{
	public ItemDimensionalPocket()
	{
		super();
		this.setMaxStackSize(1);
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack)
	{
		return 1;
	}

	/**
	 * Called whenever this item is equipped and the right mouse button is
	 * pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if (!world.isRemote)
		{
			player.openGui(Steamcraft.instance, GuiIDs.POCKET, world, 0, 0, 0);
		}
		return stack;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiPocket((ContainerPocket) this.getServerGuiElement(ID, player, world, x, y, z));
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerPocket(player, player.inventory, new InventoryPocket(player.getHeldItem()));
	}
}
