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
 * File created @ [Apr 8, 2014, 1:25:44 PM]
 */
package steamcraft.common.lib.network;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.packet.Packet250CustomPayload;

/**
 * A class to send GUI info to a server player through a packet.
 * 
 * @author Surseance (Johnny Eatmon)
 *
 */
public class NetworkUtil
{
	public static void sendGuiInfo(EntityPlayerMP player, int windowID, int type, int value) 
	{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);
		try {
			dataStream.writeInt(40);
			dataStream.writeByte(windowID);
			dataStream.writeShort(type);
			dataStream.writeInt(value);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "SC2_Channel";
		packet.data = byteStream.toByteArray();
		packet.length = byteStream.size();
		packet.isChunkDataPacket = false;
		player.playerNetServerHandler.sendPacketToPlayer(packet);
	}
}
