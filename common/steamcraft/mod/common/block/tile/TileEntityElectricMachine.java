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
 * File created @ [Feb 6, 2014, 7:20:17 PM]
 */
package common.steamcraft.mod.common.block.tile;

import java.util.EnumSet;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import universalelectricity.api.CompatibilityModule;
import universalelectricity.api.UniversalClass;
import universalelectricity.api.energy.EnergyStorageHandler;
import universalelectricity.api.energy.IEnergyContainer;
import universalelectricity.api.energy.IEnergyInterface;
import universalelectricity.api.vector.Vector3;

/**
 * @author Decebaldecebal
 *
 */
@UniversalClass
public abstract class TileEntityElectricMachine extends TileEntity implements IEnergyContainer, IEnergyInterface
{
	protected EnergyStorageHandler energy;
	protected ItemStack[] inventory;

	/**
	 * Recharges electric item.
	 */
	public void recharge(ItemStack itemStack)
	{
		this.energy.extractEnergy(CompatibilityModule.chargeItem(itemStack, this.energy.getEnergy(), true), true);
	}
	
	/**
	 * Discharges electric item.
	 */
	public void discharge(ItemStack itemStack)
	{
		/*
		if(CompatHelper.IC2Loaded && itemStack.getItem() instanceof IElectricItem)
		{
			IElectricItem item = (IElectricItem)itemStack.getItem();

			if(item.canProvideEnergy(itemStack))
			{
				long gain = (long) (ElectricItem.manager.discharge(itemStack, (int)(this.energy.getEmptySpace()*CompatHelper.UE_TO_IC2), 1, false, false)/CompatHelper.UE_TO_IC2);
				this.energy.modifyEnergyStored(gain);
			}
		}
		*/
		
		this.energy.receiveEnergy(CompatibilityModule.dischargeItem(itemStack, this.energy.getEmptySpace(), true), true);
	}
	
	/**
	 * The electrical input direction.
	 * 
	 * @return The direction that electricity is entered into the tile. Return null for no input. By
	 * default you can accept power from all sides.
	 */
	public EnumSet<ForgeDirection> getInputDirections()
	{
		return EnumSet.allOf(ForgeDirection.class);
	}

	/**
	 * The electrical output direction.
	 * 
	 * @return The direction that electricity is output from the tile. Return null for no output. By
	 * default it will return an empty EnumSet.
	 */
	public EnumSet<ForgeDirection> getOutputDirections()
	{
		return EnumSet.noneOf(ForgeDirection.class);
	}

	@Override
	public boolean canConnect(ForgeDirection direction)
	{
		if (direction == null || direction.equals(ForgeDirection.UNKNOWN))
		{
			return false;
		}

		return this.getInputDirections().contains(direction) || this.getOutputDirections().contains(direction);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		this.energy.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		this.energy.writeToNBT(nbt);
	}


	/**
	 * * @return Get the amount of energy currently stored in the block.
	 */
	
	@Override
	public long getEnergy(ForgeDirection from)
	{
		return this.energy.getEnergy();
	}



	/**
	 * @return Get the max amount of energy that can be stored in the block.
	 */
	
	@Override
	public long getEnergyCapacity(ForgeDirection from)
	{
		return this.energy.getEnergyCapacity();
	}

	/**
	 * Adds energy to a block. Returns the quantity of energy that was accepted. This should always
	 * return 0 if the block cannot be externally charged.
	 * 
	 * @param from Orientation the energy is sent in from.
	 * @param receive Maximum amount of energy (joules) to be sent into the block.
	 * @param doReceive If false, the charge will only be simulated.
	 * @return Amount of energy that was accepted by the block.
	 */
	
	@Override
	public long onReceiveEnergy(ForgeDirection from, long receive, boolean doReceive)
	{
		return this.energy.receiveEnergy(receive, doReceive);
	}

	/**
	 * Removes energy from a block. Returns the quantity of energy that was extracted. This should
	 * always return 0 if the block cannot be externally discharged.
	 * 
	 * @param from Orientation the energy is requested from. This direction MAY be passed as
	 * "Unknown" if it is wrapped from another energy system that has no clear way to find
	 * direction. (e.g BuildCraft 4)
	 * @param energy Maximum amount of energy to be sent into the block.
	 * @param doExtract If false, the charge will only be simulated.
	 * @return Amount of energy that was given out by the block.
	 */
	
	@Override
	public long onExtractEnergy(ForgeDirection from, long extract, boolean doExtract)
	{
		//return this.energy.extractEnergy(extract, doExtract);
		return 0;
	}

	/**
	 * Sets the amount of energy this unit stored.
	 * 
	 * This function is NOT recommended for calling.
	 */
	
	@Override
	public void setEnergy(ForgeDirection from, long energy)
	{
		this.energy.setEnergy(energy);
	}
	
	protected long produce(long outputEnergy)
	{
		long usedEnergy = 0;

		for (ForgeDirection direction : this.getOutputDirections())
		{
			if (outputEnergy > 0)
			{
				TileEntity tileEntity = new Vector3(this).modifyPositionFromSide(direction).getTileEntity(this.worldObj);

				if (tileEntity != null)
				{
					usedEnergy += CompatibilityModule.receiveEnergy(tileEntity, direction.getOpposite(), outputEnergy, true);
				}
			}
		}

		return usedEnergy;
	}
}
