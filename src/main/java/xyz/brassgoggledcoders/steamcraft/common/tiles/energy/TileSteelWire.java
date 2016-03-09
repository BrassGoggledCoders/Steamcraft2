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
package xyz.brassgoggledcoders.steamcraft.common.tiles.energy;

import net.minecraftforge.common.util.ForgeDirection;

import cofh.api.energy.IEnergyConnection;

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
