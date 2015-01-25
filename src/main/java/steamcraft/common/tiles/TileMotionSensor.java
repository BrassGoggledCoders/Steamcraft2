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
		// FMLLog.fine("test", "test");
		AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1, this.yCoord + 1, this.zCoord + 1)
				.expand(5, 5, 5);
		List list = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, axisalignedbb);
		Iterator iterator = list.iterator();
		EntityLiving living;

		while(iterator.hasNext())
		{
			// FMLLog.fine("test", "test");
			living = (EntityLiving) iterator.next();
			if(living.motionX > 0.5 || living.motionY > 0.5 || living.motionZ > 0.5)
			{
				worldObj.setBlock(xCoord, yCoord, zCoord, InitBlocks.blockMotionSensorOn);
			}
			else
				worldObj.setBlock(xCoord, yCoord, zCoord, InitBlocks.blockMotionSensor);
		}
	}
}
