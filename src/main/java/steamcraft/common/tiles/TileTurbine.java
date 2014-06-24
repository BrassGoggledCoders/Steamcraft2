package steamcraft.common.tiles;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

public class TileTurbine extends TileEntity implements IEnergyHandler
{
	 protected EnergyStorage storage = new EnergyStorage(10000);
	@Override
	public void updateEntity()
	{

	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return 0;
	}

	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
	  return this.storage.extractEnergy(Math.min(transferLimit(), maxExtract), simulate);
	}

	public int getEnergyStored(ForgeDirection from)
	  {
	    return this.storage.getEnergyStored();
	  }


	  public int getMaxEnergyStored(ForgeDirection from)
	  {
	    return this.storage.getMaxEnergyStored();
	  }
	  public int transferLimit()
	  {
	    return this.storage.getMaxEnergyStored();
	  }

}
