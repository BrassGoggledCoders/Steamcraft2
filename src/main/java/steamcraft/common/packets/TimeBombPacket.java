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
import steamcraft.common.tiles.TileTimeBomb;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * @author decebaldecebal
 * 
 */
public class TimeBombPacket implements IMessage
{
	public static int worldId;
	public int time;
	public int x;
	public int y;
	public int z;

	public TimeBombPacket()
	{
	} // REQUIRED

	public TimeBombPacket(int time, int x, int y, int z, int worldObj)
	{
		this.time = time;
		this.x = x;
		this.y = y;
		this.z = z;
		TimeBombPacket.worldId = worldId;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		time = buf.readInt();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		worldId = buf.readInt();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(time);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(worldId);
	}

	public static class TimeBombPacketHandler implements IMessageHandler<TimeBombPacket, IMessage>
	{
		@Override
		public IMessage onMessage(TimeBombPacket message, MessageContext ctx)
		{
			World world = DimensionManager.getWorld(worldId);

			if (world.getTileEntity(message.x, message.y, message.z) instanceof TileTimeBomb)
			{
				TileTimeBomb bomb = (TileTimeBomb) world.getTileEntity(message.x, message.y, message.z);

				bomb.setTime(message.time);
			}

			return null;
		}
	}
}
