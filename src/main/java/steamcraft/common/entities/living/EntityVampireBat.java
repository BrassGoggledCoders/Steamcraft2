package steamcraft.common.entities.living;

import java.util.Calendar;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityVampireBat extends EntityMob
{
	/** Coordinates of where the bat spawned. */
	private ChunkCoordinates spawnPosition;

	public EntityVampireBat(World p_i1680_1_)
	{
		super(p_i1680_1_);
		this.setSize(0.6F, 1F);
		this.setIsBatHanging(true);
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, 0, false));
	}

	@Override
	public void onLivingUpdate()
	{
		if(this.worldObj.isDaytime() && !this.worldObj.isRemote && !this.isChild())
		{
			float f = this.getBrightness(1.0F);

			if((f > 0.5F)
					&& ((this.rand.nextFloat() * 30.0F) < ((f - 0.4F) * 2.0F))
					&& this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY),
							MathHelper.floor_double(this.posZ)))
			{
				this.setFire(8);
			}
		}
		super.onLivingUpdate();
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataWatcher.addObject(16, new Byte((byte) 0));
	}

	/**
	 * Returns the volume for the sounds this mob makes.
	 */
	@Override
	protected float getSoundVolume()
	{
		return 0.1F;
	}

	/**
	 * Gets the pitch of living sounds in living entities.
	 */
	@Override
	protected float getSoundPitch()
	{
		return super.getSoundPitch() * 0.95F;
	}

	/**
	 * Returns the sound this mob makes while it's alive.
	 */
	@Override
	protected String getLivingSound()
	{
		return this.getIsBatHanging() && (this.rand.nextInt(4) != 0) ? null : "mob.bat.idle";
	}

	/**
	 * Returns the sound this mob makes when it is hurt.
	 */
	@Override
	protected String getHurtSound()
	{
		return "mob.bat.hurt";
	}

	/**
	 * Returns the sound this mob makes on death.
	 */
	@Override
	protected String getDeathSound()
	{
		return "mob.bat.death";
	}

	/**
	 * Returns true if this entity should push and be pushed by other entities when colliding.
	 */
	@Override
	public boolean canBePushed()
	{
		return false;
	}

	@Override
	protected void collideWithEntity(Entity p_82167_1_)
	{
	}

	@Override
	protected void collideWithNearbyEntities()
	{
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(6.0D);
	}

	public boolean getIsBatHanging()
	{
		return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
	}

	public void setIsBatHanging(boolean p_82236_1_)
	{
		byte b0 = this.dataWatcher.getWatchableObjectByte(16);

		if(p_82236_1_)
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 | 1)));
		}
		else
		{
			this.dataWatcher.updateObject(16, Byte.valueOf((byte) (b0 & -2)));
		}
	}

	/**
	 * Returns true if the newer Entity AI code should be run
	 */
	@Override
	protected boolean isAIEnabled()
	{
		return true;
	}

	/**
	 * Called to update the entity's position/logic.
	 */
	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if(this.getIsBatHanging())
		{
			this.motionX = this.motionY = this.motionZ = 0.0D;
			this.posY = (MathHelper.floor_double(this.posY) + 1.0D) - this.height;
		}
		else
		{
			this.motionY *= 0.6000000238418579D;
		}
	}

	@Override
	protected void updateAITasks()
	{
		super.updateAITasks();

		if(this.getIsBatHanging())
		{
			if(!this.worldObj.getBlock(MathHelper.floor_double(this.posX), (int) this.posY + 1, MathHelper.floor_double(this.posZ)).isNormalCube())
			{
				this.setIsBatHanging(false);
				this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1015, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
			}
			else
			{
				if(this.rand.nextInt(200) == 0)
				{
					this.rotationYawHead = this.rand.nextInt(360);
				}

				if(this.worldObj.getClosestPlayerToEntity(this, 4.0D) != null)
				{
					this.setIsBatHanging(false);
					this.worldObj.playAuxSFXAtEntity((EntityPlayer) null, 1015, (int) this.posX, (int) this.posY, (int) this.posZ, 0);
				}
			}
		}
		else
		{
			if((this.spawnPosition != null)
					&& (!this.worldObj.isAirBlock(this.spawnPosition.posX, this.spawnPosition.posY, this.spawnPosition.posZ) || (this.spawnPosition.posY < 1)))
			{
				this.spawnPosition = null;
			}

			if((this.spawnPosition == null) || (this.rand.nextInt(30) == 0)
					|| (this.spawnPosition.getDistanceSquared((int) this.posX, (int) this.posY, (int) this.posZ) < 4.0F))
			{
				this.spawnPosition = new ChunkCoordinates(((int) this.posX + this.rand.nextInt(7)) - this.rand.nextInt(7),
						((int) this.posY + this.rand.nextInt(6))
						- 2, ((int) this.posZ + this.rand.nextInt(7)) - this.rand.nextInt(7));
			}

			double d0 = (this.spawnPosition.posX + 0.5D) - this.posX;
			double d1 = (this.spawnPosition.posY + 0.1D) - this.posY;
			double d2 = (this.spawnPosition.posZ + 0.5D) - this.posZ;
			this.motionX += ((Math.signum(d0) * 0.5D) - this.motionX) * 0.10000000149011612D;
			this.motionY += ((Math.signum(d1) * 0.699999988079071D) - this.motionY) * 0.10000000149011612D;
			this.motionZ += ((Math.signum(d2) * 0.5D) - this.motionZ) * 0.10000000149011612D;
			float f = (float) ((Math.atan2(this.motionZ, this.motionX) * 180.0D) / Math.PI) - 90.0F;
			float f1 = MathHelper.wrapAngleTo180_float(f - this.rotationYaw);
			this.moveForward = 0.5F;
			this.rotationYaw += f1;

			if((this.rand.nextInt(100) == 0)
					&& this.worldObj.getBlock(MathHelper.floor_double(this.posX), (int) this.posY + 1, MathHelper.floor_double(this.posZ)).isNormalCube())
			{
				this.setIsBatHanging(true);
			}
		}
	}

	/**
	 * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to prevent them from trampling crops
	 */
	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	/**
	 * Called when the mob is falling. Calculates and applies fall damage.
	 */
	@Override
	protected void fall(float p_70069_1_)
	{
	}

	/**
	 * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance and deal fall damage if landing on the
	 * ground. Args: distanceFallenThisTick, onGround
	 */
	@Override
	protected void updateFallState(double p_70064_1_, boolean p_70064_3_)
	{
	}

	/**
	 * Return whether this entity should NOT trigger a pressure plate or a tripwire.
	 */
	@Override
	public boolean doesEntityNotTriggerPressurePlate()
	{
		return true;
	}

	/**
	 * Called when the entity is attacked.
	 */
	@Override
	public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_)
	{
		if(this.isEntityInvulnerable())
		{
			return false;
		}
		else
		{
			if(!this.worldObj.isRemote && this.getIsBatHanging())
			{
				this.setIsBatHanging(false);
			}

			return super.attackEntityFrom(p_70097_1_, p_70097_2_);
		}
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound p_70037_1_)
	{
		super.readEntityFromNBT(p_70037_1_);
		this.dataWatcher.updateObject(16, Byte.valueOf(p_70037_1_.getByte("BatFlags")));
	}

	/**
	 * (abstract) Protected helper method to write subclass entity data to NBT.
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound p_70014_1_)
	{
		super.writeEntityToNBT(p_70014_1_);
		p_70014_1_.setByte("BatFlags", this.dataWatcher.getWatchableObjectByte(16));
	}

	/**
	 * Checks if the entity's current position is a valid location to spawn this entity.
	 */
	@Override
	public boolean getCanSpawnHere()
	{
		int i = MathHelper.floor_double(this.boundingBox.minY);

		if(i >= 63)
		{
			return false;
		}
		else
		{
			int j = MathHelper.floor_double(this.posX);
			int k = MathHelper.floor_double(this.posZ);
			int l = this.worldObj.getBlockLightValue(j, i, k);
			byte b0 = 4;
			Calendar calendar = this.worldObj.getCurrentDate();

			if((((calendar.get(2) + 1) != 10) || (calendar.get(5) < 20)) && (((calendar.get(2) + 1) != 11) || (calendar.get(5) > 3)))
			{
				if(this.rand.nextBoolean())
				{
					return false;
				}
			}
			else
			{
				b0 = 7;
			}

			return l > this.rand.nextInt(b0) ? false : super.getCanSpawnHere();
		}
	}
}
