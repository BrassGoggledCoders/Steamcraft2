/*
 * 
 */
package steamcraft.common.tiles;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import steamcraft.common.lib.DamageSourceHandler;

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
