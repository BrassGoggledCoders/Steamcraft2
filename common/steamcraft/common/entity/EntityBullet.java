package common.steamcraft.common.entity;

import common.steamcraft.common.item.ModGuns;
import common.steamcraft.common.lib2.EntityIDs;
import common.steamcraft.common.lib2.LibInfo;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.*;
import net.minecraft.world.World;

import java.util.List;

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

    public EntityBullet(World world, double d1, double d2, double d3)
    {
        super(world);
        this.renderDistanceWeight = 10.0D;
        this.setSize(0.5F, 0.5F);
        this.setPosition(d1, d2, d3);
        this.yOffset = 0.0F;
    }
	
    public EntityBullet(World world, EntityLivingBase living1, EntityLivingBase living2, float par4, float par5)
    {
        super(world);
        this.renderDistanceWeight = 10.0D;
        this.shootingEntity = living1;

        if(living1 instanceof EntityPlayer)
        {
            this.canBePickedUp = 1;
        }

        this.posY = living1.posY + (double)living1.getEyeHeight() - 0.10000000149011612D;
        double d0 = living2.posX - living1.posX;
        double d1 = living2.boundingBox.minY + (double)(living2.height / 3.0F) - this.posY;
        double d2 = living2.posZ - living1.posZ;
        double d3 = (double) MathHelper.sqrt_double(d0 * d0 + d2 * d2);

        if(d3 >= 1.0E-7D)
        {
            float f2 = (float)(Math.atan2(d2, d0) * 180.0D / Math.PI) - 90.0F;
            float f3 = (float)(-(Math.atan2(d1, d3) * 180.0D / Math.PI));
            double d4 = d0 / d3;
            double d5 = d2 / d3;
            this.setLocationAndAngles(living1.posX + d4, this.posY, living1.posZ + d5, f2, f3);
            this.yOffset = 0.0F;
            float f4 = (float)d3 * 0.2F;
            this.setThrowableHeading(d0, d1 + (double)f4, d2, par4, par5);
        }
    }

    public EntityBullet(World world, EntityLivingBase living, int damage, int accuracy)
    {
        super(world);
        this.renderDistanceWeight = 10.0D;
        this.shootingEntity = living;

        if(living instanceof EntityPlayer)
        {
            this.canBePickedUp = 1;
        }

        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(living.posX, living.posY + (double)living.getEyeHeight(), living.posZ, living.rotationYaw, living.rotationPitch);
        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.posY -= 0.10000000149011612D;
        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI));
        this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI));
        this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 1.5F, accuracy);
        this.accuracy = accuracy;
        this.damage = damage;
    }
	
	@Override
	protected void entityInit() 
	{
		this.dataWatcher.addObject(EntityIDs.BULLET_ID, Byte.valueOf((byte)0));
	}
	
	@Override
	public void setThrowableHeading(double d, double d1, double d2, float f, float f1)
	{
		int i = this.accuracy;
		float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
		d /= f2;
		d1 /= f2;
		d2 /= f2;
		d += rand.nextGaussian() * 0.0034999998323619365D * (double)f1 * (double)i / 5;
		d1 += rand.nextGaussian() * 0.0034999998323619365D * (double)f1 * (double)i / 5;
		d2 += rand.nextGaussian() * 0.0034999998323619365D * (double)f1 * (double)i / 5;
		d *= f;
		d1 *= f;
		d2 *= f;
		motionX = d;
		motionY = d1;
		motionZ = d2;
		float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
		prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
		prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f3) * 180D) / 3.1415927410125732D);
		timeTillDeath = 0;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void setPositionAndRotation2(double d1, double d2, double d3, float f1, float f2, int i)
    {
        this.setPosition(d1, d2, d3);
        this.setRotation(f1, f2);
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void setVelocity(double d, double d1, double d2)
	{
		motionX = d;
		motionY = d1;
		motionZ = d2;
		
		if(prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
		{
			float f = MathHelper.sqrt_double(d * d + d2 * d2);
			prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / 3.1415927410125732D);
			prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f) * 180D) / 3.1415927410125732D);
		}
	}
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		if(flyTime > 1000)
		{
			setDead();
		}
		if(prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
		{
			float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
			prevRotationYaw = rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
			prevRotationPitch = rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / 3.1415927410125732D);
		}
		if(arrowShake > 0)
		{
			arrowShake--;
		}
		if(inGround)
		{
			int i = worldObj.getBlockId(xTile, yTile, zTile);
			
			if(i != inTile)
			{
				inGround = false;
				motionX *= rand.nextFloat() * 0.2F;
				motionY *= rand.nextFloat() * 0.2F;
				motionZ *= rand.nextFloat() * 0.2F;
				timeTillDeath = 0;
				flyTime = 0;
			} else
			{
				timeTillDeath++;
				
				if(timeTillDeath == 1200)
				{
					this.setDead();
				}
				
				return;
			}
		} else
		{
			flyTime++;
		}
		
		Vec3 vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
        Vec3 vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks_do_do(vec3, vec31, false, true);
        vec3 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
        vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if(movingobjectposition != null)
        {
            vec31 = this.worldObj.getWorldVec3Pool().getVecFromPool(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
        }
		
		Entity entity = null;
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D, 1.0D, 1.0D));
		double d = 0.0D;
		
		for(int j = 0; j < list.size(); j++)
		{
			Entity entity1 = (Entity)list.get(j);
			
			if(!entity1.canBeCollidedWith() || entity1 == shootingEntity && flyTime < 5)
			{
				continue;
			}
			
			float f4 = 0.3F;
			AxisAlignedBB axisalignedbb = entity1.boundingBox.expand(f4, f4, f4);
			MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);
			
			if(movingobjectposition1 == null)
			{
				continue;
			}
			
			double d1 = vec3.distanceTo(movingobjectposition1.hitVec);
			
			if(d1 < d || d == 0.0D)
			{
				entity = entity1;
				d = d1;
			}
		}
		if(entity != null)
		{
			movingobjectposition = new MovingObjectPosition(entity);
		}
		if(movingobjectposition != null)
		{
			if(movingobjectposition.entityHit != null)
			{
				if(movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this), damage))
				{
					worldObj.playSoundAtEntity(this, LibInfo.SC2_PREFIX + "hitflesh", 1.0F, 1.2F / (rand.nextFloat() * 0.2F + 0.9F));
					this.setDead();
				} else
				{
					motionX *= 0.10000000149011612D;
					motionY *= 0.10000000149011612D;
					motionZ *= 0.10000000149011612D;
					flyTime = 0;
					this.setDead();
				}
			} else
			{
				xTile = movingobjectposition.blockX;
				yTile = movingobjectposition.blockY;
				zTile = movingobjectposition.blockZ;
				inTile = worldObj.getBlockId(xTile, yTile, zTile);
				
				if(inTile == Block.glass.blockID || inTile == Block.glowStone.blockID || inTile == Block.leaves.blockID)
				{
					Block block = Block.blocksList[inTile];
					worldObj.playSoundEffect((double)xTile + 0.5D, (double)yTile + 0.5D, (double)zTile + 0.5D, block.stepSound.getBreakSound(), 1.0F, 1.0F);
					worldObj.setBlockToAir(xTile, yTile, zTile);
				} else
				{
					motionX = (float)(movingobjectposition.hitVec.xCoord - posX);
					motionY = (float)(movingobjectposition.hitVec.yCoord - posY);
					motionZ = (float)(movingobjectposition.hitVec.zCoord - posZ);
					float f1 = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
					posX -= (motionX / (double)f1) * 0.05000000074505806D;
					posY -= (motionY / (double)f1) * 0.05000000074505806D;
					posZ -= (motionZ / (double)f1) * 0.05000000074505806D;
					worldObj.playSoundAtEntity(this, LibInfo.SC2_PREFIX + "hitblock", 1.0F, 1.0F);
					this.setDead();
				}
			}
		}
		
		posX += motionX * 3D;
		posY += motionY * 3D;
		posZ += motionZ * 3D;
		float f2 = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
		rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / 3.1415927410125732D);
		for (rotationPitch = (float)((Math.atan2(motionY, f2) * 180D) / 3.1415927410125732D); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }
		for (; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }
		for (; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }
		for (; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }
		rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
		rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
		float f3 = 0.99F;
		float f5 = 0.03F;
		
		if(handleWaterMovement())
		{
			this.setDead(); // Bullets can't go through water, silly!
			
			/*
			for(int k = 0; k < 4; k++)
			{
				float f6 = 0.25F;
				worldObj.spawnParticle("bubble", posX - motionX * (double)f6, posY - motionY * (double)f6, posZ - motionZ * (double)f6, motionX, motionY, motionZ);
			}
			
			f3 = 0.8F;
			*/
		}
		
		motionX *= f3;
		motionY *= f3;
		motionZ *= f3;
		setPosition(posX, posY, posZ);
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setShort("xTile", (short)xTile);
		nbt.setShort("yTile", (short)yTile);
		nbt.setShort("zTile", (short)zTile);
		nbt.setByte("inTile", (byte)inTile);
		nbt.setByte("shake", (byte)arrowShake);
		nbt.setByte("inGround", (byte)(inGround ? 1 : 0));
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound nbt)
	{
		xTile = nbt.getShort("xTile");
		yTile = nbt.getShort("yTile");
		zTile = nbt.getShort("zTile");
		inTile = nbt.getByte("inTile") & 0xff;
		arrowShake = nbt.getByte("shake") & 0xff;
		inGround = nbt.getByte("inGround") == 1;
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer player)
    {
        if(!this.worldObj.isRemote && this.inGround && this.arrowShake <= 0)
        {
            boolean flag = this.canBePickedUp == 1 || this.canBePickedUp == 2 && player.capabilities.isCreativeMode;

            if(this.canBePickedUp == 1 && !player.inventory.addItemStackToInventory(new ItemStack(ModGuns.musketRound, 1)))
            {
                flag = false;
            }

            if(flag)
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