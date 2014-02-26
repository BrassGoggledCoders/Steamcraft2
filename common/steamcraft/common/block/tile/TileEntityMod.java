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
 * File created @ [Feb 7, 2014, 3:36:22 PM]
 */
package common.steamcraft.common.block.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;

/**
 * The basic TileEntity class for SC2.
 * 
 * @author MrArcane111
 *
 */
public class TileEntityMod extends TileEntity implements IPowerReceptor, ISidedInventory {
	/** */
	public static float MJ_PER_WORK_UNIT;

	/** The amount of ticks this TileEntity should wait before receiving an update. */
	public static int ticksPerUpdate;

	/** The maximum amount of energy this machine uses */
	public static float maxEnergyReceived;

	/** The minimum amount of energy this machine uses */
	public static float minEnergyReceived;

	/** The amount of energy this machine can store. */
	public static float energyStorage;

	protected static PowerHandler powerHandler;

	protected static int timer = 0;

	protected static int powerLoss;

	protected static int powerLossRegularity;

	/** This machine's inventory size. */
	protected static ItemStack[] inventory;

	/** The machine's TileEntity name. */
	protected static String displayName;

	protected static int redstoneSignal = 0;

	/** An empty array in place of inventory slots. */
	public static final int[] EMPTY_INVENTORY = new int[] {};

	public TileEntityMod() {
		powerHandler = new PowerHandler(this, Type.MACHINE);
		powerHandler.configure(minEnergyReceived, maxEnergyReceived, maxEnergyReceived, energyStorage);
		powerHandler.configurePowerPerdition(powerLoss, powerLossRegularity);
	}

	@Override 
	public void updateEntity() {
		if (this.worldObj.isRemote) {
			// Insert packet shit here...
			return;
		}

		powerHandler.update();
		boolean changed = false;
		boolean invChanged = false;

		if (invChanged) {
			this.onInventoryChanged();
		}
		if (changed) {
			worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		}
	}

	@Override
	public void doWork(PowerHandler provider) {
		// work not implemented here
	}

	/**
	 * Reads a tile entity from NBT.
	 */
	@Override 
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		NBTTagList tagList = tag.getTagList("Items");
		this.inventory = new ItemStack[this.getSizeInventory()];

		for (int invLength = 0; invLength < tagList.tagCount(); ++invLength) {
			NBTTagCompound tagCompound = (NBTTagCompound)tagList.tagAt(invLength);
			byte byte0 = tagCompound.getByte("Slot");

			if (byte0 >= 0 && byte0 < this.inventory.length) {
				this.inventory[byte0] = ItemStack.loadItemStackFromNBT(tagCompound);
			}
		}

		this.powerHandler.setEnergy(tag.getFloat("Energy"));
		this.timer = tag.getShort("Time");

