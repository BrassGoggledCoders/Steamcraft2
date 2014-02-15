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
package common.steamcraft.mod.common.core.proxy;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.input.Keyboard;

import common.steamcraft.mod.client.core.handler.SC2_GuiHandler;
import common.steamcraft.mod.client.core.handler.SC2_KeyHandler;
import common.steamcraft.mod.common.SC2;
import common.steamcraft.mod.common.block.ModBlocks;
import common.steamcraft.mod.common.core.handler.SC2_ChestGenHandler;
import common.steamcraft.mod.common.core.handler.SC2_CommandHandler;
import common.steamcraft.mod.common.core.handler.SC2_ConfigHandler;
import common.steamcraft.mod.common.core.handler.SC2_EventHandler;
import common.steamcraft.mod.common.core.handler.SC2_GenerationHandler;
import common.steamcraft.mod.common.core.handler.SC2_ServerTickHandler;
import common.steamcraft.mod.common.core.helper.SC2_CompatibilityChecker;
import common.steamcraft.mod.common.entity.EntityBullet;
import common.steamcraft.mod.common.item.ModItems;
import common.steamcraft.mod.common.lib.SC2_EntityIDs;
import common.steamcraft.mod.common.lib.SC2_Info;
import common.steamcraft.mod.common.network.SC2_PlayerTracker;

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
public class SC2_CommonProxy {
    public static SC2_ConfigHandler cfgHandler;

    public void preInit(FMLPreInitializationEvent event) {
        cfgHandler = new SC2_ConfigHandler(event.getSuggestedConfigurationFile());
        cfgHandler.loadConfig();
		ModBlocks.initBlocks();
		ModItems.initItems();
        this.initEntities();
	}

	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new SC2_EventHandler());
		GameRegistry.registerWorldGenerator(new SC2_GenerationHandler());
		NetworkRegistry.instance().registerGuiHandler(SC2.instance, new SC2_GuiHandler());
		GameRegistry.registerPlayerTracker(new SC2_PlayerTracker());
		TickRegistry.registerTickHandler(new SC2_ServerTickHandler(), Side.SERVER);
		SC2_ChestGenHandler.addItemsToChests();
		//this.registerKeyBinds();
        this.initTileEntities();
	}

	public void postInit(FMLPostInitializationEvent event) {
        cfgHandler.saveConfig();
		SC2_CompatibilityChecker.postinit();
		//ModRecipes.initRecipes();
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new SC2_CommandHandler());
	}

    public void initEntities() {
        // Bullet
        EntityRegistry.registerGlobalEntityID(EntityBullet.class, "Bullet", EntityRegistry.findGlobalUniqueEntityId());
        EntityRegistry.registerModEntity(EntityBullet.class, "Bullet", SC2_EntityIDs.BULLET_ID, SC2.instance, 100, 10, false);
    }

    public void initTileEntities() {
        // Lamp
        GameRegistry.registerTileEntity(common.steamcraft.mod.common.block.tile.TileEntityLamp.class, SC2_Info.MOD_ID + "TELamp");
        // Lightning Rod
        GameRegistry.registerTileEntity(common.steamcraft.mod.common.block.tile.TileEntityLightningRod.class, SC2_Info.MOD_ID + "TELightningRod");
    }

	public void registerKeyBinds() {
		KeyBinding[] key = { new KeyBinding("FKey", Keyboard.KEY_F) };
		boolean[] repeat = { false };
		KeyBindingRegistry.registerKeyBinding(new SC2_KeyHandler(key, repeat));
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
}