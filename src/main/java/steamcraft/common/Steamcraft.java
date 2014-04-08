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
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import steamcraft.common.config.Config;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.CommandSteamcraft;
import steamcraft.common.lib.CreativeTabSteamcraft;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.LoggerSteamcraft;
import steamcraft.common.lib.events.EventHandlerDrawHighlight;
import steamcraft.common.lib.events.EventHandlerEntity;
import steamcraft.common.lib.events.EventHandlerHUD;
import steamcraft.common.lib.events.EventHandlerTick;
import steamcraft.common.lib.events.EventHandlerWorld;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
@Mod(modid = LibInfo.ID, name = LibInfo.NAME, version = LibInfo.VERSION)
public class Steamcraft
{
	@SidedProxy(clientSide = LibInfo.CLIENT_PROXY, serverSide = LibInfo.COMMON_PROXY)
	public static CommonProxy proxy;

	@Mod.Instance(LibInfo.NAME)
	public static Steamcraft instance;

	//public SteamcraftWorldGenerator worldGen;
	public EventHandlerWorld worldEventHandler;
	public EventHandlerEntity entityEventHandler;
	public EventHandlerTick tickEventHandler;
	public EventHandlerHUD hudEventHandler;
	public EventHandlerDrawHighlight drawEventHandler;
	//public RenderEventHandler renderEventHandler;
	public File directory;

	public static CreativeTabs tabSC2 = new CreativeTabSteamcraft(CreativeTabs.getNextID(), LibInfo.NAME.toLowerCase()); //XXX Needs an item icon

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

		this.worldEventHandler = new EventHandlerWorld();
		this.entityEventHandler = new EventHandlerEntity();
		this.tickEventHandler = new EventHandlerTick();
		this.hudEventHandler = new EventHandlerHUD();
		this.drawEventHandler = new EventHandlerDrawHighlight();
		//this.renderEventHandler = new RenderEventHandler();

		MinecraftForge.EVENT_BUS.register(this.worldEventHandler);
		MinecraftForge.EVENT_BUS.register(this.entityEventHandler);
		//MinecraftForge.EVENT_BUS.register(this.tickEventHandler);
		//MinecraftForge.EVENT_BUS.register(this.hudEventHandler);
		//MinecraftForge.EVENT_BUS.register(this.drawEventHandler);
		//MinecraftForge.EVENT_BUS.register(this.renderEventHandler);

		//GameRegistry.registerFuelHandler(this.worldEventHandler);
		//GameRegistry.registerCraftingHandler(this.worldEventHandler);
		//GameRegistry.registerWorldGenerator(this.worldGen = new SteamcraftWorldGenerator());

		Config.save();
		ConfigBlocks.init();
		ConfigItems.init();
	
		proxy.registerDisplayInformation();

		//this.worldGen.initialize();
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{

		Config.registerBiomes();
		//ConfigEntities.init();
		
		//proxy.registerKeyBindinds();
	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		BiomeDictionary.registerAllBiomes();
		Config.initModCompatibility();
		ConfigItems.postInit();
		//ConfigRecipes.init();
		Config.initLoot();
		//LoggerSteamcraft.log(Level.INFO, "SC2 is " + event.getModState());
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandSteamcraft());
		LoggerSteamcraft.log(Level.INFO, "Registering commands just for you");
	}
}
