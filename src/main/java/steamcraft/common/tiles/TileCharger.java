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

import java.util.EnumSet;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import boilerplate.common.baseclasses.BaseTileWithInventory;
import boilerplate.steamapi.item.IEnergyItem;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @author decebaldecebal
 *
 */
public class TileCharger extends BaseTileWithInventory implements IEnergyHandler
{
	private static int energy = 100000;
	public static short transferRate = 80;

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
		return this.buffer.getEnergyStored() / this.buffer.getMaxEnergyStored() / par1;
	}

	@Override
	public void updateEntity()
	{
		if(!this.worldObj.isRemote)
		{

			if(this.buffer.getEnergyStored() > 0)
				this.chargeItems();

			short inputEnergy = (short) this.extractEnergy(ForgeDirection.UNKNOWN, transferRate, true);

			if(inputEnergy > 0)
				for(ForgeDirection direction : EnumSet.allOf(ForgeDirection.class))
					if(inputEnergy > 0)
					{
						TileEntity tileEntity = this.worldObj.getTileEntity(this.xCoord - direction.offsetX, this.yCoord - direction.offsetY,
								this.zCoord - direction.offsetZ);

						if(tileEntity instanceof IEnergyHandler)
							inputEnergy -= this.receiveEnergy(ForgeDirection.UNKNOWN,
									((IEnergyHandler) tileEntity).extractEnergy(direction.getOpposite(), inputEnergy, false), false);
					}
					else
						break;
		}
	}

	private void chargeItems()
	{
		for(ItemStack stack : this.inventory)
			if(stack != null)
			{
				IEnergyItem item = (IEnergyItem) stack.getItem();

				this.buffer.modifyEnergyStored(-item.receiveEnergy(stack, this.buffer.getEnergyStored(), false));

				if(this.buffer.getEnergyStored() < 0)
					break;
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
		int usedEnergy = maxExtract;
		maxExtract -= this.buffer.extractEnergy(maxExtract, simulate);

		usedEnergy -= maxExtract;

		return usedEnergy;
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
}
