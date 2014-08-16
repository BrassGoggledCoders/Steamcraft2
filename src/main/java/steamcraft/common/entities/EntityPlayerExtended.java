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
package steamcraft.common.entities;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import steamcraft.common.container.InventoryVanity;

/**
 * @author warlordjones
 * 
 */
public class EntityPlayerExtended implements IExtendedEntityProperties // TODO:
// Add
// packets
{
	public final static String EXT_PROP_NAME = "EntityPlayerExtended";

	public final InventoryVanity inventory = new InventoryVanity();

	private final EntityPlayer player;

	public EntityPlayerExtended(final EntityPlayer player)
	{
		this.player = player;
	}

	public static final void register(final EntityPlayer player)
	{
		player.registerExtendedProperties(EntityPlayerExtended.EXT_PROP_NAME, new EntityPlayerExtended(player));
	}

	public static final EntityPlayerExtended get(final EntityPlayer player)
	{
		return (EntityPlayerExtended) player.getExtendedProperties(EXT_PROP_NAME);
	}

	@Override
	public void saveNBTData(final NBTTagCompound tagCompound)
	{
		final NBTTagCompound properties = new NBTTagCompound();
		tagCompound.setTag(EXT_PROP_NAME, properties);
		this.inventory.writeToNBT(properties);
	}

	@Override
	public void loadNBTData(final NBTTagCompound tagCompound)
	{
		final NBTTagCompound properties = (NBTTagCompound) tagCompound.getTag(EXT_PROP_NAME);
		this.inventory.readFromNBT(properties);
	}

	@Override
	public void init(final Entity entity, final World world)
	{
	}

	public final void sync()
	{
		final ByteArrayOutputStream baOutputStream = new ByteArrayOutputStream(8);
		final DataOutputStream dOutputStream = new DataOutputStream(baOutputStream);
		try
		{
			// dOutputStream.writeInt(PacketHandler.PACKET_EXTENDED_PROPERTIES);
		} catch (final Exception ex)
		{
			ex.printStackTrace();
		}

		// Packet250CustomPayload packet = new
		// Packet250CustomPayload(PacketHandler.SC2_CHANNEL,
		// baOutputStream.toByteArray());

		// We only want to send from the server to the client
		if (!this.player.worldObj.isRemote)
		{
		}
	}
}
