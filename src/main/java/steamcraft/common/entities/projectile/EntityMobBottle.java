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
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import boilerplate.common.baseclasses.BaseThrowableEntity;

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
		if(mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
		{
			if(!this.worldObj.isRemote)
			{

				this.worldObj.playAuxSFX(2002, (int) Math.round(this.posX), (int) Math.round(this.posY), (int) Math.round(this.posZ), 0);
				this.setDead();
			}
		}
	}
}
