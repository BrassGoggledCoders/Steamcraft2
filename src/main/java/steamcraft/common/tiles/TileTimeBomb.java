
package steamcraft.common.tiles;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import boilerplate.api.IOpenableGUI;
import boilerplate.common.baseclasses.BaseTileWithInventory;
import steamcraft.client.gui.GuiTimeBomb;
import steamcraft.common.entities.EntityTimeBomb;
import steamcraft.common.tiles.container.ContainerTimeBomb;

public class TileTimeBomb extends BaseTileWithInventory implements IOpenableGUI
{
	public TileTimeBomb()
	{
		super(0);
		// TODO Auto-generated constructor stub
	}

	private int time;

	@Override
	public void updateEntity()
	{
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("HHmm");

		if (Integer.parseInt(sdf.format(cal.getTime())) == this.time)
		{
			this.worldObj.setBlockToAir(this.xCoord, this.yCoord, this.zCoord);
			this.worldObj.spawnEntityInWorld(new EntityTimeBomb(this.worldObj, this.xCoord, this.yCoord, this.zCoord, null, 0));
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
		nbt.setInteger("time", this.getTime());
	}

	public int getTime()
	{
		return this.time;
	}

	public void setTime(int time)
	{
		this.time = time;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiTimeBomb(player.inventory, (TileTimeBomb) world.getTileEntity(x, y, z));
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerTimeBomb(player.inventory, (TileTimeBomb) world.getTileEntity(x, y, z));
	}
}
