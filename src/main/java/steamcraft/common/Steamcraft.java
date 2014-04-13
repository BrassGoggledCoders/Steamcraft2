/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * File created @ [Mar 12, 2014, 4:10:24 PM]
 */
package steamcraft.common;

import java.io.File;
import java.util.logging.Level;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.MinecraftForge;
import steamcraft.client.RegisterKeyBindings;
import steamcraft.client.gui.GuiHandler;
import steamcraft.common.config.Config;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.config.ConfigEntities;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.CommandSteamcraft;
import steamcraft.common.lib.CreativeTabSteamcraft;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.events.EventHandlerDrawHighlight;
import steamcraft.common.lib.events.EventHandlerSC2;
import steamcraft.common.lib.network.LoggerSteamcraft;
import steamcraft.common.lib.network.PacketHandler;
import steamcraft.common.lib.world.SteamcraftWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
@Mod(modid = LibInfo.ID, name = LibInfo.NAME, version = LibInfo.VERSION)
@NetworkMod(clientSideRequired = false, serverSideRequired = true, channels = {PacketHandler.SC2_CHANNEL}, packetHandler = PacketHandler.class)
public class Steamcraft
{
	@SidedProxy(clientSide = LibInfo.CLIENT_PROXY, serverSide = LibInfo.COMMON_PROXY)
	public static CommonProxy proxy;

	@Mod.Instance(LibInfo.NAME)
	public static Steamcraft instance;

	public SteamcraftWorldGenerator worldGen;
	public File directory;

	public static CreativeTabs tabSC2 = new CreativeTabSteamcraft(CreativeTabs.getNextID(), LibInfo.NAME.toLowerCase()); //TODO: Needs Icon

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		event.getModMetadata().version = LibInfo.VERSION;
		this.directory = event.getModConfigurationDirectory();

		LanguageRegistry.instance().getStringLocalization("itemGroup.steamcraft", "en_US");
		try
		{
			Config.initialize(event.getSuggestedConfigurationFile());
		} catch (Exception e)
		{
			LoggerSteamcraft.log(Level.SEVERE, "Failed to load configuration file!");
		} finally 
		{
			if (Config.config != null) 
				Config.save();
		}
		/*
		 * Do we really need more than one event handler?
		 */
		MinecraftForge.EVENT_BUS.register(EventHandlerDrawHighlight.class);
		MinecraftForge.EVENT_BUS.register(EventHandlerSC2.class);

		//GameRegistry.registerFuelHandler(this.worldEventHandler);
		//GameRegistry.registerCraftingHandler(this.worldEventHandler);
		GameRegistry.registerWorldGenerator(this.worldGen = new SteamcraftWorldGenerator());
		
		Config.save();
		ConfigBlocks.init();
		ConfigItems.init();
	
		proxy.registerDisplayInformation();
		proxy.registerRenderers();

	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		Config.registerBiomes();
		ConfigEntities.init();
		
		RegisterKeyBindings.init();
		NetworkRegistry.instance().registerGuiHandler(instance, new GuiHandler());
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		//Dosn't work! >> BiomeDictionary.registerAllBiomes();
		ConfigEntities.initEntitySpawns();
		Config.initModCompatibility();
		ConfigItems.postInit();
		//ConfigRecipes.init();
		Config.initLoot();
		//LoggerSteamcraft.log(Level.INFO, "SC2 is " + event.getModState());
		LanguageRegistry.instance().loadLocalization("assets/steamcraft/lang/en_US.lang", "en_US", false);
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandSteamcraft());
		LoggerSteamcraft.log(Level.INFO, "Registering commands just for you");
	}
}
