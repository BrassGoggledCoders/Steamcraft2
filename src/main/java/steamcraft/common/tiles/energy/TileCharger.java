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

import boilerplate.api.IEnergyItem;
import boilerplate.api.IOpenableGUI;
import boilerplate.common.baseclasses.BaseTileWithInventory;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import steamcraft.client.gui.GuiCharger;
import steamcraft.common.tiles.container.ContainerCharger;

/**
 * @author decebaldecebal
 *
 */
public class TileCharger extends BaseTileWithInventory implements IEnergyReceiver, IOpenableGUI
{
	private static int energy = 1000000;
	public static short transferRate = 1000;

	public EnergyStorage buffer = new EnergyStorage(energy, transferRate);

	public TileCharger()
	{
		super(1);
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
		return this.buffer.getEnergyStored() / (energy / 1000) / par1;
	}

	@Override
	public void updateEntity()
	{
		if (!this.worldObj.isRemote)
		{
			if (this.buffer.getEnergyStored() > 0)
			{
				if (this.inventory[0] != null)
				{
					IEnergyItem item = (IEnergyItem) this.inventory[0].getItem();

					this.buffer.modifyEnergyStored(
							-item.receiveEnergy(this.inventory[0], Math.min(this.buffer.getEnergyStored(), this.buffer.getMaxExtract()), false));
				}
			}
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
		return "Charger";
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new GuiCharger(player.inventory, (TileCharger) world.getTileEntity(x, y, z));
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		return new ContainerCharger(player.inventory, (TileCharger) world.getTileEntity(x, y, z));
	}
}
