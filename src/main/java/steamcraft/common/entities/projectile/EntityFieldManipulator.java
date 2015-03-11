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

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

import boilerplate.common.baseclasses.BaseThrowableEntity;

public class EntityFieldManipulator extends BaseThrowableEntity
{
	public EntityFieldManipulator(World world, EntityLivingBase entity)
	{
		super(world, entity);
	}

	public EntityFieldManipulator(World world)
	{
		super(world);
		this.setSize(0.5F, 0.5F);
	}

	@Override
	/**
	 * Gets the amount of gravity to apply to the thrown entity with each tick.
	 */
	protected float getGravityVelocity()
	{
		return 0.01F;
	}

	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		if(mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
		{
			for(int i = 0; i < 32; ++i)
			{
				this.worldObj.spawnParticle("portal", this.posX, this.posY + (this.rand.nextDouble() * 2.0D), this.posZ, this.rand.nextGaussian(), 0.0D,
						this.rand.nextGaussian());
			}

			if(!this.worldObj.isRemote)
			{
				if((this.getThrower() != null) && (this.getThrower() instanceof EntityPlayerMP))
				{
					EntityPlayerMP entityplayermp = (EntityPlayerMP) this.getThrower();

					if(entityplayermp.playerNetServerHandler.func_147362_b().isChannelOpen() && (entityplayermp.worldObj == this.worldObj))
					{
						EnderTeleportEvent event = new EnderTeleportEvent(entityplayermp, this.posX, this.posY, this.posZ, 5.0F);
						if(!MinecraftForge.EVENT_BUS.post(event))
						{
							if(this.getThrower().isRiding())
							{
								this.getThrower().mountEntity(null);
							}

							this.getThrower().setPositionAndUpdate(event.targetX, event.targetY, event.targetZ);
							this.getThrower().fallDistance = 0.0F;
						}
					}
				}

				this.setDead();
			}
		}
	}
}
