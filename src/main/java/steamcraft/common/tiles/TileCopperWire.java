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
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import steamcraft.common.InitBlocks;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyStorage;

/**
 * @author warlordjones
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
		if (!worldObj.isRemote && isMaster)
			network.updateNetwork(worldObj);
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

		return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet)
	{
		extract = readDirectionFromNBT(packet.func_148857_g());
	}

	public void changeExtracting()
	{
		if (extract != null)
		{
			if (!worldObj.isRemote)
				network.inputs
						.remove(new Coords(xCoord + extract.offsetX, yCoord + extract.offsetY, zCoord + extract.offsetZ, extract.getOpposite()));

			extract = null;
		}
		else
			for (ForgeDirection dir : connections)
				if ((dir != null) && canChangeState(dir))
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

	public void updateConnections()
	{
		if (canConnect(xCoord, yCoord + 1, zCoord))
		{
			connections[0] = ForgeDirection.UP;
			updateNetwork(ForgeDirection.UP);
		}
		else
		{
			if ((connections[0] != null) && !worldObj.isRemote)
			{
				ForgeDirection dir = connections[0];

				Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());

				network.outputs.remove(temp);
			}

			connections[0] = null;
		}

		if (canConnect(xCoord, yCoord - 1, zCoord))
		{
			connections[1] = ForgeDirection.DOWN;
			updateNetwork(ForgeDirection.DOWN);
		}
		else
		{
			if ((connections[1] != null) && !worldObj.isRemote)
			{
				ForgeDirection dir = connections[1];

				Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());

				network.outputs.remove(temp);
			}

			connections[1] = null;
		}

		if (canConnect(xCoord, yCoord, zCoord + 1))
		{
			connections[2] = ForgeDirection.SOUTH;
			updateNetwork(ForgeDirection.SOUTH);
		}
		else
		{
			if ((connections[2] != null) && !worldObj.isRemote)
			{
				ForgeDirection dir = connections[2];

				Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());

				network.outputs.remove(temp);
			}
			connections[2] = null;
		}

		if (canConnect(xCoord, yCoord, zCoord - 1))
		{
			connections[3] = ForgeDirection.NORTH;
			updateNetwork(ForgeDirection.NORTH);
		}
		else
		{
			if ((connections[3] != null) && !worldObj.isRemote)
			{
				ForgeDirection dir = connections[3];

				Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());

				network.outputs.remove(temp);
			}

			connections[3] = null;
		}

		if (canConnect(xCoord + 1, yCoord, zCoord))
		{
			connections[4] = ForgeDirection.EAST;
			updateNetwork(ForgeDirection.EAST);
		}
		else
		{
			if ((connections[4] != null) && !worldObj.isRemote)
			{
				ForgeDirection dir = connections[4];

				Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());

				network.outputs.remove(temp);
			}

			connections[4] = null;
		}

		if (canConnect(xCoord - 1, yCoord, zCoord))
		{
			connections[5] = ForgeDirection.WEST;
			updateNetwork(ForgeDirection.WEST);
		}
		else
		{
			if ((connections[5] != null) && !worldObj.isRemote)
			{
				ForgeDirection dir = connections[5];

				Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());

				network.outputs.remove(temp);
			}

			connections[5] = null;
		}

		if (!worldObj.isRemote)
		{
			if (network == null)
			{
				network = new EnergyNetwork(1);
				isMaster = true;
			}

			if ((extract != null) && !canChangeState(extract))
			{
				network.inputs
						.remove(new Coords(xCoord + extract.offsetX, yCoord + extract.offsetY, zCoord + extract.offsetZ, extract.getOpposite()));
				extract = null;
			}

			for (ForgeDirection dir : connections)
				if ((dir != null) && canChangeState(dir))
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
		}
	}

	public void updateNetwork(ForgeDirection dir)
	{
		if (!worldObj.isRemote)
		{
			TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);

			if (tile instanceof TileCopperWire)
			{
				TileCopperWire wire = (TileCopperWire) tile;

				if (wire.network != null)
					if (wire.network != network)
						if (network != null)
						{
							if (network.size < wire.network.size)
							{
								network = wire.network;
								network.changeSize(1);

								updateNetworkToOthers(dir);
							}
							else
							{
								wire.network = network;
								network.changeSize(1);

								wire.updateNetworkToOthers(dir.getOpposite());
							}
						}
						else
						{
							network = wire.network;
							network.changeSize(1);
						}
			}
		}
	}

	private void updateNetworkToOthers(ForgeDirection ignore)
	{
		if (!worldObj.isRemote)
			for (ForgeDirection dir : connections)
				if ((dir != null) && (dir != ignore))
					if (worldObj.getBlock(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ) == InitBlocks.blockCopperWire)
					{
						TileCopperWire wire = (TileCopperWire) worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord
								+ dir.offsetZ);

						if ((wire.network != null) && (wire.network.size != 0))
							wire.network = null;

						network.changeSize(1);

						if (wire.network != null)
						{
							if (wire.isMaster)
								wire.isMaster = false;

							if (!wire.network.equals(network))
							{
								wire.network = network;
								wire.updateNetworkToOthers(dir.getOpposite());
							}
						}
						else
							wire.network = network;
					}
					else if (canChangeState(dir))
					{
						Coords temp = new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite());

						if (dir != extract)
						{
							if (!network.outputs.contains(temp))
								network.outputs.add(temp);
						}
						else if (!network.inputs.contains(temp))
							network.inputs.add(temp);
					}
	}

	public void removeFromNetwork()
	{
		if (!worldObj.isRemote)
			if (network != null)
			{
				network.changeSize(-1);

				if (network.size == 0)
					network = null;
				else
					for (ForgeDirection dir : connections)
						if (dir != null)
							if (!canChangeState(dir))
							{
								TileCopperWire wire = (TileCopperWire) worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord
										+ dir.offsetZ);

								network.changeSize(-network.size);
								wire.network = new EnergyNetwork(1);
								wire.isMaster = true;
							}
							else if (dir == extract)
								network.inputs
										.remove(new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite()));
							else
								network.outputs
										.remove(new Coords(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ, dir.getOpposite()));
			}
	}

	private boolean canConnect(int x, int y, int z)
	{
		return (worldObj.getBlock(x, y, z) == InitBlocks.blockCopperWire) || isEnergyHandler(x, y, z);
	}

	private boolean isEnergyHandler(int x, int y, int z)
	{
		return worldObj.getTileEntity(x, y, z) instanceof IEnergyHandler;
	}

	public boolean canChangeState(ForgeDirection dir)
	{
		if ((worldObj.getBlock(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ) != InitBlocks.blockCopperWire)
				&& isEnergyHandler(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ))
			return true;
		return false;
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

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		return 0;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		/*
		 * if(buffer.getEnergy()==null || (buffer.getEnergy()!=null &&
		 * buffer.getEnergy().getEnergy() == resource.getEnergy())) { int amount
		 * = buffer.fill(resource, doFill);
		 * 
		 * if(amount > 0) received = from; else received = null;
		 * 
		 * return amount; }
		 */
		return 0;

	}

	public static class EnergyNetwork
	{
		private static final byte ticksTillUpdate = 4;

		private static final short maxExtractPerTile = 50 * ticksTillUpdate;
		private static final short maxTransferPerTile = 100 * ticksTillUpdate;

		public IEnergyStorage buffer;
		public int size;

		ArrayList<Coords> inputs = new ArrayList<Coords>();
		ArrayList<Coords> outputs = new ArrayList<Coords>();

		private byte ticksSinceLastUpdate = 0;

		public EnergyNetwork(int size)
		{
			this.size = size;

			buffer = new EnergyStorage(200 * size);
		}

		public void updateNetwork(World world)
		{
			ticksSinceLastUpdate++;

			if (ticksSinceLastUpdate > ticksTillUpdate)
			{
				ticksSinceLastUpdate = 0;

				updateInputs(world);
				updateOutputs(world);
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
								int canFill = buffer.receiveEnergy(tile.getEnergyStored(coords.dir), true);

								buffer.receiveEnergy(tile.extractEnergy(coords.dir, canFill, false), false);

								tempSize--;
							}
						}
					}
					else
						break;
			}
		}

		private void updateOutputs(World world)
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
						if (world.getTileEntity(coords.x, coords.y, coords.z) instanceof IEnergyHandler)
						{
							IEnergyHandler tile = (IEnergyHandler) world.getTileEntity(coords.x, coords.y, coords.z);

							if (tile != null)
							{
								short transfered = 0;

								transfered = (short) tile.receiveEnergy(coords.dir, maxTransferPerTile, false);

								buffer.extractEnergy(transfered, false);

								tempSize--;
							}
						}
					}
					else
						break;
			}
		}

		public void changeSize(int with)
		{
			size += with;
		}

		public void writeToNBT(NBTTagCompound tag)
		{
			NBTTagCompound temp = new NBTTagCompound();

			temp.setInteger("bufferSize", size);

			NBTTagCompound inputTag = new NBTTagCompound();
			writeArrayListToNBT(inputTag, inputs);
			temp.setTag("inputList", inputTag);

			NBTTagCompound outputTag = new NBTTagCompound();
			writeArrayListToNBT(outputTag, outputs);
			temp.setTag("outputList", outputTag);

			tag.setTag("buffer", temp);
		}

		private void writeArrayListToNBT(NBTTagCompound tag, ArrayList<Coords> list)
		{
			NBTTagList tagList = new NBTTagList();

			for (int i = 0; i < list.size(); i++)
			{
				Coords temp = list.get(i);
				if (temp != null)
				{
					NBTTagCompound coordTag = new NBTTagCompound();

					coordTag.setInteger("index", i);
					temp.writeToNBT(coordTag);

					tagList.appendTag(coordTag);
				}
			}

			tag.setTag("list", tagList);
		}

		public static EnergyNetwork readFromNBT(NBTTagCompound tag)
		{
			NBTTagCompound temp = tag.getCompoundTag("buffer");

			EnergyNetwork buffer = new EnergyNetwork(temp.getInteger("bufferSize"));

			buffer.buffer = new EnergyStorage(200 * buffer.size);
			EnergyNetwork.readFromNBT(temp);

			readArrayListFromNBT(temp.getCompoundTag("inputList"), buffer.inputs);
			readArrayListFromNBT(temp.getCompoundTag("outputList"), buffer.outputs);

			return buffer;
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
			if (obj instanceof Coords)
			{
				Coords coord = (Coords) obj;
				if ((x == coord.x) && (y == coord.y) && (z == coord.z) && (dir == coord.dir))
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

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return network.buffer.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return network.buffer.getMaxEnergyStored();
	}

}
