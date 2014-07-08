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

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import steamcraft.common.config.ConfigBlocks;

/**
 * @author Decebaldecebal
 * 
 */
public class TileCopperPipe extends TileEntity implements IFluidHandler
{
	private static byte maxExtractPerTick = TileSteamBoiler.steamPerTick;
	private static byte maxTransferPerTick = 100;
	
	private short ticksSinceLastDistribute = 0;
	
	private ForgeDirection received = null;
	public ForgeDirection extract = null;
	public ForgeDirection[] connections = new ForgeDirection[6];

	public FluidNetwork network;
	
	public TileCopperPipe()
	{
		//this.updateConnections();
	}
	
	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote)
		{
			/*
			if(extract!=null && tank.getFluidAmount()!=tank.getCapacity())
				if(worldObj.getTileEntity(xCoord+extract.offsetX, yCoord+extract.offsetY, zCoord+extract.offsetZ)!=null 
				&& worldObj.getTileEntity(xCoord+extract.offsetX, yCoord+extract.offsetY, zCoord+extract.offsetZ) instanceof IFluidHandler
				&& worldObj.getBlock(xCoord+extract.offsetX, yCoord+extract.offsetY, zCoord+extract.offsetZ)!=ConfigBlocks.blockCopperPipe)
				{
					IFluidHandler tile = (IFluidHandler) worldObj.getTileEntity(xCoord+extract.offsetX, yCoord+extract.offsetY, zCoord+extract.offsetZ);
					
					for(FluidTankInfo info : tile.getTankInfo(extract.getOpposite()))
					{
						if(info.fluid!=null && tile.canDrain(extract.getOpposite(), info.fluid.getFluid()))
						{
							int canFill = tank.fill(new FluidStack(info.fluid.getFluid(), maxExtractPerTick), false);
							
							tank.fill(tile.drain(extract.getOpposite(), canFill, true), true);
						}
					}
				}
			
			ticksSinceLastDistribute++;
			
			if(ticksSinceLastDistribute > 5)
			{
				ticksSinceLastDistribute = 0;
				
				if(tank.getFluidAmount()>0)
					for(ForgeDirection dir : connections)
						if(dir!=null && dir!=received)
							distributeFluidTo(dir, (short) Math.min(tank.getFluidAmount(), maxTransferPerTick));
			}
			
			System.out.println(tank.getFluidAmount());
			*/
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		//tank.writeToNBT(tag);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		//tank.readFromNBT(tag);
	}
	
	public void changeExtracting()
	{
		if(extract!=null)
			extract = null;
		else
			for(ForgeDirection dir : connections)
				if(dir!=null && canChangeState(dir))
				{
					extract = dir;
					break;
				}
	}
	
	public void updateConnections()
	{
		if(canConnect(xCoord, yCoord + 1, zCoord))
		{
			connections[0] = ForgeDirection.UP;
			updateNetwork(ForgeDirection.UP);
		}
		else
			connections[0] = null;
		
		if(canConnect(xCoord, yCoord - 1, zCoord))
			connections[1] = ForgeDirection.DOWN;
		else
			connections[1] = null;
		
		if(canConnect(xCoord, yCoord, zCoord + 1))
			connections[2] = ForgeDirection.SOUTH;
		else
			connections[2] = null;
		
		if(canConnect(xCoord, yCoord, zCoord - 1))
			connections[3] = ForgeDirection.NORTH;
		else
			connections[3] = null;
		
		if(canConnect(xCoord + 1, yCoord, zCoord))
			connections[4] = ForgeDirection.EAST;
		else
			connections[4] = null;
		
		if(canConnect(xCoord - 1, yCoord, zCoord))
			connections[5] = ForgeDirection.WEST;
		else
			connections[5] = null;
	}
	
	public void updateNetwork(ForgeDirection dir)
	{
		TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
		
		if(tile instanceof TileCopperPipe)
		{
			TileCopperPipe pipe = (TileCopperPipe) tile;
			
			if(network!=null)
			{
				if(network.size < pipe.network.size)
				{
					network = pipe.network;
					network.size++;
					
					updateNetworkToOthers(dir);
				}
			}
		}
		
		if(network==null)
			network = new FluidNetwork(1);
	}
	
	private void updateNetworkToOthers(ForgeDirection ignore)
	{
		for(ForgeDirection dir : connections)
			if(dir!=null && dir!=ignore)
			{
				TileCopperPipe pipe = (TileCopperPipe) worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
				
				network.size++;
				pipe.network = network;
				pipe.updateNetworkToOthers(dir.getOpposite());
			}
	}
	
	private boolean canConnect(int x, int y, int z)
	{
		return worldObj.getBlock(x, y, z) == ConfigBlocks.blockCopperPipe || isFluidHandler(x, y, z);
	}
	
	private boolean isFluidHandler(int x, int y, int z)
	{
		return worldObj.getTileEntity(x, y, z) instanceof IFluidHandler;
	}
	
	public boolean canChangeState(ForgeDirection dir)
	{
		if(worldObj.getBlock(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ) != ConfigBlocks.blockCopperPipe &&
				isFluidHandler(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ))
			return true;
		return false;
	}
	
	public ForgeDirection onlyOneOpposite()
	{
		ForgeDirection main = null;
		boolean isOpposite = false;
		
		for(ForgeDirection dir : connections)
		{
			if(main==null && dir!=null)
				main = dir;
			
			if(dir!=null && main!=dir)
				if(!areDirectionsOpposite(main, dir) && !areDirectionsOpposite(dir, main))
					return null;
				else
					isOpposite = true;
		}
		
		if(isOpposite)
			return main;
		
		return null;
	}
	
	private boolean areDirectionsOpposite(ForgeDirection dir1, ForgeDirection dir2)
	{
		if(dir1 == ForgeDirection.UP && dir2 == ForgeDirection.DOWN)
			return true;
		if(dir1 == ForgeDirection.SOUTH && dir2 == ForgeDirection.NORTH)
			return true;
		if(dir1 == ForgeDirection.EAST && dir2 == ForgeDirection.WEST)
			return true;
		
		return false;
	}

	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return false;
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		/*
		if(tank.getFluidAmount() == tank.getCapacity())
			return false;
		
		if(tank.getFluid()==null)
			return true;
		else if(tank.getFluid().getFluid() == fluid)
			return true;
		*/
		
		return false;
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return null;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		/*
		if(tank.getFluid()==null || (tank.getFluid()!=null && tank.getFluid().getFluid() == resource.getFluid()))
		{
			int amount = tank.fill(resource, doFill);
			
			if(amount > 0)
				received = from;
			else
				received = null;
			
			return amount;
		}
		 */
		return 0;

	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		//return new FluidTankInfo[]{tank.getInfo()};
		return null;
	}
	
	/*
	private void distributeFluidTo(ForgeDirection to, short amount)
	{
		short transfered = 0;	

		IFluidHandler tile = (IFluidHandler)worldObj.getTileEntity(xCoord+to.offsetX, yCoord+to.offsetY, zCoord+to.offsetZ);

		if(tile.canFill(to.getOpposite(), tank.getFluid()!=null ? tank.getFluid().getFluid() : null))
			transfered += tile.fill(to.getOpposite(), new FluidStack(tank.getFluid(), amount), true);
		
		tank.drain(transfered, true);
	}
	*/
	
	public class FluidNetwork
	{
		FluidTank tank;
		public int size;
		
		ArrayList<Coords> inputs = new ArrayList<Coords>();
		ArrayList<Coords> outputs = new ArrayList<Coords>();
		
		public FluidNetwork(int size)
		{
			this.size = size;
			this.tank = new FluidTank(200*size);
		}
	}
	
	public class Coords
	{
		int x, y, z;
		
		public Coords(int x, int y, int z)
		{
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}
