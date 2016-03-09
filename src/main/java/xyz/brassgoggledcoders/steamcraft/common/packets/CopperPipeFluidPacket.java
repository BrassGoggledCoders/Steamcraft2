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
package xyz.brassgoggledcoders.steamcraft.common.packets;

import net.minecraft.world.World;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.fluids.FluidRegistry;
import xyz.brassgoggledcoders.steamcraft.common.tiles.TileCopperPipe;
import boilerplate.client.ClientHelper;
import io.netty.buffer.ByteBuf;

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