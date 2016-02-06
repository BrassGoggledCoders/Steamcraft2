
package steamcraft.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;

import boilerplate.api.IOpenableGUI;
import steamcraft.client.gui.GuiSteamBoiler;
import steamcraft.common.tiles.container.ContainerBaseBoiler;

/**
 * @author Decebaldecebal
 *
 */
public class TileSteamBoiler extends TileBaseBoiler implements IOpenableGUI
{
	@Override
	protected int getItemBurnTime(ItemStack stack)
	{
		if (stack == null)
			return 0;

		return TileEntityFurnace.getItemBurnTime(this.inventory[0]);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiSteamBoiler(player.inventory, (TileSteamBoiler) world.getTileEntity(x, y, z));
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerBaseBoiler(player.inventory, (TileSteamBoiler) world.getTileEntity(x, y, z));
	}
}
