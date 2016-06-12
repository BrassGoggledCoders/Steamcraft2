
package steamcraft.common.packets;

import boilerplate.client.ClientHelper;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import steamcraft.common.tiles.TileCopperPipe;

/**
 * @author decebaldecebal
 *
 */
public class CopperPipePacket implements IMessage
{
	private int x, y, z;
	ForgeDirection[] connections;
	ForgeDirection[] extractions;

	public CopperPipePacket()
	{
	} // REQUIRED

	public CopperPipePacket(int x, int y, int z, ForgeDirection[] connections, ForgeDirection[] extractions)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.connections = connections;
		this.extractions = extractions;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
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

		this.extractions = new ForgeDirection[6];

		for (int i = 0; i < 6; i++)
		{
			this.extractions[i] = ForgeDirection.getOrientation(buf.readByte());

			if (this.extractions[i] == ForgeDirection.UNKNOWN)
				this.extractions[i] = null;
		}
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);

		for (int i = 0; i < 6; i++)
			buf.writeByte(directionToByte(this.connections[i]));

		for (int i = 0; i < 6; i++)
			buf.writeByte(directionToByte(this.extractions[i]));
	}

	public static byte directionToByte(ForgeDirection dir)
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
			World world = ClientHelper.world();

			if (world.getTileEntity(message.x, message.y, message.z) instanceof TileCopperPipe)
			{
				TileCopperPipe pipe = (TileCopperPipe) world.getTileEntity(message.x, message.y, message.z);

				pipe.connections = message.connections;
				pipe.extractions = message.extractions;
			}

			return null;
		}
	}
}