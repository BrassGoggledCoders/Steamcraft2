package steamcraft.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityGrapplingHook extends Entity
{
	EntityPlayer player;
	private int unknown;

	public EntityGrapplingHook(World world)
	{
		super(world);

	}
	public EntityGrapplingHook(World world, EntityPlayer player)
	{
		super(world);
		this.player = player;
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
        this.setMotion(this.motionX, this.motionY, this.motionZ, 1.5F, 1.0F);
	}
/*
 * double d1 = this.field_146042_b.posX - this.posX;
	double d3 = this.field_146042_b.posY - this.posY;
	double d5 = this.field_146042_b.posZ - this.posZ;
	double d7 = (double)MathHelper.sqrt_double(d1 * d1 + d3 * d3 + d5 * d5);
	double d9 = 0.1D;
	entityitem.motionX = d1 * d9;
	entityitem.motionY = d3 * d9 + (double)MathHelper.sqrt_double(d7) * 0.08D;
	entityitem.motionZ = d5 * d9;
 */

	@Override
	protected void entityInit() {}

	@Override
	protected void readEntityFromNBT(NBTTagCompound p_70037_1_)
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound p_70014_1_)
	{
		// TODO Auto-generated method stub

	}
	public void setMotion(double p_146035_1_, double p_146035_3_, double p_146035_5_, float p_146035_7_, float p_146035_8_)
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
        this.unknown = 0;
    }
}
