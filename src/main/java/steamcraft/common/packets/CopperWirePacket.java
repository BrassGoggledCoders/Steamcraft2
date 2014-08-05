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
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.util.ForgeDirection;
import steamcraft.common.tiles.TileCopperWire;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * @author decebaldecebal
 * 
 */
public class CopperWirePacket implements IMessage
{
	private static int worldId;
	private int x;
	private int y;
	private int z;
	ForgeDirection[] connections;

	public CopperWirePacket()
	{
	} // REQUIRED

	public CopperWirePacket(int worldId, int x, int y, int z, ForgeDirection[] connections)
	{
		CopperWirePacket.worldId = worldId;
		this.x = x;
		this.y = y;
		this.z = z;
		this.connections = connections;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		worldId = buf.readInt();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();

		connections = new ForgeDirection[6];

		for (int i = 0; i < 6; i++)
		{
			connections[i] = ForgeDirection.getOrientation(buf.readByte());

			if (connections[i] == ForgeDirection.UNKNOWN)
				connections[i] = null;
		}
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(worldId);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		for (int i = 0; i < 6; i++)
			buf.writeByte(directionToByte(connections[i]));
	}

	private static byte directionToByte(ForgeDirection dir)
	{
		byte index = -1;

		if (dir != null)
			switch (dir)
			{
			case DOWN:
				index = 0;
				break;
			case UP:
				index = 1;
				break;
			case NORTH:
				index = 2;
				break;
			case SOUTH:
				index = 3;
				break;
			case WEST:
				index = 4;
				break;
			case EAST:
				index = 5;
				break;
			default:
				index = -1;
				break;
			}

		return index;
	}

	public static class CopperWirePacketHandler implements IMessageHandler<CopperWirePacket, IMessage>
	{
		@Override
		public IMessage onMessage(CopperWirePacket message, MessageContext ctx)
		{
			World world = DimensionManager.getWorld(worldId);

			if (world.getTileEntity(message.x, message.y, message.z) instanceof TileCopperWire)
			{
				TileCopperWire pipe = (TileCopperWire) world.getTileEntity(message.x, message.y, message.z);

				pipe.connections = message.connections;
			}

			return null;
		}
	}
}
