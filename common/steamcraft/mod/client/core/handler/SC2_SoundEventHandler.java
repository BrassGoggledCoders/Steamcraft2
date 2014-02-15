package common.steamcraft.mod.client.core.handler;

import common.steamcraft.mod.client.lib.SC2_ClientResources;
import common.steamcraft.mod.common.network.SC2_Logger;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

import java.util.logging.Level;

public class SC2_SoundEventHandler 
{	
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) 
	{
        try
        {
            event.manager.addSound(SC2_ClientResources.PREFIX_MOD + "drill.ogg");
            event.manager.addSound(SC2_ClientResources.PREFIX_MOD + "musket.ogg");
            event.manager.addSound(SC2_ClientResources.PREFIX_MOD + "rifle.ogg");
            event.manager.addSound(SC2_ClientResources.PREFIX_MOD + "reload.ogg");
            event.manager.addSound(SC2_ClientResources.PREFIX_MOD + "armor.ogg");
            event.manager.addSound(SC2_ClientResources.PREFIX_MOD + "hitflesh.ogg");
            event.manager.addSound(SC2_ClientResources.PREFIX_MOD + "hitblock.ogg");
            event.manager.addSound(SC2_ClientResources.PREFIX_MOD + "raygun.ogg");
        } catch (Exception e)
        {
            SC2_Logger.log(Level.SEVERE, "Failed to register one or more sounds!");
        }
	}
}