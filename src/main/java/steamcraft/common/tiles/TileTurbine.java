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

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate)
	{
		return storage.extractEnergy(Math.min(transferLimit(), maxExtract), simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return storage.getMaxEnergyStored();
	}

	public int transferLimit()
	{
		return storage.getMaxEnergyStored();
	}

}
