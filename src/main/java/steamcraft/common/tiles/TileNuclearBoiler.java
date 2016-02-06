
package steamcraft.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import boilerplate.api.IOpenableGUI;
import steamcraft.client.gui.GuiNuclearBoiler;
import steamcraft.common.init.InitItems;
import steamcraft.common.tiles.container.ContainerBaseBoiler;

/**
 * @author Decebaldecebal
 *
 */
public class TileNuclearBoiler extends TileBaseBoiler implements IOpenableGUI
{
	@Override
	protected int getItemBurnTime(ItemStack stack)
	{
		if (stack == null)
			return 0;
		if ((stack.getItem() == InitItems.itemResource) && (stack.getItemDamage() == 5))
			return 9000;
		else
			return 0;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiNuclearBoiler(player.inventory, (TileNuclearBoiler) world.getTileEntity(x, y, z));
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerBaseBoiler(player.inventory, (TileNuclearBoiler) world.getTileEntity(x, y, z));
	}
}
