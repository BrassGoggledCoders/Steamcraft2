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
package xyz.brassgoggledcoders.steamcraft.common.tiles.energy;

import java.util.ArrayList;

import boilerplate.api.IOpenableGUI;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyConnection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyProvider;
import cofh.api.energy.IEnergyReceiver;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import xyz.brassgoggledcoders.steamcraft.api.tile.ISpannerTile;
import xyz.brassgoggledcoders.steamcraft.client.gui.GuiChangeExtractions;
import xyz.brassgoggledcoders.steamcraft.common.init.InitBlocks;
import xyz.brassgoggledcoders.steamcraft.common.init.InitPackets;
import xyz.brassgoggledcoders.steamcraft.common.packets.WirePacket;
import xyz.brassgoggledcoders.steamcraft.common.tiles.TileCopperPipe.Coords;
import xyz.brassgoggledcoders.steamcraft.common.tiles.container.ContainerChangeExtractions;

/**
 * @author decebaldecebal
 *
 */
public class TileCopperWire extends TileEntity implements IEnergyHandler, ISpannerTile, IOpenableGUI
{
	private static int copperWireCapacity = 5000;
	private static int copperWireTransfer = 1000;

	protected int wireCapacity = copperWireCapacity;
	protected int wireTransfer = copperWireTransfer;

	public EnergyNetwork network;
	private boolean isMaster = false;

	public ForgeDirection[] connections = new ForgeDirection[6];
	public ForgeDirection[] extractions = new ForgeDirection[6];
	private Coords masterCoords = null;

