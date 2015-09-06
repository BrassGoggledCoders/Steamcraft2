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
