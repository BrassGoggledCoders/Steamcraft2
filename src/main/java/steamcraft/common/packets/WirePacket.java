
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
import steamcraft.common.tiles.energy.TileCopperWire;

/**
 * @author decebaldecebal
 *
 */
public class WirePacket implements IMessage
{
	private int x, y, z;
	ForgeDirection[] connections;
	ForgeDirection[] extractions;

	public WirePacket()
	{
	} // REQUIRED

	public WirePacket(int x, int y, int z, ForgeDirection[] connections, ForgeDirection[] extractions)
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
			buf.writeByte(CopperPipePacket.directionToByte(this.connections[i]));

		for (int i = 0; i < 6; i++)
			buf.writeByte(CopperPipePacket.directionToByte(this.extractions[i]));

	}

	public static class WirePacketHandler implements IMessageHandler<WirePacket, IMessage>
	{
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(WirePacket message, MessageContext ctx)
		{
			World world = ClientHelper.world();

			if (world.getTileEntity(message.x, message.y, message.z) instanceof TileCopperWire)
			{
				TileCopperWire wire = (TileCopperWire) world.getTileEntity(message.x, message.y, message.z);

				wire.connections = message.connections;
				wire.extractions = message.extractions;
			}

			return null;
		}
	}
}
