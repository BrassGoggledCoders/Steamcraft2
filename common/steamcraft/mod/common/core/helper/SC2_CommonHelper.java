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
 * File created @ [Jan 30, 2014, 5:51:27 PM]
 */
package common.steamcraft.mod.common.core.helper;

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
public class SC2_CommonHelper {
	/*
	 * 0 - Black
     * 1 - Dark Blue
     * 2 - Dark Green
     * 3 - Dark Aqua
     * 4 - Dark Red
     * 5 - Purple
     * 6 - Orange (Gold)
     * 7 - Gray
     * 8 - Dark Gray
     * 9 - Indigo
     * a - Bright Green
     * b - Aqua
     * c - Red
     * d - Pink
     * e - Yellow
     * f - White
     * 
	 */
	//public static String baseStem = "\u00a7";
	
	public static MinecraftServer server() {
		return MinecraftServer.getServer();
	}

	public static void printCurrentStackTrace(String message) {
		if(message != null)
			System.out.println(message);
		for(StackTraceElement element : Thread.currentThread().getStackTrace())
			System.out.println(element);
	}

	/*
	 * 	I think this is an appropriate place for it
	 * 
	 *	The function is from TinkersConstruct AbilityHelper library
	 * 
	 */
	public static MovingObjectPosition raytraceFromEntity (World world, Entity player, boolean par3, double range)
	{
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
	    if (player instanceof EntityPlayerMP)
	    {
	        d3 = ((EntityPlayerMP) player).theItemInWorldManager.getBlockReachDistance();
	    }
	    Vec3 vec31 = vec3.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
	    return world.rayTraceBlocks_do_do(vec3, vec31, par3, !par3);
	}
	
	// The colors of the rainbow:
	
	/*public String getBlack(String s)
	{
		return this.baseStem + "0";
	}
	
	public String getDarkBlue(String s)
	{
		return this.baseStem + "1";
	}
	
	public String getDarkGreen(String s)
	{
		return this.baseStem + "2";
	}
	
	public String getDarkAqua(String s)
	{
		return this.baseStem + "3";
	}
	
	public String getDarkRed(String s)
	{
		return this.baseStem + "4";
	}
	
	public String getPurple(String s)
	{
		return this.baseStem + "5";
	}
	
	public String getGold(String s)
	{
		return this.baseStem + "6";
	}
	
	public String getGray(String s)
	{
		return this.baseStem + "7";
	}
	
	public String getDarkGray(String s)
	{
		return this.baseStem + "8";
	}
	
	public String getIndigo(String s)
	{
		return this.baseStem + "9";
	}
	
	public String getBrightGreen(String s)
	{
		return this.baseStem + "a";
	}
	
	public String getAqua(String s)
	{
		return this.baseStem + "b";
	}
	
	public String getRed(String s)
	{
		return this.baseStem + "c";
	}
	
	public String getPink(String s)
	{
		return this.baseStem + "d";
	}
	
	public String getYellow(String s)
	{
		return this.baseStem + "e";
	}
	
	public String getWhite(String s)
	{
		return this.baseStem + "f";
	}*/
}
