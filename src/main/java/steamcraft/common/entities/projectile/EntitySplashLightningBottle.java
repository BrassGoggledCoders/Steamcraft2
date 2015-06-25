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
package steamcraft.common.entities.projectile;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import steamcraft.common.lib.DamageSourceHandler;
import boilerplate.common.baseclasses.BaseThrowableEntity;

public class EntitySplashLightningBottle extends BaseThrowableEntity
{
	public EntitySplashLightningBottle(World p_i1777_1_, EntityLivingBase p_i1777_2_)
	{
		super(p_i1777_1_, p_i1777_2_);
	}

	public EntitySplashLightningBottle(World world)
	{
		super(world);
		this.setSize(0.5F, 0.5F);
	}

	/**
	 * Gets the amount of gravity to apply to the thrown entity with each tick.
	 */
	@Override
	protected float getGravityVelocity()
	{
		return 0.03F;
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		if(mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
		{
			if(!this.worldObj.isRemote)
			{
				AxisAlignedBB axisalignedbb = this.boundingBox.expand(4.0D, 2.0D, 4.0D);
				List list1 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);

				if((list1 != null) && !list1.isEmpty())
				{
					for(Object obj : list1)
					{
						EntityLivingBase entitylivingbase = (EntityLivingBase) obj;
						double d0 = this.getDistanceSqToEntity(entitylivingbase);

						if(d0 < 16.0D)
						{
							double d1 = 1.0D - (Math.sqrt(d0) / 4.0D);

							if(entitylivingbase == mop.entityHit)
							{
								d1 = 1.0D;
							}
							entitylivingbase.attackEntityFrom(DamageSourceHandler.electrocution, 4);

							if(entitylivingbase instanceof EntityCreeper)
							{
								EntityCreeper creeper = (EntityCreeper) entitylivingbase;
								creeper.getDataWatcher().updateObject(17, Byte.valueOf((byte) (1)));
							}
						}
					}
				}

				this.worldObj.playAuxSFX(2002, (int) Math.round(this.posX), (int) Math.round(this.posY), (int) Math.round(this.posZ), 0);
				this.setDead();
			}
		}
	}
}
