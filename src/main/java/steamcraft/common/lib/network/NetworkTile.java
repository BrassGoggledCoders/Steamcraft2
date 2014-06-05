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
 * File created @ [Apr 8, 2014, 1:20:21 PM]
 */
package steamcraft.common.lib.network;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import boilerplate.common.utils.PlayerUtils;

/**
 * (likely needs to be updated for to the new Netty system)
 *
 * @author Surseance (Johnny Eatmon)
 */
public abstract class NetworkTile extends TileEntity
{

	/** The marked for resend. */
	private boolean markedForResend;

	/**
	 * Checks if is marked for resend.
	 *
	 * @return true, if is marked for resend
	 */
	public boolean isMarkedForResend()
	{
		return markedForResend;
	}

	/**
	 * Sets the marked for resend.
	 *
	 * @param markedForResend the new marked for resend
	 */
	public void setMarkedForResend(boolean markedForResend)
	{
		this.markedForResend = markedForResend;
	}

	/**
	 * Send packet.
	 */
	public void sendPacket()
	{
		Packet packet = getDescriptionPacket();
		// packet.isChunkDataPacket = false;
		PlayerUtils.sendToPlayers(packet, worldObj, xCoord, yCoord, zCoord, null);
	}

	/* (non-Javadoc)
	 * @see net.minecraft.tileentity.TileEntity#getDescriptionPacket()
	 */
	@Override
	public Packet getDescriptionPacket()
	{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);

		try
		{
			dataStream.writeInt(10);

			dataStream.writeInt(xCoord);
			dataStream.writeInt(yCoord);
			dataStream.writeInt(zCoord);

			writePacket(dataStream);
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		/*
		 * Packet250CustomPayload packet = new Packet250CustomPayload();
		 * packet.channel = "SC2_Channel"; packet.data =
		 * byteStream.toByteArray(); packet.length = byteStream.size();
		 * packet.isChunkDataPacket = true;
		 */

		return null;// packet;
	}

	/* (non-Javadoc)
	 * @see net.minecraft.tileentity.TileEntity#updateEntity()
	 */
	@Override
	public void updateEntity()
	{
		super.updateEntity();

		if ((!worldObj.isRemote) && (isMarkedForResend()))
		{
			setMarkedForResend(false);
		}
	}

	/**
	 * Write packet.
	 *
	 * @param dataStream the data stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public abstract void writePacket(DataOutputStream dataStream)
			throws IOException;

	/**
	 * Read packet.
	 *
	 * @param dataStream the data stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public abstract void readPacket(DataInputStream dataStream)
			throws IOException;

	/**
	 * Read packet from client.
	 *
	 * @param dataStream the data stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public abstract void readPacketFromClient(DataInputStream dataStream)
			throws IOException;
}
