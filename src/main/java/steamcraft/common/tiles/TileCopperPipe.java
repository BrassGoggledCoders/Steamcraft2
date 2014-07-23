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
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import steamcraft.client.renderers.tile.TileCopperPipeRenderer;
import steamcraft.common.InitBlocks;
import steamcraft.common.InitPackets;
import steamcraft.common.packets.CopperPipePacket;
import steamcraft.common.packets.FluidNetworkPacket;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;

/**
 * @author Decebaldecebal
 *
 */
public class TileCopperPipe extends TileEntity
{
	public ForgeDirection extract = null;
	public ForgeDirection[] connections = new ForgeDirection[6];

	public FluidNetwork network;
	
	public boolean isMaster = false;

	@Override
	public void updateEntity()
	{
		if(isMaster)
		{
			if(network.updateNetworkForPipes)
			{
				network.updateNetworkForPipes = false;
				this.updateConnections();
			}
			else if(!worldObj.isRemote)
				network.updateNetwork(this);
		}
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

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();
		
		writeDirectionToNBT(tag, extract);
		tag.setBoolean("master", isMaster);
		
		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}
	
	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
	{
		extract = readDirectionFromNBT(packet.func_148857_g());
		isMaster = packet.func_148857_g().getBoolean("master");
		
		if(isMaster)
		{
			network = new FluidNetwork(1);
			network.updateNetworkForPipes = true;
		}
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
				if(dir!=null && isFluidHandler(dir))
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

	private void removeConnections(int i)
	{		
		if(connections[i]!=null && !worldObj.isRemote)
		{
			ForgeDirection dir = connections[i];

			Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());

			network.outputs.remove(temp);
		}

		connections[i] = null;
	}
	
	public void updateConnections()
	{		
		
		System.out.println("coords " + xCoord + " " + yCoord + " " + zCoord);
		if(canConnect(ForgeDirection.UP))
		{
			if(updateNetwork(ForgeDirection.UP))
				connections[0] = ForgeDirection.UP;
		}
		else
			removeConnections(0);

		if(canConnect(ForgeDirection.DOWN))
		{
			if(updateNetwork(ForgeDirection.DOWN))
				connections[1] = ForgeDirection.DOWN;
		}
		else
			removeConnections(1);

		if(canConnect(ForgeDirection.SOUTH))
		{
			if(updateNetwork(ForgeDirection.SOUTH))
				connections[2] = ForgeDirection.SOUTH;
		}
		else
			removeConnections(2);

		if(canConnect(ForgeDirection.NORTH))
		{
			if(updateNetwork(ForgeDirection.NORTH))
				connections[3] = ForgeDirection.NORTH;
		}
		else
			removeConnections(3);

		if(canConnect(ForgeDirection.EAST))
		{
			if(updateNetwork(ForgeDirection.EAST))
				connections[4] = ForgeDirection.EAST;
		}
		else
			removeConnections(4);

		if(canConnect(ForgeDirection.WEST))
		{
			if(updateNetwork(ForgeDirection.WEST))
				connections[5] = ForgeDirection.WEST;
		}
		else
			removeConnections(5);
		
		if(network==null)
		{
			network = new FluidNetwork(1);
			this.isMaster = true;
		}
		
		if(!worldObj.isRemote)
		{						
			if(extract!=null && !isFluidHandler(extract))
			{
				network.inputs.remove(new Coords(xCoord + extract.offsetX, yCoord + extract.offsetY, zCoord + extract.offsetZ, extract.getOpposite()));
				extract=null;
			}
	
			for(ForgeDirection dir : connections)
			{
				if(dir!=null && isFluidHandler(dir))
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
		
		if(!worldObj.isRemote)
			updateClient();
	}
	
	public boolean updateNetwork(ForgeDirection dir)
	{
		TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);

		if(tile instanceof TileCopperPipe)
		{
			TileCopperPipe pipe = (TileCopperPipe) tile;

			if(pipe.network!=null)//Should only happen on world load
			{
				if(!pipe.network.equals(network))
				{
					if(network==null)
					{
						network = pipe.network;
						network.changeSize(1);
						
						pipe.updateConnections();
					}
					else
					{
						if(network.tank.getFluid()==null || pipe.network.tank.getFluid()==null)
						{
							FluidNetwork temp = network;
							
							pipe.network = network = pipe.network.tank.getFluid()!=null ? pipe.network : network;
							
							network.changeSize(1);
							
							if(!temp.equals(network))
							{
								this.isMaster = false;
								updateConnections();
							}
							else
								pipe.isMaster = false;
							
							pipe.updateConnections();

						}
						else if(network.tank.getFluid().getFluid().equals(pipe.network.tank.getFluid().getFluid()))
						{
							FluidStack fluid = new FluidStack(network.tank.getFluid(), network.tank.getFluidAmount() + pipe.network.tank.getFluidAmount());
							
							if(network.size > pipe.network.size)
							{
								pipe.isMaster = false;
								pipe.network = network;
								pipe.network.tank.setFluid(fluid);
								
								network.changeSize(1);
							}
							else
							{
								this.isMaster = false;
								network = pipe.network;
								network.tank.setFluid(fluid);
								
								network.changeSize(1);
								
								updateConnections();
							}
							
							pipe.updateConnections();
						}
						else
							return false;
					}
				}
			}
			else if(network!=null)
			{
				pipe.isMaster = false;
				pipe.network = network;
				
				pipe.updateConnections();
			}
		}

		return true;
	}

	public void removeFromNetwork() //only called server side
	{
		if(network!=null)
		{
			network.changeSize(-1);

			if(network.size!=0)
			{
				for(ForgeDirection dir : connections)
					if(dir!=null)
						if(isCopperPipe(dir))
						{
							TileCopperPipe pipe = (TileCopperPipe) worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
	
							pipe.network.changeSize(-pipe.network.size);
							
							pipe.network = new FluidNetwork(1);
							
							if(network!=null)
							{
								pipe.network.tank = network.tank;
								
								if(network.tank.getFluid()!=null)
									pipe.network.tank.setFluid(new FluidStack(network.tank.getFluid(), 
											network.tank.getFluidAmount() - FluidNetwork.capacityPerPipe));
								
								network = null;
							}
							pipe.isMaster = true;
							
							pipe.updateClient();
						}
			}
			else
				network = null;
		}
	}
	
	private boolean canConnect(ForgeDirection dir)
	{
		return isCopperPipe(dir) || isFluidHandler(dir);
	}
	
	private boolean isCopperPipe(ForgeDirection dir)
	{
		return worldObj.getBlock(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ) == InitBlocks.blockCopperPipe;
	}

	private boolean isFluidHandler(ForgeDirection dir)
	{
		return worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ) instanceof IFluidHandler;
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
	
	private void updateClient()
	{
		if(network!=null)
		{
			NBTTagCompound tag = new NBTTagCompound();
			network.tank.writeToNBT(tag);
			
			InitPackets.network.sendToAllAround(new CopperPipePacket(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, connections), 
					new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 100));
		}
	}

