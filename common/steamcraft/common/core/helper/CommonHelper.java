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
 * File created @ [Jan 30, 2014, 5:51:27 PM]
 */
package common.steamcraft.common.core.helper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

/**
 * @author MrArcane111
 *
 */
public class CommonHelper {
	public static MinecraftServer server() {
		return MinecraftServer.getServer();
	}

	public static void printCurrentStackTrace(String message) {
		if (message != null)
			System.out.println(message);
		for (StackTraceElement element : Thread.currentThread().getStackTrace())
			System.out.println(element);
	}

	public static MovingObjectPosition raytraceFromEntity(World world, Entity player, boolean par3, double range) {
	    float f = 1.0F;
	    float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
	    float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
	    double d0 = player.prevPosX + (player.posX - player.prevPosX) * (double) f;
	    double d1 = player.prevPosY + (player.posY - player.prevPosY) * (double) f;
	    
	    if (!world.isRemote && player instanceof EntityPlayer)
	        d1 += 1.62D;
	    
	    double d2 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double) f;
	    Vec3 vec3 = world.getWorldVec3Pool().getVecFromPool(d0, d1, d2);
	    float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
	    float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
	    float f5 = -MathHelper.cos(-f1 * 0.017453292F);
	    float f6 = MathHelper.sin(-f1 * 0.017453292F);
	    float f7 = f4 * f5;
	    float f8 = f3 * f5;
	    double d3 = range;
	    
	    if (player instanceof EntityPlayerMP) {
	        d3 = ((EntityPlayerMP) player).theItemInWorldManager.getBlockReachDistance();
	    }
	    
	    Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
	    return world.rayTraceBlocks_do_do(vec3, vec31, par3, !par3);
	}
}
