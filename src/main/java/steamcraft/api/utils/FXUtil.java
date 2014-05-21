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
 * File created @ [Mar 14, 2014, 10:27:11 AM]
 */
package steamcraft.api.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
@SideOnly(Side.CLIENT)
public class FXUtil
{
	private static EntityFX particle;
	
	public static void displayFX(String name, World world, double dx, double dy, double dz, double velX, double velY, double velZ, float scale)
	{
		particle = null;
		
		/** An example of adding an EntityFX class. */
		if (name.equals("smoke"))
			particle = new EntitySmokeFX(world, dx, dy, dz, velX, velY, velZ, scale);
		
		Minecraft mc = Minecraft.getMinecraft();
		double distX = mc.renderViewEntity.posX - particle.posX;
		double distY = mc.renderViewEntity.posY - particle.posY;
		double distZ = mc.renderViewEntity.posZ - particle.posZ;
		int display = mc.gameSettings.particleSetting;
		
		if ((display == 1) && (particle.worldObj.rand.nextInt(3) == 0))
		{
			display = 2;
		}
		if ((display <= 1) && (distX * distX + distY * distY + distZ * distZ <= 4096.0D))
		{
			mc.effectRenderer.addEffect(particle);
		}
	}
}