		if (tag.hasKey("CustomName")) {
			this.displayName = tag.getString("CustomName");
		}
	}

	/**
	 * Writes a tile entity to NBT.
	 */
	@Override 
	public void writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		tag.setFloat("Energy", this.powerHandler.getEnergyStored());
		tag.setShort("Time", (short)timer);
		NBTTagList tagList = new NBTTagList();

		for (int invLength = 0; invLength < this.inventory.length; ++invLength) {
			if (this.inventory[invLength] != null) {
				NBTTagCompound tagCompound = new NBTTagCompound();
				tagCompound.setByte("Slot", (byte)invLength);
				this.inventory[invLength].writeToNBT(tagCompound);
				tagList.appendTag(tagCompound);
			}
		}

		tag.setTag("Items", tagList);

		if (this.isInvNameLocalized()) {
			tag.setString("CustomName", this.displayName);
		}
	}

	/** Synchronizes the client to the server. */
	@Override
	public Packet getDescriptionPacket() {
		NBTTagCompound data = new NBTTagCompound();
		this.writeToNBT(data);
		return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, data);
	}

	/**
	 * Called when you receive a TileEntityData packet for the location this
	 * TileEntity is currently in. On the client, the NetworkManager will always
	 * be the remote server. On the server, it will be whomever is responsible for
	 * sending the packet.
	 *
	 * @param networkManager - the NetworkManager from which the packet originated
	 * @param packet - the data packet
	 */
	@Override 
	public void onDataPacket(INetworkManager networkManager, Packet132TileEntityData packet) {  
		if (packet.data.hasKey("Energy") && packet.data.hasKey("Items") && packet.data.hasKey("Time")) {
			this.readFromNBT(packet.data);
		}
	}

	@Override 
	public int getSizeInventory() {
		return inventory.length;
	}

	@Override 
	public ItemStack getStackInSlot(int i) {
		return inventory[i];
	}

	/**
	 * Removes from an inventory slot up to a specified number of items and returns them in a
	 * new stack.
	 * 
	 * @param slot - the inventory slot
	 * @param increment - the number of items
	 */
	@Override 
	public ItemStack decrStackSize(int slot, int increment) {
		if (this.inventory[slot] != null) {
			ItemStack stack;

			if (this.inventory[slot].stackSize <= increment) {
				stack = this.inventory[slot];
				this.inventory[slot] = null;
				return stack;
			} else {
				stack = this.inventory[slot].splitStack(increment);

				if (this.inventory[slot].stackSize == 0) {
					this.inventory[slot] = null;
				}

				return stack;
			}
		} else {
			return null;
		}
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
	 */
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack item) {
		return true;
	}

	/**
	 * Returns an array containing the indices of the slots that can be accessed by automation on the given side of this
	 * block.
	 */
	@Override 
	public int[] getAccessibleSlotsFromSide(int slot) {
		return this.EMPTY_INVENTORY;
	}

	/**
	 * Returns true if automation can insert the given item in the given slot from the given side. Args: Slot, item,
	 * side
	 */
	@Override 
	public boolean canInsertItem(int slot, ItemStack item, int side) {
		return this.isItemValidForSlot(slot, item);
	}

	/**
	 * Returns true if automation can extract the given item in the given slot from the given side. Args: Slot, item,
	 * side
	 */
	@Override 
	public boolean canExtractItem(int slot, ItemStack item, int side) {
		return true;
	}


	@Override
	public PowerReceiver getPowerReceiver(ForgeDirection direction) {
		if (redstoneSignal > 0) {
			return powerHandler.getPowerReceiver();
		}

		return powerHandler.getPowerReceiver();
	}

	/**
	 * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
	 * like when you close a workbench GUI.
	 */
	@Override 
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (this.inventory[slot] != null) {
			ItemStack itemstack = this.inventory[slot];
			this.inventory[slot] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
	 */
	@Override 
	public void setInventorySlotContents(int slot, ItemStack item) {
		this.inventory[slot] = item;

		if (item != null && item.stackSize > this.getInventoryStackLimit()) {
			item.stackSize = this.getInventoryStackLimit();
		}
	}

	/**
	 * Returns the maximum stack size for a inventory slot. Seems to always be 64
	 */
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}


	@Override 
	public boolean canUpdate() {
		return true;
	}

	/**
	 * Sets the custom display name to use when opening a GUI linked to this tile entity.
	 */
	public void setGuiDisplayName(String s) {
		displayName = s;
	}

	@Override
	public String getInvName() {
		return this.isInvNameLocalized() ? displayName : "container." + displayName.toLowerCase();
	} 

	@Override
	public boolean isInvNameLocalized() {
		return displayName != null && displayName.length() > 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
		return getWorld().getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	}

	@Override
	public void openChest() {}

	@Override 
	public void closeChest() {}

	public float getBuffer() {
		return powerHandler.getEnergyStored();
	}
	public float getMaxBuffer() {
		return powerHandler.getMaxEnergyStored();
	}

	@Override
	public World getWorld() {
		return this.worldObj;
	}
}

// ======= UE SHIT ======== //
/*
public EnergyStorageHandler energy;

public void recharge(ItemStack stack) {
	this.energy.extractEnergy(CompatibilityModule.chargeItem(stack, this.energy.getEnergy(), true), true);
}

public void discharge(ItemStack stack) {
	this.energy.receiveEnergy(CompatibilityModule.dischargeItem(stack, this.energy.getEmptySpace(), true), true);
}

public TileEntityCompressor() {}

@Override
public boolean canConnect(ForgeDirection direction) {
	return (direction == null || direction == ForgeDirection.UNKNOWN) ? false : true;
}

@Override
public long onReceiveEnergy(ForgeDirection from, long receive, boolean doReceive) {
	return 0;
}

@Override
public long onExtractEnergy(ForgeDirection from, long extract, boolean doExtract) {
	return 0;
}

@Override
public void setEnergy(ForgeDirection from, long energy) {

}

@Override
public long getEnergy(ForgeDirection from) {
	return 0;
}

@Override
public long getEnergyCapacity(ForgeDirection from) {
	return 0;
}
 */
