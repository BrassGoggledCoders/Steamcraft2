package common.steamcraft.mod.common.block.tile;

import ic2.api.energy.tile.IEnergySource;

import java.util.EnumSet;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.power.IPowerEmitter;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;
import cofh.api.energy.IEnergyHandler;

import common.steamcraft.mod.common.core.helper.CompatHelper;
import common.steamcraft.mod.common.util.EnergyUtils;

/** 
 * Base class for power generators compatible with different power systems.
 * 
 * @author Decebaldecebal
 * 
 */
public class TileEntityElectricGenerator extends TileEntityElectricMachine implements IPowerEmitter, IEnergySource
{	
	public TileEntityElectricGenerator()
	{
		super();
		this.energy = new EnergyUtils(40000, 128);
		inventory = new ItemStack[3];
	}
	
	public EnumSet<ForgeDirection> getInputDirections()
	{
		return EnumSet.noneOf(ForgeDirection.class);
	}
	
	@Override
	public EnumSet<ForgeDirection> getOutputDirections()
	{
		return EnumSet.allOf(ForgeDirection.class);
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (!worldObj.isRemote)
		{			
			if(inventory[2]!=null && energy.getEmptySpace() > 0)
				this.discharge(2, this);
			
			if(this.getEnergy()!=0)
			{
				if(inventory[1]!=null)
					this.charge(1, this);
				//this.energy.modifyStoredEnergy(-this.produce(this.energy.getTransferRate()));
			}
		}
	}
	
	protected int produce(int outputEnergy)
	{
		int usedEnergy = 0;

		for (ForgeDirection direction : this.getOutputDirections())
		{
			if (outputEnergy > 0)
			{
				TileEntity tileEntity = worldObj.getBlockTileEntity(xCoord - direction.offsetX, yCoord - direction.offsetY, zCoord - direction.offsetZ);

				if (tileEntity != null)
				{
					//TE
					if(tileEntity instanceof IEnergyHandler)
					{
						usedEnergy += ((IEnergyHandler) tileEntity).receiveEnergy(direction.getOpposite(), outputEnergy, false);
					}
					//BC
					else if(CompatHelper.BuildCraftLoaded && tileEntity instanceof IPowerReceptor)
					{	
						IPowerReceptor tile = (IPowerReceptor)tileEntity;
						if(tile!=null)
						{
							PowerReceiver receiver = tile.getPowerReceiver(direction.getOpposite());
							if(receiver!=null && receiver.powerRequest() > 0)
							{
								usedEnergy += receiver.receiveEnergy(Type.STORAGE, EnergyUtils.toBC(outputEnergy), direction.getOpposite());
							}
						}
					}
				}
			}
		}

		return usedEnergy;
	}
	
	/**
	 * 
	 * BuildCraft
	 * 
	 */
	@Override
	public boolean canEmitPowerFrom(ForgeDirection side)
	{
		return true;
	}

	/**
	 * 
	 * IC2
	 * 
	 */
	
	@Override
	public boolean emitsEnergyTo(TileEntity receiver, ForgeDirection from) 
	{
		return this.getOutputDirections().contains(from);
	}

	@Override
	public double getOfferedEnergy() 
	{
		if(this.energy.getStoredEnergy() >= this.energy.getTransferRate())
			return EnergyUtils.toIC2(this.energy.getTransferRate());
		return 0;
	}

	@Override
	public void drawEnergy(double amount) 
	{
		this.energy.modifyStoredEnergy(-EnergyUtils.fromIC2((int)amount));
	}
	
	@Override
	public double demandedEnergyUnits()
	{
		return 0;
	}
	
	@Override
	public int getMaxSafeInput()
	{
		return 0;
	}
}
