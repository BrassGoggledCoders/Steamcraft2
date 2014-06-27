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
import steamcraft.client.gui.GuiHandler;
import steamcraft.common.config.Config;
import steamcraft.common.config.ConfigAchievements;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.config.ConfigEntities;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.config.ConfigRecipes;
import steamcraft.common.lib.CommandSteamcraft;
import steamcraft.common.lib.CreativeTabSteamcraft;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.LoggerSteamcraft;
import steamcraft.common.lib.events.EventHandlerSC2;
import steamcraft.common.lib.world.SteamcraftWorldGenerator;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;

// TODO: Auto-generated Javadoc
/**
 * The Class Steamcraft.
 *
 * @author Surseance (Johnny Eatmon)
 */
@Mod(modid = LibInfo.ID, name = LibInfo.NAME, version = LibInfo.VERSION, dependencies = "required-after:boilerplate")
public class Steamcraft
{
	@SidedProxy(clientSide = LibInfo.CLIENT_PROXY, serverSide = LibInfo.COMMON_PROXY)
	public static CommonProxy proxy;

	/** The instance. */
	@Instance(LibInfo.ID)
	public static Steamcraft instance;

	/** The world gen. */
	public SteamcraftWorldGenerator worldGen = new SteamcraftWorldGenerator();

	/** The directory. */
	public File directory;

	/** The sc2 event handler. */
	public EventHandlerSC2 sc2EventHandler;

	/** The tab s c2. */
	public static CreativeTabs tabSC2 = new CreativeTabSteamcraft(CreativeTabs.getNextID(), "steamcraft");

	/**
	 * Pre init.
	 *
	 * @param event
	 *            the event
	 */
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		this.directory = event.getModConfigurationDirectory();

		LanguageRegistry.instance().getStringLocalization("itemGroup.steamcraft", "en_US");
		try
		{
			Config.initialize(event.getSuggestedConfigurationFile());
		} catch (final Exception e)
		{
			LoggerSteamcraft.log(Level.SEVERE, "Failed to load configuration file!");
		} finally
		{
			if (Config.config != null)
			{
				Config.save();
			}
		}
		//TODO: Reimplement DrawEvent
		sc2EventHandler = new EventHandlerSC2();
		MinecraftForge.EVENT_BUS.register(sc2EventHandler);
		FMLCommonHandler.instance().bus().register(sc2EventHandler);

		if (Config.generationEnabled)
		GameRegistry.registerWorldGenerator(this.worldGen, 0);

		ConfigBlocks.init();
		ConfigItems.init();

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		//VillagerRegistry.instance().registerVillageCreationHandler(new VillageCreationHandler());
	}

	/**
	 * Inits the.
	 *
	 * @param event
	 *            the event
	 */
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		ConfigEntities.init();
		ConfigAchievements.init();

		proxy.registerDisplayInformation();
		proxy.registerRenderers();
	}

	/**
	 * Post init.
	 *
	 * @param event
	 *            the event
	 */
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		CompatabilityLayer.init();
		ConfigEntities.initEntitySpawns();
		ConfigItems.postInit();
		ConfigRecipes.init();
		final ModContainer container = FMLCommonHandler.instance().findContainerFor(this);
		LanguageRegistry.instance().loadLanguagesFor(container, Side.CLIENT);
	}

	/**
	 * Server starting.
	 *
	 * @param event
	 *            the event
	 */
	@Mod.EventHandler
	public void serverStarting(final FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandSteamcraft());
	}
}
