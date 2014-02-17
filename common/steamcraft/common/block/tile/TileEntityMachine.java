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
 * File created @ [Feb 1, 2014, 12:54:18 PM]
 */
package common.steamcraft.common.block.tile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import common.steamcraft.api.IMachine;
import common.steamcraft.common.block.machines.MachineConstants;
import common.steamcraft.common.network.NetworkTile;

/**
 * @author MrArcane111
 *
 */
public class TileEntityMachine extends NetworkTile implements IMachine {
	protected int machineType;
	protected int facingDirection;
	private boolean markedForDeletion;

	public int getMachineType() {
		return this.machineType;
	}

	public void setMachineType(int machineType) {
		this.machineType = machineType;
	}

	public boolean isMarkedForDeletion() {
		return this.markedForDeletion;
	}

	public void setMarkedForDeletion(boolean markedForDeletion) {
		this.markedForDeletion = markedForDeletion;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		int machineType = nbtTagCompound.getInteger("machineType");

		if (machineType != 0) {
			if (getMachineType() != machineType) {
				TileEntityMachine newMachine;
				//TileEntityMachine newMachine;

				if (MachineConstants.MachineTypes.values()[machineType].getInstanceClass().isInstance(this)) {
					setMachineType(machineType);
					newMachine = this;
				} else {
					newMachine = initMachine(machineType);
					newMachine.readFromNBT(nbtTagCompound);
				}

				newMachine.setFacingDirection(nbtTagCompound.getByte("direction"));
			}
		} else {
			setMarkedForDeletion(true);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setInteger("machineType", this.machineType);
		nbtTagCompound.setByte("direction", (byte)this.facingDirection);
	}

	@Override
	public void writePacket(DataOutputStream dataStream) throws IOException {
		dataStream.writeInt(this.machineType);
		dataStream.writeByte(this.facingDirection);
	}

	@Override
	public void readPacket(DataInputStream dataStream) throws IOException {
		int readType = dataStream.readInt();
		TileEntityMachine machine;
		//TileEntityMachine machine;
		
		if (readType != getMachineType()) {
			machine = initMachine(readType);
		} else
		{
			machine = this;
		}

		machine.setFacingDirection(dataStream.readByte());
		machine.readPartialPacket(dataStream);
	}

	public void readPartialPacket(DataInputStream dataStream) throws IOException {}

	public TileEntityMachine initMachine(int machineType)
	{
		setMachineType(machineType);
		TileEntityMachine machine = null;
		
		if (machineType != 0) {
			try {
				machine = (TileEntityMachine)MachineConstants.MachineTypes.values()[machineType].instanceClass.getConstructors()[0].newInstance(new Object[0]);
				this.worldObj.setBlockTileEntity(this.xCoord, this.yCoord, this.zCoord, machine);
				machine.setMachineType(machineType);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		return machine;
	}

	public void manageDeletion() {
		if (this.markedForDeletion) {
			this.worldObj.removeBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
			return;
		}
	}

	public double[] getBounds() {
		return new double[] { 0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D };
	}

	public boolean isNormalCube() {
		return true;
	}

	@Override
	public void updateEntity() {
		manageDeletion();
		super.updateEntity();
	}

	@Override
	public int getFacingDirection() {
		return this.facingDirection;
	}

	@Override
	public void setFacingDirection(int facingDirection) {
		this.facingDirection = facingDirection;
	}

	@Override
	public boolean isSolidOnSide(ForgeDirection side) {
		return true;
	}

	@Override
	public void onBreak() {}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving entityLiving) {}

	@Override
	public boolean onActivated(EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		return false;
	}

	@Override
	public ArrayList getBlockDropped() {
		ArrayList result = new ArrayList();
		//result.add(new ItemStack(ModMachines.blockMachine, 1, getMachineType()));

		return result;
	}

	@Override
	public float getHardness() {
		return 1.0F;
	}

	@Override
	public boolean isActive() {
		return false;
	}

	@Override
	public void a(NBTTagCompound paramNBTTagCompound) {}

	@Override
	public void b(NBTTagCompound paramNBTTagCompound) {}

	@Override
	public void readPacketFromClient(DataInputStream dataStream) throws IOException {}
}
