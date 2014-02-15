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
package common.steamcraft.mod.common;

import common.steamcraft.mod.common.core.proxy.SC2_CommonProxy;
import common.steamcraft.mod.common.lib.SC2_Info;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = SC2_Info.MOD_ID, name = SC2_Info.MOD_NAME, version = SC2_Info.VERSION)
@NetworkMod(clientSideRequired = true)//, serverSideRequired = false, channels = {SC2_Info.NETWORK_CHANNEL}, packetHandler = SC2_PacketHandler.class)
/**
 * It's a main mod class just in case you couldn't tell.
 * 
 * @author MrArcane111
 *
 */
public class SC2 {
	@Instance(SC2_Info.MOD_ID)
	public static SC2 instance;
	
	@SidedProxy(clientSide = SC2_Info.CLIENT_PROXY, serverSide = SC2_Info.COMMON_PROXY)
    public static SC2_CommonProxy proxy;

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
}