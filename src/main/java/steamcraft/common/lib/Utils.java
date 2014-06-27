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
 * File created @ [Mar 14, 2014, 11:02:06 AM]
 */
package steamcraft.common.lib;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * The Class Utils.
 *
 * @author Surseance (Johnny Eatmon)
 */
public class Utils
{

	/** The random. */
	public Random random = new Random();

	/** The newest version. */
	static String newestVersion;

	/**
	 * Check for updated version.
	 *
	 * @param name
	 *            the name
	 * @param version
	 *            the version
	 * @return true, if successful
	 */
	public static boolean checkForUpdatedVersion(String name, String version)
	{
		try
		{
			URL webLink = new URL(LibInfo.VERSION_URL);
			InputStream inputStream = webLink.openStream();
			Properties prop = new Properties();
			prop.load(inputStream);
			String newVersion = prop.getProperty(name);
			newestVersion = newVersion;

			if (version.compareTo(newVersion) != 0)
			{
				return true;
			}
		} catch (Exception e)
		{
			LoggerSteamcraft.log(Level.FINEST, "Could not establish a connection to Dropbox.com; no worries!");
		}

		return false;
	}

	/**
	 * Download capes.
	 */
	@SideOnly(Side.CLIENT)
	public static void downloadCapes()
	{
		String[] developers = getUsersFromUrl("https://www.dropbox.com/s/m7tn0tx7y7w630s/devs.txt");

		for (String username : developers)
		{
			try
			{
				ThreadDownloadImageData imageData = new ThreadDownloadImageData("https://www.dropbox.com/s/cicvp1u0kq9xtbp/steamcraft_cape_1.png",
						null, null);
				Minecraft.getMinecraft().getTextureManager().loadTexture((new ResourceLocation("cloaks/" + username)), imageData);
			} catch (Exception e)
			{
				LoggerSteamcraft.log(Level.INFO, "Unable to load capes");
			}
		}

		// String donators[] = getUsersFromUrl(LibInfo.DONATOR_LIST);
		/*
		 * for (String username : donators) { try { ThreadDownloadImageData
		 * imageData = new ThreadDownloadImageData(LibInfo.DONATOR_CAPE_URL,
		 * null, null); Minecraft.getMinecraft().renderEngine.loadTexture(new
		 * ResourceLocation(("cloaks/" + username)),
		 * ((ITextureObject)imageData)); } catch (Exception e) {
		 * LoggerSteamcraft.log(Level.INFO, "Unable to load capes"); } }
		 */
	}

	/**
	 * Gets the users from url.
	 *
	 * @param url
	 *            the url
	 * @return the users from url
	 */
	private static String[] getUsersFromUrl(String url)
	{
		ArrayList<String> list = new ArrayList<String>();
		try
		{
			BufferedReader urlReader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
			String line;

			while ((line = urlReader.readLine()) != null)
			{
				list.add(line);
			}

			return list.toArray(new String[list.size()]);
		} catch (Exception e)
		{
			e.printStackTrace();
			LoggerSteamcraft.log(Level.INFO, "Unable to connect to Dropbox");

			return list.toArray(new String[list.size()]);
		}
	}
}
