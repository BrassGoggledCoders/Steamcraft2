/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [3 Apr 2014, 10:43:03]
 */
package common.steamcraft.common.inventory;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import common.steamcraft.common.network.PacketHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

/**
 * @author warlordjones
 *
 * 3 Apr 201410:43:03
 */
public class ExtendedPlayer implements IExtendedEntityProperties
{
public final static String EXT_PROP_NAME = "VanityExtendedPlayer";

public final PlayerInventoryVanity inventory = new PlayerInventoryVanity();


private final EntityPlayer player;

public ExtendedPlayer(EntityPlayer player)
{
this.player = player;
}
public static final void register(EntityPlayer player)
{
player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));
}

public static final ExtendedPlayer get(EntityPlayer player)
{
return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
}
@Override
public void saveNBTData(NBTTagCompound compound)
{
NBTTagCompound properties = new NBTTagCompound();
this.inventory.writeToNBT(properties);
compound.setTag(EXT_PROP_NAME, properties);
}
@Override
public void loadNBTData(NBTTagCompound compound)
{
NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
this.inventory.readFromNBT(properties);
}

@Override
public void init(Entity entity, World world)
{
}
public final void sync()
{
ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
DataOutputStream outputStream = new DataOutputStream(bos);
try {
outputStream.writeChars(EXT_PROP_NAME);
} catch (Exception ex) {
ex.printStackTrace();
}

Packet250CustomPayload packet = new Packet250CustomPayload(PacketHandler.SC2_CHANNEL, bos.toByteArray());

// We only want to send from the server to the client
if (!player.worldObj.isRemote) {
EntityPlayerMP player1 = (EntityPlayerMP) player;
PacketDispatcher.sendPacketToPlayer(packet, (Player) player1);
}
}
}