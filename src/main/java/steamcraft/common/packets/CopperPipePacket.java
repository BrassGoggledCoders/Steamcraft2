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
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import steamcraft.common.tiles.TileCopperPipe;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author decebaldecebal
 *
 */
public class CopperPipePacket implements IMessage
{
	private int worldId, x, y, z;
	ForgeDirection[] connections;

	public CopperPipePacket()
	{
	} // REQUIRED

	public CopperPipePacket(int worldId, int x, int y, int z, ForgeDirection[] connections)
	{
		this.worldId = worldId;
		this.x = x;
		this.y = y;
		this.z = z;
		this.connections = connections;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.worldId = buf.readInt();
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();

		this.connections = new ForgeDirection[6];

		for (int i = 0; i < 6; i++)
		{
			this.connections[i] = ForgeDirection.getOrientation(buf.readByte());

			if (this.connections[i] == ForgeDirection.UNKNOWN)
				this.connections[i] = null;
		}
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.worldId);
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
		for (int i = 0; i < 6; i++)
			buf.writeByte(directionToByte(this.connections[i]));
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

	public static class CopperPipePacketHandler implements IMessageHandler<CopperPipePacket, IMessage>
	{
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(CopperPipePacket message, MessageContext ctx)
		{
			World world = Minecraft.getMinecraft().theWorld;

			if (world.getTileEntity(message.x, message.y, message.z) instanceof TileCopperPipe)
			{
				TileCopperPipe pipe = (TileCopperPipe) world.getTileEntity(message.x, message.y, message.z);

				pipe.connections = message.connections;
			}

			return null;
		}
	}
}