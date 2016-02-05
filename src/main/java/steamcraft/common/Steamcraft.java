
package steamcraft.common;

import java.io.File;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.gen.structure.MapGenStructureIO;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.Type;

import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;

import boilerplate.client.GuiHandler;
import boilerplate.common.IBoilerplateMod;
import boilerplate.common.compathandler.FMPCompatHandler;
import boilerplate.common.utils.handlers.BucketHandler;
import steamcraft.common.compat.CompatabilityLayer;
import steamcraft.common.compat.CompatibilityHandler;
import steamcraft.common.config.Config;
import steamcraft.common.config.ConfigGeneral;
import steamcraft.common.config.ConfigWorldGen;
import steamcraft.common.init.InitAchievements;
import steamcraft.common.init.InitBiomes;
import steamcraft.common.init.InitBlocks;
import steamcraft.common.init.InitEntities;
import steamcraft.common.init.InitItems;
import steamcraft.common.init.InitMisc;
import steamcraft.common.init.InitPackets;
import steamcraft.common.init.InitRecipes;
import steamcraft.common.lib.CommandSteamcraft;
import steamcraft.common.lib.CreativeTabSteamcraft;
import steamcraft.common.lib.LoggerSteamcraft;
import steamcraft.common.lib.ModInfo;
import steamcraft.common.lib.events.EventHandlerClient;
import steamcraft.common.lib.events.EventHandlerFML;
import steamcraft.common.lib.events.EventHandlerForge;
import steamcraft.common.worldgen.WorldGenSteamcraft;
import steamcraft.common.worldgen.dimension.WorldProviderDeeps;
import steamcraft.common.worldgen.structure.MapGenCustomScatteredFeature;
import steamcraft.common.worldgen.structure.StructureUndercityPieces;
import steamcraft.common.worldgen.structure.StructureUndercityStart;

/**
 * @author Surseance
 * @commentary by Arnold
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, guiFactory = ModInfo.CONFIG_GUI, dependencies = "required-after:boilerplate;after:Thaumcraft;after:BrassUtils")
public class Steamcraft implements IBoilerplateMod
{
	@SidedProxy(clientSide = ModInfo.CLIENT_PROXY, serverSide = ModInfo.COMMON_PROXY)
	public static CommonProxy proxy;

	@Instance(ModInfo.ID)
	public static Steamcraft instance;

	public static CreativeTabs tabSC2 = new CreativeTabSteamcraft(ModInfo.ID);

	public static File configFolder;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		LoggerSteamcraft.info("Starting Preinit");

		configFolder = new File(event.getModConfigurationDirectory(), "sc2");
		Config.initialise(configFolder);

		InitBlocks.init();
		InitItems.init();
		// InitTinkersSupport.init();

		MinecraftForge.EVENT_BUS.register(new BucketHandler());

		CompatabilityLayer.initCompatItems();
		CompatibilityHandler.preInit(event);

		LoggerSteamcraft.info("Finished Preinit");
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		LoggerSteamcraft.info("Starting Init");

		CompatabilityLayer.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler(instance));
		InitPackets.init();

		InitEntities.init();
		proxy.init();

		MinecraftForge.EVENT_BUS.register(new EventHandlerForge());
		MinecraftForge.EVENT_BUS.register(new EventHandlerClient());
		FMLCommonHandler.instance().bus().register(new EventHandlerFML());
		FMLCommonHandler.instance().bus().register(new EventHandlerClient());

		MapGenStructureIO.registerStructure(StructureUndercityStart.class, ModInfo.ID + "Undercity");
		StructureUndercityPieces.registerStructurePieces();

		MapGenStructureIO.registerStructure(MapGenCustomScatteredFeature.class, ModInfo.ID + "CustomScatteredFeature");

		if (ConfigWorldGen.generationEnabled)
			GameRegistry.registerWorldGenerator(new WorldGenSteamcraft(), 1);

		if (ConfigWorldGen.deepsDimensionEnabled)
		{
			DimensionManager.registerProviderType(ConfigGeneral.deepsDimensionID, WorldProviderDeeps.class, false);
			DimensionManager.registerDimension(ConfigGeneral.deepsDimensionID, ConfigGeneral.deepsDimensionID);
		}

		InitBiomes.init();

		FMPCompatHandler.doRegister();
		CompatibilityHandler.init(event);

		LoggerSteamcraft.info("Finished Init");
	}

	@SuppressWarnings("rawtypes")
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		LoggerSteamcraft.info("Starting Postinit");

		CompatabilityLayer.postInit();

		InitRecipes.init();
		InitAchievements.init();
		InitMisc.initDungeonLoot();

		LoggerSteamcraft.info("Finished Postinit");
		LoggerSteamcraft.info(
				"Please note: Steamcraft2 is now the officially unofficial mod of the Steampunk Forum at BrassGoggles, otherwise known as BG, which is only unofficial because making it official would cause a legal headache but is pretty much official, I'm just not allowed to call it that, so its not official, but it kinda is, ok? Got that? Signed, Major Vincent Smith (Otherwise known as warlordjones) - BrassGoggles moderation team member");
		if (Loader.isModLoaded("steamnsteel"))
			LoggerSteamcraft.info("Evening to the distingushed ladies and gentlemen of the SteamNSteel club!");
		if (Loader.isModLoaded("ImmersiveEngineering"))
			LoggerSteamcraft.info("Evening to the distingushed ladies and gentlemen of the ImmersiveEngineering club!");

		CompatibilityHandler.postInit(event);
		/*
		 * if(event.getSide() == Side.CLIENT) { // Autopopulate item/block lists
		 * from creative tab Iterator iterator = Item.itemRegistry.iterator();
		 *
		 * while(iterator.hasNext()) { Item item = (Item) iterator.next();
		 *
		 * if(item == null) { continue; }
		 *
		 * for(CreativeTabs tab : item.getCreativeTabs()) { if(tab == tabSC2) {
		 * if(item instanceof ItemBlock) item.getSubItems(item, tabSC2,
		 * GuiHandbook.modBlocks); else item.getSubItems(item, tabSC2,
		 * GuiHandbook.modItems); } } } }
		 */
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event)
	{
		event.registerServerCommand(new CommandSteamcraft());
	}

	// Remap old items from merged in mods
	@EventHandler
	public void missingMapping(FMLMissingMappingsEvent event)
	{
		for (MissingMapping m : event.get())
		{
			if (m.type == Type.BLOCK)
			{
				if (m.name.contains("water"))
				{
					m.remap(GameRegistry.findBlock(ModInfo.ID, "BlockBoilingwater"));
				}
				else if (m.name.contains("mud"))
				{
					m.remap(GameRegistry.findBlock(ModInfo.ID, "BlockBoilingmud"));
				}

			}
			else if (m.type == Type.ITEM)
			{
				if (m.name.contains("water"))
				{
					m.remap(Item.getItemFromBlock(GameRegistry.findBlock(ModInfo.ID, "BlockBoilingwater")));
				}
				else if (m.name.contains("mud"))
				{
					m.remap(Item.getItemFromBlock(GameRegistry.findBlock(ModInfo.ID, "BlockBoilingmud")));
				}
			}
		}
	}

	@Override
	public String getModID()
	{
		return ModInfo.ID;
	}

	@Override
	public Object getInstance()
	{
		return instance;
	}
}
