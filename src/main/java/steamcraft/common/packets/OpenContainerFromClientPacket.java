
package steamcraft.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import steamcraft.common.Steamcraft;

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
		OpenContainerFromClientPacket.playerid = playerid;
		OpenContainerFromClientPacket.guiIDToOpen = guiIDToOpen;
		this.worldId = worldId;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		OpenContainerFromClientPacket.guiIDToOpen = buf.readInt();
		this.worldId = buf.readInt();
		OpenContainerFromClientPacket.playerid = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(OpenContainerFromClientPacket.guiIDToOpen);
		buf.writeInt(this.worldId);
		buf.writeInt(OpenContainerFromClientPacket.playerid);
	}

	public static class OpenContainerFromClientPacketHandler implements IMessageHandler<OpenContainerFromClientPacket, IMessage>
	{
		@Override
		public IMessage onMessage(OpenContainerFromClientPacket message, MessageContext ctx)
		{
			World world = DimensionManager.getWorld(message.worldId);
			EntityPlayer player = (EntityPlayer) world.getEntityByID(playerid);
			player.openGui(Steamcraft.instance, guiIDToOpen, world, (int) Math.round(player.posX), (int) Math.round(player.posY),
					(int) Math.round(player.posZ));
			return null;
		}
	}
}
