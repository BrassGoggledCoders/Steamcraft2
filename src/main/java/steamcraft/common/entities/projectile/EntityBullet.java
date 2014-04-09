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
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import steamcraft.common.config.Config;
import steamcraft.common.config.ConfigItems;
import steamcraft.common.lib.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * NOTE: Please do not tamper with this file unless 
 * you know vectors and trigonometric functions very 
 * well. Thank you. I have, however, cleaned up the
 * code to make things easier to understand.
 * 
 * @author Surseance (Johnny Eatmon)
 *
 */
public class EntityBullet extends Entity implements IProjectile
{
	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	private int inTile;
	private int inData;
	private boolean inGround;
	public int canBePickedUp;
	public int arrowShake;
	public Entity shootingEntity;
	private int timeTillDeath;
	private int flyTime;
	private int damage;
	private final float size = 1F;
	private int accuracy;

	public EntityBullet(World world)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.5F, 0.5F);
	}

	public EntityBullet(World world, double dx, double dy, double dz)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.setSize(0.5F, 0.5F);
		this.setPosition(dx, dy, dz);
		this.yOffset = 0.0F;
	}

	public EntityBullet(World world, EntityLivingBase shooter, EntityLivingBase target, float frotY, float frotP)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = shooter;

		if (shooter instanceof EntityPlayer)
		{
			this.canBePickedUp = 1;
		}

		this.posY = shooter.posY + shooter.getEyeHeight() - 0.10000000149011612D;
		double dx = target.posX - shooter.posX;
		double dy = target.boundingBox.minY + target.height / 3.0F - this.posY;
		double dz = target.posZ - shooter.posZ;
		double magnitude = MathHelper.sqrt_double(dx * dx + dz * dz); // It's a magnitude vector! Yay, math!

		if (magnitude >= 1.0E-7D)
		{
			float fx = (float)(Math.atan2(dz, dx) * 180.0D / Math.PI) - 90.0F;
			float fy = (float)(-(Math.atan2(dy, magnitude) * 180.0D / Math.PI));
			double dlx = dx / magnitude;
			double dlz = dz / magnitude;
			this.setLocationAndAngles(shooter.posX + dlx, this.posY, shooter.posZ + dlz, fx, fy);
			this.yOffset = 0.0F;
			float height = (float)magnitude * 0.2F;
			this.setThrowableHeading(dx, dy + height, dz, frotY, frotP);
		}
	}

	public EntityBullet(World world, EntityLivingBase shooter, int damage, int accuracy)
	{
		super(world);
		this.renderDistanceWeight = 10.0D;
		this.shootingEntity = shooter;

		if(shooter instanceof EntityPlayer)
		{
			this.canBePickedUp = 1;
		}

		this.setSize(0.5F, 0.5F);
		this.setLocationAndAngles(shooter.posX, shooter.posY + shooter.getEyeHeight(), shooter.posZ, shooter.rotationYaw, shooter.rotationPitch);
		this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
		this.posY -= 0.10000000149011612D;
		this.posZ -= MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F;
		this.setPosition(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = -MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI);
		this.motionZ = MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI);
		this.motionY = (-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
		this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 1.5F, accuracy);
		this.accuracy = accuracy;
		this.damage = damage;
	}

	@Override
	protected void entityInit() 
	{
		this.dataWatcher.addObject(Config.entBulletId, Byte.valueOf((byte)0));
	}

	@Override
	public void setThrowableHeading(double dx, double dy, double dz, float frotY, float frotP)
	{
		int accuracy = this.accuracy;
		float f2 = MathHelper.sqrt_double(dx * dx + dy * dy + dz * dz);
		dx /= f2;
		dy /= f2;
		dz /= f2;
		dx += this.rand.nextGaussian() * 0.0034999998323619365D * frotP * accuracy / 5;
		dy += this.rand.nextGaussian() * 0.0034999998323619365D * frotP * accuracy / 5;
		dz += this.rand.nextGaussian() * 0.0034999998323619365D * frotP * accuracy / 5;
		dx *= frotY;
		dy *= frotY;
		dz *= frotY;
		this.motionX = dx;
		this.motionY = dy;
		this.motionZ = dz;
		float magnitude = MathHelper.sqrt_double(dx * dx + dz * dz);
		this.prevRotationYaw = this.rotationYaw = (float)((Math.atan2(dx, dz) * 180D) / 3.1415927410125732D);
		this.prevRotationPitch = this.rotationPitch = (float)((Math.atan2(dy, magnitude) * 180D) / 3.1415927410125732D);
		this.timeTillDeath = 0;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double dx, double dy, double dz, float frotY, float frotP, int i)
	{
		this.setPosition(dx, dy, dz);
		this.setRotation(frotY, frotP);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void setVelocity(double dx, double dy, double dz)
	{
		this.motionX = dx;
		this.motionY = dy;
		this.motionZ = dz;

		if ((this.prevRotationPitch == 0.0F) && (this.prevRotationYaw == 0.0F))
		{
			float magnitude = MathHelper.sqrt_double(dx * dx + dz * dz);
			this.prevRotationYaw = this.rotationYaw = (float)((Math.atan2(dx, dz) * 180D) / 3.1415927410125732D);
			this.prevRotationPitch = this.rotationPitch = (float)((Math.atan2(dy, magnitude) * 180D) / 3.1415927410125732D);
		}
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();

		if (this.flyTime > 1000)
		{
			this.setDead();
		}

		if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F)
		{
			float magnitude = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = this.rotationYaw = (float)((Math.atan2(this.motionX, this.motionZ) * 180D) / 3.1415927410125732D);
			this.prevRotationPitch = this.rotationPitch = (float)((Math.atan2(this.motionY, magnitude) * 180D) / 3.1415927410125732D);
		}

		if (this.arrowShake > 0)
		{
			arrowShake--;
		}

		if (this.inGround)
		{
			int bid = worldObj.getBlockId(xTile, yTile, zTile);

			if (bid != this.inTile)
			{
				this.inGround = false;
				this.motionX *= this.rand.nextFloat() * 0.2F;
				this.motionY *= this.rand.nextFloat() * 0.2F;
				this.motionZ *= this.rand.nextFloat() * 0.2F;
				this.timeTillDeath = 0;
				this.flyTime = 0;
			} 
			else
			{
				this.timeTillDeath++;

				if (this.timeTillDeath == 1200)
				{
					this.setDead();
				}

				return;
			}
		} 
		else
		{
			this.flyTime++;
		}

		Vec3 posVector = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
		Vec3 velVector = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition mop = this.worldObj.rayTraceBlocks_do_do(posVector, velVector, false, true);
		posVector = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
		velVector = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

		if (mop != null)
		{
			velVector = this.worldObj.getWorldVec3Pool().getVecFromPool(mop.hitVec.xCoord, mop.hitVec.yCoord, mop.hitVec.zCoord);
		}

		Entity entity = null;
		List entList = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
		double distance = 0.0D;

		for (int listSize = 0; listSize < entList.size(); listSize++)
		{
			Entity collidableEnt = (Entity)entList.get(listSize);

			if (!collidableEnt.canBeCollidedWith() || collidableEnt == shootingEntity && flyTime < 5)
			{
				continue;
			}

			float amount = 0.3F;
			AxisAlignedBB aaBB = collidableEnt.boundingBox.expand(amount, amount, amount);
			MovingObjectPosition objectInVector = aaBB.calculateIntercept(posVector, velVector);

			if (objectInVector == null)
			{
				continue;
			}

			double distanceToObject = posVector.distanceTo(objectInVector.hitVec);

			if (distanceToObject < distance || distance == 0.0D) // Orthogonal check
			{
				entity = collidableEnt;
				distance = distanceToObject;
			}
		}

		if (entity != null)
		{
			mop = new MovingObjectPosition(entity);
		}

		if (mop != null)
		{
			if (mop.entityHit != null)
			{
				if (mop.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this), this.damage))
				{
					this.worldObj.playSoundAtEntity(this, LibInfo.PREFIX + "hitflesh", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
					this.setDead();
				} 
				else
				{
					this.motionX *= 0.10000000149011612D;
					this.motionY *= 0.10000000149011612D;
					this.motionZ *= 0.10000000149011612D;
					this.flyTime = 0;
					this.setDead();
				}
			} 
			else
			{
				this.xTile = mop.blockX;
				this.yTile = mop.blockY;
				this.zTile = mop.blockZ;
				this.inTile = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);

				if (this.inTile == Block.glass.blockID || this.inTile == Block.glowStone.blockID)
				{
					Block block = Block.blocksList[this.inTile];
					this.worldObj.playSoundEffect(this.xTile + 0.5D, this.yTile + 0.5D, this.zTile + 0.5D, block.stepSound.getBreakSound(), 1.0F, 1.0F);
					this.worldObj.setBlockToAir(this.xTile, this.yTile, this.zTile);
				} 
				else
				{
					this.motionX = (float)(mop.hitVec.xCoord - this.posX);
					this.motionY = (float)(mop.hitVec.yCoord - this.posY);
					this.motionZ = (float)(mop.hitVec.zCoord - this.posZ);
					float magnitude = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
					this.posX -= (motionX / magnitude) * 0.05000000074505806D;
					this.posY -= (motionY / magnitude) * 0.05000000074505806D;
					this.posZ -= (motionZ / magnitude) * 0.05000000074505806D;
					this.worldObj.playSoundAtEntity(this, LibInfo.PREFIX + "hitblock", 1.0F, 1.0F);
					this.setDead();
				}
			}
		}

		this.posX += this.motionX * 3.0D;
		this.posY += this.motionY * 3.0D;
		this.posZ += this.motionZ * 3.0D;
		float magnitude = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = (float)((Math.atan2(this.motionX, this.motionZ) * 180D) / 3.1415927410125732D);
		
		for (this.rotationPitch = (float)((Math.atan2(this.motionY, magnitude) * 180D) / 3.1415927410125732D); this.rotationPitch - this.prevRotationPitch < -180F; this.prevRotationPitch -= 360F) {}
		
		for (;this.rotationPitch - this.prevRotationPitch >= 180F; this.prevRotationPitch += 360F) {}
		
		for (;this.rotationYaw - this.prevRotationYaw < -180F; this.prevRotationYaw -= 360F) {}
		
		for (;this.rotationYaw - this.prevRotationYaw >= 180F; this.prevRotationYaw += 360F) {}
		
		this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
		this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
		float speed = 0.99F;

		if (this.handleWaterMovement())
		{
			this.setDead(); 
			// Bullets can't go through water, silly!
			//for(int k = 0; k < 4; k++)
			//{
			//	float f6 = 0.25F;
			//	worldObj.spawnParticle("bubble", posX - motionX * (double)f6, posY - motionY * (double)f6, posZ - motionZ * (double)f6, motionX, motionY, motionZ);
			//}
			//f3 = 0.8F;
		}

		this.motionX *= speed;
		this.motionY *= speed;
		this.motionZ *= speed;
		this.setPosition(posX, posY, posZ);
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		tagCompound.setShort("xTile", (short)this.xTile);
		tagCompound.setShort("yTile", (short)this.yTile);
		tagCompound.setShort("zTile", (short)this.zTile);
		tagCompound.setByte("inTile", (byte)this.inTile);
		tagCompound.setByte("shake", (byte)this.arrowShake);
		tagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound tagCompound)
	{
		this.xTile = tagCompound.getShort("xTile");
		this.yTile = tagCompound.getShort("yTile");
		this.zTile = tagCompound.getShort("zTile");
		this.inTile = tagCompound.getByte("inTile") & 0xff;
		this.arrowShake = tagCompound.getByte("shake") & 0xff;
		this.inGround = tagCompound.getByte("inGround") == 1;
	}

	@Override
	public void onCollideWithPlayer(EntityPlayer player)
	{
		if (!this.worldObj.isRemote && this.inGround && this.arrowShake <= 0)
		{
			boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2 && player.capabilities.isCreativeMode;

			if (this.canBePickedUp == 1 && !player.inventory.addItemStackToInventory(new ItemStack(ConfigItems.itemMusketRound, 1)))
			{
				flag = false;
			}

			if (flag)
			{
				this.playSound("random.pop", 0.2F, ((this.rand.nextFloat() - this.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
				player.onItemPickup(this, 1);
				this.setDead();
			}
		}
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getShadowSize()
	{
		return 0.0F;
	}

	@Override
	public boolean canAttackWithItem()
	{
		return false;
	}
}