	@Override
	public void updateEntity()
	{
		if (!this.worldObj.isRemote)
		{
			if (this.isMaster)
			{
				if (this.network.updateNetworkForWires)
				{
					this.network.updateNetworkForWires = false;
					this.updateConnections();
				}

				this.network.updateNetwork(this);
			}
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		NBTTagList extractions = new NBTTagList();

		for (int i = 0; i < 6; i++)
			if (this.extractions[i] != null)
			{
				NBTTagCompound conn = new NBTTagCompound();
				conn.setByte("index", (byte) i);
				writeDirectionToNBT(conn, this.extractions[i]);

				extractions.appendTag(conn);
			}

		tag.setTag("extractions", extractions);
		tag.setBoolean("master", this.isMaster);

		if (this.isMaster)
			this.network.writeToNBT(tag);
	}

	private static void writeDirectionToNBT(NBTTagCompound tag, ForgeDirection dir)
	{
		byte index = -1;

		if (dir != null)
			switch (dir)
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

		this.extractions = new ForgeDirection[6];

		NBTTagList extractions = tag.getTagList("extractions", Constants.NBT.TAG_COMPOUND);

		for (int i = 0; i < extractions.tagCount(); i++)
		{
			NBTTagCompound dirTag = extractions.getCompoundTagAt(i);

			this.extractions[dirTag.getByte("index")] = readDirectionFromNBT(dirTag);
		}

		this.isMaster = tag.getBoolean("master");

		if (this.isMaster)
		{
			this.network = EnergyNetwork.readFromNBT(tag, this.wireCapacity, this.wireTransfer);
			this.setMaster(this);
		}
	}

	private static ForgeDirection readDirectionFromNBT(NBTTagCompound tag)
	{
		byte index = tag.getByte("dirIndex");

		ForgeDirection dir = ForgeDirection.getOrientation(index);

		if (dir == ForgeDirection.UNKNOWN)
			dir = null;

		return dir;
	}

	@Override
	public Packet getDescriptionPacket()
	{
		NBTTagCompound tag = new NBTTagCompound();

		NBTTagList extractions = new NBTTagList();

		for (int i = 0; i < 6; i++)
			if (this.extractions[i] != null)
			{
				NBTTagCompound conn = new NBTTagCompound();
				conn.setByte("index", (byte) i);
				writeDirectionToNBT(conn, this.extractions[i]);

				extractions.appendTag(conn);
			}

		NBTTagList connections = new NBTTagList();

		for (int i = 0; i < 6; i++)
			if (this.connections[i] != null)
			{
				NBTTagCompound conn = new NBTTagCompound();
				conn.setByte("index", (byte) i);
				writeDirectionToNBT(conn, this.connections[i]);

				connections.appendTag(conn);
			}

		tag.setTag("extractions", extractions);
		tag.setTag("connections", connections);

		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
	{
		this.extractions = new ForgeDirection[6];

		NBTTagList extractions = packet.func_148857_g().getTagList("extractions", Constants.NBT.TAG_COMPOUND);

		for (int i = 0; i < extractions.tagCount(); i++)
		{
			NBTTagCompound tag = extractions.getCompoundTagAt(i);

			this.extractions[tag.getByte("index")] = readDirectionFromNBT(tag);
		}

		this.connections = new ForgeDirection[6];

		NBTTagList connections = (NBTTagList) packet.func_148857_g().getTag("connections");

		for (int i = 0; i < connections.tagCount(); i++)
		{
			NBTTagCompound tag = connections.getCompoundTagAt(i);

			this.connections[tag.getByte("index")] = readDirectionFromNBT(tag);
		}
	}

	@Override
	public void changeExtraction()
	{
		if (!this.worldObj.isRemote)
		{
			for (int i = 0; i < 6; i++)
			{
				ForgeDirection dir = this.connections[i];

				if ((dir != null) && this.isEnergyHandler(dir))
				{
					Coords temp = new Coords(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY,
						this.zCoord + dir.offsetZ, dir.getOpposite());

					if (this.extractions[i] == null)
					{
						this.extractions[i] = dir;

						this.network.outputs.remove(temp);

						if (!this.network.inputs.contains(temp))
							this.network.inputs.add(temp);

						break;
					}
					else
					{
						this.extractions[i] = null;

						this.network.inputs.remove(temp);

						if (!this.network.outputs.contains(temp))
							this.network.outputs.add(temp);
					}
				}
			}
			this.updateClientConnections();
		}
	}
	@Override
	public void changeExtraction(int dirIndex)
	{
		if (!this.worldObj.isRemote)
		{
			ForgeDirection dir = this.connections[dirIndex];

			if (dir != null)
			{
				Coords temp = new Coords(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY,
					this.zCoord + dir.offsetZ, dir.getOpposite());

				if (this.extractions[dirIndex] == null)
				{
					this.extractions[dirIndex] = this.connections[dirIndex];

					this.network.outputs.remove(temp);

					if (!this.network.inputs.contains(temp))
						this.network.inputs.add(temp);
				}
				else
				{
					this.extractions[dirIndex] = null;

					this.network.inputs.remove(temp);

					if (!this.network.outputs.contains(temp))
						this.network.outputs.add(temp);
				}

				this.updateClientConnections();
			}
		}
	}

	@Override
	public ForgeDirection[] getExtractableConnections()
	{
		ForgeDirection[] extractableConnections = new ForgeDirection[6];

		for(int i = 0;i < 6;i++)
		{
			ForgeDirection dir = this.connections[i];
			if (dir != null && this.isEnergyHandler(dir))
				extractableConnections[i] = dir;
		}

		return extractableConnections;
	}

	@Override
	public ForgeDirection[] getExtractions()
	{
		return this.extractions;
	}

	private void removeConnections(int i)
	{
		if (this.connections[i] != null && !this.worldObj.isRemote)
		{
			ForgeDirection dir = this.connections[i];

			Coords temp = new Coords(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ, dir.getOpposite());

			this.network.outputs.remove(temp);
			this.network.inputs.remove(temp);

			this.extractions[i] = null;
			this.connections[i] = null;
		}
	}

	public void updateConnections() // Only sever side
	{
		if (!this.worldObj.isRemote)
		{
			if (this.canConnect(ForgeDirection.DOWN))
			{
				if (!this.updateNetwork(ForgeDirection.DOWN))
					return;
				this.connections[0] = ForgeDirection.DOWN;
			}
			else
				this.removeConnections(0);

			if (this.canConnect(ForgeDirection.UP))
			{
				if (!this.updateNetwork(ForgeDirection.UP))
					return;
				this.connections[1] = ForgeDirection.UP;
			}
			else
				this.removeConnections(1);

			if (this.canConnect(ForgeDirection.NORTH))
			{
				if (!this.updateNetwork(ForgeDirection.NORTH))
					return;
				this.connections[2] = ForgeDirection.NORTH;
			}
			else
				this.removeConnections(2);

			if (this.canConnect(ForgeDirection.SOUTH))
			{
				if (!this.updateNetwork(ForgeDirection.SOUTH))
					return;
				this.connections[3] = ForgeDirection.SOUTH;
			}
			else
				this.removeConnections(3);

			if (this.canConnect(ForgeDirection.WEST))
			{
				if (!this.updateNetwork(ForgeDirection.WEST))
					return;
				this.connections[4] = ForgeDirection.WEST;
			}
			else
				this.removeConnections(4);

			if (this.canConnect(ForgeDirection.EAST))
			{
				if (!this.updateNetwork(ForgeDirection.EAST))
					return;
				this.connections[5] = ForgeDirection.EAST;
			}
			else
				this.removeConnections(5);

			if (this.network == null)
			{
				this.network = new EnergyNetwork(1, this.wireCapacity, this.wireTransfer);
				this.setMaster(this);
			}

			for (int i = 0;i < 6;i++)
			{
				ForgeDirection dir = this.connections[i];
				if (dir != null && this.isEnergyHandler(dir))
				{
					Coords temp = new Coords(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ, dir.getOpposite());

					if (this.extractions[i] == null && (this.worldObj.getTileEntity(temp.x, temp.y, temp.z) instanceof IEnergyReceiver))
					{
						if (!this.network.outputs.contains(temp))
							this.network.outputs.add(temp);
					}
					else if (!this.network.inputs.contains(temp) && (this.worldObj.getTileEntity(temp.x, temp.y, temp.z) instanceof IEnergyProvider))
						this.network.inputs.add(temp);
				}
			}

			this.updateClientConnections();
		}
	}

	private void updateClientConnections()
	{
		if (this.network != null && !this.worldObj.isRemote)
		{
			InitPackets.network.sendToAllAround(new WirePacket(this.xCoord, this.yCoord, this.zCoord, this.connections, this.extractions),
					new TargetPoint(this.worldObj.provider.dimensionId, this.xCoord, this.yCoord, this.zCoord, 100));
		}
	}

	private boolean updateNetwork(ForgeDirection dir)
	{
		TileEntity tile = this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);

		if (tile instanceof TileCopperWire)
		{
			TileCopperWire wire = (TileCopperWire) tile;

			if (wire.network != null) // Is null only on world load
			{
				if (!wire.network.equals(this.network))
				{
					if (this.network == null)
					{
						this.setMaster(wire.getMaster());
						this.network.changeSize(1);

						wire.updateOneConnection(dir.getOpposite());
					}
					else
					{
						int energy = this.network.buffer.getEnergyStored() + wire.network.buffer.getEnergyStored();

						if (this.network.size > wire.network.size)
						{
							wire.setMaster(this.getMaster());

							this.network.buffer.setEnergyStored(energy);
							this.network.changeSize(1);

							wire.updateConnections();
						}
						else
						{
							this.setMaster(wire.getMaster());

							this.network.buffer.setEnergyStored(energy);
							this.network.changeSize(1);

							wire.updateOneConnection(dir.getOpposite());
							this.updateConnections();
							return false;
						}
					}
				}
				else
				{
					wire.updateOneConnection(dir.getOpposite());
				}
			}
			else if (this.network != null)
			{
				wire.setMaster(this.getMaster());
				this.network.changeSize(1);
				wire.updateConnections();
			}
		}
		return true;
	}

	private void updateOneConnection(ForgeDirection dir)
	{
		int index = 0;

		switch (dir)
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

		this.updateClientConnections();
	}

	public void removeFromNetwork() // only called server side
	{
		if (this.network != null)
		{
			this.network.changeSize(-1);

			if (this.network.size != 0)
			{
				int energy = this.network.buffer.getEnergyStored();

				for (ForgeDirection dir : this.connections)
					if (dir != null)
						if (this.isWire(dir))
						{
							TileCopperWire wire = (TileCopperWire) this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY,
									this.zCoord + dir.offsetZ);

							wire.network.setSize(0);

							wire.network = new EnergyNetwork(1, this.wireCapacity, this.wireTransfer);
							wire.setMaster(wire);
							if (this.network != null)
							{
								wire.network.tempEnergy = energy;
								this.network = null;
							}

						}
			}
			this.setMaster(null);
		}
	}

