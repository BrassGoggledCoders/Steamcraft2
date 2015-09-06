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
package steamcraft.common.tiles.energy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraftforge.common.util.ForgeDirection;

import boilerplate.api.IOpenableGUI;
import boilerplate.common.baseclasses.BaseTileWithInventory;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import steamcraft.client.gui.GuiCapacitor;
import steamcraft.common.tiles.container.ContainerCapacitor;

/**
 * @author decebaldecebal
 *
 */
public class TileCapacitor extends BaseTileWithInventory implements IEnergyHandler, IOpenableGUI
{
	public static short transferRate = 100;

	public EnergyStorage buffer = new EnergyStorage(64000000, transferRate);

	public TileCapacitor()
	{
		super(0);
	}

	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		this.buffer.readFromNBT(tag);
	}

	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		this.buffer.writeToNBT(tag);
	}

	@SideOnly(Side.CLIENT)
	public int getEnergyScaled(int par1)
	{
		return this.buffer.getEnergyStored() / (this.buffer.getMaxEnergyStored() / 1000) / par1;
	}

	@Override
	public void updateEntity()
	{
		if (!this.worldObj.isRemote)
		{
			/*
			 * short outputEnergy = (short)
			 * this.extractEnergy(ForgeDirection.UNKNOWN, this.transferRate,
			 * true);
			 * 
			 * if(outputEnergy > 0) for(ForgeDirection direction :
			 * EnumSet.allOf(ForgeDirection.class)) if(outputEnergy > 0) {
			 * TileEntity tileEntity = this.worldObj.getTileEntity(this.xCoord +
			 * direction.offsetX, this.yCoord + direction.offsetY, this.zCoord +
			 * direction.offsetZ);
			 * 
			 * if(tileEntity instanceof IEnergyReceiver) outputEnergy -=
			 * this.extractEnergy(ForgeDirection.UNKNOWN, ((IEnergyReceiver)
			 * tileEntity).receiveEnergy(direction.getOpposite(), outputEnergy,
			 * false), false); } else break;
			 */
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
		return this.buffer.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		return this.buffer.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return this.buffer.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return this.buffer.getMaxEnergyStored();
	}

	@Override
	public String getInventoryName()
	{
		return "Capacitor";
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiCapacitor(player.inventory, (TileCapacitor) world.getTileEntity(x, y, z));
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerCapacitor(player.inventory, (TileCapacitor) world.getTileEntity(x, y, z));
	}
}
