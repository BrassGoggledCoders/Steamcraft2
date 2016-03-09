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
package xyz.brassgoggledcoders.steamcraft.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.steamcraft.client.gui.GuiSteamBoiler;
import xyz.brassgoggledcoders.steamcraft.common.tiles.container.ContainerSteamBoiler;
import boilerplate.api.IOpenableGUI;

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
		return new ContainerSteamBoiler(player.inventory, (TileSteamBoiler) world.getTileEntity(x, y, z));
	}
}
