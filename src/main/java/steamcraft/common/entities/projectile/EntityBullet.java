/**
 * This class was created by <Surseance> or his SC2 development team.
 * This class is available as part of the Steamcraft 2 Mod for Minecraft.
 *
 * Steamcraft 2 is open-source and is distributed under the MMPL v1.0 License.
 * (http://www.mod-buildcraft.com/MMPL-1.0.txt)
 *
 * Steamcraft 2 is based on the original Steamcraft Mod created by Proloe.
 * Steamcraft (c) Proloe 2011
 * (http://www.minecraftforum.net/topic/251532-181-steamcraft-source-code-releasedmlv054wip/)
 *
 * File created @ [Apr 8, 2014, 1:42:11 PM]
 */
package steamcraft.common.entities.projectile;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

// TODO: Auto-generated Javadoc
/**
 * NOTE: Please do not tamper with this file unless you know vectors and
 * trigonometric functions very well. Thank you. I have, however, cleaned up the
 * code to make things easier to understand.
 *
 * @author Surseance (Johnny Eatmon)
 *
 */
public class EntityBullet extends Entity implements IProjectile
{

	/** The x tile. */
	private int xTile = -1;

	/** The y tile. */
	private int yTile = -1;

	/** The z tile. */
	private int zTile = -1;

	/** The in tile. */
	private Block inTile;

	/** The in ground. */
	private boolean inGround;

	/** The can be picked up. */
	private int canBePickedUp;

	/** The arrow shake. */
	public int arrowShake;

	/** The shooting entity. */
	private Entity shootingEntity;

	/** The time till death. */
	private int timeTillDeath;

	/** The fly time. */
	private int flyTime;

	/** The damage. */
	private int damage;

	/** The accuracy. */
	private int accuracy;

	/**
	 * Instantiates a new entity bullet.
	 *
	 * @param world
	 *            the world
	 */
	public EntityBullet(World world)
	{
		super(world);
		renderDistanceWeight = 10.0D;
		setSize(0.5F, 0.5F);
	}

	/**
	 * Instantiates a new entity bullet.
	 *
	 * @param world
	 *            the world
	 * @param dx
	 *            the dx
	 * @param dy
	 *            the dy
	 * @param dz
	 *            the dz
	 */
	public EntityBullet(World world, double dx, double dy, double dz)
	{
		super(world);
		renderDistanceWeight = 10.0D;
		setSize(0.5F, 0.5F);
		setPosition(dx, dy, dz);
		yOffset = 0.0F;
	}

	/**
	 * Instantiates a new entity bullet.
	 *
	 * @param world
	 *            the world
	 * @param shooter
	 *            the shooter
	 * @param target
	 *            the target
	 * @param frotY
	 *            the frot y
	 * @param frotP
	 *            the frot p
	 */
	public EntityBullet(World world, EntityLivingBase shooter, EntityLivingBase target, float frotY, float frotP)
	{
		super(world);
		renderDistanceWeight = 10.0D;
		shootingEntity = shooter;

		if (shooter instanceof EntityPlayer)
			canBePickedUp = 1;

		posY = (shooter.posY + shooter.getEyeHeight()) - 0.10000000149011612D;
		double dx = target.posX - shooter.posX;
		double dy = (target.boundingBox.minY + (target.height / 3.0F)) - posY;
		double dz = target.posZ - shooter.posZ;
		double magnitude = MathHelper.sqrt_double((dx * dx) + (dz * dz)); // It's
																			// a
																			// magnitude
																			// vector!
																			// Yay,
																			// math!

		if (magnitude >= 1.0E-7D)
		{
			float fx = (float) ((Math.atan2(dz, dx) * 180.0D) / Math.PI) - 90.0F;
			float fy = (float) (-((Math.atan2(dy, magnitude) * 180.0D) / Math.PI));
			double dlx = dx / magnitude;
			double dlz = dz / magnitude;
			setLocationAndAngles(shooter.posX + dlx, posY, shooter.posZ + dlz, fx, fy);
			yOffset = 0.0F;
			float height = (float) magnitude * 0.2F;
			setThrowableHeading(dx, dy + height, dz, frotY, frotP);
		}
	}

