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
 * File created @ [5 Apr 2014, 20:57:54]
 */
package common.steamcraft.common.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.network.packet.Packet250CustomPayload;

/**
 * @author warlordjones
 *
 * 5 Apr 201420:57:54
 */
public class PacketOpenServerGui {
	public static Packet250CustomPayload getPacket(int guiID)
	{
	ByteArrayOutputStream bos = new ByteArrayOutputStream(5);
	DataOutputStream outputStream = new DataOutputStream(bos);

	try {
	outputStream.writeByte(PacketHandler.PACKET_OPEN_SERVER_GUI);
	outputStream.writeInt(guiID);
	} catch (Exception ex) {
	ex.printStackTrace();
	}

	return new Packet250CustomPayload(PacketHandler.SC2_CHANNEL, bos.toByteArray());
	}
}
