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

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import steamcraft.common.lib.DamageSourceHandler;

/**
 * @author warlordjones
 *
 */
public class TileTeslaCoil extends TileEntity
{
	@Override
	public void updateEntity()
	{
		AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox((double)xCoord - 5, (double)yCoord - 5, (double)zCoord - 5, (double)(xCoord + 5), (double)(yCoord + 5), (double)(zCoord + 5));
        List list = worldObj.getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb);
        Iterator iterator = list.iterator();
        EntityPlayer entityplayer;

        while (iterator.hasNext())
        {
            entityplayer = (EntityPlayer)iterator.next();
            entityplayer.attackEntityFrom(DamageSourceHandler.electrocution, 4);
            worldObj.spawnParticle("reddust", entityplayer.posX, entityplayer.posY, entityplayer.posZ, 0, 0, 0);
        }
	}
}
