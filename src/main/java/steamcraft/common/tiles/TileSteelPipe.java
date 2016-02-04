
package steamcraft.common.tiles;

import net.minecraft.tileentity.TileEntity;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.IFluidHandler;

/**
 * @author decebaldecebal
 *
 */
public class TileSteelPipe extends TileCopperPipe
{
	private static int steelPipeCapacity = 1000;
	private static int steelPipeExtract = 200;
	private static int steelPipeTransfer = 800;

	public TileSteelPipe()
	{
		super();
		this.pipeCapacity = steelPipeCapacity;
		this.pipeExtract = steelPipeExtract;
		this.pipeTransfer = steelPipeTransfer;
	}

	@Override
	public boolean isPipe(ForgeDirection dir)
	{
		return this.isSteelPipe(dir);
	}

	@Override
	protected boolean isFluidHandler(ForgeDirection dir)
	{
		TileEntity te = this.worldObj.getTileEntity(this.xCoord + dir.offsetX, this.yCoord + dir.offsetY, this.zCoord + dir.offsetZ);
		return (te instanceof IFluidHandler) && (((IFluidHandler) te).getTankInfo(dir.getOpposite()) != null) && !this.isSteelPipe(dir)
				&& !super.isPipe(dir);
	}
}
