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
 * File created @ [Feb 17, 2014, 11:57:43 AM]
 */
package common.steamcraft.common.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
/**
 * A util to help add FX (particles).
 * 
 * @author MrArcane111
 *
 */
public class EffectUtil {
	private static EntityFX particle;

	public static void displayFX(String name, World world, double x, double y, double z, double velX, double velY, double velZ, float scale) {
		particle = null;
		
		// An example for adding an effect.
		if (name.equals("smoke")) {
			particle = new EntitySmokeFX(world, x, y, z, velX, velY, velZ, scale);
		}
		
		Minecraft mc = Minecraft.getMinecraft();
		double distX = mc.renderViewEntity.posX - particle.posX;
		double distY = mc.renderViewEntity.posX - particle.posY;
		double distZ = mc.renderViewEntity.posZ - particle.posZ;
		int doDisplay = mc.gameSettings.particleSetting;
		
		if ((doDisplay == 1) && (particle.worldObj.rand.nextInt(3) == 0)) {
			doDisplay = 2;
		}
		
		if ((doDisplay <= 1) && (distX * distX + distY * distY + distZ * distZ <= 4096.0D)) {
			mc.effectRenderer.addEffect(particle);
		}
	}
}
