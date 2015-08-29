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

import boilerplate.api.IOpenableGUI;
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
	 * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		if(!world.isRemote)
		{
			player.openGui(Steamcraft.instance, GuiIDs.POCKET, world, 0, 0, 0);
		}
		return stack;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiPocket((ContainerPocket)getServerGuiElement(ID, player, world, x, y, z));
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerPocket(player, player.inventory, new InventoryPocket(player.getHeldItem()));
	}
}
