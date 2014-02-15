package common.steamcraft.mod.common.network;

import common.steamcraft.mod.common.lib.SC2_Info;

import cpw.mods.fml.common.IPlayerTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;
import net.minecraft.util.EnumChatFormatting;

import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Level;

public class SC2_PlayerTracker implements IPlayerTracker
{	
	private boolean checkUpdate = true;
    String modName = SC2_Info.MOD_NAME;
    String modVersion = SC2_Info.VERSION;
    static String newestVersion;
	
	@Override
	public void onPlayerLogin(EntityPlayer player) 
	{
		if(this.checkUpdate)
        {
            if(!checkForUpdatedVersion(this.modName, this.modVersion))
            {
                String message = "Version " + newestVersion + " of SC2 is now available!";
    			player.sendChatToPlayer(ChatMessageComponent.createFromText(message).setColor(EnumChatFormatting.RED).setBold(true));
    			SC2_Logger.log(Level.INFO, message);
                
            } else
            {
            	String message = "You are currently running the newest version of SC2!";
    			player.sendChatToPlayer(ChatMessageComponent.createFromText(message).setColor(EnumChatFormatting.GOLD).setItalic(true));
    			SC2_Logger.log(Level.INFO, message);
            }

            this.checkUpdate = false;
        }
	}
	
	public static boolean checkForUpdatedVersion(String s1, String s2)
    {
        try {
            URL webLink = new URL("https://www.dropbox.com/s/kxbx0bu0ir4ylre/sc2version.txt"); // Checks my dropbox for the most recent mod name and version
            InputStream inputStream = webLink.openStream();
            Properties prop = new Properties();
            prop.load(inputStream);
            String s3 = prop.getProperty(s1);
            newestVersion = s3;

            if(s2.compareTo(s3) != 0)
            {
                return true;
            }
        } catch (Exception e)
        {
            ;
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