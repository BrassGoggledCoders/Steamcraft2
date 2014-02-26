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
 * File created @ [Feb 15, 2014, 1:57:22 PM]
 */
package common.steamcraft.common.network;

import common.steamcraft.common.lib2.LibInfo;
import cpw.mods.fml.common.IPlayerTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumChatFormatting;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;

public class PlayerTracker implements IPlayerTracker {	
	/** Should the game check for a new SC2 update? */
	private boolean checkUpdate = true;
	
	/** The name that the mod uses to verify the newest version. */
    String modName = LibInfo.MOD_NAME;
    
    /** The version that the mod uses to verify the newest version. */
    String modVersion = LibInfo.VERSION;
    
    /** Returns the version number from the Dropbox .txt file. */
    static String newestVersion;
	
	@Override
	public void onPlayerLogin(EntityPlayer player) {
		if (this.checkUpdate) {
            if (!checkForUpdatedVersion(this.modName, this.modVersion)) {
                String message = "Version " + newestVersion + " of SC2 is now available!";
    			player.sendChatToPlayer(ChatMessageComponent.createFromText(message).setColor(EnumChatFormatting.RED).setBold(true));
    			LoggerMod.log(Level.INFO, message);
                
            } else
            {
            	String message = "You are currently running the newest version of SC2!";
    			// player.sendChatToPlayer(ChatMessageComponent.createFromText(message).setColor(EnumChatFormatting.GOLD).setItalic(true));
    			LoggerMod.log(Level.INFO, message);
            }

            this.checkUpdate = false;
        }
	}
	
	/** Checks one of my Dropbox files for the most recent mod name and version. */
	public static boolean checkForUpdatedVersion(String s1, String s2) {
        try {
            URL webLink = new URL("https://www.dropbox.com/s/kxbx0bu0ir4ylre/sc2version.txt"); 
            InputStream inputStream = webLink.openStream();
            Properties prop = new Properties();
            prop.load(inputStream);
            String s3 = prop.getProperty(s1);
            newestVersion = s3;

            if (s2.compareTo(s3) != 0)
            	return true;
        } catch (Exception e) {
            LoggerMod.log(Level.FINEST, "It seems that SC2 could not establish a connection to Dropbox; no worries!");
        }

        return false;
    }

	@Override
	public void onPlayerLogout(EntityPlayer player) {}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {}
}