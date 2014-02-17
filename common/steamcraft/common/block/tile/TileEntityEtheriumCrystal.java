/**
 * This class was created by <MrArcane111> or his SC2 development team. 
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 * 
 * Steamcraft 2 is based on the original Steamcraft created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 * 
 * Some code is derived from PowerCraft created by MightyPork which is registered
 * under the MMPL v1.0.
 * PowerCraft (c) MightyPork 2012
 *
 * File created @ [Feb 17, 2014, 10:12:21 AM]
 */
package common.steamcraft.common.block.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import common.steamcraft.common.network.NetworkTile;

/**
 * @author MrArcane111
 *
 */
public class TileEntityEtheriumCrystal extends NetworkTile {
	@Override
	public void writePacket(DataOutputStream dataStream) throws IOException {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
	}

	@Override
	public void readPacket(DataInputStream dataStream) throws IOException {
		NBTTagCompound nbt = new NBTTagCompound();
		this.readFromNBT(nbt);
	}

	@Override
	public void readPacketFromClient(DataInputStream dataStream) throws IOException {}
	
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeToNBT(nbt);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, nbt);
	}

	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData packet) {
		this.readFromNBT(packet.data);
	}
}
