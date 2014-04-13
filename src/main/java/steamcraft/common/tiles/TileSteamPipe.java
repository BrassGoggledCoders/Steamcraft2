/**
 * This class was created by <Surseance> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [Apr 13, 2014, 1:52:49 PM]
 */
package steamcraft.common.tiles;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import steamcraft.api.machines.Couple;
import steamcraft.api.steam.ISteamConsumer;
import steamcraft.api.steam.ISteamProvider;
import steamcraft.api.steam.ISteamStorage;
import steamcraft.common.lib.network.LoggerSteamcraft;
import steamcraft.common.lib.network.NetworkTile;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author Surseance (Johnny Eatmon)
 *
 */
public class TileSteamPipe extends NetworkTile
{
	private int connection;

	@SideOnly(Side.CLIENT)
	public boolean loaded;

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
		this.connection = tagCompound.getByte("Connection");
	}

	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
		tagCompound.setByte("Connection", (byte)this.connection);
	}

	@Override
	public void writePacket(DataOutputStream dataStream) throws IOException
	{
		dataStream.writeByte(this.connection);
	}

	@Override
	public void readPacket(DataInputStream dataStream) throws IOException
	{
		this.connection = dataStream.readByte();
		this.loaded = true;
	}

	@Override
	public void readPacketFromClient(DataInputStream dataStream) throws IOException {}

	public static int getConnectionCode(int side1, int side2)
	{
		if (side1 == side2) 
		{
			LoggerSteamcraft.log(Level.WARNING, "Invalid pipe connection!");
		}
		if (side1 > side2) 
		{
			return (side1 << 3) + side2;
		}

		return (side2 << 3) + side1;
	}

	public int getConnection1()
	{
		return this.connection >> 3;
	}

	public int getConnection2()
	{
		return this.connection & 0x7;
	}

	@Override
	public void updateContainingBlockInfo()
	{
		super.updateContainingBlockInfo();

		if (!this.worldObj.isRemote)
		{
			int placedSide = this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord) - 1;

			if (placedSide != -1)
			{
				boolean isConnected = false;
				this.worldObj.setBlockMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, 0, 2);
				this.connection = getConnectionCode(placedSide, ForgeDirection.OPPOSITES[placedSide]);
				ForgeDirection direction = ForgeDirection.getOrientation(placedSide);
				TileEntity te = this.worldObj.getBlockTileEntity(this.xCoord - direction.offsetX, this.yCoord - direction.offsetY, this.zCoord - direction.offsetZ);

				if ((te != null) && ((te instanceof TileSteamPipe)))
				{
					TileSteamPipe otherPipe = (TileSteamPipe)te;

					if (otherPipe.connectEmptySide(placedSide))
					{
						isConnected = true;
						this.cycleConnections(ForgeDirection.OPPOSITES[placedSide]);
					}
				}

				if (!isConnected)
				{
					this.cycleConnections(-1);
				}
			}
		}
	}

	public boolean connectEmptySide(int sideToConnect)
	{
		int[] sides = new int[2];
		sides[0] = getConnection1();
		sides[1] = getConnection2();

		if ((sides[0] == sideToConnect) || (sides[1] == sideToConnect)) 
		{
			return true;
		}

		for (int i = 0; i < 2; i++)
		{
			boolean acceptDirection = true;
			ForgeDirection direction = ForgeDirection.getOrientation(sides[i]);
			TileEntity te = this.worldObj.getBlockTileEntity(this.xCoord + direction.offsetX, this.yCoord + direction.offsetY, this.zCoord + direction.offsetZ);

			if ((te != null) && ((te instanceof TileSteamPipe)))
			{
				TileSteamPipe otherPipe = (TileSteamPipe)te;

				if (ForgeDirection.OPPOSITES[otherPipe.getConnection1()] == sides[i]) 
				{
					acceptDirection = false;
				}
				else if (ForgeDirection.OPPOSITES[otherPipe.getConnection2()] == sides[i]) 
				{
					acceptDirection = false;
				}
			}
			else if ((te != null) && (((te instanceof ISteamConsumer)) || ((te instanceof ISteamProvider)) || ((te instanceof ISteamStorage))))
			{
				acceptDirection = false;
			}

			if (!acceptDirection)
			{
				continue;
			}

			sides[i] = sideToConnect;
			this.connection = getConnectionCode(sides[0], sides[1]);
			this.setMarkedForResend(true);
			return true;
		}

		return false;
	}

	public void cycleConnections(int forcedDirection)
	{
		if (!this.worldObj.isRemote)
		{
			List possibleDirections = new ArrayList();

			for (int side = 0; side < 6; side++)
			{
				ForgeDirection direction = ForgeDirection.getOrientation(side);
				TileEntity otherTile = this.worldObj.getBlockTileEntity(this.xCoord + direction.offsetX, this.yCoord + direction.offsetY, this.zCoord + direction.offsetZ);

				if (otherTile == null)
					continue;
				if ((!(otherTile instanceof TileSteamPipe)) && (!(otherTile instanceof ISteamConsumer)) && (!(otherTile instanceof ISteamProvider)) && (!(otherTile instanceof ISteamStorage))) 
				{
					continue;
				}

				possibleDirections.add(Integer.valueOf(side));
			}

			if (possibleDirections.isEmpty()) 
			{
				return;
			}

			List availableConnection = new ArrayList();
			Integer posDir1;
			Integer posDir2;

			for (Iterator iterator = possibleDirections.iterator(); iterator.hasNext(); ) 
			{
				posDir1 = (Integer)iterator.next();
				posDir2 = (Integer)iterator.next();


				if ((posDir1 != posDir2) && (forcedDirection == -1) || (posDir1.intValue() == forcedDirection) || (posDir2.intValue() == forcedDirection))
				{
					int newConnection = getConnectionCode(posDir1.intValue(), posDir2.intValue());

					if (!availableConnection.contains(Integer.valueOf(newConnection)))
					{
						availableConnection.add(Integer.valueOf(newConnection));
					}
				}
			}

			if (availableConnection.isEmpty())
			{
				if (forcedDirection != -1)
				{
					availableConnection.add(Integer.valueOf(getConnectionCode(forcedDirection, ForgeDirection.OPPOSITES[forcedDirection])));
				}
				else 
				{
					availableConnection.add(Integer.valueOf(getConnectionCode(((Integer)possibleDirections.get(0)).intValue(), ForgeDirection.OPPOSITES[((Integer)possibleDirections.get(0)).intValue()])));
				}
			}

			Collections.sort(availableConnection);
			boolean found = false;

			for (int i = 0; i < availableConnection.size(); i++)
			{
				if (((Integer)availableConnection.get(i)).intValue() != this.connection)
					continue;

				found = true;

				if (i == availableConnection.size() - 1) 
				{
					this.connection = ((Integer)availableConnection.get(0)).intValue(); break;
				}

				this.connection = ((Integer)availableConnection.get(i + 1)).intValue();
				break;
			}

			if (!found)
			{
				this.connection = ((Integer)availableConnection.get(0)).intValue();
			}

			this.setMarkedForResend(true);
		}
	}

	public static Couple findConnectedEntity(World world, int startX, int startY, int startZ, int startSide)
	{
		int x = startX;
		int y = startY;
		int z = startZ;
		int side = startSide;
		boolean found = false;

		while (!found)
		{
			TileEntity te = world.getBlockTileEntity(x, y, z);

			if (te == null)
				return null;
			if (((te instanceof ISteamConsumer)) || ((te instanceof ISteamStorage)))
				return new Couple(te, Integer.valueOf(ForgeDirection.OPPOSITES[side]));
			if ((te instanceof TileSteamPipe))
			{
				TileSteamPipe nextPipe = (TileSteamPipe)te;
				ForgeDirection nextDirection = null;

				if (nextPipe.getConnection1() == ForgeDirection.OPPOSITES[side])
				{
					nextDirection = ForgeDirection.getOrientation(nextPipe.getConnection2());
				}
				else if (nextPipe.getConnection2() == ForgeDirection.OPPOSITES[side])
				{
					nextDirection = ForgeDirection.getOrientation(nextPipe.getConnection1());
				}
				else 
				{
					return null;
				}

				x += nextDirection.offsetX;
				y += nextDirection.offsetY;
				z += nextDirection.offsetZ;
				side = nextDirection.ordinal();
			}
			else 
			{
				return null;
			}
		}

		return null;
	}

	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox()
	{
		return AxisAlignedBB.getAABBPool().getAABB(this.xCoord, this.yCoord, this.zCoord, this.xCoord + 1.0D, this.yCoord + 1.0D, this.zCoord + 1.0D);
	}
}
