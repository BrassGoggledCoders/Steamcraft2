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

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import steamcraft.api.tile.ISpannerTile;

/**
 * @author decebaldecebal
 *
 */
public class UpdateExtractionPacket implements IMessage
{
	private int x, y, z, worldId;
	private byte dirIndex;

	public UpdateExtractionPacket()
	{
	} // REQUIRED

	public UpdateExtractionPacket(int worldId, int x, int y, int z, int dirIndex)
	{
		this.worldId = worldId;
		this.x = x;
		this.y = y;
		this.z = z;
		this.dirIndex = (byte) dirIndex;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.worldId = buf.readInt();
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		this.dirIndex = buf.readByte();
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(worldId);
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
		buf.writeByte(this.dirIndex);
	}

	public static class UpdateExtractionPacketHandler implements IMessageHandler<UpdateExtractionPacket, IMessage>
	{
		@Override
		public IMessage onMessage(UpdateExtractionPacket message, MessageContext ctx)
		{
			World world = DimensionManager.getWorld(message.worldId);

			if (world.getTileEntity(message.x, message.y, message.z) instanceof ISpannerTile)
			{
				ISpannerTile tile = (ISpannerTile) world.getTileEntity(message.x, message.y, message.z);

				tile.changeExtraction(message.dirIndex);
			}

			return null;
		}
	}
}
