package steamcraft.common.tiles;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import steamcraft.common.InitBlocks;

public class TileMotionSensor extends TileEntity
{

	@SuppressWarnings("rawtypes")
	@Override
	public void updateEntity()
	{
		AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox((double) this.xCoord - 5, (double) this.yCoord - 5,
				(double) this.zCoord - 5, this.xCoord + 5, this.yCoord + 5, this.zCoord + 5);
		List list = this.worldObj.getEntitiesWithinAABB(EntityLiving.class, axisalignedbb);
		Iterator iterator = list.iterator();
		EntityLiving living;

		while(iterator.hasNext())
		{
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
