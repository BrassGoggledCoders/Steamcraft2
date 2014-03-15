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
 * File created @ [Feb 4, 2014, 5:19:04 PM]
 */
package common.steamcraft.common;

import common.steamcraft.common.core.proxy.CommonProxy;
import common.steamcraft.common.lib2.LibInfo;
import common.steamcraft.common.network.PacketHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = LibInfo.MOD_ID, name = LibInfo.MOD_NAME, version = LibInfo.VERSION)
@NetworkMod(clientSideRequired = false, serverSideRequired = true, channels = {LibInfo.NETWORK_CHANNEL}, packetHandler = PacketHandler.class)
/**
 * @author MrArcane111
 *
 */
public class SC2 {
	@Instance(LibInfo.MOD_ID)
	public static SC2 instance;
	
	@SidedProxy(clientSide = LibInfo.CLIENT_PROXY, serverSide = LibInfo.COMMON_PROXY)
    public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
	
	// TODO: 
	// 1. Conveyor stuff: run the code; you'll see what I mean.
	// 2. Fix the Steam-Powered Furnace, Chemical Furnace, and Nuclear Furnace
	// 3. Make sure all the items/blocks use the "SC2_PREFIX" instead of MOD_ID for textures (it's located in the ClientResources)
	// 4. TileEntityCompressor needs a new Recipe handler
	// 5. There's a TileEntity extension class I made called NetworkTile that utilizes some helpful things relating to packets, so 
	// feel free to check it out and maybe make the TileEntityElectricMachine extend it
	// Send me a message for model/texture requests so I can forward the info through the proper channels.
	// 6. Post this kind of info here, so the developers can read it easily.
	// 7. Please use the nightly branch for all experimental, incomplete, and WIP stuff; only use the master and maindir branches for
	// finalized components!
	
	/**
	 * 01. Basic Steam Boiler: uses charcoal/coal to turn water into steam; can only transfer steam through steam pipes; has a small internal storage buffer
	 * 02. Compressor: uses steam (from steam pipes) to place steam into canisters which only power portable items (i.e. drills, brass wings, etc.)
	 * 03. Steam-Powered Engine: uses steam (from steam pipes) to turn steam-power into EU or MJ or Thermal Expansion power; has an internal storage buffer
     * 04. Advanced Steam Boiler: uses charcoal/coal, and wood products to turn water into steam; can also place steam into canisters; has a large internal storage buffer
	 * 05. Steam-Pipes: transfers steam to and from machines
	 * 06. Lightning rod: when struck, it generates a massive amount of EU or MJ; has a large internal storage buffer of energy
	 * 07. Tesla Coils: can send EU, MJ, or redstone power wirelessly to a Tesla Receiver within a certain distance; does NOT have an internal storage buffer
	 * 08. Chemical Furnace: uses interesting chemical compounds (i.e. gunpowder, sulphur, or glowstone) to provide a faster way to smelt things; Tier 2 Furnace
	 * 09. Steam-Powered Furnace: uses steam to provide a faster way to smelt things; Tier 3 Furnace
	 * 10. Nuclear Furnace: uses MJ or EU to provide the fastest way to smelt things; must have �exhaust� routed through smoke stacks, otherwise the nuclear furnace will explode; smoke stacks pollute nearby area (change grass color, excessive rain, etc.); Tier 4 Furnace
	 * 11. Clockwork machines: run off of a mechanical energy, much like steam-power; highest order of tools and armor; recharges via winding machine
	 * 12. Winding Machine: uses EU, MJ, or Steam-Power to rewind clockwork tools; does NOT have an internal buffer
	 */
}