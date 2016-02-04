
package steamcraft.common.packets;

import net.minecraft.world.World;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.fluids.FluidRegistry;

import boilerplate.client.ClientHelper;
import io.netty.buffer.ByteBuf;
import steamcraft.common.tiles.TileCopperPipe;

/**
 * @author decebaldecebal
 *
 */
public class CopperPipeFluidPacket implements IMessage
{
	private float fluidScaled;
	private int x, y, z;
	private String fluidName;

	public CopperPipeFluidPacket()
	{
	} // REQUIRED

	public CopperPipeFluidPacket(int x, int y, int z, float fluidScaled, String fluidName)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.fluidScaled = fluidScaled;
		this.fluidName = fluidName;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.fluidScaled = buf.readFloat();
		this.fluidName = ByteBufUtils.readUTF8String(buf);
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
		buf.writeFloat(this.fluidScaled);
		ByteBufUtils.writeUTF8String(buf, this.fluidName);
	}

	public static class FluidNetworkPacketHandler implements IMessageHandler<CopperPipeFluidPacket, IMessage>
	{
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(CopperPipeFluidPacket message, MessageContext ctx)
		{
			World world = ClientHelper.world();

			if (world.getTileEntity(message.x, message.y, message.z) instanceof TileCopperPipe)
			{
				TileCopperPipe pipe = (TileCopperPipe) world.getTileEntity(message.x, message.y, message.z);

				pipe.fluidScaled = message.fluidScaled;
				pipe.fluidInPipe = FluidRegistry.getFluid(message.fluidName);
			}

			return null;
		}

	}
}