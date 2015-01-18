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
package steamcraft.common.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import steamcraft.common.Steamcraft;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * @author decebaldecebal
 * 
 */
public class OpenContainerFromClientPacket implements IMessage
{
	public int worldId;
	public static int guiIDToOpen;
	public static int playerid;

	public OpenContainerFromClientPacket()
	{
	} // REQUIRED

	public OpenContainerFromClientPacket(int playerid, int guiIDToOpen, int worldId)
	{
		this.playerid = playerid;
		this.guiIDToOpen = guiIDToOpen;
		this.worldId = worldId;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.guiIDToOpen = buf.readInt();
		this.worldId = buf.readInt();
		this.playerid = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.guiIDToOpen);
		buf.writeInt(this.worldId);
		buf.writeInt(this.playerid);
	}

	public static class OpenContainerFromClientPacketHandler implements IMessageHandler<OpenContainerFromClientPacket, IMessage>
	{
		@Override
		public IMessage onMessage(OpenContainerFromClientPacket message, MessageContext ctx)
		{
			World world = DimensionManager.getWorld(message.worldId);
			EntityPlayer player = (EntityPlayer) world.getEntityByID(playerid);
			player.openGui(Steamcraft.instance, guiIDToOpen, world, player.serverPosX, player.serverPosY, player.serverPosZ);
			return null;
		}
	}
}
