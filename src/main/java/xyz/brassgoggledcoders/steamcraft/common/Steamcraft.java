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
package xyz.brassgoggledcoders.steamcraft.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import xyz.brassgoggledcoders.boilerplate.lib.BoilerplateLib;
import xyz.brassgoggledcoders.boilerplate.lib.common.IBoilerplateMod;
import xyz.brassgoggledcoders.boilerplate.lib.common.utils.ModLogger;
import xyz.brassgoggledcoders.steamcraft.common.lib.CommandSteamcraft;
import xyz.brassgoggledcoders.steamcraft.common.lib.CreativeTabSteamcraft;

@Mod(modid = Steamcraft.ID, name = Steamcraft.NAME, version = Steamcraft.VERSION)
public class Steamcraft implements IBoilerplateMod {
	@Instance(Steamcraft.ID)
	public static Steamcraft instance;

	public static final String ID = "steamcraft2";
	public static final String NAME = "Steamcraft 2";
	public static final String VERSION = "@VERSION@";

	public static final String CLIENT_PROXY = "xyz.brassgoggledcoders.steamcraft.client.ClientProxy";
	public static final String COMMON_PROXY = "xyz.brassgoggledcoders.steamcraft.common.CommonProxy";
	public static final String CONFIG_GUI = "xyz.brassgoggledcoders.steamcraft.client.gui.GuiFactorySteamcraft";

	public static final String PREFIX = "steamcraft:";

	public static ModLogger logger;

	public static Configuration config;
	public static CreativeTabs tabSC2 = new CreativeTabSteamcraft(Steamcraft.ID);

	@SidedProxy(clientSide = Steamcraft.CLIENT_PROXY, serverSide = Steamcraft.COMMON_PROXY)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		BoilerplateLib.getInstance().preInitStart(event);
		
		BoilerplateLib.getInstance().preInitEnd(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		BoilerplateLib.getInstance().init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		BoilerplateLib.getInstance().postInit(event);
	}

	@Override
	public Object getInstance() {
		return instance;
	}

	@Override
	public CreativeTabs getCreativeTab() {
		return tabSC2;
	}

	@Override
	public String getID() {
		return ID;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getVersion() {
		return VERSION;
	}

	@Override
	public String getPrefix() {
		return PREFIX;
	}

	@Override
	public ModLogger getLogger() {
		return logger;
	}

	@Override
	public Configuration getConfig() {
		return config;
	}

	@Override
	public String getClientProxyPath() {
		return CLIENT_PROXY;
	}

	@Override
	public String getCommonProxyPath() {
		return COMMON_PROXY;
	}
}
