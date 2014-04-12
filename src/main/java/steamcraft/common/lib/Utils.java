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
import net.minecraft.client.renderer.texture.TextureObject;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import steamcraft.api.coord.Coord;
import steamcraft.client.fx.FXLaser;
import steamcraft.common.config.ConfigBlocks;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.network.LoggerSteamcraft;
import cpw.mods.fml.client.FMLClientHandler;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class Utils
{
	public Random random = new Random();
	static String newestVersion;

	public static boolean checkForUpdatedVersion(String name, String version)
	{
		try {
			URL webLink = new URL(LibInfo.VERSION_URL);
			InputStream inputStream = webLink.openStream();
			Properties prop = new Properties();
			prop.load(inputStream);
			String newVersion = prop.getProperty(name);
			newestVersion = newVersion;

			if (version.compareTo(newVersion) != 0)
				return true;
		} catch (Exception e) {
			LoggerSteamcraft.log(Level.FINEST, "Could not establish a connection to Dropbox.com; no worries!");
		}

		return false;
	}

	public static void sparkle(World world, int x, int y, int z, String particleName)
	{
		Random random = world.rand;
		double offset = 0.0625D;

		for (int amount = 0; amount < 6; amount++)
		{
			double dx = (float)x + random.nextFloat();
			double dy = (float)y + random.nextFloat();
			double dz = (float)z + random.nextFloat();

			if ((amount == 0) && (!world.isBlockNormalCubeDefault(x, y + 1, z, false)))
			{
				dy = (double)(y + 1) + offset;
			}
			
			if ((amount == 1) && (!world.isBlockNormalCubeDefault(x, y - 1, z, false)))
			{
				dy = (double)(y + 0) - offset;
			}
			
			if ((amount == 2) && (!world.isBlockNormalCubeDefault(x, y, z + 1, false)))
			{
				dz = (double)(z + 1) + offset;
			}
			
			if ((amount == 3) && (!world.isBlockNormalCubeDefault(x, y, z - 1, false)))
			{
				dz = (double)(z + 0) - offset;
			}
			
			if ((amount == 4) && (!world.isBlockNormalCubeDefault(x + 1, y, z, false)))
			{
				dx = (double)(x + 1) + offset;
			}
			
			if ((amount == 5) && (!world.isBlockNormalCubeDefault(x - 1, y, z, false)))
			{
				dx = (double)(x + 0) - offset;
			}
			
			if ((dx < (double)x) || (dx > (double)(x + 1)) || (dy < 0.0D) || (dy > (double)(y + 1)) || (dz < (double)z) || (dz > (double)(z + 1)))
			{
				world.spawnParticle(particleName, dx, dy, dz, -1.0D, 1.0D, -1.0D);
				//Steamcraft.proxy.smokeFX(world, dx, dy, dz, FXSmoke.class);
			}
		}
	}

	public static void downloadCapes()
	{
		String[] developers = getUsersFromUrl("https://www.dropbox.com/s/m7tn0tx7y7w630s/devs.txt");

		for (String username : developers)
		{
			try {	
				ThreadDownloadImageData imageData = new ThreadDownloadImageData("https://www.dropbox.com/s/cicvp1u0kq9xtbp/steamcraft_cape_1.png", null, null);
				Minecraft.getMinecraft().getTextureManager().loadTexture((new ResourceLocation("cloaks/" + username)), (TextureObject)imageData);
			} catch (Exception e) {
				LoggerSteamcraft.log(Level.INFO, "Unable to load capes");
			}
		}

		//String donators[] = getUsersFromUrl(LibInfo.DONATOR_LIST);
		/*
		for (String username : donators)
		{
			try
			{
				ThreadDownloadImageData imageData = new ThreadDownloadImageData(LibInfo.DONATOR_CAPE_URL, null, null);
				Minecraft.getMinecraft().renderEngine.loadTexture(new ResourceLocation(("cloaks/" + username)), ((ITextureObject)imageData));
			} catch (Exception e) {
				LoggerSteamcraft.log(Level.INFO, "Unable to load capes");
			}
		}*/
	}

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

			return (String[]) list.toArray(new String[list.size()]);
		} catch(Exception e) {
			e.printStackTrace();
			LoggerSteamcraft.log(Level.INFO, "Unable to connect to Dropbox");
			
			return (String[]) list.toArray(new String[list.size()]);
		}
	}

	private void spawnParticles(Coord cnt, Coord move, World world, int half, double r, double g, double b)
	{
		double shift = 0;
		
		if (half == 0) 
			shift = -0.5D + this.random.nextFloat() * 1.0D;
		if (half == 1)
			shift = -this.random.nextFloat() * 0.5D;
		if (half == 2) 
			shift = this.random.nextFloat() * 0.5D;

		double dx = cnt.x + 0.5D;
		double dy = cnt.y + 0.5D;
		double dz = cnt.z + 0.5D;

		double dmx, dmy, dmz;

		dmx = dx;
		dmy = dy;
		dmz = dz;

		if (move.x > 0) 
			dmx = dx + shift;
		if (move.y > 0)
			dmy = dy + shift;
		if (move.z > 0) 
			dmz = dz + shift;

		if (move.x < 0)
			dmx = dx - shift;
		if (move.y < 0)
			dmy = dy - shift;
		if (move.z < 0)
			dmz = dz - shift;

		FMLClientHandler.instance().getClient().effectRenderer.addEffect(new FXLaser(world, dmx, dmy, dmz, (float) r, (float) g, (float) b, move.x, move.y, move.z, half));
	}

	private ItemStack fillBucket(World world, MovingObjectPosition mop) 
	{
		int bid = world.getBlockId(mop.blockX, mop.blockY, mop.blockZ);

		if ((bid == ConfigBlocks.blockSteam.blockID) && (world.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ) == 0)) 
		{
			world.setBlockToAir(mop.blockX, mop.blockY, mop.blockZ);
			return new ItemStack(ConfigItems.itemBucketSteam);
		} 
		else
		{
			return null;
		}
	}
}
