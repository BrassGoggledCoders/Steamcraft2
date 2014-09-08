package steamcraft.common.entities;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import steamcraft.common.InitItems;
import steamcraft.common.items.ItemGrappleGun;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityGrapplingHook extends Entity
{
	    private int xTile;
	    private int yTile;
	    private int zTile;
	    private Block block;
	    private boolean inGround;
	    public EntityPlayer player;
	    private int ticksInGround;
	    private int ticksInAir;
	    public Entity caughtEntity;
	    private double posX;
	    private double posY;
	    private double posZ;
	    private double hookYaw;
	    private double hookPitch;
	    private int rotationIncrements;
	    @SideOnly(Side.CLIENT)
	    private double clientMotionX;
	    @SideOnly(Side.CLIENT)
	    private double clientMotionY;
	    @SideOnly(Side.CLIENT)
	    private double clientMotionZ;
	    private ItemGrappleGun gun;

	    public EntityGrapplingHook(World world)
	    {
	        super(world);
	        this.xTile = -1;
	        this.yTile = -1;
	        this.zTile = -1;
	        this.setSize(0.25F, 0.25F);
	        this.ignoreFrustumCheck = true;
	    }

	    @SideOnly(Side.CLIENT)
	    public EntityGrapplingHook(World world, double x, double y, double z, EntityPlayer player)
	    {
	        this(world);
	        this.setPosition(x, y, z);
	        this.ignoreFrustumCheck = true;
	        this.player = player;
	    }

	    public EntityGrapplingHook(World world, EntityPlayer player, ItemGrappleGun gun)
	    {
	        super(world);
	        this.gun = gun;
	        this.xTile = -1;
	        this.yTile = -1;
	        this.zTile = -1;
	        this.ignoreFrustumCheck = true;
	        this.player = player;
	        gun.hook = this;
	        this.setSize(0.25F, 0.25F);
	        this.setLocationAndAngles(player.posX, player.posY + 1.62D - (double)player.yOffset, player.posZ, player.rotationYaw, player.rotationPitch);
	        this.posX -= (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
	        this.posY -= 0.10000000149011612D;
	        this.posZ -= (double)(MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * 0.16F);
	        this.setPosition(this.posX, this.posY, this.posZ);
	        this.yOffset = 0.0F;
	        float f = 0.4F;
	        this.motionX = (double)(-MathHelper.sin(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
	        this.motionZ = (double)(MathHelper.cos(this.rotationYaw / 180.0F * (float)Math.PI) * MathHelper.cos(this.rotationPitch / 180.0F * (float)Math.PI) * f);
	        this.motionY = (double)(-MathHelper.sin(this.rotationPitch / 180.0F * (float)Math.PI) * f);
	        this.func_146035_c(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);
	    }

	    protected void entityInit() {}
	    //On Cast
	    public void func_146035_c(double p_146035_1_, double p_146035_3_, double p_146035_5_, float p_146035_7_, float p_146035_8_)
	    {
	        float f2 = MathHelper.sqrt_double(p_146035_1_ * p_146035_1_ + p_146035_3_ * p_146035_3_ + p_146035_5_ * p_146035_5_);
	        p_146035_1_ /= (double)f2;
	        p_146035_3_ /= (double)f2;
	        p_146035_5_ /= (double)f2;
	        p_146035_1_ += this.rand.nextGaussian() * 0.007499999832361937D * (double)p_146035_8_;
	        p_146035_3_ += this.rand.nextGaussian() * 0.007499999832361937D * (double)p_146035_8_;
	        p_146035_5_ += this.rand.nextGaussian() * 0.007499999832361937D * (double)p_146035_8_;
	        p_146035_1_ *= (double)p_146035_7_;
	        p_146035_3_ *= (double)p_146035_7_;
	        p_146035_5_ *= (double)p_146035_7_;
	        this.motionX = p_146035_1_;
	        this.motionY = p_146035_3_;
	        this.motionZ = p_146035_5_;
	        float f3 = MathHelper.sqrt_double(p_146035_1_ * p_146035_1_ + p_146035_5_ * p_146035_5_);
	        this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(p_146035_1_, p_146035_5_) * 180.0D / Math.PI);
	        this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(p_146035_3_, (double)f3) * 180.0D / Math.PI);
	        this.ticksInGround = 0;
	    }

	    /**
	     * Checks if the entity is in range to render by using the past in distance and comparing it to its average edge
	     * length * 64 * renderDistanceWeight Args: distance
	     */
	    @SideOnly(Side.CLIENT)
	    public boolean isInRangeToRenderDist(double distance)
	    {
	        double d1 = this.boundingBox.getAverageEdgeLength() * 4.0D;
	        d1 *= 64.0D;
	        return distance < d1 * d1;
	    }

	    /**
	     * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
	     * posY, posZ, yaw, pitch
	     */
	    @SideOnly(Side.CLIENT)
	    public void setPositionAndRotation2(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_)
	    {
	        this.posX = p_70056_1_;
	        this.posY = p_70056_3_;
	        this.posZ = p_70056_5_;
	        this.hookYaw = (double)p_70056_7_;
	        this.hookPitch = (double)p_70056_8_;
	        this.rotationIncrements = p_70056_9_;
	        this.motionX = this.clientMotionX;
	        this.motionY = this.clientMotionY;
	        this.motionZ = this.clientMotionZ;
	    }

	    /**
	     * Sets the velocity to the args. Args: x, y, z
	     */
	    @SideOnly(Side.CLIENT)
	    public void setVelocity(double p_70016_1_, double p_70016_3_, double p_70016_5_)
	    {
	        this.clientMotionX = this.motionX = p_70016_1_;
	        this.clientMotionY = this.motionY = p_70016_3_;
	        this.clientMotionZ = this.motionZ = p_70016_5_;
	    }

	    /**
	     * Called to update the entity's position/logic.
	     */
	    public void onUpdate()
	    {
	        if (this.rotationIncrements > 0)
	        {
	            double d7 = this.posX + (this.posX - this.posX) / (double)this.rotationIncrements;
	            double d8 = this.posY + (this.posY - this.posY) / (double)this.rotationIncrements;
	            double d9 = this.posZ + (this.posZ - this.posZ) / (double)this.rotationIncrements;
	            double d1 = MathHelper.wrapAngleTo180_double(this.hookYaw - (double)this.rotationYaw);
	            this.rotationYaw = (float)((double)this.rotationYaw + d1 / (double)this.rotationIncrements);
	            this.rotationPitch = (float)((double)this.rotationPitch + (this.hookPitch - (double)this.rotationPitch) / (double)this.rotationIncrements);
	            --this.rotationIncrements;
	            this.setPosition(d7, d8, d9);
	            this.setRotation(this.rotationYaw, this.rotationPitch);
	        }
	        else
	        {
	            if (!this.worldObj.isRemote && player != null)
	            {
	                ItemStack itemstack = this.player.getCurrentEquippedItem();

	                if (!this.player.isEntityAlive() || itemstack == null || itemstack.getItem() != InitItems.itemGrappleGun || this.getDistanceSqToEntity(this.player) > 1024.0D)
	                {
	                    this.setDead();
	                    gun.hook = null;
	                    return;
	                }

	                if (this.caughtEntity != null)
	                {
	                    if (!this.caughtEntity.isDead)
	                    {
	                        this.posX = this.caughtEntity.posX;
	                        this.posY = this.caughtEntity.boundingBox.minY + (double)this.caughtEntity.height * 0.8D;
	                        this.posZ = this.caughtEntity.posZ;
	                        return;
	                    }

	                    this.caughtEntity = null;
	                }
	            }

	            if (this.inGround)
	            {
	                if (this.worldObj.getBlock(this.xTile, this.yTile, this.zTile) == this.block)
	                {
	                    ++this.ticksInGround;

	                    if (this.ticksInGround == 1200)
	                    {
	                        this.setDead();
	                    }

	                    return;
	                }

	                this.inGround = false;
	                this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
	                this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
	                this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
	                this.ticksInGround = 0;
	                this.ticksInAir = 0;
	            }
	            else
	            {
	                ++this.ticksInAir;
	            }

	            Vec3 vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
	            Vec3 vec3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
	            MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec31, vec3);
	            vec31 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
	            vec3 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

	            if (movingobjectposition != null)
	            {
	                vec3 = Vec3.createVectorHelper(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
	            }

	            Entity entity = null;
	            List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
	            double d0 = 0.0D;
	            double d2;

	            for (int i = 0; i < list.size(); ++i)
	            {
	                Entity entity1 = (Entity)list.get(i);

	                if (entity1.canBeCollidedWith() && (entity1 != this.player || this.ticksInAir >= 5))
	                {
	                    float f = 0.3F;
	                    AxisAlignedBB axisalignedbb = entity1.boundingBox.expand((double)f, (double)f, (double)f);
	                    MovingObjectPosition movingobjectposition1 = axisalignedbb.calculateIntercept(vec31, vec3);

	                    if (movingobjectposition1 != null)
	                    {
	                        d2 = vec31.distanceTo(movingobjectposition1.hitVec);

	                        if (d2 < d0 || d0 == 0.0D)
	                        {
	                            entity = entity1;
	                            d0 = d2;
	                        }
	                    }
	                }
	            }

	            if (entity != null)
	            {
	                movingobjectposition = new MovingObjectPosition(entity);
	            }

	            if (movingobjectposition != null)
	            {
	                if (movingobjectposition.entityHit != null)
	                {
	                    if (movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.player), 0.0F))
	                    {
	                        this.caughtEntity = movingobjectposition.entityHit;
	                    }
	                }
	                else
	                {
	                    this.inGround = true;
	                }
	            }

	            if (!this.inGround)
	            {
	                this.moveEntity(this.motionX, this.motionY, this.motionZ);
	                float f5 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
	                this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / Math.PI);

	                for (this.rotationPitch = (float)(Math.atan2(this.motionY, (double)f5) * 180.0D / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
	                {
	                    ;
	                }

	                while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
	                {
	                    this.prevRotationPitch += 360.0F;
	                }

	                while (this.rotationYaw - this.prevRotationYaw < -180.0F)
	                {
	                    this.prevRotationYaw -= 360.0F;
	                }

	                while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
	                {
	                    this.prevRotationYaw += 360.0F;
	                }

	                this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
	                this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
	                float f6 = 0.92F;

	                if (this.onGround || this.isCollidedHorizontally)
	                {
	                    f6 = 0.5F;
	                }

	                byte b0 = 5;
	                double d10 = 0.0D;

	                for (int j = 0; j < b0; ++j)
	                {
	                    double d3 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(j + 0) / (double)b0 - 0.125D + 0.125D;
	                    double d4 = this.boundingBox.minY + (this.boundingBox.maxY - this.boundingBox.minY) * (double)(j + 1) / (double)b0 - 0.125D + 0.125D;
	                    AxisAlignedBB axisalignedbb1 = AxisAlignedBB.getBoundingBox(this.boundingBox.minX, d3, this.boundingBox.minZ, this.boundingBox.maxX, d4, this.boundingBox.maxZ);

	                    if (this.worldObj.isAABBInMaterial(axisalignedbb1, Material.water))
	                    {
	                        d10 += 1.0D / (double)b0;
	                    }
	                }

	                d2 = d10 * 2.0D - 1.0D;
	                this.motionY += 0.03999999910593033D * d2;

	                if (d10 > 0.0D)
	                {
	                    f6 = (float)((double)f6 * 0.9D);
	                    this.motionY *= 0.8D;
	                }

	                this.motionX *= (double)f6;
	                this.motionY *= (double)f6;
	                this.motionZ *= (double)f6;
	                this.setPosition(this.posX, this.posY, this.posZ);
	            }
	        }
	    }

	    /**
	     * (abstract) Protected helper method to write subclass entity data to NBT.
	     */
	    public void writeEntityToNBT(NBTTagCompound comp)
	    {
	        comp.setShort("xTile", (short)this.xTile);
	        comp.setShort("yTile", (short)this.yTile);
	        comp.setShort("zTile", (short)this.zTile);
	        comp.setByte("inTile", (byte)Block.getIdFromBlock(this.block));
	        comp.setByte("inGround", (byte)(this.inGround ? 1 : 0));
	    }

	    /**
	     * (abstract) Protected helper method to read subclass entity data from NBT.
	     */
	    public void readEntityFromNBT(NBTTagCompound p_70037_1_)
	    {
	        this.xTile = p_70037_1_.getShort("xTile");
	        this.yTile = p_70037_1_.getShort("yTile");
	        this.zTile = p_70037_1_.getShort("zTile");
	        this.block = Block.getBlockById(p_70037_1_.getByte("inTile") & 255);
	        this.inGround = p_70037_1_.getByte("inGround") == 1;
	    }

	    @SideOnly(Side.CLIENT)
	    public float getShadowSize()
	    {
	        return 0.0F;
	    }
	    //Retract Hook and Calculate Damage
	    public int func_146034_e()
	    {
	        if (this.worldObj.isRemote)
	        {
	            return 0;
	        }
	        else
	        {
	            byte damageTaken = 0;

	            if (this.caughtEntity != null)
	            {
	            	if(caughtEntity.height < player.height)
	            	{
		                double d0 = this.player.posX - this.posX;
		                double d2 = this.player.posY - this.posY;
		                double d4 = this.player.posZ - this.posZ;
		                double d6 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2 + d4 * d4);
		                double d8 = 0.3D;
		                this.caughtEntity.motionX += d0 * d8;
		                this.caughtEntity.motionY += d2 * d8 + (double)MathHelper.sqrt_double(d6) * 0.08D;
		                this.caughtEntity.motionZ += d4 * d8;
		                damageTaken = 3;
	            	}
	            	else
	            	{
	            		double d0 = this.player.posX - this.posX;
		                double d2 = this.player.posY - this.posY;
		                double d4 = this.player.posZ - this.posZ;
		                double d6 = (double)MathHelper.sqrt_double(d0 * d0 + d2 * d2 + d4 * d4);
		                double d8 = 0.3D;
		                player.motionX += -(d0 * d8);
		                player.motionY += -(d2 * d8 + (double)MathHelper.sqrt_double(d6) * 0.08D);
		                player.motionZ += -(d4 * d8);
		                damageTaken = 3;
	            	}
	            }
	              /*  double d1 = this.player.posX - this.posX;
	                double d3 = this.player.posY - this.posY;
	                double d5 = this.player.posZ - this.posZ;
	                double d7 = (double)MathHelper.sqrt_double(d1 * d1 + d3 * d3 + d5 * d5);
	                double d9 = 0.1D;
	                player.motionX = -(d1 * d9);
	                player.motionY = -(d3 * d9 + (double)MathHelper.sqrt_double(d7) * 0.08D);
	                player.motionZ = -(d5 * d9);
	                b0 = 1;*/

	            if (this.inGround)
	            {
	                damageTaken = 2;
	            }

	            this.setDead();
	            gun.hook = null;
	            return damageTaken;
	        }
	    }

	    /**
	     * Will get destroyed next tick.
	     */
	    public void setDead()
	    {
	        super.setDead();

	        if (this.player != null)
	        {
	            gun.hook = null;
	        }
	    }
}
