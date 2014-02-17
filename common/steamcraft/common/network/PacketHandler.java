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
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 15, 2014, 1:57:22 PM]
 */
package common.steamcraft.common.network;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 * @author MrArcane111
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
	
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		DataInputStream dataStream = new DataInputStream(new ByteArrayInputStream(packet.data));
		
		try {
			int packetType = dataStream.read();
			
			if ((packetType == 10) || (packetType == 20) || (packetType == 30)) {
				int x = dataStream.readInt();
				int y = dataStream.readInt();
				int z = dataStream.readInt();
				
				World world = ((EntityPlayer) player).worldObj;
				TileEntity te = world.getBlockTileEntity(x, y, z);
				
				if (te instanceof NetworkTile) {
					NetworkTile netTE = (NetworkTile)te;
					
					if (packetType == 10) {
						netTE.readPacket(dataStream);
					} else if (packetType == 20) {
						netTE.sendPacket();
					} else if (packetType == 30) {
						netTE.readPacketFromClient(dataStream);
					}
				}
			} else if (packetType == 40) {
				int windowID = dataStream.readByte();
				int barID = dataStream.readShort();
				int content = dataStream.readInt();
				
				Container container = Minecraft.getMinecraft().thePlayer.openContainer;
				
				if ((container != null) && (container.windowId == windowID)) {
					container.updateProgressBar(barID, content);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