	public void setMaster(TileCopperWire master)
	{
		if (master != null)
		{
			this.masterCoords = new Coords(master.xCoord, master.yCoord, master.zCoord, null);
			this.network = master.network;
			if (master == this)
				this.isMaster = true;
			else
				this.isMaster = false;
		}
		else
		{
			this.masterCoords = null;
			this.isMaster = false;
			this.network = null;
		}
	}

	public TileCopperWire getMaster()
	{
		if (this.masterCoords != null)
			return (TileCopperWire) this.worldObj.getTileEntity(this.masterCoords.x, this.masterCoords.y, this.masterCoords.z);
		return null;
	}

	private boolean canConnect(ForgeDirection dir)
	{
		return this.isEnergyHandler(dir) || this.isWire(dir);
	}

	protected boolean isWire(ForgeDirection dir)
	{
		return this.worldObj.getBlock(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ) == InitBlocks.blockCopperWire;
	}

	protected boolean isEnergyHandler(ForgeDirection dir)
	{
		return (this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY,
				this.zCoord + dir.offsetZ) instanceof IEnergyConnection)
				&& ((IEnergyConnection) this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ))
						.canConnectEnergy(dir.getOpposite())
				&& !this.isWire(dir) && !this.isSteelWire(dir);
	}

	protected boolean isSteelWire(ForgeDirection dir)
	{
		return this.worldObj.getBlock(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ) == InitBlocks.blockSteelWire;
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		for (ForgeDirection dir : this.extractions)
			if (dir == from)
				return 0;

		if ((this.network != null)) // should actively receive energy from  where it is not actively pulling
		{
			int amount = Math.min(maxReceive, this.wireTransfer);

			return this.network.buffer.receiveEnergy(amount, simulate);
		}

		return 0;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		for (ForgeDirection dir : this.extractions)
			if (dir == from)
				return 0;

		if ((this.network != null))
		{
			int amount = Math.min(maxExtract, this.wireTransfer);

			return this.network.buffer.extractEnergy(amount, simulate);
		}

		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		if (this.network != null)
			return this.network.buffer.getEnergyStored();

		return 0;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		if (this.network != null)
			return this.network.buffer.getMaxEnergyStored();

		return 0;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiChangeExtractions(world.getTileEntity(x, y, z), player.dimension);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerChangeExtractions();
	}

	private static class EnergyNetwork
	{
		private int capacityPerWire;
		private int maxTransferPerTile;

		private boolean updateNetworkForWires = false;

		private EnergyStorage buffer;
		private int size;
		private int tempEnergy = 0;

		private ArrayList<Coords> inputs = new ArrayList<Coords>();
		private ArrayList<Coords> outputs = new ArrayList<Coords>();

		private EnergyNetwork(int size, int wireCapacity, int wireTransfer)
		{
			this.size = size;

			this.capacityPerWire = wireCapacity;
			this.maxTransferPerTile = wireTransfer;
			this.buffer = new EnergyStorage(wireCapacity * size, this.maxTransferPerTile);
		}

		private void updateNetwork(TileCopperWire wire)
		{
			if (this.tempEnergy > 0)
			{
				this.buffer.modifyEnergyStored(this.tempEnergy);
				this.tempEnergy = 0;
			}

			this.updateInputs(wire.worldObj);
			this.updateOutputs(wire);
		}

		private void updateInputs(World world)
		{
			int distribute = 0;
			int tempSize = this.inputs.size();

			for (Coords coords : this.inputs)
			{
				if (tempSize != 0)
					distribute = (this.buffer.getMaxEnergyStored() - this.buffer.getEnergyStored()) / tempSize;
				else
					break;

				if (coords != null)
					if (this.buffer.getEnergyStored() != this.buffer.getMaxEnergyStored())
					{
						if (world.getTileEntity(coords.x, coords.y, coords.z) instanceof IEnergyProvider)
						{
							IEnergyProvider tile = (IEnergyProvider) world.getTileEntity(coords.x, coords.y, coords.z);

							if (tile != null)
							{
								this.buffer.receiveEnergy(tile.extractEnergy(coords.dir, Math.min(distribute, this.maxTransferPerTile), false),
										false);

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

			for (Coords coords : this.outputs)
			{
				if (tempSize != 0)
					distribute = this.buffer.getEnergyStored() / tempSize;
				else
					break;

				if (coords != null)
					if (this.buffer.getEnergyStored() > 0)
					{
						if (wire.worldObj.getTileEntity(coords.x, coords.y, coords.z) instanceof IEnergyReceiver)
						{
							IEnergyReceiver tile = (IEnergyReceiver) wire.worldObj.getTileEntity(coords.x, coords.y, coords.z);

							if (tile != null)
							{
								this.buffer.extractEnergy(tile.receiveEnergy(coords.dir, Math.min(distribute, this.maxTransferPerTile), false),
										false);

								tempSize--;
							}
						}
					}
					else
						break;
			}
		}

		public void setSize(int size)
		{
			this.size = size;
			this.buffer.setCapacity(this.capacityPerWire * this.size);
		}

		public void changeSize(int with)
		{
			this.size += with;
			this.buffer.setCapacity(this.capacityPerWire * this.size);
		}

		public void writeToNBT(NBTTagCompound tag)
		{
			NBTTagCompound temp = new NBTTagCompound();

			temp.setInteger("energyLevel", this.buffer.getEnergyStored());

			tag.setTag("network", temp);
		}

		private static EnergyNetwork readFromNBT(NBTTagCompound tag, int wireCapacity, int wireTransfer)
		{
			NBTTagCompound temp = tag.getCompoundTag("network");

			EnergyNetwork network = new EnergyNetwork(1, wireCapacity, wireTransfer);

			network.updateNetworkForWires = true;
			network.tempEnergy = temp.getInteger("energyLevel");

			return network;
		}
	}
}
