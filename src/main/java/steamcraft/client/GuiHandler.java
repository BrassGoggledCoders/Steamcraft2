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
package steamcraft.client;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import steamcraft.client.gui.GuiVanity;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.container.ContainerVanity;
import steamcraft.common.entities.EntityPlayerExtended;

/**
 * @author Surseance
 *
 */
public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(final int id, final EntityPlayer player, final World world, final int x, final int y, final int z)
	{
		Object object = world.getTileEntity(x, y, z);

		if(object instanceof IOpenableGUI && ((IOpenableGUI) object).getGuiID() == id)
		{
			return ((IOpenableGUI) object).getServerGuiElement(id, player, world, x, y, z);
		}

		ItemStack itemStack = player.getHeldItem();

		if(itemStack != null && itemStack.getItem() instanceof IOpenableGUI && ((IOpenableGUI) itemStack.getItem()).getGuiID() == id)
		{
			return ((IOpenableGUI) itemStack.getItem()).getServerGuiElement(id, player, world, x, y, z);
		}

		switch(id)
		{
			case GuiIDs.VANITY:
				return new ContainerVanity(player, player.inventory, EntityPlayerExtended.get(player).getInventory());
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(final int id, final EntityPlayer player, final World world, final int x, final int y, final int z)
	{
		Object object = world.getTileEntity(x, y, z);

		if(object instanceof IOpenableGUI && ((IOpenableGUI) object).getGuiID() == id)
		{
			return ((IOpenableGUI) object).getClientGuiElement(id, player, world, x, y, z);
		}

		ItemStack itemStack = player.getHeldItem();

		if(itemStack != null && itemStack.getItem() instanceof IOpenableGUI && ((IOpenableGUI)itemStack.getItem()).getGuiID() == id)
		{
			return ((IOpenableGUI)itemStack.getItem()).getClientGuiElement(id, player, world, x, y, z);
		}

		switch(id)
		{
			case GuiIDs.VANITY:
				return new GuiVanity(player, player.inventory, EntityPlayerExtended.get(player).getInventory());
		}
		return null;
	}
}
