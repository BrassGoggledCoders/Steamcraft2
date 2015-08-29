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
package steamcraft.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import boilerplate.api.IOpenableGUI;
import steamcraft.client.gui.GuiLiquidBoiler;
import steamcraft.common.tiles.container.ContainerLiquidBoiler;

/**
 * @author Decebaldecebal
 *
 */
public class TileLiquidBoiler extends TileBaseBoiler implements IOpenableGUI
{
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiLiquidBoiler(player.inventory, (TileLiquidBoiler) world.getTileEntity(x, y, z));
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerLiquidBoiler(player.inventory, (TileLiquidBoiler) world.getTileEntity(x, y, z));
	}

	@Override
	protected int getItemBurnTime()
	{
		return 0;
	}
}
