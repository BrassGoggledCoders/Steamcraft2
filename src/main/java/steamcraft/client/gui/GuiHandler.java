/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ 23-May-2014
 */
package steamcraft.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.client.lib.GuiIDs;
import steamcraft.common.container.ContainerVanity;
import steamcraft.common.entities.EntityPlayerExtended;
import steamcraft.common.tiles.TileArmorEditor;
import steamcraft.common.tiles.TileSteamBoiler;
import steamcraft.common.tiles.container.ContainerArmorEditor;
import steamcraft.common.tiles.container.ContainerSteamBoiler;
import cpw.mods.fml.common.network.IGuiHandler;

// TODO: Auto-generated Javadoc
/**
 * The Class GuiHandler.
 */
public class GuiHandler implements IGuiHandler {
	
	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.network.IGuiHandler#getServerGuiElement(int, net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, int, int, int)
	 */
	@Override
	public Object getServerGuiElement(final int id, final EntityPlayer player,
			final World world, final int x, final int y, final int z) {
		final TileEntity tile = world.getTileEntity(x, y, z);

		switch (id) {
		/*
		 * case GuiIDs.GUI_ID_NUKE_OVEN: return new
		 * ContainerNukeFurnace(player.inventory, (TileEntityNukeFurnace) tile);
		 * 
		 * case GuiIDs.GUI_ID_CHEM_OVEN: return new
		 * ContainerChemicalFurnace(player.inventory,
		 * (TileEntityChemicalFurnace) tile);
		 * 
		 * case GuiIDs.GUI_ID_STEAM_OVEN: return new
		 * ContainerSteamFurnace(player.inventory, (TileEntitySteamFurnace)
		 * tile);
		 * 
		 * case GuiIDs.GUI_ID_COMPRESSOR: return new
		 * ContainerCompressor(player.inventory, (TileEntityCompressor) tile);
		 */
		case GuiIDs.GUI_ID_STEAM_BOILER:
			return new ContainerSteamBoiler(player.inventory,
					(TileSteamBoiler) tile);
		case GuiIDs.GUI_ID_VANITY:
			return new ContainerVanity(player, player.inventory,
					EntityPlayerExtended.get(player).inventory);
		case GuiIDs.GUI_ID_ARMOREDITOR:
			return new ContainerArmorEditor(player.inventory,
					(TileArmorEditor) tile);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see cpw.mods.fml.common.network.IGuiHandler#getClientGuiElement(int, net.minecraft.entity.player.EntityPlayer, net.minecraft.world.World, int, int, int)
	 */
	@Override
	public Object getClientGuiElement(final int id, final EntityPlayer player,
			final World world, final int x, final int y, final int z) {
		final TileEntity tile = world.getTileEntity(x, y, z);

		switch (id) {
		/*
		 * case GuiIDs.GUI_ID_NUKE_OVEN: return new
		 * GuiNukeFurnace(player.inventory, (TileEntityNukeFurnace) tile);
		 * 
		 * case GuiIDs.GUI_ID_CHEM_OVEN: return new
		 * GuiChemicalFurnace(player.inventory, (TileEntityChemicalFurnace)
		 * tile);
		 * 
		 * case GuiIDs.GUI_ID_STEAM_OVEN: return new
		 * GuiSteamFurnace(player.inventory, (TileEntitySteamFurnace) tile);
		 * 
		 * case GuiIDs.GUI_ID_COMPRESSOR: return new
		 * GuiCompressor(player.inventory, (TileEntityCompressor) tile);
		 * 
		 * case GuiIDs.GUI_ID_GUIDE_BOOK: return new GuiGuideBook(player,
		 * player.inventory.getItemStack());
		 */
		case GuiIDs.GUI_ID_STEAM_BOILER:
			return new GuiSteamBoiler(player.inventory, (TileSteamBoiler) tile);
		case GuiIDs.GUI_ID_ARMOREDITOR:
			return new GuiArmorEditor(player.inventory, (TileArmorEditor) tile);
		case GuiIDs.GUI_ID_VANITY:
			return new GuiVanity(player, player.inventory,
					EntityPlayerExtended.get(player).inventory);
		}

		return null;
	}
}
