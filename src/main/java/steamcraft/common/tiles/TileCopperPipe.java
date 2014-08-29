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
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
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
 * @author decebaldecebal
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
		if(this.isMaster)
		{
			if(this.network.updateNetworkForPipes)
			{
				this.network.updateNetworkForPipes = false;
				this.updateConnections();
			}
			this.network.updateNetwork(this);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		writeDirectionToNBT(tag, this.extract);

		tag.setBoolean("master", this.isMaster);

		if(this.isMaster)
			this.network.writeToNBT(tag);
	}

	private static void writeDirectionToNBT(NBTTagCompound tag, ForgeDirection dir)
	{
		byte index = -1;;

		if(dir != null)
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

		this.extract = readDirectionFromNBT(tag);

		this.isMaster = tag.getBoolean("master");

		if(this.isMaster)
			this.network = FluidNetwork.readFromNBT(tag);
	}

	private static ForgeDirection readDirectionFromNBT(NBTTagCompound tag)
	{
		byte index = tag.getByte("dirIndex");

		ForgeDirection dir = ForgeDirection.getOrientation(index);

		if(dir == ForgeDirection.UNKNOWN)
			dir = null;

		return dir;
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();

		writeDirectionToNBT(tag, this.extract);
		tag.setBoolean("master", this.isMaster);

		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
	{
		this.extract = readDirectionFromNBT(packet.func_148857_g());
		this.isMaster = packet.func_148857_g().getBoolean("master");

		if(this.isMaster)
		{
			this.network = new FluidNetwork(1);
			this.network.updateNetworkForPipes = true;
		}
	}

	public void changeExtracting()
	{
		if(this.extract != null)
		{
			if(!this.worldObj.isRemote)
				this.network.inputs.remove(new Coords(this.xCoord + this.extract.offsetX, this.yCoord + this.extract.offsetY, this.zCoord
						+ this.extract.offsetZ, this.extract.getOpposite()));

			this.extract = null;
		}
		else
			for(ForgeDirection dir : this.connections)
				if(dir != null && this.isFluidHandler(dir))
				{
					this.extract = dir;

					if(!this.worldObj.isRemote)
					{
						Coords temp = new Coords(this.xCoord + this.extract.offsetX, this.yCoord + this.extract.offsetY, this.zCoord
								+ this.extract.offsetZ, this.extract.getOpposite());

						this.network.outputs.remove(temp);
						this.network.inputs.add(temp);
					}

					break;
				}
	}

	private void removeConnections(int i)
	{
		if(this.connections[i] != null && !this.worldObj.isRemote)
		{
			ForgeDirection dir = this.connections[i];

			Coords temp = new Coords(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ, dir.getOpposite());

			this.network.outputs.remove(temp);
			
			if(this.connections[i] == extract)
				this.network.inputs.remove(temp);
		}
		
		if(extract == connections[i])
			this.extract = null;

		this.connections[i] = null;
	}

	public void updateConnections()
	{
		if(this.canConnect(ForgeDirection.DOWN))
		{
			if(this.updateNetwork(ForgeDirection.DOWN))
				this.connections[0] = ForgeDirection.DOWN;
		}
		else
			this.removeConnections(0);

		if(this.canConnect(ForgeDirection.UP))
		{
			if(this.updateNetwork(ForgeDirection.UP))
				this.connections[1] = ForgeDirection.UP;
		}
		else
			this.removeConnections(1);

		if(this.canConnect(ForgeDirection.NORTH))
		{
			if(this.updateNetwork(ForgeDirection.NORTH))
				this.connections[2] = ForgeDirection.NORTH;
		}
		else
			this.removeConnections(2);

		if(this.canConnect(ForgeDirection.SOUTH))
		{
			if(this.updateNetwork(ForgeDirection.SOUTH))
				this.connections[3] = ForgeDirection.SOUTH;
		}
		else
			this.removeConnections(3);

		if(this.canConnect(ForgeDirection.WEST))
		{
			if(this.updateNetwork(ForgeDirection.WEST))
				this.connections[4] = ForgeDirection.WEST;
		}
		else
			this.removeConnections(4);

		if(this.canConnect(ForgeDirection.EAST))
		{
			if(this.updateNetwork(ForgeDirection.EAST))
				this.connections[5] = ForgeDirection.EAST;
		}
		else
			this.removeConnections(5);

		if(this.network == null)
		{
			this.network = new FluidNetwork(1);
			this.isMaster = true;
		}

		if(!this.worldObj.isRemote)
		{
			for(ForgeDirection dir : this.connections)
			{
				if(dir != null && this.isFluidHandler(dir))
				{
					Coords temp = new Coords(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ, dir.getOpposite());

					if(this.extract != dir)
					{
						if(!this.network.outputs.contains(temp))
							this.network.outputs.add(temp);
					}
					else if(!this.network.inputs.contains(temp))
						this.network.inputs.add(temp);
				}
			}

			this.updateClient();
		}
	}

	public boolean updateNetwork(ForgeDirection dir)
	{
		TileEntity tile = this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);

		if(tile instanceof TileCopperPipe)
		{
			TileCopperPipe pipe = (TileCopperPipe) tile;

			if(pipe.network != null)// Should only happen on world load
			{
				if(!pipe.network.equals(this.network))
				{
					if(this.network == null)
					{
						this.network = pipe.network;
						this.network.changeSize(1);

						pipe.updateConnections();
					}
					else
					{
						if(this.network.tank.getFluid() == null || pipe.network.tank.getFluid() == null)
						{
							FluidNetwork temp = this.network;

							pipe.network = this.network = pipe.network.tank.getFluid() != null ? pipe.network : this.network;

							this.network.changeSize(1);

							if(!temp.equals(this.network))
							{
								this.isMaster = false;
								this.updateConnections();
							}
							else
								pipe.isMaster = false;

							pipe.updateConnections();

						}
						else if(this.network.tank.getFluid().getFluid().equals(pipe.network.tank.getFluid().getFluid()))
						{
							FluidStack fluid = new FluidStack(this.network.tank.getFluid(), this.network.tank.getFluidAmount()
									+ pipe.network.tank.getFluidAmount());

							if(this.network.size > pipe.network.size)
							{
								pipe.isMaster = false;
								pipe.network = this.network;
								pipe.network.tank.setFluid(fluid);

								this.network.changeSize(1);
							}
							else
							{
								this.isMaster = false;
								this.network = pipe.network;
								this.network.tank.setFluid(fluid);

								this.network.changeSize(1);

								this.updateConnections();
							}

							pipe.updateConnections();
						}
						else
							return false;
					}
				}
				else
					pipe.updateOneConnection(dir.getOpposite()); // happens if you have multiple pipes next to each other
			}
			else if(this.network != null)
			{
				pipe.isMaster = false;
				pipe.network = this.network;

				pipe.updateConnections();
			}
		}

		return true;
	}

	public void updateOneConnection(ForgeDirection dir)
	{
		int index = 0;

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

		this.connections[index] = dir;
	}

	public void removeFromNetwork() // only called server side
	{
		if(this.network != null)
		{
			this.network.changeSize(-1);

			if(this.network.size != 0)
			{
				for(ForgeDirection dir : this.connections)
					if(dir != null)
						if(this.isCopperPipe(dir))
						{
							TileCopperPipe pipe = (TileCopperPipe) this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY,
									this.zCoord + dir.offsetZ);

							pipe.network.changeSize(-pipe.network.size);

							pipe.network = new FluidNetwork(1);

							if(this.network != null)
							{
								pipe.network.tank = this.network.tank;

								if(this.network.tank.getFluid() != null)
									pipe.network.tank.setFluid(new FluidStack(this.network.tank.getFluid(), this.network.tank.getFluidAmount()
											- FluidNetwork.capacityPerPipe));

								this.network = null;
							}
							pipe.isMaster = true;

							pipe.updateClient();
						}
			}
			else
				this.network = null;
		}
	}

	private boolean canConnect(ForgeDirection dir)
	{
		return this.isCopperPipe(dir) || this.isFluidHandler(dir);
	}

	private boolean isCopperPipe(ForgeDirection dir)
	{
		return this.worldObj.getBlock(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ) == InitBlocks.blockCopperPipe;
	}

	public boolean isFluidHandler(ForgeDirection dir)
	{
		return this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ) instanceof IFluidHandler 
				&& !isCopperPipe(dir);
	}

	public ForgeDirection onlyOneOpposite()
	{
		ForgeDirection main = null;
		boolean isOpposite = false;

		for(ForgeDirection dir : this.connections)
		{
			if(main == null && dir != null)
				main = dir;

			if(dir != null && main != dir)
				if(!this.areDirectionsOpposite(main, dir) && !this.areDirectionsOpposite(dir, main))
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
		if(this.network != null && this.worldObj.isRemote)
		{
			NBTTagCompound tag = new NBTTagCompound();
			this.network.tank.writeToNBT(tag);

			InitPackets.network.sendToAllAround(new CopperPipePacket(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord,
					this.connections), new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 100));
		}
	}


	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return from == extract && (this.network.tank.getFluid()==null || this.network.tank.getFluid().getFluid() == fluid);
	}

	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return from != extract && (this.network.tank.getFluid()==null || this.network.tank.getFluid().getFluid() == fluid);
	}

	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		if(from != extract && network.tank.getFluid()!=null && network.tank.getFluid().isFluidEqual(resource)) 
		{
			int amount = Math.min(resource.amount, FluidNetwork.maxTransferPerTile/FluidNetwork.ticksTillUpdate);
			
			return network.tank.drain(amount, doDrain);
		}
		
		return null;
	}

	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		if(from != extract && network.tank.getFluid()!=null)
		{
			int amount = Math.min(maxDrain, FluidNetwork.maxTransferPerTile/FluidNetwork.ticksTillUpdate);
			
			return network.tank.drain(amount, doDrain);
		}
		
		return null;
	}

	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		if(extract == from)
		{
			int amount = Math.min(resource.amount, FluidNetwork.maxExtractPerTile/FluidNetwork.ticksTillUpdate);
			
			return network.tank.fill(new FluidStack(resource, amount), doFill);
		}
		
		return 0;
	}

	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		if(network != null)
			return new FluidTankInfo[] { network.tank.getInfo() };
		
		return null;
	}
	
	public static class FluidNetwork
	{
		public static final short capacityPerPipe = (short) 200;

		private static final byte ticksTillUpdate = 4;

		private static final short maxExtractPerTile = 50 * ticksTillUpdate;
		private static final short maxTransferPerTile = 100 * ticksTillUpdate;

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

			this.tank = new FluidTank(capacityPerPipe * size);
		}

		public void updateNetwork(TileCopperPipe pipe)
		{
			this.ticksSinceLastUpdate++;

			if(this.ticksSinceLastUpdate == ticksTillUpdate / 2)
				this.updateClient(pipe);

			if(this.ticksSinceLastUpdate == ticksTillUpdate)
			{
				if(this.tank.getFluidAmount() == 0)
					this.tank.setFluid(null);

				this.ticksSinceLastUpdate = 0;

				this.updateInputs(pipe.worldObj);
				this.updateOutputs(pipe);
			}
		}

		private void updateClient(TileCopperPipe pipe)
		{
			if(!MinecraftServer.getServer().isDedicatedServer())
			{
				if(this.tank.getFluid() != null)
				{
					this.fluidScaled = this.tank.getFluidAmount() / (float) this.size / FluidNetwork.capacityPerPipe
							* (2 * TileCopperPipeRenderer.pixel);

					if(this.fluidScaled > 2 * TileCopperPipeRenderer.pixel)
						this.fluidScaled = 2 * TileCopperPipeRenderer.pixel;

					InitPackets.network.sendToAllAround(new FluidNetworkPacket(pipe.worldObj.provider.dimensionId, pipe.xCoord, pipe.yCoord,
							pipe.zCoord, this.fluidScaled, this.tank.getFluid().getFluid().getName()), new TargetPoint(
							pipe.worldObj.provider.dimensionId, pipe.xCoord, pipe.yCoord, pipe.zCoord, 100));
				}
				else
				{
					InitPackets.network
							.sendToAllAround(new FluidNetworkPacket(pipe.worldObj.provider.dimensionId, pipe.xCoord, pipe.yCoord, pipe.zCoord, 0,
									"water"), new TargetPoint(pipe.worldObj.provider.dimensionId, pipe.xCoord, pipe.yCoord, pipe.zCoord, 100));
				}
			}
		}

		private void updateInputs(World world)
		{
			int distribute = 0;
			int tempSize = this.inputs.size();

			for(Coords coords : this.inputs)
			{
				if(tempSize != 0)
					distribute = (this.tank.getCapacity() - this.tank.getFluidAmount()) / tempSize;
				else
					break;

				if(coords != null)
				{
					if(this.tank.getFluidAmount() != this.tank.getCapacity())
					{
						if(world.getTileEntity(coords.x, coords.y, coords.z) instanceof IFluidHandler)
						{
							IFluidHandler tile = (IFluidHandler) world.getTileEntity(coords.x, coords.y, coords.z);
							if(tile != null && tile.getTankInfo(coords.dir)!=null)
							{
								for(FluidTankInfo info : tile.getTankInfo(coords.dir))
								{
									if(info.fluid != null && tile.canDrain(coords.dir, info.fluid.getFluid()))
									{
										int canFill = this.tank.fill(new FluidStack(info.fluid.getFluid(), Math.min(distribute, maxExtractPerTile)),
												false);

										this.tank.fill(tile.drain(coords.dir, canFill, true), true);

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
			int tempSize = this.outputs.size();

			for(Coords coords : this.outputs)
			{
				if(tempSize != 0)
					distribute = this.tank.getFluidAmount() / tempSize;
				else
					break;

				if(coords != null)
				{
					if(this.tank.getFluidAmount() > 0)
					{
						if(pipe.worldObj.getTileEntity(coords.x, coords.y, coords.z) instanceof IFluidHandler)
						{
							IFluidHandler tile = (IFluidHandler) pipe.worldObj.getTileEntity(coords.x, coords.y, coords.z);

							if(tile != null)
							{
								short transfered = 0;

								if(tile.canFill(coords.dir, this.tank.getFluid().getFluid()))
									transfered = (short) tile.fill(coords.dir,
											new FluidStack(this.tank.getFluid(), Math.min(distribute, maxTransferPerTile)), true);

								this.tank.drain(transfered, true);

								tempSize--;
							}
						}
					}
					else
						break;

					if(tempSize == this.outputs.size() / 2)
						this.updateClient(pipe);
				}
			}
		}

		public void changeSize(int with)
		{
			this.size += with;
			this.tank.setCapacity(200 * this.size);
		}

		public void writeToNBT(NBTTagCompound tag)
		{
			NBTTagCompound temp = new NBTTagCompound();

			this.tank.writeToNBT(temp);

			tag.setTag("network", temp);
		}

		public static FluidNetwork readFromNBT(NBTTagCompound tag)
		{
			NBTTagCompound temp = tag.getCompoundTag("network");

			FluidNetwork network = new FluidNetwork(1);

			network.updateNetworkForPipes = true;
			network.tank = new FluidTank(200 * network.size);
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
				if(this.x == coord.x && this.y == coord.y && this.z == coord.z && this.dir == coord.dir)
					return true;
			}
			return false;
		}

		public void writeToNBT(NBTTagCompound tag)
		{
			tag.setInteger("coordX", this.x);
			tag.setInteger("coordY", this.y);
			tag.setInteger("coordZ", this.z);

			writeDirectionToNBT(tag, this.dir);
		}

		public static Coords readFromNBT(NBTTagCompound tag)
		{
			return new Coords(tag.getInteger("coordX"), tag.getInteger("coordY"), tag.getInteger("coordZ"), readDirectionFromNBT(tag));
		}
	}
}