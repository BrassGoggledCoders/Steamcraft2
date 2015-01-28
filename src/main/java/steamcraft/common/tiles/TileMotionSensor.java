/**
 * This class was created by BrassGoggledCoders modding team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 */
package steamcraft.common.tiles;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import steamcraft.common.InitBlocks;

public class TileMotionSensor extends TileEntity
{
	@Override
	public void updateEntity()
	{
		AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1, this.yCoord + 1, this.zCoord + 1)
				.expand(5, 5, 5);
		List list = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, axisalignedbb);
		Iterator iterator = list.iterator();
		EntityLiving living;

		while(iterator.hasNext())
		{
			living = (EntityLiving) iterator.next();
			if((living.motionX > 0.5) || (living.motionY > 0.5) || (living.motionZ > 0.5))
			{
				this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, InitBlocks.blockMotionSensorOn);
			}
			else
				this.worldObj.setBlock(this.xCoord, this.yCoord, this.zCoord, InitBlocks.blockMotionSensor);
		}
	}
}
