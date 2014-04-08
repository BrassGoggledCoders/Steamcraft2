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
 * File created @ [Mar 14, 2014, 10:33:23 AM]
 */
package steamcraft.api.utils;

import net.minecraft.world.World;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class TimerUtil
{
	private World world;
	private int interval;
	private long prevTime;

	public int getInterval() 
	{
		return this.interval;
	}

	public void setInterval(int interval) 
	{
		this.interval = interval;
	}

	public TimerUtil(World world, int interval) 
	{
		this.world = world;
		this.interval = interval;
		this.prevTime = world.getWorldTime();
	}

	public TimerUtil copy() 
	{
		TimerUtil timer = new TimerUtil(this.world, this.interval);
		return timer;
	}

	public int checkTime()
	{
		long time = this.world.getWorldTime();

		if (this.prevTime + this.interval < time)
			return 0;

		int diff = (int)(time - this.prevTime);
		int count = (int)(Math.floor(diff / this.interval));
		this.prevTime += this.interval * count;
		return count;
	}

	public void reInit() 
	{
		this.prevTime = this.world.getWorldTime();
	}
}
