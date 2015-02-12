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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import boilerplate.client.ClientHelper;
import boilerplate.common.baseclasses.BaseTileWithInventory;

/**
 * @author decebaldecebal
 * 
 */
public class UpdateClientsideInventoryPacket implements IMessage
{
	private int x, y, z;
	private int[] ids = new int[] {};

	public UpdateClientsideInventoryPacket()
	{
	} // REQUIRED

	public UpdateClientsideInventoryPacket(int x, int y, int z, int[] ids)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.ids = ids;
	}

	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.x = buf.readInt();
		this.y = buf.readInt();
		this.z = buf.readInt();
		for(int i = 0; i < this.ids.length; i++)
		{
			this.ids[i] = buf.readInt();
		}
	}

	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.x);
		buf.writeInt(this.y);
		buf.writeInt(this.z);
		for(int id : this.ids)
		{
			buf.writeInt(id);
		}
	}

	public static class UpdateClientsideInventoryPacketHandler implements IMessageHandler<UpdateClientsideInventoryPacket, IMessage>
	{
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(UpdateClientsideInventoryPacket message, MessageContext ctx)
		{
			World world = ClientHelper.world();

			if(world.getTileEntity(message.x, message.y, message.z) instanceof BaseTileWithInventory)
			{
				BaseTileWithInventory tile = (BaseTileWithInventory) world.getTileEntity(message.x, message.y, message.z);

				for(int i = 0; i < message.ids.length; i++)
				{
					tile.inventory[i] = new ItemStack(Item.getItemById(message.ids[i]));
				}
			}

			return null;
		}
	}
}