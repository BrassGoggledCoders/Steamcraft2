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
package common.steamcraft.client.core.handler;

import common.steamcraft.client.lib2.ClientResources;
import common.steamcraft.common.network.LoggerMod;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

import java.util.logging.Level;

/**
 * @author MrArcane111
 *
 */
public class SoundHandler {	
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
        try {
            event.manager.addSound(ClientResources.PREFIX_MOD + "drill.ogg");
            event.manager.addSound(ClientResources.PREFIX_MOD + "musket.ogg");
            event.manager.addSound(ClientResources.PREFIX_MOD + "rifle.ogg");
            event.manager.addSound(ClientResources.PREFIX_MOD + "reload.ogg");
            event.manager.addSound(ClientResources.PREFIX_MOD + "armor.ogg");
            event.manager.addSound(ClientResources.PREFIX_MOD + "hitflesh.ogg");
            event.manager.addSound(ClientResources.PREFIX_MOD + "hitblock.ogg");
            event.manager.addSound(ClientResources.PREFIX_MOD + "raygun.ogg");
        } catch (Exception e) {
            LoggerMod.log(Level.SEVERE, "Failed to register one or more sounds!");
        }
	}
}
