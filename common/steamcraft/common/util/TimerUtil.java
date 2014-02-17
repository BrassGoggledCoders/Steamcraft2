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
 * File created @ [Feb 17, 2014, 11:51:22 AM]
 */
package common.steamcraft.common.util;

import net.minecraft.world.World;

/**
 * A simple timer that would be useful for machines.
 * 
 * @author MrArcane111
 *
 */
public class TimerUtil {
	private World world;
	private int interval;
	private long prevTime;
	
	public int getInterval() {
		return this.interval;
	}
	
	public void setInterval(int interval) {
		this.interval = interval;
	}
	
	public TimerUtil(World world, int interval) {
		this.world = world;
		this.interval = interval;
		this.prevTime = world.getWorldTime();
	}
	
	public TimerUtil copy() {
		TimerUtil timer = new TimerUtil(this.world, this.interval);
		return timer;
	}
	
	public int checkTime() {
		long time = this.world.getWorldTime();
		
		if (this.prevTime + this.interval < time) {
			return 0;
		}
		
		int diff = (int)(time - this.prevTime);
		int count = (int)(Math.floor(diff / this.interval));
		this.prevTime += this.interval * count;
		
		return count;
	}
	
	public void reInit() {
		this.prevTime = this.world.getWorldTime();
	}
}