	/**
	 * Instantiates a new entity bullet.
	 *
	 * @param world
	 *            the world
	 * @param shooter
	 *            the shooter
	 * @param damage
	 *            the damage
	 * @param accuracy
	 *            the accuracy
	 */
	public EntityBullet(World world, EntityLivingBase shooter, int damage, int accuracy)
	{
		super(world);
		renderDistanceWeight = 10.0D;
		shootingEntity = shooter;

		if (shooter instanceof EntityPlayer)
			canBePickedUp = 1;

		setSize(0.5F, 0.5F);
		setLocationAndAngles(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
		posX -= MathHelper.cos((rotationYaw / 180.0F) * (float) Math.PI) * 0.16F;
		posY -= 0.10000000149011612D;
		posZ -= MathHelper.sin((rotationYaw / 180.0F) * (float) Math.PI) * 0.16F;
		setPosition(posX, posY, posZ);
		yOffset = 0.0F;
		motionX = -MathHelper.sin((rotationYaw / 180.0F) * (float) Math.PI) * MathHelper.cos((rotationPitch / 180.0F) * (float) Math.PI);
		motionZ = MathHelper.cos((rotationYaw / 180.0F) * (float) Math.PI) * MathHelper.cos((rotationPitch / 180.0F) * (float) Math.PI);
		motionY = (-MathHelper.sin((rotationPitch / 180.0F) * (float) Math.PI));
		setThrowableHeading(motionX, motionY, motionZ, 1F, accuracy);
		this.accuracy = accuracy;
		this.damage = damage;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.entity.Entity#entityInit()
	 */
	@Override
	protected void entityInit()
	{
		// dataWatcher.addObject(Config.entBulletId, Byte.valueOf((byte) 0));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.entity.IProjectile#setThrowableHeading(double, double,
	 * double, float, float)
	 */
	@Override
	public void setThrowableHeading(double dx, double dy, double dz, float frotY, float frotP)
	{
		int accuracy = this.accuracy;
		float f2 = MathHelper.sqrt_double((dx * dx) + (dy * dy) + (dz * dz));
		dx /= f2;
		dy /= f2;
		dz /= f2;
		dx += (rand.nextGaussian() * 0.0034999998323619365D * frotP * accuracy) / 5;
		dy += (rand.nextGaussian() * 0.0034999998323619365D * frotP * accuracy) / 5;
		dz += (rand.nextGaussian() * 0.0034999998323619365D * frotP * accuracy) / 5;
		dx *= frotY;
		dy *= frotY;
		dz *= frotY;
		motionX = dx;
		motionY = dy;
		motionZ = dz;
		float magnitude = MathHelper.sqrt_double((dx * dx) + (dz * dz));
		prevRotationYaw = rotationYaw = (float) ((Math.atan2(dx, dz) * 180D) / 3.1415927410125732D);
		prevRotationPitch = rotationPitch = (float) ((Math.atan2(dy, magnitude) * 180D) / 3.1415927410125732D);
		timeTillDeath = 0;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.entity.Entity#setPositionAndRotation2(double, double,
	 * double, float, float, int)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double dx, double dy, double dz, float frotY, float frotP, int i)
	{
		setPosition(dx, dy, dz);
		setRotation(frotY, frotP);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.entity.Entity#setVelocity(double, double, double)
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public void setVelocity(double dx, double dy, double dz)
	{
		motionX = dx;
		motionY = dy;
		motionZ = dz;

		if ((prevRotationPitch == 0.0F) && (prevRotationYaw == 0.0F))
		{
			float magnitude = MathHelper.sqrt_double((dx * dx) + (dz * dz));
			prevRotationYaw = rotationYaw = (float) ((Math.atan2(dx, dz) * 180D) / 3.1415927410125732D);
			prevRotationPitch = rotationPitch = (float) ((Math.atan2(dy, magnitude) * 180D) / 3.1415927410125732D);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.entity.Entity#onUpdate()
	 */
	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (flyTime > 1000)
			setDead();

		if ((prevRotationPitch == 0.0F) && (prevRotationYaw == 0.0F))
		{
			float magnitude = MathHelper.sqrt_double((motionX * motionX) + (motionZ * motionZ));
			prevRotationYaw = rotationYaw = (float) ((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
			prevRotationPitch = rotationPitch = (float) ((Math.atan2(motionY, magnitude) * 180D) / 3.1415927410125732D);
		}

		if (arrowShake > 0)
			arrowShake--;

		if (inGround)
		{
			Block block = worldObj.getBlock(xTile, yTile, zTile);

			if (block != inTile)
			{
				inGround = false;
				motionX *= rand.nextFloat() * 0.2F;
				motionY *= rand.nextFloat() * 0.2F;
				motionZ *= rand.nextFloat() * 0.2F;
				timeTillDeath = 0;
				flyTime = 0;
			}
			else
			{
				timeTillDeath++;

				if (timeTillDeath == 1200)
					setDead();

				return;
			}
		}
		else
			flyTime++;

		Vec3 posVector = worldObj.getWorldVec3Pool().getVecFromPool(posX, posY, posZ);
		Vec3 velVector = worldObj.getWorldVec3Pool().getVecFromPool(posX + motionX, posY + motionY, posZ + motionZ);
		MovingObjectPosition mop = worldObj.rayTraceBlocks(posVector, velVector, false); // TODO:
																							// missing
																							// a
																							// true,
																							// check
																							// how
																							// this
																							// is
																							// affected
		posVector = worldObj.getWorldVec3Pool().getVecFromPool(posX, posY, posZ);
		velVector = worldObj.getWorldVec3Pool().getVecFromPool(posX + motionX, posY + motionY, posZ + motionZ);

		if (mop != null)
			velVector = worldObj.getWorldVec3Pool().getVecFromPool(mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord);

		Entity entity = null;
		List<?> entList = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ)
				.expand(1.0D, 1.0D, 1.0D));
		double distance = 0.0D;

		for (int listSize = 0; listSize < entList.size(); listSize++)
		{
			Entity collidableEnt = (Entity) entList.get(listSize);

			if (!collidableEnt.canBeCollidedWith() || ((collidableEnt == shootingEntity) && (flyTime < 5)))
				continue;

			float amount = 0.3F;
			AxisAlignedBB aaBB = collidableEnt.boundingBox.expand(amount, amount, amount);
			MovingObjectPosition objectInVector = aaBB.calculateIntercept(posVector, velVector);

			if (objectInVector == null)
				continue;

			double distanceToObject = posVector.distanceTo(objectInVector.hitVec);

			if ((distanceToObject < distance) || (distance == 0.0D)) // Orthogonal
																		// check
			{
				entity = collidableEnt;
				distance = distanceToObject;
			}
		}

		if (entity != null)
			mop = new MovingObjectPosition(entity);

		if (mop != null)
			if (mop.entityHit != null)
			{
				if (mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this), damage))
				{
					worldObj.playSoundAtEntity(this, LibInfo.PREFIX + "hitflesh", 1.0F, 1.2F / ((rand.nextFloat() * 0.2F) + 0.9F));
					setDead();
				}
				else
				{
					motionX *= 0.10000000149011612D;
					motionY *= 0.10000000149011612D;
					motionZ *= 0.10000000149011612D;
					flyTime = 0;
					setDead();
				}
			}
			else
			{
				xTile = mop.blockX;
				yTile = mop.blockY;
				zTile = mop.blockZ;
				inTile = worldObj.getBlock(xTile, yTile, zTile);

				if ((inTile == Blocks.glass) || (inTile == Blocks.glowstone))
				{
					Block block = inTile;
					worldObj.playSoundEffect(xTile + 0.5D, yTile + 0.5D, zTile + 0.5D, block.stepSound.getBreakSound(), 1.0F, 1.0F);
					worldObj.setBlockToAir(xTile, yTile, zTile);
				}
				else
				{
					motionX = (float) (mop.hitVec.xCoord - posX);
					motionY = (float) (mop.hitVec.yCoord - posY);
					motionZ = (float) (mop.hitVec.zCoord - posZ);
					float magnitude = MathHelper.sqrt_double((motionX * motionX) + (motionY * motionY) + (motionZ * motionZ));
					posX -= (motionX / magnitude) * 0.05000000074505806D;
					posY -= (motionY / magnitude) * 0.05000000074505806D;
					posZ -= (motionZ / magnitude) * 0.05000000074505806D;
					worldObj.playSoundAtEntity(this, LibInfo.PREFIX + "hitblock", 1.0F, 1.0F);
					setDead();
				}
			}

		posX += motionX * 3.0D;
		posY += motionY * 3.0D;
		posZ += motionZ * 3.0D;
		float magnitude = MathHelper.sqrt_double((motionX * motionX) + (motionZ * motionZ));
		rotationYaw = (float) ((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);

		for (rotationPitch = (float) ((Math.atan2(motionY, magnitude) * 180D) / 3.1415927410125732D); (rotationPitch - prevRotationPitch) < -180F; prevRotationPitch -= 360F)
		{
		}

		for (; (rotationPitch - prevRotationPitch) >= 180F; prevRotationPitch += 360F)
		{
		}

		for (; (rotationYaw - prevRotationYaw) < -180F; prevRotationYaw -= 360F)
		{
		}

		for (; (rotationYaw - prevRotationYaw) >= 180F; prevRotationYaw += 360F)
		{
		}

		rotationPitch = prevRotationPitch + ((rotationPitch - prevRotationPitch) * 0.2F);
		rotationYaw = prevRotationYaw + ((rotationYaw - prevRotationYaw) * 0.2F);
		float speed = 0.99F;

		if (handleWaterMovement())
			setDead();
		// Bullets can't go through water, silly!
		// for(int k = 0; k < 4; k++)
		// {
		// float f6 = 0.25F;
		// worldObj.spawnParticle("bubble", posX - motionX * (double)f6,
		// posY - motionY * (double)f6, posZ - motionZ * (double)f6,
		// motionX, motionY, motionZ);
		// }
		// f3 = 0.8F;

		motionX *= speed;
		motionY *= speed;
		motionZ *= speed;
		setPosition(posX, posY, posZ);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * net.minecraft.entity.Entity#writeEntityToNBT(net.minecraft.nbt.NBTTagCompound
	 * )
	 */
	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		tagCompound.setShort("xTile", (short) xTile);
		tagCompound.setShort("yTile", (short) yTile);
		tagCompound.setShort("zTile", (short) zTile);
		// tagCompound.setByte("inTile", (byte)this.inTile.getI);
		tagCompound.setByte("shake", (byte) arrowShake);
		tagCompound.setByte("inGround", (byte) (inGround ? 1 : 0));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.entity.Entity#readEntityFromNBT(net.minecraft.nbt.
	 * NBTTagCompound)
	 */
	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound)
	{
		xTile = tagCompound.getShort("xTile");
		yTile = tagCompound.getShort("yTile");
		zTile = tagCompound.getShort("zTile");
		// this.inTile = tagCompound.getByte("inTile") & 0xff;
		arrowShake = tagCompound.getByte("shake") & 0xff;
		inGround = tagCompound.getByte("inGround") == 1;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.entity.Entity#canTriggerWalking()
	 */
	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.entity.Entity#getShadowSize()
	 */
	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see net.minecraft.entity.Entity#canAttackWithItem()
	 */
	@Override
	public boolean canAttackWithItem()
	{
		return false;
	}
}
