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
 * @author warlordjones
 *
 */
public class TileCopperWire extends TileEntity
{
	public ForgeDirection extract = null;
	public ForgeDirection[] connections = new ForgeDirection[6];

	public EnergyNetwork network;

	public boolean isMaster = false;

	@Override
	public void updateEntity()
	{
		if (isMaster)
			if (network.updateNetworkForWires)
			{
				network.updateNetworkForWires = false;
				updateConnections();
			}
			network.updateNetwork(this);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);

		writeDirectionToNBT(tag, extract);

		tag.setBoolean("master", isMaster);

		if (isMaster)
			network.writeToNBT(tag);
	}

	private static void writeDirectionToNBT(NBTTagCompound tag, ForgeDirection dir)
	{
		byte index = -1;
		;

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

		extract = readDirectionFromNBT(tag);

		isMaster = tag.getBoolean("master");

		if (isMaster)
			network = EnergyNetwork.readFromNBT(tag);
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

		writeDirectionToNBT(tag, extract);
		tag.setBoolean("master", isMaster);

		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
	{
		extract = readDirectionFromNBT(packet.func_148857_g());
		isMaster = packet.func_148857_g().getBoolean("master");

		if (isMaster)
		{
			network = new EnergyNetwork(1);
			network.updateNetworkForWires = true;
		}
	}

	public void changeExtracting()
	{
		if (extract != null)
		{
			if (!worldObj.isRemote)
				network.inputs.remove(new Coords(xCoord + extract.offsetX, yCoord + extract.offsetY, zCoord + extract.offsetZ, extract.getOpposite()));

			extract = null;
		}
		else
			for (ForgeDirection dir : connections)
				if ((dir != null) && isEnergyHandler(dir))
				{
					extract = dir;

					if (!worldObj.isRemote)
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
		if ((connections[i] != null) && !worldObj.isRemote)
		{
			ForgeDirection dir = connections[i];

			Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());

			network.outputs.remove(temp);
		}

		connections[i] = null;
	}

	public void updateConnections()
	{
		if (canConnect(ForgeDirection.DOWN))
		{
			updateNetwork(ForgeDirection.DOWN);
			connections[0] = ForgeDirection.DOWN;
		}
		else
			removeConnections(0);

		if (canConnect(ForgeDirection.UP))
		{
			updateNetwork(ForgeDirection.UP);
			connections[1] = ForgeDirection.UP;
		}
		else
			removeConnections(1);

		if (canConnect(ForgeDirection.NORTH))
		{
			updateNetwork(ForgeDirection.NORTH);
			connections[2] = ForgeDirection.NORTH;
		}
		else
			removeConnections(2);

		if (canConnect(ForgeDirection.SOUTH))
		{
			updateNetwork(ForgeDirection.SOUTH);
			connections[3] = ForgeDirection.SOUTH;
		}
		else
			removeConnections(3);

		if (canConnect(ForgeDirection.WEST))
		{
			updateNetwork(ForgeDirection.WEST);
			connections[4] = ForgeDirection.WEST;
		}
		else
			removeConnections(4);

		if (canConnect(ForgeDirection.EAST))
		{
			updateNetwork(ForgeDirection.EAST);
			connections[5] = ForgeDirection.EAST;
		}
		else
			removeConnections(5);

		if (network == null)
		{
			network = new EnergyNetwork(1);
			isMaster = true;
		}

		if (!worldObj.isRemote)
		{
			if ((extract != null) && !isEnergyHandler(extract))
			{
				network.inputs.remove(new Coords(xCoord + extract.offsetX, yCoord + extract.offsetY, zCoord + extract.offsetZ, extract.getOpposite()));

				extract = null;
			}

			for (ForgeDirection dir : connections)
				if ((dir != null) && isEnergyHandler(dir))
				{
					Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());

					if (extract != dir)
					{
						if (!network.outputs.contains(temp))
							network.outputs.add(temp);
					}
					else if (!network.inputs.contains(temp))
						network.inputs.add(temp);
				}

			updateClient();
		}
	}

	public void updateNetwork(ForgeDirection dir)
	{
		TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);

		if (tile instanceof TileCopperWire)
		{
			TileCopperWire wire = (TileCopperWire) tile;

			if (wire.network != null)// Should only happen on world load
			{
				if (!wire.network.equals(network))
				{
					if (network == null)
					{
						network = wire.network;
						network.changeSize(1);

						wire.updateConnections();
					}
					else
					{
						int energy = network.buffer.getEnergyStored() + wire.network.buffer.getEnergyStored();

						if (network.size > wire.network.size)
						{
							wire.isMaster = false;
							wire.network = network;
							wire.network.buffer.setEnergyStored(energy);

							network.changeSize(1);
						}
						else
						{
							isMaster = false;
							network = wire.network;
							wire.network.buffer.setEnergyStored(energy);

							network.changeSize(1);

							updateConnections();
						}

						wire.updateConnections();
					}
				}
				else
					wire.updateOneConnection(dir.getOpposite()); // happens if you have	wires next to each other
			}
			else if (network != null)
			{
				wire.isMaster = false;
				wire.network = network;

				wire.updateConnections();
			}
		}
	}

	public void updateOneConnection(ForgeDirection dir)
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

		connections[index] = dir;
	}

	public void removeFromNetwork() // only called server side
	{
		if (network != null)
		{
			network.changeSize(-1);

			if (network.size != 0)
			{
				for (ForgeDirection dir : connections)
					if (dir != null)
						if (isCopperWire(dir))
						{
							TileCopperWire wire = (TileCopperWire) worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord
									+ dir.offsetZ);

							wire.network.changeSize(-wire.network.size);

							wire.network = new EnergyNetwork(1);

							if (network != null)
							{
								wire.network.buffer = network.buffer;
								wire.network.buffer.setEnergyStored(network.buffer.getEnergyStored() - EnergyNetwork.capacityPerWire);

								network = null;
							}
							wire.isMaster = true;

							wire.updateClient();
						}
			}
			else
				network = null;
		}
	}

	private boolean canConnect(ForgeDirection dir)
	{
		return isCopperWire(dir) || isEnergyHandler(dir);
	}

	private boolean isCopperWire(ForgeDirection dir)
	{
		return worldObj.getBlock(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ) == InitBlocks.blockCopperWire;
	}

	private boolean isEnergyHandler(ForgeDirection dir)
	{
		return worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ) instanceof IEnergyHandler;
	}

	public ForgeDirection onlyOneOpposite()
	{
		ForgeDirection main = null;
		boolean isOpposite = false;

		for (ForgeDirection dir : connections)
		{
			if ((main == null) && (dir != null))
				main = dir;

			if ((dir != null) && (main != dir))
				if (!areDirectionsOpposite(main, dir) && !areDirectionsOpposite(dir, main))
					return null;
				else
					isOpposite = true;
		}

		if (isOpposite)
			return main;

		return null;
	}

	private boolean areDirectionsOpposite(ForgeDirection dir1, ForgeDirection dir2)
	{
		if ((dir1 == ForgeDirection.UP) && (dir2 == ForgeDirection.DOWN))
			return true;
		if ((dir1 == ForgeDirection.SOUTH) && (dir2 == ForgeDirection.NORTH))
			return true;
		if ((dir1 == ForgeDirection.EAST) && (dir2 == ForgeDirection.WEST))
			return true;

		return false;
	}

	private void updateClient()
	{
		if (network != null && !MinecraftServer.getServer().isDedicatedServer())
		{
			InitPackets.network.sendToAllAround(new CopperWirePacket(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, connections),
					new TargetPoint(worldObj.provider.dimensionId, xCoord, yCoord, zCoord, 100));
		}
	}

	public static class EnergyNetwork
	{
		public static final short capacityPerWire = (short) 2500;

		private static final byte ticksTillUpdate = 2;

		private static final short maxTransferPerTile = 2500 * ticksTillUpdate; //can transmit a max of 2500RF/t

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

			buffer = new EnergyStorage(capacityPerWire * size, 2500);
		}

		public void updateNetwork(TileCopperWire wire)
		{
			ticksSinceLastUpdate++;

			if (ticksSinceLastUpdate == (ticksTillUpdate / 2))
				updateClient(wire);

			if (ticksSinceLastUpdate == ticksTillUpdate)
			{
				ticksSinceLastUpdate = 0;

				updateInputs(wire.worldObj);
				updateOutputs(wire);
			}
		}

		private void updateClient(TileCopperWire wire)
		{
			if(!MinecraftServer.getServer().isDedicatedServer())
			{
				energyScaled = (buffer.getEnergyStored() / (float) size / EnergyNetwork.capacityPerWire) * (2 * TileCopperWireRenderer.pixel);

				if (energyScaled > (2 * TileCopperWireRenderer.pixel))
					energyScaled = 2 * TileCopperWireRenderer.pixel;

				InitPackets.network.sendToAllAround(new EnergyNetworkPacket(wire.worldObj.provider.dimensionId, wire.xCoord, wire.yCoord, wire.zCoord,
						energyScaled), new TargetPoint(wire.worldObj.provider.dimensionId, wire.xCoord, wire.yCoord, wire.zCoord, 100));
			}
		}

		private void updateInputs(World world)
		{
			int distribute = 0;
			int tempSize = inputs.size();

			for (Coords coords : inputs)
			{
				if (tempSize != 0)
					distribute = (buffer.getMaxEnergyStored() - buffer.getEnergyStored()) / tempSize;
				else
					break;

				if (coords != null)
					if (buffer.getEnergyStored() != buffer.getMaxEnergyStored())
					{
						if (world.getTileEntity(coords.x, coords.y, coords.z) instanceof IEnergyHandler)
						{
							IEnergyHandler tile = (IEnergyHandler) world.getTileEntity(coords.x, coords.y, coords.z);
							if (tile != null)
							{
								int canFill = tile.extractEnergy(coords.dir, Math.min(distribute, maxTransferPerTile), true);

								buffer.receiveEnergy(tile.extractEnergy(coords.dir, canFill, false), false);

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
			int tempSize = outputs.size();

			for (Coords coords : outputs)
			{
				if (tempSize != 0)
					distribute = buffer.getEnergyStored() / tempSize;
				else
					break;

				if (coords != null)
					if (buffer.getEnergyStored() > 0)
					{
						if (wire.worldObj.getTileEntity(coords.x, coords.y, coords.z) instanceof IEnergyHandler)
						{
							IEnergyHandler tile = (IEnergyHandler) wire.worldObj.getTileEntity(coords.x, coords.y, coords.z);

							if (tile != null)
							{
								short transfered = (short) tile.receiveEnergy(coords.dir, Math.min(distribute, maxTransferPerTile), false);

								buffer.extractEnergy(transfered, false);

								tempSize--;
							}
						}
					}
					else
						break;

				if (tempSize == (outputs.size() / 2))
					updateClient(wire);
			}
		}

		public void changeSize(int with)
		{
			size += with;
			buffer.setCapacity(capacityPerWire * size);
		}

		public void writeToNBT(NBTTagCompound tag)
		{
			NBTTagCompound temp = new NBTTagCompound();

			buffer.writeToNBT(temp);

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
