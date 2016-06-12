
package steamcraft.common.tiles.energy;

import cofh.api.energy.IEnergyConnection;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author decebaldecebal
 *
 */
public class TileSteelWire extends TileCopperWire
{
	private static int steelWireCapacity = 40000;
	private static int steelWireTransfer = 10000;

	public TileSteelWire()
	{
		super();
		this.wireCapacity = steelWireCapacity;
		this.wireTransfer = steelWireTransfer;
	}

	@Override
	protected boolean isWire(ForgeDirection dir)
	{
		return this.isSteelWire(dir);
	}

	@Override
	protected boolean isEnergyHandler(ForgeDirection dir)
	{
		return (this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY,
				this.zCoord + dir.offsetZ) instanceof IEnergyConnection)
				&& ((IEnergyConnection) this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ))
						.canConnectEnergy(dir.getOpposite())
				&& !this.isSteelWire(dir) && !super.isWire(dir);
	}
}
