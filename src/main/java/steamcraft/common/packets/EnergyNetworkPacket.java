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
import steamcraft.common.tiles.TileCopperWire;
import steamcraft.common.tiles.TileCopperWire.EnergyNetwork;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

/**
 * @author decebaldecebal
 * 
 */
public class EnergyNetworkPacket implements IMessage
{
	private float fluidScaled;
	private static int worldId;
	private int x;
	private int y;
	private int z;
	private String fluidName;

	public EnergyNetworkPacket()
	{
	} // REQUIRED

	public EnergyNetworkPacket(int worldId, int x, int y, int z, float fluidScaled, String fluidName)
	{
		EnergyNetworkPacket.worldId = worldId;
		this.x = x;
		this.y = y;
		this.z = z;
		this.fluidScaled = fluidScaled;
		this.fluidName = fluidName;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		worldId = buf.readInt();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		fluidScaled = buf.readFloat();
		fluidName = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(worldId);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeFloat(fluidScaled);
		ByteBufUtils.writeUTF8String(buf, fluidName);
	}

	public static class EnergyNetworkPacketHandler implements IMessageHandler<EnergyNetworkPacket, IMessage>
	{
		@Override
		public IMessage onMessage(EnergyNetworkPacket message, MessageContext ctx)
		{
			World world = DimensionManager.getWorld(worldId);

			if (world.getTileEntity(message.x, message.y, message.z) instanceof TileCopperWire)
			{
				TileCopperWire pipe = (TileCopperWire) world.getTileEntity(message.x, message.y, message.z);

				if (pipe.network == null)
					pipe.network = new EnergyNetwork(1);

				// pipe.network.fluidScaled = message.fluidScaled;
				// pipe.network.buffer.(new
				// FluidStack(FluidRegistry.getFluid(message.fluidName), 0));
			}

			return null;
		}

	}
}