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
import net.minecraft.nbt.NBTTagList;
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
	public ForgeDirection extract = null;
	public ForgeDirection[] connections = new ForgeDirection[6];

	public FluidNetwork network;
	
	public boolean isMaster = false;

	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote && isMaster)
			network.updateNetwork(this.worldObj);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		writeDirectionToNBT(tag, extract);
		
		tag.setBoolean("master", isMaster);
		
		if(isMaster)
			network.writeToNBT(tag);
	}
	
	private static void writeDirectionToNBT(NBTTagCompound tag, ForgeDirection dir)
	{
		byte index = -1;;
		
		if(dir!=null)
			switch(dir)
			{
				case DOWN:
					index = 0;
				break;
				case UP:
					index = 1;
				break;
				case NORTH:
					index = 2;
				break;
				case SOUTH:
					index = 3;
				break;
				case WEST:
					index = 4;
				break;
				case EAST:
					index = 5;
				break;
				default:
					index = -1;
				break;
			}
		
		tag.setByte("dirIndex", index);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
		extract = readDirectionFromNBT(tag);
		
		isMaster = tag.getBoolean("master");
		
		if(isMaster)
			network = FluidNetwork.readFromNBT(tag);
	}
	
	private static ForgeDirection readDirectionFromNBT(NBTTagCompound tag)
	{
		byte index = tag.getByte("dirIndex");
		
		ForgeDirection dir = ForgeDirection.getOrientation(index);
		
		if(dir==ForgeDirection.UNKNOWN)
			dir = null;
		
		return dir;
	}

	public void changeExtracting()
	{
		if(extract!=null)
		{
			if(!worldObj.isRemote)
				network.inputs.remove(new Coords(xCoord + extract.offsetX, yCoord + extract.offsetY, zCoord + extract.offsetZ, extract.getOpposite()));
			
			extract = null;
		}
		else
			for(ForgeDirection dir : connections)
				if(dir!=null && canChangeState(dir))
				{
					extract = dir;

					if(!worldObj.isRemote)
					{
						Coords temp = new Coords(xCoord + extract.offsetX, yCoord + extract.offsetY, zCoord + extract.offsetZ, extract.getOpposite());
	
						network.outputs.remove(temp);
						network.inputs.add(temp);
					}

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
		{
			if(connections[0]!=null && !worldObj.isRemote)
			{
				ForgeDirection dir = connections[0];
				
				Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());
				
				network.outputs.remove(temp);
			}
			
			connections[0] = null;
		}

		if(canConnect(xCoord, yCoord - 1, zCoord))
		{
			connections[1] = ForgeDirection.DOWN;
			updateNetwork(ForgeDirection.DOWN);
		}
		else
		{
			if(connections[1]!=null && !worldObj.isRemote)
			{
				ForgeDirection dir = connections[1];
				
				Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());
				
				network.outputs.remove(temp);
			}
			
			connections[1] = null;
		}

		if(canConnect(xCoord, yCoord, zCoord + 1))
		{
			connections[2] = ForgeDirection.SOUTH;
			updateNetwork(ForgeDirection.SOUTH);
		}
		else
		{
			if(connections[2]!=null && !worldObj.isRemote)
			{
				ForgeDirection dir = connections[2];
				
				Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());
				
				network.outputs.remove(temp);
			}
			connections[2] = null;
		}

		if(canConnect(xCoord, yCoord, zCoord - 1))
		{
			connections[3] = ForgeDirection.NORTH;
			updateNetwork(ForgeDirection.NORTH);
		}
		else
		{
			if(connections[3]!=null && !worldObj.isRemote)
			{
				ForgeDirection dir = connections[3];
				
				Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());
				
				network.outputs.remove(temp);
			}
			
			connections[3] = null;
		}

		if(canConnect(xCoord + 1, yCoord, zCoord))
		{
			connections[4] = ForgeDirection.EAST;
			updateNetwork(ForgeDirection.EAST);
		}
		else
		{
			if(connections[4]!=null && !worldObj.isRemote)
			{
				ForgeDirection dir = connections[4];
				
				Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());
				
				network.outputs.remove(temp);
			}
			
			connections[4] = null;
		}

		if(canConnect(xCoord - 1, yCoord, zCoord))
		{
			connections[5] = ForgeDirection.WEST;
			updateNetwork(ForgeDirection.WEST);
		}
		else
		{
			if(connections[5]!=null && !worldObj.isRemote)
			{
				ForgeDirection dir = connections[5];
				
				Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());
				
				network.outputs.remove(temp);
			}
			
			connections[5] = null;
		}

		if(!worldObj.isRemote)
		{
			if(network==null)
			{
				network = new FluidNetwork(1);
				this.isMaster = true;
			}
			
			if(extract!=null && !canChangeState(extract))
			{
				network.inputs.remove(new Coords(xCoord + extract.offsetX, yCoord + extract.offsetY, zCoord + extract.offsetZ, extract.getOpposite()));
				extract=null;
			}
	
			for(ForgeDirection dir : connections)
			{
				if(dir!=null && canChangeState(dir))
				{
					Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());
					
					if(extract!=dir)
					{
						if(!network.outputs.contains(temp))
							network.outputs.add(temp);
					}
					else
						if(!network.inputs.contains(temp))
							network.inputs.add(temp);
				}
			}
		}
	}

	public void updateNetwork(ForgeDirection dir)
	{
		if(!worldObj.isRemote)
		{
			TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
	
			if(tile instanceof TileCopperPipe)
			{
				TileCopperPipe pipe = (TileCopperPipe) tile;
	
				if(pipe.network!=null)
				{
					if(pipe.network!=network)
						if(network!=null)
						{
							if(network.size < pipe.network.size && network.tank.getFluidAmount()==0)
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
		}
	}

	private void updateNetworkToOthers(ForgeDirection ignore)
	{
		if(!worldObj.isRemote)
		{
			for(ForgeDirection dir : connections)
				if(dir!=null && dir!=ignore)			
					if(worldObj.getBlock(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ) == ConfigBlocks.blockCopperPipe)
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
					else if(canChangeState(dir))
					{
						Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());
						
						if(dir!=extract)
						{
							if(!network.outputs.contains(temp))
								network.outputs.add(temp);
						}
						else
							if(!network.inputs.contains(temp))
								network.inputs.add(temp);
					}	
		}
	}

	public void removeFromNetwork()
	{
		if(!worldObj.isRemote)
		{
			if(network!=null)
			{
				network.changeSize(-1);
	
				if(network.size==0)
					network = null;
				else
				{
					for(ForgeDirection dir : connections)
						if(dir!=null)
							if(!canChangeState(dir))
							{
								TileCopperPipe pipe = (TileCopperPipe) worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
		
								network.changeSize(-network.size);
								pipe.network = new FluidNetwork(1);							
								pipe.isMaster = true;
							}
							else if(dir==extract)
								network.inputs.remove(new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite()));
							else
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

	public static class FluidNetwork
	{
		private static final byte ticksTillUpdate = 4;

		private static final short maxExtractPerTile = 50*ticksTillUpdate;
		private static final short maxTransferPerTile = 100*ticksTillUpdate;

		FluidTank tank;
		public int size;
		
		public int outputsSize;
		public int inputsSize;

		ArrayList<Coords> inputs = new ArrayList<Coords>();
		ArrayList<Coords> outputs = new ArrayList<Coords>();

		private byte ticksSinceLastUpdate = 0;

		public FluidNetwork(int size)
		{
			this.size = size;
			this.outputsSize = this.inputsSize = 0;
			
			this.tank = new FluidTank(200*size);
		}

		public void updateNetwork(World world)
		{
			ticksSinceLastUpdate++;

			if(ticksSinceLastUpdate > ticksTillUpdate)
			{
				if(tank.getFluidAmount() == 0)
					tank.setFluid(null);
				
				System.out.println(tank.getFluidAmount());
				ticksSinceLastUpdate = 0;

				updateInputs(world);
				updateOutputs(world);
			}
		}

		private void updateInputs(World world)
		{
			int distribute = 0;
			int tempSize = inputs.size();
			
			for(Coords coords : inputs)
			{
				if(tempSize!=0)
					distribute = (tank.getCapacity()-tank.getFluidAmount())/tempSize;
				else
					break;
				
				if(coords!=null)
				{
					if(tank.getFluidAmount()!=tank.getCapacity())
					{
						if(world.getTileEntity(coords.x, coords.y, coords.z) instanceof IFluidHandler)
						{
							IFluidHandler tile = (IFluidHandler) world.getTileEntity(coords.x, coords.y, coords.z);
							if(tile!=null)
							{
								for(FluidTankInfo info : tile.getTankInfo(coords.dir))
								{
									if(info.fluid!=null && tile.canDrain(coords.dir, info.fluid.getFluid()))
									{
										int canFill = tank.fill(new FluidStack(info.fluid.getFluid(), Math.min(distribute, maxExtractPerTile)), false);
										
										tank.fill(tile.drain(coords.dir, canFill, true), true);
										
										tempSize--;
									}
								}
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
			int distribute = 0;
			int tempSize = outputs.size();
			
			for(Coords coords : outputs)
			{
				if(tempSize!=0)
					distribute = tank.getFluidAmount()/tempSize;
				else
					break;
				
				if(coords!=null)
				{
					if(tank.getFluidAmount()>0)
					{
						if(world.getTileEntity(coords.x, coords.y, coords.z) instanceof IFluidHandler)
						{
							IFluidHandler tile = (IFluidHandler) world.getTileEntity(coords.x, coords.y, coords.z);
	
							if(tile!=null)
							{
								short transfered = 0;
	
								if(tile.canFill(coords.dir, tank.getFluid().getFluid()))
									transfered = (short) tile.fill(coords.dir, new FluidStack(tank.getFluid(), Math.min(distribute, maxTransferPerTile)), true);
	
								tank.drain(transfered, true);
								
								tempSize--;
							}
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
		
		public void writeToNBT(NBTTagCompound tag)
		{
			NBTTagCompound temp = new NBTTagCompound();
			
			temp.setInteger("networkSize", size);
			tank.writeToNBT(temp);
			
			temp.setInteger("outputSize", outputsSize);
			temp.setInteger("inputSize", inputsSize);
			
			NBTTagCompound inputTag = new NBTTagCompound();
			writeArrayListToNBT(inputTag, inputs);
			temp.setTag("inputList", inputTag);
			
			NBTTagCompound outputTag = new NBTTagCompound();
			writeArrayListToNBT(outputTag, outputs);
			temp.setTag("outputList", outputTag);
			
			tag.setTag("network", temp);
		}
		
		private void writeArrayListToNBT(NBTTagCompound tag, ArrayList<Coords> list)
		{
			NBTTagList tagList = new NBTTagList();

			for(int i=0;i<list.size();i++)
			{
				Coords temp = list.get(i);
				if(temp!=null)
				{
					NBTTagCompound coordTag = new NBTTagCompound();
					
					coordTag.setInteger("index", i);
					temp.writeToNBT(coordTag);
					
					tagList.appendTag(coordTag);
				}
			}
			
			tag.setTag("list", tagList);
		}
		
		public static FluidNetwork readFromNBT(NBTTagCompound tag)
		{
			NBTTagCompound temp = tag.getCompoundTag("network");
			
			FluidNetwork network = new FluidNetwork(temp.getInteger("network"));
			
			network.tank = new FluidTank(200*network.size);
			network.tank.readFromNBT(temp);
			
			network.outputsSize = temp.getInteger("outputSize");
			network.inputsSize = temp.getInteger("inputSize");
			
			readArrayListFromNBT(temp.getCompoundTag("inputList"), network.inputs);
			readArrayListFromNBT(temp.getCompoundTag("outputList"), network.outputs);
			
			return network;
		}
		
		private static void readArrayListFromNBT(NBTTagCompound tag, ArrayList<Coords> list)
		{
			NBTTagList tagList = (NBTTagList) tag.getTag("list");
			
			for (int i = 0; i < tagList.tagCount(); ++i)
			{
				NBTTagCompound temp = tagList.getCompoundTagAt(i);
				
				list.add(temp.getInteger("index"), Coords.readFromNBT(temp));
			}
		}
	}

	public static class Coords
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
		
		@Override
		public boolean equals(Object obj)
		{
			if(obj instanceof Coords)
			{
				Coords coord = (Coords) obj;
				if(x == coord.x && y==coord.y && z==coord.z && dir==coord.dir)
					return true;
			}
			return false;
		}
		
		public void writeToNBT(NBTTagCompound tag)
		{
			tag.setInteger("coordX", x);
			tag.setInteger("coordY", y);
			tag.setInteger("coordZ", z);
			
			writeDirectionToNBT(tag, dir);
		}
		
		public static Coords readFromNBT(NBTTagCompound tag)
		{
			return new Coords(tag.getInteger("coordX"), tag.getInteger("coordY"), tag.getInteger("coordZ"), readDirectionFromNBT(tag));
		}
	}
}
