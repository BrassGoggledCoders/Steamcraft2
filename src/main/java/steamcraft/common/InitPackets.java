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
package steamcraft.common;

import steamcraft.common.packets.CopperPipePacket;
import steamcraft.common.packets.CopperPipePacket.CopperPipePacketHandler;
import steamcraft.common.packets.CopperWirePacket;
import steamcraft.common.packets.CopperWirePacket.CopperWirePacketHandler;
import steamcraft.common.packets.EnergyNetworkPacket;
import steamcraft.common.packets.EnergyNetworkPacket.EnergyNetworkPacketHandler;
import steamcraft.common.packets.FluidNetworkPacket;
import steamcraft.common.packets.FluidNetworkPacket.FluidNetworkPacketHandler;
import steamcraft.common.packets.TimeBombPacket;
import steamcraft.common.packets.TimeBombPacket.TimeBombPacketHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

/**
 * @author decebaldecebal
 * 
 */
public class InitPackets
{
	public static SimpleNetworkWrapper network;

	private static byte packetId = 0;

	public static void init()
	{
		network = NetworkRegistry.INSTANCE.newSimpleChannel("steamcraft2");
		network.registerMessage(TimeBombPacketHandler.class, TimeBombPacket.class, packetId++, Side.SERVER);
		network.registerMessage(CopperPipePacketHandler.class, CopperPipePacket.class, packetId++, Side.CLIENT);
		network.registerMessage(FluidNetworkPacketHandler.class, FluidNetworkPacket.class, packetId++, Side.CLIENT);
		network.registerMessage(CopperWirePacketHandler.class, CopperWirePacket.class, packetId++, Side.CLIENT);
		network.registerMessage(EnergyNetworkPacketHandler.class, EnergyNetworkPacket.class, packetId++, Side.CLIENT);
	}
}
