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
import java.util.Random;

import net.minecraft.entity.EntityLiving;
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
        List list = worldObj.getEntitiesWithinAABB(EntityLiving.class, axisalignedbb);
        Iterator iterator = list.iterator();
        EntityLiving living;

        while (iterator.hasNext())
        {
            living = (EntityLiving)iterator.next();
            living.attackEntityFrom(DamageSourceHandler.electrocution, 3);
            Random random = new Random();
            worldObj.spawnParticle("reddust", living.posX + random.nextFloat()/2, living.posY + random.nextFloat()/2, living.posZ + random.nextFloat()/2, 0, 0, 0);
        }
        AxisAlignedBB axisalignedbb2 = AxisAlignedBB.getBoundingBox((double)xCoord - 5, (double)yCoord - 5, (double)zCoord - 5, (double)(xCoord + 5), (double)(yCoord + 5), (double)(zCoord + 5));
        List list2 = worldObj.getEntitiesWithinAABB(EntityPlayer.class, axisalignedbb2);
        Iterator iterator2 = list2.iterator();
        EntityPlayer player;

        while (iterator2.hasNext())
        {
    		player = (EntityPlayer)iterator2.next();
    		player.attackEntityFrom(DamageSourceHandler.electrocution, 3);
    		Random random = new Random();
    		worldObj.spawnParticle("reddust", player.posX + random.nextFloat()/2, player.posY-0.5F + random.nextFloat()/2, player.posZ + random.nextFloat()/2, 0, 0, 0);
        }
	}
}
