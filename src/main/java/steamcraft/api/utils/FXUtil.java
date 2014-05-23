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

	public static void displayFX(final String name, final World world,
			final double dx, final double dy, final double dz,
			final double velX, final double velY, final double velZ,
			final float scale)
	{
		particle = null;

		/** An example of adding an EntityFX class. */
		if (name.equals("smoke"))
		{
			particle = new EntitySmokeFX(world, dx, dy, dz, velX, velY, velZ,
					scale);
		}

		final Minecraft mc = Minecraft.getMinecraft();
		final double distX = mc.renderViewEntity.posX - particle.posX;
		final double distY = mc.renderViewEntity.posY - particle.posY;
		final double distZ = mc.renderViewEntity.posZ - particle.posZ;
		int display = mc.gameSettings.particleSetting;

		if ((display == 1) && (particle.worldObj.rand.nextInt(3) == 0))
		{
			display = 2;
		}
		if ((display <= 1)
				&& (distX * distX + distY * distY + distZ * distZ <= 4096.0D))
		{
			mc.effectRenderer.addEffect(particle);
		}
	}
}
