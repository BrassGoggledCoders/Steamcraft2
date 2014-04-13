package steamcraft.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.common.container.ContainerVanity;
import steamcraft.common.entities.EntityPlayerExtended;
import steamcraft.common.tiles.TileEntitySteamBoiler;
import steamcraft.common.tiles.container.ContainerSteamBoiler;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) 
	{
		TileEntity tile = world.getBlockTileEntity(x, y, z);

		switch(id)
		{
		/*case GuiIDs.GUI_ID_NUKE_OVEN:
			return new ContainerNukeFurnace(player.inventory, (TileEntityNukeFurnace) tile);

		case GuiIDs.GUI_ID_CHEM_OVEN:
			return new ContainerChemicalFurnace(player.inventory, (TileEntityChemicalFurnace) tile);

		case GuiIDs.GUI_ID_STEAM_OVEN:
			return new ContainerSteamFurnace(player.inventory, (TileEntitySteamFurnace) tile);
			
		case GuiIDs.GUI_ID_COMPRESSOR:
			return new ContainerCompressor(player.inventory, (TileEntityCompressor) tile);
			*/
		case GuiIDs.GUI_ID_COAL_GENERATOR:
			return new ContainerSteamBoiler(player.inventory, (TileEntitySteamBoiler) tile);
		case GuiIDs.GUI_ID_VANITY:
			return new ContainerVanity(player, player.inventory, EntityPlayerExtended.get(player).inventory);
		}
		
		return null;
	}
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tile = world.getBlockTileEntity(x, y, z);

		switch(id)
		{
		/*case GuiIDs.GUI_ID_NUKE_OVEN:
			return new GuiNukeFurnace(player.inventory, (TileEntityNukeFurnace) tile);

		case GuiIDs.GUI_ID_CHEM_OVEN:
			return new GuiChemicalFurnace(player.inventory, (TileEntityChemicalFurnace) tile);

		case GuiIDs.GUI_ID_STEAM_OVEN:
			return new GuiSteamFurnace(player.inventory, (TileEntitySteamFurnace) tile);
			
		case GuiIDs.GUI_ID_COMPRESSOR:
			return new GuiCompressor(player.inventory, (TileEntityCompressor) tile);
			
		case GuiIDs.GUI_ID_GUIDE_BOOK:
			return new GuiGuideBook(player, player.inventory.getItemStack());
		*/	
		case GuiIDs.GUI_ID_COAL_GENERATOR:
			return new GuiSteamBoiler(player.inventory, (TileEntitySteamBoiler) tile);
			
		case GuiIDs.GUI_ID_VANITY:
			return new GuiVanity(player, player.inventory, EntityPlayerExtended.get(player).inventory);
		}
		
		return null;
	}
}
