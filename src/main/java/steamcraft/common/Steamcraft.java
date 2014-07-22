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

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import steamcraft.client.gui.GuiHandler;
import steamcraft.common.compat.CompatabilityLayer;
import steamcraft.common.config.Config;
import steamcraft.common.config.ConfigWorldGen;
import steamcraft.common.lib.CommandSteamcraft;
import steamcraft.common.lib.LibInfo;
import steamcraft.common.lib.events.EventHandlerFML;
import steamcraft.common.lib.events.EventHandlerForge;
import boilerplate.common.CreativeTabBase;
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
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
@Mod(modid = LibInfo.ID, name = LibInfo.NAME, version = LibInfo.VERSION, dependencies = "required-after:boilerplate")
public class Steamcraft
{
	@SidedProxy(clientSide = LibInfo.CLIENT_PROXY, serverSide = LibInfo.COMMON_PROXY)
	public static CommonProxy proxy;

	@Instance(LibInfo.ID)
	public static Steamcraft instance;


	
	public InitWorldGen worldGen = new InitWorldGen();

	public static BiomeGenBase biomeBrassForest;

	public File directory;

	public static CreativeTabs tabSC2 = new CreativeTabBase(CreativeTabs.getNextID(), "steamcraft", InitItems.brassGoggles);

	public static String configPath;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		directory = event.getModConfigurationDirectory();

		LanguageRegistry.instance().getStringLocalization("itemGroup.steamcraft", "en_US");

		configPath = event.getModConfigurationDirectory() + "/sc2/";

		Config.initialise(configPath);

		// TODO: Reimplement DrawEvent
		MinecraftForge.EVENT_BUS.register(new EventHandlerForge());
		FMLCommonHandler.instance().bus().register(new EventHandlerFML());

		if (ConfigWorldGen.generationEnabled)
			GameRegistry.registerWorldGenerator(worldGen, 0);

		InitBlocks.init();
		InitItems.init();

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		
		InitPackets.init();
		
		// VillagerRegistry.instance().registerVillageCreationHandler(new
		// VillageCreationHandler());
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		InitEntities.init();
		InitAchievements.init();

		proxy.registerDisplayInformation();
		proxy.registerRenderers();

		//biomeBrassForest = new BiomeGenBrassForest(Config.biomeIDBrassForest).setBiomeName("Brass Forest");
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		CompatabilityLayer.init();
		InitEntities.initEntitySpawns();
		InitItems.postInit();
		InitRecipes.init();
		final ModContainer container = FMLCommonHandler.instance().findContainerFor(this);
		LanguageRegistry.instance().loadLanguagesFor(container, Side.CLIENT);

		// if(ClientHelper.mc().currentScreen instanceof GuiMainMenu)
		// SplashesHelper.hackSplashes((GuiMainMenu)
		// ClientHelper.mc().currentScreen);
	}

	@Mod.EventHandler
	public void serverStarting(final FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandSteamcraft());
	}
}
