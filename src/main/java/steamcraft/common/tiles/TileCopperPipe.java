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

import steamcraft.common.config.ConfigBlocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

/**
 * @author Decebaldecebal
 * 
 */
public class TileCopperPipe extends TileEntity
{
	public ForgeDirection[] connections = new ForgeDirection[6];

	@Override
	public void updateEntity()
	{
		//updateConnections();
	}
	
	public void updateConnections()
	{
		if(worldObj.getBlock(xCoord, yCoord + 1, zCoord) == ConfigBlocks.blockCopperPipe)
			connections[0] = ForgeDirection.UP;
		else
			connections[0] = null;
		
		if(worldObj.getTileEntity(xCoord, yCoord - 1, zCoord) instanceof TileCopperPipe)
			connections[1] = ForgeDirection.DOWN;
		else
			connections[1] = null;
		
		if(worldObj.getTileEntity(xCoord, yCoord, zCoord - 1) instanceof TileCopperPipe)
			connections[2] = ForgeDirection.NORTH;
		else
			connections[2] = null;
		
		if(worldObj.getTileEntity(xCoord, yCoord, zCoord + 1) instanceof TileCopperPipe)
			connections[3] = ForgeDirection.SOUTH;
		else
			connections[3] = null;
		
		if(worldObj.getTileEntity(xCoord + 1, yCoord, zCoord) instanceof TileCopperPipe)
			connections[4] = ForgeDirection.EAST;
		else
			connections[4] = null;
		
		if(worldObj.getTileEntity(xCoord - 1, yCoord, zCoord) instanceof TileCopperPipe)
			connections[5] = ForgeDirection.WEST;
		else
			connections[5] = null;
	}
}
