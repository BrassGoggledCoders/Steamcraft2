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
package xyz.brassgoggledcoders.steamcraft.common.init;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import xyz.brassgoggledcoders.steamcraft.common.packets.CopperPipeFluidPacket;
import xyz.brassgoggledcoders.steamcraft.common.packets.CopperPipePacket;
import xyz.brassgoggledcoders.steamcraft.common.packets.CopperTankPacket;
import xyz.brassgoggledcoders.steamcraft.common.packets.OpenContainerFromClientPacket;
import xyz.brassgoggledcoders.steamcraft.common.packets.TimeBombPacket;
import xyz.brassgoggledcoders.steamcraft.common.packets.UpdateClientsideInventoryPacket;
import xyz.brassgoggledcoders.steamcraft.common.packets.UpdateExtractionPacket;
import xyz.brassgoggledcoders.steamcraft.common.packets.WirePacket;
import xyz.brassgoggledcoders.steamcraft.common.packets.CopperPipeFluidPacket.FluidNetworkPacketHandler;
import xyz.brassgoggledcoders.steamcraft.common.packets.CopperPipePacket.CopperPipePacketHandler;
import xyz.brassgoggledcoders.steamcraft.common.packets.CopperTankPacket.CopperTankPacketHandler;
import xyz.brassgoggledcoders.steamcraft.common.packets.OpenContainerFromClientPacket.OpenContainerFromClientPacketHandler;
import xyz.brassgoggledcoders.steamcraft.common.packets.TimeBombPacket.TimeBombPacketHandler;
import xyz.brassgoggledcoders.steamcraft.common.packets.UpdateClientsideInventoryPacket.UpdateClientsideInventoryPacketHandler;
import xyz.brassgoggledcoders.steamcraft.common.packets.UpdateExtractionPacket.UpdateExtractionPacketHandler;
import xyz.brassgoggledcoders.steamcraft.common.packets.WirePacket.WirePacketHandler;

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
		network.registerMessage(OpenContainerFromClientPacketHandler.class, OpenContainerFromClientPacket.class, packetId++, Side.SERVER);
		network.registerMessage(UpdateExtractionPacketHandler.class, UpdateExtractionPacket.class, packetId++, Side.SERVER);

		network.registerMessage(CopperPipePacketHandler.class, CopperPipePacket.class, packetId++, Side.CLIENT);
		network.registerMessage(FluidNetworkPacketHandler.class, CopperPipeFluidPacket.class, packetId++, Side.CLIENT);
		network.registerMessage(CopperTankPacketHandler.class, CopperTankPacket.class, packetId++, Side.CLIENT);
		network.registerMessage(WirePacketHandler.class, WirePacket.class, packetId++, Side.CLIENT);

		network.registerMessage(UpdateClientsideInventoryPacketHandler.class, UpdateClientsideInventoryPacket.class, packetId++, Side.CLIENT);
	}
}
