/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * File created @ [Jan 30, 2014, 5:19:11 PM]
 */
package common.steamcraft.client.core.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import common.steamcraft.client.gui.GuiChemicalFurnace;
import common.steamcraft.client.gui.GuiCoalGenerator;
import common.steamcraft.client.gui.GuiCompressor;
import common.steamcraft.client.gui.GuiGuideBook;
import common.steamcraft.client.gui.GuiNukeFurnace;
import common.steamcraft.client.gui.GuiSteamFurnace;
import common.steamcraft.client.lib2.GuiIDs;
import common.steamcraft.common.block.tile.TileEntityChemicalFurnace;
import common.steamcraft.common.block.tile.TileEntityCoalGenerator;
import common.steamcraft.common.block.tile.TileEntityCompressor;
import common.steamcraft.common.block.tile.TileEntityNukeFurnace;
import common.steamcraft.common.block.tile.TileEntitySteamFurnace;
import common.steamcraft.common.block.tile.container.ContaineCoalGenerator;
import common.steamcraft.common.block.tile.container.ContainerChemicalFurnace;
import common.steamcraft.common.block.tile.container.ContainerCompressor;
import common.steamcraft.common.block.tile.container.ContainerNukeFurnace;
import common.steamcraft.common.block.tile.container.ContainerSteamFurnace;

import cpw.mods.fml.common.network.IGuiHandler;

/**
 * @author MrArcane111
 *
 */
public class GuiHandler implements IGuiHandler
{
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity tile = world.getBlockTileEntity(x, y, z);

		switch(id)
		{
		case GuiIDs.GUI_ID_NUKE_OVEN:
			return new ContainerNukeFurnace(player.inventory, (TileEntityNukeFurnace) tile);

		case GuiIDs.GUI_ID_CHEM_OVEN:
			return new ContainerChemicalFurnace(player.inventory, (TileEntityChemicalFurnace) tile);

		case GuiIDs.GUI_ID_STEAM_OVEN:
			return new ContainerSteamFurnace(player.inventory, (TileEntitySteamFurnace) tile);
			
		case GuiIDs.GUI_ID_COMPRESSOR:
			return new ContainerCompressor(player.inventory, (TileEntityCompressor) tile);
			
		case GuiIDs.GUI_ID_COAL_GENERATOR:
			return new ContaineCoalGenerator(player.inventory, (TileEntityCoalGenerator) tile);
		}
		
		return null;
	}

	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);

		switch(id)
		{
		case GuiIDs.GUI_ID_NUKE_OVEN:
			return new GuiNukeFurnace(player.inventory, (TileEntityNukeFurnace) tile);

		case GuiIDs.GUI_ID_CHEM_OVEN:
			return new GuiChemicalFurnace(player.inventory, (TileEntityChemicalFurnace) tile);

		case GuiIDs.GUI_ID_STEAM_OVEN:
			return new GuiSteamFurnace(player.inventory, (TileEntitySteamFurnace) tile);
			
		case GuiIDs.GUI_ID_COMPRESSOR:
			return new GuiCompressor(player.inventory, (TileEntityCompressor) tile);
			
		case GuiIDs.GUI_ID_COAL_GENERATOR:
			return new GuiCoalGenerator(player.inventory, (TileEntityCoalGenerator) tile);
			
		case GuiIDs.GUI_ID_GUIDE_BOOK:
			return new GuiGuideBook(player, player.inventory.getItemStack());
		}
		
		return null;
	}
}