
package steamcraft.common.entities.projectile;

import boilerplate.common.baseclasses.BaseThrowableEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityMobBottle extends BaseThrowableEntity
{
	public EntityMobBottle(World p_i1777_1_, EntityLivingBase p_i1777_2_, NBTBase nbtBase)
	{
		super(p_i1777_1_, p_i1777_2_);
	}

	public EntityMobBottle(World world)
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
	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		if (mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
		{
			if (!this.worldObj.isRemote)
			{

				this.worldObj.playAuxSFX(2002, (int) Math.round(this.posX), (int) Math.round(this.posY), (int) Math.round(this.posZ), 0);
				this.setDead();
			}
		}
	}
}
