package steamcraft.common.tiles;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import steamcraft.common.entities.EntityTimeBomb;

public class TileTimeBomb extends TileEntity
{
	private int time = 0000;
	@Override
	public void updateEntity()
	{
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
    	//System.out.println(sdf.format(cal.getTime()));
    	if(Integer.parseInt(sdf.format(cal.getTime())) == time)
    	{
    		System.out.print("BANG!");
    		worldObj.setBlockToAir(xCoord, yCoord, zCoord);
    		worldObj.spawnEntityInWorld(new EntityTimeBomb(worldObj, xCoord, yCoord, zCoord, null, 0));
    	}
	}
	@Override
	 public void readFromNBT(NBTTagCompound nbt)
	    {
			super.readFromNBT(nbt);
	        this.setTime(nbt.getInteger("time"));
	    }
	@Override
	    public void writeToNBT(NBTTagCompound nbt)
	    {
			super.writeToNBT(nbt);
			nbt.setInteger("time", getTime());
	    }
	public int getTime()
	{
		return time;
	}
	public void setTime(int time)
	{
		this.time = time;
	}
}
