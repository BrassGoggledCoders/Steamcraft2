
package steamcraft.common.packets;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import steamcraft.common.tiles.TileTimeBomb;

/**
 * @author decebaldecebal
 *
 */
public class TimeBombPacket implements IMessage
{
	public int worldId;
	public int time;
	public int x;
	public int y;
	public int z;

	public TimeBombPacket()
	{
	} // REQUIRED

	public TimeBombPacket(int time, int x, int y, int z, int worldId)
	{
		this.time = time;
		this.x = x;
		this.y = y;
		this.z = z;

		this.worldId = worldId;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.time = buf.readInt();
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.worldId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.time);
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
		buf.writeInt(this.worldId);
	}

	public static class TimeBombPacketHandler implements IMessageHandler<TimeBombPacket, IMessage>
	{
		@Override
		public IMessage onMessage(TimeBombPacket message, MessageContext ctx)
		{
			World world = DimensionManager.getWorld(message.worldId);

			if (world.getTileEntity(message.x, message.y, message.z) instanceof TileTimeBomb)
			{
				TileTimeBomb bomb = (TileTimeBomb) world.getTileEntity(message.x, message.y, message.z);

				bomb.setTime(message.time);
			}

			return null;
		}
	}
}
