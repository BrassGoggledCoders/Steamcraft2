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
 * File created @ [Feb 1, 2014, 12:54:18 PM]
 */
package common.steamcraft.mod.common.block.tile;

//import com.steamcraft.mod.common.api.power.IPowerGenerator;
//import com.steamcraft.mod.common.api.power.IPowerReceptor;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

/**
 * @author MrArcane111
 *
 */
public class TileEntityMachine extends TileEntity// implements IPowerGenerator, IPowerReceptor
{
	protected boolean isRedstonePowered;
	protected boolean isReceivingPower;
	ForgeDirection orientation = ForgeDirection.UP;

	/*@Override
	public void updateEntity()
	{
		if(this instanceof IPowerGenerator)
		{
			this.generatePower(this.orientation); // Cats
		} else if(this instanceof IPowerReceptor)
		{
			this.receievePower(this.orientation);
		}
	}

	@Override
	public void receievePower(ForgeDirection side)
	{
		if(this.isRedstonePowered = true)
		{
			this.checkForRedstonePower();
		} else
		{

		}
	}

	@Override
	public boolean isReceivingPower()
	{
		return this.isReceivingPower;
	}

	@Override
	public void doWork()
	{

	}

	@Override
	public World getWorld()
	{
		return this.worldObj;
	}

	@Override
	public int powerEmitionLimit()
	{
		return 0;
	}

	@Override
	public boolean generatePower(ForgeDirection side)
	{
		return side == this.orientation;
	}

	public void checkForRedstonePower()
	{
		this.isRedstonePowered = this.worldObj.isBlockIndirectlyGettingPowered(this.xCoord, this.yCoord, this.zCoord);
	}*/
}
