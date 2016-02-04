
package steamcraft.common.entities.projectile;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import boilerplate.common.baseclasses.BaseThrowableEntity;
import steamcraft.common.lib.DamageSourceHandler;

public class EntitySplashLightningBottle extends BaseThrowableEntity
{
	public EntitySplashLightningBottle(World world, EntityLivingBase entityLiving)
	{
		super(world, entityLiving);
	}

	public EntitySplashLightningBottle(World world)
	{
		super(world);
		this.setSize(0.5F, 0.5F);
	}

	@Override
	protected float getGravityVelocity()
	{
		return 0.03F;
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
		{
			if (!this.worldObj.isRemote)
			{
				AxisAlignedBB axisalignedbb = this.boundingBox.expand(4.0D, 2.0D, 4.0D);
				List list1 = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class, axisalignedbb);

				if ((list1 != null) && !list1.isEmpty())
				{
					for (Object obj : list1)
					{
						EntityLivingBase entitylivingbase = (EntityLivingBase) obj;
						double d0 = this.getDistanceSqToEntity(entitylivingbase);

						if (d0 < 16.0D)
						{
							double d1 = 1.0D - (Math.sqrt(d0) / 4.0D);

							if (entitylivingbase == mop.entityHit)
							{
								d1 = 1.0D;
							}
							entitylivingbase.attackEntityFrom(DamageSourceHandler.electrocution, 4);

							if (entitylivingbase instanceof EntityCreeper)
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
