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

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;

/**
 * @author warlordjones
 *
 * 5 Apr 201420:57:54
 */
public class PacketOpenServerGui extends AbstractPacket
{
	// this will store the id of the gui to open
	private int id;

	// The basic, no-argument constructor MUST be included to use the new automated handling
	public PacketOpenServerGui() {}

	// if there are any class fields, be sure to provide a constructor that allows
	// for them to be initialized, and use that constructor when sending the packet
	public PacketOpenServerGui(int id) {
	this.id = id;
	}

	@Override
	public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
	// basic Input/Output operations, very much like DataOutputStream
	buffer.writeInt(id);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
	// basic Input/Output operations, very much like DataInputStream
	id = buffer.readInt();
	}

	@Override
	public void handleClientSide(EntityPlayer player) {
	// for opening a GUI, we don't need to do anything here
	}

	@Override
	public void handleServerSide(EntityPlayer player) {
	// because we sent the gui's id with the packet, we can handle all cases with one line:
	player.openGui(TutorialMain.instance, id, player.worldObj, (int) player.posX, (int) player.posY, (int) player.posZ);
	}
	}