	public static class FluidNetwork
	{
		public static final short capacityPerPipe = (short) 200;
		
		private static final byte ticksTillUpdate = 4;

		private static final short maxExtractPerTile = 50*ticksTillUpdate;
		private static final short maxTransferPerTile = 100*ticksTillUpdate;

		public boolean updateNetworkForPipes = false;
		
		public FluidTank tank;
		public int size;
		public float fluidScaled;
		
		ArrayList<Coords> inputs = new ArrayList<Coords>();
		ArrayList<Coords> outputs = new ArrayList<Coords>();

		private byte ticksSinceLastUpdate = 0;

		public FluidNetwork(int size)
		{
			this.size = size;
			
			this.tank = new FluidTank(capacityPerPipe*size);
		}

		public void updateNetwork(TileCopperPipe pipe)
		{
			ticksSinceLastUpdate++;

			if(ticksSinceLastUpdate == 3)
				updateClient(pipe);
			
			if(ticksSinceLastUpdate > ticksTillUpdate)
			{				
				if(tank.getFluidAmount() == 0)
					tank.setFluid(null);
				
				ticksSinceLastUpdate = 0;

				updateInputs(pipe.worldObj);			
				updateOutputs(pipe);
			}
		}

		private void updateClient(TileCopperPipe pipe)
		{
			if(tank.getFluid()!=null)
			{
				fluidScaled = (float)(tank.getFluidAmount()/(float)size)/FluidNetwork.capacityPerPipe*(2*TileCopperPipeRenderer.pixel);
				
				if(fluidScaled > 2*TileCopperPipeRenderer.pixel)
					fluidScaled = 2*TileCopperPipeRenderer.pixel;
				
				InitPackets.network.sendToAllAround(new FluidNetworkPacket(pipe.worldObj.provider.dimensionId, pipe.xCoord, pipe.yCoord, pipe.zCoord, 
						fluidScaled, tank.getFluid().getFluid().getName()), 
						new TargetPoint(pipe.worldObj.provider.dimensionId, pipe.xCoord, pipe.yCoord, pipe.zCoord, 100));
			}
			else
			{
				InitPackets.network.sendToAllAround(new FluidNetworkPacket(pipe.worldObj.provider.dimensionId, pipe.xCoord, pipe.yCoord, pipe.zCoord, 
						0, "water"),
						new TargetPoint(pipe.worldObj.provider.dimensionId, pipe.xCoord, pipe.yCoord, pipe.zCoord, 100));
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

		private void updateOutputs(TileCopperPipe pipe)
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
						if(pipe.worldObj.getTileEntity(coords.x, coords.y, coords.z) instanceof IFluidHandler)
						{
							IFluidHandler tile = (IFluidHandler) pipe.worldObj.getTileEntity(coords.x, coords.y, coords.z);
	
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
					
					if(tempSize == outputs.size()/2)
						updateClient(pipe);
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
			
			tank.writeToNBT(temp);
			
			tag.setTag("network", temp);
		}
		
		public static FluidNetwork readFromNBT(NBTTagCompound tag)
		{
			NBTTagCompound temp = tag.getCompoundTag("network");
			
			FluidNetwork network = new FluidNetwork(1);
			
			network.updateNetworkForPipes = true;
			network.tank = new FluidTank(200*network.size);
			network.tank.readFromNBT(temp);
			
			return network;
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
