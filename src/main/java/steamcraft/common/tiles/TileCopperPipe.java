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
import net.minecraft.world.World;
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
	private short ticksSinceLastDistribute = 0;
	
	private ForgeDirection received = null;
	public ForgeDirection extract = null;
	public ForgeDirection[] connections = new ForgeDirection[6];

	public FluidNetwork network;
	public boolean isMaster = false;
	
	public TileCopperPipe()
	{

	}
	
	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote)
		{
			if(isMaster)
				network.updateNetwork(this.worldObj);
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
		{
			network.inputs.remove(new Coords(xCoord + extract.offsetX, yCoord + extract.offsetY, zCoord + extract.offsetZ, extract.getOpposite()));
			extract = null;
		}
		else
			for(ForgeDirection dir : connections)
				if(dir!=null && canChangeState(dir))
				{
					extract = dir;
					
					Coords temp = new Coords(xCoord + extract.offsetX, yCoord + extract.offsetY, zCoord + extract.offsetZ, extract.getOpposite());
					
					network.outputs.remove(temp);
					network.inputs.add(temp);
					
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
		{
			connections[1] = ForgeDirection.DOWN;
			updateNetwork(ForgeDirection.DOWN);
		}
		else
			connections[1] = null;
		
		if(canConnect(xCoord, yCoord, zCoord + 1))
		{
			connections[2] = ForgeDirection.SOUTH;
			updateNetwork(ForgeDirection.SOUTH);
		}
		else
			connections[2] = null;
		
		if(canConnect(xCoord, yCoord, zCoord - 1))
		{
			connections[3] = ForgeDirection.NORTH;
			updateNetwork(ForgeDirection.NORTH);
		}
		else
			connections[3] = null;
		
		if(canConnect(xCoord + 1, yCoord, zCoord))
		{
			connections[4] = ForgeDirection.EAST;
			updateNetwork(ForgeDirection.EAST);
		}
		else
			connections[4] = null;
		
		if(canConnect(xCoord - 1, yCoord, zCoord))
		{
			connections[5] = ForgeDirection.WEST;
			updateNetwork(ForgeDirection.WEST);
		}
		else
			connections[5] = null;
		
		if(extract!=null && !canChangeState(extract))
			network.inputs.remove(new Coords(xCoord + extract.offsetX, yCoord + extract.offsetY, zCoord + extract.offsetZ, extract.getOpposite()));
			
		if(network==null)
		{
			network = new FluidNetwork(1);
			this.isMaster = true;
		}
		
		for(ForgeDirection dir : connections)
		{
			if(dir!=null && extract!=dir)
				if(canChangeState(dir))
				{
					Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());
					
					if(!network.outputs.contains(temp))
						network.outputs.add(temp);
				}
		}
	}
	
	public void updateNetwork(ForgeDirection dir)
	{
		TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
		
		if(tile instanceof TileCopperPipe)
		{
			TileCopperPipe pipe = (TileCopperPipe) tile;
			
			if(pipe.network!=null && pipe.network!=network)
				if(network!=null)
				{
					if(network.size < pipe.network.size)
					{
						network = pipe.network;
						network.changeSize(1);
						
						updateNetworkToOthers(dir);
					}
					else
					{
						pipe.network = network;
						network.changeSize(1);
						
						pipe.updateNetworkToOthers(dir.getOpposite());
					}
				}
				else
				{
					network = pipe.network;
					network.changeSize(1);
				}
			
		}
	}
	
	private void updateNetworkToOthers(ForgeDirection ignore)
	{		
		for(ForgeDirection dir : connections)
			if(dir!=null && dir!=ignore && !canChangeState(dir))
			{
				TileCopperPipe pipe = (TileCopperPipe) worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
				
				if(pipe.network!=null && pipe.network.size!=0)
					pipe.network = null;
				
				network.changeSize(1);
				
				if(pipe.network!=null)
				{
					if(pipe.isMaster)
						pipe.isMaster = false;
					
					if(!pipe.network.equals(network))
					{
						pipe.network = network;
						pipe.updateNetworkToOthers(dir.getOpposite());
					}
				}
				else
					pipe.network = network;
			}
	}
	
	//maybe make the pipes distribute the fluid equally?
	public void removeFromNetwork()
	{
		if(network!=null)
		{
			network.changeSize(-1);
		
			if(network.size==0)
				network = null;
			else
			{
				for(ForgeDirection dir : connections)
					if(dir!=null && !canChangeState(dir))
					{	
						TileCopperPipe pipe = (TileCopperPipe) worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
					
						network.changeSize(-network.size);
						pipe.network = new FluidNetwork(1);
						pipe.isMaster = true;
						
						if(dir==extract)
							network.inputs.remove(new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite()));
						else if(canChangeState(dir))
							network.outputs.remove(new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite()));
					}				
			}
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
	
	public class FluidNetwork
	{
		private static final byte ticksTillUpdate = 4;
		
		private static final short maxExtractPerTile = 50*ticksTillUpdate;
		private static final short maxTransferPerTile = 100*ticksTillUpdate;
		
		FluidTank tank;
		public int size;
		
		ArrayList<Coords> inputs = new ArrayList<Coords>();
		ArrayList<Coords> outputs = new ArrayList<Coords>();
		
		private byte ticksSinceLastUpdate = 0;
		
		public FluidNetwork(int size)
		{
			this.size = size;
			this.tank = new FluidTank(200*size);
		}
		
		public void updateNetwork(World world)
		{
			ticksSinceLastUpdate++;
			
			if(ticksSinceLastUpdate > ticksTillUpdate)
			{
				System.out.println(tank.getFluidAmount());
				ticksSinceLastUpdate = 0;
				
				updateInputs(world);
				updateOutputs(world);
			}
		}
		
		private void updateInputs(World world)
		{
			for(Coords coords : inputs)
			{
				if(coords!=null)
				{
					if(tank.getFluidAmount()!=tank.getCapacity())
					{
						IFluidHandler tile = (IFluidHandler) world.getTileEntity(coords.x, coords.y, coords.z);
						if(tile!=null)
							for(FluidTankInfo info : tile.getTankInfo(coords.dir))
							{
								if(info.fluid!=null && tile.canDrain(coords.dir, info.fluid.getFluid()))
								{
									int canFill = tank.fill(new FluidStack(info.fluid.getFluid(), maxExtractPerTile), false);
									
									tank.fill(tile.drain(coords.dir, canFill, true), true);
								}
							}
					}
					else
						break;
				}
			}
		}
		
		private void updateOutputs(World world)
		{
			for(Coords coords : outputs)
			{
				if(coords!=null)
				{
					if(tank.getFluidAmount()>0)
					{
						IFluidHandler tile = (IFluidHandler) world.getTileEntity(coords.x, coords.y, coords.z);
						
						if(tile!=null)
						{
							short transfered = 0;	
				
							if(tile.canFill(coords.dir, tank.getFluid().getFluid()))
								transfered = (short) tile.fill(coords.dir, new FluidStack(tank.getFluid(), 
										Math.min(maxTransferPerTile, tank.getFluidAmount())), true);
							
							tank.drain(transfered, true);
						}
					}
					else
						break;
				}
			}
		}
		
		public void changeSize(int with)
		{
			this.size += with;
			this.tank.setCapacity(200*size);
		}
	}
	
	public class Coords
	{
		int x, y, z;
		ForgeDirection dir;
		
		public Coords(int x, int y, int z, ForgeDirection dir)
		{
			this.x = x;
			this.y = y;
			this.z = z;
			this.dir = dir;
		}
	}
}
