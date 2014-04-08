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
 * File created @ [Apr 8, 2014, 1:18:11 PM]
 */
package steamcraft.common.lib.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.common.Steamcraft;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class PacketHandler implements IPacketHandler {
	/** */
	public static final String SC2_CHANNEL = "SC2_Channel";

	/** */
	public static final int PACKET_UPDATE = 10;

	/** */
	public static final int PACKET_REQUEST_UPDATE = 20;

	/** */
	public static final int PACKET_CLIENT_UPDATE = 30;

	/** */
	public static final int PACKET_GUI_INFO = 40;

	/** */
	public static final int PACKET_OPEN_SERVER_GUI = 50;

	/** */
	public static final int PACKET_EXTENDED_PROPERTIES = 60;

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) 
	{
		try {
			DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(packet.data));
			int packetType = dataStream.read();

			switch(packetType)
			{
			case PACKET_UPDATE:
				handleUpdatePacket("server", dataStream, player);
				break;
			case PACKET_REQUEST_UPDATE:
				handleUpdatePacket("request", dataStream, player);
				break;
			case PACKET_CLIENT_UPDATE:
				handleUpdatePacket("client", dataStream, player);
				break;
			case PACKET_GUI_INFO:
				handleGUIInfoPacket(dataStream);
				break;
			case PACKET_OPEN_SERVER_GUI:
				handleOpenServerGui(packet, (EntityPlayer) player, dataStream);
				break;
			case PACKET_EXTENDED_PROPERTIES:
				handleExtendedProperties(packet, player, dataStream);
			default:
				FMLLog.log(Level.WARNING, "", "[SC2] Unknown Packet Type:" + packetType);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param packet
	 * @param player
	 * @param dataStream
	 */
	private void handleExtendedProperties(Packet250CustomPayload packet, Player player, DataInputStream dataStream) {}
	
	/**
	 * @param packet
	 * @param player
	 * @param dataStream
	 */
	private void handleOpenServerGui(Packet250CustomPayload packet, EntityPlayer player, DataInputStream dataStream) 
	{
		int guiID;

		try {
			guiID = dataStream.readInt();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		//player.displayGUIWorkbench((int)player.posX, (int)player.posY, (int)player.posZ);
		player.openGui(Steamcraft.instance, guiID, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);

	}
	
	/**
	 * @throws IOException 
	 * @param type
	 * @param dataStream
	 * @param player
	 */
	private void handleGUIInfoPacket(DataInputStream dataStream) throws IOException 
	{
		int windowID = dataStream.readByte();
		int barID = dataStream.readShort();
		int content = dataStream.readInt();

		net.minecraft.inventory.Container container = Minecraft.getMinecraft().thePlayer.openContainer;

		if ((container != null) && (container.windowId == windowID)) 
		{
			container.updateProgressBar(barID, content);
		}
	}
	
	/**
	 * @throws IOException 
	 * @param type
	 * @param dataStream
	 * @param player
	 */
	private void handleUpdatePacket(String type, DataInputStream dataStream, Player player) throws IOException 
	{
		int x = dataStream.readInt();
		int y = dataStream.readInt();
		int z = dataStream.readInt();

		World world = ((EntityPlayer) player).worldObj;
		TileEntity te = world.getBlockTileEntity(x, y, z);

		if (te instanceof NetworkTile) 
		{
			NetworkTile netTE = (NetworkTile)te;

			if (type == "server")
			{
				netTE.readPacket(dataStream);
			} 
			else if (type == "request") 
			{
				netTE.sendPacket();
			} 
			else if (type == "client") 
			{
				netTE.readPacketFromClient(dataStream);
			}
		}
	}
}
