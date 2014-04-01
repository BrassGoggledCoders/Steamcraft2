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
 * File created @ [Jan 30, 2014, 5:19:11 PM]
 */
package common.steamcraft.common.core.proxy;

import java.util.Iterator;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.Packet;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import common.steamcraft.client.core.handler.GuiHandler;
import common.steamcraft.client.core.handler.KeyHandlerMod;
import common.steamcraft.common.SC2;
import common.steamcraft.common.block.ModBlocks;
import common.steamcraft.common.core.compat.ModCompatLayer;
import common.steamcraft.common.core.handler.ChestLootGenerator;
import common.steamcraft.common.core.handler.CommandHandler;
import common.steamcraft.common.core.handler.ConfigHandler;
import common.steamcraft.common.core.handler.ModEventHandler;
import common.steamcraft.common.core.handler.TickHandler;
import common.steamcraft.common.core.handler.WorldGenerator;
import common.steamcraft.common.core.handler.recipe.ModRecipes;
import common.steamcraft.common.core.helper.CompatHelper;
import common.steamcraft.common.entity.EntityBullet;
import common.steamcraft.common.item.ModItems;
import common.steamcraft.common.lib2.EntityIDs;
import common.steamcraft.common.lib2.LibInfo;
import common.steamcraft.common.network.PlayerTracker;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

/**
 * @author MrArcane111 & general3214
 *
 */
public class CommonProxy {
	public static ConfigHandler cfgHandler;

	public void preInit(FMLPreInitializationEvent event) {
		cfgHandler = new ConfigHandler(event.getSuggestedConfigurationFile());
		cfgHandler.loadConfig();
		ModBlocks.initBlocks();
		ModItems.initItems();
		this.initEntities();
		MinecraftForge.addGrassSeed(new ItemStack(ModItems.teaSeed,1,0), 1);
	}

	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ModEventHandler());
		GameRegistry.registerWorldGenerator(new WorldGenerator());
		NetworkRegistry.instance().registerGuiHandler(SC2.instance, new GuiHandler());
		GameRegistry.registerPlayerTracker(new PlayerTracker());
		TickRegistry.registerTickHandler(new TickHandler(), Side.SERVER);
		ChestLootGenerator.addChestLoot();
		//this.registerKeyBinds();
		this.initTileEntities();
		ModCompatLayer.registerOreDictionary();
	}

	public void postInit(FMLPostInitializationEvent event) {
		cfgHandler.saveConfig();
		CompatHelper.postinit();
		//Does nothing yet.
		ModCompatLayer.loadModCompat();
		ModRecipes.initRecipes();
		ModRecipes.initSmelting();
	}

	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new CommandHandler());
	}

	public void initEntities() {
		// Bullet
		EntityRegistry.registerGlobalEntityID(EntityBullet.class, "Bullet", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(EntityBullet.class, "Bullet", EntityIDs.BULLET_ID, SC2.instance, 100, 10, false);
	}

	public void initTileEntities() {
		// Lamp
		GameRegistry.registerTileEntity(common.steamcraft.common.block.tile.TileEntityLamp.class, LibInfo.MOD_ID + "TELamp");
		// Lightning Rod
		GameRegistry.registerTileEntity(common.steamcraft.common.block.tile.TileEntityLightningRod.class, LibInfo.MOD_ID + "TELightningRod");
		// Etherium Crystal
		GameRegistry.registerTileEntity(common.steamcraft.common.block.tile.TileEntityEtheriumCrystal.class, LibInfo.MOD_ID + "TECrystal");
	}

	public void registerKeyBinds() {
		KeyBinding[] key = { new KeyBinding("FKey", Keyboard.KEY_F) };
		boolean[] repeat = { false };
		KeyBindingRegistry.registerKeyBinding(new KeyHandlerMod(key, repeat));
	}

	public boolean isClient() {
		return false;
	}

	public ModelBiped getMonocleArmorModel(int id) {
		return null;
	}

	public ModelBiped getWingsArmorModel(int id) {
		return null;
	}

	public ModelBiped getCapeArmorModel(int id) {
		return null;
	}

	public Object rayBeam(World world, EntityPlayer player, double tx, double ty, double tz, int type, /*int color,*/ boolean reverse, float endmod, Object input, int impact) {
		return null;
	}

	public void sendToPlayers(Packet packet, World world, int x, int y, int z, Integer maxDistance) {
		if (maxDistance == null) {
			maxDistance = Integer.valueOf(128);
		}

		Iterator<?> i$;

		if (packet != null) {
			for (i$ = world.playerEntities.iterator(); i$.hasNext(); ) { 
				Object player = i$.next();
				EntityPlayerMP playerMP = (EntityPlayerMP)player;

				if ((Math.abs(playerMP.posX - x) <= maxDistance.intValue()) && (Math.abs(playerMP.posY - y) <= maxDistance.intValue()) && (Math.abs(playerMP.posZ - z) <= maxDistance.intValue())) {
					playerMP.playerNetServerHandler.sendPacketToPlayer(packet);
				}	
			}
		}
	}
}