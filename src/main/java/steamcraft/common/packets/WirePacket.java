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

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.util.ForgeDirection;

import steamcraft.common.tiles.energy.TileCopperWire;
import boilerplate.client.ClientHelper;

/**
 * @author decebaldecebal
 *
 */
public class WirePacket implements IMessage
{
	private int x, y, z;
	ForgeDirection[] connections;
	ForgeDirection extract;

	public WirePacket()
	{
	} // REQUIRED

	public WirePacket(int x, int y, int z, ForgeDirection[] connections, ForgeDirection extract)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.connections = connections;
		this.extract = extract;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();

		this.connections = new ForgeDirection[6];

		for(int i = 0; i < 6; i++)
		{
			this.connections[i] = ForgeDirection.getOrientation(buf.readByte());

			if(this.connections[i] == ForgeDirection.UNKNOWN)
				this.connections[i] = null;
		}
		this.extract = ForgeDirection.getOrientation(buf.readByte());
		if(this.extract == ForgeDirection.UNKNOWN)
			this.extract = null;
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
		
		for(int i = 0; i < 6; i++)
			buf.writeByte(CopperPipePacket.directionToByte(this.connections[i]));
		buf.writeByte(CopperPipePacket.directionToByte(this.extract));
		
	}

	public static class WirePacketHandler implements IMessageHandler<WirePacket, IMessage>
	{
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(WirePacket message, MessageContext ctx)
		{
			World world = ClientHelper.world();

			if(world.getTileEntity(message.x, message.y, message.z) instanceof TileCopperWire)
			{
				TileCopperWire wire = (TileCopperWire) world.getTileEntity(message.x, message.y, message.z);

				wire.connections = message.connections;
				wire.extract = message.extract;
			}

			return null;
		}
	}
}
