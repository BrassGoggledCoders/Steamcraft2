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
import steamcraft.client.renderers.tile.TileCopperWireRenderer;
import steamcraft.common.InitBlocks;
import steamcraft.common.InitPackets;
import steamcraft.common.packets.CopperWirePacket;
import steamcraft.common.packets.EnergyNetworkPacket;
import steamcraft.common.tiles.TileCopperPipe.Coords;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;

/**
 * @author decebaldecebal
 * 
 */
public class TileCopperWire extends TileEntity implements IEnergyHandler
{
	public ForgeDirection extract = null;
	public ForgeDirection[] connections = new ForgeDirection[6];

	public EnergyNetwork network;

	public boolean isMaster = false;

	@Override
	public void updateEntity()
	{
		if(this.isMaster)
		{
			if(this.network.updateNetworkForWires)
			{
				this.network.updateNetworkForWires = false;
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
			this.network = EnergyNetwork.readFromNBT(tag);
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
			this.network = new EnergyNetwork(1);
			this.network.updateNetworkForWires = true;
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
				if(dir != null && this.isEnergyHandler(dir))
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
			this.updateNetwork(ForgeDirection.DOWN);
			this.connections[0] = ForgeDirection.DOWN;
		}
		else
			this.removeConnections(0);

		if(this.canConnect(ForgeDirection.UP))
		{
			this.updateNetwork(ForgeDirection.UP);
			this.connections[1] = ForgeDirection.UP;
		}
		else
			this.removeConnections(1);

		if(this.canConnect(ForgeDirection.NORTH))
		{
			this.updateNetwork(ForgeDirection.NORTH);
			this.connections[2] = ForgeDirection.NORTH;
		}
		else
			this.removeConnections(2);

		if(this.canConnect(ForgeDirection.SOUTH))
		{
			this.updateNetwork(ForgeDirection.SOUTH);
			this.connections[3] = ForgeDirection.SOUTH;
		}
		else
			this.removeConnections(3);

		if(this.canConnect(ForgeDirection.WEST))
		{
			this.updateNetwork(ForgeDirection.WEST);
			this.connections[4] = ForgeDirection.WEST;
		}
		else
			this.removeConnections(4);

		if(this.canConnect(ForgeDirection.EAST))
		{
			this.updateNetwork(ForgeDirection.EAST);
			this.connections[5] = ForgeDirection.EAST;
		}
		else
			this.removeConnections(5);

		if(this.network == null)
		{
			this.network = new EnergyNetwork(1);
			this.isMaster = true;
		}

		if(!this.worldObj.isRemote)
		{
			for(ForgeDirection dir : this.connections)
				if(dir != null && this.isEnergyHandler(dir))
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

			this.updateClient();
		}
	}

	public void updateNetwork(ForgeDirection dir)
	{
		TileEntity tile = this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);

		if(tile instanceof TileCopperWire)
		{
			TileCopperWire wire = (TileCopperWire) tile;

			if(wire.network != null)// Should only happen on world load
			{
				if(!wire.network.equals(this.network))
				{
					if(this.network == null)
					{
						this.network = wire.network;
						this.network.changeSize(1);

						wire.updateConnections();
					}
					else
					{
						int energy = this.network.buffer.getEnergyStored() + wire.network.buffer.getEnergyStored();

						if(this.network.size > wire.network.size)
						{
							wire.isMaster = false;
							wire.network = this.network;
							wire.network.buffer.setEnergyStored(energy);

							this.network.changeSize(1);
						}
						else
						{
							this.isMaster = false;
							this.network = wire.network;
							wire.network.buffer.setEnergyStored(energy);

							this.network.changeSize(1);

							this.updateConnections();
						}

						wire.updateConnections();
					}
				}
				else
					wire.updateOneConnection(dir.getOpposite());
			}
			else if(this.network != null)
			{
				wire.isMaster = false;
				wire.network = this.network;

				wire.updateConnections();
			}
		}
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
						if(this.isCopperWire(dir))
						{
							TileCopperWire wire = (TileCopperWire) this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY,
									this.zCoord + dir.offsetZ);

							wire.network.changeSize(-wire.network.size);

							wire.network = new EnergyNetwork(1);

							if(this.network != null)
							{
								wire.network.buffer = this.network.buffer;
								wire.network.buffer.setEnergyStored(this.network.buffer.getEnergyStored() - EnergyNetwork.capacityPerWire);

								this.network = null;
							}
							wire.isMaster = true;

							wire.updateClient();
						}
			}
			else
				this.network = null;
		}
	}

	private boolean canConnect(ForgeDirection dir)
	{
		return this.isCopperWire(dir) || this.isEnergyHandler(dir);
	}

	private boolean isCopperWire(ForgeDirection dir)
	{
		return this.worldObj.getBlock(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ) == InitBlocks.blockCopperWire;
	}

	public boolean isEnergyHandler(ForgeDirection dir)
	{
		return this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ) instanceof IEnergyHandler
				&& !this.isCopperWire(dir);
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
		if(this.network != null && !MinecraftServer.getServer().isDedicatedServer())
		{
			InitPackets.network.sendToAllAround(new CopperWirePacket(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord,
					this.connections), new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 100));
		}
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		if(from == this.extract && network != null)
		{
			int amount = Math.min(maxReceive, EnergyNetwork.maxTransferPerTile / EnergyNetwork.ticksTillUpdate);

			return this.network.buffer.receiveEnergy(amount, simulate);
		}

		return 0;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		if(from != this.extract && network != null)
		{
			int amount = Math.min(maxExtract, EnergyNetwork.maxTransferPerTile / EnergyNetwork.ticksTillUpdate);

			return this.network.buffer.extractEnergy(amount, simulate);
		}

		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		if(network != null)
			return this.network.buffer.getEnergyStored();
		
		return 0;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		if(network != null)
			return this.network.buffer.getMaxEnergyStored();
		
		return 0;
	}

	public static class EnergyNetwork
	{
		public static final short capacityPerWire = (short) 2500;

		private static final byte ticksTillUpdate = 1;

		private static final short maxTransferPerTile = 2500 * ticksTillUpdate; // can transmit a max of 2500RF/t

		public boolean updateNetworkForWires = false;

		public EnergyStorage buffer;
		public int size;
		public float energyScaled;

		ArrayList<Coords> inputs = new ArrayList<Coords>();
		ArrayList<Coords> outputs = new ArrayList<Coords>();

		private byte ticksSinceLastUpdate = 0;

		public EnergyNetwork(int size)
		{
			this.size = size;

			this.buffer = new EnergyStorage(capacityPerWire * size, 2500);
		}

		public void updateNetwork(TileCopperWire wire)
		{
			this.ticksSinceLastUpdate++;

			if(this.ticksSinceLastUpdate == ticksTillUpdate / 2)
				this.updateClient(wire);

			if(this.ticksSinceLastUpdate == ticksTillUpdate)
			{
				this.ticksSinceLastUpdate = 0;

				this.updateInputs(wire.worldObj);
				this.updateOutputs(wire);
			}
		}

		private void updateClient(TileCopperWire wire)
		{
			if(!MinecraftServer.getServer().isDedicatedServer())
			{
				this.energyScaled = this.buffer.getEnergyStored() / (float) this.size / EnergyNetwork.capacityPerWire
						* (2 * TileCopperWireRenderer.pixel);

				if(this.energyScaled > 2 * TileCopperWireRenderer.pixel)
					this.energyScaled = 2 * TileCopperWireRenderer.pixel;

				InitPackets.network.sendToAllAround(new EnergyNetworkPacket(wire.worldObj.provider.dimensionId, wire.xCoord, wire.yCoord,
						wire.zCoord, this.energyScaled), new TargetPoint(wire.worldObj.provider.dimensionId, wire.xCoord, wire.yCoord, wire.zCoord,
						100));
			}
		}

		private void updateInputs(World world)
		{
			int distribute = 0;
			int tempSize = this.inputs.size();

			for(Coords coords : this.inputs)
			{
				if(tempSize != 0)
					distribute = (this.buffer.getMaxEnergyStored() - this.buffer.getEnergyStored()) / tempSize;
				else
					break;

				if(coords != null)
					if(this.buffer.getEnergyStored() != this.buffer.getMaxEnergyStored())
					{
						if(world.getTileEntity(coords.x, coords.y, coords.z) instanceof IEnergyHandler)
						{
							IEnergyHandler tile = (IEnergyHandler) world.getTileEntity(coords.x, coords.y, coords.z);
							
							if(tile != null)
							{
								int canFill = tile.extractEnergy(coords.dir, Math.min(distribute, maxTransferPerTile), true);

								this.buffer.receiveEnergy(tile.extractEnergy(coords.dir, canFill, false), false);

								tempSize--;
							}
						}
					}
					else
						break;
			}
		}

		private void updateOutputs(TileCopperWire wire)
		{
			int distribute = 0;
			int tempSize = this.outputs.size();

			for(Coords coords : this.outputs)
			{
				if(tempSize != 0)
					distribute = this.buffer.getEnergyStored() / tempSize;
				else
					break;

				if(coords != null)
					if(this.buffer.getEnergyStored() > 0)
					{
						if(wire.worldObj.getTileEntity(coords.x, coords.y, coords.z) instanceof IEnergyHandler)
						{
							IEnergyHandler tile = (IEnergyHandler) wire.worldObj.getTileEntity(coords.x, coords.y, coords.z);

							if(tile != null)
							{
								short transfered = (short) tile.receiveEnergy(coords.dir, Math.min(distribute, maxTransferPerTile), false);

								this.buffer.extractEnergy(transfered, false);

								tempSize--;
							}
						}
					}
					else
						break;

				if(tempSize == this.outputs.size() / 2)
					this.updateClient(wire);
			}
		}

		public void changeSize(int with)
		{
			this.size += with;
			this.buffer.setCapacity(capacityPerWire * this.size);
		}

		public void writeToNBT(NBTTagCompound tag)
		{
			NBTTagCompound temp = new NBTTagCompound();

			this.buffer.writeToNBT(temp);

			tag.setTag("network", temp);
		}

		public static EnergyNetwork readFromNBT(NBTTagCompound tag)
		{
			NBTTagCompound temp = tag.getCompoundTag("network");

			EnergyNetwork network = new EnergyNetwork(1);

			network.updateNetworkForWires = true;
			network.buffer = new EnergyStorage(capacityPerWire * network.size);
			network.buffer.readFromNBT(temp);

			return network;
		}
	}
}
